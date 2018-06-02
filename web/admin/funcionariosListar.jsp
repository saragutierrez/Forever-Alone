<section class="content">
	<div class="content__mainbox">
		<div class="mainbox__title">
			<i class="fas fa-users mainbox__icon"></i>
			<h6 class="mainbox__h6">Funcionários</h6>
		</div>
		
			<table class="table-hover">
				<thead>
					<tr>
						<th>Nome</th>
						<th>E-mail</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lista}" var="aux">
						<tr>
							<td>${aux.nome}</td>
							<td>${aux.email}</td>
							<td>
								<a
								class="box__button box__button--filled"
								href="AdminServlet?action=formUpdate&id=<c:out value="${aux.idUsuario}"/>">Editar</a>
								<a class="box__button box__button--outlined"
								href="AdminServlet?action=remove&id=<c:out value="${aux.idUsuario}"/>">Remover</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

	</div>
</section>