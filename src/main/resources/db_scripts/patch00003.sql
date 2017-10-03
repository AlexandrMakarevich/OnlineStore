CREATE TABLE `order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_1_idx` (`product_id` ASC),
  CONSTRAINT `fk_order_1`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
