package com.example.kalah.model;

import com.example.kalah.exception.InvalidActionException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameBoardTest {

    @Test
    public void newBoard() {
        GameBoard newBoard = new GameBoard(0);
        assertFalse(newBoard.isEnded());
        assertEquals(0, newBoard.getTurn());
        assertEquals(0, newBoard.getPlayer0Store());
        assertEquals(0, newBoard.getPlayer1Store());
        assertEquals(2, newBoard.getSpots().length);
        assertEquals(6, newBoard.getSpots()[0].length);
        assertEquals(6, newBoard.getSpots()[1].length);
        for (int i = 0; i < newBoard.getSpots().length; i++) {
            for (int j = 0; j < newBoard.getSpots()[i].length; j++) {
                assertEquals(6, newBoard.getSpots()[i][j]);
            }
        }
    }

    @Test(expected = InvalidActionException.class)
    public void invalidActionMove1() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.move(1, 0);
    }

    @Test(expected = InvalidActionException.class)
    public void invalidActionMove2() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.move(0, 10);
    }

    @Test(expected = InvalidActionException.class)
    public void invalidActionMove3() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.setEnded(true);
        newBoard.move(0, 0);
    }

    @Test(expected = InvalidActionException.class)
    public void invalidActionMove4() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.move(2, 0);
    }

    @Test
    public void gameEnded1() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.setSpots(new int[][]{{1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0}});
        newBoard.move(0, 0);
        assertTrue(newBoard.isEnded());
        assertEquals(1, newBoard.getPlayer0Store());
        assertEquals(3, newBoard.getPlayer1Store());
        for (int i = 0; i < newBoard.getSpots().length; i++) {
            for (int j = 0; j < newBoard.getSpots()[i].length; j++) {
                assertEquals(0, newBoard.getSpots()[i][j]);
            }
        }
    }

    @Test
    public void gameEnded2() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.setSpots(new int[][]{{0, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0}});
        newBoard.move(0, 1);
        assertTrue(newBoard.isEnded());
        assertEquals(2, newBoard.getPlayer0Store());
        assertEquals(2, newBoard.getPlayer1Store());
        for (int i = 0; i < newBoard.getSpots().length; i++) {
            for (int j = 0; j < newBoard.getSpots()[i].length; j++) {
                assertEquals(0, newBoard.getSpots()[i][j]);
            }
        }
    }

    @Test
    public void gameEnded3() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.setSpots(new int[][]{{2, 1, 1, 1, 1, 1}, {0, 0, 0, 0, 0, 8}});
        newBoard.setTurn(1);
        newBoard.move(1, 5);
        assertEquals(10, newBoard.getPlayer0Store());
        assertEquals(5, newBoard.getPlayer1Store());
        assertEquals(0, newBoard.getTurn());
        assertTrue(newBoard.isEnded());
    }

    @Test
    public void move1() {
        GameBoard newBoard = new GameBoard(0);
        newBoard.move(0, 5);
        assertEquals(0, newBoard.getSpots()[0][5]);
        assertEquals(7, newBoard.getSpots()[0][4]);
        assertEquals(7, newBoard.getSpots()[0][3]);
        assertEquals(7, newBoard.getSpots()[0][2]);
        assertEquals(7, newBoard.getSpots()[0][1]);
        assertEquals(7, newBoard.getSpots()[0][0]);
        assertEquals(1, newBoard.getPlayer0Store());
        assertEquals(0, newBoard.getTurn());
        newBoard.move(0, 4);
        assertEquals(0, newBoard.getSpots()[0][4]);
        assertEquals(8, newBoard.getSpots()[0][3]);
        assertEquals(8, newBoard.getSpots()[0][2]);
        assertEquals(8, newBoard.getSpots()[0][1]);
        assertEquals(8, newBoard.getSpots()[0][0]);
        assertEquals(7, newBoard.getSpots()[1][0]);
        assertEquals(7, newBoard.getSpots()[1][1]);
        assertEquals(2, newBoard.getPlayer0Store());
        assertEquals(1, newBoard.getTurn());
    }
}