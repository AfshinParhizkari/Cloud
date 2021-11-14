DROP TABLE IF EXISTS cart;
CREATE TABLE cart (
      cartpk INT AUTO_INCREMENT NOT NULL,
      customerfk int(6) NOT NULL,
      productfk int(11) NOT NULL,
      quantity int(4) NOT NULL,
      price decimal(10,2) NOT NULL,
      adddate datetime NOT NULL DEFAULT current_timestamp()
);
/*
CREATE USER guest PASSWORD '123' ADMIN ;
GRANT ALTER ANY SCHEMA TO guest;
*/
