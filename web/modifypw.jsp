<%-- 
    Document   : modifypw
    Created on : 17-nov-2014, 18:38:36
    Author     : KILIAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ModifyPw</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel="stylesheet">
        <link href="css/navbar.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/javascript.js"></script>
        <script src="js/ajax.js"></script>
        <link rel="SHORTCUT ICON" href="fonts/1198.png">
    </head>
    <body>
        <%@ include file="/include/navbar.jsp" %>

        <div class="row" >
            <div class="col-md-5 col-md-offset-3  colored border-radius">
                <h2>Change Your Password</h2>
                <form role="form" method="post" action="changePass.do" >
                    <input type="hidden" name="form_action" value="changePass" />

                    <table id="tmodify">
                        <tr>
                            <td class="camp">Old Password: </td>
                            <td class="active"> <input id="old" required="" name="password" type="password" class="form-control"  placeholder=""></td>
                            <td><span id="checkpwold" class="hide glyphicon glyphicon-ok"></span></td>
                            <td><span id="checkpwold" class="hide glyphicon glyphicon-remove"></span></td>
                        </tr>
                        <tr>
                            <td class="camp" >New Password: <span class="glyphicon glyphicon-question-sign" data-placement="right" title="Composta per: minúscules, majúscules, números i major de 6" data-toggle="tooltip"></span></td>
                            <td class="active"> <input required="" name="newPass1" type="password" class="form-control" id="password" placeholder=""> </td>
                            <td>
                                <span id="checkpw" class="hide glyphicon glyphicon-ok"></span>
                                <span id="checkpw2" class="hide glyphicon glyphicon-remove"></span>
                            </td>
                        </tr>
                        <tr>
                            <td class="camp">Repeat New Password: </td>
                            <td class="active"> <input required="" name="newPass2" type="password" class="form-control" id="password2" placeholder=""> </td>
                            <td>
                                <span id="coincident" class="hide">Not coincident</span>
                                <span id="coincident2" class="hide glyphicon glyphicon-ok"></span>
                            </td>
                            <td class="success"> <button type="submit" id="submitOldPass" class="btn btn-info col-md-offset-2 disabled">Submit</button> </td>
                        </tr>

                        <%if (request.getAttribute("passUpdated") != null) {%>
                        <div class="alert alert-success">
                            <a href="#" class="close" data-dismiss="alert">&times;</a>
                            <strong>SUCCESS!</strong> <%= request.getAttribute("passUpdated")%>
                        </div>
                        <% }%>
                    </table>
                </form>
            </div>
        </div>   
    </body>
</html>
