package controller.promotions;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Attraction;
import model.BasePromotion;
import services.AttractionService;
import services.PromotionService;

@WebServlet("/promotions/create.do")
public class CreatePromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AttractionService attractionService = new AttractionService();
		List<Attraction> attractions = attractionService.list();

		req.setAttribute("attractions", attractions);
		
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/promotions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		Double value = Double.parseDouble(req.getParameter("value"));
		String[] included = req.getParameterValues("included");
		String[] free = req.getParameterValues("free");
		
		BasePromotion promotion;
		try {
			promotion = promotionService.create(name, type, value, included, free);
			
			if (promotion.isValid()) {
				resp.sendRedirect("/TierraMedia3/promotions/index.do");
			} else {
				req.setAttribute("promotion", promotion);

				AttractionService attractionService = new AttractionService();
				List<Attraction> attractions = attractionService.list();

				req.setAttribute("attractions", attractions);
				
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/views/promotions/create.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
