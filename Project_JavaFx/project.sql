CREATE TABLE `account` (
  `accountID` int(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` int(11) NOT NULL,
  `email` varchar(100) NOT NULL
);


CREATE TABLE `brand` (
  `brandID` int(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) NOT NULL,
  `status` int(1) NOT NULL
);

CREATE TABLE `color` (
  `colorID` int(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  `color` varchar(100) NOT NULL
);

CREATE TABLE `car` (
  `carID` int(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  `sku` varchar(5) NOT NULL,
  `carName` varchar(255) NOT NULL,
  `yearOfManufacture` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `seat` int(11) NOT NULL,
  `fuelUsed` varchar(50) NOT NULL,
  `gear` varchar(50) NOT NULL,
  `status` int(1) NOT NULL,
  `brandID` int(11) NOT NULL,
  `categoryID` int(11) NOT NULL,
  `colorID` INT NOT NULL
);

CREATE TABLE `category` (
  `categoryID` int(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(255) NOT NULL,
  `status` int(1) NOT NULL
);

CREATE TABLE `contract` (
  `contractID` int(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  `customerID` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `dateOfSale` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `deposits` int(11) NOT NULL,
  `productReceiptDate` date NOT NULL,
  `accountant` varchar(50) NOT NULL,
  `CarID` int(11) NOT NULL,
  `note` varchar(255) DEFAULT NULL
);

CREATE TABLE `customer` (
  `customerID` int(11) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
);

--
-- Đang đổ dữ liệu cho bảng `customer`

ALTER TABLE `car`
  ADD CONSTRAINT `FK_Car_Brand` FOREIGN KEY (`brandID`) REFERENCES `brand` (`brandID`),
  ADD CONSTRAINT `PK_Car_Category` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`),
  ADD CONSTRAINT `FK_Car_Color` FOREIGN KEY (`colorID`) REFERENCES `Color` (`colorID`);

ALTER TABLE `contract`
  ADD CONSTRAINT `PK_Contract_Car` FOREIGN KEY (`CarID`) REFERENCES `car` (`carID`),
  ADD CONSTRAINT `PK_Contract_Customer` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`);

INSERT INTO `brand` ( `brand`, `status`) VALUES
( 'Toyota', 0),
('Hyundai', 0);

INSERT INTO `category` ( `categoryName`, `status`) VALUES
( 'Xe Con', 1),
( 'Bán Tải', 0),
( 'Xe Hơi', 1);


INSERT INTO `color` (`color`) VALUES
( 'Xanh'),
( 'Trắng'),
( 'Vàng'),
( 'Đen');


INSERT INTO `account` ( `userName`, `password`, `name`, `phone`, `email`) VALUES
( 'admin', 'abcdefghik', 'Vũ Toàn Phong', 915021999, 'phongvt@gmail.com'),
( 'admin1', 'asdfghjkl', 'Nguyễn Thị Xuân', 326585858, 'xuannguyen@gmail.com');


INSERT INTO `customer` ( `customerName`, `phone`, `address`, `email`) VALUES
( 'Cao Kiên Trung', '989565656', 'Hà Nội', 'trungkc@gmail.com'),
( 'Vũ Thiên Hương', '985113113', 'Hòa Bình', 'huong@gami.com'),
( 'Tô Tiến Tài', '326749515', 'Nam Định', 'hiep@gmail.com');
