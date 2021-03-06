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

                if (!updateCategory.getCategory().equals(editCategory.getCategory())) {
                    if (!Category.checkCategory(updateCategory.getCategory())) {
                        boolean result = Category.update(updateCategory);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        if (result) {
                            alert.setHeaderText("Cập nhật phân loại thành công");
                            alert.show();
                        } else {
                            alert.setHeaderText("Cập nhật phân loại không thành công");
                            alert.show();
                        }
                        Navigator.getInstance().goToMain();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Phân loại đã tồn tại, vui lòng kiểm tra lại");
                        alert.show();
                    }

                } else {
                    boolean result = Category.update(updateCategory);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    if (result) {
                        alert.setHeaderText("Cập nhật phân loại thành công");
                        alert.show();
                    } else {
                        alert.setHeaderText("Cập nhật phân loại không thành công");
                        alert.show();
                    }
                    Navigator.getInstance().goToMain();

                }

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
        String msg = "";
        if (txtCategory.getText().isEmpty() || cbxStatus.getSelectionModel().isEmpty()) {
            msg += "Không được để trống";
        } else {

            if (txtCategory.getText().length() > 50) {
                msg += "Phân loại nhập không vượt quá 50 kí tự" + "\n";
            }

            String categoryName = txtCategory.getText();
            String regex = "[a-zA-ZÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ0-9_@ ]{1,}";
            if (!Pattern.matches(regex, categoryName)) {
                msg += "Phân loại chỉ gồm các ký tự a-z, A-Z, 0-9, _, @, " + "\n";
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
