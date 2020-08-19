/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Brand;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author lehie
 */
public class CUBrandController {

    private Brand editBrand = null;

    @FXML
    private ComboBox<String> cbxStatus;

    @FXML
    private TextField txtBrand;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnSave(ActionEvent event) throws SQLException, IOException {
        if (validation()) {
            if (editBrand == null) {
                Brand insertBrand = extractBrandFromFields();
                insertBrand = Brand.insert(insertBrand);
                Navigator.getInstance().goToMain();
            } else {
                Brand updateBrand = extractBrandFromFields();
                updateBrand.setBrandID(this.editBrand.getBrandID());

                boolean result = Brand.update(updateBrand);
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

    @FXML
    void txtBrand(ActionEvent event) {

    }

    private Brand extractBrandFromFields() {
        Brand brand = new Brand();
        brand.setBrand(txtBrand.getText());
        brand.setStatus(cbxStatus.getSelectionModel().getSelectedItem());
        return brand;
    }

    public void initialize(Brand editBrand) {
        this.editBrand = editBrand;
        String msg = "";
        if (editBrand == null) {
            msg = "Thêm mới thương hiệu";
            cbxStatus.getItems().add("Đang Kinh Doanh");
            cbxStatus.getItems().add("Ngừng Kinh Doanh");
        } else {
            msg = "Chỉnh sửa thương hiệu";
            cbxStatus.getItems().add("Đang Kinh Doanh");
            cbxStatus.getItems().add("Ngừng Kinh Doanh");
            txtBrand.setText(editBrand.getBrand());
            if (editBrand.getStatus().equals("Đang Kinh Doanh")) {
                cbxStatus.getSelectionModel().select("Đang Kinh Doanh");
            } else {
                cbxStatus.getSelectionModel().select("Ngừng Kinh Doanh");
            }
        }
    }

    String mgs1 = "";
    String mgs2 = "";
    String mgs3 = "";
    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (txtBrand.getText().isEmpty() || cbxStatus.getSelectionModel().isEmpty()) {
            mgs1 = "Không được để trống";
            return false;
        }
        if (txtBrand.getText().length() > 50 || txtBrand.getText().length() < 1) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Thương hiệu nhập không vượt quá 50 kí tự");
            alert.show();
            return false;
        }

        String username = txtBrand.getText();
        String regex = "[a-zA-Z0-9_@]{1, 50}";
        if (!Pattern.matches(regex, username)) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Thương hiệu chỉ gồm các ký tự a-z, A-Z, 0-9, _, @");
            alert.show();
            return false;
        }

        return true;
    }
}
