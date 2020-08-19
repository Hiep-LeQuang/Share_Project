/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Color;

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
public class CUColorController {

    private Color editColor = null;

    @FXML
    private TextField txtColor;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnSave(ActionEvent event) throws IOException, SQLException {
        if (validation()) {
            if (editColor == null) {
                Color insertColor = extractColorFromFields();
                insertColor = Color.insert(insertColor);
                Navigator.getInstance().goToMain();
            } else {
                Color updateColor = extractColorFromFields();
                updateColor.setColorID(this.editColor.getColorID());

                boolean result = Color.update(updateColor);
                Alert alert = new Alert(Alert.AlertType.NONE);
                if (result) {
                    alert.setHeaderText("Cập nhật màu sắc thành công");
                } else {
                    alert.setHeaderText("Cập nhật màu sắc không thành công");
                }
                Navigator.getInstance().goToMain();
            }
        }
    }

    @FXML
    void txtColor(ActionEvent event) {

    }

    private Color extractColorFromFields() {
        Color color = new Color();
        color.setColor(txtColor.getText());
        return color;
    }

    public void initialize(Color editColor) {
        this.editColor = editColor;
        String msg = "";
        if (editColor == null) {
            msg = "Thêm mới màu xe";
        } else {
            msg = "Chỉnh sửa màu xe";
            txtColor.setText(editColor.getColor());
        }
    }

    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (txtColor.getText().equals("")) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Không được để trống");
            alert.show();
            return false;
        }
        if (txtColor.getText().length() > 50 || txtColor.getText().length() < 1) {
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại nhập không vượt quá 50 kí tự");
            alert.show();
            return false;
        }

        String username = txtColor.getText();
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
