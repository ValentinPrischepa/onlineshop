package web;

import entity.Product;
import service.ProductService;
import service.ProductServiceImpl;
import web.templater.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetAllProductsServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        List<Product> products = productService.getAll();
        HashMap<String, Object> params = new HashMap<>();
        params.put("products",products);
        String page = pageGenerator.getPage("allproducts.html",params);
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(page);
    }
}
