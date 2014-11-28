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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
public class ChangePassCommand implements Command {

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPass = (String) request.getParameter("oldPass");
        String newPass1 = (String) request.getParameter("newPass1");
        String newPass2 = (String) request.getParameter("newPass2");
        
        HttpSession userSession = request.getSession(true);
        User userLogin = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            if (userLogin.getPassword().equals(oldPass)) {
                if (newPass1.equals(newPass2)) {
                    if (validatePassword(newPass1)) {
                        userDAO.updatePassword(newPass1, userLogin.getId());
                        request.setAttribute("passUpdated", "The password has been modified");
                        
                        userLogin.setPassword(newPass1);
                        userSession.setAttribute(Config.ATTR_SERVLET_USER, userLogin);
                        ServletContext context = request.getSession().getServletContext();
                        context.getRequestDispatcher("/modifydata.jsp").forward(request, response);
                    } else {
                        request.setAttribute("passError", "The password doesn't comply the requirements");
                        ServletContext context = request.getSession().getServletContext();
                        context.getRequestDispatcher("/modifypw.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("passError", "The password does not match");
                    ServletContext context = request.getSession().getServletContext();
                    context.getRequestDispatcher("/modifypw.jsp").forward(request, response);
                }

            } else {
                
                request.setAttribute("errorOldPass", "La contrasenya antiga no es correcta");
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/modifypw.jsp").forward(request, response);
            }
        } catch (DaoException ex) {
            ex.toString();
        }
    }

    private boolean validatePassword(String password) {

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

}
