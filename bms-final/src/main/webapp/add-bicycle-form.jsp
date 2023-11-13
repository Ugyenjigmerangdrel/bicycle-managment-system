<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Bicycle Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="#" class="navbar-brand"> Bicycle Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Bicycles</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${bicycle != null}">
					<form action="updateBc" method="post">
				</c:if>
				<c:if test="${bicycle == null}">
					<form action="insertBc" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${bicycle != null}">
            			Edit Bicycle
            		</c:if>
						<c:if test="${bicycle == null}">
            			Add New Bicycle
            		</c:if>
					</h2>
				</caption>

				<c:if test="${bicycle != null}">
					<input type="hidden" name="id" value="<c:out value='${bicycle.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Bicycle No</label> <input type="text"
						value="<c:out value='${bicycle.bicycle_no}' />" class="form-control"
						name="bc_no" type="number" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Bicycle CUUID</label> <input type="text"
						value="<c:out value='${bicycle.CUUID}' />" class="form-control"
						name="cuuid">
				</fieldset>

				<fieldset class="form-group">
					<label>User Country</label> <input type="text"
						value="<c:out value='${bicycle.SUUID}' />" class="form-control"
						name="suuid">
				</fieldset>

				<button type="submit" class="btn btn-success">ADD
				</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>