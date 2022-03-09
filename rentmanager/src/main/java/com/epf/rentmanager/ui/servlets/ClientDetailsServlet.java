package com.epf.rentmanager.ui.servlets;

import java.io.IOException;
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


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/users/details")
public class ClientDetailsServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    public ClientDetailsServlet() {
    }
    

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;

    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.valueOf(request.getQueryString().substring(3));

        List<Vehicle> listV = new ArrayList<>();
        List<Reservation> listR = new ArrayList<>();

        Client client = new Client();
        try {
            client = clientService.findById(id);
        } catch (ServiceException e) {
        }

        try {
            listR =reservationService.findResaByClientId(id);
//        	for (int i = 0; i < listR.size(); i++) {
//                listR.add(reservationService.findById(listR.get(i).getId()));
//            }
        } catch (ServiceException e) {
        }

        try {
            for (int i = 0; i < listR.size(); i++) {
                listV.add(vehicleService.findById(listR.get(i).getIdVehicle()));
            }
        } catch (ServiceException e) {
        }

        request.setAttribute("last_name", client.getLastname());
        request.setAttribute("first_name", client.getFirstname());
        request.setAttribute("email", client.getEmail());
        request.setAttribute("birthDate", client.getBirthdate());

        request.setAttribute("reservations", listR);
        request.setAttribute("vehicles", listV);

        request.setAttribute("Vsize", listV.size());
        request.setAttribute("Rsize", listR.size());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/details.jsp");
        dispatcher.forward(request, response);
    }

    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}