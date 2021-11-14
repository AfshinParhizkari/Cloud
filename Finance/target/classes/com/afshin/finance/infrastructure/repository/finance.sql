CREATE TABLE `orders` (
                          `orderpk` int(11) NOT NULL AUTO_INCREMENT,
                          `customerfk` int(6) NOT NULL,
                          `orderdate` datetime NOT NULL DEFAULT current_timestamp(),
                          `shippdate` datetime DEFAULT NULL,
                          `state` varchar(15) NOT NULL,
                          `note` varchar(200) DEFAULT NULL,
                          PRIMARY KEY (`orderpk`)
) ENGINE=InnoDB AUTO_INCREMENT=10466 DEFAULT CHARSET=latin1;

CREATE TABLE `orderitem` (
     `orderfk` int(11) NOT NULL,
     `productfk` int(11) NOT NULL,
     `quantity` int(4) NOT NULL,
     `price` decimal(10,2) NOT NULL,
     PRIMARY KEY (`orderfk`,`productfk`),
     CONSTRAINT `fk_orderitem_1` FOREIGN KEY (`orderfk`) REFERENCES `order` (`orderpk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `payment` (
                           `paymentpk` int(11) NOT NULL AUTO_INCREMENT,
                           `orderfk` int(11) NOT NULL,
                           `paymentdate` datetime NOT NULL DEFAULT current_timestamp(),
                           `amount` decimal(10,2) NOT NULL,
                           `transaction` varchar(50) DEFAULT NULL,
                           PRIMARY KEY (`paymentpk`),
                           KEY `fk_payment_1` (`orderfk`),
                           CONSTRAINT `fk_payment_1` FOREIGN KEY (`orderfk`) REFERENCES `orders` (`orderpk`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=340 DEFAULT CHARSET=latin1;

