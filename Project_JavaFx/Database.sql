CREATE TABLE `account` (
  `accountID` int(11) PRIMARY KEY AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL
);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brand`
--

CREATE TABLE `brand` (
  `brandID` int(11) PRIMARY KEY AUTO_INCREMENT,
  `brand` varchar(255) NOT NULL,
  `status` int(1) NOT NULL
);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `categoryID` int(11) PRIMARY KEY AUTO_INCREMENT,
  `categoryName` varchar(255) NOT NULL,
  `status` int(1) NOT NULL
);

-- --------------------------------------------------------
--
-- Cấu trúc bảng cho bảng `car`
--

CREATE TABLE `car` (
  `carID` int(11) PRIMARY KEY AUTO_INCREMENT,
  `sku` varchar(17) NOT NULL,
  `carName` varchar(255) NOT NULL,
  `yearOfManufacture` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `seat` int(11) NOT NULL,
  `fuelUsed` varchar(50) NOT NULL,
  `gear` varchar(50) NOT NULL,
  `status` int(1) NOT NULL,
  `brandID` int(11) NOT NULL,
  `categoryID` int(11) NOT NULL,
  `colorID` int(11) NOT NULL
);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `color`
--

CREATE TABLE `color` (
  `colorID` int(11) PRIMARY KEY AUTO_INCREMENT,
  `color` varchar(100) NOT NULL
);
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contract`
--

CREATE TABLE `contract` (
  `contractID` int(11) PRIMARY KEY AUTO_INCREMENT,
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

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `customerID` int(11) PRIMARY KEY AUTO_INCREMENT,
  `customerName` varchar(255) NOT NULL,
  `CMND` varchar(12) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
);

--

--
-- Các ràng buộc cho bảng `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `FK_Car_Brand` FOREIGN KEY (`brandID`) REFERENCES `brand` (`brandID`),
  ADD CONSTRAINT `FK_Car_Color` FOREIGN KEY (`colorID`) REFERENCES `color` (`colorID`),
  ADD CONSTRAINT `FK_Car_Category` FOREIGN KEY (`categoryID`) REFERENCES `category` (`categoryID`);

--
-- Các ràng buộc cho bảng `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `FK_Contract_Car` FOREIGN KEY (`CarID`) REFERENCES `car` (`carID`),
  ADD CONSTRAINT `FK_Contract_Customer` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`);


INSERT INTO `account` ( `userName`, `password`) VALUES
( 'Admin', '25d55ad283aa40af464c76d713c7ad');

INSERT INTO `brand` ( `brand`, `status`) VALUES
('Toyota', 1),
('Hyundai', 1),
( 'Audi', 1),
( 'Honda', 1),
('Mercedes', 0);

INSERT INTO `color` ( `color`) VALUES
( 'Đỏ'),
( 'Xanh'),
( 'Vàng'),
( 'Đen'),
( 'Trắng');

INSERT INTO `category` ( `categoryName`, `status`) VALUES
( 'Xe Con', 1),
( 'Bán Tải', 1);

INSERT INTO `customer` ( `customerName`, `CMND`, `phone`, `address`, `email`) VALUES
( 'Vũ Văn Minh', '563548591525', '1234567892', 'Hà Nội', 'minh@gmail.com'),
( 'Hoàng Trung Hòa', '123456789121', '0989585858', 'Hà Nội', 'ha2@gmail.com'),
( 'Nguyễn Ngọc Ánh', '123456789123', '0915464646', 'Hà Nam', 'anh@gmail.com');


INSERT INTO `car` (`sku`, `carName`, `yearOfManufacture`, `price`, `seat`, `fuelUsed`, `gear`, `status`, `brandID`, `categoryID`, `colorID`) VALUES
('OTO01', 'Camry', 2020, 123, 5, 'Xăng', 'Tự động', 1, 1, 1, 4),
('OTO02', 'Elantra', 2019, 456, 5, 'Xăng', 'Ly hợp kép', 1, 2, 2, 3),
('OTO03', 'Rolls Royce', 2018, 789, 5, 'Diesel', 'Tự động vô cấp', 1, 3, 1, 4),
('OTO04', 'Rafael', 2018, 456, 5, 'Xăng', 'Tự động', 1, 4, 1, 2);


INSERT INTO `contract` (`customerID`, `price`, `dateOfSale`, `status`, `deposits`, `productReceiptDate`, `accountant`, `CarID`, `note`) VALUES
( 1, 456, '2020-08-21 08:49:48', 0, 123, '2020-08-21', 'Lê Thị Huyền', 1, 'ghhfg'),
( 2, 7894, '2020-08-21 08:54:18', 0, 123, '2020-08-21', 'Lê Thị Huyền', 2, ''),
( 2, 789, '2020-08-21 09:09:05', 0, 416, '2020-08-30', 'Lê Thị Huyền', 4, '');



