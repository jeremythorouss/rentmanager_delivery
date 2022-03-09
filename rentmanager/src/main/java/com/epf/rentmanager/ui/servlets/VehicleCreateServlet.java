package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;

/**
 * Servlet implementation class ClientCreateServlet
 */
@WebServlet("/cars/create")
public class VehicleCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
   
    public VehicleCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/create.jsp");
        dispatcher.forward(request, response);
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String constructeur;
        String model;
        int nb_places;

        constructeur = request.getParameter("constructeur");
        model = request.getParameter("model");
        nb_places = Integer.valueOf(request.getParameter("nb_places"));

        Vehicle vehicle = new Vehicle(constructeur, model, nb_places);
        try {
            vehicleService.create(vehicle);
        } catch (ServiceException e) {
            // TODO: handle exception
        }
        response.sendRedirect("http://localhost:8080/rentmanager/cars");


    }

}
