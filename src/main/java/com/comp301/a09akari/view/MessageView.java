package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MessageView implements FXComponent {

  AlternateMvcController controller;

  public MessageView(AlternateMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox vb = new VBox();
    vb.getStyleClass().add("message-box");

    Label puzzleNum =
        new Label(
            "Puzzle #: "
                + (((ControllerImpl) controller).getActivePuzzleIndex() + 1)
                + "/"
                + ((ControllerImpl) controller).getPuzzleLibrarySize());
    puzzleNum.getStyleClass().add("puz-num");
    vb.getChildren().add(puzzleNum);

    Label isSolved;
    if (controller.isSolved()) {
      isSolved = new Label("Puzzle Solved!");
      isSolved.getStyleClass().add("solved");
    } else {
      isSolved = new Label("Puzzle in progress");
      isSolved.getStyleClass().add("unsolved");
    }

    vb.getChildren().add(isSolved);

    return vb;
  }
}
