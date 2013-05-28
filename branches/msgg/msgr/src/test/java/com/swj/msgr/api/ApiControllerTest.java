package com.swj.msgr.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.swj.msgr.config.MsgrWebAppInitializer;
import com.swj.msgr.maze.Cell;
import com.swj.msgr.maze.Maze;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=MsgrWebAppInitializer.class)
public class ApiControllerTest {

	@Test
	public void test() {
		
		
		
		Cell[][] cells = new Maze(80, 80).getCells();
		
		
		
		
		
	}

}
