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
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Reservations</h1>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<!-- Horizontal Form -->
						<div class="box">
							<!-- form start -->
							<!--                         <form class="form-horizontal" method="post" action="/rents/create"> -->
							<form class="form-horizontal" method="post">

								<div class="box-body">
									<div class="form-group">
										<label for="client" class="col-sm-2 control-label">Client</label>

										<div class="col-sm-10">
											<select class="form-control" id="user" name="user">
												<c:forEach items="${clients}" var="client">
													<option value="${client.id}">${client.firstname}
														${client.lastname}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label for="car" class="col-sm-2 control-label">Voiture</label>

										<div class="col-sm-10">
											<select class="form-control" id="car" name="car">
												<c:forEach items="${vehicles}" var="vehicle">
													<option value="${vehicle.id}">${vehicle.constructeur}
														${vehicle.model}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- 									<div class="form-group"> -->
									<!-- 										<label for="clientname" class="col-sm-2 control-label">clientname</label> -->

									<!-- 										<div class="col-sm-10"> -->
									<!-- 											<input type="text" class="form-control" id="clientname" -->
									<!-- 												name="clientname" placeholder="clientname"> -->
									<!-- 										</div> -->

									<!-- 										<label for="model" class="col-sm-2 control-label">voiture</label> -->
									<!-- 										<div class="col-sm-10"> -->
									<!-- 											<input type="text" class="form-control" id="car" name="car" -->
									<!-- 												placeholder="car"> -->
									<!-- 										</div> -->

									<!-- 										<div class="form-group"> -->
									<!-- 											<label for="last_name" class="col-sm-2 control-label">clientname</label> -->
									<!-- 											<div class="col-sm-10"> -->
									<!-- 												<select class="form-control" id="reservation" -->
									<!-- 													name="reservation"> -->
									<%-- 													<c:forEach items="${listC}" var="reservations"> --%>
									<%-- 														<option value="${listC}">${listC}</option> --%>
									<%-- 													</c:forEach> --%>
									<!-- 												</select> -->
									<!-- 											</div> -->
									<!-- 										<div class="col-sm-10"> -->
									<!-- 											<input type="text" class="form-control" id="clientname" -->
									<!-- 												name="clientname" placeholder="Nom"> -->
									<!-- 										</div> -->
								</div>
								<div class="form-group">
									<label for="begin" class="col-sm-2 control-label">Date
										de debut</label>

									<div class="col-sm-10">
										<input type="date" class="form-control" id="dateStart"
											name="dateStart" required data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
										<!-- 											<input type="date" class="form-control" id="dateStart" -->
										<!-- 											name="dateStart" required -->
										<!-- 											data-inputmask="'alias': 'dd/mm/yyyy'" data-mask> -->
									</div>
								</div>
								<div class="form-group">
									<label for="end" class="col-sm-2 control-label">Date de
										fin</label>

									<div class="col-sm-10">
										<input type="date" class="form-control" id="datEnd"
											name="datEnd" required data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
									</div>
								</div>
								<%-- <label for="client" class="col-sm-2 control-label">Client</label>
                                    <div class="col-sm-10">
                                        <select class="form-control" id="client" name="client">
                                            <c:forEach items="${clients}" var="user">
                                                <option value="${user.id}">${user.nom}</option>
                                                <option value="${user.nom}"</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="car" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="car" name="car">
                                            <c:forEach items="${vehicules}" var="vehicule">
                                                <option value="${vehicule.id}">${vehicule.modele}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="begin" class="col-sm-2 control-label">Date de debut</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="begin" name="begin" required
                                               data-inputmask="'alias': 'yyyy-mm-dd'" data-mask>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="end" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="end" name="end" required
                                               data-inputmask="'alias': 'yyyy-mm-dd'" data-mask>
                                    </div>
                                </div> --%>
						</div>
						<!-- <div class="form-group">
                                    <label for="car" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="car" name="car">
                                            <option value="1">Renault Clio</option>
                                            <option value="2">Citroen C2</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="client" name="client">
                                            <option value="1">John Doe</option>
                                            <option value="2">Jane Doe</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="begin" class="col-sm-2 control-label">Date de debut</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="begin" name="begin" required
                                               data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="end" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="end" name="end" required
                                               data-inputmask="'alias': 'dd/mm/yyyy'" data-mask>
                                    </div>
                                </div> -->
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-info pull-right">Ajouter</button>
					</div>
					<!-- /.box-footer -->
					</form>
				</div>
				<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	</section>
	<!-- /.content -->
	</div>

	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/common/js_imports.jsp"%>
</body>
</html>
<%-- <%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script>
    $(function () {
        $('[data-mask]').inputmask()
    });
</script>
</body>
</html> --%>
