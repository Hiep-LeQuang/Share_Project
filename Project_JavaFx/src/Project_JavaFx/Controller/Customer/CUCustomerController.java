/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Customer;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author lehie
 */
public class CUCustomerController {

    private Customer editCustomer = null;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnSave(ActionEvent event) throws IOException, SQLException {
        if (validation()) {
            if (editCustomer == null) {
                Customer insertCustomer = extractCustomerFromFields();
                insertCustomer = Customer.insert(insertCustomer);
                Navigator.getInstance().goToMain();
            } else {
                Customer updateCustomer = extractCustomerFromFields();
                updateCustomer.setCustomerID(this.editCustomer.getCustomerID());

                boolean result = Customer.update(updateCustomer);
                Alert alert = new Alert(Alert.AlertType.NONE);
                if (result) {
                    alert.setHeaderText("Cập nhật khách hàng thành công");
                } else {
                    alert.setHeaderText("Cập nhật khách hàng không thành công");
                }
                Navigator.getInstance().goToMain();
            }
        }
    }

    @FXML
    void txtAddress(ActionEvent event) {

    }

    @FXML
    void txtEmail(ActionEvent event) {

    }

    @FXML
    void txtName(ActionEvent event) {

    }

    @FXML
    void txtPhone(ActionEvent event) {

    }

    private Customer extractCustomerFromFields() {
        Customer customer = new Customer();
        customer.setCustomerName(txtName.getText());
        customer.setPhone(txtPhone.getText());
        customer.setAddress(txtAddress.getText());
        customer.setEmail(txtEmail.getText());
        return customer;
    }

    public void initialize(Customer editCustomer) {
        this.editCustomer = editCustomer;
        String msg = "";
        if (editCustomer == null) {
            msg = "Thêm mới thông tin khách hàng";
        } else {
            msg = "Chỉnh sửa thông tin khách hàng";
            txtName.setText(editCustomer.getCustomerName());
            txtPhone.setText(editCustomer.getPhone());
            txtAddress.setText(editCustomer.getAddress());
            txtEmail.setText(editCustomer.getEmail());
        }
    }
//    List<String> error = new ArrayList<>();
    String mgs = "";
    String mgs1 = "";
    String mgs2 = "";
    String mgs3 = "";

    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (txtName.getText().isEmpty() || txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty() || txtEmail.getText().isEmpty()) {
//            alert.setTitle("Cảnh báo đăng nhập");
//            alert.setHeaderText("Không được để trống");
//            alert.show();
//            return false;
            mgs = "Không được để trống";
        }
        if (txtName.getText().length() > 50 || txtName.getText().length() < 1) {
//            alert.setTitle("Cảnh báo đăng nhập");
//            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
//            alert.show();
//            return false;
            mgs1 = "Phân loại nhập không vượt quá 30 kí tự";
        }

        if (txtPhone.getText().length() > 50 || txtPhone.getText().length() < 1) {
//            alert.setTitle("Cảnh báo đăng nhập");
//            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
//            alert.show();
//            return false;
            mgs2 = "Số điện thoại nhập không vượt quá 10 kí tự";
        }

        if (txtAddress.getText().length() > 50 || txtAddress.getText().length() < 1) {
//            alert.setTitle("Cảnh báo đăng nhập");
//            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
//            alert.show();
//            return false;
            mgs3 = "Số điện thoại nhập không vượt quá 10 kí tự";
        }
//
        alert.setTitle("Cảnh báo đăng nhập");
        alert.setHeaderText(mgs + "\n" + mgs1 + "\n" + mgs2 + "\n" + mgs3);
        alert.show();

//        if (txtEmail.getText().length() > 50 || txtEmail.getText().length() < 1) {
//            alert.setTitle("Cảnh báo đăng nhập");
//            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
//            alert.show();
//            return false;
//        }
//        String name = txtName.getText();
//        String phone = txtPhone.getText();
//        String address = txtAddress.getText();
//        String email = txtEmail.getText();
//        String regex = "[a-zA-Z0-9_@]";
//        if (!Pattern.matches(regex, name) || !Pattern.matches(regex, address) || !Pattern.matches(regex, email)) {
//            alert.setTitle("Cảnh báo đăng nhập");
//            alert.setHeaderText("Thiing tin chỉ gồm các ký tự a-z, A-Z, 0-9, _, @");
//            alert.show();
//            return false;
//        }
        return true;
    }
}
