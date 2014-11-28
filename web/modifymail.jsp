<%-- 
    Document   : modifymail
    Created on : 17-nov-2014, 18:38:21
    Author     : KILIAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ModifyMail</title>
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
                <h2>Change Your E-Mail</h2>
                <form role="form" method="post" action="modifyMail.do" >
                    <table id="tmodify">
                        <input type="hidden" name="form_action" value="changeMail" />
                        <tr>
                            <td class="camp">E-Mail: </td>
                            <td class="active"> <input required="" name="emailModify" type="text" class="form-control" id="" placeholder="email"> </td>
                            <td class="success">  <button type="submit" class="btn btn-info col-md-offset-2">Submit</button> </td>
                        </tr>
                    </table>
                </form>        
            </div>
        </div>   
    </body>
</html>
