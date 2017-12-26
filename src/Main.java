import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void init()
    {

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Viewer/Layout.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Viewer/MyStyle.css");


        //scene.getStylesheets().add(getClass().getResource("MyStyle.css").toExternalForm());
        primaryStage.setTitle("Earthquake Search");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Images/icon.png")));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}