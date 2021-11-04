/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */

package baseline;

import javafx.scene.layout.Pane;

public class TabVisibility {

    public void makeVisible(Pane pane){
        // Will make a particular pane appear based on what tab button is clicked
        pane.toFront();
    }
}
