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

                <table id="tmodify">
                    <tbody>
                        <form role="form" method="post" action="ForgetPassword.do">
                            <input type="hidden" name="form_action" value="forget_pass" />
                            <tr>
                                <td class="camp">Introdueix mail: </td>
                                <td class="active"> <input required="" name="" type="email" class="form-control" id="" placeholder=""> </td>
                                <td class="success"> <a href="#" class="btn btn-info col-md-offset-4 col-md-9">Envia</a> </td>
                            </tr>
                        </form>        
                    </tbody>
                </table>
            </div>
        </div>   
    </body>
</html>
