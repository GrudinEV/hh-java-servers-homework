package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CounterService;

import java.io.IOException;
import java.util.Arrays;

public class ClearCounterServlet extends HttpServlet {
    private final CounterService service = CounterService.SERVICE;
    private static final String COOKIE_AUTH = "hh-auth";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCookies() != null) {
            Arrays.stream(req.getCookies())
                    .filter(cookie -> cookie.getName().equals(COOKIE_AUTH) && cookie.getValue().length() > 10)
                    .findFirst()
                    .ifPresent(cookie -> {
                        try {
                            service.clearCounter();
                            resp.setContentType("application/json");
                            resp.setStatus(HttpServletResponse.SC_OK);
                            resp.getWriter().print("Count cleared");
                        } catch (IOException e) {
                            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    });
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
