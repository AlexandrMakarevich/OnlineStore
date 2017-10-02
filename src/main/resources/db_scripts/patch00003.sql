CREATE TABLE `orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_1_idx` (`product_id` ASC),
  CONSTRAINT `fk_orders_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `store`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
