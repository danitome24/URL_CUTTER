/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
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
 * @author danie_000
 */
public class AddUrlCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String urlName = null;
        HttpSession userSession = request.getSession(false);
        Url urlFind = null;
        urlName = request.getParameter(Config.ATTR_URL_NAME);
        if (urlName.length() > 26) {
            User idUser = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
            out.println("Pasa el filtro de tamaño");
            out.println("ID_USER: "+idUser.getId());
            if (urlName != null) {
                Url url = new Url();
                out.println("URL != NULL");
                url.setUrl(urlName);
                IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
                try {
                    boolean insert = false;
                    String hashUrl = getHashUrl(url.getUrl());
                    url.setUrlShort(hashUrl);
                    urlFind = urlDao.findByUrlShort(url);
                    url.setIdUrl(urlFind.getIdUrl());
                    if (urlFind.getUrlShort() == null) {
                        out.println("No hay URL igual en la base de datos");
                        insert = urlDao.addUrl(url, idUser.getId());
                        if (insert) {
                            out.println("Se ha insertado la nueva URL");                           
                        } else {
                            out.println("NO SE HA PODIDO INSERTAR LA URL");                          
                        }
                    }else{
                        boolean exists = urlDao.findRelationByUrl(url.getIdUrl(), idUser.getId());
                        if(!exists){
                            urlDao.insertRelation(url.getIdUrl(), idUser.getId());
                            out.println("Insert en la relacion hecha");
                            
                        }else{
                            out.println("YA EXISTE RELACION");                            
                        }
                    }
                    String urlShortAll = "http://localhost:8080/SOB/url/" + url.getUrlShort();
                    userSession.setAttribute(Config.ATTR_URL_URLSHORT, urlShortAll);
                    ServletContext context = request.getSession().getServletContext();
                    context.getRequestDispatcher("/addurl.jsp").forward(request, response);
                } catch (DaoException ex) {
                    ex.toString();
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(AddUrlCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                out.println("NO HAY URL I/O SESION");
            }
        } else {
            out.println("URL MUY CORTA");
            userSession.setAttribute("lengthUrl", "No tiene la suficiente longitud");
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/addurl.jsp").forward(request, response);
        }
    }

    private String getHashUrl(String url) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        out.println("HOLAAA");
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
