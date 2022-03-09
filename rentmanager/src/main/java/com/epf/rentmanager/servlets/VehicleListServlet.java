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
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

@WebServlet(urlPatterns = "/cars")




public class VehicleListServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
	@Autowired
	VehicleService vehicleService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	ClientService clientService;

	private static final long serialVersionUID =1L;
	private static String Vue_Formulaire= "/WEB-INF/views/cars/list.jsp";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		try{
			request.setAttribute("vehicles",this.vehicleService.findAll());
			
			this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 
		} catch(ServiceException e){
			e.printStackTrace();
		}	
		  
}	public VehicleService getClientService() { 
		  return vehicleService; 
		  }
public void createVehicle() {
    String constructeur = IOUtils.readString("constructeur", true);
    String model = IOUtils.readString("model", true);
    int nb_places=IOUtils.readInt("nb_places");
    // Validation technique (la variable reprÃ©sente un email)
    
    Vehicle vehicle = new Vehicle(constructeur,model,nb_places);
    try {
        this.vehicleService.create(vehicle);
        System.out.println("L'utilisateur a bien ete cree");
    } catch (ServiceException e) {
        e.printStackTrace();
    }
}
public void deleteVehicle() {
    int id = IOUtils.readInt("id");
    try {
    	this.getClientService().delete(id);
        System.out.println("L'utilisateur a bien ete delete");
    } catch (ServiceException e) {
        e.printStackTrace();
    }
}
}

