package com.example.kalah.rest;

import com.example.kalah.exception.GameNotFoundException;
import com.example.kalah.model.GameBoard;
import com.example.kalah.rest.dto.MoveAction;
import com.example.kalah.store.GameBoardStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/game/")
public class GameController {

    public static final String ID = "ID";

    @Autowired
    private GameBoardStore gameBoardStore;

    @RequestMapping(method = RequestMethod.POST, value = "new")
    @ResponseBody
    public GameBoard newGame(HttpSession session) {
        final GameBoard gameBoard = new GameBoard(0);
        int id = gameBoardStore.add(gameBoard);
        session.setAttribute(ID, id);
        return gameBoard;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "move")
    @ResponseBody
    public GameBoard move(@RequestBody MoveAction moveAction, HttpSession session) {
        if (session.getAttribute(ID) == null) {
            throw new GameNotFoundException("No game found");
        }
        Integer id = (Integer) session.getAttribute(ID);
        final GameBoard gameBoard = gameBoardStore.get(id);
        gameBoard.move(moveAction.getPlayerIndex(), moveAction.getIndex());
        return gameBoard;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public GameBoard get(HttpSession session) {
        if (session.getAttribute(ID) == null) {
            throw new GameNotFoundException("No game found");
        }
        Integer id = (Integer) session.getAttribute(ID);
        final GameBoard gameBoard = gameBoardStore.get(id);
        return gameBoard;
    }
}
