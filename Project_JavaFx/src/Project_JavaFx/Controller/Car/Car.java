/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Car;

import Project_JavaFx.Controller.DbService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lehie
 */
public class Car {

    private ObjectProperty<Integer> carID;
    private StringProperty sku;
    private StringProperty carName;
    private StringProperty brand;
    private StringProperty category;
    private ObjectProperty<Integer> yearOfManufacture;
    private StringProperty gear;
    private StringProperty color;
    private ObjectProperty<Integer> price;
    private StringProperty status;
    private StringProperty seat;
    private StringProperty fuelUsed;
    private ObjectProperty<Integer> brandID;
    private ObjectProperty<Integer> categoryID;
    private ObjectProperty<Integer> colorID;

    public Car() {
        carID = new SimpleObjectProperty<>(null);
        brandID = new SimpleObjectProperty<>(null);
        categoryID = new SimpleObjectProperty<>(null);
        colorID = new SimpleObjectProperty<>(null);
        sku = new SimpleStringProperty();
        carName = new SimpleStringProperty();
        brand = new SimpleStringProperty();
        category = new SimpleStringProperty();
        yearOfManufacture = new SimpleObjectProperty<>(1900);
        gear = new SimpleStringProperty();
        color = new SimpleStringProperty();
        price = new SimpleObjectProperty<>(0);
        status = new SimpleStringProperty();
        seat = new SimpleStringProperty();
        fuelUsed = new SimpleStringProperty();
    }

    public Integer getCarID() {
        return carID.get();
    }

    public Integer getBrandID() {
        return brandID.get();
    }

    public Integer getCategoryID() {
        return categoryID.get();
    }

    public Integer getColorID() {
        return colorID.get();
    }
    
    public String getSku() {
        return sku.get();
    }

    public String getCarName() {
        return carName.get();
    }

    public String getBrand() {
        return brand.get();
    }

    public String getCategory() {
        return category.get();
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture.get();
    }

    public String getGear() {
        return gear.get();
    }

    public String getColor() {
        return color.get();
    }

    public Integer getPrice() {
        return price.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getSeat() {
        return seat.get();
    }

    public String getFuelUsed() {
        return fuelUsed.get();
    }

    public void setCarID(int carID) {
        this.carID.set(carID);
    }

    public void setBrandID(int brandID) {
        this.brandID.set(brandID);
    }

    public void setCategoryID(int categoryID) {
        this.categoryID.set(categoryID);
    }
 
    public void setColorID(int colorID) {
        this.colorID.set(colorID);
    }

    public void setSku(String sku) {
        this.sku.set(sku);
    }

    public void setCarName(String carName) {
        this.carName.set(carName);
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture.set(yearOfManufacture);
    }

    public void setGear(String gear) {
        this.gear.set(gear);
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setSeat(String seat) {
        this.seat.set(seat);
    }

    public void setFuelUsed(String fuelUsed) {
        this.fuelUsed.set(fuelUsed);
    }

    public ObjectProperty<Integer> getCarIDProperty() {
        return this.carID;
    }

    public ObjectProperty<Integer> getBrandIDProperty() {
        return this.brandID;
    }

    public ObjectProperty<Integer> getCategoryIDProperty() {
        return this.categoryID;
    }
    
    public ObjectProperty<Integer> getColorIDProperty() {
        return this.colorID;
    }

    public StringProperty getCarSkuProperty() {
        return this.sku;
    }

    public StringProperty getCarNameProperty() {
        return this.carName;
    }

    public StringProperty getBrandProperty() {
        return this.brand;
    }

    public StringProperty getCategoryProperty() {
        return this.category;
    }

    public ObjectProperty<Integer> getYearOfManufactureProperty() {
        return this.yearOfManufacture;
    }

    public StringProperty getGearProperty() {
        return this.gear;
    }

    public StringProperty getColorProperty() {
        return this.color;
    }

    public ObjectProperty<Integer> getPriceProperty() {
        return this.price;
    }

    public StringProperty getStatusProperty() {
        return this.status;
    }

    public StringProperty getSeatProperty() {
        return this.seat;
    }

    public StringProperty getFuelUsedProperty() {
        return this.fuelUsed;
    }

    public static ObservableList<Car> selectAll() {
        ObservableList<Car> cars = FXCollections.observableArrayList();

        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT car.carID, car.sku,car.seat,car.fuelUsed,brand.brandID, brand.brand, Category.categoryID, category.categoryName, Car.carName, car.yearOfManufacture, car.price, car.status, car.gear,color.colorID, color.color FROM car, brand ,category,color WHERE brand.brandID = car.brandID AND category.categoryID = car.categoryID AND car.colorID = color.colorID ");) {

            while (rs.next()) {
                Car b = new Car();
                b.setCarID(rs.getInt("carID"));
                b.setSku(rs.getString("sku"));
                b.setCarName(rs.getString("carName"));
                b.setBrand(rs.getString("brand"));
                b.setCategory(rs.getString("categoryName"));
                b.setYearOfManufacture(rs.getInt("YearOfManufacture"));
                b.setGear(rs.getString("gear"));
                b.setPrice(rs.getInt("price"));
                b.setColor(rs.getString("color"));
                b.setSeat(rs.getString("seat"));
                b.setFuelUsed(rs.getString("fuelUsed"));
                b.setBrandID(rs.getInt("brandID"));
                b.setCategoryID(rs.getInt("categoryID"));
                b.setColorID(rs.getInt("colorID"));
                
                if (rs.getInt("status") == 0) {
                    b.setStatus("Dừng Bán");
                } else {
                    b.setStatus("Đang Bán");
                }

                cars.add(b);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return cars;
    }

    public static ObservableList<Car> selectCategory1() {
        ObservableList<Car> cars = FXCollections.observableArrayList();

        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT car.carID, car.sku,car.seat,car.fuelUsed,brand.brandID, brand.brand, Category.categoryID, category.categoryName, Car.carName, car.yearOfManufacture, car.price, car.status, car.gear,color.colorID, color.color FROM car, brand ,category,color WHERE brand.brandID = car.brandID AND category.categoryID = car.categoryID AND car.colorID = color.colorID AND category.categoryName = 'Xe Con';");) {

            while (rs.next()) {
                Car b = new Car();
                b.setCarID(rs.getInt("carID"));
                b.setSku(rs.getString("sku"));
                b.setCarName(rs.getString("carName"));
                b.setBrand(rs.getString("brand"));
                b.setCategory(rs.getString("categoryName"));
                b.setYearOfManufacture(rs.getInt("YearOfManufacture"));
                b.setGear(rs.getString("gear"));
                b.setPrice(rs.getInt("price"));
                b.setColor(rs.getString("color"));
                b.setSeat(rs.getString("seat"));
                b.setFuelUsed(rs.getString("fuelUsed"));
                b.setBrandID(rs.getInt("brandID"));
                b.setCategoryID(rs.getInt("categoryID"));
                b.setColorID(rs.getInt("colorID"));
                if (rs.getInt("status") == 0) {
                    b.setStatus("Dừng Bán");
                } else {
                    b.setStatus("Đang Bán");
                }

                cars.add(b);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return cars;
    }

    public static ObservableList<Car> selectCategory2() {
        ObservableList<Car> cars = FXCollections.observableArrayList();

        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT car.carID, car.sku,car.seat,car.fuelUsed,brand.brandID, brand.brand, Category.categoryID, category.categoryName, Car.carName, car.yearOfManufacture, car.price, car.status, car.gear,color.colorID, color.color FROM car, brand ,category,color WHERE brand.brandID = car.brandID AND category.categoryID = car.categoryID AND car.colorID = color.colorID AND category.categoryName = 'Bán Tải';");) {

            while (rs.next()) {
                Car b = new Car();
                b.setCarID(rs.getInt("carID"));
                b.setSku(rs.getString("sku"));
                b.setCarName(rs.getString("carName"));
                b.setBrand(rs.getString("brand"));
                b.setCategory(rs.getString("categoryName"));
                b.setYearOfManufacture(rs.getInt("YearOfManufacture"));
                b.setGear(rs.getString("gear"));
                b.setPrice(rs.getInt("price"));
                b.setColor(rs.getString("color"));
                b.setSeat(rs.getString("seat"));
                b.setFuelUsed(rs.getString("fuelUsed"));
                b.setBrandID(rs.getInt("brandID"));
                b.setCategoryID(rs.getInt("categoryID"));
                b.setColorID(rs.getInt("colorID"));
                if (rs.getInt("status") == 0) {
                    b.setStatus("Dừng Bán");
                } else {
                    b.setStatus("Đang Bán");
                }

                cars.add(b);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return cars;
    }

    public static Car insert(Car newCar) throws SQLException {
        String sql = "INSERT INTO car ( sku, carName, yearOfManufacture, price, seat, fuelUsed, gear, status, brandID, categoryID, colorID ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        ResultSet key = null;
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, newCar.getSku());
            stmt.setString(2, newCar.getCarName());
            stmt.setInt(3, newCar.getYearOfManufacture());
            stmt.setInt(4, newCar.getPrice());
       
            if (newCar.getSeat().equals("2")) {
                stmt.setInt(5, 2);
            } else if (newCar.getSeat().equals("5")) {
                stmt.setInt(5, 5);
            } else {
                stmt.setInt(5, 7);
            }
            
            stmt.setString(6, newCar.getFuelUsed());
            stmt.setString(7, newCar.getGear());
            
            if (newCar.getStatus().equals("Dừng Bán")) {
                stmt.setInt(8, 0);
            } else {
                stmt.setInt(8, 1);
            }

            stmt.setInt(9, newCar.getBrandID());
            stmt.setInt(10, newCar.getCategoryID());
            stmt.setInt(11, newCar.getColorID());
            
            int rowInsert = stmt.executeUpdate();

            if (rowInsert == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newCar.setCarID(newKey);
                return newCar;
            } else {
                System.out.println("Thêm mới không thành công");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (key != null) {
                key.close();
            }
        }
    }

    public static boolean update(Car updateCar) {
        String sql = "UPDATE car SET "
                + "sku = ?, carName = ?, yearOfManufacture = ?, price = ?, seat = ?, fuelUsed = ?, gear = ?, status = ?, brandID = ?, categoryID = ?, colorID = ? WHERE car.carID = ?";
        System.out.println(updateCar);
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, updateCar.getSku());
            stmt.setString(2, updateCar.getCarName());
            stmt.setInt(3, updateCar.getYearOfManufacture());
            stmt.setInt(4, updateCar.getPrice());
            if (updateCar.getSeat().equals("2")) {
                stmt.setInt(5, 2);
            } else if (updateCar.getSeat().equals("5")){
                stmt.setInt(5, 5);
            }else{
                stmt.setInt(5, 7);
            }
            
            stmt.setString(6, updateCar.getFuelUsed());
            stmt.setString(7, updateCar.getGear());
            if (updateCar.getStatus().equals("Đang Bán")) {
                stmt.setInt(8, 1);
            } else {
                stmt.setInt(8, 0);
            }

            stmt.setInt(9, updateCar.getBrandID());
            stmt.setInt(10, updateCar.getCategoryID());
            stmt.setInt(11, updateCar.getColorID());
            stmt.setInt(12, updateCar.getCarID());
            
            int rowUpdate1 = stmt.executeUpdate();
            if (rowUpdate1 == 1) {
                return true;
            } else {
                System.out.println("Không thay đổi được trạng thái của xe");
                return false;
            }

        } catch (Exception e) {
            System.err.print(e);
            return false;
        }
    }
    
    public static int getBrandID(String brand){
        try {
            Connection connection = DbService.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from brand where brand = '" +brand + "'" );
            if(rs.next()){
                return rs.getInt("brandID");
            } else{
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public static int getCategoryID(String categoryName){
        try {
            Connection connection = DbService.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from category where categoryName = '" +categoryName + "'" );
            if(rs.next()){
                return rs.getInt("categoryID");
            } else{
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public static int getColorID(String color){
        try {
            Connection connection = DbService.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from color where color = '" +color + "'" );
            if(rs.next()){
                return rs.getInt("colorID");
            } else{
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
