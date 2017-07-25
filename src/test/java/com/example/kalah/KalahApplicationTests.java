package com.example.kalah;

import com.example.kalah.rest.GameController;
import com.example.kalah.store.GameBoardStore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KalahApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(applicationContext.getBean(GameController.class));
        Assert.assertNotNull(applicationContext.getBean(GameBoardStore.class));
    }
}
