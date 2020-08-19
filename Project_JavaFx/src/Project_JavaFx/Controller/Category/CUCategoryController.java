/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Category;

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
public class CUCategoryController {

    private Category editCategory = null;

    @FXML
    private ComboBox<String> cbxStatus;

    @FXML
    private TextField txtCategory;

    @FXML
    void cbxStatus(ActionEvent event) {

    }

    @FXML
    void txtCategory(ActionEvent event) {

    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnSave(ActionEvent event) throws SQLException, IOException {
        if (validation()) {
            if (editCategory == null) {
                Category insertCategory = extractCategoryFromFields();
                insertCategory = Category.insert(insertCategory);
                Navigator.getInstance().goToMain();
            } else {
                Category updateCategory = extractCategoryFromFields();
                updateCategory.setCategoryID(this.editCategory.getCategoryID());

                boolean result = Category.update(updateCategory);
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

    private Category extractCategoryFromFields() {
        Category category = new Category();
        category.setCategory(txtCategory.getText());
        category.setStatus(cbxStatus.getSelectionModel().getSelectedItem());
        return category;
    }

    public void initialize(Category editCategory) {
        this.editCategory = editCategory;
        String msg = "";
        if (editCategory == null) {
            msg = "Thêm mới thương hiệu";
            cbxStatus.getItems().add("Đang Kinh Doanh");
            cbxStatus.getItems().add("Ngừng Kinh Doanh");
        } else {
            msg = "Chỉnh sửa thương hiệu";
            cbxStatus.getItems().add("Đang Kinh Doanh");
            cbxStatus.getItems().add("Ngừng Kinh Doanh");
            txtCategory.setText(editCategory.getCategory());
            if (editCategory.getStatus().equals("Đang Kinh Doanh")) {
                cbxStatus.getSelectionModel().select("Đang Kinh Doanh");
            } else {
                cbxStatus.getSelectionModel().select("Ngừng Kinh Doanh");
            }
        }
    }

    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (txtCategory.getText().equals("") || cbxStatus.getSelectionModel().isEmpty()) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Không được để trống");
            alert.show();
            return false;
        }
        if (txtCategory.getText().length() > 50 || txtCategory.getText().length() < 1) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
            alert.show();
            return false;
        }

        String username = txtCategory.getText();
        String regex = "[a-zA-Z0-9_@]{1, 50}";
        if (!Pattern.matches(regex, username)) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại chỉ gồm các ký tự a-z, A-Z, 0-9, _, @");
            alert.show();
            return false;
        }

        return true;
    }

}
