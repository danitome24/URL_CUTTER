<%-- 
    Document   : modifyuser
    Created on : 17-nov-2014, 18:37:51
    Author     : KILIAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ModifyUser</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>
        <div class="row" >
            <div class="col-md-5 col-md-offset-3  colored border-radius">
                <h2>Canvia el teu usuari</h2>

                <table id="tmodify">
                    <tbody>
                    <form role="form" method="post" action="" >
                        <tr>
                            <td class="camp">Nou user: </td>
                            <td class="active"> <input required="" name="" type="text" class="form-control" id="" placeholder=""> </td>
                            <td class="success"> <a href="#" class="btn btn-info col-md-offset-4 col-md-9">Modifica</a> </td>
                        </tr>
                    </form>        
                    </tbody>
                </table>
            </div>
        </div>   
    </body>
</html>
