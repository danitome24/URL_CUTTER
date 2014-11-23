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
        out.println("Old Pass: " + oldPass + " -NewPass1: " + newPass1 + " -NewPass2:" + newPass2);
        HttpSession userSession = request.getSession(true);
        User userLogin = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            if (userLogin.getPassword().equals(oldPass)) {
                    if (newPass1.equals(newPass2)) {
                        userDAO.updatePassword(newPass1, userLogin.getId());
                        request.setAttribute("passUpdated", "El password ha sido actualizado correctamente");
                        out.println("Password cambiada");
                        ServletContext context = request.getSession().getServletContext();
                        context.getRequestDispatcher("/modifydata.jsp").forward(request, response);
                    } else {
                        request.setAttribute("passError", "Error a la pass");
                        ServletContext context = request.getSession().getServletContext();
                        context.getRequestDispatcher("/modifypw.jsp").forward(request, response);
                    }

            } else {
                out.println("La contraseña no coincide con la antigua");
                request.setAttribute("errorOldPass", "La pass antigua no coincide");
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/modifypw.jsp").forward(request, response);
            }
        } catch (DaoException ex) {
            ex.toString();
        }
    }

}
