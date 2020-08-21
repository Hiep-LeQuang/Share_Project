/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Category;

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
public class CategoryController {

    @FXML
    private TableView<Category> tvCategory;

    @FXML
    private TableColumn<Category, String> tcCategory;

    @FXML
    private TableColumn<Category, String> tcStatus;

    @FXML
    private TextField txtSearch;

    @FXML
    void btnCreate(ActionEvent event) throws IOException {
        Navigator.getInstance().goToCreateCategory(null);
    }

    @FXML
    void btnStatus(ActionEvent event) {
        Category updateStatus = tvCategory.getSelectionModel().getSelectedItem();

        if (updateStatus == null) {
            selectedCategoryWarning();
        } else {
            if (updateStatus.getStatus().equals("Đang Kinh Doanh")) {

                updateStatus.setStatus("Ngừng Kinh Doanh");
            } else {
                updateStatus.setStatus("Đang Kinh Doanh");
            }
            Category.update(updateStatus);
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) throws IOException {
        Category updateCategory = tvCategory.getSelectionModel().getSelectedItem();

        if (updateCategory == null) {
            selectedCategoryWarning();
        } else {
            Navigator.getInstance().goToCreateCategory(updateCategory);
        }
    }

    @FXML
    void btnSearch(ActionEvent event) {
        String input = txtSearch.getText();
        if (!input.isEmpty()) {
            ObservableList<Category> categorys = Category.selectCategory(input);

            tvCategory.setItems(categorys);

            tcCategory.setCellValueFactory((Category) -> {
                return Category.getValue().getCategoryProperty();
            });

            tcStatus.setCellValueFactory((Category) -> {
                return Category.getValue().getStatusProperty();
            });
        } else {
            tvCategory.setItems(Category.selectAll());

            tcCategory.setCellValueFactory((Category) -> {
                return Category.getValue().getCategoryProperty();
            });

            tcStatus.setCellValueFactory((Category) -> {
                return Category.getValue().getStatusProperty();
            });
        }
    }

    private void selectedCategoryWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vui lòng chọn một hợp đồng");
        alert.setHeaderText("Bạn phải chọn một hợp đồng ở trong danh sách");
        alert.showAndWait();
    }

    public void initialize() {

        tvCategory.setItems(Category.selectAll());

        tcCategory.setCellValueFactory((Category) -> {
            return Category.getValue().getCategoryProperty();
        });

        tcStatus.setCellValueFactory((Category) -> {
            return Category.getValue().getStatusProperty();
        });
    }
}
