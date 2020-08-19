/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Account;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class CUAccountController {

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPass;

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnSave(ActionEvent event) throws SQLException, IOException {
        if (validation()) {
            
            Account insertAccount = extractAccountFromFields();
            insertAccount = Account.insert(insertAccount);
            Navigator.getInstance().goToMain();
        }
    }

    private Account extractAccountFromFields() {
        Account account = new Account();
        account.setUserName(txtUserName.getText());
        account.setPassword(cryptWithMD5(txtPass.getText()));
        return account;
    }

    @FXML
    void txtPass(ActionEvent event) {

    }

    @FXML
    void txtUserName(ActionEvent event) {

    }

    private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        String msg = "";
        if (txtUserName.getText().isEmpty() || txtPass.getText().isEmpty()) {
            msg += "Các trường giá trị không được để trống";
        } else {
            if(Account.checkUser(txtUserName.getText())){
                msg += "Tên tài khoản đã được tạo,vui lòng đổi lại" + "\n";
            }

            if (txtUserName.getText().length() > 30) {
                msg += "Tài khoản nhập không vượt quá 30 kí tự" + "\n";
            }
            if (txtPass.getText().length() > 16 || txtPass.getText().length() < 8) {
                msg += "Mật khẩu nhập có chiều dài từ 8 đến 16 kí tự" + "\n";
            }

            String username = txtUserName.getText();
            String password = txtPass.getText();
            String regex = "[a-zA-Z0-9_]{1,}";
            if (!Pattern.matches(regex, username) || !Pattern.matches(regex, password)) {
                msg += "Tài khoản và mật khẩu chỉ gồm các ký tự a-z, A-Z, 0-9, _" + "\n";
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
