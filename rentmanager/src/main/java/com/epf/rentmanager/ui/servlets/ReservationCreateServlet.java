package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;

/**
 * Servlet implementation class ReservationCreateServlet
 */
@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Autowired
    VehicleService vehicleService;
	@Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    public ReservationCreateServlet() {
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<Vehicle> listV = new ArrayList<>();
	        try {
	            listV = vehicleService.findAll();
	        } catch (ServiceException e) {
	        }
	        request.setAttribute("vehicles", listV);

	        List<Client> listC = new ArrayList<>();
	        try {
	            listC = clientService.findAll();
	        } catch (ServiceException e) {
	        }
	        request.setAttribute("clients", listC);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/create.jsp");
	        dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     	int client_id;
	        int vehicle_id;
			
			String model;
			String lastname;
		
	        LocalDate dateStart;
	        LocalDate datEnd;
	        model= request.getParameter("model");
	        lastname= request.getParameter("lastname");
	      
	       client_id = Integer.valueOf(request.getParameter("user"));
	       vehicle_id = Integer.valueOf(request.getParameter("car"));
	        
	        dateStart = LocalDate.parse(request.getParameter("dateStart"));
	        datEnd = LocalDate.parse(request.getParameter("datEnd"));

	        Reservation reservation = new Reservation(client_id, vehicle_id, dateStart, datEnd);
	        
	       
	        try {
	            reservationService.create(reservation);
	        } catch (ServiceException e) {
	            // TODO: handle exception
	        }
	        response.sendRedirect("http://localhost:8080/rentmanager/rents");

	}

}
