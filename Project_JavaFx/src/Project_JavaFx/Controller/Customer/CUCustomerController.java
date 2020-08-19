/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Customer;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.SQLException;
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
    private TextField txtCmnd;

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

                if (Customer.getCustomer(insertCustomer.getCmnd()) == null) {
                    insertCustomer = Customer.insert(insertCustomer);
                    Navigator.getInstance().goToMain();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Chứng minh nhân dân hoặc thẻ căn cước đã tồn tại");
                    alert.show();
                }

            } else {
                Customer updateCustomer = extractCustomerFromFields();
                updateCustomer.setCustomerID(this.editCustomer.getCustomerID());

                if (!updateCustomer.getCmnd().equals(editCustomer.getCmnd())) {
                    if (Customer.getCustomer(updateCustomer.getCmnd()) == null) {
                        boolean result = Customer.update(updateCustomer);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        if (result) {
                            alert.setHeaderText("Cập nhật khách hàng thành công");
                            alert.show();
                        } else {
                            alert.setHeaderText("Cập nhật khách hàng không thành công");
                            alert.show();
                        }
                        Navigator.getInstance().goToMain();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Chứng minh nhân dân hoặc thẻ căn cước đã tồn tại");
                        alert.show();
                    }

                } else {
                    boolean result = Customer.update(updateCustomer);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    if (result) {
                        alert.setHeaderText("Cập nhật khách hàng thành công");
                        alert.show();
                    } else {
                        alert.setHeaderText("Cập nhật khách hàng không thành công");
                        alert.show();
                    }
                    Navigator.getInstance().goToMain();

                }

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
    void txtCmnd(ActionEvent event) {

    }

    @FXML
    void txtPhone(ActionEvent event) {

    }

    private Customer extractCustomerFromFields() {
        Customer customer = new Customer();
        customer.setCustomerName(txtName.getText());
        customer.setCmnd(txtCmnd.getText());
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
            txtCmnd.setText(editCustomer.getCmnd());
            txtPhone.setText(editCustomer.getPhone());
            txtAddress.setText(editCustomer.getAddress());
            txtEmail.setText(editCustomer.getEmail());
        }
    }

    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String msg = "";
        if (txtName.getText().isEmpty() || txtCmnd.getText().isEmpty() || txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            msg = "Không được để trống";
        } else {

            if (txtCmnd.getText().length() != 12) {

                msg += "Số chứng minh thư hoặc căn cước công dân nhập phải 12 số" + "\n";
            }

            if (txtName.getText().length() > 50) {

                msg += "Tên khách hàng nhập không vượt quá 50 kí tự" + "\n";
            }

            if (txtPhone.getText().length() != 10) {

                msg += "Số điện thoại nhập phải 10 số" + "\n";
            }

            if (txtAddress.getText().length() > 100) {

                msg += "Địa chỉ nhập không vượt quá 100 kí tự" + "\n";
            }

            if (txtEmail.getText().length() > 50) {
                msg += "Email nhập không vượt quá 50 kí tự" + "\n";
            }

            String cmnd = txtCmnd.getText();
            String name = txtName.getText();
            String phone = txtPhone.getText();
            String address = txtAddress.getText();
            String email = txtEmail.getText();

            String regex = "[0-9]{1,}";
            if (!Pattern.matches(regex, cmnd)) {
                msg += "Số chứng minh thư hoặc căn cước công dân chỉ gồm các số từ 0-9" + "\n";
            }

            if (!Pattern.matches(regex, phone)) {
                msg += "Số điện thoại chỉ gồm các số từ 0-9" + "\n";
            }

            String regex1 = "[a-zA-ZÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ ]{1,50}";
            if (!Pattern.matches(regex1, name)) {
                msg += "Tên khách hàng chỉ gồm các ký tự a-z, A-Z" + "\n";
            }

            String regex2 = "[a-zA-ZÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ0-9_@ ]{1,}";
            if (!Pattern.matches(regex2, address)) {
                msg += "Địa chỉ gồm các ký tự a-z, A-Z, 0-9, _, @" + "\n";
            }

            String regex3 = "[a-z][a-z0-9_\\.]{1,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}";
            if (!Pattern.matches(regex3, email)) {
                msg += "Email chỉ gồm các ký tự a-z, A-Z, 0-9, _, @, .  Ví dụ : abc@gmail.com" + "\n";
            }
        }

        if (!msg.isEmpty()) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText(msg);
            alert.show();
            return false;
        }
        return true;
    }
}
