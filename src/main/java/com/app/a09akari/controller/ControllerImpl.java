package com.app.a09akari.controller;

import com.app.a09akari.model.CellType;
import com.app.a09akari.model.Model;
import com.app.a09akari.model.Puzzle;

public class ControllerImpl implements AlternateMvcController {

  Model model;

  public ControllerImpl(Model model) {
    if (model != null) {
      this.model = model;
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public void clickNextPuzzle() {
    int ind = model.getActivePuzzleIndex();
    if (ind < model.getPuzzleLibrarySize() - 1) {
      model.setActivePuzzleIndex(ind + 1);
    } else {
      model.setActivePuzzleIndex(0);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    int ind = model.getActivePuzzleIndex();
    if (ind > 0) {
      model.setActivePuzzleIndex(ind - 1);
    } else {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {
    int min = 0;
    int max = model.getPuzzleLibrarySize() - 1;
    int random_index = (int) Math.floor(Math.random() * (max - min + 1) + min);

    while (random_index == getActivePuzzleIndex()) {
      random_index = (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    model.setActivePuzzleIndex(random_index);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    //        if(r < 0 || c < 0 || r >= model.getPuzzleLibrarySize() || c >=
    // model.getPuzzleLibrarySize()){
    //            throw new IndexOutOfBoundsException();
    //        }

    if (model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (isLamp(r, c)) {
        model.removeLamp(r, c);
      } else {
        model.addLamp(r, c);
      }
    }

    System.out.println("Clicked");
  }

  @Override
  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  @Override
  public boolean isLamp(int r, int c) {
    //        if(r < 0 || c < 0 || r >= model.getPuzzleLibrarySize() || c >=
    // model.getPuzzleLibrarySize()){
    //            throw new IndexOutOfBoundsException();
    //        }
    return model.isLamp(r, c);
  }

  public boolean isLampIllegal(int r, int c) {
    if (isLamp(r, c)) {
      return model.isLampIllegal(r, c);
    }

    return false;
  }

  public int getActivePuzzleIndex() {
    return model.getActivePuzzleIndex();
  }

  public int getPuzzleLibrarySize() {
    return model.getPuzzleLibrarySize();
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public boolean isSolved() {
    return model.isSolved();
  }

  @Override
  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }
}
