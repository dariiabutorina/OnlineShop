package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.storeservice.ProductService;
import java.math.BigDecimal;

public class MainApp {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        ProductService productService = (ProductService) INJECTOR.getInstance(ProductService.class);
        Product diorProduct = new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.0));
        productService.create(diorProduct);
        Product limecrimeProduct = new Product("LIMECRIME Nude Eyeshadow Palette",
                BigDecimal.valueOf(700.0));
        productService.create(limecrimeProduct);
        System.out.println("All products:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println("Changing price of Dior Foundation:");
        diorProduct.setPrice(diorProduct.getPrice().multiply(BigDecimal.valueOf(0.9)));
        productService.update(diorProduct);
        System.out.println(limecrimeProduct);
        System.out.println("Deleting LIMECRIME Eyeshadow Palette:");
        productService.deleteById(limecrimeProduct.getId());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}
