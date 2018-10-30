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

public class GetProductById extends HttpServlet {

    ProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        Product product = productService.getById(Integer.parseInt(req.getParameter("id")));
        HashMap<String, Object> params = new HashMap<>();
        params.put("product",product);
        String page = pageGenerator.getPage("product.html",params);

        resp.getWriter().write(page);
    }
}
