create table `product_department_map`(
  `product_id` int not null,
  `department_id` int not null,
  index `index_product_department_map_1`(`product_id` asc),
  index `index_product_department_map_2`(`department_id` asc),
  constraint `fk_product_department_map_1`
  foreign key (`product_id`)
  references `product`(`id`),
  constraint `fk_product_department_map_2`
  foreign key (`department_id`)
  references `department`(`id`))