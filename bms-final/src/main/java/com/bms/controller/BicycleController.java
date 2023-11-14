package com.bms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bms.dao.BicycleDAO;
import com.bms.dao.UserDAO;
import com.bms.model.Bicycle;
import com.bms.model.User;



/**
 * Servlet implementation class BicycleController
 */
@WebServlet("/")
public class BicycleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BicycleDAO bcDAO;
    private UserDAO userDAO; // Add UserDAO instance variable
    
   
    public BicycleController() {
    	bcDAO = new BicycleDAO();
    	userDAO = new UserDAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if ("/logout".equals(action)) {
            logoutUser(request, response);
            return;
        }
		// Check if the user is already logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && action.equals("/login")) {
            // User is already logged in, redirect to the main page
            response.sendRedirect("list");
            return;
        }

		try {
			switch (action) {
			case "/login":
                loginUser(request, response);
                break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/insertBc":
				insertBC(request, response);
				break;
			case "/deleteBc":
				deleteBC(request, response);
				break;
			case "/editBc":
				showEditForm(request, response);
				break;
			case "/updateBc":
				updateBC(request, response);
				break;
			
			default:
				listBC(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the user credentials
        User user = userDAO.getUserByUsername(username);
        
        if (user != null && user.getPassword().trim().equals(password)) {
            // Authentication successful, store user information in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            System.out.print("Sucess Loged in");
            // Redirect to the main page or any other page after successful login
            response.sendRedirect("list");
        } else {
            // Authentication failed, forward to login page with an error message
        	System.out.print("Failure");
            request.setAttribute("error", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
	
	private void logoutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Invalidate the session and redirect to the login page
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login");
    }
	
	private void listBC(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Check if the user is logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // User is logged in, proceed with listing bicycles
            List<Bicycle> listBC = bcDAO.selectAllBicycles();
            request.setAttribute("listBicycle", listBC);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bicycle-list.jsp");
            dispatcher.forward(request, response);
        } else {
            // User is not logged in, redirect to the login page
            response.sendRedirect("login");
        }
    }

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
		if (user != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("add-bicycle-form.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
        	int id = Integer.parseInt(request.getParameter("id"));
    		Bicycle existingBicycle = bcDAO.selectBicycle(id);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("add-bicycle-form.jsp");
    		request.setAttribute("bicycle", existingBicycle);
    		dispatcher.forward(request, response);
        } else {
        	response.sendRedirect("login");
        }
	}

	private void insertBC(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int bicycle_no = Integer.parseInt(request.getParameter("bc_no"));
		String cuuid = request.getParameter("cuuid");
		String suuid = request.getParameter("suuid");
		Bicycle newbicycle = new Bicycle(bicycle_no, cuuid, suuid);
		bcDAO.insertUser(newbicycle);
		response.sendRedirect("listBC");
	}

	private void updateBC(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int bicycle_no = Integer.parseInt(request.getParameter("bc_no"));
		String cuuid = request.getParameter("cuuid");
		String suuid = request.getParameter("suuid");

		Bicycle bicycle = new Bicycle(id, bicycle_no, cuuid, suuid);
		bcDAO.updateBicycle(bicycle);
		response.sendRedirect("list");
	}

	private void deleteBC(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bcDAO.deleteBicycle(id);
		response.sendRedirect("list");

	}
	
	

    

	

}
