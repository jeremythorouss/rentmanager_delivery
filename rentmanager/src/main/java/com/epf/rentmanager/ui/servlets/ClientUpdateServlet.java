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
import com.epf.rentmanager.service.ClientService;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet("/users/update")
public class ClientUpdateServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    private int id;

    public ClientUpdateServlet() {
    }

	
	  @Autowired ClientService clientService;
	 

    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        id = Integer.valueOf(request.getQueryString().substring(3));
        request.setAttribute("id", id);
        Client client = new Client();
        try {
            client = clientService.findById(id);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        request.setAttribute("last_name", client.getLastname());
        request.setAttribute("first_name", client.getFirstname());
        request.setAttribute("email", client.getEmail());
        request.setAttribute("birthDate", client.getBirthdate());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/update.jsp");
        dispatcher.forward(request, response);
    }

    
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom;
        String prenom;
        String email;
        LocalDate naissance;
      
        
        nom = request.getParameter("last_name");
        prenom = request.getParameter("first_name");
        email = request.getParameter("email");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
        naissance = LocalDate.parse(request.getParameter("birthDate"));

        Client client = new Client(id, nom, prenom, email, naissance);

        try {
            clientService.update(client);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/users");

    }

}