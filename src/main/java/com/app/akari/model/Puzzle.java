package com.app.akari.model;

public interface Puzzle {
  int getWidth();

  int getHeight();

 
  CellType getCellType(int r, int c);


  int getClue(int r, int c);
}
