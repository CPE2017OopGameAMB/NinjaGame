package com.ninja.game;

import com.ninja.game.Calculate.AiBot;
import com.ninja.game.Sprite.Character;
import com.ninja.game.Sprite.Enemy;

/**
 * Created by ather on 15/12/2559.
 */
public class testmain_aibot {
    public static void main(String[] args) {
        AiBot aiBot;
        Character player = new Enemy();
        player.setPos(3,5);
        Character target = new Enemy();
        target.setPos(3,4);
        aiBot = new AiBot(player);
        aiBot.setTarget(target, true);

        System.out.println(aiBot.chk_angle());
    }
}
