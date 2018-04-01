package main;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class mainC extends Application
{
    @FXML
    public SplitPane content;

    @Override
    public void start(Stage stage) throws Exception
    {
       /* Parent root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("InnLogix Login");
        stage.setResizable(false);
        stage.show();*/

        Parent root = FXMLLoader.load(getClass().getResource("/administrationModules/Housekeeping/housekeeping.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("InnLogix Housekeeping");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}