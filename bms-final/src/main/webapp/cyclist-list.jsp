<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />

    <title>Bicycle Management System</title>

    <!-- Custom fonts for this template-->
    <style type="text/css">
	  <%@include file="vendor/fontawesome-free/css/all.min.css" %>
	</style>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />

     <style type="text/css">
	  <%@include file="css/sb-admin-2.min.css" %>
	</style>
  </head>

  <body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
      	
      	<%@include file="sidebar.jsp" %>

          <!-- Begin Page Content -->
          <div class="container-fluid">
            <!-- Page Heading -->
            <div
              class="d-sm-flex align-items-center justify-content-between mb-4"
            >
              <h1 class="h3 mb-0 text-gray-800">Cyclist List</h1>
            </div>

           
            <!-- Table -->
           
           

            <!-- DataTales Example -->
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Cyclist Listing</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr class="bg-primary text-light">
                                    <th>Id</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Contact</th>
                                    <th>Card_id</th>
                                    <th>Enrolment Number</th>
                                    <th>Photo</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                               
                                <c:forEach var="bc" items="${listCyclists}">
									<tr>
										<td><c:out value="${bc.id}" /></td>
										<td><c:out value="${bc.name}" /></td>
										<td><c:out value="${bc.email}" /></td>
										<td><c:out value="${bc.contact}" /></td>
										<td><c:out value="${bc.card_id}" /></td>
										<td><c:out value="${bc.enrolment_number}" /></td>
										<td> <img src="<c:out value="${bc.photo}" />" width="100" class="img-fluid"></td>
											<td><a href="editCy?card_id=<c:out value='${bc.card_id}' />">Edit</a>
											&nbsp;&nbsp;&nbsp;&nbsp; <a
											href="deleteCy?id=<c:out value='${bc.id}' />">Delete</a></td>
									</tr>
								</c:forEach>
                               
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Table ENd -->
            
              </div>
            </div>
          </div>
          <!-- /.container-fluid -->
        </div>
        <!-- End of Main Content -->

       
        <!-- End of Footer -->
      </div>
      <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div
      class="modal fade"
      id="logoutModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button
              class="close"
              type="button"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">
            Select "Logout" below if you are ready to end your current session.
          </div>
          <div class="modal-footer">
            <button
              class="btn btn-secondary"
              type="button"
              data-dismiss="modal"
            >
              Cancel
            </button>
            <a class="btn btn-primary" href="<%=request.getContextPath()%>/logout">Logout</a>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script type="text/javascript">
    <%@include file="vendor/jquery/jquery.min.js" %>
    </script>
    
    <script type="text/javascript">
    <%@include file="vendor/bootstrap/js/bootstrap.bundle.min.js" %>
    </script>
  
	<script type="text/javascript">
    <%@include file="vendor/jquery-easing/jquery.easing.min.js" %>
    </script>
    
    <script type="text/javascript">
    <%@include file="js/sb-admin-2.min.js" %>
    </script>
    
    

    
  </body>
</html>
