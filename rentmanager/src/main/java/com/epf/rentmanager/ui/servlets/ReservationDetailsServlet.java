package com.epf.rentmanager.ui.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/rents/details")
public class ReservationDetailsServlet extends HttpServlet {

    public ReservationDetailsServlet() {
    }

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    
    /** 
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.valueOf(request.getQueryString().substring(3));

        Reservation reservation = new Reservation();
        try {
            reservation = reservationService.findById(id);
        } catch (ServiceException e) {
        }
        Client client = new Client();
        try {
            client = clientService.findById(reservation.getIdClient());
        } catch (ServiceException e) {
        }
        Vehicle vehicle = new Vehicle();
        try {
            vehicle = vehicleService.findById(reservation.getIdVehicle());
        } catch (ServiceException e) {
        }


        request.setAttribute("client_id", client.getId());
        request.setAttribute("first_name", client.getFirstname());
        request.setAttribute("last_name", client.getLastname());
        request.setAttribute("email", client.getEmail());
        request.setAttribute("birthDate", client.getBirthdate());
        
        request.setAttribute("vehicle_id", vehicle.getId());
        request.setAttribute("constructeur", vehicle.getConstructeur());
        request.setAttribute("modele", vehicle.getModel());
        request.setAttribute("nb_place", vehicle.getNb_places());

        request.setAttribute("reservation_id", client.getId());
        request.setAttribute("debut", reservation.getDateStart());
        request.setAttribute("fin", reservation.getDatEnd());
       

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rents/details.jsp");
        dispatcher.forward(request, response);
    }

    
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}