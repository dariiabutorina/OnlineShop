package com.internet.shop.controller;

import com.internet.shop.library.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.service.interfaces.ProductService;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartController extends HttpServlet {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");
    private static final ProductService productService =
            (ProductService) INJECTOR.getInstance(ProductService.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Product diorProduct = new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.0));
        Product limeCrimeProduct = new Product("Lime Crime Nude Eyeshadow Palette",
                BigDecimal.valueOf(700.0));
        Product guerlainProduct = new Product("Guerlain Cils D'Enfer So Volume Mascara",
                BigDecimal.valueOf(1200.0));
        productService.create(diorProduct);
        productService.create(limeCrimeProduct);
        productService.create(guerlainProduct);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String timeValue = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        req.setAttribute("time", timeValue);
        req.getRequestDispatcher("/WEB-INF/views/start.jsp").forward(req, resp);
    }
}
