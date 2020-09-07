package com.internet.shop;

import com.internet.shop.library.Injector;
import com.internet.shop.models.Order;
import com.internet.shop.models.Product;
import com.internet.shop.models.ShoppingCart;
import com.internet.shop.models.User;
import com.internet.shop.service.interfaces.OrderService;
import com.internet.shop.service.interfaces.ProductService;
import com.internet.shop.service.interfaces.ShoppingCartService;
import com.internet.shop.service.interfaces.UserService;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class MainApp {
    private static final Injector INJECTOR = Injector.getInstance("com.internet.shop");

    public static void main(String[] args) {
        /*
        * First Part:
        * Product - CRUD operations usage
        */

        System.out.println("Creating three new products.");
        ProductService productService =
                (ProductService) INJECTOR.getInstance(ProductService.class);
        Product diorProduct = new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.0));
        Product limeCrimeProduct = new Product("Lime Crime Nude Eyeshadow Palette",
                BigDecimal.valueOf(700.0));
        Product guerlainProduct = new Product("Guerlain Cils D'Enfer So Volume Mascara",
                BigDecimal.valueOf(1200.0));
        productService.create(diorProduct);
        productService.create(limeCrimeProduct);
        productService.create(guerlainProduct);
        printAllProducts(productService);

        System.out.println("\nChanging price of Dior Foundation.");
        Product diorProductNew = new Product("Dior Backstage Foundation",
                BigDecimal.valueOf(2000.0)
                        .multiply(BigDecimal.valueOf(0.9)));
        diorProductNew.setId(diorProduct.getId());
        System.out.println("New Dior product:\n"
                + diorProductNew);
        productService.update(diorProductNew);
        printAllProducts(productService);

        System.out.println("\nDeleting Lime Crime Eyeshadow Palette."
                + "\nLime Crime product:\n"
                + limeCrimeProduct);
        productService.deleteById(limeCrimeProduct.getId());
        printAllProducts(productService);

        /*
        * Second Part:
        * User - CRUD operations usage
        */

        System.out.println("\nCreating three users.");
        UserService userService =
                (UserService) INJECTOR.getInstance(UserService.class);
        User firstUser =
                new User("Diane", "diana_nguyen", "ILoveBoJack123");
        User secondUser =
                new User("Ashley", "halsey", "YungbludRocks789");
        User thirdUser =
                new User("Max", "maxim_dao", "BeautyBlogger456");
        userService.create(firstUser);
        userService.create(secondUser);
        userService.create(thirdUser);
        final Long firstUserId = firstUser.getId();
        final Long secondUserId = secondUser.getId();
        printAllUsers(userService);

        System.out.println("\nChanging password of first user - "
                + firstUser.getName()
                + ".");
        User firstUserNew =
                new User("Diane", "diana_nguyen", "ILoveMyself321");
        firstUserNew.setId(firstUserId);
        System.out.println("New first user:\n"
                + firstUserNew);
        userService.update(firstUserNew);
        printAllUsers(userService);

        System.out.println("\nDeleting third user - "
                + thirdUser.getName()
                + ".");
        userService.deleteById(thirdUser.getId());
        printAllUsers(userService);

        /*
         * Shopping Cart - CRUD operations usage
         */

        System.out.println("\nCreating two shopping carts for the existing Users.");
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) INJECTOR.getInstance(ShoppingCartService.class);
        ShoppingCart firstCart = new ShoppingCart(firstUserId);
        ShoppingCart secondCart = new ShoppingCart(secondUserId);
        shoppingCartService.create(firstCart);
        System.out.println("First user's shopping cart:\n"
                + shoppingCartService.getByUserId(firstUserId));
        shoppingCartService.create(secondCart);
        System.out.println("Second user's shopping cart:\n"
                + shoppingCartService.getByUserId(secondUserId));
        printAllShoppingCarts(userService, shoppingCartService);

        System.out.println("\nFilling existing carts with products.");
        shoppingCartService.addProduct(firstCart, diorProductNew);
        shoppingCartService.addProduct(secondCart, diorProductNew);
        shoppingCartService.addProduct(secondCart, guerlainProduct);
        printAllShoppingCarts(userService, shoppingCartService);

        System.out.println("\nClearing the first user's shopping cart."
                + "\nBefore:");
        printAllShoppingCarts(userService, shoppingCartService);
        shoppingCartService.clear(firstCart);
        System.out.println("After:");
        printAllShoppingCarts(userService, shoppingCartService);

        System.out.println("\nDeleting Dior Foundation from the second cart."
                + "\nBefore:"
                + secondCart);
        shoppingCartService.deleteProduct(secondCart, diorProductNew);
        System.out.println("After:"
                + secondCart);

        System.out.println("\nDeleting the first user's shopping cart."
                + "\nBefore:");
        printAllShoppingCarts(userService, shoppingCartService);
        shoppingCartService.deleteCart(firstCart);
        System.out.println("After:");
        try {
            System.out.println(shoppingCartService.getByUserId(firstUserId));
        } catch (NoSuchElementException exception) {
            System.out.println("First user's shopping cart deleted successfully!"
                    + "\nAll shopping carts:");
            IntStream.range(1, userService.getAll().size())
                    .mapToObj(userService.getAll()::get)
                    .map(User::getId)
                    .map(shoppingCartService::getByUserId)
                    .forEach(System.out::println);
        }

        /*
         * Order - CRUD operations usage
         */

        System.out.println("\nCompleting second user's order.");
        OrderService orderService =
                (OrderService) INJECTOR.getInstance(OrderService.class);
        Order firstOrder = orderService.completeOrder(secondCart);
        System.out.println("First order:"
                + orderService.get(firstOrder.getId()));

        System.out.println("\nCompleting the second order of the same user.");
        shoppingCartService.addProduct(secondCart, diorProductNew);
        Order secondOrder = orderService.completeOrder(secondCart);
        System.out.println("All second user's orders:"
                + orderService.getUsersOrders(firstOrder.getUserId()));

        System.out.println("\nDeleting the second order."
                + "\nBefore:");
        final long secondOrderId = secondOrder.getId();
        printAllOrders(orderService);
        orderService.deleteById(secondOrderId);
        System.out.println("After:");
        printAllOrders(orderService);

    }

    private static void printAllProducts(ProductService productService) {
        System.out.println("All products:");
        productService.getAll()
                .forEach(System.out::println);
    }

    private static void printAllUsers(UserService userService) {
        System.out.println("All users:");
        userService.getAll()
                .forEach(System.out::println);
    }

    private static void printAllShoppingCarts(UserService userService,
                                              ShoppingCartService shoppingCartService) {
        System.out.println("All shopping carts:");
        userService.getAll().stream()
                .map(User::getId)
                .map(shoppingCartService::getByUserId)
                .forEach(System.out::println);
    }

    private static void printAllOrders(OrderService orderService) {
        System.out.print("All orders:");
        orderService.getAll()
                .forEach(System.out::print);
    }
}
