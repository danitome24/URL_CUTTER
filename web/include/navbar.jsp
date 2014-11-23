<%@page import="cat.urv.deim.sob.model.User"%>
<%@page import="cat.urv.deim.sob.model.IUserDao"%>
<%@page import="cat.urv.deim.sob.model.UserDaoFactory"%>
<%@page import="cat.urv.deim.sob.Config"%>
<% User currentUser = (User) (session.getAttribute(Config.ATTR_SERVLET_USER));%>
<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="generalnav">

    <div class="container-fluid">
        <div class="navbar-header">               
            <a href="http://localhost:8080/SOB/index.do?form_action=showUrl&page=1" class="navbar-brand" id="navhead">URL Cutter</a>
        </div>
        <div class="dropdown navbar-brand" id="dropurl">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false" id="menuurl">Manage </a>
            <ul class="dropdown-menu" role="menu">
                <li><a href="addurl.jsp">Afegeix URL</a></li>
                <li class="divider"></li>
                <li><a href="http://localhost:8080/SOB/index.do?form_action=showUrl&page=1">Llista URL</a></li>
            </ul>
        </div>
        <div class="nav navbar-nav navbar-brand" id="menulogin">
            <li class="dropdown">
               <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="loginDrop">

                    
                    <span class="glyphicon glyphicon-user"></span> 
                    <strong><%= currentUser.getUserName()%></strong>
                    <span class="glyphicon glyphicon-chevron-down"></span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <div class="navbar-login" >
                            <div class="row">
                                <div class="col-lg-4">
                                    <p class="text-center">
                                        <span class="glyphicon glyphicon-user icon-size"></span>
                                    </p>
                                </div>
                                <div class="col-lg-8">
                                    <%
                                        String userFirstName = currentUser.getFirstName();
                                        String userLastName = currentUser.getLastName();
                                    %>
                                    <p class="text-left"><strong><%=userFirstName%></strong></p>
                                    <p class="text-left small"><%= currentUser.getEmail() %></p>
                                    <p class="text-left">
                                        <a href="modifydata.jsp" class="btn btn-primary btn-block btn-sm">Actualizar Datos</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <div class="navbar-login navbar-login-session">
                            <div class="row">
                                <div class="col-lg-12">
                                    <p>
                                        <a href="http://localhost:8080/SOB/logout.do?form_action=logout" class="btn btn-danger btn-block">Cerrar Sesion</a>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
        </div>
    </div>
</div>



                    
                    