package com.example.kalah.model;

import com.example.kalah.exception.InvalidActionException;

/**
 *
 * This class represents Kalah Game as a 2-dimension array {@link #spots} as follow
 * [ [6, 6, 6, 6, 6, 6], [6, 6, 6, 6, 6, 6]] which means the starting stage of the game.
 * The element[0] which is a 1-dimension array of 6 elements belong to player#0 and the element[1] belong to player#1
 *
 * - {@link #player0Store} and {@link #player1Store} shows how many stones is stored for any players
 * - {@link #turn} shows who should make next move
 * - {@link #ended} shows if the game is ended or not
 */
public class GameBoard {
    private int[][] spots;
    private int player0Store;
    private int player1Store;
    private int turn;
    private boolean ended;

    public GameBoard(){
        this(0);
    }

    public GameBoard(int startTurn) {
        setSpots(new int[2][6]);
        setPlayer0Store(0);
        setPlayer1Store(0);
        setEnded(false);
        this.setTurn(startTurn);
        for (int i = 0; i < getSpots().length; i++) {
            for (int j = 0; j < getSpots()[i].length; j++) {
                getSpots()[i][j] = 6;
            }
        }
    }

    public int[][] getSpots() {
        return spots;
    }

    public void setSpots(int[][] spots) {
        this.spots = spots;
    }

    public int getPlayer0Store() {
        return player0Store;
    }

    public void setPlayer0Store(int player0Store) {
        this.player0Store = player0Store;
    }

    public int getPlayer1Store() {
        return player1Store;
    }

    public void setPlayer1Store(int player1Store) {
        this.player1Store = player1Store;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public void move(int playerNumber, int spotIndex) {
        if (playerNumber < 0 || playerNumber > 1) {
            throw new InvalidActionException(String.format("PlayerNumber#%s is invalid", playerNumber));
        }
        if (spotIndex < 0 || spotIndex >= spots[0].length) {
            throw new InvalidActionException(String.format("Invalid spotIndex#%s", spotIndex));
        }
        if (getSpots()[playerNumber][spotIndex] == 0) {
            throw new InvalidActionException(String.format("There is no stone for PlayerNumber#%s at spot#%s to move", playerNumber, spotIndex));
        }
        if (playerNumber != getTurn()) {
            throw new InvalidActionException(String.format("Right now is turn of PlayerNumber#%s and not PlayerNumber#%s", getTurn(), playerNumber));
        }
        if (ended) {
            throw new InvalidActionException("Game has already ended");
        }
        int stones = getSpots()[playerNumber][spotIndex];
        getSpots()[playerNumber][spotIndex] = 0;
        int step = playerNumber == 0 ? -1 : 1;
        int index = spotIndex + step;
        int row = playerNumber;
        boolean endWithAnotherMove = false;
        while (stones > 0) {
            if (index < 0) {
                index = 0;
                step = 1;
                row = 1;
                if (playerNumber == 0) {
                    setPlayer0Store(getPlayer0Store() + 1);
                    endWithAnotherMove = true;
                    stones--;
                }
            } else if (index >= getSpots()[0].length) {
                index = getSpots()[0].length - 1;
                step = -1;
                row = 0;
                if (playerNumber == 1) {
                    setPlayer1Store(getPlayer1Store() + 1);
                    endWithAnotherMove = true;
                    stones--;
                }
            } else {
                getSpots()[row][index]++;
                index += step;
                endWithAnotherMove = false;
                stones--;
            }
        }
        if (!endWithAnotherMove) {
            index -= +step;
            if (getSpots()[row][index] == 1 && row == playerNumber && getSpots()[opposite(row)][index] > 0) {
                if (playerNumber == 0) {
                    setPlayer0Store(getPlayer0Store() + getSpots()[row][index] + getSpots()[opposite(row)][index]);
                } else {
                    setPlayer1Store(getPlayer1Store() + getSpots()[row][index] + getSpots()[opposite(row)][index]);
                }
                getSpots()[row][index] = 0;
                getSpots()[opposite(row)][index] = 0;
            }
            turn = opposite(turn);
        }
        reorderStonesIfGameEnded(0);
        reorderStonesIfGameEnded(1);
    }

    private void reorderStonesIfGameEnded(int playerIndex) {
        int sum = 0;
        for (int i = 0; i < spots[playerIndex].length; i++) {
            sum += spots[playerIndex][i];
        }
        if (sum == 0) {
            for (int i = 0; i < spots[playerIndex].length; i++) {
                sum += spots[opposite(playerIndex)][i];
                spots[opposite(playerIndex)][i] = 0;
            }
            if (opposite(playerIndex) == 0) {
                player0Store += sum;
            } else {
                player1Store += sum;
            }
            this.setEnded(true);
        }
    }

    private int opposite(int i) {
        return i == 0 ? 1 : 0;
    }
}
