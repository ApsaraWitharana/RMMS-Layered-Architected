package lk.ijse.RMMS.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.RMMS.dto.LoginDTO;
import lk.ijse.RMMS.bo.BOFactory;
import lk.ijse.RMMS.bo.custom.SignUpBO;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpFromController implements Initializable {

        @FXML
        private Button btnSignup;

        @FXML
        private TextField txEmail;

        @FXML
        private TextField txtConfromPassword;

        @FXML
        private TextField txtPassword;

        @FXML
        private TextField txtUserName;


        SignUpBO signUpBO = (SignUpBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SIGNUP);
      //    SignUpDAO signUpDAO = (SignUpDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SIGNUP);

        @FXML
        void btnSignUpOnAction(ActionEvent event) {


            boolean isSaved = false;
            if (txtPassword.getText().equals(txtConfromPassword.getText())){

                System.out.println("in");
                try {
                    LoginDTO loginDTO = new LoginDTO(txtUserName.getText(), txEmail.getText(), txtPassword.getText());
                    isSaved = signUpBO.saveUser(loginDTO);


                    if (isSaved) {
                        Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.centerOnScreen();
                        stage.show();

                    } else {
                        new Alert(Alert.AlertType.WARNING, "Wrong Password").show();

                        txtUserName.clear();
                        txtPassword.clear();
                        txEmail.clear();

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }else {

                new Alert(Alert.AlertType.WARNING, "passwords doesn't match").show();

            }



        }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


