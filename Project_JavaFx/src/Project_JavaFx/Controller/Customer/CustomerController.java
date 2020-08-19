/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Customer;

import Project_JavaFx.Controller.Contract.Contract;
import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author lehie
 */
public class CustomerController {

    @FXML
    private TableView<Customer> tvCustomer;

    @FXML
    private TableColumn<Customer, String> tcName;

    @FXML
    private TableColumn<Customer, String> tcCmnd;

    @FXML
    private TableColumn<Customer, String> tcphone;

    @FXML
    private TableColumn<Customer, String> tcAddress;

    @FXML
    private TableColumn<Customer, String> tcEmail;

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateCustomer(null);
    }

    @FXML
    void btnSearch(ActionEvent event) {

    }

    @FXML
    void btnContract(ActionEvent event) throws IOException, SQLException {
        Customer customer = tvCustomer.getSelectionModel().getSelectedItem();
        ObservableList<Contract> contracts = Contract.getContract(customer.getCustomerID());
        Navigator.getInstance().goToContract(contracts);
    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
        Customer updateCustomer = tvCustomer.getSelectionModel().getSelectedItem();

        if (updateCustomer == null) {
            selectedCustomerWarning();
        } else {
            Navigator.getInstance().goToCreateCustomer(updateCustomer);
        }
    }

    @FXML
    void txtSearch(ActionEvent event) {

    }

    private void selectedCustomerWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một hợp đồng");
        alert.setHeaderText("Bạn phải chọn một hợp đồng ở trong danh sách");
        alert.showAndWait();
    }

    public void initialize() {

        tvCustomer.setItems(Customer.selectAll());

        tcName.setCellValueFactory((Customer) -> {
            return Customer.getValue().getCustomerNameProperty();
        });

        tcCmnd.setCellValueFactory((Customer) -> {
            return Customer.getValue().getCmndProperty();
        });

        tcphone.setCellValueFactory((Customer) -> {
            return Customer.getValue().getPhoneProperty();
        });

        tcAddress.setCellValueFactory((Customer) -> {
            return Customer.getValue().getAddressProperty();
        });

        tcEmail.setCellValueFactory((Customer) -> {
            return Customer.getValue().getEmailProperty();
        });
    }
}
