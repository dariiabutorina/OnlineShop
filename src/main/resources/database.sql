CREATE SCHEMA `top_store` DEFAULT CHARACTER SET utf8;

CREATE TABLE `top_store`.`product`
(
    `id`      BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(256) NOT NULL,
    `price`   DOUBLE       NOT NULL,
    `deleted` TINYINT      NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `top_store`.`shopping_cart`
(
    `id`      BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `deleted` TINYINT    NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE
);

CREATE TABLE `top_store`.`user`
(
    `id`       BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(256) NOT NULL,
    `login`    VARCHAR(256) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `deleted`  TINYINT      NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
);

CREATE TABLE `top_store`.`orders`
(
    `id`      BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `deleted` TINYINT    NULL DEFAULT 0,
    PRIMARY KEY (`id`)
);

CREATE TABLE `top_store`.`role`
(
    `id`   BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);

CREATE TABLE `top_store`.`user_role`
(
    `id_user` BIGINT(11) NOT NULL,
    `id_role` BIGINT(11) NOT NULL,
    INDEX `ur_fk_user_idx` (`id_user` ASC) VISIBLE,
    INDEX `ur_fk_role_idx` (`id_role` ASC) VISIBLE,
    CONSTRAINT `ur_fk_user`
        FOREIGN KEY (`id_user`)
            REFERENCES `top_store`.`user` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `ur_fk_role`
        FOREIGN KEY (`id_role`)
            REFERENCES `top_store`.`role` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `top_store`.`order_product`
(
    `id_order`   BIGINT(11) NOT NULL,
    `id_product` BIGINT(11) NOT NULL,
    INDEX `op_fk_order_idx` (`id_order` ASC) VISIBLE,
    INDEX `op_fk_product_idx` (`id_product` ASC) VISIBLE,
    CONSTRAINT `op_fk_order`
        FOREIGN KEY (`id_order`)
            REFERENCES `top_store`.`order` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `op_fk_product`
        FOREIGN KEY (`id_product`)
            REFERENCES `top_store`.`product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);


CREATE TABLE `top_store`.`shopping_cart_product`
(
    `id_shopping_cart` BIGINT(11) NOT NULL,
    `id_product`       BIGINT(11) NOT NULL,
    INDEX `scp_fk_shopping_cart_idx` (`id_shopping_cart` ASC) VISIBLE,
    INDEX `scp_fk_product_idx` (`id_product` ASC) VISIBLE,
    CONSTRAINT `scp_fk_shopping_cart`
        FOREIGN KEY (`id_shopping_cart`)
            REFERENCES `top_store`.`shopping_cart` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `scp_fk_product`
        FOREIGN KEY (`id_product`)
            REFERENCES `top_store`.`product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

INSERT INTO top_store.role(name) VALUE ('ADMIN');
INSERT INTO top_store.role(name) VALUE ('USER');
INSERT INTO top_store.product(name, price) VALUES ('Dior Backstage Foundation', 2000.0);
INSERT INTO top_store.product(name, price) VALUES ('Lime Crime Nude Eyeshadow Palette', 800.0);
INSERT INTO top_store.product(name, price) VALUES ('Guerlain So Volume Mascara', 1200.0);
INSERT INTO top_store.user(name, login, password) VALUES ('Dariia', 'DariiaPikul', 'Admin007');
INSERT INTO top_store.user(name, login, password) VALUES ('Ashley', 'halsey', 'YungbludRocks789');
INSERT INTO top_store.user(name, login, password) VALUES ('Max', 'maxim_dao', 'BeautyBlogger456');
INSERT INTO top_store.user_role(id_user, id_role) VALUES (1, 1);
INSERT INTO top_store.user_role(id_user, id_role) VALUES (2, 2);
INSERT INTO top_store.user_role(id_user, id_role) VALUES (3, 2);
INSERT INTO top_store.shopping_cart(user_id) VALUES (2);
INSERT INTO top_store.shopping_cart(user_id) VALUES (3);
