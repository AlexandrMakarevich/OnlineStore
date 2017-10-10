CREATE TABLE `order_item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `index_order_item` (`product_id` ASC),
  CONSTRAINT `fk_order_item`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
