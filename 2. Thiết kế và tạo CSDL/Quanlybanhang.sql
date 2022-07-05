create database quanlybanhang;
use quanlybanhang;
CREATE TABLE `customer` (
  `cID` int NOT NULL,
  `cName` varchar(45) DEFAULT NULL,
  `cAge` int DEFAULT NULL,
  PRIMARY KEY (`cID`));

CREATE TABLE `orders` (
  `oID` int NOT NULL,
  `cID` int DEFAULT NULL,
  `oDate` date DEFAULT NULL,
  `oTotalPrice` double DEFAULT NULL);

CREATE TABLE `orderdetail` (
  `oID` int NOT NULL,
  `pID` int DEFAULT NULL,
  `odQTY` int DEFAULT NULL);

CREATE TABLE `product` (
  `pID` int NOT NULL,
  `pName` varchar(45) DEFAULT NULL,
  `pPrice` double DEFAULT NULL)
 