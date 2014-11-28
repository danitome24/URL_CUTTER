/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import cat.urv.deim.sob.model.IUrlDao;
import cat.urv.deim.sob.model.Url;
import cat.urv.deim.sob.model.UrlDaoFactory;
import cat.urv.deim.sob.model.User;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danie_000
 */
public class AddUrlCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Url url = new Url();

        String urlName = null;
        HttpSession userSession = request.getSession(false);
        User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        Url urlFind = new Url();
        url.setUrl(request.getParameter("url"));
        url.setUrlShort(request.getParameter("urlShort"));
        boolean exists = false;
        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            boolean insert = false;
            urlFind = urlDao.findByUrlShort(url);
            url.setIdUrl(urlFind.getIdUrl());
            if (urlFind.getUrlShort() == null) {
                insert = urlDao.addUrl(url, user.getId());

            } else {
                exists = urlDao.findRelationByUrl(url.getIdUrl(), user.getId());
                if (!exists) {
                    urlDao.insertRelation(url.getIdUrl(), user.getId());
                } 
            }
            if(exists){
                request.setAttribute("repeatUrl", "This url is already inserted");
            }else{
            request.setAttribute("insertUrl", "Url introduida!");
            }
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/addurl.jsp").forward(request, response);
        } catch (DaoException ex) {
            ex.toString();
        }

    }

}
