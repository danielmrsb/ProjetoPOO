<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Cadastro de Produtos</title>
        <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../assets/css/main.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css">
    </head>
    <body>
        <header>
            <h1 style="text-align: center;">
                <span class="sr-only">Floricultura TADES</span>
                <img src="../assets/img/logo.png">
            </h1>
            <nav class="navbar navbar-expand-md navbar-light bg-warning mb-4">
                <ul id="itensMenu" class="nav justify-content-center">
                    <li class="nav-item">
                        <form action="../ti/listagem_usuarios" method="GET">
                            <button type="submit" class="btn nav-link nav-text" >Usuarios</button>
                        </form>
                    </li>
                </ul>
                <i class="fas fa-user-tie" style="margin-left: -189px;"></i> &nbsp;&nbsp; Olá, <c:out value="${nomeUsuario}"/> 
                <form action="../login/logout" method="POST">
                    <button type="submit" class="btn nav-link nav-text" >Sair</button>
                </form>
            </nav>
        </header>

        <c:if test="${acao == 'editar'}"> 
            <h2 class="h2 text-center subtitulo">Editar Produto</h2>
        </c:if>
        <c:if test="${empty acao}"> 
            <h2 class="h2 text-center subtitulo">Cadastrar Produto</h2>
        </c:if>

        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <c:choose>
                        <c:when test="${acao == 'editar'}">
                            <form action="editar_produto" method="POST">
                            </c:when>
                            <c:otherwise>
                                <form action="cadastro_produto" method="POST">
                                </c:otherwise>
                            </c:choose>

                            <c:if test="${varMsg == true}">
                                <div class="alert alert-danger" role="alert">
                                    <c:out value="${msg}"/>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:if>

                            <div class="form-group">
                                <label for="codigoProduto" class="sr-only">Código:</label>
                                <c:choose>
                                    <c:when test="${acao == 'editar'}">
                                        <input type="number" class="form-control inputForm sr-only" id="codigoProduto"
                                               value="${codigo}" name="codigoProduto">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="number" class="form-control inputForm sr-only" id="codigoProduto"
                                               placeholder="Codigo do produto" name="codigoProduto">
                                    </c:otherwise>
                                </c:choose>

                                <label for="nomeProduto">Nome:</label>
                                <c:choose>
                                    <c:when test="${acao == 'editar'}">
                                        <c:if test="${empty nomeErro}">
                                            <input type="text" class="form-control inputForm" id="nome"
                                                   value="${nome}" name="nome" minlength="0" maxlength="50">                                            
                                        </c:if>
                                        <c:if test="${not empty nomeErro}">         
                                            <input type="text" class="form-control inputForm error" id="nome"
                                                   placeholder="${nomeErro}" name="nome" minlength="0" maxlength="50">                                  
                                        </c:if>    
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${empty nomeErro}">
                                            <input type="text" class="form-control inputForm" id="nome"
                                                   placeholder="Nome do produto" name="nome" minlength="0" maxlength="50">
                                        </c:if>
                                        <c:if test="${not empty nomeErro}">         
                                            <input type="text" class="form-control inputForm error" id="nome"
                                                   placeholder="${nomeErro}" name="nome" minlength="0" maxlength="50">                                  
                                        </c:if>    
                                    </c:otherwise> 
                                </c:choose>

                                <label for="descricaoProduto">Descrição:</label>
                                <c:choose>
                                    <c:when test="${acao == 'editar'}">
                                        <input type="text" class="form-control inputForm" id="descricao"
                                               value="${descricao}" name="descricao" minlength="0" maxlength="100">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="text" class="form-control inputForm" id="descricao"
                                               placeholder="Descrição do produto" name="descricao" minlength="0" maxlength="100">
                                    </c:otherwise>
                                </c:choose>

                                <label for="quantidadeEstoque">Quantidade em estoque:</label>
                                <c:choose>
                                    <c:when test="${acao == 'editar'}">
                                        <c:if test="${empty quantidadeEstoqueErro}">
                                            <input type="number" class="form-control inputForm" id="quantidadeEstoque"
                                                   value="${qtd_estoque}" name="quantidadeEstoque" min="0" minlength="0" maxlength="11">
                                        </c:if>
                                        <c:if test="${not empty quantidadeEstoqueErro}">
                                            <input type="number" class="form-control inputForm error" id="quantidadeEstoque"
                                                   placeholder="${quantidadeEstoqueErro}" name="quantidadeEstoque" min="0" minlength="0" maxlength="11">
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${empty quantidadeEstoqueErro}">
                                            <input type="number" class="form-control inputForm" id="quantidadeEstoque"
                                                   placeholder="Quantidade em estoque" name="quantidadeEstoque" min="0" minlength="0" maxlength="11">
                                        </c:if>
                                        <c:if test="${not empty quantidadeEstoqueErro}">        
                                            <input type="number" class="form-control inputForm error" id="quantidadeEstoque"
                                                   placeholder="${quantidadeEstoqueErro}" name="quantidadeEstoque" min="0" minlength="0" maxlength="11">
                                        </c:if> 
                                    </c:otherwise>
                                </c:choose>

                                <label for="valor">Valor Unitário:</label>
                                <c:choose>
                                    <c:when test="${acao == 'editar'}">
                                        <c:if test="${empty valorUnitarioErro}">
                                            <input type="text" class="form-control inputForm" id="valor"
                                                   value="${valor_unidade}" name="valorUnitario" minlength="0" maxlength="9">
                                        </c:if>
                                        <c:if test="${not empty valorUnitarioErro}">         
                                            <input type="text" class="form-control inputForm error" id="valor"
                                                   placeholder="${valorUnitarioErro}" name="valorUnitario" min="0" minlength="0" maxlength="9">
                                        </c:if> 
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${empty valorUnitarioErro}">
                                            <input type="text" class="form-control inputForm" id="valor"
                                                   placeholder="Valor Unitário" name="valorUnitario" minlength="0" maxlength="9">
                                        </c:if>
                                        <c:if test="${not empty valorUnitarioErro}">         
                                            <input type="text" class="form-control inputForm error" id="valor"
                                                   placeholder="${valorUnitarioErro}" name="valorUnitario" min="0" minlength="0" maxlength="9">
                                        </c:if> 
                                    </c:otherwise>
                                </c:choose>                               

                                <label for="tipo" >Tipo:</label>
                                <c:choose>
                                    <c:when test="${acao == 'editar'}">
                                        <select class="custom-select inputForm" id="tipo" name="tipo">
                                            <option hidden="" value="${tipoCadastrado}">${tipoCadastrado}</option>
                                            <option value="Produto">Produto</option>
                                            <option value="Serviço">Serviço</option>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <select class="custom-select inputForm" id="tipo" name="tipo">
                                            <option disabled="" selected="" hidden="">Tipo do produto</option>
                                            <option value="Produto">Produto</option>
                                            <option value="Serviço">Serviço</option>
                                        </select>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <button type="submit" class="btn btn-light btn-block">
                                <i class="far fa-save"></i>
                                Salvar
                            </button>
                        </form>
                        <form action="listagem_produtos" method="GET" style="padding-top: 8px; ">
                            <button type="submit" class="btn btn-light btn-block">
                                <i class="fas fa-ban"></i>
                                Cancelar
                            </button>
                        </form>
                </div>
                <div class="col-md-3"></div>
            </div>
            <footer class="pt-4 my-md-5 pt-md-5 footer-border-top">
                <div class="row text-center">
                    <div class="col-12 col-md">
                        <img class="mb-2" src="../assets/img/logo.png" alt="">
                        <small class="d-block mb-3 text-muted">&copy; Brothers Ltda - 2019</small>
                    </div>
                    <div class="col-12 col-md">
                        <h5 class="title-footer">Brothers Ltda</h5>
                        <ul class="list-unstyled text-small">
                            <li class="text-muted">Daniel Morais</li>
                            <li class="text-muted">Guilherme Ricardo</li>
                        </ul>
                    </div>
                </div>
            </footer>
        </div>
        <script src="../assets/js/jquery-2.1.3.min.js"></script>
        <script src="../assets/js/jquery.mask.min.js"></script>
        <script src="../assets/js/bootstrap.min.js"></script>
        <script src="../assets/js/main.js"></script>
    </body>
</html>
