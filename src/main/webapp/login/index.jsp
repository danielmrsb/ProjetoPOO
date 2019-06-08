<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Floricultura Tades</title>
        <link rel="stylesheet" href="../assets/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="../assets/css/main.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <form action ="../login/valida_usuario" method="post" >
                        <div class="text-center mb-4">
                            <h1 class="h3 mb-3 font-weight-normal">
                                <span class="sr-only">Floricultura TADES</span>
                                <img src="../assets/img/logo.png">
                            </h1>
                        </div>
                        <c:if test="${varMsg == true}">
                            <div class="alert alert-danger" role="alert">
                                <c:out value="${msg}"/>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="inputEmail">E-mail</label>
                            <c:choose>
                                <c:when test="${empty emailError}">
                                    <c:choose>
                                        <c:when test="${empty loginError}">
                                            <input name="email" type="email" id="inputEmail" 
                                                   class="form-control inputForm" 
                                                   placeholder="Digite seu e-mail" autofocus minlength="0" maxlength="50">
                                        </c:when>
                                        <c:otherwise>  
                                            <input name="email" type="email" id="inputEmail" 
                                                   class="form-control inputForm" 
                                                   value="${loginError}" autofocus  minlength="0" maxlength="50">
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <input name="email" type="email" id="inputEmail" 
                                           class="form-control inputForm error" 
                                           placeholder="${emailError}" autofocus  minlength="0" maxlength="50">
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword">Senha</label>
                            <c:choose>
                                <c:when test="${empty senhaError}">
                                    <input name="password" type="password" id="inputPassword" 
                                           class="form-control inputForm" 
                                           placeholder="Digite sua senha"  minlength="0" maxlength="25">
                                </c:when>
                                <c:otherwise>
                                    <input name="password" type="password" id="inputPassword" 
                                           class="form-control inputForm error" 
                                           placeholder="${senhaError}"  minlength="0" maxlength="25">
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <button class="btn btn-lg btn-warning btn-block" type="submit">Sign in</button>
                        <p class="mt-5 mb-3 text-center subtitulo">&copy; Brothers Ltda - 2019</p>
                    </form>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
