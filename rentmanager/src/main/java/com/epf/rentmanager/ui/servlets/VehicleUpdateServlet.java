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

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/cars/update")
public class VehicleUpdateServlet extends HttpServlet {

    private int id;
    @Autowired
    VehicleService vehicleService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    public VehicleUpdateServlet() {
    }

	
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        id = Integer.valueOf(request.getQueryString().substring(3));
        request.setAttribute("id", id);
        Vehicle vehicle = new Vehicle();
        try {
        	vehicle = vehicleService.findById(id);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        request.setAttribute("constructeur", vehicle.getConstructeur());
        request.setAttribute("model", vehicle.getModel());
        request.setAttribute("nb_places", vehicle.getNb_places());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars/update.jsp");
        dispatcher.forward(request, response);
    }

    
    /** 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String constructeur;
        String model;
        int nb_places;

       
        constructeur = request.getParameter("constructeur");
        model = request.getParameter("model");
        nb_places = Integer.valueOf(request.getParameter("nb_places"));
        
        Vehicle vehicle = new Vehicle(id, constructeur, model, nb_places);

        try {
            vehicleService.update(vehicle);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/cars");

    }

}