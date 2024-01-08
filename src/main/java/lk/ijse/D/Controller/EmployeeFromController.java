package lk.ijse.D.Controller;


        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.layout.AnchorPane;
        import lk.ijse.D.DTO.EmployeeDTO;
        import lk.ijse.D.bo.BOFactory;
        import lk.ijse.D.bo.custom.EmployeeBO;
        import lk.ijse.D.bo.custom.impl.EmployeeBOImpl;
        import lk.ijse.D.dao.DAOFactory;
        import lk.ijse.D.dbConnection.DbConnection;
        import lk.ijse.D.view.tdm.EmployeeTM;
        import net.sf.jasperreports.engine.*;
        import net.sf.jasperreports.engine.design.JRDesignQuery;
        import net.sf.jasperreports.engine.design.JasperDesign;
        import net.sf.jasperreports.engine.xml.JRXmlLoader;
        import net.sf.jasperreports.view.JasperViewer;

        import java.io.InputStream;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.List;

public class EmployeeFromController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colJobTitle;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtDob;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtJobTitle;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtSallary;

    @FXML
    private AnchorPane txtpane;

    @FXML
    private TextField txtContact;

    @FXML
    private Button btnAddNewEmployee;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    public void initialize(){
        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("emp_address"));
        tblEmployee.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblEmployee.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("emp_email"));
        tblEmployee.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("emp_nic"));
        tblEmployee.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("job_title"));
        tblEmployee.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("emp_dob"));
        tblEmployee.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("salary"));

        initUI();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue )->{
            btnDelete.setDisable(newValue == null );
            btnSave.setText(newValue !=null ? "Update":"Save");
            btnSave.setDisable(newValue == null);

            if (newValue !=null){

                txtId.setText(newValue.getEmp_id());
                txtName.setText(newValue.getEmp_name());
                txtDob.setText(newValue.getEmp_dob());
                txtAddress.setText(newValue.getEmp_address());
                txtJobTitle.setText(newValue.getJob_title());
                txtNic.setText(newValue.getEmp_nic());
                txtContact.setText(newValue.getContact());
                txtEmail.setText(newValue.getEmp_email());
                txtSallary.setText(newValue.getSalary());

                txtId.setDisable(false);
                txtName.setDisable(false);
                txtDob.setDisable(false);
                txtAddress.setDisable(false);
                txtNic.setDisable(false);
                txtContact.setDisable(false);
                txtEmail.setDisable(false);
                txtSallary.setDisable(false);
            }
        });

        txtAddress.setOnAction(actionEvent -> btnSave.fire());
        loadAllEmployees();
    }

    private void loadAllEmployees() {
        tblEmployee.getItems().clear();
        //grt all employee//

        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for (EmployeeDTO dto: allEmployee){

                tblEmployee.getItems().add(
                        new EmployeeTM(
                                dto.getEmp_id(),
                                dto.getName(),
                                dto.getDob(),
                                dto.getAddress(),
                                dto.getJobTitle(),
                                dto.getNic(),
                                dto.getContact(),
                                dto.getEmail(),
                                dto.getSalary()
                        ));

            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();

        }catch (ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String emp_id = tblEmployee.getSelectionModel().getSelectedItem().getEmp_id();
        try {
            if (!existEmployee(emp_id)){
                new Alert(Alert.AlertType.ERROR,"There is no such employee associated with the emp_id"+emp_id).show();

            }
            employeeBO.deleteEmployee(emp_id);
            tblEmployee.getItems().remove(tblEmployee.getSelectionModel().getSelectedItem());
            tblEmployee.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to delete the employee"+emp_id).show();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initUI() {
        txtId.clear();
        txtName.clear();
        txtDob.clear();
        txtAddress.clear();
        txtJobTitle.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();
        txtSallary.clear();
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtDob.setDisable(true);
        txtAddress.setDisable(true);
        txtNic.setDisable(true);
        txtContact.setDisable(true);
        txtEmail.setDisable(true);
        txtSallary.setDisable(true);
        txtId.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
         String emp_id = txtId.getText();
         String emp_name = txtName.getText();
         String emp_dob = txtDob.getText();
         String emp_address = txtAddress.getText();
         String jon_title = txtJobTitle.getText();
         String emp_nic = txtNic.getText();
         String contact = txtContact.getText();
         String emp_email = txtEmail.getText();
         String salary = txtSallary.getText();

         if (!emp_name.matches("[A-Za-z ]+")){
             new Alert(Alert.AlertType.ERROR,"Invalid name").show();
             txtName.requestFocus();
             return;
         }else if (!emp_address.matches(".{3,}")){
             new Alert(Alert.AlertType.ERROR,"Address should be at least 3 characters long").show();
             txtAddress.requestFocus();
             return;
         }

         if (btnSave.getText().equalsIgnoreCase("save")){
             /*Save employee*/

             try {
                 if (existEmployee(emp_id)){
                     new Alert(Alert.AlertType.ERROR,emp_id+"already exists").show();

                 }
                 boolean isSave = employeeBO.saveEmployee(new EmployeeDTO(emp_id,emp_name,emp_dob,emp_address,jon_title,emp_nic,contact,emp_email,salary));

                 if (isSave){
                     tblEmployee.getItems().add(new EmployeeTM(emp_id,emp_name,emp_dob,emp_address,jon_title,emp_nic,contact,emp_email,salary));
                     new Alert(Alert.AlertType.CONFIRMATION,"employee saved!").show();
                 }

             }catch (SQLException e){
                 new Alert(Alert.AlertType.ERROR,"Failed to save the employee"+e.getMessage()).show();
             }catch (ClassNotFoundException e){
                 e.printStackTrace();
             }
         }else {
             /*update employee*/

             try {
                 if (!existEmployee(emp_id)){
                     new Alert(Alert.AlertType.ERROR,"There is no such employee associated with the emp_id"+emp_id).show();

                 }

                 EmployeeDTO dto = new EmployeeDTO(emp_id,emp_name,emp_dob,emp_address,jon_title,emp_nic,contact,emp_email,salary);
                 employeeBO.updateEmployee(dto);
             }catch (SQLException e){
                 new Alert(Alert.AlertType.ERROR,"Failed to update the employee"+ emp_id + e.getMessage()).show();

             }catch (ClassNotFoundException e ) {
               e.printStackTrace();

             }

             EmployeeTM selectedEmployee = tblEmployee.getSelectionModel().getSelectedItem();
             selectedEmployee.setEmp_id(emp_id);
             selectedEmployee.setEmp_name(emp_name);
             selectedEmployee.setEmp_dob(emp_dob);
             selectedEmployee.setEmp_address(emp_address);
             selectedEmployee.setJob_title(jon_title);
             selectedEmployee.setEmp_nic(emp_nic);
             selectedEmployee.setContact(contact);
             selectedEmployee.setEmp_email(emp_email);
             selectedEmployee.setSalary(salary);
             tblEmployee.refresh();

         }
         btnAddNewEmployee.fire();
    }

    private boolean existEmployee(String emp_id) throws SQLException, ClassNotFoundException {
        return employeeBO.existEmployee(emp_id);

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnAddNewOnAction(ActionEvent event) {

        txtId.setDisable(false);
        txtName.setDisable(false);
        txtDob.setDisable(false);
        txtAddress.setDisable(false);
        txtNic.setDisable(false);
        txtContact.setDisable(false);
        txtEmail.setDisable(false);
        txtSallary.setDisable(false);
        txtId.clear();
        txtId.setText(generateNewId());
        txtName.clear();
        txtDob.clear();
        txtAddress.clear();
        txtJobTitle.clear();
        txtNic.clear();
        txtContact.clear();
        txtEmail.clear();
        txtSallary.clear();
        txtName.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblEmployee.getSelectionModel().clearSelection();

    }

    private String generateNewId() {
        try {
            return employeeBO.generateEmployeeID();

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Failed to generate a new id"+ e .getMessage()).show();

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        if (tblEmployee.getItems().isEmpty()){
            return "E00-001";

        }else {
            String emp_id = getLastEmployeeId();
            int newEmployeeId = Integer.parseInt(emp_id.replace("E",""))+1;
            return String.format("E00-%03d",newEmployeeId);
        }
    }

    private String getLastEmployeeId() {
        List<EmployeeTM> tempEmployeesList = new ArrayList<>(tblEmployee.getItems());
        Collections.sort(tempEmployeesList);
        return tempEmployeesList.get(tempEmployeesList.size() - 1).getEmp_id();

    }

    @FXML
    void btnEmployeeReportOnAction(ActionEvent event) throws JRException, SQLException, ClassNotFoundException {
        printEmployeeReport();
    }

    private void printEmployeeReport() throws JRException, SQLException, ClassNotFoundException {

        String cu_id = this .txtId .getText();
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/employeeR.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT * FROM employee ");
        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, DbConnection.getDbConnection().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }


}

