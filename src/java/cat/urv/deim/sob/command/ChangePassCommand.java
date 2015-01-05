/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import cat.urv.deim.sob.MD5Crypt;
import cat.urv.deim.sob.model.IUserDao;
import cat.urv.deim.sob.model.User;
import cat.urv.deim.sob.model.UserDaoFactory;
import java.io.IOException;
import static java.lang.System.out;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String newPass1 = (String) request.getParameter("newPass1");
        String newPass2 = (String) request.getParameter("newPass2");

        HttpSession userSession = request.getSession(true);
        User userLogin = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
                MD5Crypt md5 = new MD5Crypt(newPass1);
                String pass = md5.cryptMD5();
                userDAO.updatePassword(pass, userLogin.getId());
                request.setAttribute("passUpdated", "The password has been modified");
                userLogin.setPassword(newPass1);
                userSession.setAttribute(Config.ATTR_SERVLET_USER, userLogin);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/modifydata.jsp").forward(request, response);
        } catch (DaoException ex) {
            ex.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ChangePassCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
