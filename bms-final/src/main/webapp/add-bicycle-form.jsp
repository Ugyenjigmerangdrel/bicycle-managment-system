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

    <title>Bicycle Managment System</title>

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
          <!-- Begin Page Content -->
          <div class="container-fluid">

            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h1 class="h3 mb-0 text-gray-800">
				
						<c:if test="${bicycle != null}">
            			Edit Bicycle
            		</c:if>
						<c:if test="${bicycle == null}">
            			Add New Bicycle
            		</c:if>
					
				</h1>
               
            </div>


                <div class="row">

                    <!-- Area Chart -->
                    <div class="col-xl-8 col-lg-7 col-md-12 col-sm-12">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">
									<c:if test="${bicycle != null}">
			            			Edit Bicycle
			            		</c:if>
									<c:if test="${bicycle == null}">
			            			Add New Bicycle
			            		</c:if>
								</h6>
                                <div class="dropdown no-arrow">
                                   
                                </div>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                          
	                            <c:if test="${bicycle != null}">
									<form action="updateBc" method="post" class="needs-validation" novalidate>
								</c:if>
								<c:if test="${bicycle == null}">
									<form action="insertBc" method="post" class="needs-validation" novalidate>
								</c:if>
								
								<c:if test="${bicycle != null}">
									<input type="hidden" name="id" value="<c:out value='${bicycle.id}' />" />
								</c:if>
								
                                <div class="form-group">
                                    <label for="">Bicycle Number:</label>
                                    <input type="text" name="bc_no" value="<c:out value='${bicycle.bicycle_no}' />" class="form-control" required>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="">CUUID:</label>
                                    <input type="text" name="cuuid" value="<c:out value='${bicycle.CUUID}' />" class="form-control" required>
                                </div>
                                <br>
                                <div class="form-group">
                                    <label for="">SUUID:</label>
                                    <input type="text" name="suuid" value="<c:out value='${bicycle.SUUID}' />" class="form-control" required>
                                </div>
                                <br>
                                
                                <button type="submit" class="btn btn-primary p-2">Submit Post</button>
                                </form>
                            </div>
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
            <a class="btn btn-primary" href="login.html">Logout</a>
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
