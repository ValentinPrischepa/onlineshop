import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import web.*;

public class Starter {
    public static void main(String[] args) throws Exception {
        GetAllProductsServlet getAllProductsServlet = new GetAllProductsServlet();
        AddProductServlet addProductServlet = new AddProductServlet();
        EditProductServlet editProductServlet = new EditProductServlet();
        AssetsServlet assetsServlet = new AssetsServlet();
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(addProductServlet), "/addproduct");
        context.addServlet(new ServletHolder(getAllProductsServlet), "/");
        context.addServlet(new ServletHolder(getAllProductsServlet), "/allproducts");
        context.addServlet(new ServletHolder(editProductServlet),"/product/edit/*");
        context.addServlet(new ServletHolder(assetsServlet),"/assets/*");
        context.addServlet(new ServletHolder(deleteProductServlet),"/product/delete");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
    }
}
