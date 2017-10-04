CREATE TABLE `order_item` (
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  INDEX `fk_order_item_1_idx` (`order_id` ASC),
  CONSTRAINT `fk_order_item_1`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);