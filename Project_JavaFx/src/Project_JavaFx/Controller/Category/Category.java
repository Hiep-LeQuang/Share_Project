/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Category;

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
public class Category {
    private ObjectProperty<Integer> categoryID;
    private StringProperty category;
    private StringProperty status;
    
    public Category(){
        categoryID = new SimpleObjectProperty<>(null);
        category = new SimpleStringProperty();
        status = new SimpleStringProperty();
    }
    
    public Integer getCategoryID() {
        return categoryID.get();
    }

    public String getCategory() {
        return category.get();
    }
    
    public String getStatus() {
        return status.get();
    }

    public void setCategoryID(int categoryID) {
        this.categoryID.set(categoryID);
    }
    
    public void setCategory(String category) {
        this.category.set(category);
    }
    
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    public ObjectProperty<Integer> getCategoryIDProperty() {
        return this.categoryID;
    }

    public StringProperty getCategoryProperty() {
        return this.category;
    }
    
    public StringProperty getStatusProperty() {
        return this.status;
    }
    
    public static ObservableList<Category> selectAll(){
        ObservableList<Category> categorys = FXCollections.observableArrayList();
            
        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM category");){
            
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("categoryID"));
                c.setCategory(rs.getString("categoryName"));
                if(rs.getInt("status") == 0){
                     c.setStatus("Ngừng Kinh Doanh");
                } else{
                    c.setStatus("Đang Kinh Doanh");
                }
                
                categorys.add(c);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return categorys;
    }
    
    public static Category insert(Category newCategory) throws SQLException{
        String sql = "INSERT INTO category ( categoryName, status) VALUES ( ?, ?);";
        
        ResultSet key = null;
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            
            stmt.setString(1, newCategory.getCategory());
            if(newCategory.getStatus().equals("Ngừng Kinh Doanh")){
                     stmt.setInt(2,0);
                } else{
                    stmt.setInt(2,1);
                }
            
            int rowInsert = stmt.executeUpdate();
            
            if(rowInsert == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newCategory.setCategoryID(newKey);
                return newCategory;
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
    
    public static boolean update(Category updateCategory){
        String sql = "UPDATE category SET categoryName = ?, status = ? WHERE category.categoryID = ?;";
               System.out.println(updateCategory); 
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            
            stmt.setString(1, updateCategory.getCategory());
            if(updateCategory.getStatus().equals("Đang Kinh Doanh")){
                stmt.setInt(2,1); 
            } else{
                stmt.setInt(2,0); 
            }
            
            stmt.setInt(3, updateCategory.getCategoryID());
                    
            int rowUpdate1 = stmt.executeUpdate();
            if(rowUpdate1 == 1 ){
                return true;
            }else{
                System.out.println("Không thay đổi được trạng thái của loại");
                return false;
            }
   
        } catch (Exception e) {
            System.err.print(e);
            return false;
        }
    }
}
