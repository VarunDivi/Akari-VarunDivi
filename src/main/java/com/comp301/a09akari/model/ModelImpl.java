package com.comp301.a09akari.model;

import java.io.ObjectStreamException;
import java.util.ArrayList;

public class ModelImpl implements Model{

    private PuzzleLibrary library;
    private final ArrayList<ModelObserver> modelObservers;
    private Puzzle currentPuzzle;
    private int puzzleIndex;
    private ArrayList<Lamp> lampList = new ArrayList<Lamp>();

    public ModelImpl(PuzzleLibrary library){
        if(library == null){
            throw new IllegalArgumentException();
            }
        this.library = library;
        modelObservers = new ArrayList<ModelObserver>();
        puzzleIndex = 0;
    }

    @Override
    public void addLamp(int r, int c) {
        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(getActivePuzzle().getCellType(r,c) == CellType.CORRIDOR){
            lampList.add(new Lamp(r,c));
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeLamp(int r, int c) {
        Lamp toRemove = null;
        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }

        if(getActivePuzzle().getCellType(r,c) == CellType.CORRIDOR && isLamp(r,c)) {
            for (Lamp l : lampList) {
                if (l.getRow() == r && l.getCol() == c) {
                    toRemove = l;
                }

            }
        }
        else{
            throw new IllegalArgumentException();
        }

        if(toRemove != null){
            lampList.remove(toRemove);
        }

    }

    @Override
    public boolean isLit(int r, int c) {
        for(Lamp l : lampList){

        }
        return false;
    }

    @Override
    public boolean isLamp(int r, int c) {
        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }


        if(getActivePuzzle().getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }

        for(Lamp l : lampList){
            if(l.getRow() == r && l.getCol() == c){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        int count = 0;
        ArrayList<Lamp> lampListCopy = new ArrayList<Lamp>(lampList);

        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }

        if(!isLamp(r,c)){
            throw new IllegalArgumentException();
        }
        lampListCopy.remove(new Lamp(r,c));

        for(Lamp l : lampListCopy){
            if(l.getCol() == c && l.getRow() == r){
                return true;
            }
        }
//        if(count > 1){
//            return true;
//        }



        for(Lamp l : lampListCopy){
            if(l.getCol() == c){
                if(l.getRow() < r){
                    for(int i = l.getRow() ; i < r ; i++) {
                        if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
                            return false;
                        }
                    }
                }
                if(l.getRow() > r){
                    for(int i = r ; i < l.getRow() ; i++) {
                        if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
                            return false;
                        }
                    }
                }
                return true;
            }
            if(l.getRow() == r){
                if(l.getCol() < c){
                    for(int i = l.getCol() ; i < c ; i++) {
                        if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
                            return false;
                        }
                    }
                }
                if(l.getCol() > c){
                    for(int i = c ; i < l.getCol() ; i++) {
                        if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public Puzzle getActivePuzzle() {
        return library.getPuzzle(getActivePuzzleIndex());
    }

    @Override
    public int getActivePuzzleIndex() {
        return puzzleIndex;
    }

    @Override
    public void setActivePuzzleIndex(int index) {
        if(index >= 0 && index < library.size()) {
            puzzleIndex = index;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getPuzzleLibrarySize() {
        return library.size();
    }

    @Override
    public void resetPuzzle() {

    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        return false;
    }

    @Override
    public void addObserver(ModelObserver observer) {
        modelObservers.add(observer);
    }

    @Override
    public void removeObserver(ModelObserver observer) {
        modelObservers.remove(observer);
    }


    private void notifyObservers() {
        for (ModelObserver observer : modelObservers) {
            observer.update(this);
        }
    }

}
