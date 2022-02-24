package org.pokemonshootinggame.game.enemy;

import org.pokemonshootinggame.R;
import org.pokemonshootinggame.framework.AppManager;

public class Enemy_boss extends Enemy {
    public static int m_stage = 0;

    public Enemy_boss(int width, int hp, int speed) {
        super(AppManager.getInstance().getResizeBitmap(R.drawable.enemy_boss, width, (int) (width / Enemy.Boss_ImgProportion)));

        m_max_hp = hp;
        m_hp = hp;
        m_speed = speed;
        this.width = m_bitmap.getWidth();
        this.height = m_bitmap.getHeight();

        CreateType = TYPE_BOSS;
    }
}
