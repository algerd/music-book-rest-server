DROP TABLE IF EXISTS musician_song;
DROP TABLE IF EXISTS musician_instrument;
DROP TABLE IF EXISTS musician_album;
DROP TABLE IF EXISTS musician_group;
DROP TABLE IF EXISTS musician_genre;
DROP TABLE IF EXISTS song_genre;
DROP TABLE IF EXISTS album_genre;
DROP TABLE IF EXISTS artist_genre;
DROP TABLE IF EXISTS artist_reference;
DROP TABLE IF EXISTS musician;
DROP TABLE IF EXISTS instrument;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS song;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS artist;

CREATE TABLE `musicbook`.`artist` (
  `id`          INT             NOT NULL AUTO_INCREMENT COMMENT 'id группы',
  `name`        VARCHAR(255)    NOT NULL                COMMENT 'Название группы',
  `rating`      INT             DEFAULT 0               COMMENT 'Рейтинг группы',
  `description` BLOB(10000)                             COMMENT 'Описание группы',
  `image_link`  VARCHAR(255)    DEFAULT ""              COMMENT 'Ссылка изображения',
  PRIMARY KEY (`id`))
COMMENT = 'Таблица групп';

CREATE TABLE `musicbook`.`album` (
  `id`          INT             NOT NULL AUTO_INCREMENT COMMENT 'Id альбома',
  `id_artist`   INT             NOT NULL DEFAULT 1      COMMENT 'Id артиста',
  `name`        VARCHAR(255)    NOT NULL                COMMENT 'Название альбома',
  `year`        INT             DEFAULT 0               COMMENT 'Год альбома',
  `time`        VARCHAR(45)     DEFAULT ""              COMMENT 'Длительность альбома',
  `rating`      INT             DEFAULT 0               COMMENT 'Рейтинг альбома',
  `description` BLOB(10000)                             COMMENT 'Описание альбома',
  `image_link`  VARCHAR(255)    DEFAULT ""              COMMENT 'Ссылка изображения',
  PRIMARY KEY (`id`),
  INDEX `fk_album_id_artist_idx` (`id_artist` ASC),
  CONSTRAINT `fk_album_id_artist`
    FOREIGN KEY (`id_artist`)
    REFERENCES `musicbook`.`artist` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Таблица альбомов';

CREATE TABLE `musicbook`.`song` (
  `id`          INT             NOT NULL AUTO_INCREMENT COMMENT 'Id песни',
  `id_album`    INT             NOT NULL DEFAULT 1      COMMENT 'Id альбома',
  `name`        VARCHAR(255)    NOT NULL                COMMENT 'Название песни',
  `track`       INT             DEFAULT 0               COMMENT 'Номер трека песни',
  `lyric`       BLOB(10000)                             COMMENT 'Текст песни',
  `time`        VARCHAR(45)     DEFAULT ""              COMMENT 'Длительность песни',
  `rating`      INT             DEFAULT 0               COMMENT 'Рейтинг песни',
  `description` BLOB(10000)                             COMMENT 'Описание песни',
  `image_link`  VARCHAR(255)    DEFAULT ""              COMMENT 'Ссылка изображения',
  PRIMARY KEY (`id`),
  INDEX `fk_song_id_album_idx` (`id_album` ASC),
  CONSTRAINT `fk_song_id_album`
    FOREIGN KEY (`id_album`)
    REFERENCES `musicbook`.`album` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Таблица песен';

CREATE TABLE `musicbook`.`genre` (
  `id`          INT             NOT NULL AUTO_INCREMENT COMMENT 'id жанра',
  `name`        VARCHAR(255)    NOT NULL                COMMENT 'Название жанра',
  `description` BLOB(10000)                             COMMENT 'Описание жанра',
  `image_link`  VARCHAR(255)    DEFAULT ""              COMMENT 'Ссылка изображения',
  PRIMARY KEY (`id`))
COMMENT = 'Таблица жанров';

CREATE TABLE `musicbook`.`instrument` (
  `id`          INT             NOT NULL AUTO_INCREMENT COMMENT 'id инструмента',
  `name`        VARCHAR(255)    NOT NULL                COMMENT 'Название инструмента',
  `description` BLOB(10000)                             COMMENT 'Описание инструмента',
  `image_link`  VARCHAR(255)    DEFAULT ""              COMMENT 'Ссылка изображения',
  PRIMARY KEY (`id`))
COMMENT = 'Таблица инструментов';

CREATE TABLE `musicbook`.`musician` (
  `id`          INT             NOT NULL AUTO_INCREMENT COMMENT 'id музыканта',
  `name`        VARCHAR(255)    NOT NULL                COMMENT 'Название музыканта',
  `date_of_birth` VARCHAR(45)   DEFAULT ""              COMMENT 'Дата рождения музыканта',
  `date_of_death` VARCHAR(45)   DEFAULT ""              COMMENT 'Дата смерти музыканта',
  `country`     VARCHAR(255)    DEFAULT ""              COMMENT 'Страна музыканта',
  `rating`      INT             DEFAULT 0               COMMENT 'Рейтинг музыканта',
  `description` BLOB(10000)                             COMMENT 'Описание музыканта',
  `image_link`  VARCHAR(255)    DEFAULT ""              COMMENT 'Ссылка изображения',
  PRIMARY KEY (`id`))
COMMENT = 'Таблица музыканта';

CREATE TABLE `musicbook`.`artist_reference` (
  `id`          INT             NOT NULL AUTO_INCREMENT COMMENT 'Id ссылки',
  `id_artist`   INT             NOT NULL DEFAULT 1      COMMENT 'Id артиста',
  `name`        VARCHAR(255)    DEFAULT ""              COMMENT 'Название ссылки',
  `reference`   VARCHAR(255)    DEFAULT ""              COMMENT 'Ссылка',
  PRIMARY KEY (`id`),
  INDEX `fk_artist_reference_id_artist_idx` (`id_artist` ASC),
  CONSTRAINT `fk_artist_reference_id_artist`
    FOREIGN KEY (`id_artist`)
    REFERENCES `musicbook`.`artist` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Таблица ссылок';

CREATE TABLE `musicbook`.`artist_genre` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_genre`    INT             NOT NULL DEFAULT 1,  
  `id_artist`   INT             NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_artist_genre`(`id_genre`, `id_artist`), 
  INDEX `fk_artist_genre_id_genre_idx` (`id_genre` ASC),
  CONSTRAINT `fk_artist_genre_id_genre`
    FOREIGN KEY (`id_genre`)
    REFERENCES `musicbook`.`genre` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_artist_genre_id_artist_idx` (`id_artist` ASC),
  CONSTRAINT `fk_artist_genre_id_artist`
    FOREIGN KEY (`id_artist`)
    REFERENCES `musicbook`.`artist` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

CREATE TABLE `musicbook`.`album_genre` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_genre`    INT             NOT NULL DEFAULT 1,  
  `id_album`    INT             NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_album_genre`(`id_genre`, `id_album`),
  INDEX `fk_album_genre_id_genre_idx` (`id_genre` ASC),
  CONSTRAINT `fk_album_genre_id_genre`
    FOREIGN KEY (`id_genre`)
    REFERENCES `musicbook`.`genre` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_album_genre_id_album_idx` (`id_album` ASC),
  CONSTRAINT `fk_album_genre_id_album`
    FOREIGN KEY (`id_album`)
    REFERENCES `musicbook`.`album` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

CREATE TABLE `musicbook`.`song_genre` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_genre`    INT             NOT NULL DEFAULT 1,  
  `id_song`     INT             NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_song_genre`(`id_genre`, `id_song`),
  INDEX `fk_song_genre_id_genre_idx` (`id_genre` ASC),
  CONSTRAINT `fk_song_genre_id_genre`
    FOREIGN KEY (`id_genre`)
    REFERENCES `musicbook`.`genre` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_song_genre_id_song_idx` (`id_song` ASC),
  CONSTRAINT `fk_song_genre_id_song`
    FOREIGN KEY (`id_song`)
    REFERENCES `musicbook`.`song` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

CREATE TABLE `musicbook`.`musician_genre` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_genre`    INT             NOT NULL DEFAULT 1,  
  `id_musician` INT             NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_musician_genre`(`id_genre`, `id_musician`),
  INDEX `fk_musician_genre_id_genre_idx` (`id_genre` ASC),
  CONSTRAINT `fk_musician_genre_id_genre`
    FOREIGN KEY (`id_genre`)
    REFERENCES `musicbook`.`genre` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_musician_genre_id_musician_idx` (`id_musician` ASC),
  CONSTRAINT `fk_musician_genre_id_musician`
    FOREIGN KEY (`id_musician`)
    REFERENCES `musicbook`.`musician` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

CREATE TABLE `musicbook`.`musician_group` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_musician` INT             NOT NULL,  
  `id_artist`   INT             NOT NULL,
  `start_date`  VARCHAR(45)     DEFAULT ""      COMMENT 'Дата прихода музыканта в группу',
  `end_date`    VARCHAR(45)     DEFAULT ""      COMMENT 'Дата ухода музыканта из группы',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_musician_group`(`id_artist`, `id_musician`),
  INDEX `fk_musician_group_id_musician_idx` (`id_musician` ASC),
  CONSTRAINT `fk_musician_group_id_musician`
    FOREIGN KEY (`id_musician`)
    REFERENCES `musicbook`.`musician` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_musician_group_id_artist_idx` (`id_artist` ASC),
  CONSTRAINT `fk_musician_group_id_artist`
    FOREIGN KEY (`id_artist`)
    REFERENCES `musicbook`.`artist` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

CREATE TABLE `musicbook`.`musician_album` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_musician` INT             NOT NULL,  
  `id_album`    INT             NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_musician_album`(`id_musician`, `id_album`),
  INDEX `fk_musician_album_id_musician_idx` (`id_musician` ASC),
  CONSTRAINT `fk_musician_album_id_musician`
    FOREIGN KEY (`id_musician`)
    REFERENCES `musicbook`.`musician` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_musician_album_id_album_idx` (`id_album` ASC),
  CONSTRAINT `fk_musician_album_id_album`
    FOREIGN KEY (`id_album`)
    REFERENCES `musicbook`.`album` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

CREATE TABLE `musicbook`.`musician_instrument` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_musician` INT             NOT NULL,  
  `id_instrument` INT           NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_musician_instrument`(`id_musician`, `id_instrument`),
  INDEX `fk_musician_instrument_id_musician_idx` (`id_musician` ASC),
  CONSTRAINT `fk_musician_instrument_id_musician`
    FOREIGN KEY (`id_musician`)
    REFERENCES `musicbook`.`musician` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_musician_instrument_id_instrument_idx` (`id_instrument` ASC),
  CONSTRAINT `fk_musician_instrument_id_instrument`
    FOREIGN KEY (`id_instrument`)
    REFERENCES `musicbook`.`instrument` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

CREATE TABLE `musicbook`.`musician_song` (
  `id`          INT             NOT NULL AUTO_INCREMENT,
  `id_musician` INT             NOT NULL,  
  `id_song`     INT             NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_musician_song`(`id_musician`, `id_song`),
  INDEX `fk_musician_song_id_musician_idx` (`id_musician` ASC),
  CONSTRAINT `fk_musician_song_id_musician`
    FOREIGN KEY (`id_musician`)
    REFERENCES `musicbook`.`musician` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION,
  INDEX `fk_musician_song_id_song_idx` (`id_song` ASC),
  CONSTRAINT `fk_musician_song_id_song`
    FOREIGN KEY (`id_song`)
    REFERENCES `musicbook`.`song` (`id`)
    ON DELETE CASCADE ON UPDATE NO ACTION)
COMMENT = 'Joining table';

