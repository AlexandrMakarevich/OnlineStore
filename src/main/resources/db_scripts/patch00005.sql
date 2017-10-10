CREATE TABLE `order_order_item_map` (
  `order_id` INT NOT NULL,
  `order_item_id` INT NOT NULL,
  INDEX `index_order_id_map` (`order_id` ASC),
  INDEX `index_order_item_id_map` (`order_item_id` ASC),
  CONSTRAINT `fk_order_id_map`
    FOREIGN KEY (`order_id`)
    REFERENCES `order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_item_id_map`
    FOREIGN KEY (`order_item_id`)
    REFERENCES `order_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);