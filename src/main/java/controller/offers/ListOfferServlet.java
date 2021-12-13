package controller.offers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Offer;
import services.OfferService;

@WebServlet("/offers/index.do")
public class ListOfferServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8346640902238722429L;
	private OfferService offerService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.offerService = new OfferService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Offer> offers = offerService.list();
		req.setAttribute("offers", offers);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/offers/index.jsp");
		dispatcher.forward(req, resp);

	}

}
