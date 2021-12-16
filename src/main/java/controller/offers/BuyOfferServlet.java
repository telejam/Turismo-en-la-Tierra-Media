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
import services.BuyOfferService;

@WebServlet("/offers/buy.do")
public class BuyOfferServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private BuyOfferService buyOfferService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyOfferService = new BuyOfferService();
}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Map<String, String> errors = null;

		User user = (User) req.getSession().getAttribute("user");
		Integer id = Integer.parseInt(req.getParameter("id"));
		Integer type = Integer.parseInt(req.getParameter("type"));

		errors = buyOfferService.buy(user.getId(), id, type);

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
