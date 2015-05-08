package edu.chl.Game.entity;

import edu.chl.Game.handler.GameHandler;

public class UnitProperties {
	
	private int x, y;
	private int velX, velY;
	private int width, height;
	private double healthPoints;
	private GameHandler handler;
	
	public UnitProperties(GameHandler handler, int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.handler = handler;
		this.healthPoints = 100.0;
	}
	
	public int getVelX(){
		return velX;
	}
	
	public void setVelX(int velX){
		this.velX = velX;
	}
	
	public int getVelY(){
		return velY;
	}
	
	public void setVelY(int vely){
		this.velY = vely;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public GameHandler getHandler(){
		return handler;
	}
	
	public double getHealthPoints(){
		return healthPoints;
	}
	
	public void setHealthPoints(double healthPoints){
		this.healthPoints = healthPoints;
	}
	
}
