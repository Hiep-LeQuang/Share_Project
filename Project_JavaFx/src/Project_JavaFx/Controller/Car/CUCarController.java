/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Car;

import Project_JavaFx.Controller.DbService;
import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author lehie
 */
public class CUCarController {

    private Car editCar = null;

    @FXML
    private TextField txtCarName;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<String> cbxBrand;

    @FXML
    private Button btnCancel;

    @FXML
    private ImageView btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cbxCategory;

    @FXML
    private ComboBox<String> cbxFuel;

    @FXML
    private ComboBox<String> cbxColor;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtSku;

    @FXML
    private ComboBox<String> cbxSeat;

    @FXML
    private ComboBox<String> cbxGear;

    @FXML
    private ComboBox<String> cbxStatus;

    @FXML
    void cbxBrand(ActionEvent event) {

    }

    @FXML
    void cbxCategory(ActionEvent event) {

    }

    @FXML
    void cbxColor(ActionEvent event) {

    }

    @FXML
    void cbxFuel(ActionEvent event) {

    }

    @FXML
    void cbxGear(ActionEvent event) {

    }

    @FXML
    void cbxSeat(ActionEvent event) {

    }

    @FXML
    void cbxStatus(ActionEvent event) {

    }

    @FXML
    void txtCarName(ActionEvent event) {

    }

    @FXML
    void txtPrice(ActionEvent event) {

    }

    @FXML
    void txtSku(ActionEvent event) {

    }

    @FXML
    void txtYear(ActionEvent event) {

    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
        Navigator.getInstance().goToMain();
    }

    @FXML
    void btnSave(ActionEvent event) throws IOException, SQLException {
        if (editCar == null) {
            Car insertCar = extractCarFromFields();
            insertCar = Car.insert(insertCar);
            Navigator.getInstance().goToMain();
        } else {
            Car updateCar = extractCarFromFields();
            updateCar.setCarID(this.editCar.getCarID());
            
            boolean result = Car.update(updateCar);
            Alert alert = new Alert(Alert.AlertType.NONE);
            if (result) {
                alert.setHeaderText("Cập nhật thương hiệu thành công");
            } else {
                alert.setHeaderText("Cập nhật thương hiệu không thành công");
            }
            Navigator.getInstance().goToMain();
        }
    }

    private Car extractCarFromFields() {
        Car car = new Car();
        car.setSku(txtSku.getText());
        car.setCarName(txtCarName.getText());
        car.setYearOfManufacture(Integer.parseInt(txtYear.getText()));
        car.setPrice(Integer.parseInt(txtPrice.getText()));
        car.setSeat(cbxSeat.getSelectionModel().getSelectedItem());
        car.setFuelUsed(cbxFuel.getSelectionModel().getSelectedItem());
        car.setStatus(cbxStatus.getSelectionModel().getSelectedItem());
        
        String brand = cbxBrand.getSelectionModel().getSelectedItem();
        if(Car.getBrandID(brand) != 0){
            car.setBrandID(Car.getBrandID(brand));
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Khong co thuong hieu!");
            
        }
        
        String categoryName = cbxCategory.getSelectionModel().getSelectedItem();
        if(Car.getCategoryID(categoryName) != 0){
            car.setCategoryID(Car.getCategoryID(categoryName));
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Khong co loai san pham!");
            
        }
        
        String color = cbxColor.getSelectionModel().getSelectedItem();
        if(Car.getColorID(color) != 0){
            car.setColorID(Car.getColorID(color));
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Khong co mau!");
            
        }
        
        car.setGear(cbxGear.getSelectionModel().getSelectedItem());
        return car;
    }

    public void initialize(Car editCar) throws SQLException {
        this.editCar = editCar;
        String msg = "";
        if (editCar == null) {
            msg = "Thêm mới xe";
            cbxStatus.getItems().add("Đang Bán");
            cbxStatus.getItems().add("Dừng Bán");
            cbxFuel.getItems().add("Xăng");
            cbxFuel.getItems().add("Diesel");
            cbxSeat.getItems().add("2");
            cbxSeat.getItems().add("5");
            cbxSeat.getItems().add("7");
            cbxGear.getItems().add("Số sàn/Số tay");
            cbxGear.getItems().add("Tự động");
            cbxGear.getItems().add("Tự động vô cấp");
            cbxGear.getItems().add("Ly hợp kép");
        } else {
            msg = "Chỉnh sửa xe";
            cbxStatus.getItems().add("Đang Bán");
            cbxStatus.getItems().add("Dừng Bán");
            cbxFuel.getItems().add("Xăng");
            cbxFuel.getItems().add("Diesel");
            cbxSeat.getItems().add("2");
            cbxSeat.getItems().add("5");
            cbxSeat.getItems().add("7");
            cbxGear.getItems().add("Số sàn/Số tay");
            cbxGear.getItems().add("Tự động");
            cbxGear.getItems().add("Tự động vô cấp");
            cbxGear.getItems().add("Ly hợp kép");

            txtSku.setText(editCar.getSku());
            txtCarName.setText(editCar.getCarName());
            txtYear.setText(editCar.getYearOfManufacture().toString());
            txtPrice.setText(editCar.getPrice().toString());
            cbxBrand.getSelectionModel().select(editCar.getBrand());
            
            cbxCategory.getSelectionModel().select(editCar.getCategory());
            
            cbxColor.getSelectionModel().select(editCar.getColor());
            
            if (editCar.getStatus().equals("Đang Bán")) {
                cbxStatus.getSelectionModel().select("Đang Bán");
            } else {
                cbxStatus.getSelectionModel().select("Dừng Bán");
            }

            if (editCar.getFuelUsed().equals("Xăng")) {
                cbxFuel.getSelectionModel().select("Xăng");
            } else {
                cbxFuel.getSelectionModel().select("Diesel");
            }

            if (editCar.getSeat().equals("2")) {
                cbxSeat.getSelectionModel().select("2");
            }else if(editCar.getSeat().equals("5")) {
                cbxSeat.getSelectionModel().select("5");
            }else{
                cbxSeat.getSelectionModel().select("7");
                    }

            if (editCar.getGear().equals("Số sàn/Số tay")) {
                cbxGear.getSelectionModel().select("Hộp số sàn/Số tay");
            } else if(editCar.getGear().equals("Tự động")) {
                cbxGear.getSelectionModel().select("Tự động vô cấp");
            } else if(editCar.getGear().equals("Tự động vô cấp")) {
                cbxGear.getSelectionModel().select("Tự động");
            } else {
                cbxGear.getSelectionModel().select("Ly hợp kép");
            }
        }
            
        try (Connection connection = DbService.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from brand");
            while (rs.next()) {
                cbxBrand.getItems().add(rs.getString("brand"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        try (Connection connection = DbService.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from category");
            while (rs.next()) {
                cbxCategory.getItems().add(rs.getString("categoryName"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        try (Connection connection = DbService.getConnection()) {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from color");
            while (rs.next()) {
                cbxColor.getItems().add(rs.getString("color"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    

private boolean validation() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(txtCarName.getText().equals("") || txtPrice.getText().equals("") || txtSku.getText().equals("") || txtYear.getText().equals("")){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Không được để trống");
            alert.show();
            return false;
        }
        if(txtCarName.getText().length() > 50 || txtCarName.getText().length() < 1){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
            alert.show();
            return false;
        }
        
        if(txtPrice.getText().length() > 50 || txtPrice.getText().length() < 1){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
            alert.show();
            return false;
        }
        
        if(txtSku.getText().length() > 50 || txtSku.getText().length() < 1){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
            alert.show();
            return false;
        }
        
        if(txtYear.getText().length() > 50 || txtYear.getText().length() < 1){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại nhập không vượt quá 30 kí tự");
            alert.show();
            return false;
        }

        String name = txtCarName.getText();
        String price = txtPrice.getText();
        String sku = txtSku.getText();
        String year = txtYear.getText();
        String regex = "[a-zA-Z0-9_@]{6,}";
        if(!Pattern.matches(regex, name) || !Pattern.matches(regex, price) || !Pattern.matches(regex, sku) || !Pattern.matches(regex, year)){
            alert.setTitle("Cảnh báo đăng nhập");
            alert.setHeaderText("Phân loại chỉ gồm các ký tự a-z, A-Z, 0-9, _, @");
            alert.show();
            return false;
        }
        
        return true;
    }}
