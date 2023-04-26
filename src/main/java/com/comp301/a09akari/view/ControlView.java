package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class ControlView implements FXComponent {

  AlternateMvcController controller;

  public ControlView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.getStyleClass().add("controls-layout");

    TilePane tp = new TilePane();
    tp.getStyleClass().add("tile-pane");

    Button shuffleButton = new Button("Shuffle");
    shuffleButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    shuffleButton.getStyleClass().add("shuffle-button");

    Button next = new Button(">");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    next.getStyleClass().add("next-button");

    Button prev = new Button("<");
    prev.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    prev.getStyleClass().add("prev-button");

    tp.getChildren().add(prev);
    tp.getChildren().add(shuffleButton);
    tp.getChildren().add(next);

    HBox reset_frame = new HBox();
    reset_frame.getStyleClass().add("reset-frame");

    Button resetButton = new Button("Reset");
    resetButton.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    resetButton.getStyleClass().add("reset-button");

    reset_frame.getChildren().add(resetButton);

    layout.getChildren().add(tp);
    layout.getChildren().add(reset_frame);
    return layout;
  }
}
