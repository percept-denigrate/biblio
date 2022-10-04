package com.example.demojfx2;

import javafx.event.Event;
import javafx.event.EventHandler;

public class MenuHandler implements  EventHandler{
    private HelloController controller;

    public MenuHandler(HelloController controller){
        this.controller = controller;
    }

    @Override
    public void handle(Event event) {
        this.controller.appuiSurOpen();
    }
}
