package com.internet.shop.controller.product;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.interfaces.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/products/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");

        if (name.length() == 0
                || price.length() == 0) {
            req.setAttribute("message", "All the fields must be filled in.");
            req.getRequestDispatcher("/WEB-INF/views/products/add.jsp").forward(req, resp);
        }

        BigDecimal productPrice = BigDecimal.valueOf(Long.parseLong(req.getParameter("price")));
        productService.create(new Product(name, productPrice));
        resp.sendRedirect(req.getContextPath() + "/products/all");
    }
}
