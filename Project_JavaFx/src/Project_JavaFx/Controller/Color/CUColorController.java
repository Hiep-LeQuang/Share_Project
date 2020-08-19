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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if (result) {
                    alert.setHeaderText("Cập nhật màu sắc thành công");
                    alert.show();
                } else {
                    alert.setHeaderText("Cập nhật màu sắc không thành công");
                    alert.show();
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
        String msg = "";
        if (txtColor.getText().isEmpty()) {
            msg += "Không được để trống";
        } else {
            if(Color.checkColor(txtColor.getText())){
                msg = "Màu sắc nhập đã tồn tại, vui lòng kiểm tra lại" + "\n";
            }
            
            if (txtColor.getText().length() > 50) {
                msg = "Màu sắc nhập không vượt quá 50 kí tự" + "\n";
            }

            String color = txtColor.getText();
            String regex = "[a-zA-ZÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶEÉÈẺẼẸÊẾỀỂỄỆIÍÌỈĨỊOÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢUÚÙỦŨỤƯỨỪỬỮỰYÝỲỶỸỴĐaáàảãạâấầẩẫậăắằẳẵặeéèẻẽẹêếềểễệiíìỉĩịoóòỏõọôốồổỗộơớờởỡợuúùủũụưứừửữựyýỳỷỹỵđ ]{1,50}";
            if (!Pattern.matches(regex, color)) {
                msg += "Màu xe chỉ gồm các ký tự a-z, A-Z" + "\n";
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
