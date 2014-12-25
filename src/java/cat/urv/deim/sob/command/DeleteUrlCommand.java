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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
public class DeleteUrlCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idUrlToRemove = Integer.parseInt(request.getParameter("id"));
        HttpSession userSession = request.getSession(true);
        User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        boolean urlDeleted = false;
        try {
            urlDeleted = urlDao.deleteUrl(idUrlToRemove, user.getId());
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/http://localhost:8080/S/index.do?form_action=showUrl&page=1").forward(request, response);
        } catch (DaoException ex) {
            ex.toString();
        }
    }
    
}
