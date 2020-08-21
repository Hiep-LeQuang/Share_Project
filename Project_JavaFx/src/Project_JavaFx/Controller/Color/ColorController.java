/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Color;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author lehie
 */
public class ColorController {

    @FXML
    private TableView<Color> tvColor;

    @FXML
    private TableColumn<Color, String> tcColor;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateColor(null);
    }

    @FXML
    void btnSearch(ActionEvent event) {
        String input = txtSearch.getText();
        if (!input.isEmpty()) {
            ObservableList<Color> colors = Color.selectColor(input);

            tvColor.setItems(colors);

            tcColor.setCellValueFactory((Color) -> {
                return Color.getValue().getColorProperty();
            });
        } else {
            tvColor.setItems(Color.selectAll());

            tcColor.setCellValueFactory((Color) -> {
                return Color.getValue().getColorProperty();
            });
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
        Color updateColor = tvColor.getSelectionModel().getSelectedItem();

        if (updateColor == null) {
            selectedColorWarning();
        } else {
            Navigator.getInstance().goToCreateColor(updateColor);
        }
    }

    private void selectedColorWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một thương hiệu");
        alert.setHeaderText("Bạn phải chọn một thuiwng hiệu ở trong danh sách");
        alert.showAndWait();
    }

    public void initialize() {

        tvColor.setItems(Color.selectAll());

        tcColor.setCellValueFactory((Color) -> {
            return Color.getValue().getColorProperty();
        });
    }
}
