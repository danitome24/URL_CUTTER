<%@page import="cat.urv.deim.sob.model.User"%>
<%@page import="cat.urv.deim.sob.model.IUserDao"%>
<%@page import="cat.urv.deim.sob.model.UserDaoFactory"%>
<%@page import="cat.urv.deim.sob.Config"%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation" id="nav">
    <div class="container"> 
        <div class="navbar-header">               
            <a href="#" class="navbar-brand" id="navhead">URL Cutter</a>
        </div>
        <div class="collapse navbar-collapse">    
            <ul class="nav navbar-nav navbar-right" id="menu">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="loginDrop">

                        <% User currentUser = (User) (session.getAttribute(Config.ATTR_SERVLET_USER));%>
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
                                        <p class="text-left"><strong>Nombre Apellido</strong></p>
                                        <p class="text-left small">correoElectronico@email.com</p>
                                        <p class="text-left">
                                            <a href="#" class="btn btn-primary btn-block btn-sm">Actualizar Datos</a>
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
            </ul>
        </div>
    </div>
</div>
