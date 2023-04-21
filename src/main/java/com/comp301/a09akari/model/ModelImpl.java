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

        for(Lamp l : lampList){
            if(l.getRow() == r && l.getCol() == c){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isLampIllegal(int r, int c) {
        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }

        if(!isLamp(r,c)){
            throw new IllegalArgumentException();
        }

        for(Lamp l : lampList){
            if(l.getCol() == c){
                return true;
            }
            if(l.getRow() == r){
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
