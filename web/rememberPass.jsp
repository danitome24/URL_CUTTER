<%-- 
    Document   : rememberPass
    Created on : 11-nov-2014, 21:36:34
    Author     : Daniel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RememberPw</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
    </head>
    <body>
        <div class="row" >
            <div class="col-md-6 col-md-offset-3  colored border-radius">
                <h2>Recorda la teva password</h2>

                <form role="form" method="post" action="ForgetPassword.do">
                    <table id="tmodify">
                        <input type="hidden" name="form_action" value="forget_pass" />
                        <tr>
                            <td class="camp">Insert e-mail: </td>
                            <td class="active"> <input required="" name="email" type="email" class="form-control" id="" placeholder=""> </td>
                            <td class="success"> <button type="submit" class="btn btn-info col-md-offset-4 col-md-9">Submit</button> </td>
                        </tr>
                    </table>
                </form>        

                <%
                    if (request.getAttribute("emailSent") != null) {
                %>
                <div class="alert alert-success">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>PERFECTE!</strong> Correu enviat!.
                </div>
                <%
                    }
                %>
                <%
                    if (request.getAttribute("emailError") != null) {
                %>
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>ERROR!</strong> Correu incorrecte!.
                </div>
                <%
                    }
                %>
            </div>
        </div>   
    </body>
</html>
