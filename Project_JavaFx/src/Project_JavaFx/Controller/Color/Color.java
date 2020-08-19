/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Color;

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
public class Color {
    private ObjectProperty<Integer> colorID;
    private StringProperty color;
    
    public Color(){
        colorID = new SimpleObjectProperty<>(null);
        color = new SimpleStringProperty();
    }
    
    public Integer getColorID() {
        return colorID.get();
    }

    public String getColor() {
        return color.get();
    }

    public void setColorID(int colorID) {
        this.colorID.set(colorID);
    }
    
    public void setColor(String color) {
        this.color.set(color);
    }
    
    public ObjectProperty<Integer> getColorIDProperty() {
        return this.colorID;
    }

    public StringProperty getColorProperty() {
        return this.color;
    }

    public static ObservableList<Color> selectAll(){
        ObservableList<Color> colors = FXCollections.observableArrayList();
            
        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Color");){
            
            while (rs.next()) {
                Color c = new Color();
                c.setColorID(rs.getInt("colorID"));
                c.setColor(rs.getString("color"));
                
                colors.add(c);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return colors;
    }
    
    public static Color insert(Color newColor) throws SQLException{
        String sql = "INSERT INTO color ( color ) VALUES ( ? );";
        
        ResultSet key = null;
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            
            stmt.setString(1, newColor.getColor());
            
            int rowInsert = stmt.executeUpdate();
            
            if(rowInsert == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newColor.setColorID(newKey);
                return newColor;
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
    
    public static boolean update(Color updateColor){
        String sql = "UPDATE color SET color = ? WHERE color.colorID = ?;";
               System.out.println(updateColor); 
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            
            stmt.setString(1, updateColor.getColor());
            stmt.setInt(2, updateColor.getColorID());
                    
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
