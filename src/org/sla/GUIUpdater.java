package org.sla;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIUpdater implements Runnable {
    private SynchronizedQueue originalQueue;
    private ImageView GUIimageview;


    GUIUpdater(SynchronizedQueue queue, ImageView imageview) {
        originalQueue = queue;
        GUIimageview = imageview;
    }

    public void run() {
        Thread.currentThread().setName("GUIUpdater Thread");

        while (!Thread.interrupted()) {
            // Ask queue for a file to open
            Image next = (Image)originalQueue.get();
            while (next == null) {
                Thread.currentThread().yield();
                next = (Image)originalQueue.get();
            }
            // FINALLY I have a file to do something with
            GUIimageview.setImage(next);
        }
    }

}
