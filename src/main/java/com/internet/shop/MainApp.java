package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.models.Product;
import com.internet.shop.storeservice.ProductService;
import java.math.BigDecimal;

public class MainApp {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");
    private static final Long DIOR_FOUNDATION_ID = 1L;
    private static final Long LIMECRIME_EYESHADOW_ID = 2L;

    public static void main(String[] args) {
        ProductService productService = (ProductService) INJECTOR.getInstance(ProductService.class);
        productService.create(new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.0)));
        productService.create(new Product("LIMECRIME Nude Eyeshadow Palette",
                BigDecimal.valueOf(700.0)));
        System.out.println("All products:");
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
        System.out.println("Changing price of Dior Foundation:");
        Product diorProduct = new Product(productService.getById(DIOR_FOUNDATION_ID));
        diorProduct.setPrice(diorProduct.getPrice().multiply(BigDecimal.valueOf(0.9)));
        productService.update(diorProduct);
        System.out.println(productService.getById(DIOR_FOUNDATION_ID));
        System.out.println("Deleting LIMECRIME Eyeshadow Palette:");
        productService.deleteById(LIMECRIME_EYESHADOW_ID);
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }
    }
}
