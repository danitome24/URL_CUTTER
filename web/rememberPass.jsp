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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Remember password</h1>
        <form role="form" method="post" action="ForgetPassword.do" id="myForm">
            <label for="email">E-mail:</label>
            <input type="hidden" name="form_action" value="forget_pass" />
            <input required="" name="email" type="email" class="form-control" id="emailForm" placeholder="Email">
            <button type="submit" class="btn btn-info col-md-4 col-md-offset-7">Submit</button>
        </form>
    </body>
</html>
