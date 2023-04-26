package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle{

    int[][] board;

    public PuzzleImpl(int[][] board){
        if(board == null){
            throw new IllegalArgumentException();
        }
        this.board = board;
    }

    @Override
    public int getWidth() {
        return board[0].length;
    }

    @Override
    public int getHeight() {
        return board.length;
    }

    @Override
    public CellType getCellType(int r, int c) {
        if(r >= getHeight() && c >= getWidth()){
            throw new IndexOutOfBoundsException();
        }

        if(board[r][c] == 6){
            return CellType.CORRIDOR;
        }
        else if(board[r][c] == 5){
            return CellType.WALL;
        }
        else{
            switch(board[r][c]){
                case 0:
                    return CellType.CLUE;
                case 1:
                    return CellType.CLUE;
                case 2:
                    return CellType.CLUE;
                case 3:
                    return CellType.CLUE;
                case 4:
                    return CellType.CLUE;
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public int getClue(int r, int c) {

        if(r >= board.length && c >= board[r].length){
            throw new IndexOutOfBoundsException();
        }
        if(board[r][c] == 6 || board[r][c] == 5){
            throw new IllegalArgumentException();
        }

        return board[r][c];
    }
}
