package lk.ijse.D.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.D.dao.DAOFactory;
import lk.ijse.D.dao.custom.LoginDAO;
import lk.ijse.D.dao.custom.impl.LoginDAOImpl;
import lk.ijse.D.utile.EmailSender;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginFromController implements Initializable {

        @FXML
        private Button btnLogin;

        @FXML
        private TextField txtName;

        @FXML
        private PasswordField txtPassword;


      LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.LOGIN);


        @FXML
        void btnLoginOnAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
            boolean isCorrect = loginDAO.isCurrect(txtName.getText(), txtPassword.getText());

            if (isCorrect){
                Parent root = FXMLLoader.load(getClass().getResource("/view/homepage_from.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
                EmailSender.sendEmail("sachiniapsara703@gmail.com","Login Alert",txtName.getText()+" logged into the system");
//sachiniapsara703@gmail.com
                // duvinduthathsara@gmail.com
            }else {
                //new Alert(Alert.AlertType.WARNING,"Wrong Password").show();
                txtPassword.setStyle("-fx-border-color: red;");


            }
        }

        @FXML
        void btnSignUpOnAction(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/view/signup_form.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();


        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
       // txtPassword.setPromptText("PASSWORD");

    }


   // public void txtPasswordOnKeyType(KeyEvent keyEvent) {
      //  txtPassword.setStyle("-fx-border-color: transparent;");
    //}

    public void txtPasswordOnKeyTyped(KeyEvent keyEvent) {

            txtPassword.setStyle("-fx-border-color: transparent;");
    }
}


