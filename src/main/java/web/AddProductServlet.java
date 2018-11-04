package web;

import entity.Product;
import service.ProductService;
import service.ProductServiceImpl;
import web.templater.PageGenerator;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;

public class AddProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        Product product = new Product();
        product.setName(req.getParameter("productName"));
        product.setPrice(req.getParameter("productPrice"));
        product.setImage(req.getParameter("productImage"));
        product.setDate(new Timestamp(System.currentTimeMillis()));
        productService.add(product);
        resp.sendRedirect("allproducts");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> params = new HashMap<>();
        String page = pageGenerator.getPage("addproduct.html",params);
        resp.getWriter().write(page);
    }
}
