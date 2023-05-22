package com.app.akari.view;

import com.app.akari.controller.AlternateMvcController;
import com.app.akari.controller.ControllerImpl;
import com.app.akari.model.CellType;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class PuzzleView implements FXComponent {

  AlternateMvcController controller;

  public PuzzleView(AlternateMvcController controller) {
    this.controller = controller;
  }

  private Button makeButton(int r, int c) {
    Button click;

    if (controller.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      click = new Button("");
      click.setOnAction(
          (ActionEvent event) -> {
            controller.clickCell(r, c);
          });

      if (controller.isLamp(r, c)) {
        click.getStyleClass().add("tile_lamp");
      }
      if (((ControllerImpl) controller).isLampIllegal(r, c)) {
        click.getStyleClass().add("tile_illegal");
      }
      if (controller.isLit(r, c)) {
        if (!controller.isLamp(r, c)) {
          click.getStyleClass().add("tile_lit");
        }
      }
    } else if (controller.getActivePuzzle().getCellType(r, c) == CellType.CLUE) {
      if (controller.isClueSatisfied(r, c)) {
        click = new Button(String.valueOf(controller.getActivePuzzle().getClue(r, c)));
        click.getStyleClass().add("clue_solved");
      } else {
        click = new Button(String.valueOf(controller.getActivePuzzle().getClue(r, c)));
        click.getStyleClass().add("tile_clue");
      }
    } else {
      click = new Button("");
      click.getStyleClass().add("tile_wall");
    }

    click.getStyleClass().add("tile");

    return click;
  }

  @Override
  public Parent render() {
    StackPane layout = new StackPane();
    layout.getStyleClass().add("grid-layout");

    GridPane board = new GridPane();
    board.setHgap(10);
    board.setVgap(10);
    board.getStyleClass().add("board");

    int width = controller.getActivePuzzle().getWidth();
    int height = controller.getActivePuzzle().getHeight();

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        // Ability to change row and col order
        board.add(makeButton(j, i), i, j);
      }
    }

    // layout.getChildren().add(click);

    layout.getChildren().add(board);

    return layout;
  }
}
