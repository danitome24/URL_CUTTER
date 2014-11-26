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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
public class ChangeMailCommand implements Command{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("Change Mail Command");
        String newEmail = (String)request.getParameter("emailModify");
        HttpSession userSession = request.getSession(true);
        User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            boolean isUpdated = userDAO.updateEmail(user, newEmail);
            if(isUpdated){
                request.setAttribute("emailUpdated", "El correu ha sigut actualitzat satisfactoriament");
                user.setEmail(newEmail);
                userSession.setAttribute(Config.ATTR_SERVLET_USER, user);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/modifydata.jsp").forward(request, response);
            }
        } catch (DaoException ex) {
            ex.toString();
        }
    }
    
}
