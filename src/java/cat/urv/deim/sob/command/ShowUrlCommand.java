/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import cat.urv.deim.sob.model.IUrlDao;
import cat.urv.deim.sob.model.UrlDaoFactory;
import cat.urv.deim.sob.model.User;
import java.io.IOException;
import static java.lang.System.out;
import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danie_000
 */
public class ShowUrlCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 0;

        out.println("Show Url Command");
        page = Integer.parseInt(request.getParameter("page"));
        out.println("Pagina: "+page);
        HttpSession userSession = request.getSession(true);
        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        out.println("ID usuario: "+user.getId());
        try {
            Collection allUrl = urlDao.showUrl(user.getId(),page);
            userSession.setAttribute(Config.ATTR_URL_NAME, allUrl);
            float numOfRow = urlDao.getNumberOfRow();
            float division = numOfRow / Config.NUM_OF_ROWS_PER_PAGE;
            int numOfPages = (int)Math.ceil(division);
            out.println("Nmber of pages: "+numOfPages);

            userSession.setAttribute("numPage", numOfPages);
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DaoException ex) {
            ex.toString();
        }
    }

}
