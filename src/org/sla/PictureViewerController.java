package org.sla;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PictureViewerController {
    public ImageView senderImage;
    public ImageView receiverImage;
    public Button openButton;
    public Button sendButton;

    private SynchronizedQueue myQueue;
    private Stage stage;

    public void initialize() {
        // Create the queue that will be used for communication
        // between the GUI thread that reacts to User Interaction and the GUIUpdater that updates the GUI
        myQueue = new SynchronizedQueue();

        // Create and start the GUI updater thread
        GUIUpdater updater = new GUIUpdater(myQueue, receiverImage);
        Thread updaterThread = new Thread(updater);
        updaterThread.start();
    }

    public void setStage(Stage theStage) {
        stage = theStage;
    }

    public void openPicture() {
        // Show a FileChooser
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);

        // If user chose a file via FileChooser
        if (file != null) {
            Image newImage = new Image(file.toURI().toString());
            senderImage.setImage(newImage);
        }
    }

    public void sendPicture() {
        Image imageToSend = senderImage.getImage();

        if (imageToSend != null) {
            while (!myQueue.put(imageToSend)) {
                Thread.currentThread().yield();
            }
        }

    }

}
