package be.vdab.servlets.orders;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.OrderService;

/**
 * Servlet implementation class OrderDetailServlet
 */
@WebServlet("/orderdetail.htm")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orders/orderdetail.jsp";
	private static final transient OrderService orderService = new OrderService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid = request.getParameter("id");
		if (orderid !=null){
			request.setAttribute("order", orderService.read(Integer.parseInt(orderid)));				
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
