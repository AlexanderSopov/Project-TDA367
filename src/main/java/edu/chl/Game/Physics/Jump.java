package edu.chl.Game.Physics;

import edu.chl.Game.Vector.Vector2D;
import edu.chl.Game.entity.Entity;
import edu.chl.Game.entity.UnitProperties;
import edu.chl.Game.handler.KeyInput;

public class Jump extends Thread implements Runnable {
	private KeyInput keyInput;
	private Entity en;
	private static double ns = 1000000.0;
	int jumps;
	
	public Jump(Entity o, KeyInput k){
		en = o;
		keyInput = k;
	}
	@Override
	public void run(){
		jumps = 1;
		UnitProperties up = en.getUnitProperties();
	
		while(keyInput.isWPressed())
		
			if(en.isTouchingGround()){
				if(jumps < 6){
					up.setVelocity(up.getVelocity().addWith(
							new Vector2D(0,-5/jumps)));
					jumps++;
					try {
						this.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					jumps = 1;
					en.setTouchesGround(false);
			}
		}
	}
	
}