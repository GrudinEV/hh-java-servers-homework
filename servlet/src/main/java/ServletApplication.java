import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;
import servlet.ClearCounterServlet;
import servlet.CounterServlet;

public class ServletApplication {

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = new Server();
    ServerConnector connector = new ServerConnector(server);
    connector.setPort(8081);
    server.setConnectors(new Connector[] {connector});
    ServletHandler servletHandler = new ServletHandler();
    server.setHandler(servletHandler);
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(ClearCounterServlet.class, "/counter/clear");
    server.start();
    server.join();
  }
}
