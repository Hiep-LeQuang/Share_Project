/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Login;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

/**
 *
 * @author lehie
 */
public class LoginController {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPass;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCancel;
    
     @FXML
    private Hyperlink btnCUAccount;

    @FXML
    void btnCUAccount(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateAccount();
    }

    @FXML
    void btnCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        if(validation()){
            Navigator.getInstance().goToMain();
        }
        
    }
    
    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(txtName.getText().equals("") || txtPass.getText().equals("")){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Không được để trống");
            alert.show();
            return false;
        }
        if(txtName.getText().length() > 30 || txtName.getText().length() < 1){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Tài khoản nhập không vượt quá 30 kí tự");
            alert.show();
            return false;
        }
        if(txtPass.getText().length() > 16 || txtPass.getText().length() < 8){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Mật khẩu nhập có chiều dài từ 8 đến 16 kí tự");
            alert.show();
            return false;
        }
        String username = txtName.getText();
        String password = txtPass.getText();
        String regex = "[a-zA-Z0-9_@]{6,}";
        if(!Pattern.matches(regex, username) || !Pattern.matches(regex, password)){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("username và pasword chỉ gồm các ký tự a-z, A-Z, 0-9, _, @");
            alert.show();
            return false;
        }
        
        return true;
    }
}
