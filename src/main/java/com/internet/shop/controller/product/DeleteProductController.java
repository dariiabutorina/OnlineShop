package com.internet.shop.controller.product;

import com.internet.shop.library.Injector;
import com.internet.shop.service.interfaces.ProductService;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class DeleteProductController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteProductController.class);
    private static final Injector injector = Injector.getInstance("com.internet.shop");
    private final ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        productService.deleteById(id);
        LOGGER.info("Deleted the product - " + productService.get(id));
        resp.sendRedirect(req.getContextPath() + "/products/all/admin");
    }
}
