-- drop for testing so any new app contexts that are spawned will be fresh
DROP TABLE IF EXISTS `pokemon`;

CREATE TABLE `pokemon` (
	`id` LONG AUTO_INCREMENT,
    `pokedex_number` INT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `can_evolve` BOOLEAN NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`id`),
    CHECK(`pokedex_number` >= 1),
    CHECK(`pokedex_number` <= 898)
);