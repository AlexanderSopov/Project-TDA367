/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.Game.model.gameobject.item;


import edu.chl.Game.model.gameobject.entity.Entity;

import java.awt.Graphics;
import java.util.Observer;

/**
 *
 * @author Rasmus
 */
public interface Character{
   
   public void render(Graphics g);
   
   public void update();
    
   public double getHealth();
   
   public double getArmor();
   
   public void effect();
    
}