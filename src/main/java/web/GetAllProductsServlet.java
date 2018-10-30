package web;

import entity.Product;
import service.ProductService;
import service.ProductServiceImpl;
import web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class GetAllProductsServlet extends HttpServlet {

    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        List<Product> products = productService.getAll();
        HashMap<String, Object> params = new HashMap<>();
        params.put("products",products);
        String page = pageGenerator.getPage("allproducts.html",params);

        resp.getWriter().write(page);
    }
}
