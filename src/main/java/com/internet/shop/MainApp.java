package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.storeservice.ProductService;
import java.math.BigDecimal;

public class MainApp {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        System.out.println("Creating two new products.");
        ProductService productService = (ProductService) INJECTOR.getInstance(ProductService.class);
        Product diorProduct = new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.0));
        Product limecrimeProduct = new Product("LIMECRIME Nude Eyeshadow Palette",
                BigDecimal.valueOf(700.0));
        productService.create(diorProduct);
        productService.create(limecrimeProduct);
        System.out.println("All products:");
        productService
                .getAll()
                .forEach(System.out::println);

        System.out.println("\nChanging price of Dior Foundation.");
        Product diorProductNew = new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.0)
                        .multiply(BigDecimal.valueOf(0.9)));
        diorProductNew.setId(diorProduct.getId());
        System.out.println("New Dior product:\n"
                + diorProductNew);
        productService.update(diorProductNew);
        System.out.println("All products:");
        productService
                .getAll()
                .forEach(System.out::println);

        System.out.println("\nDeleting LIMECRIME Eyeshadow Palette."
                + "\nLIMECRIME product:\n"
                + limecrimeProduct);
        productService.deleteById(limecrimeProduct.getId());
        System.out.println("All products:");
        productService
                .getAll()
                .forEach(System.out::println);
    }
}
