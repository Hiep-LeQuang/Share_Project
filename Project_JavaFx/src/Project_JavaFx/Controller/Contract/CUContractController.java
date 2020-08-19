/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Contract;

import Project_JavaFx.Controller.Customer.Customer;
import Project_JavaFx.Controller.DbService;
import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author lehie
 */
public class CUContractController {

    private Contract editContract = null;

    @FXML
    private TextField txtCustomer;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPrice;

    @FXML
    private DatePicker txtRD;

    @FXML
    private DatePicker txtDos;

    @FXML
    private TextArea txtNote;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAccountant;

    @FXML
    private TextField txtDeposits;

    @FXML
    private ComboBox<String> cbxStatus;

    @FXML
    private ComboBox<String> cbxSku;

    @FXML
    private TextField txtNameCar;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnSave(ActionEvent event) throws IOException, SQLException {
        if(validation()){
            if (editContract == null) {
            Customer insertCustomer = extractCustomerFromFields();
            Contract insertContract = extractContractFromFields();
            insertCustomer = Customer.insert(insertCustomer);
            insertContract.setCustomerID(insertCustomer.getCustomerID());
            insertContract = Contract.insert(insertContract);

            Navigator.getInstance().goToMain();
        } else {
            Contract updateContract = extractContractFromFields();
            updateContract.setContractID(this.editContract.getContractID());

            Customer updateCustomer = extractCustomerFromFields();
            updateCustomer.setCustomerID(editContract.getCustomerID());
            Customer.update(updateCustomer);

            boolean result = Contract.update(updateContract);
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (result) {
                alert.setHeaderText("Cập nhật thương hiệu thành công");
            } else {
                alert.setHeaderText("Cập nhật thương hiệu không thành công");
            }
            Navigator.getInstance().goToMain();
        }
        }
    }

    private Customer extractCustomerFromFields() {
        Customer customer = new Customer();
        customer.setCustomerName(txtCustomer.getText());
        customer.setPhone(txtPhone.getText());
        customer.setAddress(txtAddress.getText());
        customer.setEmail(txtEmail.getText());
        return customer;
    }

    private Contract extractContractFromFields() {
        Contract contract = new Contract();
        contract.setPrice(Integer.parseInt(txtPrice.getText()));
        LocalDate date = txtRD.getValue();
        contract.setDateOfSale(txtDos.getValue().toString());
        contract.setDeposits(Integer.parseInt(txtDeposits.getText()));
        contract.setProductReceiptDate(txtRD.getValue().toString());
        
        contract.setAccountant(txtAccountant.getText());
        contract.setNote(txtNote.getText());

        contract.setSku(cbxSku.getSelectionModel().getSelectedItem());
        contract.setStatus(cbxStatus.getSelectionModel().getSelectedItem());

        String sku = cbxSku.getSelectionModel().getSelectedItem();
        if (contract.getSku() != null) {
            contract.setCarID(contract.getCarID(sku));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Khong co xe!");

        }

        return contract;
    }

    @FXML
    void txtNameCar(ActionEvent event) {

    }

    @FXML
    void cbxSku(ActionEvent event) {

    }

    @FXML
    void cbxStatus(ActionEvent event) {

    }

    @FXML
    void txtAccountant(ActionEvent event) {

    }

    @FXML
    void txtAddress(ActionEvent event) {

    }

    @FXML
    void txtCustomer(ActionEvent event) {

    }

    @FXML
    void txtDeposits(ActionEvent event) {

    }

    @FXML
    void txtDos(ActionEvent event) {

    }

    @FXML
    void txtEmail(ActionEvent event) {

    }

    @FXML
    void txtPhone(ActionEvent event) {

    }

    @FXML
    void txtPrice(ActionEvent event) {

    }

    @FXML
    void txtRD(ActionEvent event) {

    }

    public void initialize(Contract editContract) throws SQLException {
        this.editContract = editContract;
        String msg = "";
        if (editContract == null) {
            msg = "Thêm hợp đồng xe";
            cbxStatus.getItems().add("Chờ Lấy Hàng");
            cbxStatus.getItems().add("Đã Giao");

            txtNameCar.setDisable(true);

            cbxSku.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String sku = newValue;
                String carName = Contract.getCar(sku);
                txtNameCar.setText(carName);

            });

        } else {
            msg = "Chỉnh sửa hợp đồng";
            cbxStatus.getItems().add("Chờ Lấy Hàng");
            cbxStatus.getItems().add("Đã Giao");

            txtPrice.setText(editContract.getPrice().toString());
            txtDos.setValue(LocalDate.parse(editContract.getDateOfSale()));
            txtDeposits.setText(editContract.getDeposits().toString());
            txtRD.setValue(LocalDate.parse(editContract.getProductReceiptDate()));
            txtAccountant.setText(editContract.getAccountant());
            txtNote.setText(editContract.getNote());
            cbxSku.getSelectionModel().select(editContract.getSku());
            txtNameCar.setText(editContract.getCarName());
            txtNameCar.setDisable(true);

            cbxSku.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String sku = newValue;
                String carName = Contract.getCar(sku);
                txtNameCar.setText(carName);

            });

            txtCustomer.setText(editContract.getCustomerName());
            txtPhone.setText(editContract.getPhone());
            txtAddress.setText(editContract.getAddress());
            txtEmail.setText(editContract.getEmail());

            if (editContract.getStatus().equals("Chờ Lấy Hàng")) {
                cbxStatus.getSelectionModel().select("Chờ Lấy Hàng");
            } else {
                cbxStatus.getSelectionModel().select("Đã Giao");
            }

        }

        try (Connection connection = DbService.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from Car");
            while (rs.next()) {
                cbxSku.getItems().add(rs.getString("sku"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    
    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (cbxSku.getSelectionModel().isEmpty() || txtCustomer.getText().equals("") || txtPhone.getText().equals("") || txtAddress.getText().equals("") || txtEmail.getText().equals("") || txtAccountant.getText().equals("") || txtDos.getChronology().equals("") || txtRD.getChronology().equals("") || cbxStatus.getSelectionModel().isEmpty() || txtPrice.getText().isEmpty() || txtDeposits.getText().isEmpty()) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Không được để trống");
            alert.show();
            return false;
        }
        if (txtCustomer.getText().length() > 50 || txtCustomer.getText().length() < 1) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Tên nhập không vượt quá 50 kí tự");
            alert.show();
            return false;
        }

        if (txtPhone.getText().length() == 10 ) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Số điện thoại bạn nhập không hợp lệ");
            alert.show();
            return false;
        }

        if (txtAddress.getText().length() > 100 || txtAddress.getText().length() < 1) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Địa chỉ nhập không vượt quá 100 kí tự");
            alert.show();
            return false;
        }

        if (txtEmail.getText().length() > 50 || txtEmail.getText().length() < 1) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Email nhập không vượt quá 50 kí tự");
            alert.show();
            return false;
        }
        
        if (txtAccountant.getText().length() > 50 || txtAccountant.getText().length() < 1) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Tên nhập không vượt quá 50 kí tự");
            alert.show();
            return false;
        }

        if (txtDeposits.getText().length() > 11 ) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Số tiền nhập không hợp lệ");
            alert.show();
            return false;
        }

        if (txtPrice.getText().length() > 11 ) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Số tiền nhập không hợp lệ");
            alert.show();
            return false;
        }

        if (txtNote.getText().length() > 255) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Ghi chú nhập không vượt quá 255 kí tự");
            alert.show();
            return false;
        }

        String customerName = txtCustomer.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String accountant = txtAccountant.getText();
        String price = txtPrice.getText();
        String deposits = txtDeposits.getText();
        String note = txtNote.getText();
        
        String regex = "[a-zA-Z0-9_@]{1,255}";
        if (!Pattern.matches(regex, address) || !Pattern.matches(regex, email) || !Pattern.matches(regex, note)) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại chỉ gồm các ký tự a-z, A-Z, 0-9, _, @");
            alert.show();
            return false;
        }
        
        String regex1 = "[a-zA-Z0-9]{6,}";
        if (!Pattern.matches(regex, address) || !Pattern.matches(regex, email) || !Pattern.matches(regex, note)) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại chỉ gồm các ký tự a-z, A-Z, 0-9, _, @");
            alert.show();
            return false;
        }

        return true;
    }
}
