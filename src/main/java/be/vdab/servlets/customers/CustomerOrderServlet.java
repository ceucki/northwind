package be.vdab.servlets.customers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.CustomerService;

/**
 * Servlet implementation class CustomerDetailServlet
 */
@WebServlet("/orders.htm")
public class CustomerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/customers/orders.jsp";
	private static final transient CustomerService customerService = new CustomerService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customerid = request.getParameter("id");
		if (customerid!=null){
			request.setAttribute("customer",
					customerService.read(Integer.parseInt(customerid)));
		}		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
