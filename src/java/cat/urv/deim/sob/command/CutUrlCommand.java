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
        if (urlName.length() >= 26 && urlName != null) {
            try {
                User idUser = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
                userSession.setAttribute("lengthUrl", null);
                out.println("ID_USER: " + idUser.getId());
                Url url = new Url();
                url.setUrl(urlName);
                String hashUrl = getHashUrl(url.getUrl());
                out.println("El hash es: "+hashUrl);
                String urlShortAll = "http://localhost:8080/SOB/url/" + hashUrl;
                url.setUrlShort(urlShortAll);
                request.setAttribute("urlCorta", urlShortAll);
                request.setAttribute("urlLarga", urlName);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(CutUrlCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/addurl.jsp").forward(request, response);
        } else {
            out.println("URL MUY CORTA");
            request.setAttribute("lengthUrl", "No tiene la suficiente longitud");
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/addurl.jsp").forward(request, response);
        }
    }

    private String getHashUrl(String url) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = url.getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < 3; i++) {
            hexStr += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
        }
        return hexStr;
    }

}