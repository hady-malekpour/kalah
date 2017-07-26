package com.example.kalah.rest;

import com.example.kalah.model.GameBoard;
import com.example.kalah.rest.dto.MoveAction;
import com.example.kalah.store.GameBoardStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameBoardStore gameBoardStore;

    @Captor
    private ArgumentCaptor<GameBoard> argumentCaptor;

    @Test
    public void newGame() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/game/new")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        verify(gameBoardStore).add(argumentCaptor.capture());
        JSONAssert.assertEquals(new ObjectMapper().writeValueAsString(argumentCaptor.getValue()), result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void move() throws Exception {
        final GameBoard gameBoard = new GameBoard(0);
        given(gameBoardStore.get(1)).willReturn(gameBoard);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/api/game/move")
                .contentType(MediaType.APPLICATION_JSON).sessionAttr(GameController.ID, 1).content(new ObjectMapper().writeValueAsString(new MoveAction(0, 0)))).andReturn();

        JSONAssert.assertEquals(new ObjectMapper().writeValueAsString(gameBoard), result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void get() throws Exception {
        final GameBoard gameBoard = new GameBoard(0);
        given(gameBoardStore.get(1)).willReturn(gameBoard);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/api/game/")
                .contentType(MediaType.APPLICATION_JSON).sessionAttr(GameController.ID, 1)).andReturn();
        JSONAssert.assertEquals(new ObjectMapper().writeValueAsString(gameBoard), result.getResponse()
                .getContentAsString(), false);
    }
}