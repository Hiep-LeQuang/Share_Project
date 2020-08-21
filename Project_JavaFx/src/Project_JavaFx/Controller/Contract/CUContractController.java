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
import java.time.LocalDateTime;
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
    private TextField txtCmnd;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtPrice;

    @FXML
    private DatePicker txtRD;

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
        if (validation()) {
            if (editContract == null) {
                Customer insertCustomer = extractCustomerFromFields();
                Contract insertContract = extractContractFromFields();

                if (Customer.getCustomer(txtCmnd.getText()) == null) {
                    insertCustomer = Customer.insert(insertCustomer);
                }else{
                    insertCustomer = Customer.getCustomer(txtCmnd.getText());
                }

                insertContract.setCustomerID(insertCustomer.getCustomerID());
                insertContract = Contract.insert(insertContract);
                Navigator.getInstance().goToMain();

            } else {
                Contract updateContract = extractContractFromFields();
                updateContract.setContractID(this.editContract.getContractID());

                Customer updateCustomer = extractCustomerFromFields();
                updateCustomer.setCustomerID(editContract.getCustomerID());

                if (!updateCustomer.getCmnd().equals(editContract.getCmnd())) {
                    if (Customer.getCustomer(updateCustomer.getCmnd()) == null) {
                        boolean result1 = Customer.update(updateCustomer);
                        boolean result2 = Contract.update(updateContract);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        if (result1 && result2) {
                            alert.setHeaderText("Cập nhật hợp đồng thành công");
                            alert.show();
                        } else {
                            alert.setHeaderText("Cập nhật hợp đồng không thành công");
                            alert.show();
                        }
                        Navigator.getInstance().goToMain();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Chứng minh nhân dân hoặc thẻ căn cước đã tồn tại");
                        alert.show();
                    }

                } else {
                    boolean result1 = Customer.update(updateCustomer);
                    boolean result2 = Contract.update(updateContract);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    if (result1 && result2) {
                        alert.setHeaderText("Cập nhật hợp đồng thành công");
                        alert.show();
                    } else {
                        alert.setHeaderText("Cập nhật hợp đồng không thành công");
                        alert.show();
                    }
                    Navigator.getInstance().goToMain();
                }
            }
        }
    }

    private Customer extractCustomerFromFields() {
        Customer customer = new Customer();
        customer.setCustomerName(txtCustomer.getText());
        customer.setCmnd(txtCmnd.getText());
        customer.setPhone(txtPhone.getText());
        customer.setAddress(txtAddress.getText());
        customer.setEmail(txtEmail.getText());
        return customer;
    }

    private Contract extractContractFromFields() {
        Contract contract = new Contract();
        contract.setPrice(Integer.parseInt(txtPrice.getText()));
        LocalDate date = txtRD.getValue();
        LocalDateTime now = LocalDateTime.now();
        contract.setDateOfSale(now.toString());
        contract.setDeposits(Integer.parseInt(txtDeposits.getText()));
        contract.setProductReceiptDate(txtRD.getValue().toString());

        contract.setAccountant(txtAccountant.getText());
        contract.setNote(txtNote.getText());

        contract.setSku(cbxSku.getSelectionModel().getSelectedItem());
        contract.setStatus(cbxStatus.getSelectionModel().getSelectedItem());

        String seri = cbxSku.getSelectionModel().getSelectedItem();
        if (contract.getSku()!= null) {
            contract.setCarID(contract.getCarID(seri));
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

    @FXML
    void txtCmnd(ActionEvent event) {

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
                String seri = newValue;
                String carName = Contract.getCar(seri);
                txtNameCar.setText(carName);

            });

            txtCmnd.textProperty().addListener((observable, oldValue, newValue) -> {
                String cmnd = newValue;
                Customer u = Customer.getCustomer(cmnd);
                if (u != null) {
                    txtCustomer.setText(u.getCustomerName());
                    txtPhone.setText(u.getPhone());
                    txtAddress.setText(u.getAddress());
                    txtEmail.setText(u.getEmail());
                }
            });

        } else {
            msg = "Chỉnh sửa hợp đồng";
            cbxStatus.getItems().add("Chờ Lấy Hàng");
            cbxStatus.getItems().add("Đã Giao");
            txtPrice.setText(editContract.getPrice().toString());
            txtDeposits.setText(editContract.getDeposits().toString());
            txtRD.setValue(LocalDate.parse(editContract.getProductReceiptDate()));
            txtAccountant.setText(editContract.getAccountant());
            txtNote.setText(editContract.getNote());
            cbxSku.getSelectionModel().select(editContract.getSku());
            txtNameCar.setText(editContract.getCarName());
            txtNameCar.setDisable(true);

            cbxSku.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                String seri = newValue;
                String carName = Contract.getCar(seri);
                txtNameCar.setText(carName);

            });

            txtCustomer.setText(editContract.getCustomerName());
            txtCmnd.setText(editContract.getCmnd());
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
                cbxSku.getItems().add(rs.getString("seri"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String msg = "";

        if (txtAccountant.getText().isEmpty() || txtAddress.getText().equals("") || txtCmnd.getText().equals("") || txtCustomer.getText().equals("") || txtDeposits.getText().equals("") || txtEmail.getText().equals("") || txtNameCar.getText().equals("") || txtRD.getValue() == null || txtPhone.getText().isEmpty() || txtPrice.getText().isEmpty() || cbxSku.getSelectionModel().isEmpty() || cbxStatus.getSelectionModel().isEmpty()) {
            msg += "Không được để trống" + "\n";
        } else {

            String accountant = txtAccountant.getText();
            String price = txtPrice.getText();
            String deposits = txtDeposits.getText();
            String note = txtNote.getText();
            String cmnd = txtCmnd.getText();
            String name = txtCustomer.getText();
            String phone = txtPhone.getText();
            String address = txtAddress.getText();
            String email = txtEmail.getText();

            String regex = "[0-9]{1,10}";
            String regex5 = "[0-9]{1,}";

            if (!Pattern.matches(regex, price)) {
                msg += "Giá sản phẩm chỉ gồm các số từ 0-9 và dài không quá 10 kí tự" + "\n";
            } else {               
                if (Integer.parseInt(txtPrice.getText()) > 500000) {
                    msg += "Giá xe nhập không vượt quá 500000 $" + "\n";
                }
            }

            if (!Pattern.matches(regex, deposits)) {
                msg += "Tiền đặt cọc chỉ gồm các số từ 0-9" + "\n";
            } else {
                if (Integer.parseInt(txtDeposits.getText()) > 500000) {
                    msg += "Tiền đặt cọc nhập không vượt quá 500000 $" + "\n";
                }
            }

            String regex4 = "[0-9]{1,}";
            if (!Pattern.matches(regex4, cmnd)) {
                msg += "Số chứng minh thư hoặc căn cước công dân chỉ gồm các số từ 0-9" + "\n";
            } else {
                if (txtCmnd.getText().length() != 12) {

                    msg = "Số chứng minh thư hoặc căn cước công dân nhập phải 12 số" + "\n";
                }
            }

            if (!Pattern.matches(regex5, phone)) {
                msg += "Số điện thoại chỉ gồm các số từ 0-9" + "\n";
            } else {
                if (txtPhone.getText().length() != 10) {

                    msg = "Số điện thoại nhập phải 10 số" + "\n";
                }
            }

            String regex1 = "[a-zA-ZÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ ]{1,}";

            if (!Pattern.matches(regex1, accountant)) {
                msg += "Tên kế toán chỉ gồm các ký tự a-z, A-Z" + "\n";
            } else {
                if (txtAccountant.getText().length() > 50) {
                    msg += "Tên nhập không vượt quá 50 kí tự" + "\n";
                }
            }

            if (!Pattern.matches(regex1, name)) {
                msg += "Tên khách hàng chỉ gồm các ký tự a-z, A-Z" + "\n";
            } else {
                if (txtCustomer.getText().length() > 50) {

                    msg = "Tên khách hàng nhập không vượt quá 50 kí tự" + "\n";
                }
            }

            String regex2 = "[a-zA-Z0-9_@ÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ ]{1,}";

                if (txtNote.getText().length() > 255) {
                    msg += "Ghi chú nhập không vượt quá 255 kí tự" + "\n";
                }

            if (!Pattern.matches(regex2, address)) {
                msg += "Địa chỉ gồm các ký tự a-z, A-Z, 0-9, _, @" + "\n";
            } else {
                if (txtAddress.getText().length() > 100) {

                    msg = "Địa chỉ nhập không vượt quá 100 kí tự" + "\n";
                }
            }

            String regex3 = "[a-z][a-z0-9_\\.]{1,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}";

            if (!Pattern.matches(regex3, email)) {
                msg += "Email chỉ gồm các ký tự a-z, A-Z, 0-9, _, @, .  Ví dụ : abc@gmail.com" + "\n";
            } else {
                if (txtEmail.getText().length() > 30) {
                    msg += "Email nhập không vượt quá 30 kí tự" + "\n";
                }
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
