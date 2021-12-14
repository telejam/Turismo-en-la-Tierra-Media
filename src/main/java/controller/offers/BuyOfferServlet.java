package controller.offers;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import persistence.commons.DAOFactory;
import services.BuyAttractionService;
import services.BuyPromotionService;

@WebServlet("/offers/buy.do")
public class BuyOfferServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("user");
		Map<String, String> errors;
		
		if (req.getParameter("id") == "A") { 
			BuyAttractionService buyAttractionService = new BuyAttractionService();
			Integer attractionId = Integer.parseInt(req.getParameter("id"));
			errors = buyAttractionService.buy(user.getId(), attractionId);
		} else {
			BuyPromotionService buyPromotionService = new BuyPromotionService();
			Integer promotionId = Integer.parseInt(req.getParameter("id"));
			errors = buyPromotionService.buy(user.getId(), promotionId);
		}

		User user2 = DAOFactory.getUserDAO().find(user.getId());
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("success", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/offers/index.do");
		dispatcher.forward(req, resp);
	}
}
