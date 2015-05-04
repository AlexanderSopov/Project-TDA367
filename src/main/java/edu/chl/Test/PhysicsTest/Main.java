package edu.chl.Test.PhysicsTest;


import edu.chl.Game.GameObject.Circle;
import edu.chl.Game.GameObject.Box;
import edu.chl.Game.GameObject.Circle;
import edu.chl.Game.GameObject.StaticBox;



public class Main  {
	public static GameThread run;
	public static Circle c1;
	public static Circle c2;
	public static Circle c3;
	public static Box b1;
	public static StaticBox b2;
	
	
	public static void main(String[] args) {
		GameThread run = new GameThread();
		run.start();
		c1 = new Circle(50 ,150, 50, new Float(0.5), 10);
		c2 = new Circle(800, 400, 150, new Float(0.80),500);
		c3 = new Circle(1000, 1500, 150, new Float(0.85), 300);
		b1 = new Box(950,400,150, 80,  new Float(0.1), 50);
		b2 = new StaticBox(50, 800, 1500, 50, new Float(0.2), 0);
		run.addObserver(b1);
		run.addObserver(b2);
		run.addObserver(c1);
		run.addObserver(c2);
		run.addObserver(new Handler());
	}
	
	
}
