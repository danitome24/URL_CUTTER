<%-- 
    Document   : errorRedirect
    Created on : 26-nov-2014, 18:49:07
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ERROR GREU!!!!!</h1>
        <% String error = (String)request.getAttribute("url"); %>
        <h3><%=error%></h3>
    </body>
</html>
