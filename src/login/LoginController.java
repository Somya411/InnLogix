package login;

import administrationModules.*;
import guestModules.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{

    public LoginModel loginModel = new LoginModel();

    @FXML
    private Label isConnected;
    @FXML
    private JFXComboBox<String> user;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginButton;
    @FXML
    private Label wrongCreds;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        if (this.loginModel.isDbConnected())
        {
            this.isConnected.setText("Database Connected");
        }
        else
        {
            this.isConnected.setText("Not Connected");
        }
//        this.userType.setItems(FXCollections.observableArrayList(user.values()));

        user.getItems().add("admin"); //Change Selection colour
        user.getItems().add("guest");
    }


    @FXML
    private void loginButtonMethod(ActionEvent event)
    {
        System.out.println(this.username.getText());  //TODO Remove Trailing spaces
        System.out.println(this.password.getText());
        System.out.println(this.user.getValue());
        try
        {
            boolean temp = loginModel.isLogin(username.getText(), password.getText(), user.getValue());
            System.out.println(temp);
            if (temp)
            {
                Stage stage = (Stage)this.loginButton.getScene().getWindow();
                stage.close();

                switch (this.user.getValue())
                {
                    case "admin":
                        adminLogin();
                        break;
                    case "guest":
                        guestLogin();
                }
            }
            else
            {
                this.wrongCreds.setText("Wrong Credentials");
            }
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }

    public void guestLogin()
    {
        try
        {
            Stage userStage = new Stage();
            FXMLLoader guestLoader = new FXMLLoader();
            Pane root = (Pane)guestLoader.load(getClass().getResource("/guestModules/guest.fxml").openStream());
            guestController guestController = (guestController)guestLoader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("Guest Dashboard");
            userStage.setResizable(false);
            userStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void adminLogin()
    {
        try
        {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminroot = (Pane)FXMLLoader.load(getClass().getResource("/administrationModules/admin.fxml"));
            AdminController adminController = (AdminController)adminLoader.getController();

            Scene adminscene = new Scene(adminroot);

            adminStage.setScene(adminscene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


