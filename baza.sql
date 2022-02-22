/*
SQLyog Ultimate
MySQL - 10.4.21-MariaDB : Database - medicalis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`medicalis` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `medicalis`;

/*Table structure for table `analiza` */

CREATE TABLE `analiza` (
  `sifra_analize` bigint(20) NOT NULL,
  `vrsta_analize` varchar(30) DEFAULT NULL,
  `vrsta_uzorka` varchar(20) DEFAULT NULL,
  `sifra_uputa` bigint(20) NOT NULL,
  PRIMARY KEY (`sifra_analize`,`sifra_uputa`),
  KEY `sifra_uputa` (`sifra_uputa`),
  CONSTRAINT `analiza_ibfk_1` FOREIGN KEY (`sifra_uputa`) REFERENCES `uput` (`sifra_uputa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `analiza` */

insert  into `analiza`(`sifra_analize`,`vrsta_analize`,`vrsta_uzorka`,`sifra_uputa`) values (1,'Bakterioloska','Bris grla',1);
insert  into `analiza`(`sifra_analize`,`vrsta_analize`,`vrsta_uzorka`,`sifra_uputa`) values (2,'Mikoloska','Bris grla',1);
insert  into `analiza`(`sifra_analize`,`vrsta_analize`,`vrsta_uzorka`,`sifra_uputa`) values (3,'Bakterioloska','Sputum',2);
insert  into `analiza`(`sifra_analize`,`vrsta_analize`,`vrsta_uzorka`,`sifra_uputa`) values (4,'Bakterioloska','Bris grla',2);
insert  into `analiza`(`sifra_analize`,`vrsta_analize`,`vrsta_uzorka`,`sifra_uputa`) values (5,'Bakterioloska','Urin',3);

/*Table structure for table `kartonpacijenta` */

CREATE TABLE `kartonpacijenta` (
  `jmbg` varchar(13) NOT NULL,
  `lbo` varchar(11) DEFAULT NULL,
  `ime` varchar(20) DEFAULT NULL,
  `prezime` varchar(20) DEFAULT NULL,
  `datumrodjenja` date DEFAULT NULL,
  `pol` varchar(20) DEFAULT NULL,
  `adresa` varchar(50) DEFAULT NULL,
  `kontakt_telefon` varchar(20) DEFAULT NULL,
  `krvna_grupa` varchar(4) DEFAULT NULL,
  `hronicne_dijagnoze` varchar(255) DEFAULT NULL,
  `lekar` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`jmbg`),
  KEY `lekar` (`lekar`),
  CONSTRAINT `kartonpacijenta_ibfk_1` FOREIGN KEY (`lekar`) REFERENCES `lekar` (`korisnicko_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `kartonpacijenta` */

insert  into `kartonpacijenta`(`jmbg`,`lbo`,`ime`,`prezime`,`datumrodjenja`,`pol`,`adresa`,`kontakt_telefon`,`krvna_grupa`,`hronicne_dijagnoze`,`lekar`) values ('0101000111222','12345678910','Ana','Ancic','2000-01-01','Zenski','Nemanjina 32','0635556663','A+','Nema','majpav');
insert  into `kartonpacijenta`(`jmbg`,`lbo`,`ime`,`prezime`,`datumrodjenja`,`pol`,`adresa`,`kontakt_telefon`,`krvna_grupa`,`hronicne_dijagnoze`,`lekar`) values ('0101977333222','11112225568','Bojan','Bojic','1977-01-01','Nije odredjeno','Kraljice Natalije 8','0638595666','A+','Nema','pajicp');
insert  into `kartonpacijenta`(`jmbg`,`lbo`,`ime`,`prezime`,`datumrodjenja`,`pol`,`adresa`,`kontakt_telefon`,`krvna_grupa`,`hronicne_dijagnoze`,`lekar`) values ('0306987222111','11122244556','Nikola','NIkolic','1987-06-03','Muski','','','A+','','majpav');
insert  into `kartonpacijenta`(`jmbg`,`lbo`,`ime`,`prezime`,`datumrodjenja`,`pol`,`adresa`,`kontakt_telefon`,`krvna_grupa`,`hronicne_dijagnoze`,`lekar`) values ('1111111111111','15451','Nemanja','Nemanjic','1999-03-27','Muski','Bul oslobodjenja 10','0638595666','A+','Nema','majpav');
insert  into `kartonpacijenta`(`jmbg`,`lbo`,`ime`,`prezime`,`datumrodjenja`,`pol`,`adresa`,`kontakt_telefon`,`krvna_grupa`,`hronicne_dijagnoze`,`lekar`) values ('2703999715077','12398765413','Maja','Pavlovic','1999-03-27','Muski','Bulevar oslobodjenja 10','0637888564','A+','Hronican umor','majpav');

/*Table structure for table `laborant` */

CREATE TABLE `laborant` (
  `korisnicko_ime` varchar(15) NOT NULL,
  `korisnicka_sifra` varchar(15) DEFAULT NULL,
  `ime` varchar(20) DEFAULT NULL,
  `prezime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`korisnicko_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `laborant` */

insert  into `laborant`(`korisnicko_ime`,`korisnicka_sifra`,`ime`,`prezime`) values ('majpav','12345','Maja','Pavlovic');

/*Table structure for table `lekar` */

CREATE TABLE `lekar` (
  `korisnicko_ime` varchar(15) NOT NULL,
  `korisnicka_sifra` varchar(15) DEFAULT NULL,
  `ime` varchar(20) DEFAULT NULL,
  `prezime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`korisnicko_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `lekar` */

insert  into `lekar`(`korisnicko_ime`,`korisnicka_sifra`,`ime`,`prezime`) values ('majpav','12345','Maja','Pavlovic');
insert  into `lekar`(`korisnicko_ime`,`korisnicka_sifra`,`ime`,`prezime`) values ('pajicp','12345','Pavle','Pajic');

/*Table structure for table `rezultat` */

CREATE TABLE `rezultat` (
  `sifra_rezultata` bigint(20) NOT NULL,
  `rezultat_analize` varchar(50) NOT NULL,
  `datum_izdavanja` date DEFAULT NULL,
  `laborant` varchar(15) DEFAULT NULL,
  `sifra_analize` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sifra_rezultata`),
  KEY `laborant` (`laborant`),
  KEY `sifra_analize` (`sifra_analize`),
  CONSTRAINT `rezultat_ibfk_4` FOREIGN KEY (`laborant`) REFERENCES `laborant` (`korisnicko_ime`),
  CONSTRAINT `rezultat_ibfk_6` FOREIGN KEY (`sifra_analize`) REFERENCES `analiza` (`sifra_analize`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `rezultat` */

insert  into `rezultat`(`sifra_rezultata`,`rezultat_analize`,`datum_izdavanja`,`laborant`,`sifra_analize`) values (1,'Izolovana je normalna flora','2022-02-19','majpav',1);

/*Table structure for table `uput` */

CREATE TABLE `uput` (
  `sifra_uputa` bigint(20) NOT NULL,
  `datum_uputa` date DEFAULT NULL,
  `uputna_dijagnoza` varchar(50) DEFAULT NULL,
  `sifra_lekara` varchar(15) DEFAULT NULL,
  `jmbg` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`sifra_uputa`),
  KEY `sifra_lekara` (`sifra_lekara`),
  KEY `jmbg` (`jmbg`),
  CONSTRAINT `uput_ibfk_3` FOREIGN KEY (`sifra_lekara`) REFERENCES `lekar` (`korisnicko_ime`),
  CONSTRAINT `uput_ibfk_4` FOREIGN KEY (`jmbg`) REFERENCES `kartonpacijenta` (`jmbg`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `uput` */

insert  into `uput`(`sifra_uputa`,`datum_uputa`,`uputna_dijagnoza`,`sifra_lekara`,`jmbg`) values (1,'2022-02-18','R50','majpav','1111111111111');
insert  into `uput`(`sifra_uputa`,`datum_uputa`,`uputna_dijagnoza`,`sifra_lekara`,`jmbg`) values (2,'2022-02-19','Upala bronhija','pajicp','1111111111111');
insert  into `uput`(`sifra_uputa`,`datum_uputa`,`uputna_dijagnoza`,`sifra_lekara`,`jmbg`) values (3,'2022-02-19','R50','pajicp','0101977333222');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
