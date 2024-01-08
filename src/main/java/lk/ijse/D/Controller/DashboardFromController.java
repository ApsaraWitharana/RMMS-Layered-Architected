package lk.ijse.D.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import lk.ijse.D.bo.custom.DashboardBO;
import lk.ijse.D.bo.custom.ItemBO;
import lk.ijse.D.bo.custom.impl.DashboardBOImpl;
import lk.ijse.D.bo.custom.impl.ItemBOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardFromController implements Initializable {

        DashboardBO dashboardBO = new DashboardBOImpl();

        @FXML
        private BarChart<String, Integer> barChart;

        @FXML
        void btnLogoutOnAction(ActionEvent event) throws IOException {
                Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
        }

        public void setDataToBarChart() throws SQLException, ClassNotFoundException {

                ObservableList<XYChart.Series<String, Integer>> barChartData =  dashboardBO.getData();

                barChart.setData(barChartData);
        }



        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                try {
                        setDataToBarChart();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                }

        }
}


