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
public class ChangeNameCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = (String) request.getParameter("firstname");
        String lastName = (String) request.getParameter("lastname");
        if (firstName != null && lastName != null) {
            HttpSession userSession = request.getSession(true);
            User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
            IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            
            try {
                boolean isUpdated = userDAO.updateName(user);
                if (isUpdated) {
                    out.println("se ha actualizado");
                    request.setAttribute("nameUpdated", "El nom ha sigut actualitzat correctament");
                    userSession.setAttribute(Config.ATTR_SERVLET_USER, user);
                    ServletContext context = request.getSession().getServletContext();
                    context.getRequestDispatcher("/modifydata.jsp").forward(request, response);
                }else{
                    request.setAttribute("error", "Hi ha hagut algun error");
                    ServletContext context = request.getSession().getServletContext();
                    context.getRequestDispatcher("/modifydata.jsp").forward(request, response);
                }
            } catch (DaoException ex) {
                ex.toString();
            }
        }
    }

}
