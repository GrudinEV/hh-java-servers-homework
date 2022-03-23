package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CounterService;

import java.io.IOException;

public class CounterServlet extends HttpServlet {
    private final CounterService service = CounterService.SERVICE;
    private static final String HEADER_SUBSTRUCTION_COUNTER = "Subtraction-Value";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print(service.getCount());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.incrementCount();
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print("Counter is incremented");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int subtractionValue = Integer.parseInt(req.getHeader(HEADER_SUBSTRUCTION_COUNTER));
        int factSubstractionValue = service.substructionCount(subtractionValue);
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print(String.format("Counter is reduced by %d", factSubstractionValue));
    }
}
