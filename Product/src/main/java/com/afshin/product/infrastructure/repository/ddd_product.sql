-- MySQL dump 10.19  Distrib 10.3.31-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: ddd_product
-- ------------------------------------------------------
-- Server version	10.3.31-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `categorypk` int(11) NOT NULL AUTO_INCREMENT,
  `categoryname` varchar(4000) DEFAULT NULL,
  `categorydescription` mediumtext DEFAULT NULL,
  PRIMARY KEY (`categorypk`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Bomber','bombing1'),(2,'Classic Cars','Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale and include numerous limited edition and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.'),(3,'Sample','just update order database modely'),(4,'Motorcycles','Our motorcycles are state of the art replicas of classic as well as contemporary motorcycle legends such as Harley Davidson, Ducati and Vespa. Models contain stunning details such as official logos, rotating wheels, working kickstand, front suspension, gear-shift lever, footbrake lever, and drive chain. Materials used include diecast and plastic. The models range in size from 1:10 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. All models come fully assembled and ready for display in the home or office. Most include a certificate of authenticity.'),(5,'Planes','Unique, diecast airplane and helicopter replicas suitable for collections, as well as home, office or classroom decorations. Models contain stunning details such as official logos and insignias, rotating jet engines and propellers, retractable wheels, and so on. Most come fully assembled and with a certificate of authenticity from their manufacturers.'),(6,'Ships','The perfect holiday or anniversary gift for executives, clients, friends, and family. These handcrafted model ships are unique, stunning works of art that will be treasured for generations! They come fully assembled and ready for display in the home or office. We guarantee the highest quality, and best value.'),(7,'Trains','Model trains are a rewarding hobby for enthusiasts of all ages. Whether you\'re looking for collectible wooden trains, electric streetcars or locomotives, you\'ll find a number of great choices for any budget within this category. The interactive aspect of trains makes toy trains perfect for young children. The wooden train sets are ideal for children under the age of 5.'),(8,'Trucks and Buses','The Truck and Bus models are realistic replicas of buses and specialized trucks produced from the early 1920s to present. The models range in size from 1:12 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. Materials used include tin, diecast and plastic. All models include a certificate of authenticity from their manufacturers and are a perfect ornament for the home and office.'),(9,'Vintage Cars','Our Vintage Car models realistically portray automobiles produced from the early 1900s through the 1940s. Materials used include Bakelite, diecast, plastic and wood. Most of the replicas are in the 1:18 and 1:24 scale sizes, which provide the optimum in detail and accuracy. Prices range from $30.00 up to $180.00 for some special limited edition replicas. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'1969 Harley Davidson Ultimate Chopper',4,'Min Lin Diecast1',7933,'Liter',48.81,95.70,1,'This replica features working kickstand, front suspension, gear-shift lever, footbrake lever, drive chain, wheels and steering. All parts are particularly delicate due to their precise scale and require special care and attention.'),(2,'1952 Alpine Renault 1300',2,'Classic Metal Creations',7305,'Liter',98.58,214.30,1,'Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.'),(3,'1996 Moto Guzzi 1100i',4,'Highway 66 Mini Classics',6625,'Liter',68.99,118.94,0,'Official Moto Guzzi logos and insignias, saddle bags located on side of motorcycle, detailed engine, working steering, working suspension, two leather seats, luggage rack, dual exhaust pipes, small saddle bag located on handle bars, two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand, diecast metal with plastic parts and baked enamel finish.'),(4,'2003 Harley-Davidson Eagle Drag Bike',4,'Red Start Diecast',5582,'Liter',91.02,193.66,1,'Model features, official Harley Davidson logos and insignias, detachable rear wheelie bar, heavy diecast metal with resin parts, authentic multi-color tampo-printed graphics, separate engine drive belts, free-turning front fork, rotating tires and rear racing slick, certificate of authenticity, detailed engine, display stand'),(5,'1972 Alfa Romeo GTA',2,'Motor City Art Classics',3252,'Liter',85.68,136.00,0,'Features include: Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.'),(6,'1962 LanciaA Delta 16V',2,'Second Gear Diecast',6791,'Liter',103.42,147.74,1,'Features include: Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.'),(7,'1968 Ford Mustang',2,'Autoart Studio Design',68,'Count',95.34,194.57,1,'Hood, doors and trunk all open to reveal highly detailed interior features. Steering wheel actually turns the front wheels. Color dark green.'),(8,'2001 Ferrari Enzo',2,'Second Gear Diecast',3619,'Count',95.59,207.80,1,'Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.'),(9,'1958 Setra Bus',8,'Welly Diecast Productions',1579,'Count',77.90,136.67,1,'Model features 30 windows, skylights & glare resistant glass, working steering system, original logos'),(10,'2002 Suzuki XREO',4,'Unimax Art Galleries',9997,'Count',66.27,150.62,0,'Official logos and insignias, saddle bags located on side of motorcycle, detailed engine, working steering, working suspension, two leather seats, luggage rack, dual exhaust pipes, small saddle bag located on handle bars, two-tone paint with chrome accents, superior die-cast detail , rotating wheels , working kick stand, diecast metal with plastic parts and baked enamel finish.'),(11,'1969 Corvair Monza',2,'Welly Diecast Productions',6906,'Count',89.14,151.08,1,'1:18 scale die-cast about 10\" long doors open, hood opens, trunk opens and wheels roll'),(12,'1968 Dodge Charger',2,'Welly Diecast Productions',9123,'Count',75.16,117.44,0,'1:12 scale model of a 1968 Dodge Charger. Hood, doors and trunk all open to reveal highly detailed interior features. Steering wheel actually turns the front wheels. Color black'),(13,'1969 Ford Falcon',2,'Second Gear Diecast',1049,'Count',83.05,173.02,1,'Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.'),(14,'1970 Plymouth Hemi Cuda',2,'Studio M Art Models',5663,'Count',31.92,79.80,0,'Very detailed 1970 Plymouth Cuda model in 1:12 scale. The Cuda is generally accepted as one of the fastest original muscle cars from the 1970s. This model is a reproduction of one of the orginal 652 cars built in 1970. Red color.'),(15,'1957 Chevy Pickup',8,'Exoto Designs',6125,'Count',55.70,118.50,1,'1:12 scale die-cast about 20\" long Hood opens, Rubber wheels'),(16,'1969 Dodge Charger',2,'Welly Diecast Productions',7323,'Count',58.73,115.16,1,'Detailed model of the 1969 Dodge Charger. This model includes finely detailed interior and exterior features. Painted in red and white.'),(17,'1940 Ford Pickup Truck',8,'Studio M Art Models',2613,'Count',58.33,116.67,0,'This model features soft rubber tires, working steering, rubber mud guards, authentic Ford logos, detailed undercarriage, opening doors and hood,  removable split rear gate, full size spare mounted in bed, detailed interior with opening glove box'),(18,'1993 Mazda RX-7',2,'Highway 66 Mini Classics',3975,'Count',83.51,141.54,0,'This model features, opening hood, opening doors, detailed engine, rear spoiler, opening trunk, working steering, tinted windows, baked enamel finish. Color red.'),(19,'1937 Lincoln Berline',9,'Motor City Art Classics',8693,'Count',60.62,102.74,1,'Features opening engine cover, doors, trunk, and fuel filler cap. Color black'),(20,'1936 Mercedes-Benz 500K Special Roadster',9,'Studio M Art Models',8635,'Count',24.26,53.91,0,'This 1:18 scale replica is constructed of heavy die-cast metal and has all the features of the original: working doors and rumble seat, independent spring suspension, detailed interior, working steering system, and a bifold hood that reveals an engine so accurate that it even includes the wiring. All this is topped off with a baked enamel finish. Color white.'),(21,'1965 Aston Martin DB5',2,'Classic Metal Creations',9042,'Count',65.96,124.44,1,'Die-cast model of the silver 1965 Aston Martin DB5 in silver. This model includes full wire wheels and doors that open with fully detailed passenger compartment. In 1:18 scale, this model measures approximately 10 inches/20 cm long.'),(22,'1980s Black Hawk Helicopter',5,'Red Start Diecast',5330,'Kgram',77.27,157.69,0,'1:18 scale replica of actual Army\'s UH-60L BLACK HAWK Helicopter. 100% hand-assembled. Features rotating rotor blades, propeller blades and rubber wheels.'),(23,'1917 Grand Touring Sedan',9,'Welly Diecast Productions',2724,'Kgram',86.70,170.00,1,'This 1:18 scale replica of the 1917 Grand Touring car has all the features you would expect from museum quality reproductions: all four doors and bi-fold hood opening, detailed engine and instrument panel, chrome-look trim, and tufted upholstery, all topped off with a factory baked-enamel finish.'),(24,'1948 Porsche 356-A Roadster',2,'Gearbox Collectibles',8826,'Kgram',53.90,77.00,1,'This precision die-cast replica features opening doors, superb detail and craftsmanship, working steering system, opening forward compartment, opening rear trunk with removable spare, 4 wheel independent spring suspension as well as factory baked enamel finish.'),(25,'1995 Honda Civic',2,'Min Lin Diecast',9772,'Kgram',93.89,142.25,0,'This model features, opening hood, opening doors, detailed engine, rear spoiler, opening trunk, working steering, tinted windows, baked enamel finish. Color yellow.'),(26,'1998 Chrysler Plymouth Prowler',2,'Gearbox Collectibles',4724,'Kgram',101.51,163.73,0,'Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.'),(27,'1911 Ford Town Car',9,'Motor City Art Classics',540,'Kgram',33.30,60.54,0,'Features opening hood, opening doors, opening trunk, wide white wall tires, front door arm rests, working steering system.'),(28,'1964 Mercedes Tour Bus',8,'Unimax Art Galleries',8258,'Kgram',74.86,122.73,1,'Exact replica. 100+ parts. working steering system, original logos'),(29,'1932 Model A Ford J-Coupe',9,'Autoart Studio Design',9354,'Kgram',58.48,127.13,0,'This model features grille-mounted chrome horn, lift-up louvered hood, fold-down rumble seat, working steering system, chrome-covered spare, opening doors, detailed and wired engine'),(30,'1926 Ford Fire Engine',8,'Carousel DieCast Legends',2018,'Kgram',24.92,60.77,1,'Gleaming red handsome appearance. Everything is here the fire hoses, ladder, axes, bells, lanterns, ready to fight any inferno.'),(31,'P-51-D Mustang',5,'Gearbox Collectibles',992,'Kgram',49.00,84.48,1,'Has retractable wheels and comes with a stand'),(32,'1936 Harley Davidson El Knucklehead',4,'Welly Diecast Productions',4357,'Kmeter',24.23,60.57,0,'Intricately detailed with chrome accents and trim, official die-struck logos and baked enamel finish.'),(33,'1928 Mercedes-Benz SSK',9,'Gearbox Collectibles',548,'Kmeter',72.56,168.75,0,'This 1:18 replica features grille-mounted chrome horn, lift-up louvered hood, fold-down rumble seat, working steering system, chrome-covered spare, opening doors, detailed and wired engine. Color black.'),(34,'1999 Indy 500 Monte Carlo SS',2,'Red Start Diecast',8164,'Kmeter',56.76,132.00,1,'Features include opening and closing doors. Color: Red'),(35,'1913 Ford Model T Speedster',9,'Carousel DieCast Legends',4189,'Kmeter',60.78,101.31,1,'This 250 part reproduction includes moving handbrakes, clutch, throttle and foot pedals, squeezable horn, detailed wired engine, removable water, gas, and oil cans, pivoting monocle windshield, all topped with a baked enamel red finish. Each replica comes with an Owners Title and Certificate of Authenticity. Color red.'),(36,'1934 Ford V8 Coupe',9,'Min Lin Diecast',5649,'Kmeter',34.35,62.46,1,'Chrome Trim, Chrome Grille, Opening Hood, Opening Doors, Opening Trunk, Detailed Engine, Working Steering System'),(37,'1999 Yamaha Speed Boat',6,'Min Lin Diecast',4259,'Kmeter',51.61,86.02,0,'Exact replica. Wood and Metal. Many extras including rigging, long boats, pilot house, anchors, etc. Comes with three masts, all square-rigged.');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-12  7:28:12
