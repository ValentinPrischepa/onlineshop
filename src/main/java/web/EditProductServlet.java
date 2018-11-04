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

public class EditProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    private Product product;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String[] uriParts = req.getRequestURI().split("/");
        product = productService.getById(Integer.parseInt(uriParts[3]));

        HashMap<String, Object> params = new HashMap<>();
        params.put("product",product);
        String page = pageGenerator.getPage("editproduct.html",params);

        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        product.setName(req.getParameter("productName"));
        product.setPrice(req.getParameter("productPrice"));
        product.setImage(req.getParameter("productImage"));
        product.setDate(new Timestamp(System.currentTimeMillis()));
        productService.update(product);
        resp.sendRedirect("allproducts");
    }
}
