package lk.ijse.RMMS.controller;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;
        import lk.ijse.RMMS.dto.StockDTO;
        import lk.ijse.RMMS.bo.BOFactory;
        import lk.ijse.RMMS.bo.custom.StockBO;
        import lk.ijse.RMMS.dbConnection.DbConnection;
        import lk.ijse.RMMS.view.tdm.StockTM;
        import net.sf.jasperreports.engine.*;
        import net.sf.jasperreports.engine.design.JasperDesign;
        import net.sf.jasperreports.engine.xml.JRXmlLoader;
        import net.sf.jasperreports.view.JasperViewer;

        import java.io.InputStream;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

public class StockFromController {

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<StockTM> tblStock;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private AnchorPane txtPane;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;


    @FXML
    private Button btnAddNewStock;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

   StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);

   public void initialize(){
       tblStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
       tblStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("material_name"));
       tblStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
       tblStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unit_price"));
       tblStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qty"));

       initUI();

       tblStock.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
         btnDelete.setDisable(newValue == null);
         btnSave.setText(newValue !=null ? "Update":"Save");
         btnSave.setDisable(newValue == null);
         if (newValue != null){
             txtId.setText(newValue.getId());
             txtName.setText(newValue.getMaterial_name());
             txtQty.setText(newValue.getQty()+"");
             txtDescription.setText(newValue.getDescription());
             txtUnitPrice.setText(String.valueOf(newValue.getUnit_price()));

             txtId.setDisable(false);
             txtName.setDisable(false);
             txtQty.setDisable(false);
             txtDescription.setDisable(false);
             txtUnitPrice.setDisable(false);
         }
       });

       txtName.setOnAction(actionEvent -> btnSave.fire());
       loadAllStock();

   }

    private void loadAllStock() {
       tblStock.getItems().clear();

       try {
           ArrayList<StockDTO> allStock = stockBO.getAllStock();
           for (StockDTO dto : allStock){
               tblStock.getItems().add(
                       new StockTM(
                               dto.getId(),
                               dto.getMaterial_name(),
                               dto.getQty(),
                               dto.getDescription(),
                               dto.getUnit_price()
                       ));


           }
       } catch (SQLException e) {
          new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       } catch (ClassNotFoundException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
       }
    }

    private void initUI() {
       txtId.clear();
       txtName.clear();
       txtQty.clear();
       txtDescription.clear();
       txtUnitPrice.clear();
       txtId.setDisable(true);
       txtName.setDisable(true);
       txtQty.setDisable(true);
       txtDescription.setDisable(true);
       txtUnitPrice.setDisable(true);
       txtId.setEditable(false);
       btnSave.setDisable(true);
       btnDelete.setDisable(true);
   }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String id = tblStock.getSelectionModel().getSelectedItem().getId();

        try {
            if (!existStock(id)) {
                new Alert(Alert.AlertType.ERROR,"There is no such stock associated with the id "+id).show();

            }
            stockBO.deleteStock(id);
            tblStock.getItems().remove(tblStock.getSelectionModel().getSelectedItem());
            tblStock.getSelectionModel().clearSelection();
        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR,"Failed to delete the stock"+id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String material_name = txtName.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String description = txtDescription.getText();
        double unit_price = Double.parseDouble(txtUnitPrice.getText());


        if (!material_name.matches("[A-Za-z ]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid name").show();
            txtName.requestFocus();
            return;
        }else if (!description.matches("[A-Za-z]{4,}\\d\\w[A-Za-z]\\w")){
            new Alert(Alert.AlertType.ERROR,"Invalid description").show();
            txtDescription.requestFocus();
            return;
        }

        if (btnSave.getText().equalsIgnoreCase("save")){
            //stock save//

            try {
                if (existStock(id)){
                    new Alert(Alert.AlertType.ERROR,id+"already exists").show();

                }
                boolean isSave = stockBO.saveStock(new StockDTO(id,material_name,qty,description,unit_price));
                if (isSave){
                    tblStock.getItems().add(new StockTM(id,material_name,qty,description,unit_price));
                    new Alert(Alert.AlertType.CONFIRMATION,"stock saved!").show();

                }
            } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR,"failed to save the stock "+ e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            //update stock//

            try {
                if (!existStock(id)){
                    new Alert(Alert.AlertType.ERROR,"There is no such stock associated with the id"+id).show();

                }
                StockDTO dto = new StockDTO(id,material_name,qty,description,unit_price);
                stockBO.updateStock(dto);
            } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR,"Failed to update the stock"+id+e.getMessage()).show();

            } catch (ClassNotFoundException e) {
              e.printStackTrace();
            }

            StockTM sekectedStock = tblStock.getSelectionModel().getSelectedItem();
            sekectedStock.setMaterial_name(material_name);
            sekectedStock.setQty(qty);
            sekectedStock.setDescription(description);
            sekectedStock.setUnit_price(unit_price);
            tblStock.refresh();

        }

        btnAddNewStock.fire();
    }

    private boolean existStock(String id) throws SQLException, ClassNotFoundException {
        return stockBO.existStock(id);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewOnAction(ActionEvent event) {

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtQty.setDisable(false);
        txtDescription.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtQty.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblStock.getSelectionModel().clearSelection();
    }

    private String generateNewId() {
        try {
            return stockBO.generateStockID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to generate a new id"+e.getMessage()).show();

        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        }

        if (tblStock.getItems().isEmpty()){
            return "D00-001";
        }else {
            String id = getLastStockId();
            int newStockId = Integer.parseInt(id.replace("D",""))+1;
            return String.format("D00-%03d",newStockId);
        }
    }

    private String getLastStockId() {
        List<StockTM> tempStockList = new ArrayList<>(tblStock.getItems());
        Collections.sort(tempStockList);
        return tempStockList.get(tempStockList.size()-1).getId();
    }

    @FXML
    void btnStockReportOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        printStockReport();
    }

    private void printStockReport() throws JRException, SQLException, ClassNotFoundException {
        String id = this .txtId .getText();
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/stockR.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
//        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//        jrDesignQuery.setText("SELECT * FROM stock ");
//        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }


}
