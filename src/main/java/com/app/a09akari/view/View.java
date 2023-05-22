package com.app.a09akari.view;

import com.app.a09akari.controller.AlternateMvcController;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  AlternateMvcController controller;

  public View(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    ControlView controlView = new ControlView(controller);
    PuzzleView puzzleView = new PuzzleView(controller);
    MessageView messageView = new MessageView(controller);

    layout.getChildren().add(messageView.render());
    layout.getChildren().add(puzzleView.render());
    layout.getChildren().add(controlView.render());

    layout.getStyleClass().add("whole-layout");

    return layout;
  }
}
