use pricemonitoring;

-- -----------------------------------------------------
-- Table 'addresses`
-- -----------------------------------------------------
INSERT INTO addresses (country, city, street, building) VALUES ('Беларусь', 'Минск', 'Притыцкого', '156');
INSERT INTO addresses (country, city, street, building) VALUES ('Беларусь', 'Минск', 'Казимировская', '6');
INSERT INTO addresses (country, city, street, building) VALUES ('Беларусь', 'Минск', 'Горецкого', '2');
INSERT INTO addresses (country, city, street, building) VALUES ('Беларусь', 'Минск', 'Налибокская', '1');

-- -----------------------------------------------------
-- Table `sections`
-- -----------------------------------------------------
INSERT INTO sections (title) VALUES ('Бакалея');
INSERT INTO sections (title) VALUES ('Овощи');
INSERT INTO sections (title) VALUES ('Фрукты');
INSERT INTO sections (title) VALUES ('Хлебобулочные изделия');


-- -----------------------------------------------------
-- Table `categories`
-- -----------------------------------------------------
INSERT INTO categories (title, sections_id) VALUES ('Макароны', '1');
INSERT INTO categories (title, sections_id) VALUES ('Крупы', '1');
INSERT INTO categories (title, sections_id) VALUES ('Мука', '1');
INSERT INTO categories (title, sections_id) VALUES ('Сахар', '1');
INSERT INTO categories (title, sections_id) VALUES ('Хлопья, каши', '1');
INSERT INTO categories (title, sections_id) VALUES ('Томат', '2');
INSERT INTO categories (title, sections_id) VALUES ('Баклажан', '2');
INSERT INTO categories (title, sections_id) VALUES ('Огурец', '2');
INSERT INTO categories (title, sections_id) VALUES ('Лук', '2');
INSERT INTO categories (title, sections_id) VALUES ('Яблоко', '3');
INSERT INTO categories (title, sections_id) VALUES ('Груша', '3');
INSERT INTO categories (title, sections_id) VALUES ('Банан', '3');
INSERT INTO categories (title, sections_id) VALUES ('Слива', '3');
INSERT INTO categories (title, sections_id) VALUES ('Виноград', '3');
INSERT INTO categories (title, sections_id) VALUES ('Хлеб', '4');
INSERT INTO categories (title, sections_id) VALUES ('Батон', '4');


-- -----------------------------------------------------
-- Table `units`
-- -----------------------------------------------------
INSERT INTO units (title) VALUES ('кг');
INSERT INTO units (title) VALUES ('шт');


-- -----------------------------------------------------
-- Table `origin`
-- -----------------------------------------------------
INSERT INTO origin (title) VALUES ('Беларусь');
INSERT INTO origin (title) VALUES ('Италия');
INSERT INTO origin (title) VALUES ('Польша');
INSERT INTO origin (title) VALUES ('Германия');
INSERT INTO origin (title) VALUES ('Россия');
INSERT INTO origin (title) VALUES ('Украина');
INSERT INTO origin (title) VALUES ('Финляндия');
INSERT INTO origin (title) VALUES ('Аргентина');


-- -----------------------------------------------------
-- Table `products`
-- -----------------------------------------------------
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Макароны Reggia', '2', '2', '1');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Макароны Barilla', '2', '5', '1');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Макароны Divella', '2', '2', '1');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Макароны Makfa', '2', '5', '1');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Макароны Барымак', '2', '1', '1');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Рис Gusto', '2', '1', '2');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Рис Food Collection', '2', '5', '2');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Рис Эколайн', '2', '1', '2');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Мука Makfa', '2', '5', '3');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Мука Гаспадар', '2', '1', '3');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Мука Лидская', '2', '1', '3');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Мука Нордик', '2', '7', '3');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Сахар Слуцкий', '2', '1', '4');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Сахар Скидельский', '2', '1', '4');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Хлопья овсяные Экстра', '2', '1', '5');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Хлопья овсяные Геркулес', '2', '1', '5');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Хлопья овсяные Сила злаков', '2', '5', '5');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Томат красный', '1', '5', '6');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Томат желтый', '1', '1', '6');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Баклажан', '1', '1', '7');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Огурец колючий', '1', '3', '8');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Лук репчатый', '1', '5', '9');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Яблоко Мутсу', '1', '1', '10');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Яблоко Ред Принц', '1', '3', '10');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Яблоко крупное', '1', '3', '10');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Груша Конференция', '1', '3', '11');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Груша Форел', '1', '8', '11');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Груша Пакхам', '1', '6', '11');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Банан', '1', '8', '12');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Слива красная', '1', '3', '13');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Слива желтая', '1', '6', '13');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Виноград Ред Глоб', '1', '8', '14');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Хлеб ржаной', '2', '4', '15');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Хлеб тостовый', '2', '1', '15');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Хлеб тостовый новый', '2', '5', '15');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Батон вкусный', '2', '1', '16');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Батон семейный', '2', '1', '16');
INSERT INTO products (name, units_id, origin_id, categories_id) VALUES ('Батон любительский', '2', '1', '16');



-- -----------------------------------------------------
-- Table `stores`
-- -----------------------------------------------------
INSERT INTO stores (name, phone, addresses_id) VALUES ('Green', '+375297886933', '1');
INSERT INTO stores (name, phone, addresses_id) VALUES ('Евроопт', '+375296896934', '2');
INSERT INTO stores (name, phone, addresses_id) VALUES ('Гиппо', '+375293896955', '3');
INSERT INTO stores (name, phone, addresses_id) VALUES ('Соседи', '+375297687914', '4');


-- -----------------------------------------------------
-- Table `assortment`
-- -----------------------------------------------------
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.50', '18849', '1', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.45', '18849', '2', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.20', '18849', '3', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.95', '18849', '4', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.45', '18849', '5', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.10', '18849', '6', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.70', '18849', '7', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.15', '18849', '8', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.99', '18849', '9', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.20', '18849', '10', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.15', '18849', '11', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('9.20', '18849', '12', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.73', '18849', '14', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.75', '18849', '15', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.70', '18849', '16', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.35', '18849', '18', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.70', '18849', '19', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.50', '18849', '20', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.40', '18849', '21', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.75', '18849', '22', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.55', '18849', '23', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.25', '18849', '24', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.65', '18849', '26', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('8.99', '18849', '27', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.75', '18849', '28', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.80', '18849', '29', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.90', '18849', '30', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('6.20', '18849', '31', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.25', '18849', '33', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.60', '18849', '34', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.80', '18849', '35', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.95', '18849', '36', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.26', '18849', '37', '1');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.30', '18849', '38', '1');

INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.10', '18849', '1', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.30', '18849', '2', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.90', '18849', '3', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.90', '18849', '4', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.30', '18849', '5', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.10', '18849', '6', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.50', '18849', '7', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.10', '18849', '8', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.00', '18849', '9', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.10', '18849', '10', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.55', '18849', '11', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('8.40', '18849', '12', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.72', '18849', '13', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.73', '18849', '14', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.75', '18849', '15', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.65', '18849', '16', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.69', '18849', '17', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.99', '18849', '18', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.65', '18849', '19', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.50', '18849', '20', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.40', '18849', '21', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.55', '18849', '22', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.65', '18849', '23', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.05', '18849', '24', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.65', '18849', '25', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.65', '18849', '26', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('8.25', '18849', '27', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.45', '18849', '28', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.99', '18849', '29', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.65', '18849', '30', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('6.20', '18849', '31', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('9.90', '18849', '32', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.20', '18849', '33', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.60', '18849', '34', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.70', '18849', '35', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.87', '18849', '36', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.36', '18849', '37', '2');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.20', '18849', '38', '2');

INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.25', '18849', '1', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.15', '18849', '2', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.90', '18849', '3', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.99', '18849', '4', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.45', '18849', '5', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.15', '18849', '6', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.50', '18849', '7', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.30', '18849', '8', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.15', '18849', '10', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.45', '18849', '11', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('8.50', '18849', '12', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.72', '18849', '13', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.72', '18849', '14', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.75', '18849', '15', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.65', '18849', '16', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.79', '18849', '17', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.35', '18849', '18', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.90', '18849', '19', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.50', '18849', '20', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.40', '18849', '21', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.60', '18849', '22', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.65', '18849', '23', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.25', '18849', '24', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.85', '18849', '25', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.40', '18849', '26', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('8.50', '18849', '27', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.55', '18849', '28', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.99', '18849', '29', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.70', '18849', '30', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('6.50', '18849', '31', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('9.99', '18849', '32', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.30', '18849', '33', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.60', '18849', '34', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.75', '18849', '35', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.85', '18849', '36', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.36', '18849', '37', '3');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.25', '18849', '38', '3');

INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.10', '18849', '1', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.35', '18849', '2', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.30', '18849', '4', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.68', '18849', '5', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.18', '18849', '6', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.63', '18849', '7', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.99', '18849', '8', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.55', '18849', '9', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.10', '18849', '10', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.55', '18849', '11', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.75', '18849', '13', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.75', '18849', '14', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('0.75', '18849', '15', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.99', '18849', '17', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.20', '18849', '19', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.40', '18849', '21', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.63', '18849', '22', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.65', '18849', '23', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.28', '18849', '24', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.75', '18849', '25', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('2.45', '18849', '26', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('5.45', '18849', '28', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.99', '18849', '29', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('3.65', '18849', '30', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('6.58', '18849', '31', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('10.20', '18849', '32', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.20', '18849', '33', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.68', '18849', '34', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.99', '18849', '35', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.38', '18849', '37', '4');
INSERT INTO assortment (price, update_date, products_id, stores_id) VALUES ('1.39', '18849', '38', '4');







-- -----------------------------------------------------
-- Table `histories`
-- -----------------------------------------------------
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.50','1');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.10', '2');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.60', '3');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.80', '4');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.50', '5');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.20', '6');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.25', '7');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.28', '8');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','3.50', '9');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.20', '10');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.65', '11');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','8.50', '12');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.72', '13');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.72', '14');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','0.85', '15');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','0.63', '16');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.79', '17');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.89', '18');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','5.75', '19');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.60', '20');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.50', '21');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.58', '22');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.75', '23');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.25', '24');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.63', '25');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','2.67', '26');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','8.35', '27');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','5.35', '28');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.85', '29');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','3.45', '30');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','6.30', '31');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','9.80', '32');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.10', '33');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.40', '34');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.80', '35');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','0.97', '36');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.46', '37');
INSERT INTO histories (price_date, price, products_id) VALUES ('18803','1.30', '38');


INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.05','1');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.10', '2');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.70', '3');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.80', '4');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.50', '5');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.30', '6');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.40', '7');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.90', '8');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','3.60', '9');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.05', '10');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.50', '11');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','8.30', '12');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.72', '13');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.73', '14');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','0.71', '15');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','0.67', '16');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.69', '17');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.20', '18');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','5.65', '19');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.40', '20');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.45', '21');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.35', '22');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.55', '23');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.15', '24');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.15', '25');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','2.35', '26');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','8.25', '27');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','5.99', '28');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.99', '29');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','3.35', '30');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','6.10', '31');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','9.00', '32');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.23', '33');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.61', '34');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.78', '35');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','0.99', '36');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.16', '37');
INSERT INTO histories (price_date, price, products_id) VALUES ('18815','1.25', '38');


INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.99','1');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.20', '2');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.90', '3');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.90', '4');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.35', '5');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.18', '6');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.20', '7');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.70', '8');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','3.00', '9');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.50', '10');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.75', '11');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','8.95', '12');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.72', '13');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.72', '14');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','0.35', '15');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','0.55', '16');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.03', '17');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.99', '18');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','5.15', '19');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.48', '20');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.45', '21');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.55', '22');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.75', '23');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.95', '24');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.45', '25');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','2.45', '26');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','8.85', '27');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','5.15', '28');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.99', '29');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','3.15', '30');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','6.37', '31');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','9.99', '32');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.30', '33');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.79', '34');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.79', '35');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','0.97', '36');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.28', '37');
INSERT INTO histories (price_date, price, products_id) VALUES ('18839','1.18', '38');



INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.10','1');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.30', '2');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.90', '3');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.90', '4');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.30', '5');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.10', '6');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.50', '7');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.10', '8');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','3.00', '9');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.10', '10');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.55', '11');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','8.40', '12');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.72', '13');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.73', '14');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','0.75', '15');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','0.65', '16');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.69', '17');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.99', '18');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','5.65', '19');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.50', '20');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.40', '21');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.55', '22');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.65', '23');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.05', '24');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.65', '25');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','2.65', '26');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','8.25', '27');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','5.45', '28');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.99', '29');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','3.65', '30');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','6.20', '31');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','9.90', '32');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.20', '33');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.60', '34');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.70', '35');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','0.87', '36');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.36', '37');
INSERT INTO histories (price_date, price, products_id) VALUES ('18849','1.20', '38');

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
INSERT INTO users (login, password, email) VALUES ('admin','$2a$10$dMJtp1aBXvr6vHUCPVtcMOtoYm0sYlH7uHTQomaA1YzDC6pg64qOu', 'admin@mail.ru');
INSERT INTO users (login, password, email) VALUES ('user','$10$7bQ6NoP5rGXWY3XDdwyG3e4AWi9BRrOqvaIpTHLUk41LphczDfSFu', 'user@mail.ru');



-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
INSERT INTO roles (value) VALUES ('ROLE_ADMIN');
INSERT INTO roles (value) VALUES ('ROLE_USER');


-- -----------------------------------------------------
-- Table `users_roles`
-- -----------------------------------------------------
INSERT INTO users_roles (users_id, roles_id) VALUES ('1','1');
INSERT INTO users_roles (users_id, roles_id) VALUES ('1','2');
INSERT INTO users_roles (users_id, roles_id) VALUES ('2','1');