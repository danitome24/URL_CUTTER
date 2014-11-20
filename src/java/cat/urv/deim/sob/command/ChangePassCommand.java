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
public class ChangePassCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPass = (String) request.getParameter("oldPass");
        String newPass1 = (String) request.getParameter("newPass1");
        String newPass2 = (String) request.getParameter("newPass2");
        boolean existsPass = false;
        HttpSession userSession = request.getSession(true);
        User userLogin = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        out.println("Estamos en ChangePassCommand");
        out.println(userLogin.getUserName());
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            existsPass = userDAO.userPassIsCorrect(userLogin);
            if (existsPass) {
                if(newPass1.equals(newPass2)){
                    userDAO.updatePassword(newPass1, userLogin.getId());
                    out.println("Password cambiada");
                    ServletContext context = request.getSession().getServletContext();
                    context.getRequestDispatcher("/modifydata.jsp").forward(request, response);
                }
            }
        } catch (DaoException ex) {
            ex.toString();
        }
    }

}
