/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.model.IUrlDao;
import cat.urv.deim.sob.model.Url;
import cat.urv.deim.sob.model.UrlDaoFactory;
import cat.urv.deim.sob.model.User;
import java.io.IOException;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Daniel
 */
public class RedirectCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURL().toString();
        String longUrl = null;
        out.println("Hay que redirigir desde este hash: " + path);
        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            Url url = urlDao.getLongUrl(path);
            longUrl = url.getUrl();
            
            if (longUrl != null) {                
                out.println("La url a redirigir es: "+longUrl);
                response.sendRedirect(longUrl);
            }
        } catch (Exception ex) {
            Logger.getLogger(RedirectCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
