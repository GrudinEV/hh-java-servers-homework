import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {
  private static final int PORT = 8081;

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = createServer();
    server.start();
    server.join();
  }

  private static Server createServer() throws Exception {
    Server server = new Server(PORT);
    ServletContextHandler ctx = new ServletContextHandler();
    server.setHandler(ctx);
    ServletContainer jersey = new ServletContainer(ResourceConfig.forApplicationClass(MyApplication.class));
    ServletHolder servletHolder = new ServletHolder(jersey);
    servletHolder.setInitOrder(1);
    ctx.addServlet(servletHolder, "/*");
    return server;
  }


}
