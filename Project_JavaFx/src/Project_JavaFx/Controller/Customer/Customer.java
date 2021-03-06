/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Customer;

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
public class Customer {

    private ObjectProperty<Integer> customerID;
    private StringProperty customerName;
    private StringProperty cmnd;
    private StringProperty phone;
    private StringProperty address;
    private StringProperty email;
    private ObjectProperty<Integer> contractID;

    public Customer() {
        customerID = new SimpleObjectProperty<>(null);
        customerName = new SimpleStringProperty();
        cmnd = new SimpleStringProperty();
        phone = new SimpleStringProperty();
        address = new SimpleStringProperty();
        email = new SimpleStringProperty();
        contractID = new SimpleObjectProperty<>(null);
    }

    public Integer getCustomerID() {
        return customerID.get();
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public String getCmnd() {
        return cmnd.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getEmail() {
        return email.get();
    }

    public Integer getContractID() {
        return contractID.get();
    }

    public void setCustomerID(int customerID) {
        this.customerID.set(customerID);
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public void setCmnd(String cmnd) {
        this.cmnd.set(cmnd);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setContractID(int contractID) {
        this.contractID.set(contractID);
    }

    public ObjectProperty<Integer> getCustomerIDProperty() {
        return this.customerID;
    }

    public StringProperty getCustomerNameProperty() {
        return this.customerName;
    }

    public StringProperty getCmndProperty() {
        return this.cmnd;
    }

    public StringProperty getPhoneProperty() {
        return this.phone;
    }

    public StringProperty getAddressProperty() {
        return this.address;
    }

    public StringProperty getEmailProperty() {
        return this.email;
    }

    public ObjectProperty<Integer> getContractIDProperty() {
        return this.contractID;
    }

    public static ObservableList<Customer> selectAll() {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM customer");) {

            while (rs.next()) {
                Customer b = new Customer();
                b.setCustomerID(rs.getInt("customerID"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCmnd(rs.getString("cmnd"));
                b.setPhone(rs.getString("phone"));
                b.setAddress(rs.getString("address"));
                b.setEmail(rs.getString("email"));
                //b.setContractID(rs.getInt("contractID"));

                customers.add(b);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return customers;
    }

    public static Customer insert(Customer newCustomer) throws SQLException {
        String sql = "INSERT INTO Customer ( customerName, cmnd, phone, address, email) VALUES ( ?, ?, ?, ?, ?);";

        ResultSet key = null;
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, newCustomer.getCustomerName());
            stmt.setString(2, newCustomer.getCmnd());
            stmt.setString(3, newCustomer.getPhone());
            stmt.setString(4, newCustomer.getAddress());
            stmt.setString(5, newCustomer.getEmail());

            int rowInsert = stmt.executeUpdate();

            if (rowInsert == 1) {
                key = stmt.getGeneratedKeys();
                key.next();
                int newKey = key.getInt(1);
                newCustomer.setCustomerID(newKey);
                return newCustomer;
            } else {
                System.out.println("Thêm mới không thành công");
                return null;
            }

        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            if (key != null) {
                key.close();
            }
        }
    }

    public static boolean update(Customer updateCustomer) {
        String sql = "UPDATE customer SET customerName = ?, cmnd = ?, phone = ?, address = ?, email = ? WHERE customerID = ? ;";
        System.out.println(updateCustomer);
        try (
                Connection conn = DbService.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, updateCustomer.getCustomerName());
            stmt.setString(2, updateCustomer.getCmnd());
            stmt.setString(3, updateCustomer.getPhone());
            stmt.setString(4, updateCustomer.getAddress());
            stmt.setString(5, updateCustomer.getEmail());
            stmt.setInt(6, updateCustomer.getCustomerID());

            int rowUpdate1 = stmt.executeUpdate();
            if (rowUpdate1 == 1) {
                return true;
            } else {
                System.out.println("Cập nhật không thành công");
                return false;
            }

        } catch (Exception e) {
            System.err.print(e);
            return false;
        }
    }

    public static Customer getCustomer(String cmnd) {
        try {
            Connection connection = DbService.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from customer where cmnd = '" + cmnd + "'");
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setCmnd(rs.getString("cmnd"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                return customer;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean checkCmnd(String cmnd) {
        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Customer Where cmnd = '" + cmnd + "'");) {

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public static ObservableList<Customer> selectCustomerByCmnd(String cmnd) {
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        try (
                Connection conn = DbService.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM customer Where cmnd like '%" +cmnd + "%'");) {

            while (rs.next()) {
                Customer b = new Customer();
                b.setCustomerID(rs.getInt("customerID"));
                b.setCustomerName(rs.getString("customerName"));
                b.setCmnd(rs.getString("cmnd"));
                b.setPhone(rs.getString("phone"));
                b.setAddress(rs.getString("address"));
                b.setEmail(rs.getString("email"));
                //b.setContractID(rs.getInt("contractID"));

                customers.add(b);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return customers;
    }
}
