/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Contract;

import Project_JavaFx.Controller.Car.Car;
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
public class Contract {
    private ObjectProperty<Integer> contractID;
    private ObjectProperty<Integer> carID;
    private StringProperty sku;
    private StringProperty carName;
    private StringProperty productReceiptDate;
    private ObjectProperty<Integer> price;
    private ObjectProperty<Integer> deposits;
    private StringProperty customerName;
    private StringProperty phone;
    private StringProperty address;
    private ObjectProperty<Integer> customerID;
    private StringProperty status;
    private StringProperty note;
    private StringProperty accountant;
    private StringProperty dateOfSale;
    private StringProperty email;
    public Contract(){
        contractID = new SimpleObjectProperty<>(null);
        carID = new SimpleObjectProperty<>(null);
        customerID = new SimpleObjectProperty<>(null);
        sku = new SimpleStringProperty();
        carName = new SimpleStringProperty();
        productReceiptDate = new SimpleStringProperty();
        price = new SimpleObjectProperty<>(0);
        deposits = new SimpleObjectProperty<>(0);
        customerName = new SimpleStringProperty();
        phone = new SimpleStringProperty();
        status = new SimpleStringProperty();
        note = new SimpleStringProperty();
        accountant = new SimpleStringProperty();
        dateOfSale = new SimpleStringProperty();
        email = new SimpleStringProperty();
        address = new SimpleStringProperty();
    }   
    
    public Integer getContractID() {
        return contractID.get();
    }
    
    public Integer getCarID() {
        return carID.get();
    }
    
    public Integer getCustomerID() {
        return this.customerID.get();
    }
    
    public String getSku() {
        return sku.get();
    }
    
    public String getCarName() {
        return carName.get();
    }
    
    public String getProductReceiptDate() {
        return productReceiptDate.get();
    }
    
    public Integer getPrice() {
        return price.get();
    }
    
    public Integer getDeposits() {
        return deposits.get();
    }
    
    public String getCustomerName() {
        return customerName.get();
    }
    
    public String getPhone() {
        return phone.get();
    }
    
    public String getAddress() {
        return address.get();
    }
    
    public String getStatus() {
        return status.get();
    }
    
    public String getNote() {
        return note.get();
    }
    
    public String getAccountant() {
        return accountant.get();
    }
    
    public String getDateOfSale() {
        return dateOfSale.get();
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public void setContractID(int contractID) {
        this.contractID.set(contractID);
    }
    
    public void setCarID(int carID) {
        this.carID.set(carID);
    }

    public void setSku(String sku) {
        this.sku.set(sku);
    }
    
    public void setCarName(String carName) {
        this.carName.set(carName);
    }
    
    public void setProductReceiptDate(String productReceiptDate) {
        this.productReceiptDate.set(productReceiptDate);
    }

    public void setPrice(int price) {
        this.price.set(price);
    }
    
    public void setDeposits(int deposits) {
        this.deposits.set(deposits);
    }
    
    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }
    
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    public void setAddress(String address) {
        this.address.set(address);
    }
    
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    public void setNote(String note) {
        this.note.set(note);
    }
    
    public void setAccountant(String accountant) {
        this.accountant.set(accountant);
    }
    
    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale.set(dateOfSale);
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    public void setCustomerID(int customerID){
        this.customerID.set(customerID);
    }
    public ObjectProperty<Integer> getContractIDProperty() {
        return this.contractID;
    }
    
    public ObjectProperty<Integer> getCarIDProperty() {
        return this.carID;
    }

    public StringProperty getCarSkuProperty() {
        return this.sku;
    }

    public StringProperty getCarNameProperty() {
        return this.carName;
    }

    public StringProperty getProductReceiptDateProperty() {
        return this.productReceiptDate;
    }

    public ObjectProperty<Integer> getPriceProperty() {
        return this.price;
    }

    public ObjectProperty<Integer> getDepositsProperty() {
        return this.deposits;
    }

    public StringProperty getCustomerNameProperty() {
        return this.customerName;
    }

    public StringProperty getPhoneProperty() {
        return this.phone;
    }

    public StringProperty getAddressProperty() {
        return this.address;
    }
    
    public ObjectProperty<Integer> getCustomerIDProperty() {
        return this.customerID;
    }
    
    public StringProperty getStatusProperty() {
        return this.status;
    }
    
    public StringProperty getNoteProperty() {
        return this.note;
    }
    
    public StringProperty getAccountantProperty() {
        return this.accountant;
    }
    
    public StringProperty getDateOfSaleProperty() {
        return this.dateOfSale;
    }
    
    public StringProperty getEmailProperty() {
        return this.email;
    }
    
    public static ObservableList<Contract> selectAll(){
        ObservableList<Contract> contracts = FXCollections.observableArrayList();
            
        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT contract.contractID, car.carID, contract.customerID, car.sku, car.carName, contract.productReceiptDate, contract.status , contract.note,  contract.accountant, contract.dateOfSale, contract.price, contract.deposits, customer.customerName,customer.email, customer.phone, customer.address FROM contract, car, customer WHERE contract.CarID = car.carID AND contract.customerID = customer.customerID");){
            
            while (rs.next()) {
                Contract c = new Contract();
                c.setContractID(rs.getInt("contractID"));
                c.setSku(rs.getString("sku"));
                c.setCarName(rs.getString("carName"));
                c.setProductReceiptDate(rs.getString("productReceiptDate"));
                c.setPrice(rs.getInt("price"));
                c.setDeposits(rs.getInt("deposits"));
                c.setCustomerName(rs.getString("customerName"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setCustomerID(rs.getInt("customerID"));
                if(rs.getInt("status") == 1){
                     c.setStatus("Đã Giao");
                } else{
                    c.setStatus("Chờ Lấy Hàng");
                }
                c.setNote(rs.getString("note"));
                c.setAccountant(rs.getString("accountant"));
                c.setDateOfSale(rs.getString("dateOfSale"));
                c.setEmail(rs.getString("email"));
                c.setCarID(rs.getInt("carID"));
                
                contracts.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return contracts;
    }
    
    public static Contract insert(Contract newContract) throws SQLException {
        String sql = "INSERT INTO contract ( price, dateOfSale, status, deposits, productReceiptDate, accountant, CarID, note, customerID ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        ResultSet key = null;
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setInt(1, newContract.getPrice());
            stmt.setString(2, newContract.getDateOfSale());
            if (newContract.getStatus().equals("Chờ Lấy Hàng")) {
                stmt.setInt(3, 0);
            } else {
                stmt.setInt(3, 1);
            }
            stmt.setInt(4, newContract.getDeposits());
   
            stmt.setString(5, newContract.getProductReceiptDate());
            stmt.setString(6, newContract.getAccountant());  
            stmt.setInt(7, newContract.getCarID());
            stmt.setString(8, newContract.getNote());
            stmt.setInt(9, newContract.getCustomerID());
            
            int rowInsert = stmt.executeUpdate();

            if (rowInsert == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newContract.setCarID(newKey);
                return newContract;
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
    
    public static boolean update(Contract updateContract){
        String sql = "UPDATE contract SET price = ?, dateOfSale = ?, status = ?, deposits = ?, productReceiptDate = ?, accountant = ?, CarID = ?, note = ? WHERE contract.contractID = ?";
               System.out.println(updateContract); 
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ){
            
            stmt.setInt(1, updateContract.getPrice());
            stmt.setString(2, updateContract.getDateOfSale());
            if(updateContract.getStatus().equals("Đã Giao")){
                stmt.setInt(3,1); 
            } else{
                stmt.setInt(3,0); 
            }
            stmt.setInt(4, updateContract.getDeposits());
            stmt.setString(5, updateContract.getProductReceiptDate());
            stmt.setString(6, updateContract.getAccountant());
            stmt.setInt(7, updateContract.getCarID());
            stmt.setString(8,updateContract.getNote());
            stmt.setInt(9, updateContract.getContractID());
            
            int rowUpdate = stmt.executeUpdate();
            if(rowUpdate == 1 ){
                return true;
            }else{
                System.out.println("Chuyển Trạng Thái Không Thành Công");
                return false;
            }
   
        } catch (Exception e) {
            System.err.print(e);
            return false;
        }
    }
    
    public static boolean delete(Contract deleteContract){
        String sql1 = "DELETE FROM Contract WHERE contractID = ?";
        
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                ){
            stmt1.setInt(1, deleteContract.getContractID());
            
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
    
     public int getCarID(String sku){
        try {
            Connection connection = DbService.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from car where sku = '" +sku + "'" );
            if(rs.next()){
                return rs.getInt("carID");
            } else{
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public static int getCustomerID(String customerName){
        try {
            Connection connection = DbService.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from category where categoryName = '" +customerName + "'" );
            if(rs.next()){
                return rs.getInt("customerName");
            } else{
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public static String getCar(String sku){
        try {
            Connection connection = DbService.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select car.carName from car where sku = '" +sku + "'" );
            if(rs.next()){
                
                return rs.getString(1);
            } else{
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
