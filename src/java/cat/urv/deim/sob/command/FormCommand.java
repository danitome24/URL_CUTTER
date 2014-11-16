/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import cat.urv.deim.sob.model.IUserDao;
import cat.urv.deim.sob.model.User;
import cat.urv.deim.sob.model.UserDaoFactory;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author danie_000
 */
public class FormCommand implements Command {

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User usuari = new User();
            boolean inserit = false;
            usuari.setFirstName(request.getParameter(Config.ATTR_USER_FIRSTNAME));
            usuari.setPassword(request.getParameter(Config.ATTR_USER_PASSWORD));
            usuari.setLastName(request.getParameter(Config.ATTR_USER_LASTNAME));
            usuari.setUserName(request.getParameter(Config.ATTR_USER_USERNAME));
            usuari.setEmail(request.getParameter(Config.ATTR_USER_EMAIL));

            IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
            inserit = userDAO.add(usuari);
            if (inserit) {
                out.println("Nuevo usuario insertado en la BD");
                HttpSession userSession = request.getSession(true);
                Cookie loginCookie = new Cookie(Config.COOKIE_USER, usuari.getUserName());
                loginCookie.setMaxAge(30 * 60); //expire in 30 min
                response.addCookie(loginCookie);              
                User id = userDAO.findUserByName(usuari);
                usuari.setId(id.getId());
                userSession.setAttribute(Config.ATTR_SERVLET_USER, usuari);
            }

            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (DaoException ex) {
            ex.printStackTrace();
        }

    }
}
