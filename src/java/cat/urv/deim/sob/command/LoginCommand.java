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
        HttpSession userSession = request.getSession(true);
        usuari.setUserName(request.getParameter(Config.ATTR_USER_USERNAME));
        usuari.setPassword(request.getParameter(Config.ATTR_USER_PASSWORD));
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        //userSession.setAttribute("errorLogin","");
        try {
            isValid = userDAO.login(usuari);
            if (isValid) {            
                User idUser = userDAO.findUserByName(usuari);
                Cookie loginCookie = new Cookie(Config.COOKIE_USER, usuari.getUserName());
                loginCookie.setMaxAge(30 * 60); //expire in 30 min
                usuari.setId(idUser.getId());
                response.addCookie(loginCookie);
                userSession.setAttribute(Config.ATTR_SERVLET_USER, idUser);
            }
        } catch (DaoException ex) {
            ex.printStackTrace();
        }
        if (isValid) {
            response.sendRedirect("http://localhost:8080/SOB/login.do?form_action=showUrl&page=1");
        } else {
            userSession.setAttribute("errorLogin","El usuario no se encuentra registrado");
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

}
