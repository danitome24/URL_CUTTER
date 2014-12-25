/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.command;

import ObjectList.UrlList;
import SAXparser.SaxHandler;
import cat.urv.deim.sob.Config;
import cat.urv.deim.sob.model.IUrlDao;
import cat.urv.deim.sob.model.Url;
import cat.urv.deim.sob.model.UrlDaoFactory;
import cat.urv.deim.sob.model.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author Daniel Tomé <daniel.tome@estudiants.urv.cat>
 */
public class ExportUrlToXMLCommand implements Command {

    private static final String PATH_DATA = "C:/Users/Daniel/Documents/NetbeansProjects/sob_url/web/data/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("Exportar las url en un documento XML");
        int typeOfXml = Integer.parseInt(request.getParameter("t"));
        //Controll the parameter
        if (typeOfXml != 1 && typeOfXml != 2) {
            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        if (typeOfXml == 1) {
            out.println("Hay que descargar");
        } else if (typeOfXml == 2) {
            out.println("Hay que visualizar");
        }
        HttpSession userSession = request.getSession(true);
        User user = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);

        //get path to be dinamic
        ServletContext servletContext = request.getServletContext();
        String contextPath = servletContext.getRealPath(File.separator);
        out.println(contextPath);
        String path = PATH_DATA + user.getId() + ".xml";

        IUrlDao urlDao = UrlDaoFactory.getUserDAO(Config.JDBC_DRIVER);
        try {
            //importamos las Url de un usuario
            Collection urls = urlDao.fetchAllUrlFromUser(user.getId());
            LinkedList<Url> urlList = new LinkedList();
            Iterator it = urls.iterator();
            while (it.hasNext()) {
                Url u = (Url) it.next();
                urlList.add(u);
            }
            UrlList urlList2 = new UrlList();
            urlList2.setUrlList(urlList);

            FileOutputStream file = new FileOutputStream(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(UrlList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(urlList2, file);

            if (typeOfXml == 1) {
                FileInputStream inStream = new FileInputStream(path);
                //Downloading the file
                String mimeType = servletContext.getMimeType(path);
                System.out.println("MIME type: " + mimeType);
                File fileToDownload = new File(path);
                response.setContentType(mimeType);
                response.setContentLength((int) fileToDownload.length());

                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileToDownload.getName());
                response.setHeader(headerKey, headerValue);

                OutputStream outStream = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;

                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }

                inStream.close();
                outStream.close();
            } else if (typeOfXml == 2) {
                out.println("CASE 2");
                StringBuffer buffer = new StringBuffer();
                BufferedReader in = new BufferedReader(new FileReader(path));
                String str = "";
                String fileOutput;
                while ((str = in.readLine()) != null) {
                    buffer.append(str).append("\n");
                }
                fileOutput = buffer.toString();
                request.setAttribute("file", fileOutput);
                ServletContext context = request.getSession().getServletContext();
                context.getRequestDispatcher("/showXML.jsp").forward(request, response);

            }
            
            /**
             * SAX parse
             */
            // instantiate a SAX parser
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // instantiate our little demo handler
            SaxHandler handler = new SaxHandler();
            // parse the file
            parser.parse( path, handler );
            
            // obtain the order
            out.println ( handler.getOutput() );

            ServletContext context = request.getSession().getServletContext();
            context.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception ex) {
            ex.toString();
        }

    }

}
