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
import com.bms.dao.CyclistDAO;
import com.bms.dao.MasterDAO;
import com.bms.dao.UserDAO;
import com.bms.model.Bicycle;
import com.bms.model.Cyclist;
import com.bms.model.User;
import com.bms.model.Master;


/**
 * Servlet implementation class BicycleController
 */
@WebServlet("/")
public class BicycleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BicycleDAO bcDAO;
    private UserDAO userDAO; // Add UserDAO instance variable
    private CyclistDAO cyDAO;
    private MasterDAO mDAO;
   
    public BicycleController() {
    	bcDAO = new BicycleDAO();
    	userDAO = new UserDAO();
    	cyDAO = new CyclistDAO();
    	mDAO = new MasterDAO();
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
			case "/newCyclist":
				showNewCyclistForm(request, response);
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
			case "/insertCy":
				insertCy(request, response);
				break;
			case "/deleteCy":
				deleteCy(request, response);
				break;
			case "/editCy":
				showEditCyForm(request, response);
				break;
			case "/updateCy":
				updateCy(request, response);
				break;
			case "/listCyclist":
				listCY(request, response);
				break;
			case "/history":
				listHistory(request, response);
				break;
			case "/viewPath":
				showPath(request, response);
				break;
			case "/listBicycle":
				listBC(request, response);
				break;
			case "/tracePath":
				showActiveMap(request, response);
				break;
			default:
				showActive(request, response);				
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
            List<Bicycle> listBc = bcDAO.selectAllBicycles();
            request.setAttribute("listBicycle", listBc);
            RequestDispatcher dispatcher = request.getRequestDispatcher("bicycle-list.jsp");
            try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            // User is not logged in, redirect to the login page
            response.sendRedirect("login");
        }
    }
	
	private void showActive(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // User is logged in, proceed with listing bicycles
            List<Master> listMaster = mDAO.selectAllActive();
            request.setAttribute("listMaster", listMaster);
            RequestDispatcher dispatcher = request.getRequestDispatcher("show-active.jsp");
            try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            // User is not logged in, redirect to the login page
            response.sendRedirect("login");
        }
	}
	
	private void listHistory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Check if the user is logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // User is logged in, proceed with listing bicycles
            List<Master> listBc = mDAO.selectAllMaster();
            request.setAttribute("listMaster", listBc);
            RequestDispatcher dispatcher = request.getRequestDispatcher("master-list.jsp");
            try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	private void showPath(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
		if (user != null) {
			String cardId = request.getParameter("card_id");
			Cyclist cyclist = cyDAO.selectCyclist(cardId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("showPath.jsp");
			request.setAttribute("cyclist", cyclist);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}
	
	private void showActiveMap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
		if (user != null) {
			String cardId = request.getParameter("card_id");
			Cyclist cyclist = cyDAO.selectCyclist(cardId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("tracePath.jsp");
			request.setAttribute("cyclist", cyclist);
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
		String bicycle_no = request.getParameter("bc_no");
		String cuuid = request.getParameter("cuuid");
		String suuid = request.getParameter("suuid");
		Bicycle newbicycle = new Bicycle(bicycle_no, cuuid, suuid);
		bcDAO.insertBicycle(newbicycle);
		 // Use forward instead of sendRedirect
		response.sendRedirect("listBicycle");
	   
	}

	private void updateBC(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String bicycle_no = request.getParameter("bc_no");
		String cuuid = request.getParameter("cuuid");
		String suuid = request.getParameter("suuid");

		Bicycle bicycle = new Bicycle(id, bicycle_no, cuuid, suuid);
		bcDAO.updateBicycle(bicycle);
		response.sendRedirect("listBicycle");
	}

	private void deleteBC(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bcDAO.deleteBicycle(id);
		response.sendRedirect("listBicycle");

	}
	
	
	//Cyclists Operation
	private void listCY(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Check if the user is logged in
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // User is logged in, proceed with listing bicycles
            List<Cyclist> listCy = cyDAO.selectAllCyclist();
            request.setAttribute("listCyclists", listCy);
            RequestDispatcher dispatcher = request.getRequestDispatcher("cyclist-list.jsp");
            try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
            // User is not logged in, redirect to the login page
            response.sendRedirect("login");
        }
    }
	
	private void showNewCyclistForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
		if (user != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("add-cyclist-form.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			response.sendRedirect("login");
		}
	}
	
	private void insertCy(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String card_id = request.getParameter("card_id");
		String enrolment_number = request.getParameter("enrolment_no");
		String photo = request.getParameter("photo");
		Cyclist newCyclist = new Cyclist(name, email, contact, card_id, enrolment_number, photo);
		cyDAO.insertCyclist(newCyclist);
		
	    try {
			response.sendRedirect("listCyclist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateCy(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String card_id = request.getParameter("card_id");
		String enrolment_number = request.getParameter("enrolment_no");
		String photo = request.getParameter("photo");

		Cyclist cyclist = new Cyclist(id, name, email, contact, card_id, enrolment_number, photo);
		cyDAO.updateCyclist(cyclist);
		
	    try {
			response.sendRedirect("listCyclist");
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteCy(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		cyDAO.deleteCyclist(id);
		response.sendRedirect("listCyclist");

	}
	
	private void showEditCyForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
        	String card_id = request.getParameter("card_id");
    		Cyclist existingCyclist = cyDAO.selectCyclist(card_id);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("add-cyclist-form.jsp");
    		request.setAttribute("cyclist", existingCyclist);
    		try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
        	response.sendRedirect("login");
        }
	}
	
}
