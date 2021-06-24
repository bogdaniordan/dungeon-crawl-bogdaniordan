package com.codecool.dungeoncrawl.button;

import com.codecool.dungeoncrawl.logic.popups.LoadJSONPopup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LoadJSONFile implements EventHandler<ActionEvent> {

    @Override
    public void handle(javafx.event.ActionEvent actionEvent) {
        LoadJSONPopup.display();
    }
}
