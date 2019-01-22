package org.sla;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PictureViewer extends Application {
    private PictureViewerController myController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load View from xml description
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PictureViewer.fxml"));
        Parent root = loader.load();
        myController = loader.getController();
        myController.setStage(primaryStage);

        Thread.currentThread().setName("PictureView GUI Thread");

        // Display the scene
        primaryStage.setTitle("Picture Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
