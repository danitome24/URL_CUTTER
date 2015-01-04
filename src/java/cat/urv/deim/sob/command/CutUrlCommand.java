/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.model.Url;
import cat.urv.deim.sob.model.User;
import com.service.NoSuchAlgorithmException_Exception;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class CutUrlCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlName = null;
        HttpSession userSession = request.getSession(false);
        Url urlFind = null;
        request.setAttribute("urlCorta", null);
        urlName = request.getParameter(Config.ATTR_URL_NAME);

        User idUser = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
        userSession.setAttribute("lengthUrl", null);
        Url url = new Url();
        url.setUrl(urlName);
        /**
         * Web Service Call
         */
        String hashUrl;
        try {
            hashUrl = urlCut(url.getUrl());

            /**
             *
             */
            String urlShortAll = "short.ly:8080/SOB/r/" + hashUrl;
            url.setUrlShort(hashUrl);
            request.setAttribute("urlCorta", urlShortAll);
            request.setAttribute("urlLarga", urlName);
        } catch (NoSuchAlgorithmException_Exception ex) {
            Logger.getLogger(CutUrlCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServletContext context = request.getSession().getServletContext();
        context.getRequestDispatcher("/addurl.jsp").forward(request, response);

    }

    private static String urlCut(java.lang.String url) throws NoSuchAlgorithmException_Exception {
        com.service.UrlWebService_Service service = new com.service.UrlWebService_Service();
        com.service.UrlWebService port = service.getUrlWebServicePort();
        return port.urlCut(url);
    }

}
