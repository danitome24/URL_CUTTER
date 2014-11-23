<%-- 
    Document   : modifyname
    Created on : 17-nov-2014, 18:38:06
    Author     : KILIAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ModifyName</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <div class="row" >
            <div class="col-md-6 col-md-offset-3  colored border-radius">
                <h2>Canvia el teu nom</h2>
                <form role="form" method="post" action="changename.do" >
                    <input type="hidden" name="form_action" value="changeName" />
                    <table id="tmodify">
                        <tr>
                            <td class="camp">Nou nom: </td>
                            <td class="active"><input required="" name="firstname" type="text" class="form-control" id=""> </td>            
                        </tr>
                        <tr>
                            <td class="camp">Nou cognoms: </td>
                            <td class="active"><input required="" name="lastname" type="text" class="form-control" id="" > </td>
                            <td class="success"><button type="submit" class="btn btn-info col-md-offset-2">Modifica</button></td>
                        </tr>
                    </table>
                </form>        
            </div>
        </div>   
    </body>
</html>
