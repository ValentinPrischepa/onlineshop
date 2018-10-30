import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.AddProductServlet;
import web.GetAllProductsServlet;
import web.GetProductById;

public class Starter {
    public static void main(String[] args) throws Exception {
        GetAllProductsServlet getAllProductsServlet = new GetAllProductsServlet();
        AddProductServlet addProductServlet = new AddProductServlet();
        GetProductById getProductById = new GetProductById();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addProductServlet), "/addproduct");
        context.addServlet(new ServletHolder(getAllProductsServlet), "/");
        context.addServlet(new ServletHolder(getProductById),"/product");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
