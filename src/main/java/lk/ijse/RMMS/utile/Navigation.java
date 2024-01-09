package lk.ijse.RMMS.utile;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    private static Parent rootNode;
    private static Scene scene;
    private static Stage stage;

    public static void switchPaging(Pane ChangePane, String path, String name) throws IOException {
//        pane.getChildren().clear();
        Parent parent = FXMLLoader.load(Navigation.class.getResource("/view/"+path));
        ChangePane.getChildren().clear();
        ChangePane.getChildren().add(parent);
    }

}
