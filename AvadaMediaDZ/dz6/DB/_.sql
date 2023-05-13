CREATE SCHEMA `dz6` ;
 
CREATE TABLE `dz6`.`seo_block` (
  `id` SERIAL NOT NULL,
  `seo_url` TEXT NULL,
  `seo_title` TEXT NULL,
  `seo_keywords` TEXT NULL,
  `seo_description` LONGTEXT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `dz6`.`advertising` (
  `id` SERIAL NOT NULL,
  `name` TEXT NULL,
  `description` LONGTEXT NULL,
  `path_to_main_image` TEXT NULL,
  `state` BOOLEAN NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_seo_block_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_seo_block`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`cinema` (
  `id` SERIAL NOT NULL,
  `name` TEXT NULL,
  `description` LONGTEXT NULL,
  `about_cinema` LONGTEXT NULL,
  `conditions` LONGTEXT NULL,
  `path_to_logo_image` TEXT NULL,
  `path_to_top_banner_image` TEXT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_seo_block_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_seo_block_id`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`movie` (
  `id` SERIAL NOT NULL,
  `name` TEXT NULL,
  `rating` FLOAT NULL,
  `year` INT NULL,
  `country` TEXT NULL,
  `director` TEXT NULL,
  `producer` TEXT NULL,
  `screenwriter` TEXT NULL,
  `genre` TEXT NULL,
  `age` TEXT NULL,
  `time` TEXT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `dz6`.`type_movie` (
  `id` SERIAL NOT NULL,
  `three d` BOOLEAN NOT NULL,
  `two d` BOOLEAN NOT NULL,
  `imax` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `dz6`.`movie_page` (
  `id` SERIAL NOT NULL,
  `title` TEXT NULL,
  `description` TEXT NULL,
  `path_to_main_image` TEXT NULL,
  `trailer_link` TEXT NULL,
  `movie_id` BIGINT UNSIGNED NOT NULL,
  `type_movie_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_movie_id_idx` (`movie_id` ASC) VISIBLE,
  INDEX `fk_type_movie_id_idx` (`type_movie_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id1_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_id`
    FOREIGN KEY (`movie_id`)
    REFERENCES `dz6`.`movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_type_movie_id`
    FOREIGN KEY (`type_movie_id`)
    REFERENCES `dz6`.`type_movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id1`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`showing_movie` (
  `id` SERIAL NOT NULL,
  `movie_page_id` BIGINT UNSIGNED NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_movie_page_id_idx` (`movie_page_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_page_id`
    FOREIGN KEY (`movie_page_id`)
    REFERENCES `dz6`.`movie_page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`hall` (
  `id` SERIAL NOT NULL,
  `number` TEXT NOT NULL,
  `description` LONGTEXT NULL,
  `path_to_layout_hall_image` TEXT NULL,
  `path_to_top_banner_image` TEXT NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id_idx` (`cinema_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id1_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id2`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id2`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`hall_place` (
  `id` SERIAL NOT NULL,
  `row` INT NOT NULL,
  `number_of_place` INT NOT NULL,
  `hall_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hall_id_idx` (`hall_id` ASC) VISIBLE,
  CONSTRAINT `fk_hall_id`
    FOREIGN KEY (`hall_id`)
    REFERENCES `dz6`.`hall` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- CREATE TABLE `dz6`.`take_up_place_list` (
--   `id` SERIAL NOT NULL,
--   `showing_movie_id` BIGINT UNSIGNED NOT NULL,
--   `hall_place_id` BIGINT UNSIGNED NOT NULL,
--   PRIMARY KEY (`id`),
--   INDEX `fk_showing_movie_id_idx` (`showing_movie_id` ASC) VISIBLE,
--   INDEX `fk_hall_place_id_idx` (`hall_place_id` ASC) VISIBLE,
--   CONSTRAINT `fk_showing_movie_id`
--     FOREIGN KEY (`showing_movie_id`)
--     REFERENCES `dz6`.`showing_movie` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE,
--   CONSTRAINT `fk_hall_place_id`
--     FOREIGN KEY (`hall_place_id`)
--     REFERENCES `dz6`.`hall_place` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE
-- );

CREATE TABLE `dz6`.`take_up_place_list` (
  `id` SERIAL NOT NULL,
  `showing_movie_id` BIGINT UNSIGNED NOT NULL,
  `hall_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_showing_movie_id_idx` (`showing_movie_id` ASC) VISIBLE,
  INDEX `fk_hall_id_idx` (`hall_id` ASC) VISIBLE,
  CONSTRAINT `fk_showing_movie_id`
    FOREIGN KEY (`showing_movie_id`)
    REFERENCES `dz6`.`showing_movie` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_hall_id9`
    FOREIGN KEY (`hall_id`)
    REFERENCES `dz6`.`hall` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`news` (
  `id` SERIAL NOT NULL,
  `title` TEXT NULL,
  `description` LONGTEXT NULL,
  `date_` DATE NULL,
  `path_to_main_image` TEXT NULL,
  `video_link` TEXT NULL,
  `state` BOOLEAN NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id3_idx` (`cinema_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id3_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id3`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id3`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`stock` (
  `id` SERIAL NOT NULL,
  `title` TEXT NULL,
  `description` LONGTEXT NULL,
  `path_to_main_image` TEXT NULL,
  `video_link` TEXT NULL,
  `state` BOOLEAN NOT NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id5_idx` (`cinema_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id5_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id5`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id5`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`cafe_bar` (
  `id` SERIAL NOT NULL,
  `title` TEXT NULL,
  `description` LONGTEXT NULL,
  `path_to_main_image` TEXT NULL,
  `state` BOOLEAN NOT NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id6_idx` (`cinema_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id6_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id6`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id6`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`vip_hall` (
  `id` SERIAL NOT NULL,
  `title` TEXT NULL,
  `description` LONGTEXT NULL,
  `path_to_main_image` TEXT NULL,
  `state` BOOLEAN NOT NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id7_idx` (`cinema_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id7_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id7`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id7`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`children_s_room` (
  `id` SERIAL NOT NULL,
  `title` TEXT NULL,
  `description` LONGTEXT NULL,
  `path_to_main_image` TEXT NULL,
  `state` BOOLEAN NOT NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id8_idx` (`cinema_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id8_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id8`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id8`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`contacts` (
  `id` SERIAL NOT NULL,
  `address` LONGTEXT NULL,
  `coordinate_for_map` TEXT NULL,
  `state` BOOLEAN NOT NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  `seo_block_id` BIGINT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id9_idx` (`cinema_id` ASC) VISIBLE,
  INDEX `fk_seo_block_id9_idx` (`seo_block_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id9`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_seo_block_id9`
    FOREIGN KEY (`seo_block_id`)
    REFERENCES `dz6`.`seo_block` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_movie_page` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `movie_page_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_movie_page_id1_idx` (`movie_page_id` ASC) VISIBLE,
  CONSTRAINT `fk_movie_page_id1`
    FOREIGN KEY (`movie_page_id`)
    REFERENCES `dz6`.`movie_page` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_cinema` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `cinema_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cinema_id10_idx` (`cinema_id` ASC) VISIBLE,
  CONSTRAINT `fk_cinema_id10`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `dz6`.`cinema` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_hall` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `hall_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hall_id2_idx` (`hall_id` ASC) VISIBLE,
  CONSTRAINT `fk_hall_id2`
    FOREIGN KEY (`hall_id`)
    REFERENCES `dz6`.`hall` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_news` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `news_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_news_id_idx` (`news_id` ASC) VISIBLE,
  CONSTRAINT `fk_news_id`
    FOREIGN KEY (`news_id`)
    REFERENCES `dz6`.`news` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_stock` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `stock_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_stock_id_idx` (`stock_id` ASC) VISIBLE,
  CONSTRAINT `fk_stock_id`
    FOREIGN KEY (`stock_id`)
    REFERENCES `dz6`.`stock` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_cafe_bar` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `cafe_bar_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cafe_bar_id_idx` (`cafe_bar_id` ASC) VISIBLE,
  CONSTRAINT `fk_cafe_bar_id`
    FOREIGN KEY (`cafe_bar_id`)
    REFERENCES `dz6`.`cafe_bar` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_vip_hall` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `vip_hall_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vip_hall_id_idx` (`vip_hall_id` ASC) VISIBLE,
  CONSTRAINT `fk_vip_hall_id`
    FOREIGN KEY (`vip_hall_id`)
    REFERENCES `dz6`.`vip_hall` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_children_s_room` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `children_s_room_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_children_s_room_id_idx` (`children_s_room_id` ASC) VISIBLE,
  CONSTRAINT `fk_children_s_room_id`
    FOREIGN KEY (`children_s_room_id`)
    REFERENCES `dz6`.`children_s_room` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`gallery_for_contacts` (
  `id` SERIAL NOT NULL,
  `path_to_image` TEXT NOT NULL,
  `contacts_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contacts_id_idx` (`contacts_id` ASC) VISIBLE,
  CONSTRAINT `fk_contacts_id`
    FOREIGN KEY (`contacts_id`)
    REFERENCES `dz6`.`contacts` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

CREATE TABLE `dz6`.`city` (
  `id` SERIAL NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `dz6`.`gender` (
  `id` SERIAL NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `dz6`.`user` (
  `id` SERIAL NOT NULL,
  `name` TEXT NOT NULL,
  `surname` TEXT NOT NULL,
  `nickname` TEXT NOT NULL,
  `e_mail` TEXT NULL,
  `address` TEXT NULL,
  `password` TEXT NOT NULL,
  `card_number` TEXT NULL,
  `language` TEXT NULL,
  `gender_id` BIGINT UNSIGNED NOT NULL,
  `phone` TEXT NULL,
  `birthday` DATE NULL,
  `city_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_gender_id_idx` (`gender_id` ASC) VISIBLE,
  INDEX `fk_city_id_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_gender_id`
    FOREIGN KEY (`gender_id`)
    REFERENCES `dz6`.`gender` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_city_id`
    FOREIGN KEY (`city_id`)
    REFERENCES `dz6`.`city` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- CREATE TABLE `dz6`.`ticket_booking` (
--   `id` SERIAL NOT NULL,
--   `user_id` BIGINT UNSIGNED NOT NULL,
--   `take_up_place_list_id` BIGINT UNSIGNED NOT NULL,
--   `date_` DATE NOT NULL,
--   PRIMARY KEY (`id`),
--   INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
--   INDEX `fk_take_up_place_list_id_idx` (`take_up_place_list_id` ASC) VISIBLE,
--   CONSTRAINT `fk_user_id`
--     FOREIGN KEY (`user_id`)
--     REFERENCES `dz6`.`user` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE,
--   CONSTRAINT `fk_take_up_place_list_id`
--     FOREIGN KEY (`take_up_place_list_id`)
--     REFERENCES `dz6`.`take_up_place_list` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE CASCADE
-- );

CREATE TABLE `dz6`.`ticket_booking` (
  `id` SERIAL NOT NULL,
  `user_id` BIGINT UNSIGNED NOT NULL,
  `take_up_place_list_id` BIGINT UNSIGNED NOT NULL,
  `hall_place_id` BIGINT UNSIGNED NOT NULL,
  `date_` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_take_up_place_list_id_idx` (`take_up_place_list_id` ASC) VISIBLE,
  INDEX `fk_hall_place_id_idx` (`hall_place_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `dz6`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_take_up_place_list_id`
    FOREIGN KEY (`take_up_place_list_id`)
    REFERENCES `dz6`.`take_up_place_list` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_hall_place_id`
    FOREIGN KEY (`hall_place_id`)
    REFERENCES `dz6`.`hall_place` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
