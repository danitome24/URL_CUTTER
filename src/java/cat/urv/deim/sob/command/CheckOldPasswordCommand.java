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
import cat.urv.deim.sob.MD5Crypt;
import cat.urv.deim.sob.model.IUserDao;
import cat.urv.deim.sob.model.User;
import cat.urv.deim.sob.model.UserDaoFactory;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
public class CheckOldPasswordCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String password = request.getParameter("pass");
            User user = new User();
            HttpSession userSession = request.getSession(true);
            User userSes = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
            MD5Crypt md = new MD5Crypt(password);
            String cryptPass = md.cryptMD5();
            user.setPassword(cryptPass);
            user.setId(userSes.getId());
            IUserDao userDao = UserDaoFactory.getUserDAO(Config.JDBC_DRIVER);
            boolean passIsCorrect = userDao.userPassIsCorrect(user);
            if (passIsCorrect) {
                response.getWriter().write("true");
            } else {
                response.getWriter().write("false");
            }
        } catch (DaoException | NoSuchAlgorithmException ex) {
            Logger.getLogger(CheckOldPasswordCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
