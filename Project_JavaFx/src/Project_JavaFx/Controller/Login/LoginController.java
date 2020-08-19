/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Login;

import Project_JavaFx.Controller.Account.Account;
import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    void btnCancel(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void btnLogin(ActionEvent event) throws IOException {
        if (validation()) {
            String name = txtName.getText();
            String pass = txtPass.getText();

            pass = cryptWithMD5(pass);

            if (Account.checkAccount(name, pass) != null) {
                Navigator.getInstance().goToMain();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh báo đăng nhập");
                alert.setHeaderText("Tài khoản hoặc mật khẩu không chính xác, vuio lòng kiểm tra lại");
                alert.show();
            }
        }

    }

    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String msg = "";
        if (txtName.getText().isEmpty() || txtPass.getText().isEmpty()) {
            msg += "Không được để trống";
        } else {
            if (txtName.getText().length() > 30 || txtName.getText().length() < 1) {
                msg += "Tài khoản nhập không vượt quá 30 kí tự" + "\n";
            }
            if (txtPass.getText().length() > 16 || txtPass.getText().length() < 8) {
                msg += "Mật khẩu nhập có chiều dài từ 8 đến 16 kí tự" + "\n";

            }
            String username = txtName.getText();
            String password = txtPass.getText();
            String regex = "[a-zA-Z0-9_@]{1,}";
            if (!Pattern.matches(regex, username) || !Pattern.matches(regex, password)) {
                msg += "username và pasword chỉ gồm các ký tự a-z, A-Z, 0-9, _";
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

    public String cryptWithMD5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());

        }
        return null;

    }
}
