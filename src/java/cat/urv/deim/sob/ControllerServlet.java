package cat.urv.deim.sob;

import cat.urv.deim.sob.command.Command;
import cat.urv.deim.sob.command.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import static java.lang.System.out;
import java.util.Map;
import java.util.HashMap;

public class ControllerServlet extends HttpServlet {

    private Map commands = new HashMap();
    private String xml;

    @Override
    public void init() {
        // list of commands
        this.commands.put("form", new FormCommand());
        this.commands.put("login", new LoginCommand());
        this.commands.put("logout", new LogoutCommand());
        this.commands.put("forget_pass", new ForgetPassCommand());
        this.commands.put("addurl", new AddUrlCommand());
        this.commands.put("redirect", new RedirectCommand());
        this.commands.put("changePass", new ChangePassCommand());
        this.commands.put("showUrl", new ShowUrlCommand());
        this.commands.put("cutUrl", new CutUrlCommand());
        this.commands.put("changeMail", new ChangeMailCommand());
        this.commands.put("changeName", new ChangeNameCommand());
        this.commands.put("deleteUrl", new DeleteUrlCommand());
        this.commands.put("exportUrl", new ExportUrlToXMLCommand());
    }

    protected void processCommand(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        out.println("URI: " + request.getRequestURI());
        out.println("Path: " + request.getPathInfo());
        String formAction = request.getParameter("form_action");
        String path = request.getPathInfo();
        if (null == formAction && path != null) {
            formAction = "redirect";
            out.println("URL detectada... Vamos a redirigir..");
        }

        Command command = (Command) commands.get(formAction);
        if (null == command) {
            throw new IllegalArgumentException(
                    "No command for form action: " + formAction);
        }
        command.execute(request, response);

    }

    @Override
    public void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        processCommand(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        processCommand(request, response);
    }
}
