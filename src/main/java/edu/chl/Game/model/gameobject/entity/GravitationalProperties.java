package edu.chl.Game.model.gameobject.entity;

import edu.chl.Game.sound.SFX;	

public class GravitationalProperties {
	private UnitProperties unitProperties;
	private EntityProperties entityProperties;
	private EntityState entityState;
    private Entity en;
	private SFX sfx;
    
	public GravitationalProperties(Entity en){
		this.unitProperties = en.getUnitProperties();
		this.entityProperties = en.getEntityProperties();
		this.entityState = en.getEntityState();
		this.en = en;
		sfx = new SFX();
	}
	
	public void jumpingMechanics(){
		if(entityState.getContactWithGround()){
			entityProperties.setGravity(25.0);
		}
		
		if (entityState.isJumping()) {
			entityState.setContactWithGround(false);
			entityProperties.setGravity(entityProperties.getGravity() - 1.0);			
			en.setVelY(((int) -entityProperties.getGravity()));				
			if (entityProperties.getGravity() <= 0.0) {									
				entityState.setFalling(true);											 
				entityState.setJumping(false);											
			}
		}
	}
	
	public void fallingMechanics(){
		if (entityState.isFalling()) {													
			entityProperties.setGravity(entityProperties.getGravity() + 1.0);		
			en.setVelY(((int) entityProperties.getGravity()));		
		}
	}
	

	
	
}
