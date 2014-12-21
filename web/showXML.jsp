<%-- 
    Document   : showXML
    Created on : 21-dic-2014, 12:20:32
    Author     : Daniel Tomé <daniel.tome@estudiants.urv.cat>
--%>

<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>URL in XML </title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
        <script type="text/javascript" src="js/shCore.js"></script>
        <script type="text/javascript" src="js/shBrushJScript.js"></script>
        <script type="text/javascript" src="js/shBrushXml.js"></script>
        <link href="css/shCoreEclipse.css" rel="stylesheet" type="text/css" />
        <link href="css/shThemeFadeToGray.css" rel="stylesheet" type="text/css" />
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <div class="row">
            <div class="col-md-9 col-md-offset-2 colored">
                <%String fileOutput = (String) request.getAttribute("file");%>
                <pre class="brush: js">
                    <%= (fileOutput != null) ? fileOutput : ""%>
                </pre>
                <script type="text/javascript">
                    SyntaxHighlighter.all();
                </script>
            </div>
        </div>
    </body>
</html>
