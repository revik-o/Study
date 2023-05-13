/**
* INNER JOIN
*/
SELECT `hall`.`id`,
    `hall`.`number`,
    `hall`.`description`,
    `hall`.`path_to_layout_hall_image`,
    `hall`.`path_to_top_banner_image`,
    `cinema`.`name`
FROM `dz6`.`hall`
INNER JOIN `cinema` ON `cinema`.id = `hall`.`cinema_id`;

/**
* LEFT JOIN -> занятые места (где null то пустое)
*/
SELECT `hall_place`.`id`,
    `hall_place`.`row`,
    `hall_place`.`number_of_place`,
    `hall`.`number`,
    `user`.`name`,
    `user`.surname
FROM `dz6`.`hall_place`
JOIN `hall` ON `hall`.id = `hall_place`.`hall_id`
LEFT JOIN `user` ON `user`.id = (SELECT user_id FROM dz6.ticket_booking WHERE hall_place_id = `hall_place`.`id`);

/**
* RIGHT JOIN -> забронированные билеты (где null - человек не бронировал)
*/
SELECT `user`.`name`,
    `user`.surname,
    `ticket_booking`.`date_`
FROM `dz6`.`ticket_booking`
RIGHT JOIN `user` ON `user`.id = `ticket_booking`.`user_id`;



-- -----------------------
SELECT `hall`.`id`,
    `hall`.`number`,
    `hall`.`description`,
    `hall`.`path_to_layout_hall_image`,
    `hall`.`path_to_top_banner_image`,
    cinema.name,
    (SELECT COUNT(hall_id) FROM hall_place WHERE hall_place.hall_id = `hall`.`id`) as count
FROM `dz6`.`hall`
join cinema on cinema.id = cinema_id;
