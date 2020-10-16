package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.LoadException;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dictionary");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("appllication.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        /*DictionaryCommandline moi = new DictionaryCommandline();
        moi.dictionaryBasic();
        moi.dictionaryAdvanced();
        moi.showAllWord();
         */ 
    }
}
