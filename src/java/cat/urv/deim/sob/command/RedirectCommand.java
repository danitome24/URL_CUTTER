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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Daniel
 */
public class RedirectCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURL().toString();
        String longUrl = null;
        
        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            Url url = urlDao.getLongUrl(path);
            longUrl = url.getUrl();
            
            if (longUrl != null) {                
                
                response.sendRedirect(longUrl);
            }else{
                request.setAttribute("url", "The url : "+path+" is not in the system");
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/errorRedirect.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(RedirectCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
