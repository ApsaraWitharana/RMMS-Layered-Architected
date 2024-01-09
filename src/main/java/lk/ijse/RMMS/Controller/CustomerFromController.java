
package lk.ijse.RMMS.Controller;

        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;
        import lk.ijse.RMMS.DTO.CustomerDTO;
        import lk.ijse.RMMS.bo.BOFactory;
        import lk.ijse.RMMS.bo.custom.CustomerBO;
        import lk.ijse.RMMS.dbConnection.DbConnection;
        import lk.ijse.RMMS.view.tdm.CustomerTM;
        import net.sf.jasperreports.engine.*;
        import net.sf.jasperreports.engine.design.JasperDesign;
        import net.sf.jasperreports.engine.xml.JRXmlLoader;
        import net.sf.jasperreports.view.JasperViewer;

        import java.io.InputStream;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

public class CustomerFromController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnAddNewCustomer;

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
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private AnchorPane txtpane;

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize(){
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("cu_id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cu_name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("cu_contact"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("cu_email"));

      initUI();


        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable ,oldValue,newValue )->{
        btnDelete.setDisable(newValue == null);
        btnSave.setText(newValue != null ? "Update" :"Save");
        btnSave.setDisable(newValue == null);

      if (newValue != null){
          txtId.setText(newValue.getCu_id());
          txtName.setText(newValue.getCu_name());
          txtAddress.setText(newValue.getAddress());
          txtContact.setText(newValue.getCu_contact());
          txtEmail.setText(newValue.getCu_email());

          txtId.setDisable(false);
          txtName.setDisable(false);
          txtAddress.setDisable(false);
          txtContact.setDisable(false);
          txtEmail.setDisable(false);


      }

    });

        txtAddress.setOnAction(actionEvent -> btnSave.fire());
        loadAllCustomers();
}

    private void loadAllCustomers() {
        tblCustomer.getItems().clear();

        try {
            ArrayList<CustomerDTO> allCustomer = customerBO.getAllCustomer();
            for (CustomerDTO dto : allCustomer){
                tblCustomer.getItems().add(
                        new CustomerTM(
                                dto.getCu_id(),
                                dto.getCu_name(),
                                dto.getAddress(),
                                dto.getCu_contact(),
                                dto.getCu_email()
                        ));

            }

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }catch (ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String cu_id =tblCustomer.getSelectionModel().getSelectedItem().getCu_id();
        try {
            if (!exisCustomer(cu_id)){
                new Alert(Alert.AlertType.ERROR,"Therre is no such customer associated with the id "+ cu_id).show();

            }
            customerBO.deleteCustomer(cu_id);
            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();
            initUI();
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Failed to delete the customer"+cu_id).show();

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    private String generateNewId() {
        try {
            return customerBO.generateCustomerID();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id" + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (tblCustomer.getItems().isEmpty()){
            return "C00-001";
        }else {
            String cu_id = generateLastCustomerId();
            int newCustomerId = Integer.parseInt(cu_id.replace("C",""))+ 1;
            return String.format("C00-%03d",newCustomerId);
        }
    }

    private String generateLastCustomerId() {
        List<CustomerTM> tempCustomerList = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(tempCustomerList);
        return tempCustomerList.get(tempCustomerList.size() - 1).getCu_id();
    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtEmail.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtEmail.setDisable(true);
        txtId.setEditable(false);
        btnSave.setDisable(true);

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String cu_id = txtId.getText();
        String cu_name = txtName.getText();
        String address = txtAddress.getText();
        String cu_contact = txtContact.getText();
        String cu_email = txtEmail.getText();

        if (!cu_name.matches("[A-Za-z ]+")){
            new Alert(Alert.AlertType.ERROR,"Invalid name").show();
            txtName.requestFocus();
            return;
        }else if (!address.matches(".{3,}")){
            new Alert(Alert.AlertType.ERROR,"Address should be at least 3 characters long").show();
            txtAddress.requestFocus();
            return;
        }


        if (btnSave.getText().equalsIgnoreCase("save")){
            /*Save Customer*/
            try {
                if (exisCustomer(cu_id)){
                    new Alert(Alert.AlertType.ERROR,cu_id +"already exists").show();
                }
                boolean isSaved = customerBO.saveCustomer(new CustomerDTO(cu_id,cu_name,address,cu_contact,cu_email));

                if (isSaved){
                    tblCustomer.getItems().add(new CustomerTM(cu_id,cu_name,address,cu_contact,cu_email));
                    new Alert(Alert.AlertType.CONFIRMATION,"customer saved!").show();

                }
            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,"Failed to save the customer"+ e.getMessage()).show();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }else {
            /* Update customer*/
            try{
                if (!exisCustomer(cu_id)){
                    //new Alert(Alert.AlertType.CONFIRMATION,"customer update!").show();
                    new Alert(Alert.AlertType.ERROR,"There is no such customer associate with the id"+cu_id).show();

                }
                CustomerDTO dto = new CustomerDTO(cu_id,cu_name,address,cu_contact,cu_email);
                customerBO.updateCustomer(dto);

            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,"Failed to update the customer"+cu_id+e.getMessage()).show();

            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
// table stor the data
            CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
            selectedCustomer.setCu_id(cu_id);
            selectedCustomer.setCu_name(cu_name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setCu_contact(cu_contact);
            selectedCustomer.setCu_email(cu_email);
            tblCustomer.refresh();
        }

        btnAddNewCustomer.fire();



    }

    boolean exisCustomer(String cu_id) throws SQLException, ClassNotFoundException {

        return customerBO.existCustomer(cu_id);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewOnAction(ActionEvent event) {

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
        tblCustomer.getSelectionModel().clearSelection();


    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {

        printCustomerReport();

    }

    private void printCustomerReport() throws JRException, SQLException, ClassNotFoundException {
        String Id = this .txtId .getText();
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/Custom_4.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        //JRDesignQuery jrDesignQuery = new JRDesignQuery();
        //jrDesignQuery.setText("SELECT * FROM customer ");
        //load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }


}
