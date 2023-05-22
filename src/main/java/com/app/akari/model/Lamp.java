package com.app.akari.model;

public class Lamp {

  private final int r;
  private final int c;

  public Lamp(int r, int c) {
    if (r >= 0 && c >= 0) {
      this.r = r;
      this.c = c;
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  public int getRow() {
    return this.r;
  }

  public int getCol() {
    return this.c;
  }
}
