<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="erro.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
		<html>
			<head>
			<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	        <link rel="stylesheet" href="templates/template.css" type="text/css"/>
			<link rel="icon" href="fa.ico">			
<title>Cadastro</title>
</head>
<body>

<!-- INICIO HEADER -->
<%@include file="header.jsp" %>
<!-- FIM HEADER -->

<!-- INICIO FORMULÁRIO -->
<section class="content">
	<div class="content__mainbox">
		<div class="mainbox__title">
			<i class="fas fa-address-book mainbox__icon"></i>
			<h6 class="mainbox__h6">Formulário de Cadastro</h6>
		</div>
		<div class="container">
			<div class="row">
				<form action="CadastroServlet" method="post">
					<div class="form-group" style="margin: 3%">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" placeholder="Insira seu email" required
							name="email">
					</div>
					<div class="form-group" style="margin: 3%">
						<label for="nome">Nome:</label> 
						<input type="text" class="form-control" id="nome" placeholder="insira seu nome"	required
						name="nome">
					</div>
					<div class="form-group" style="margin: 3%">
						<label for="senha1">Senha:</label> <input type="password"
							class="form-control" id="senha1" placeholder="Insira a senha" required
							name="senha1">
					</div>
					<div class="form-group" style="margin: 3%">
						<label for="senha2">Confirmar senha:</label> <input type="password"
							class="form-control" id="senha2" placeholder="Repita a senha" required
							name="senha2">
					</div>
					<div class="form-group" style="margin: 3%">
					<a class="box__button box__button--outlined"
						href="index.jsp" style="margin-right: 5%">Voltar</a>
					<button type="submit" class="box__button box__button--filled">Salvar</button>
					</div>
				</form>
				</div>
		</div>
	</div>
</section>
<!-- FIM FORMULÁRIO -->

<!-- INICIO FOOTER-->			
<%@include file="footer.jsp" %>
<!-- FIM FOOTER-->

</body>
</html>