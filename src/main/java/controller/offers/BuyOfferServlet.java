package controller.offers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
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
		Map<String, String> errors = null;
		
		if (req.getParameter("type") == "1") { 
			BuyAttractionService buyAttractionService = new BuyAttractionService();
			Integer attractionId = Integer.parseInt(req.getParameter("id"));
			try {
				errors = buyAttractionService.buy(user.getId(), attractionId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			BuyPromotionService buyPromotionService = new BuyPromotionService();
			Integer promotionId = Integer.parseInt(req.getParameter("id"));
			errors = buyPromotionService.buy(user.getId(), promotionId);
		}

		req.getSession().setAttribute("user", user);
		
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
