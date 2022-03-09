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

/**
 * Servlet implementation class ClientCreateServlet
 */
@WebServlet("/users/create")
public class ClientCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Autowired
    ClientService clientService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientCreateServlet() {
        super();
     
    }
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/users/create.jsp");
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
        
        Client client = new Client(0, nom, prenom, email, naissance);
        try {
            clientService.create(client);
        } catch (ServiceException e) {
        	e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/rentmanager/users");

    }

}
