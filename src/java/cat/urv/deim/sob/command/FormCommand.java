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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author danie_000
 */
public class FormCommand implements Command {

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void execute(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User usuari = new User();
            IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
            boolean inserit = false;
            usuari.setFirstName(request.getParameter(Config.ATTR_USER_FIRSTNAME));
            usuari.setPassword(request.getParameter(Config.ATTR_USER_PASSWORD));
            usuari.setLastName(request.getParameter(Config.ATTR_USER_LASTNAME));
            usuari.setUserName(request.getParameter(Config.ATTR_USER_USERNAME));
            usuari.setEmail(request.getParameter(Config.ATTR_USER_EMAIL));

            boolean isRepeat = userDAO.isUsernameRepeat(usuari);
            if (!isRepeat) {
                boolean passwordIsValid = validatePassword(request.getParameter("password"));
                if (passwordIsValid) {
                    if (request.getParameter("password").equals(request.getParameter("password2"))) {
                        inserit = userDAO.add(usuari);
                        if (inserit) {
                            
                            HttpSession userSession = request.getSession(true);
                            Cookie loginCookie = new Cookie(Config.COOKIE_USER, usuari.getUserName());
                            loginCookie.setMaxAge(30 * 60); //expire in 30 min
                            response.addCookie(loginCookie);
                            User id = userDAO.findUserByName(usuari);
                            usuari.setId(id.getId());
                            userSession.setAttribute(Config.ATTR_SERVLET_USER, usuari);
                        }
                        ServletContext context = request.getSession().getServletContext();
                        context.getRequestDispatcher("/http://localhost:8080/SOB/login.do?form_action=showUrl&page=1").forward(request, response);
                    } else {
                        request.setAttribute("password", "The two passwords are different");
                        ServletContext context = request.getSession().getServletContext();
                        context.getRequestDispatcher("/registre.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("password", "The password doesn't comply the requirements");
                    ServletContext context = request.getSession().getServletContext();
                    context.getRequestDispatcher("/registre.jsp").forward(request, response);
                }
            } else {

                request.setAttribute("user", "This user is used");
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/registre.jsp").forward(request, response);
            }

        } catch (DaoException ex) {
            ex.printStackTrace();
        }

    }

    private boolean validatePassword(String password) {

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}
