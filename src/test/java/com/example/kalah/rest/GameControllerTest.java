package com.example.kalah.rest;

import com.example.kalah.KalahApplication;
import com.example.kalah.model.GameBoard;
import com.example.kalah.rest.dto.MoveAction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = KalahApplication.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void newGame() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/api/game/new")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        JSONAssert.assertEquals(new ObjectMapper().writeValueAsString(new GameBoard(0)), result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void move() throws Exception {
        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/api/game/move")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new MoveAction(0, 0)))).andReturn();
        Assert.assertEquals(404, result.getResponse().getStatus());
    }
}