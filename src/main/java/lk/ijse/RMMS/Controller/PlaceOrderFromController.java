package lk.ijse.RMMS.Controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.RMMS.DTO.CustomerDTO;
import lk.ijse.RMMS.DTO.ItemDTO;
import lk.ijse.RMMS.DTO.OrderDetailDTO;
import lk.ijse.RMMS.bo.BOFactory;
import lk.ijse.RMMS.bo.custom.PlaceOrderBO;
import lk.ijse.RMMS.dbConnection.DbConnection;
import lk.ijse.RMMS.view.tdm.OrderDetailTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

public class PlaceOrderFromController {

        @FXML
        private ComboBox<String> cmbCustomerId;

        @FXML
        private ComboBox<String> cmbItem;

        @FXML
        private TableColumn<?, ?> colAction;

        @FXML
        private TableColumn<?, ?> colCode;

        @FXML
        private TableColumn<?, ?> colDescription;

        @FXML
        private TableColumn<?, ?> colQty;

        @FXML
        private TableColumn<?, ?> colTotal;

        @FXML
        private TableColumn<?, ?> colUnitPrice;

        @FXML
        private Text lblDate;

        @FXML
        private Text lblOrderId;

        @FXML
        private Text lblTotal;

        @FXML
        private Text lbltotal;

        @FXML
        private TableView<OrderDetailTM> tblPlaceOrder;

        @FXML
        private TextField txtCustomerName;

        @FXML
        private TextField txtDescription;

        @FXML
        private TextField txtQty;

        @FXML
        public TextField txtQtyOnHand;

        @FXML
        private TextField txtUnitPrice;

    @FXML
    public Button btnPlaceOrder;

    @FXML
    public Button btnSave;
   // @FXML
   // private Text orderId;

    private String orderId;
    PlaceOrderBO placeOrderBO=  (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);

    // private OrderDetailDTO newValue;

    public void initialize() throws SQLException, ClassNotFoundException {
            tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_code"));
            tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
            tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
            tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unit_price"));
            tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
            TableColumn<OrderDetailTM,Button> lastCol=(TableColumn<OrderDetailTM, Button>) tblPlaceOrder.getColumns().get(5);

            lastCol.setCellValueFactory(param ->{
                Button btnDelete = new Button("Delete");

                btnDelete.setOnAction(event -> {
                    tblPlaceOrder.getItems().remove(param.getValue());
                    tblPlaceOrder.getSelectionModel().clearSelection();
                    calculateTotal();
                    enableOrDisablePlaceOrderButton();
                });
                return new ReadOnlyObjectWrapper<>(btnDelete);
            });

            orderId = String.valueOf(generateNewOrderId());
            lblOrderId.setText("Order Id:"+ orderId);
            lblDate.setText(LocalDate.now().toString());
            btnPlaceOrder.setDisable(true);
            txtCustomerName.setFocusTraversable(false);
            txtCustomerName.setEditable(false);
            txtDescription.setFocusTraversable(false);
            txtDescription.setEditable(false);
            txtUnitPrice.setFocusTraversable(false);
            txtUnitPrice.setEditable(false);
            txtQtyOnHand.setFocusTraversable(false);
            txtQtyOnHand.setEditable(false);
            txtQty.setOnAction(event -> btnSave.fire());
            txtQty.setEditable(false);
            btnSave.setDisable(true);

            cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
                enableOrDisablePlaceOrderButton();
                if (newValue != null){
                    try {
                        if (!exisCustomer(newValue+"")){
                            new Alert(Alert.AlertType.ERROR,"There is no such customer associated with the id"+newValue+"").show();

                        }

                        CustomerDTO customerDTO = placeOrderBO.searchCustomer(newValue+"");
                        txtCustomerName.setText(customerDTO.getCu_name());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR,"Failed to find the customer"+newValue+""+e).show();

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    txtCustomerName.clear();
                }
            });

            cmbItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode)->{
                txtQty.setEditable(newItemCode !=null);
                btnSave.setDisable(newItemCode == null);

                if (newItemCode !=null){
                    try {
                        if (!existItem(newItemCode + "")){

                        }

                        ItemDTO item = placeOrderBO.searchItem(newItemCode + "");

                        txtDescription.setText(item.getDescription());
                        txtUnitPrice.setText(String.valueOf(item.getUnit_price()));

                        Optional<OrderDetailTM> optOrderDetail = tblPlaceOrder.getItems().stream().filter(detail -> detail.getItem_code().equals(newItemCode)).findFirst();
                        txtQtyOnHand.setText((optOrderDetail.isPresent() ? item.getQty() - optOrderDetail.get().getQty():item.getQty()) + "");

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                }else {
                    txtDescription.clear();
                    txtQty.clear();
                    txtQtyOnHand.clear();
                    txtUnitPrice.clear();
                }
            });

           tblPlaceOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail)->{

               if (selectedOrderDetail != null){
                   cmbItem.setDisable(true);
                   cmbItem.setValue(selectedOrderDetail.getItem_code());
                   btnSave.setText("Update");
                   txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText())+selectedOrderDetail.getQty()+"");
                   txtQty.setText(selectedOrderDetail.getQty()+"");


               }else {
                   btnSave.setText("Add");
                   cmbItem.setDisable(false);
                   cmbItem.getSelectionModel().clearSelection();
                   txtQty.clear();
               }
           });
            loadAllCustomerIds();
            loadAllItemCode();
        }

    public String generateNewOrderId() {
            try {
                return placeOrderBO.generateOrderID();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"Failed to generate a new order id").show();
            } catch (ClassNotFoundException e) {
               e.printStackTrace();
            }

            return "OID-001";
    }

    private void enableOrDisablePlaceOrderButton() {
            btnPlaceOrder.setDisable(!(cmbCustomerId.getSelectionModel().getSelectedItem() !=null && !tblPlaceOrder.getItems().isEmpty()));
    }

    private void calculateTotal() {
        BigDecimal total = new BigDecimal(0);

        for (OrderDetailTM detail : tblPlaceOrder.getItems()){
            total = total.add(detail.getTotal());
            System.out.println(total);
        }
        lbltotal.setText("Total : " + total);
    }

    @FXML
     public    void btnAddOnAction(ActionEvent event) {
         if (!txtQty.getText().matches("\\d+")|| Integer.parseInt(txtQty.getText()) <=0 ||
         Integer.parseInt(txtQty.getText())>Integer.parseInt(txtQtyOnHand.getText())){
             new Alert(Alert.AlertType.ERROR,"Invalid qty").show();
             txtQty.requestFocus();
             txtQty.selectAll();
             return;
         }

         String item_code = cmbItem.getSelectionModel().getSelectedItem();
         String description = txtDescription.getText();
         double unit_price = Double.parseDouble(txtUnitPrice.getText());
         int qty = Integer.parseInt(txtQty.getText());
         BigDecimal total = BigDecimal.valueOf(Double.parseDouble(txtUnitPrice.getText()));

         boolean exists = tblPlaceOrder.getItems().stream().anyMatch(detail -> detail.getItem_code().equals(item_code));
//        Optional<Object> optionalString = null;
//        optionalString.ifPresent(val -> System.out.println("Value is: " + val));

//        Optional<Object> optionalString = 0;
//        if(optionalString.isPresent()) {
//            String value = (String) optionalString.get();
//        }

        if (exists){
            System.out.println(exists);
             OrderDetailTM orderDetailTM = tblPlaceOrder.getItems().stream().filter(detail -> detail.getItem_code().equals(item_code)).findFirst().get();

             if (btnSave.getText().equalsIgnoreCase("Update")){
                 orderDetailTM.setQty(qty);
                 orderDetailTM.setTotal(total);
                 tblPlaceOrder.getSelectionModel().clearSelection();
             }else {
                 orderDetailTM.setQty(orderDetailTM.getQty() + qty);
                 total = new BigDecimal(orderDetailTM.getQty()).multiply(BigDecimal.valueOf(unit_price));
                 orderDetailTM.setTotal(total);


             }

             tblPlaceOrder.refresh();
         }else {
             tblPlaceOrder.getItems().add(new OrderDetailTM(item_code,description,qty, (int) unit_price,total));
         }

         cmbItem.getSelectionModel().clearSelection();
         cmbItem.requestFocus();
         calculateTotal();
         enableOrDisablePlaceOrderButton();



        }

        @FXML
    public void btnPlaceOrderOnAction(ActionEvent event) {
           // Collectors Collections = null;
            boolean b = saveOrder(orderId,LocalDate.now(),cmbCustomerId.getValue(),
                    tblPlaceOrder.getItems().stream().map(tm -> new OrderDetailDTO(orderId,tm.getItem_code(),tm.getQty(),tm.getUnit_price())).collect(Collectors.toList()));
             if (b){
                 new Alert(Alert.AlertType.INFORMATION,"Order has been placed successfully").show();

             }else {
                 new Alert(Alert.AlertType.ERROR,"Order has not been placed successfully").show();
             }
            orderId = generateNewOrderId();
             lblOrderId.setText("Order Id :"+ orderId);
             cmbCustomerId.getSelectionModel().clearSelection();
             cmbItem.getSelectionModel().clearSelection();
             tblPlaceOrder.getItems().clear();
             txtQty.clear();
             calculateTotal();

        }

    private  boolean saveOrder(String orderId, LocalDate date, String cu_id, List<OrderDetailDTO> orderDetails) {
            try {
                //System.out.println(Arrays.toString(orderDetails));
                return placeOrderBO.placeOrder(orderId,date,cu_id,orderDetails);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
    }


    @FXML
        void cmbCustomeIdrOnAction(ActionEvent event) {

        }

        @FXML
        void cmbItemOnAction(ActionEvent event) {

        }

        private void loadAllCustomerIds(){
            try {
                ArrayList<CustomerDTO> allCustomers = placeOrderBO.getAllCustomer();
                for (CustomerDTO c : allCustomers){
                    cmbCustomerId.getItems().add(c.getCu_id());
                }
            } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR,"Failed to load customer ids").show();
            } catch (ClassNotFoundException e) {
               e.printStackTrace();
            }
        }

        private void loadAllItemCode(){
            try {
                ArrayList<ItemDTO> allItems = placeOrderBO.getAllItem();
                for (ItemDTO i : allItems){
                    cmbItem.getItems().add(i.getItem_code());
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    private boolean existItem(String item_code) throws SQLException, ClassNotFoundException {

        return placeOrderBO.existItem(item_code);
    }

    boolean exisCustomer(String cu_id) throws SQLException, ClassNotFoundException {

        return placeOrderBO.existCustomer(cu_id);
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        printOrdersrReport();
    }

    private void printOrdersrReport() throws JRException, SQLException, ClassNotFoundException {
        String order_id = this .lblOrderId .getText();
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/CustomerOrder.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
//        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//        jrDesignQuery.setText("select * from order_details\n" +
//                "left join\n" +
//                "orders \n" +
//                "on order_details. order_id = orders.order_id;");
//        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    @FXML
    void btnGeneratebillOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        printOrderbill();

    }

    private void printOrderbill() throws JRException, SQLException, ClassNotFoundException {

        String order_id = this .lblOrderId .getText();
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/cuorderbill.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
//        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//        jrDesignQuery.setText("select * from order_details\n" +
//                "left join\n" +
//                "orders \n" +
//                "on order_details. order_id = orders.order_id;");
//        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }


}


