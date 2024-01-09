package lk.ijse.RMMS.Controller;


        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;
        import lk.ijse.RMMS.DTO.ItemDTO;
        import lk.ijse.RMMS.bo.BOFactory;
        import lk.ijse.RMMS.bo.custom.ItemBO;
        import lk.ijse.RMMS.dbConnection.DbConnection;
        import lk.ijse.RMMS.view.tdm.ItemTM;
        import net.sf.jasperreports.engine.*;
        import net.sf.jasperreports.engine.design.JasperDesign;
        import net.sf.jasperreports.engine.xml.JRXmlLoader;
        import net.sf.jasperreports.view.JasperViewer;

        import java.io.InputStream;
        import java.sql.SQLException;
        import java.util.ArrayList;

public class ItemFromController {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<ItemTM> tbleItem;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private AnchorPane txtpane;

    @FXML
    private Button btnAddNewItem;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

   ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

   public void initialize(){
       tbleItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_code"));
       tbleItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
       tbleItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
       tbleItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unit_price"));
       tbleItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));

       initUI();
       tbleItem.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
           btnDelete.setDisable(newValue == null);
           btnSave.setText(newValue!=null? "Update":"Save");
           btnSave.setDisable(newValue == null);

           if (newValue != null){
               txtId.setText(newValue.getItem_code());
               txtName.setText(newValue.getName());
               txtDescription.setText(newValue.getDescription());
               txtUnitPrice.setText(String.valueOf(newValue.getUnit_price()));
               txtQty.setText(newValue.getQty()+"");

           }
       });

        txtQty.setOnAction(actionEvent -> btnSave.fire());
        loadAllItems();

   }

    private void loadAllItems() {
       tbleItem.getItems().clear();
       try {
           ArrayList<ItemDTO> allItem = itemBO.getAllItem();
           for (ItemDTO dto : allItem){
               tbleItem.getItems().add(
                       new ItemTM(
                               dto.getItem_code(),
                               dto.getName(),
                               dto.getDescription(),
                               dto.getUnit_price(),
                               dto.getQty()
                       ));

           }
       }catch (SQLException e){
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       }catch (ClassNotFoundException e){
           e.printStackTrace();
       }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
         String item_code = tbleItem.getSelectionModel().getSelectedItem().getItem_code();
         try {
             if (!existItem(item_code)){
                 new Alert(Alert.AlertType.ERROR,"There is no such item associated with the item_code"+item_code).show();

             }
             itemBO.deleteItem(item_code);

             tbleItem.getItems().remove(tbleItem.getSelectionModel().getSelectedItem());
             tbleItem.getSelectionModel().clearSelection();

             initUI();
         }catch (SQLException e){
             new Alert(Alert.AlertType.ERROR,"Failed to delete the item"+item_code).show();

         }catch (ClassNotFoundException e){
             e.printStackTrace();
         }
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
       // txtId.setDisable(true);
        txtName.setDisable(true);
        txtDescription.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQty.setDisable(true);
        txtId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String item_code = txtId.getText();
        String description = txtDescription.getText();

        if (!description.matches("[A-Za-z0-9 ]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid description").show();
            txtDescription.requestFocus();
            return;
        }else  if (!txtUnitPrice.getText().matches("^[0-9]+[.]?[0-9]*$")){
            new Alert(Alert.AlertType.ERROR,"Invalid unit price").show();
            txtUnitPrice.requestFocus();
            return;
        }else if (!txtQty.getText().matches("^\\d+$")){
            new Alert(Alert.AlertType.ERROR,"Invalid qty on hand").show();
            txtQty.requestFocus();
            return;
        }

        int qty = Integer.parseInt(txtQty.getText());
        double unit_price = Double.parseDouble(txtUnitPrice.getText());
        String name = txtName.getText();

        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(item_code)){
                    new Alert(Alert.AlertType.ERROR,item_code+"already exists").show();

                }

                //save item


                boolean isSave = itemBO.saveItem(new ItemDTO(item_code,name,description,unit_price,qty));
                if (isSave){
                    tbleItem.getItems().add(new ItemTM(item_code,name,description,unit_price,qty));
                    new Alert(Alert.AlertType.CONFIRMATION,"Item save").show();
                }

            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }catch (ClassNotFoundException e ){
                e.printStackTrace();
            }
        }else {
            try {
                if (!existItem(item_code)){
                    new Alert(Alert.AlertType.ERROR,"There is no such item associated with the item_code"+item_code).show();

                }

                //update item

                itemBO.updateItem(new ItemDTO(item_code,name,description,unit_price,qty));
                ItemTM selectedItem = tbleItem.getSelectionModel().getSelectedItem();
                selectedItem.setName(name);
                selectedItem.setDescription(description);
                selectedItem.setUnit_price(unit_price);
                selectedItem.setQty(qty);
                tbleItem.refresh();
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }

        btnAddNewItem.fire();
    }

    private boolean existItem(String itemCode) throws SQLException, ClassNotFoundException {

        return itemBO.existItem(itemCode);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewOnAction(ActionEvent event) {

        txtId.setEditable(false);
        txtName.setDisable(false);
        txtDescription.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtQty.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQty.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tbleItem.getSelectionModel().clearSelection();

    }

    private String generateNewId() {
        try {
            return itemBO.generateItemID();
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return "I00-001";
    }

    @FXML
    void btnItemReportOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        printItemReport();
    }

    private void printItemReport() throws JRException, SQLException, ClassNotFoundException {

        String item_code = this .txtId .getText();
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Item-.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
//        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//        jrDesignQuery.setText("SELECT * FROM item ");
//        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

//    public static ObservableList<XYChart.Series<String, Integer>> getData() throws SQLException {
//        //  String sql="SELECT item_name,item_qty FROM item WHERE item_qty<=100 ";
//
//        ObservableList<XYChart.Series<String, Integer>> datalist = FXCollections.observableArrayList();
//        // ResultSet resultSet = CrudUtil.execute(sql);
//
//         DbConnection dbConnection = null;
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT item_name,item_qty FROM item WHERE item_qty<=100";
//
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        //pstm.setString(1, code);
//
//        ResultSet resultSet = pstm.executeQuery();
//
//        XYChart.Series<String, Integer> series = new XYChart.Series<>();
//
//        while(resultSet.next()){
//            String itemName = resultSet.getString("item_name");
//            int itemQty = resultSet.getInt("item_qty");
//            series.getData().add(new XYChart.Data<>(itemName, itemQty));
//        }
//
//        datalist.add(series);
//        return datalist;
//    }



}
