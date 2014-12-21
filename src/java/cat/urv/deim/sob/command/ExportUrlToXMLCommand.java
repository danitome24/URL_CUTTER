/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import ObjectList.UrlList;
import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.DaoException;
import cat.urv.deim.sob.model.IUrlDao;
import cat.urv.deim.sob.model.Url;
import cat.urv.deim.sob.model.UrlDaoFactory;
import cat.urv.deim.sob.model.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
public class ExportUrlToXMLCommand implements Command {

    private static String PATH_DATA = "C:/Users/Daniel/Documents/NetbeansProjects/sob_url/web/data/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("Exportar las url en un documento XML");

        HttpSession userSession = request.getSession(true);
        User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);

        //get path to be dinamic
        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getRealPath(File.separator);
        String path = PATH_DATA + user.getId() + ".xml";
        out.println(PATH_DATA);
        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            //importamos las Url de un usuario
            Collection urls = urlDao.fetchAllUrlFromUser(user.getId());
            JAXBContext jaxbContext = JAXBContext.newInstance(UrlList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            LinkedList<Url> urlList = new LinkedList();
            Iterator it = urls.iterator();
            while (it.hasNext()) {
                Url u = (Url) it.next();
                urlList.add(u);
            }
            UrlList urlList2 = new UrlList();
            urlList2.setUrlList(urlList);
            File file = new File(path);
            FileInputStream inStream = new FileInputStream(file);
            jaxbMarshaller.marshal(urlList2, file);

            //Downloading the file
            String mimeType = servletContext.getMimeType(path);
            System.out.println("MIME type: " + mimeType);

            response.setContentType(mimeType);
            response.setContentLength((int) file.length());

            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
            response.setHeader(headerKey, headerValue);

            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inStream.close();
            outStream.close();

            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.toString();
        }

    }

}
