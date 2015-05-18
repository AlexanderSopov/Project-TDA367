package edu.chl.Game.model.gameobject.entity.entityTools;

import edu.chl.Game.*;
import edu.chl.Game.handler.*;
import edu.chl.Game.model.gameobject.Id;
import edu.chl.Game.model.gameobject.entity.*;
import edu.chl.Game.model.gameobject.entity.enemy.*;
import edu.chl.Game.model.gameobject.entity.entityTools.*;
import edu.chl.Game.controller.GameHandler;

public class AI {

	private GameHandler handler;
	private int playerXCoordinate;
	private Cooldown cd;
	private EntityState es;
	private EnemyUnit eu;
	private UnitAttraction unitAttraction;
	private boolean strikeComplete;
	private FrameIterator fi;

	public AI(EnemyUnit eu) {
		this.eu = eu;
		this.handler = eu.getHandler();
		this.es = eu.getEntityState();
		unitAttraction = new UnitAttraction(400);
		this.fi = new FrameIterator(1, eu.getUnitValues().getAttackRate());
		this.cd = new Cooldown(fi);
	}

	public void exerciseBehaviour() {
		searchPremesis();
		attack();
		cd.updateCooldown();
	}

	public void searchPremesis() {
		if (!unitAttraction.targetIsFound()) {
			unitAttraction.searchAttractionArea(eu.getX(), handler.getPlayer()
					.getX());
		}
		unitIsAttracted();
	}

	public void unitIsAttracted() {
		if (unitAttraction.targetIsFound()) {
			react();
		}
	}

	public void react() {
		findPlayer();
		followPlayer();
	}

	public void followPlayer() {
		if (handler.getPlayer().getX() < eu.getX()) {
			eu.setVelX(-4);
			updateFacingDirectionLeft();
		} else {
			eu.setVelX(4);
			updateFacingDirectionRight();
		}

		if ((handler.getPlayer().getX() - 100) < eu.getX()
				&& eu.getX() < (handler.getPlayer().getX() + 100)) {
			eu.setVelX(0);
		}
	}

	public void updateFacingDirectionRight() {
		es.setFacingDirection(FacingDirection.FacingRight);
	}

	public void updateFacingDirectionLeft() {
		es.setFacingDirection(FacingDirection.FacingLeft);
	}

	public void findPlayer() {
		playerXCoordinate = handler.getPlayer().getX();
	}

	public boolean withinAttackRange() {
		if (playerXCoordinate <= eu.getX()) {
			return ((eu.getX() - playerXCoordinate) <= 100);
		} else {
			return ((playerXCoordinate - eu.getX()) <= 100);
		}
	}

	public void attack() {
		resetStrike();
		if (withinAttackRange()) {
			if (cd.attackIsReady()) {
				engageAttack();
			}
			activateCooldown();
		}
		strikeFrameHit();
	}
	
	public void engageAttack(){
		eu.setAttacking(true);
	}
	
	public void strikeFrameHit(){
		if(eu.getUnitMeasurement().getStrikeFrame() == eu.getFrameIterator_attack().getFrame()){
			if(!strikeComplete){
				strikeComplete = true;
				deliverDamage();
			}
		}
	}
	
	public void deliverDamage(){
		handler.getPlayer().takeDamage(eu.getUnitValues().getAttackDamage());
	}
	
	public void resetStrike(){
		if(eu.getFrameIterator_attack().getFrame() == 0){
			strikeComplete = false;
		}
	}

	public void activateCooldown() {
		cd.activateCooldown();
	}
	
}
