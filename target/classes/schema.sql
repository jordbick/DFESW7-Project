-- drop for testing so any new app contexts that are spawned will be fresh
DROP TABLE IF EXISTS `pokemon`;

CREATE TABLE `pokemon` (
	`id` INT AUTO_INCREMENT,
    `pokedexNumber` INT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `canEvolve` BOOLEAN NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`id`),
    -- <> is not equal to
    CHECK(`pokedexNumber` >= 1),
    CHECK(`pokedexNumber` <= 898)
);