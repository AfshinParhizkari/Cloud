CREATE TABLE `product` (
                           `productpk` int(11) NOT NULL AUTO_INCREMENT,
                           `productname` varchar(70) NOT NULL,
                           `categoryfk` int(11) NOT NULL,
                           `vendor` varchar(50) DEFAULT NULL,
                           `quantity` smallint(6) DEFAULT NULL,
                           `unit` varchar(20) DEFAULT NULL,
                           `buyprice` decimal(10,2) DEFAULT NULL,
                           `saleprice` decimal(10,2) DEFAULT NULL,
                           `active` tinyint(4) DEFAULT NULL,
                           `description` text DEFAULT NULL,
                           PRIMARY KEY (`productpk`),
                           KEY `fk_product_1` (`categoryfk`),
                           CONSTRAINT `fk_product_1` FOREIGN KEY (`categoryfk`) REFERENCES `category` (`categorypk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

CREATE TABLE `category` (
                            `categorypk` int(11) NOT NULL AUTO_INCREMENT,
                            `categoryname` varchar(4000) DEFAULT NULL,
                            `categorydescription` mediumtext DEFAULT NULL,
                            PRIMARY KEY (`categorypk`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
