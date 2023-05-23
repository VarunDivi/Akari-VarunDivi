package com.app.akari.model;

public interface Model {
  
  void addLamp(int r, int c);

  void removeLamp(int r, int c);

  boolean isLit(int r, int c);

  boolean isLamp(int r, int c);

  boolean isLampIllegal(int r, int c);

  Puzzle getActivePuzzle();

  int getActivePuzzleIndex();

  /**
   * Setter method for the current active Puzzle index. If the passed index is out of bounds, this
   * method should throw an IndexOutOfBoundsException
   */
  void setActivePuzzleIndex(int index);

  /** Getter method for the number of puzzles contained in the internal PuzzleLibrary */
  int getPuzzleLibrarySize();

  /** Resets the active puzzle by removing all lamps which have been placed */
  void resetPuzzle();

  /**
   * Returns true if the active puzzle is solved (i.e. every clue is satisfied and every corridor is
   * illuminated)
   */
  boolean isSolved();

  /**
   * Returns true if the clue located at row r, column c of the active puzzle is satisfied (i.e. has
   * exactly the number of lamps adjacent as is specified by the clue). Throws an
   * IndexOutOfBoundsException if r or c is out of bounds, or an IllegalArgumentException if the
   * cell is not type CLUE
   */
  boolean isClueSatisfied(int r, int c);

  /** Adds an observer to the model */
  void addObserver(ModelObserver observer);

  /** Removes an observer from the model */
  void removeObserver(ModelObserver observer);
}
