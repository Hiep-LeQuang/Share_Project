/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Car;

import Project_JavaFx.Controller.Brand.Brand;
import Project_JavaFx.Controller.Category.Category;
import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author lehie
 */
public class CarController {

    @FXML
    private TableView<Car> tvCars;

    @FXML
    private TableColumn<Car, String> tcSku;

    @FXML
    private TableColumn<Car, String> tcProduct;

    @FXML
    private TableColumn<Car, String> tcBrand;

    @FXML
    private TableColumn<Car, String> tcCategory;

    @FXML
    private TableColumn<Car, Integer> tcYear;

    @FXML
    private TableColumn<Car, String> tcGear;

    @FXML
    private TableColumn<Car, String> tcColor;

    @FXML
    private TableColumn<Car, Integer> tcPrice;

    @FXML
    private TableColumn<Car, String> tcStatus;

    @FXML
    private Pane secPane;

    @FXML
    private MenuButton MbCategory;

    @FXML
    private MenuButton MbBrand;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnSearch(ActionEvent event) {
        String input = txtSearch.getText();
        if (!input.isEmpty()) {
            ObservableList<Car> cars = Car.selectCarsByName(input);

            loadTable(cars);
        } else {
            loadTable(Car.selectAll());
        }

    }

    @FXML
    void btnDetails(ActionEvent event) throws IOException, SQLException {
        Car ShowCar = tvCars.getSelectionModel().getSelectedItem();
        if (ShowCar == null) {
            selectedCarWarning();
        } else {
            Navigator.getInstance().goToDetailsCar(ShowCar);
        }

    }

    @FXML
    void btnCreate(ActionEvent event) throws IOException, SQLException {
        Navigator.getInstance().goToCreateCar(null);
    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException, SQLException {
        Car updateCar = tvCars.getSelectionModel().getSelectedItem();
        if (updateCar == null) {
            selectedCarWarning();
        } else {
            Navigator.getInstance().goToCreateCar(updateCar);
        }
    }

    @FXML
    void btnStatus(ActionEvent event) {
        Car updateStatus = tvCars.getSelectionModel().getSelectedItem();

        if (updateStatus == null) {
            selectedCarWarning();
        } else {
            if (updateStatus.getStatus().equals("Dừng Bán")) {

                updateStatus.setStatus("Đang Bán");
            } else {
                updateStatus.setStatus("Dừng Bán");
            }
            Car.update(updateStatus);
        }
    }

    private void selectedCarWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một xe");
        alert.setHeaderText("Bạn phải chọn một xe ở trong danh sách");
        alert.showAndWait();
    }

    @FXML
    void btnHonda(ActionEvent event) {

    }

    @FXML
    void btnToyota(ActionEvent event) {

    }

    @FXML
    void btnVinfat(ActionEvent event) {

    }

    public void initialize() {

        loadTable(Car.selectAll());

        ObservableList<Brand> brands = Brand.selectAll();
        MbBrand.getItems().removeAll();
        for (Brand brand : brands) {
            if (brand.getStatus() == "Đang Kinh Doanh") {
                MenuItem item = new MenuItem(brand.getBrand());
                item.setOnAction(a -> {
                    ObservableList<Car> cars = Car.getCarByBrand(brand.getBrandID());
                    loadTable(cars);
                });
                MbBrand.getItems().add(item);
            }
        }

        ObservableList<Category> categorys = Category.selectAll();

        MbCategory.getItems().removeAll();
        for (Category category : categorys) {
            if (category.getStatus() == "Đang Kinh Doanh") {
                MenuItem item = new MenuItem(category.getCategory());
                item.setOnAction(a -> {
                    ObservableList<Car> cars = Car.selectCategory(category.getCategoryID());
                    loadTable(cars);
                });
                MbCategory.getItems().add(item);
            }
        }
    }

    void loadTable(ObservableList<Car> cars) {
        tvCars.setItems(cars);

        tcSku.setCellValueFactory((Car) -> {
            return Car.getValue().getSkuProperty();
        });

        tcProduct.setCellValueFactory((Car) -> {
            return Car.getValue().getCarNameProperty();
        });
        tcBrand.setCellValueFactory((Car) -> {
            return Car.getValue().getBrandProperty();
        });
        tcCategory.setCellValueFactory((Car) -> {
            return Car.getValue().getCategoryProperty();
        });
        tcYear.setCellValueFactory((Car) -> {
            return Car.getValue().getYearOfManufactureProperty();
        });
        tcPrice.setCellValueFactory((Car) -> {
            return Car.getValue().getPriceProperty();
        });
        tcGear.setCellValueFactory((Car) -> {
            return Car.getValue().getGearProperty();
        });
        tcColor.setCellValueFactory((Car) -> {
            return Car.getValue().getColorProperty();
        });
        tcStatus.setCellValueFactory((Car) -> {
            return Car.getValue().getStatusProperty();
        });
    }
}
