package com.epf.rentmanager.servlets;

import java.io.IOException;
import java.time.LocalDate;

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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

@WebServlet(urlPatterns = "/rents") 
public class ReservationListServlet extends HttpServlet{
	//private ReservationService reservationService ;
	private static final long serialVersionUID =1L;
	private static String Vue_Formulaire= "/WEB-INF/views/rents/list.jsp";
	
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
		try{
			request.setAttribute("reservations",this.reservationService.findAll());

			request.setAttribute("nb_reservations",this.reservationService.count());

			this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 
		} catch(ServiceException e){
			e.printStackTrace();
		}
			
		
        
}


  public ReservationService getReservationService() { 
	  return reservationService; 
	  }
 
    public void createReservation() {
        int idClient = IOUtils.readInt("idClient");
        int idVehicule = IOUtils.readInt("idVehicule");
   
        
        LocalDate dateStart = IOUtils.readDate("dd/MM/yyyy", true);
        LocalDate datEnd = IOUtils.readDate("dd/MM/yyyy", true);
        
        Reservation reservation = new Reservation(idClient, idVehicule, dateStart, datEnd);
        try {
            this.reservationService.create(reservation);
            System.out.println("L'utilisateur a bien ete cree");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    public void deleteReservation() {
        int id = IOUtils.readInt("id");
        try {
        	this.getReservationService().delete(id);
            System.out.println("L'utilisateur a bien ete delete");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
