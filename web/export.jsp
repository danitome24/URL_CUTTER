<%-- 
    Document   : export
    Created on : 20-dic-2014, 22:09:53
    Author     : Daniel Tomé <daniel.tome@estudiants.urv.cat>
--%>

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
        <script src="js/javascript.js"></script>
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <div class="row" >
            <div class="col-md-6 col-md-offset-3 colored border-login">
                <h2>Export</h2>             
                <div>                        
                    <a href="http://localhost:8080/S/export.do?form_action=exportUrl&t=1"><button class="btn btn-info col-md-offset-2 col-md-3 paddingBottom">Download <span class="glyphicon glyphicon-download"></span></button></a>
                    <a href="http://localhost:8080/S/export.do?form_action=exportUrl&t=2"><button class="btn btn-info col-md-offset-1 col-md-3">View <span class="glyphicon glyphicon-eye-open"></span></button></a>
                </div>
                </form>
            </div>                
        </div>
    </div>
</body>
</html>
