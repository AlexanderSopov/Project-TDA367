package edu.chl.Test.PhysicsTest;

import java.util.Observable;
import java.util.Observer;

import edu.chl.Test.GameObject.Box;
import edu.chl.Test.GameObject.Circle;
import edu.chl.Test.Physics.CollisionStrategy;
import edu.chl.Test.Vector.Vector2D;

/**
*
* @author Alexander Sopov
*/
public class Handler implements Observer {
	
	public Circle c1 = Main.c1;
	public Circle c2 = Main.c2;
	public Circle c3 = Main.c3;

	public Box b1 = Main.b1;
	public Box b2 = Main.b2;
	
	public Handler(){

	}
	
	
	
	public void update(Observable arg0, Object arg1) {
		CollisionStrategy strategy = new CollisionStrategy(c1,c2);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();
		
		strategy = new CollisionStrategy(c2,c3);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

		strategy = new CollisionStrategy(c1,c3);
		if (strategy.areObjectsColliding())
			strategy.resolveCollision();

		strategy = new CollisionStrategy(b1,b2);
		if (strategy.areObjectsColliding()){
			strategy.resolveCollision();
		}
		/**/		
		
		
	}
}