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

@WebServlet("/promotions/edit.do")
public class EditPromotionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private PromotionService promotionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promotionService = new PromotionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		try {
			BasePromotion promotion = promotionService.findBase(id);
			req.setAttribute("promotion", promotion);

			List<Integer> included = promotionService.findIdsIncluded(id);
			req.setAttribute("included", included);
			
			List<Integer> free= promotionService.findIdsFree(id);
			req.setAttribute("free", free);
			
			AttractionService attractionService = new AttractionService();
			List<Attraction> attractions = attractionService.list();

			req.setAttribute("attractions", attractions);
			
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promotions/edit.jsp");
			dispatcher.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		Double value = Double.parseDouble(req.getParameter("value"));
		String[] included = req.getParameterValues("included");
		String[] free = req.getParameterValues("free");

		try {
			BasePromotion promotion = promotionService.update(id, name, type, value, included, free);
			
			if (promotion.isValid()) {
				resp.sendRedirect("/TierraMedia3/promotions/index.do");
			} else {
				req.setAttribute("promotion", promotion);

				AttractionService attractionService = new AttractionService();
				List<Attraction> attractions = attractionService.list();

				req.setAttribute("attractions", attractions);
				
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/views/promotions/edit.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
