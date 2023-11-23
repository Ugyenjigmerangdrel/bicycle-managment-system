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

    <!-- Custom fonts for this template-->
    <style type="text/css">
	  <%@include file="vendor/fontawesome-free/css/all.min.css" %>
	</style>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link
      href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
      rel="stylesheet"
    />
    
    <script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-app.js"></script>
    <script src="https://www.gstatic.com/firebasejs/8.2.9/firebase-firestore.js"></script>

    <!-- Add Leaflet.js and CSS -->
    <link
      rel="stylesheet"
      href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
    />
    <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"></script>

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
              <h1 class="h3 mb-0 text-gray-800">Live Tracking</h1>
            </div>
            	 <!-- Earnings (Monthly) Card Example -->
              <div class="col-xl-6 col-md-6 mb-4">
                <div class="card border-left-primary shadow h-100 py-2">
                	<div class="card-header bg-primary"><h5><b><i class="fa-solid fa-person-biking"></i> Cyclist Details</b></h5></div>
                  <div class="card-body">
                    	
                  		<hr>
                    	<h5><b>Name:</b> <c:out value='${cyclist.name}' /></h5>
                    	<h5><b>Enr. Number:</b> <c:out value='${cyclist.enrolment_number}' /></h5>
                    	<h5><b>Contact:</b> <c:out value='${cyclist.contact}' /></h5>
                    	<img src="<c:out value='${cyclist.photo}' />" class="img-fluid">
                    </div>
                  </div>
                </div>
              </div>
              
				<h3 id="tid"></h3>
				<div id="map" style="height: 70vh;"></div>

            
                    
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
    
    <script>
   		 const urlParams = new URLSearchParams(window.location.search);
        var transactionId = urlParams.get("tx_id");
        document.querySelector('#tid').innerHTML = transactionId;
     // Initialize Firebase with your configuration
        const firebaseConfig = {
          apiKey: "AIzaSyCm61ep8edTmcNh3W43EBENa8f-jf8Mkic",
          authDomain: "synergy-6bab1.firebaseapp.com",
          databaseURL: "https://synergy-6bab1-default-rtdb.firebaseio.com",
          projectId: "synergy-6bab1",
          storageBucket: "synergy-6bab1.appspot.com",
          messagingSenderId: "82792438228",
          appId: "1:82792438228:web:652f318c42912c33e61e41",
          measurementId: "G-SYMJ0Z520Z",
        };

        // Initialize Firebase
        firebase.initializeApp(firebaseConfig);

        // Initialize Firestore
        var db = firebase.firestore();

        // Reference to the "trackedCoordinates" collection
        var trackedCoordinatesRef = db.collection("trackedCoordinates");

        <!-- Create a Leaflet map -->
        var map = L.map("map").setView([0, 0], 19); // Initialize with a default center and zoom

        // Add a tile layer (you can use other map providers)
        L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
          attribution:
            '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
        }).addTo(map);

        // Initialize a circular marker
        var circleMarker = L.circleMarker([0, 0], {
          radius: 8,
          color: 'blue',
          fillColor: 'blue',
          fillOpacity: 0.7
        }).addTo(map);

        // Function to update the map with the latest position
        function updateMap(snapshot) {
          snapshot.forEach(function (doc) {
            var userData = doc.data();

            // Update the circular marker with new position
            circleMarker.setLatLng([userData.latitude, userData.longitude]);

            // Pan the map to the new position
            map.panTo([userData.latitude, userData.longitude]);
          });
        }

        // Replace 'yourTransactionId' with the actual transactionId you want to query
        var transactionIdToFetch = transactionId;

        // Realtime listener to get updates when data changes
        trackedCoordinatesRef
		  .where("transactionId", "==", transactionIdToFetch)
		  .orderBy("timestamp")
		  .limit(1) // Limit to only one result
		  .get()
		  .then(function(querySnapshot) {
		    // Initial update
		    updateMap(querySnapshot);
		
		    // Subsequent updates
		    trackedCoordinatesRef
		      .where("transactionId", "==", transactionIdToFetch)
		      .orderBy("timestamp")
		      .onSnapshot(function(snapshot) {
		        updateMap(snapshot);
		      });
		  })
		  .catch(function(error) {
		    console.error("Error getting document:", error);
		  });

    </script>

    
  </body>
</html>
