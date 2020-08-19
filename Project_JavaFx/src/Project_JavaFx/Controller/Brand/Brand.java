/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Brand;

import Project_JavaFx.Controller.DbService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Brand {
    private ObjectProperty<Integer> brandID;
    private StringProperty brand;
    private StringProperty status;
    
    public Brand(){
        brandID = new SimpleObjectProperty<>(null);
        brand = new SimpleStringProperty();
        status = new SimpleStringProperty();
    }
    
    public Integer getBrandID() {
        return brandID.get();
    }

    public String getBrand() {
        return brand.get();
    }
    
    public String getStatus() {
        return status.get();
    }

    public void setBrandID(int brandID) {
        this.brandID.set(brandID);
    }
    
    public void setBrand(String brand) {
        this.brand.set(brand);
    }
    
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    public ObjectProperty<Integer> getBrandIDProperty() {
        return this.brandID;
    }

    public StringProperty getBrandProperty() {
        return this.brand;
    }
    
    public StringProperty getStatusProperty() {
        return this.status;
    }

    public static ObservableList<Brand> selectAll(){
        ObservableList<Brand> brands = FXCollections.observableArrayList();
            
        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM brand");){
            
            while (rs.next()) {
                Brand b = new Brand();
                b.setBrandID(rs.getInt("brandID"));
                b.setBrand(rs.getString("brand"));
                if(rs.getInt("status") == 0){
                     b.setStatus("Ngừng Kinh Doanh");
                } else{
                    b.setStatus("Đang Kinh Doanh");
                }
                
                brands.add(b);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return brands;
    }
    
    public static Brand insert(Brand newBrand) throws SQLException{
        String sql = "INSERT INTO brand ( brand, status) VALUES ( ?, ?);";
        
        ResultSet key = null;
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            
            stmt.setString(1, newBrand.getBrand());
            if(newBrand.getStatus().equals("Ngừng Kinh Doanh")){
                     stmt.setInt(2,0);
                } else{
                    stmt.setInt(2,1);
                }
            
            int rowInsert = stmt.executeUpdate();
            
            if(rowInsert == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newBrand.setBrandID(newKey);
                return newBrand;
            }else{
                System.out.println("Thêm mới không thành công");
                return null;
            }
                    
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally{
            if(key != null){
                key.close();
            }
        }
    }
    
    public static boolean update(Brand updateBrand){
        String sql = "UPDATE brand SET brand = ?, status = ? WHERE brandID = ? ;";
               System.out.println(updateBrand); 
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            
            stmt.setString(1, updateBrand.getBrand());
            if(updateBrand.getStatus().equals("Đang Kinh Doanh")){
                stmt.setInt(2,1); 
            } else{
                stmt.setInt(2,0); 
            }
            
            stmt.setInt(3, updateBrand.getBrandID());
                    
            int rowUpdate1 = stmt.executeUpdate();
            if(rowUpdate1 == 1 ){
                return true;
            }else{
                System.out.println("Cập nhật không thành công");
                return false;
            }
   
        } catch (Exception e) {
            System.err.print(e);
            return false;
        }
    }
}
