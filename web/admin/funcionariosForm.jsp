<section class="content">
	<div class="content__mainbox">
		<div class="mainbox__title">
			<i class="fas fa-address-book mainbox__icon"></i>
			<h6 class="mainbox__h6"><c:out value="${(empty funcionario)? \"Cadastrar Funcionário\": \"Editar Funcionário\"}"/></h6>
		</div>
		<div class="containet">
		<form
			action="AdminServlet?action=<c:out value="${(empty funcionario)? \"new\": \"update&id=\" + funcionario.idUsuario}"/>"
			method="post">
			<div class="form-group" style="margin: 3%">
				<label for="nome">Nome:</label> 
				<input type="text" class="form-control" id="nome" placeholder="insira seu nome"	required
				name="nome" value="<c:out value="${funcionario.nome}"/>" >
			</div>
			<div class="form-group" style="margin: 3%">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" placeholder="Insira seu email" required
					name="email" value="<c:out value="${funcionario.email}"/>">
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
				href="AdminServlet?action=list" style="margin-right: 5%">Cancelar</a>
			<button type="submit" class="box__button box__button--filled">Salvar</button>
			</div>
		</form>
		</div>
	</div>
</section>