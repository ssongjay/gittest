package org.pokemonshootinggame.game.player;

import android.graphics.Bitmap;

import org.pokemonshootinggame.R;
import org.pokemonshootinggame.framework.AppManager;
import org.pokemonshootinggame.game.missile.Missile_Player;
import org.pokemonshootinggame.game.SpecialAttack;

public class Player_3_evolved extends Player {
    public Player_3_evolved(int life, int x, int y) {
        super(AppManager.getInstance().getBitmap(R.drawable.lizamong));
        setPosition(x,y);
        m_life=life;
        m_power=2;
        m_speed=35;
        m_msSpeed=30;
        m_msTerm = 500;
        evolved=true;
        width = m_bitmap.getWidth();
        height = m_bitmap.getHeight();
    }

    //현재 동일하지만 캐릭터별로 속도 등 조절 가능
    @Override
    public void attack() { //짧은 시간 텀, 여러 갈래로 쏘거나, 충돌에도 사라지지 않고 지속적 공격 등 캐릭터별 구현 가능
        if (System.currentTimeMillis() - lastShoot >= m_msTerm) {
            lastShoot = System.currentTimeMillis();
            AppManager.getInstance().getGameState().getPmsList().add(new Missile_Player(this,m_x+10, m_y-50));
        }
    }

    @Override
    public Player evolve() {
        return this;
    } //진화x

    @Override
    public Bitmap getMsBitmap() {return AppManager.getInstance().getBitmap(R.drawable.fire2); }//미사일 비트맵

    @Override
    public SpecialAttack getSpecial() { //캐릭터별 Special Attack 객체 생성
        return new SpecialAttack(AppManager.getInstance().getBitmap(R.drawable.thunderbomb));
    }
}
