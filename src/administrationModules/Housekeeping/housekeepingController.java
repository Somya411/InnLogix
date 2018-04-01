package administrationModules.Housekeeping;

import com.jfoenix.controls.JFXButton;
import com.sun.rowset.CachedRowSetImpl;
import database.dbUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static database.dbUtil.*;


public class housekeepingController implements Initializable{

        @FXML
        private TableView<Record> tableView;
        @FXML
        private TableColumn<Record,String> roomNo;
        @FXML
        private TableColumn<Record,String> roomType;
        @FXML
        private TableColumn<Record,String> status;
        @FXML
        private TableColumn<Record,String> availability;
        @FXML
        private TableColumn<Record,String> attendant;
        @FXML
        private TableColumn<Record,String> guest;
        @FXML
        private TableColumn<Record,String> request;

        @FXML
        private Button backToDashboard;
        @FXML
        private Button guestEntry;
        @FXML
        private Button maintainance;
        @FXML
        private Button occupancyReports;
        @FXML
        private Button housekeepingReports;

        @FXML
        private Label housekeepingLabel;
        @FXML
        private Label roomInfoLabel;
        @FXML
        private Label HKdbIsConnectedLabel;


        private ObservableList<Record>data;
      //  private dbUtil dc;



    private static final String connStr = "jdbc:sqlite:C:\\Users\\HP\\Desktop\\Dattebayo!!\\OOPS PROJECT\\InnLogix\\InnLogix.sqlite";

        housekeepingModel hkmodel=new housekeepingModel();

        @Override
         public void initialize(URL url, ResourceBundle rb) {
            if (this.hkmodel.isDbConnected())
            {
                this.HKdbIsConnectedLabel.setText("Database Connected");
            }
            else
            {
                this.HKdbIsConnectedLabel.setText("Not Connected");
            }

           // roomNo.setCellValueFactory(new PropertyValueFactory<Record,String>("r"));

            //dc=new dbUtil();

         }



         //load data from database
        @FXML
        private void loadHousekeepingTableData(ActionEvent event){


            try {
                Connection conn =null;boolean isConn = false;
                try {
                    conn = DriverManager.getConnection(connStr);
                    isConn = true;

                } catch (SQLException e) {

                    e.printStackTrace();

                }
                if(isConn==true) {
                    data = FXCollections.observableArrayList();
                    ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM housekeeping");
                    while (rs.next()) {
                        data.add(new Record(rs.getString(0), rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            roomNo.setCellValueFactory(new PropertyValueFactory<>("RoomNo"));
            roomType.setCellValueFactory(new PropertyValueFactory<>("RoomType"));
            status.setCellValueFactory(new PropertyValueFactory<>("Status"));
            availability.setCellValueFactory(new PropertyValueFactory<>("Availability"));
            attendant.setCellValueFactory(new PropertyValueFactory<>("Attendant"));
            guest.setCellValueFactory(new PropertyValueFactory<>("Guest"));
            request.setCellValueFactory(new PropertyValueFactory<>("Request"));

            tableView.setItems(null);
            tableView.setItems(data);
        }


        //guest entry button
        @FXML
        private void guestEntryButtonMethod(ActionEvent event)
        {
            guestEntry.setOnAction(e-> {
                Stage stage = (Stage)this.guestEntry.getScene().getWindow();
                stage.close();
            });
            try
            {
                Stage guestEntryStage = new Stage();
                FXMLLoader guestEntryLoader = new FXMLLoader();
                Pane root = (Pane)guestEntryLoader.load(getClass().getResource("/administrationModules/Housekeeping/guestEntryPage.fxml"));
                guestEntryPageController guestEntryPageController = (guestEntryPageController)guestEntryLoader.getController();

                Scene scene = new Scene(root);
                guestEntryStage.setScene(scene);
                guestEntryStage.setTitle("Guest Entry");
                guestEntryStage.setResizable(false);
                guestEntryStage.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        //maintainance
        @FXML
        private void maintainanceButtonMethod(ActionEvent event)
        {
            maintainance.setOnAction(e-> {
                Stage stage = (Stage)this.maintainance.getScene().getWindow();
                stage.close();
            });
            try
            {
                Stage maintainanceStage = new Stage();
                FXMLLoader maintainanceLoader = new FXMLLoader();
                Pane root = (Pane)maintainanceLoader.load(getClass().getResource("/administrationModules/Housekeeping/maintainancePage.fxml").openStream());
                maintainanceController maintainanceController = (maintainanceController)maintainanceLoader.getController();

                Scene scene = new Scene(root);
                maintainanceStage.setScene(scene);
                maintainanceStage.setTitle("Maintainance");
                maintainanceStage.setResizable(false);
                maintainanceStage.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


        //occupancyReports
        @FXML
        private void occupancyReportsButtonMethod(ActionEvent event)
        {
            occupancyReports.setOnAction(e-> {
                Stage stage = (Stage)this.occupancyReports.getScene().getWindow();
                stage.close();
            });
            try
            {
                Stage occupancyReportsStage = new Stage();
                FXMLLoader occupancyReportsLoader = new FXMLLoader();
                Pane root = (Pane)occupancyReportsLoader.load(getClass().getResource("/administrationModules/Housekeeping/occupancyReportsPage.fxml").openStream());
                occupancyReportsController occupancyReportsController = (occupancyReportsController)occupancyReportsLoader.getController();

                Scene scene = new Scene(root);
                occupancyReportsStage.setScene(scene);
                occupancyReportsStage.setTitle("Occupancy Reports");
                occupancyReportsStage.setResizable(false);
                occupancyReportsStage.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


        //housekeepingReports
        @FXML
        private void housekeepingReportsButtonMethod(ActionEvent event)
        {
            housekeepingReports.setOnAction(e-> {
                Stage stage = (Stage)this.housekeepingReports.getScene().getWindow();
                stage.close();
            });
            try
            {
                Stage housekeepingReportsStage = new Stage();
                FXMLLoader housekeepingReportsLoader = new FXMLLoader();
                Pane root = (Pane)housekeepingReportsLoader.load(getClass().getResource("/administrationModules/Housekeeping/housekeepingReportsPage.fxml").openStream());
                housekeepingReportsController housekeepingReportsController = (housekeepingReportsController)housekeepingReportsLoader.getController();

                Scene scene = new Scene(root);
                housekeepingReportsStage.setScene(scene);
                housekeepingReportsStage.setTitle("Housekeeping Reports");
                housekeepingReportsStage.setResizable(false);
                housekeepingReportsStage.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }


        //back to admin Dashboard
        @FXML
        private void backToDashboarduttonMethod(ActionEvent event)
        {
            backToDashboard.setOnAction(e-> {
                Stage stage = (Stage)this.backToDashboard.getScene().getWindow();
                stage.close();
            });
            try {

                login.LoginController object = new login.LoginController();
                object.adminLogin();
            }
            catch (Exception localException)
            {
                localException.printStackTrace();
            }
        }
    }


