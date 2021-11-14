CREATE TABLE `person` (
                          `personpk` int(6) NOT NULL AUTO_INCREMENT,
                          `persontypeid` int(3) NOT NULL DEFAULT 1 COMMENT 'نوع مشتری حقیقی - حقوقی - خارجی',
                          `typedetailid` int(3) DEFAULT NULL,
                          `nationalkey` varchar(11) COLLATE utf8mb4_persian_ci NOT NULL COMMENT 'کد یا شناسه ملی',
                          `booknumber` int(9) DEFAULT NULL COMMENT 'شماره شناسنامه / ثبت',
                          `bookserial` varchar(15) COLLATE utf8mb4_persian_ci DEFAULT NULL COMMENT 'سریال شناسنامه',
                          `bookserie` varchar(11) COLLATE utf8mb4_persian_ci DEFAULT NULL COMMENT 'سری شناسنامه',
                          `passportno` varchar(20) COLLATE utf8mb4_persian_ci DEFAULT NULL COMMENT 'شماره پاسپورت',
                          `lastname` varchar(90) COLLATE utf8mb4_persian_ci NOT NULL COMMENT 'نام خانوادگی / نام شرکت',
                          `firstname` varchar(45) COLLATE utf8mb4_persian_ci DEFAULT NULL,
                          `countryid` int(3) NOT NULL DEFAULT 98 COMMENT 'کلید کشور',
                          `cityid` int(3) DEFAULT NULL COMMENT 'کلید شهر',
                          `birthdate` date DEFAULT NULL COMMENT 'تاریخ تولد یا تاسیس',
                          `changerdate` datetime DEFAULT current_timestamp(),
                          PRIMARY KEY (`personpk`),
                          UNIQUE KEY `nationalkey_UNIQUE` (`nationalkey`)
) ENGINE=InnoDB AUTO_INCREMENT=466 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_persian_ci;


CREATE TABLE `contact` (
                           `contactpk` binary(16) NOT NULL,
                           `personfk` int(6) NOT NULL,
                           `ownernationalkey` varchar(11) COLLATE utf8mb4_persian_ci DEFAULT NULL COMMENT 'کد دارنده تماس',
                           `contacttypeid` int(3) NOT NULL COMMENT 'نوع تماس- آدرس تلفن',
                           `countryid` int(3) DEFAULT 98 COMMENT 'کد کشور +98',
                           `provinceid` int(3) DEFAULT NULL,
                           `cityid` int(3) DEFAULT NULL,
                           `contactnumber` varchar(10) COLLATE utf8mb4_persian_ci DEFAULT NULL COMMENT 'شماره تلفن یا کد پستی',
                           `email` varchar(100) COLLATE utf8mb4_persian_ci DEFAULT NULL,
                           `contactdesc` varchar(100) COLLATE utf8mb4_persian_ci DEFAULT NULL COMMENT 'آدرس پستی',
                           `location` point DEFAULT NULL,
                           `contactstatus` tinyint(1) NOT NULL COMMENT 'وضعیت تماس',
                           `changerdate` datetime DEFAULT current_timestamp(),
                           PRIMARY KEY (`contactpk`),
                           KEY `fk_contact_1` (`personfk`),
                           CONSTRAINT `fk_contact_1` FOREIGN KEY (`personfk`) REFERENCES `person` (`personpk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_persian_ci;
