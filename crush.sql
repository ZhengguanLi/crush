create database if not exists `crushecommerce`;
use `crushecommerce`;

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` int(11),
  `role_id` int(11),
  PRIMARY KEY (`user_id`,`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
  ON DELETE CASCADE
);

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL UNIQUE,
  `password` char(80) NOT NULL,
  `email` varchar(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id`)
);

--
-- meal part 
--
-- meal(id, name, price, category)
-- extra/topping for salads and pasta: (id, name, price, category)

drop table if exists `category`;
create table `category`(
	id int not null auto_increment,
    name varchar(50),
    primary key (id)
);

drop table if exists `meal`;
create table `meal`(
	id int not null auto_increment,
    name varchar(50),
    price float,
    category varchar(30),
    primary key (id)
);

drop table if exists `extra`;
create table `extra`(
	id int not null auto_increment,
    name varchar(50),
    price float,
    category varchar(30),
    primary key (id)
);

set foreign_key_checks = 1;

-- --------------------------------------------------------------------------
--
-- user part
--

LOCK TABLES `role` WRITE;
INSERT INTO `role` VALUES (1,"ROLE_EMPLOYEE"),(2,"ROLE_MANAGER"),(3,"ROLE_ADMIN"),(4,"ROLE_USER");
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,"john","$2a$04$lks/XsW.aGOU8qqyuEmFxuMhz/AiSu2yYeZsTXiT80hYmhYD8spUO","john@luv2code.com"),
(2,"mary","$2a$04$lks/XsW.aGOU8qqyuEmFxuMhz/AiSu2yYeZsTXiT80hYmhYD8spUO","mary@luv2code.com"),
(3,"susan","$2a$04$lks/XsW.aGOU8qqyuEmFxuMhz/AiSu2yYeZsTXiT80hYmhYD8spUO","susan@luv2code.com");
UNLOCK TABLES;

--
-- Dumping data for table `users_role`
--

LOCK TABLES `users_roles` WRITE;
INSERT INTO `users_roles` VALUES (1,1), (2,1), (3,1), (2,2), (3,3);
UNLOCK TABLES;

-- --------------------------------------------------------------------------
-- category
insert into `category` values (null, "Beverages"), (null, "Salads"), (null, "Pizza"), (null, "Subs"), (null, "Pasta");

--
-- meal
--

-- meal for polular
insert into `meal` values (null, "Garden Salad", 7.75, "Popular"), (null, "Greek Salad", 9.25, "Popular"), (null, "Baked Ziti", 7.75, "Popular");

-- meal for Beveragesuser
insert into `meal` values (null, "2 Liter Sodas", 4.00, "Beverages"), (null, "Drinks", 2.75, "Beverages");

-- meal for Salads
insert into `meal` values (null, "Garden Salad", 7.75, "Salads"), (null, "Greek Salad", 9.25, "Salads"),
(null, "Antipasto Salad", 9.25, "Salads");

-- meal for Pizza
insert into `meal` values (null, "Regular Cheese Pizza", 13.45, "Pizza"), (null, "Sicilian Cheese Pizza", 24.95, "Pizza");

-- meal for Subs
insert into `meal` values (null, "Cheese Sub", 7.50, "Subs"), (null, "Italian Sub", 7.50, "Subs"),
(null, "Ham and Cheese Sub", 7.50, "Subs"), (null, "Meatball Sub", 7.50, "Subs"),
(null, "Tuna Sub", 7.50, "Subs"), (null, "Turkey Sub", 7.95, "Subs"),
(null, "Chicken Parmigiana Sub", 7.95, "Subs"), (null, "Eggplant Parmigiana Sub", 7.50, "Subs"),
(null, "Steak Sub", 7.75, "Subs"), (null, "Steak and Cheese Sub", 8.25, "Subs"),
(null, "Sausage, Peppers and Onions Sub", 9.20, "Subs"), (null, "Hamburger Sub", 8.95, "Subs"),
(null, "Cheeseburger Sub", 6.50, "Subs"), (null, "Fried Chicken Sub", 8.50, "Subs"),
(null, "Veggie Sub", 8.50, "Subs");

-- meal for Pasta
insert into `meal` values (null, "Baked Ziti", 7.75, "Pasta");

--
-- extras/toppings
--
-- extra for beverages
insert into `extra` values (null, "Pepsi", 0, "2 Liter Sodas"), (null, "Diet Pepsi", 0, "2 Liter Sodas"),
(null, "Ginger Ale", 0, "2 Liter Sodas");

insert into `extra` values (null, "20 oz Pepsi", 0.25, "Drinks"), (null, "20 oz Diet Pepsi", 0.25, "Drinks"), 
(null, "20 oz Ginger Ale", 0.25, "Drinks"), (null, "20 oz Mountain Dew", 0.25, "Drinks"),
(null, "Bottled Water", 0, "Drinks"), (null, "Gatorade", 0.25, "Drinks"), (null, "IBC Root Beer", 0.25, "Drinks"); 

-- salad extras
insert into `extra` values (null, "Tuna", 1.45, "Salads"), (null, "Chicken", 1.45, "Salads"); 

-- sub extras
insert into `extra` values (null, "Lettuce", 0, "Subs"), (null, "Tomatoes", 0, "Subs"), (null, "Onions", 0, "Subs"),
(null, "Pickles", 0, "Subs"), (null, "Hot Peppers", 0, "Subs"), (null, "Mayo", 0, "Subs"), (null, "Mustard", 0, "Subs"), 
(null, "Ketchup", 0, "Subs"), (null, "Oil", 0, "Subs"), (null, "Vinegar", 0, "Subs"), (null, "Salt", 0, "Subs"), 
(null, "Pepper", 0, "Subs");

-- pizza toppings
insert into `extra` values (null, "Pepperoni", 1.50, "Pizza"), (null, "Green Peppers", 1.50, "Pizza"), 
(null, "Sausage", 1.50, "Pizza"), (null, "Ham", 1.50, "Pizza"), (null, "Pineapple", 1.50, "Pizza"), 
(null, "Zucchini", 1.50, "Pizza"), (null, "Anchovies", 1.50, "Pizza"), (null, "Eggplant", 1.50, "Pizza"), 
(null, "Mushroom", 1.50, "Pizza"), (null, "Onions", 1.50, "Pizza"), (null, "Hamburger", 1.50, "Pizza"),
(null, "Spinach", 1.50, "Pizza"), (null, "Antichoke", 1.50, "Pizza"), (null, "Black Olive", 1.50, "Pizza"), 
(null, "Fresh Garlic", 1.50, "Pizza"), (null, "Meantball", 1.50, "Pizza"), (null, "Pizza Rossa", 1.50, "Pizza"),
(null, "Tomato and Basil", 3.00, "Pizza"), (null, "Buffalo Chicken", 3.00, "Pizza"), (null, "BBQ Chicken", 3.00, "Pizza"),
(null, "Chicken Pesto", 3.00, "Pizza");

-- pasta extra
insert into `extra` values (null, "Meatball", 2.00, "Pasta"), (null, "Chicken", 2.45, "Pasta");