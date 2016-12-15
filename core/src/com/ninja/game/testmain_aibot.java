package com.ninja.game;

import com.ninja.game.Calculate.AiBot;
import com.ninja.game.Calculate.CollisionLayer;
import com.ninja.game.Sprite.Character;
import com.ninja.game.Sprite.Enemy;

/**
 * Created by ather on 15/12/2559.
 */
public class testmain_aibot {
    public static void main(String[] args) {
        AiBot aiBot;
        Character player = new Enemy();
        player.setPos(1,0);
        Character target = new Enemy();
        target.setPos(0,0);
        //aiBot = new AiBot(player);
        //aiBot.setTarget(target, true);

        //System.out.println(aiBot.chk_angle());

        CollisionLayer col = new CollisionLayer(player, target);
        System.out.println(col.point2angle());
    }
}
