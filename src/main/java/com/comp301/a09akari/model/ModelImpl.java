package com.comp301.a09akari.model;

import java.io.ObjectStreamException;
import java.util.ArrayList;

public class ModelImpl implements Model{

    private PuzzleLibrary library;
    private final ArrayList<ModelObserver> modelObservers;
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

        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }

        if(getActivePuzzle().getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        else{
            if(isLamp(r,c)){
                return true;
            }
        }

        for(int i = r+1; i < getActivePuzzle().getHeight(); i++){

            if(getActivePuzzle().getCellType(i,c) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(i,c) == CellType.CORRIDOR){
                if(isLamp(i,c)){
                    return true;
                }
            }
        }

        //Across column negative
        for(int i = r-1; i >= 0 ; i--){

            if(getActivePuzzle().getCellType(i,c) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(i,c) == CellType.CORRIDOR){
                if(isLamp(i,c)){
                    return true;
                }
            }
        }

        //Across Row Positive
        for(int i = c+1; i < getActivePuzzle().getWidth() ; i++){

            if(getActivePuzzle().getCellType(r,i) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(r,i) == CellType.CORRIDOR){
                if(isLamp(r,i)){
                    return true;
                }
            }
        }

        //Across Row negative
        for(int i = c-1; i >= 0 ; i--){

            if(getActivePuzzle().getCellType(r,i) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(r,i) == CellType.CORRIDOR){
                if(isLamp(r,i)){
                    return true;
                }
            }
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

        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }

        if(!isLamp(r,c)){
            throw new IllegalArgumentException();
        }

        for(Lamp l : lampList){
            if(l.getCol() == c && l.getRow() == r){
                count++;
            }
        }

        if(count > 1){
            return true;
        }



        for(int i = r+1; i < getActivePuzzle().getHeight(); i++){

            if(getActivePuzzle().getCellType(i,c) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(i,c) == CellType.CORRIDOR){
                if(isLamp(i,c)){
                    return true;
                }
            }
        }

        //Across column negative
        for(int i = r-1; i >= 0 ; i--){

            if(getActivePuzzle().getCellType(i,c) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(i,c) == CellType.CORRIDOR){
                if(isLamp(i,c)){
                    return true;
                }
            }
        }

        //Across Row Positive
        for(int i = c+1; i < getActivePuzzle().getWidth() ; i++){

            if(getActivePuzzle().getCellType(r,i) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(r,i) == CellType.CORRIDOR){
                if(isLamp(r,i)){
                    return true;
                }
            }

        }

            //Across Row negative
        for(int i = c-1; i >= 0 ; i--){

            if(getActivePuzzle().getCellType(r,i) != CellType.CORRIDOR){
                break;
            }

            if(getActivePuzzle().getCellType(r,i) == CellType.CORRIDOR){
                if(isLamp(r,i)){
                    return true;
                }
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
        lampList = new ArrayList<Lamp>();

    }

    @Override
    public boolean isSolved() {
        return false;
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
        if(r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()){
            throw new IndexOutOfBoundsException();
        }

        if(getActivePuzzle().getCellType(r,c) != CellType.CLUE){
            throw new IllegalArgumentException();
        }

        //Counting adjacent lamps
        int count = 0;
        for(int i = r - 1; i <= r+1; i++){
            if(i < getActivePuzzle().getHeight() && i >= 0) {
                if(getActivePuzzle().getCellType(i,c) == CellType.CORRIDOR) {
                    if (isLamp(i,c)) {
                        count++;
                    }
                }
            }
        }

        for(int j = c - 1; j <= c + 1; j++){
            if(j < getActivePuzzle().getWidth() && j >= 0) {
                if(getActivePuzzle().getCellType(r,j) == CellType.CORRIDOR) {
                    if (isLamp(r, j)) {
                        count++;
                    }
                }
            }
        }

        if(count == getActivePuzzle().getClue(r,c)){
            return true;
        }
        else{
            return false;
        }

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
