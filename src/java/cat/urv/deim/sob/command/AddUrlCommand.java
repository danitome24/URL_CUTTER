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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        out.println("ADD URL COMMAND");
        String urlName = null;
        HttpSession userSession = request.getSession(false);
        User user = (User)userSession.getAttribute(Config.ATTR_SERVLET_USER);
        Url urlFind = new Url();
        url.setUrl(request.getParameter("url"));
        url.setUrlShort(request.getParameter("urlShort"));
        out.println("URL SHORT: "+url.getUrlShort());
        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            boolean insert = false;
            urlFind = urlDao.findByUrlShort(url);
            url.setIdUrl(urlFind.getIdUrl());
            if (urlFind.getUrlShort() == null) {
                out.println("No hay URL igual en la base de datos");
                insert = urlDao.addUrl(url, user.getId());
                if (insert) {
                    out.println("Se ha insertado la nueva URL");
                } else {
                    out.println("NO SE HA PODIDO INSERTAR LA URL");
                }
            } else {
                boolean exists = urlDao.findRelationByUrl(url.getIdUrl(), user.getId());
                if (!exists) {
                    urlDao.insertRelation(url.getIdUrl(), user.getId());
                    out.println("Insert en la relacion hecha");
                } else {
                    out.println("YA EXISTE RELACION");
                }
            }
            request.setAttribute("insertUrl", "Url introduida!");
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/addurl.jsp").forward(request, response);
        } catch (DaoException ex) {
            ex.toString();
        }

    }

}
