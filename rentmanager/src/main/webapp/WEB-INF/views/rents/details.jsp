<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="/WEB-INF/views/common/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@ include file="/WEB-INF/views/common/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">

				<div class="row">
					<div class="col-md-3">

						<!-- Profile Image -->
						<div class="box box-primary">
							<div class="box-body box-profile">
								<h3 class="profile-username text-center">Id :
									${reservation_id}</h3>
								<ul class="list-group list-group-unbordered">
									<li class="list-group-item"><b>Debut</b> <a
										class="pull-right">${debut}</a></li>
									<li class="list-group-item"><b>Fin</b> <a
										class="pull-right">${fin}</a></li>
								</ul>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
					<div class="col-md-9">
						<div class="nav-tabs-custom">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#rents" data-toggle="tab">Client</a></li>
								<li><a href="#cars" data-toggle="tab">Voitures</a></li>
							</ul>
							<div class="tab-content">
								<div class="active tab-pane" id="rents">
									<div class="box-body no-padding">
										<table class="table table-striped">

											<tr>
												<th style="width: 10px">#</th>

												<th>Nom</th>
												<th>Prenom</th>
												<th>Email</th>
												<th>Naissance</th>
											</tr>

											<c:forEach items="${clients}" var="user">
												<tr>
													<td>${user.id }</td>
													<td>${user.lastname }</td>
													<td>${user.firstname }</td>
													<td>${user.email }</td>
													<td>${user.birthDate }</td>
												</tr>
											</c:forEach>

										</table>
									</div>
								</div>
								<!-- /.tab-pane -->
								<div class="tab-pane" id="cars">
									<!-- /.box-header -->
									<div class="box-body no-padding">
										<table class="table table-striped">
											<tr>
												<th style="width: 10px">#</th>

												<th>Modele</th>
												<th>Constructeur</th>
												<th>Nombre de places</th>
											</tr>

											<c:forEach items="${vehicles}" var="vehicle">
												<tr>
													<td>${vehicle.id}</td>
													<td>${vehicle.constructeur}</td>
													<td>${vehicle.model}</td>
													<td>${vehicle.nb_places}</td>
												</tr>
											</c:forEach>

										</table>
									</div>
								</div>
								<!-- /.tab-pane -->
							</div>
							<!-- /.tab-content -->
						</div>
						<!-- /.nav-tabs-custom -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->

			</section>
			<!-- /.content -->
		</div>

		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>
