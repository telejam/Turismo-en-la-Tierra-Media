package controller.promotions;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promotion;
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

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/views/promotions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		Double cost = Double.parseDouble(req.getParameter("cost"));
		String[] included = req.getParameterValues("included");
		String[] free = req.getParameterValues("free");
		
		Promotion promotion = promotionService.create(name, type, cost, included, free);
		
		if (promotion.isValid()) {
			resp.sendRedirect("/Prueba-III/promotions/index.do");
		} else {
			req.setAttribute("promotion", promotion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promotions/create.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
