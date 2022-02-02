-- drop for testing so any new app contexts that are spawned will be fresh
DROP TABLE IF EXISTS `type`;
DROP TABLE IF EXISTS `pokemon`;


CREATE TABLE `pokemon` (
	`id` BIGINT AUTO_INCREMENT,
    `pokedex_number` INT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `can_evolve` BOOLEAN NOT NULL,
    PRIMARY KEY(`id`),
    CHECK(`pokedex_number` >= 1),
    CHECK(`pokedex_number` <= 898)
);

CREATE TABLE `type` (
`id` BIGINT AUTO_INCREMENT,
`pokemon_type` VARCHAR(255) NOT NULL,
`pokemon_id` BIGINT,
PRIMARY KEY(`id`),
FOREIGN KEY(`pokemon_id`) REFERENCES `pokemon`(`id`)
);