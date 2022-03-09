package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/rents/update")
public class ReservationUpdateServlet extends HttpServlet {

	private int id;

	public ReservationUpdateServlet() {
	}

	
	  @Autowired ClientService clientService;
	  @Autowired VehicleService vehicleService;
	  @Autowired ReservationService reservationService;
	    @Override
	    public void init() throws ServletException {
	        super.init();
	        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	    }

	@Override
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		id = Integer.valueOf(request.getQueryString().substring(3));
		request.setAttribute("id", id);
		Reservation reservation = new Reservation();
		Vehicle vehicles = new Vehicle();
		Client clients = new Client();
		try {
			reservation = reservationService.findById(id);
			
			request.setAttribute("vehicles", vehicleService.findAll());
			request.setAttribute("clients", clientService.findAll());
			

		} catch (ServiceException e) {
			e.printStackTrace();
		}

		request.setAttribute("debut", reservation.getDateStart());
		request.setAttribute("fin", reservation.getDatEnd());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/update.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int client_id;
		int vehicle_id;
		LocalDate dateStart;
		LocalDate datEnd;

		client_id = Integer.valueOf(request.getParameter("client"));
		vehicle_id = Integer.valueOf(request.getParameter("car"));
		dateStart = LocalDate.parse(request.getParameter("dateStart"));
		datEnd = LocalDate.parse(request.getParameter("datEnd"));

		Reservation reservation = new Reservation(id, client_id, vehicle_id, dateStart, datEnd);

		try {
			reservationService.update(reservation);
		} catch (ServiceException e) {
			// TODO: handle exception
		}
		response.sendRedirect("http://localhost:8080/rentmanager/rents");

	}

}