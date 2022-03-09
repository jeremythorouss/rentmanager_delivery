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
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

@WebServlet(urlPatterns = "/users") 
public class userservlet extends HttpServlet{
	//private ClientService clientService ;
	private static final long serialVersionUID =1L;
	private static String Vue_Formulaire= "/WEB-INF/views/users/list.jsp";
	
	
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
			request.setAttribute("clients",this.clientService.findAll());

			request.setAttribute("clients_count",this.clientService.count());

			this.getServletContext().getRequestDispatcher(Vue_Formulaire).forward(request,response); 
		} catch(ServiceException e){
			e.printStackTrace();
		}
			
		
        
}


  public ClientService getClientService() { 
	  return clientService; 
	  }
 
    public void createClient() {
        String nom = IOUtils.readString("nom", true);
        String prenom = IOUtils.readString("prenom", true);
        String email = null;

        //adresse email?
        do {
            email = IOUtils.readString("email", true);
        } while (!email.contains("@"));
        LocalDate naissance = IOUtils.readDate("dd/MM/yyyy", true);
        Client client = new Client(nom, prenom, email, naissance);
        try {
            this.clientService.create(client);
            System.out.println("L'utilisateur a bien ete cree");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    public void deleteClient() {
        int id = IOUtils.readInt("id");
        try {
        	this.getClientService().delete(id);
            System.out.println("L'utilisateur a bien ete delete");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
