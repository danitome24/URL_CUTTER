/*
 * Copyright (C) 2015 Daniel Tomé <daniel.tome@estudiants.urv.cat>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import cat.urv.deim.sob.model.IUserDao;
import cat.urv.deim.sob.model.User;
import cat.urv.deim.sob.model.UserDaoFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
public class UserIsRepeatCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user");
        User user = new User();
        user.setUserName(userName);
        IUserDao userDAO = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            boolean isRepeat = userDAO.isUsernameRepeat(user);
            if (isRepeat) {
                response.setContentType("text/xml");
                System.out.println("Esta repetido");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<repeat>true</repeat>");
            } else {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                System.out.println("No esta repetido");
                response.getWriter().write("<repeat>false</repeat>");
            }
        } catch (DaoException ex) {
            Logger.getLogger(UserIsRepeatCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
