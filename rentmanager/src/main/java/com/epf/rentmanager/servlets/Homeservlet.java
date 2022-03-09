package com.epf.rentmanager.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
@WebServlet("/home") 
public class Homeservlet extends HttpServlet{
	private static final long serialVersionUID =1L;
	private static String Vue_Formulaire= "/WEB-INF/views/home.jsp";
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int nb_Clients = 0;
		int nb_vehicles= 0;
		int nb_reservations=0;
      try {
			nb_Clients = clientService.count();
			nb_vehicles= vehicleService.count();
			nb_reservations= reservationService.count();
			
			request.setAttribute("nb_clients", nb_Clients);
			request.setAttribute("nb_vehicles", nb_vehicles);
			request.setAttribute("nb_reservations", nb_reservations);
      } catch (ServiceException e) {

			e.printStackTrace();
      }
        getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 
} 
}
