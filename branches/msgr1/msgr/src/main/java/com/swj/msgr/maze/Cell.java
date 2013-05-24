package com.swj.msgr.maze;

public class Cell {

	private int n=1;
	private int e=1;
	private int s=1;
	private int w=1;
	
	private int x;
	private int y;
	
	

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getN() {
		return n;
	}

	public int getE() {
		return e;
	}

	public int getS() {
		return s;
	}

	public int getW() {
		return w;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void setE(int e) {
		this.e = e;
	}

	public void setS(int s) {
		this.s = s;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getData() {
		return (this.w << 3) + (this.s << 2) + (this.e << 1) + this.n;
	}

	public boolean isWall() {
		return (getData() == 15);
	}
	
}
