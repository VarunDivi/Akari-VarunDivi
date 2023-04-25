package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class ControlView implements FXComponent{

    AlternateMvcController controller;

    public ControlView(AlternateMvcController controller) {
        this.controller = controller;
    }



    @Override
    public Parent render() {
        StackPane layout = new StackPane();
        layout.getStyleClass().add("controls-layout");

        HBox hb = new HBox();
        layout.getStyleClass().add("hbox-pane");

        Label puzzleNum = new Label("Puzzle #: " + String.valueOf(((ControllerImpl) controller).getActivePuzzleIndex()));
        puzzleNum.getStyleClass().add("puz-num");
        hb.getChildren().add(puzzleNum);


        TilePane tp = new TilePane();
        tp.getStyleClass().add("tile-pane");

        Button shuffleButton = new Button("Shuffle");
        shuffleButton.setOnAction((ActionEvent event) -> {
            controller.clickRandPuzzle();
        });
        shuffleButton.getStyleClass().add("shuffle-button");


        Button next = new Button(">");
        next.setOnAction((ActionEvent event) -> {
            controller.clickNextPuzzle();
        });
        next.getStyleClass().add("next-button");


        Button prev = new Button("<");
        prev.setOnAction((ActionEvent event) -> {
            controller.clickPrevPuzzle();
        });
        prev.getStyleClass().add("prev-button");


        tp.getChildren().add(prev);
        tp.getChildren().add(shuffleButton);
        tp.getChildren().add(next);

        layout.getChildren().add(hb);
        layout.getChildren().add(tp);
        return layout;
    }
}
