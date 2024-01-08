package lk.ijse.D.Controller;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.D.DTO.ItemDTO;
import lk.ijse.D.DTO.SupplierDTO;
import lk.ijse.D.DTO.SupplierOrderDetailDTO;
import lk.ijse.D.bo.BOFactory;
import lk.ijse.D.bo.custom.PlaceSupplierOrderBO;
import lk.ijse.D.bo.custom.impl.PlaceSupplierOrderBOImpl;
import lk.ijse.D.dbConnection.DbConnection;
import lk.ijse.D.view.tdm.OrderDetailTM;
import lk.ijse.D.view.tdm.SupplierOrderDetailTM;
import lk.ijse.D.view.tdm.SupplierOrderTM;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlaceSupplierOrderFromController {

        @FXML
        private Button btnSave;

        @FXML
        private ComboBox<String> cmbItemCode;

        @FXML
        private ComboBox<String> cmbSupplierId;

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
        private Label lblDate;

        @FXML
        private Label lblOrderId;

        @FXML
        private Label lblTotal;

        @FXML
        private TableView<SupplierOrderDetailTM> tblPlaceOrder;

        @FXML
        private TextField txtDescription;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtQty;

        @FXML
        private TextField txtQtyOnHand;

        @FXML
        private TextField txtUnitPrice;

        private String order_id;

        @FXML
        private Button btnPlaceOrder;
        PlaceSupplierOrderBO placeSupplierOrderBO = (PlaceSupplierOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_SUPPLIER_ORDER);

        public void initialize(){

                tblPlaceOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_code"));
                tblPlaceOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
                tblPlaceOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
                tblPlaceOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unit_price"));
                tblPlaceOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
                TableColumn<SupplierOrderDetailTM,Button> lastCol = (TableColumn<SupplierOrderDetailTM, Button>) tblPlaceOrder.getColumns().get(5);

                lastCol.setCellValueFactory(param -> {
                        Button btnDelete = new Button("Delete");

                        btnDelete.setOnAction(event -> {
                                tblPlaceOrder.getItems().remove(param.getValue());
                                tblPlaceOrder.getSelectionModel().clearSelection();
                                calculateTotal();
                                enableOrDisablePlaceOrderButton();
                        });

                        return new ReadOnlyObjectWrapper<>(btnDelete);
                });

                order_id = generateNewOrderId();
                lblOrderId.setText("Order Id: " + order_id);
                lblDate.setText(LocalDate.now().toString());
                btnPlaceOrder.setDisable(true);
                txtName.setFocusTraversable(false);
                txtName.setEditable(false);
                txtDescription.setFocusTraversable(false);
                txtDescription.setEditable(false);
                txtUnitPrice.setFocusTraversable(false);
                txtUnitPrice.setEditable(false);
                txtQtyOnHand.setFocusTraversable(false);
                txtQtyOnHand.setEditable(false);
                txtQty.setOnAction(event -> btnSave.fire());
                txtQty.setEditable(false);
                btnSave.setDisable(true);

                cmbSupplierId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        enableOrDisablePlaceOrderButton();
                        if (newValue != null){
                                try {
                                        try {
                                                if (!existSupplier(newValue + "")){
                                                        new Alert(Alert.AlertType.ERROR,"There is no such supplier associated with the id" + newValue + "").show();


                                                }

                                                SupplierDTO supplierDTO = placeSupplierOrderBO.searchSupplier(newValue + "");
                                                txtName.setText(supplierDTO.getSu_name());
                                        } catch (SQLException e) {
                                            new Alert(Alert.AlertType.ERROR,"Failed to find the customer" +newValue + ""+e ).show();

                                        }
                                }catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                }
                        }else{
                                txtName.clear();
                        }
                });

               cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
                       txtQty.setEditable(newItemCode !=null);
                       btnSave.setDisable(newItemCode ==null);

                       if (newItemCode !=null){
                               try {
                                       if (!existItem(newItemCode + "")){

                                       }
                                       ItemDTO item = placeSupplierOrderBO.searchItem(newItemCode + "");
                                       txtDescription.setText(item.getDescription());
                                       txtUnitPrice.setText(String.valueOf(item.getUnit_price()));

                                       Optional<SupplierOrderDetailTM> optOrderDetail = tblPlaceOrder.getItems().stream().filter(detail -> detail.getItem_code().equals(newItemCode)).findFirst();
                                       txtQtyOnHand.setText((optOrderDetail.isPresent() ? item.getQty() - optOrderDetail.get().getQty() : item.getQty()) + "");

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
          tblPlaceOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedSupplierOrderDetail) -> {
                  if (selectedSupplierOrderDetail !=null){
                          cmbItemCode.setDisable(true);
                          cmbItemCode.setValue(selectedSupplierOrderDetail.getItem_code());
                          btnSave.setText("Update");
                          txtQtyOnHand.setText(Integer.parseInt(txtQtyOnHand.getText())+selectedSupplierOrderDetail.getQty() + "");
                          txtQty.setText(selectedSupplierOrderDetail.getQty()+"");

                  }else {
                          btnSave.setText("Add");
                          cmbItemCode.setDisable(false);
                          cmbItemCode.getSelectionModel().clearSelection();
                          txtQty.clear();
                  }
          });

          loadAllItemCodes();
          loadAllSupplierIds();
        }

        boolean existSupplier(String su_id) throws SQLException, ClassNotFoundException {

                return placeSupplierOrderBO.existSupplier(su_id);
        }

        private boolean existItem(String item_code) throws SQLException, ClassNotFoundException {

                return placeSupplierOrderBO.existItem(item_code);
        }

        private void loadAllSupplierIds(){
                try {
                        ArrayList<SupplierDTO> allSuppliers = placeSupplierOrderBO.getAllSupplier();
                        for (SupplierDTO s : allSuppliers){
                                cmbSupplierId.getItems().add(s.getSu_id());
                        }
                } catch (SQLException e) {
                   new Alert(Alert.AlertType.ERROR,"Failed to load supplier ids").show();
                } catch (ClassNotFoundException e) {
                   e.printStackTrace();
                }
        }

        private void loadAllItemCodes(){
                try {
                        ArrayList<ItemDTO> allItems = placeSupplierOrderBO.getAllItem();
                        for (ItemDTO i : allItems){
                                cmbItemCode.getItems().add(i.getItem_code());
                        }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
        }

        public String generateNewOrderId(){
                try {
                        return placeSupplierOrderBO.generateOrderId();
                }catch (SQLException e ){
                        new Alert(Alert.AlertType.ERROR,"Failed to generate a new order id").show();
                }catch (ClassNotFoundException e ){
                        e.printStackTrace();
                }
                return "SOID-001";
        }


        @FXML
        void btnAddOnAction(ActionEvent event) {
                if (!txtQty.getText().matches("\\d+") || Integer.parseInt(txtQty.getText()) <= 0 ||
                        Integer.parseInt(txtQty.getText())> Integer.parseInt(txtQtyOnHand.getText())) {
                        new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
                        txtQty.requestFocus();
                        txtQty.selectAll();
                        return;
                }

                String item_code = cmbItemCode.getSelectionModel().getSelectedItem();
                String description = txtDescription.getText();
                double unit_price = Double.parseDouble(txtUnitPrice.getText());
                int qty = Integer.parseInt(txtQty.getText());
                BigDecimal total = BigDecimal.valueOf(Double.parseDouble(txtUnitPrice.getText()));

                boolean exists = tblPlaceOrder.getItems().stream().anyMatch(detail -> detail.getItem_code().equals(item_code));

                if (exists) {
                        System.out.println(exists);
                        SupplierOrderDetailTM supplierOrderDetailTM  = tblPlaceOrder.getItems().stream().filter(detail -> detail.getItem_code().equals(item_code)).findFirst().get();

                        if (btnSave.getText().equalsIgnoreCase("Update")) {
                                supplierOrderDetailTM.setQty(qty);
                                supplierOrderDetailTM.setTotal(total);
                                tblPlaceOrder.getSelectionModel().clearSelection();
                        }else {
                                supplierOrderDetailTM.setQty(supplierOrderDetailTM.getQty() +qty);
                                total = new BigDecimal(supplierOrderDetailTM.getQty()).multiply(BigDecimal.valueOf(unit_price));
                                supplierOrderDetailTM.setTotal(total);
                        }
                        tblPlaceOrder.refresh();
                }else {
                        tblPlaceOrder.getItems().add(new SupplierOrderDetailTM(item_code,description,qty, (int) unit_price,total));

                }
                cmbItemCode.getSelectionModel().clearSelection();
                cmbItemCode.requestFocus();
                calculateTotal();
                enableOrDisablePlaceOrderButton();
        }

        private void enableOrDisablePlaceOrderButton() {
                btnPlaceOrder.setDisable(!(cmbSupplierId.getSelectionModel().getSelectedItem() !=null && !tblPlaceOrder.getItems().isEmpty()));
        }

        @FXML
        void btnPlaceOrderOnAction(ActionEvent event) {

                boolean b = saveOrder(order_id, LocalDate.now(),cmbSupplierId.getValue(),
                        tblPlaceOrder.getItems().stream().map(tm -> new SupplierOrderDetailDTO(order_id,tm.getItem_code(),tm.getQty(),tm.getUnit_price())).collect(Collectors.toList()));

                System.out.println("b:"+b);

                        if(b){
                                new Alert(Alert.AlertType.INFORMATION,"Order has been placed successfully").show();

                        }else {
                                new Alert(Alert.AlertType.ERROR,"Order has not been placed successfully").show();
                        }

                        order_id = generateNewOrderId();
                        lblOrderId.setText("Order Id : "+ order_id);
                        cmbSupplierId.getSelectionModel().clearSelection();
                        cmbItemCode.getSelectionModel().clearSelection();
                        tblPlaceOrder.getItems().clear();
                        txtQty.clear();
                        calculateTotal();
        }



        private void calculateTotal() {

                BigDecimal total = new BigDecimal(0);

                for (SupplierOrderDetailTM detail : tblPlaceOrder.getItems()){
                        total = total.add(detail.getTotal());
                        System.out.println(total);
                }
                lblTotal.setText("Total : " + total);
        }

        private boolean saveOrder(String order_id, LocalDate dueDte, String su_id, List<SupplierOrderDetailDTO> supplierOrderDetails) {
                try {
                        return placeSupplierOrderBO.placeOrder(order_id,dueDte,su_id, supplierOrderDetails);
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                }
        }

        @FXML
        void btnOrderOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
                printOrderReport();
        }

        private void printOrderReport() throws JRException, SQLException, ClassNotFoundException {

                String order_id = this.lblOrderId .getText();
                InputStream resourceAsStream = getClass().getResourceAsStream("/report/supplierOr.jrxml");
                JasperDesign load = JRXmlLoader.load(resourceAsStream);
                JRDesignQuery jrDesignQuery = new JRDesignQuery();
                jrDesignQuery.setText("select * from supplier_lord_details\n" +
                        "left join\n" +
                        "supplier_order\n" +
                        "on supplier_lord_details . order_id = supplier_order.order_id; ");
                load.setQuery(jrDesignQuery);

                JasperReport jasperReport = JasperCompileManager.compileReport(load);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
                JasperViewer.viewReport(jasperPrint,false);
        }
}





