<%-- 
    Document   : index
    Created on : 16-oct-2014, 17:30:18
    Author     : danie_000
--%>


<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="cat.urv.deim.sob.model.Url"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="cat.urv.deim.sob.model.UrlDaoImp"%>
<%@page import="cat.urv.deim.sob.model.UrlDaoFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
        <script src="js/javascript.js"></script>
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <!--
            Testing div URL container
        --> 
        <div class="row" id="divurl">
            <div class=" col-md-12 colored border-radius" >
                <table class="table table-striped">
                    <tr>
                        <th>URL</th>
                        <th>Short URL</th>
                        <th>Number of visits</th>
                    </tr>
                    <%                        HttpSession userSession = request.getSession(false);
                        User idUser = (User) userSession.getAttribute(Config.ATTR_SERVLET_USER);
                        Collection urlCol = (Collection) userSession.getAttribute(Config.ATTR_URL_NAME);
                        int numOfPages = (Integer) userSession.getAttribute("numPage");
                        Iterator it = urlCol.iterator();
                        while (it.hasNext()) {
                            Url urlShow = (Url) it.next();
                            int id = urlShow.getIdUrl();
                            String url = urlShow.getUrl();
                            String urlShort = urlShow.getUrlShort();
                            int nVisits = urlShow.getNumVisits();
                    %>
                    <tr>
                        <td>
                            <%= url%>
                        </td>
                        <td>
                            <%="short.ly:8080/SOB/r/" + urlShort%>
                        </td>
                        <td>
                            <%= nVisits%>
                        </td>
                        <%
                            if ((Boolean) request.getAttribute("delete") != null) {%>
                        <td>                          
                            <a class="btn btn-danger remove_fields" href="http://localhost:8080/SOB/deleteUrl.do?form_action=deleteUrl&id=<%=id%>">
                                <i class="glyphicon glyphicon-trash"></i>
                            </a>  
                        </td>
                        <% }%>
                    </tr>
                    <% }%>
                </table>
            </div>
            <nav class="col-md-offset-6">
                <%
                    int currentPage = Integer.parseInt(request.getParameter("page"));
                    System.out.println(currentPage);
                %>
                <ul class="pagination">
                    <%
                        if (currentPage - 1 > 0) {
                        %>
                    <li><a href="http://localhost:8080/SOB/login.do?form_action=showUrl&page=<%=currentPage - 1%>"> < </a></li>
                        <% } %>
                    <% for (int i = 1; i <= numOfPages; i++) {%> 
                    <% if (numOfPages > 1) {%>
                    <li><a href="http://localhost:8080/SOB/login.do?form_action=showUrl&page=<%=i%>"i><%=i%></a></li>
                        <% }%>
                        <% } %>
                        <%
                            if (currentPage + 1 <= numOfPages) {
                        %>
                    <li><a href="http://localhost:8080/SOB/login.do?form_action=showUrl&page=<%=currentPage + 1%>"> > </a></li>
                        <% }%>
                </ul>
            </nav>
        </div>
    </body>
</html>
