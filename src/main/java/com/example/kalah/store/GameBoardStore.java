package com.example.kalah.store;

import com.example.kalah.model.GameBoard;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class GameBoardStore {
    private ConcurrentMap<Integer, GameBoard> store;
    private int idGenerator;

    private GameBoardStore() {
        store = new ConcurrentHashMap<>();
        idGenerator = 0;
    }

    public synchronized int add(GameBoard gameBoard) {
        idGenerator++;
        store.put(idGenerator, gameBoard);
        return idGenerator;
    }

    public synchronized GameBoard get(int id) {
        return store.get(id);
    }
}
