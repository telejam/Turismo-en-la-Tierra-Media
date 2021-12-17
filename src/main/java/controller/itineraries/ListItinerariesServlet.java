package controller.itineraries;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Offer;
import model.User;
import services.*;

@WebServlet("/itineraries/index.do")
public class ListItinerariesServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private ItineraryService itineraryService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.itineraryService = new ItineraryService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		try {

			User user = (User) req.getSession().getAttribute("user");
			UserService userService = new UserService();
			User tmp_user = userService.find(user.getId());
			req.setAttribute("user", tmp_user);

			List<Offer> offers;
			if (user.isAdmin()) {
				offers = itineraryService.list();
			}else {
				offers = itineraryService.findByIdUser(user.getId());
			}
			req.setAttribute("offers", offers);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/itineraries/index.jsp");
			dispatcher.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
