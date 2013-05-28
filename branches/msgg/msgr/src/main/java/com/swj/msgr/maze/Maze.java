package com.swj.msgr.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

public class Maze {
	
	private static final Logger logger = Logger.getLogger(Maze.class);
	
	private int w;
	private int h;
	
	private int totalCells ;
	private int visitedCells = 1;
	
	private Cell[][] cells;
	
	private Stack<Cell> visitedCellHistroy = new Stack<Cell>();

	public Maze(int w, int h) {
		this.w = w;
		this.h = h;
		this.totalCells = w*h;
		
		initCells();
		mazePathFind(selectStartPoint(w, h));
	}
	
	private void initCells() {

		cells = new Cell[w][h];

		for (int i=0; i< w; i++) {
			for (int j=0; j< h; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}
	
	private Cell selectStartPoint(int w, int h) {
		return cells[(int) Math.floor(Math.random()*w)][(int) Math.floor(Math.random()*w)];
	}
	
    /*
    private void mazePathFind(Cell cell)
    {
            if (visitedCells >= totalCells) return;

            	Cell[] neighborCells = getNeighbor(cell);
                    int length = neighborCells.length;
                    
                    if(length > 0)
                    {
                            visitedCells ++;
                            if(length>1)
                            {
                                    visitedCellHistroy.push(cell);
                            }
                            Cell nextCell = neighborCells[(int) Math.floor(Math.random()*length)];
                            //cell.knowkDown(nextCell);
                            knowkDown(cell, nextCell);
                            mazePathFind(nextCell);
                            //cell = nextCell;
                    }
                    else
                    {
                    	mazePathFind( visitedCellHistroy.pop());
                    }

    }*/

	int order =0;
    private void mazePathFind(Cell cell)
    {
    	while (visitedCells < totalCells) { 

        Cell[] neighborCells = getNeighbor(cell);
        
        int length = neighborCells.length;
        
        if (length == 0) {
        	cell = (visitedCellHistroy.pop());
        	continue;
        }
        
        visitedCells ++;
        
        if (length > 1)
        	visitedCellHistroy.push(cell);
        
        int tmp = (int) Math.floor(Math.random()*length);
        
        Cell nextCell = neighborCells[tmp];

        knowkDown(cell, nextCell);
        cell =  nextCell;
    	}
       
    }
    
    private Cell[] getNeighbor(Cell cell)
    {
            List<Cell> retArr = new ArrayList<Cell>();

            if(cell.getX() - 1 >= 0 && cells[cell.getX() - 1][cell.getY()].isWall() )
                    retArr.add(cells[cell.getX() - 1][cell.getY()]);

            if(cell.getX() + 1 < w && cells[cell.getX() + 1][cell.getY()].isWall() )
                    retArr.add(cells[cell.getX() + 1][cell.getY()]);

            if(cell.getY() - 1 >= 0 && cells[cell.getX()][cell.getY() - 1].isWall() )
                    retArr.add(cells[cell.getX()][cell.getY() - 1]);

            if(cell.getY() + 1 < h && cells[cell.getX()][cell.getY() + 1].isWall() )
                    retArr.add(cells[cell.getX()][cell.getY() + 1]);
            return retArr.toArray(new Cell[]{});
    }
    
    private void knowkDown(Cell cell, Cell nextCell) {
    	
        if(cell.getX() < nextCell.getX())
        {
                //right
        		cell.setE(0);
        		nextCell.setW(0);
        }
        else if(cell.getX() > nextCell.getX())
        {
                //left
        	cell.setW(0);
        	nextCell.setE(0);
        }
        else if(cell.getY() < nextCell.getY())
        {
                //down
        	cell.setS(0);
        	nextCell.setN(0);
        }
        else
        {
                //up
        	cell.setN(0);
        	nextCell.setS(0);
        }
    	
    	
    }
	
	public Cell[][] getCells() {
		return cells;
	}


}
