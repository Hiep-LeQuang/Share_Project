/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Account;

import Project_JavaFx.Controller.Contract.Contract;
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
public class Account {
    private ObjectProperty<Integer> accountID;
    private StringProperty userName;
    private StringProperty password;
    
    public Account(){
        accountID = new SimpleObjectProperty<>(null);
        userName = new SimpleStringProperty();
        password = new SimpleStringProperty();
    }
    
    public Integer getAccountID() {
        return accountID.get();
    }

    public String getUserName() {
        return userName.get();
    }
    
    public String getPassword() {
        return password.get();
    }

    public void setAccountID(int accountID) {
        this.accountID.set(accountID);
    }
    
    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    public ObjectProperty<Integer> getAccountIDProperty() {
        return this.accountID;
    }

    public StringProperty getUserNameProperty() {
        return this.userName;
    }
    
    public StringProperty getPasswordProperty() {
        return this.password;
    }

    public static ObservableList<Account> selectAll(){
        ObservableList<Account> accounts = FXCollections.observableArrayList();
            
        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM account");){
            
            while (rs.next()) {
                Account b = new Account();
                b.setAccountID(rs.getInt("accountID"));
                b.setUserName(rs.getString("userName"));
                b.setPassword(rs.getString("password"));
                
                accounts.add(b);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return accounts;
    }
    
    public static Account insert(Account newAccount) throws SQLException{
        String sql = "INSERT INTO account ( userName, password) VALUES ( ?, ?);";
        
        ResultSet key = null;
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){
            
            stmt.setString(1, newAccount.getUserName());
            stmt.setString(2, newAccount.getPassword());
            
            int rowInsert = stmt.executeUpdate();
            
            if(rowInsert == 1){
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newAccount.setAccountID(newKey);
                return newAccount;
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
    
    public static boolean update(Account updateAccount){
        String sql = "UPDATE account SET brand = ?, password = ? WHERE accountID = ? ;";
               System.out.println(updateAccount); 
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            
            stmt.setString(1, updateAccount.getUserName());
            stmt.setString(2, updateAccount.getPassword());
            stmt.setInt(3, updateAccount.getAccountID());
                    
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
    
    public static boolean delete(Account deleteAccount){
        String sql1 = "DELETE FROM account WHERE accountID = ?";
        
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                ){
            stmt1.setInt(1, deleteAccount.getAccountID());
            
            int rowDelete = stmt1.executeUpdate();
            if(rowDelete == 1){
                System.out.println("Xoa ok");
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
