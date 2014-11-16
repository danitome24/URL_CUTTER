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
public class LoginCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User usuari = new User();
        boolean isValid = false;
        usuari.setUserName(request.getParameter(Config.ATTR_USER_USERNAME));
        usuari.setPassword(request.getParameter(Config.ATTR_USER_PASSWORD));
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            isValid = userDAO.login(usuari);
            if (isValid) {
                HttpSession userSession = request.getSession(true);
                int idUser = userDAO.findUserByName(usuari);
                userSession.setAttribute(Config.ATTR_USER_ID, idUser);
                Cookie loginCookie = new Cookie(Config.COOKIE_USER, usuari.getUserName());
                loginCookie.setMaxAge(30 * 60); //expire in 30 min
                response.addCookie(loginCookie);
                userSession.setAttribute(Config.ATTR_SERVLET_USER, usuari);
            }
        } catch (DaoException ex) {
            ex.printStackTrace();
        }
        if (isValid) {
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {

            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

}
