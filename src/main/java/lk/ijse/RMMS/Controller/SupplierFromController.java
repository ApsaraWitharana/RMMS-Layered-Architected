package lk.ijse.RMMS.Controller;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;
        import lk.ijse.RMMS.DTO.SupplierDTO;
        import lk.ijse.RMMS.bo.BOFactory;
        import lk.ijse.RMMS.bo.custom.SupplierBO;
        import lk.ijse.RMMS.dbConnection.DbConnection;
        import lk.ijse.RMMS.view.tdm.SupplierTM;
        import net.sf.jasperreports.engine.*;
        import net.sf.jasperreports.engine.design.JasperDesign;
        import net.sf.jasperreports.engine.xml.JRXmlLoader;
        import net.sf.jasperreports.view.JasperViewer;

        import java.io.InputStream;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

public class SupplierFromController {

    @FXML
    private Button btnAddNewSupplier;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private AnchorPane txtAncerpane;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    public void initialize(){
        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("su_id"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("su_name"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("su_address"));
        tblSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("su_email"));

        initUI();
        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable ,oldValue,newValue) ->{
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue!=null?"Update ":"Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null){
                txtId.setText(newValue.getSu_id());
                txtName.setText(newValue.getSu_name());
                txtAddress.setText(newValue.getSu_address());
                txtContact.setText(newValue.getContact());
                txtEmail.setText(newValue.getSu_email());
            }
                } );
        txtAddress.setOnAction(actionEvent -> btnSave.fire());
        loadAllSuppliers();

    }

    private void loadAllSuppliers(){
        tblSupplier.getItems().clear();
        try {
            ArrayList<SupplierDTO> allSupplier = supplierBO.getAllSupplier();
            for (SupplierDTO dto : allSupplier){
                tblSupplier.getItems().add(
                        new SupplierTM(
                                dto.getSu_id(),
                                dto.getSu_name(),
                                dto.getSu_address(),
                                dto.getContact(),
                                dto.getSu_email()
                        ));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
  private    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        /*Delete supplier*/

        String su_id = tblSupplier.getSelectionModel().getSelectedItem().getSu_id();
        try {
            if (!existSupplier(su_id)){
                new Alert(Alert.AlertType.ERROR,"There is no such customer associated with the su_id"+su_id).show();

            }

            supplierBO.deleteSupplier(su_id);
            tblSupplier.getItems().remove(tblSupplier.getSelectionModel().getSelectedItem());
            tblSupplier.getSelectionModel().clearSelection();

            initUI();
        }catch (SQLException e){
             new Alert(Alert.AlertType.ERROR,"Failed to delete the supplier"+su_id).show();

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private void initUI() {

        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
       // txtId.setDisable(true);
       // txtName.setDisable(true);
       // txtAddress.setDisable(true);
       // txtContact.setDisable(true);
       // txtEmail.setDisable(true);
        txtId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
  public   void btnSaveOnAction(ActionEvent event) {
       String su_id = txtId.getText();
       String su_name = txtName.getText();
       String su_address = txtAddress.getText();
       String su_email = txtEmail.getText();
       String contact = txtContact.getText();

       if (!su_name.matches("[A-Za-z ]+")){
           new Alert(Alert.AlertType.ERROR,"Invalid name ").show();
           txtName.requestFocus();
           return;
       }else if (!su_address.matches(".{3,}")){
           new Alert(Alert.AlertType.ERROR,"Address should be at least 3 characters long").show();
           txtAddress.requestFocus();
           return;
       }

       if (btnSave.getText().equalsIgnoreCase("save")){
           /*save customer */

           try {
               if (existSupplier(su_id)){
                   new Alert(Alert.AlertType.ERROR,su_id+"already exists").show();

               }
               boolean isSave = supplierBO.saveSupplier(new SupplierDTO(su_id,su_name,su_address,contact,su_email));

               if (isSave){
                   tblSupplier.getItems().add(new SupplierTM(su_id,su_name,su_address,contact,su_email));
                   new Alert(Alert.AlertType.CONFIRMATION,"supplier saved!").show();
               }
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR,"failed to save the supplier"+e.getMessage()).show();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       }else {
           /*Update supplier*/

           try {
               if (!existSupplier(su_id)){
                   new Alert(Alert.AlertType.ERROR,"There is no such customer associated with the su_id"+su_id).show();
               }

               SupplierDTO dto = new SupplierDTO(su_id,su_name,su_address,contact,su_email);
               supplierBO.updateSupplier(dto);
           } catch (SQLException e) {
               new Alert(Alert.AlertType.ERROR,"Failed to update the supplier"+su_id+e.getMessage()).show();

           } catch (ClassNotFoundException e) {
              e.printStackTrace();
           }

           SupplierTM selectSupplier = tblSupplier.getSelectionModel().getSelectedItem();
           selectSupplier.setSu_name(su_name);
           selectSupplier.setSu_address(su_address);
           selectSupplier.setContact(contact);
           selectSupplier.setSu_email(su_email);
           tblSupplier.refresh();
       }

       btnAddNewSupplier.fire();
    }

   boolean existSupplier(String su_id) throws SQLException, ClassNotFoundException {

        return supplierBO.existSupplier(su_id);
    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    public void btnAddNewOnAction(ActionEvent event) {

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblSupplier.getSelectionModel().clearSelection();

    }

    private String generateNewId() {
        try {
            return supplierBO.generateSupplierID();

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Failed to generate a new su-id "+e.getMessage()).show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        if (tblSupplier.getItems().isEmpty()){
            return "S00-001";
        }else {
            String su_id = getLastSupplierId();
            int newSupplierId = Integer.parseInt(su_id.replace("S","")) + 1;
            return String.format("S00-%03d",newSupplierId);
        }
    }

    private String getLastSupplierId() {
        List<SupplierTM> tempSupplierList = new ArrayList<>(tblSupplier.getItems());
        Collections.sort(tempSupplierList);
        return tempSupplierList.get(tempSupplierList.size()-1).getSu_id();
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        printSupplierReport();
    }

    private void printSupplierReport() throws JRException, SQLException, ClassNotFoundException {
        String su_id = this .txtId .getText();
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/SupplierR.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
//        JRDesignQuery jrDesignQuery = new JRDesignQuery();
//        jrDesignQuery.setText("SELECT * FROM supplier ");
//        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }


}
