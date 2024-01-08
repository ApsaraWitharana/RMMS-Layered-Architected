package lk.ijse.D.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.D.utile.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class HomepageFromController implements Initializable {

        @FXML
        private Text lblDate;

        @FXML
        private Text lblTime;

        @FXML
        private Text lbldate;

        @FXML
        private AnchorPane txtAnchorPane;

        @FXML
        void btnCustomerOnAction(ActionEvent event) throws IOException {
            Navigation.switchPaging(txtAnchorPane,"customer_from.fxml","Customer");

        }

        @FXML
        void btnCustomerOrdersOnAction(ActionEvent event) throws IOException {
           Navigation.switchPaging(txtAnchorPane,"place_order_form.fxml","CustomerPlaceOrder");


        }

        @FXML
        void btnDashboardOnAction(ActionEvent event) throws IOException {
            Navigation.switchPaging(txtAnchorPane,"dashboard_from.fxml","Dashboard");

        }

        @FXML
        void btnEmployeeOnAction(ActionEvent event) throws IOException {
            Navigation.switchPaging(txtAnchorPane,"employee_from.fxml","Employee");

        }

        @FXML
        void btnItemOnAction(ActionEvent event) throws IOException {
            Navigation.switchPaging(txtAnchorPane,"item_from.fxml","Item");

        }

        @FXML
        void btnStockOnAction(ActionEvent event) throws IOException {
                Navigation.switchPaging(txtAnchorPane,"stock_from.fxml","Stock");

        }

        @FXML
        void btnSupplierOnAction(ActionEvent event) throws IOException {
            Navigation.switchPaging(txtAnchorPane,"supplier_from.fxml","Supplier");

        }

        @FXML
        void btnSupplierOrdersOnAction(ActionEvent event) throws IOException {
                Navigation.switchPaging(txtAnchorPane,"place_su_order_form.fxml","SupplierPlaceOrder");
        }

        public void timenow(Text time , Text date){
                Thread thread = new Thread(() ->{
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                        SimpleDateFormat sdf1 = new SimpleDateFormat("MMM, dd, yyy");
                        while (true){
                                try {
                                        Thread.sleep(1000);
                                }catch (Exception e){
                                        System.out.println(e);
                                }
                                final String timenow = sdf.format(new Date());
                                String timenow1 = sdf1.format(new Date());

                                Platform.runLater(() ->{
                                        time.setText(timenow);
                                        date.setText(timenow1);

                                });
                        }
                });
                thread.start();
        }

        public void initialize(URL url , ResourceBundle resourceBundle){
                timenow(lblTime,lblDate);

                try {
                        Navigation.switchPaging(txtAnchorPane,"dashboard_from.fxml","Dashboard");
                }catch (IOException e){
                        throw new RuntimeException(e);
                }
        }

    }


