/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project_JavaFx.Controller.Main;

import Project_JavaFx.Controller.Navigator;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author lehie
 */
public class Main_Project extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Navigator.getInstance().setStage(primaryStage);
        Navigator.getInstance().getStage().setTitle("Quản Lý Bán Ô Tô");
        Navigator.getInstance().goToLogin();
        Navigator.getInstance().getStage().show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
