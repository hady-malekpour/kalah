package com.example.kalah.rest.dto;


public class MoveAction {

    public MoveAction(int playerIndex, int index) {
        this.playerIndex = playerIndex;
        this.index = index;
    }

    public MoveAction() {
    }

    private int playerIndex;
    private int index;


    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
