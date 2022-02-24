package org.pokemonshootinggame.game.player;

import android.graphics.Bitmap;
import android.graphics.Rect;

import org.pokemonshootinggame.framework.AppManager;
import org.pokemonshootinggame.framework.GraphicObject;
import org.pokemonshootinggame.game.SpecialAttack;

public abstract class Player extends GraphicObject {
    Rect m_boundBox=new Rect();
    protected int m_life;
    protected int m_speed;
    public int m_power;
    protected boolean evolved;
    int m_msSpeed; //미사일 스피드
    int m_msTerm; //미사일 발사 간격
    protected int width;
    protected int height;
    protected long lastShoot = System.currentTimeMillis(); //발사 시간 정보 저장
    private long lastHittedTime = System.currentTimeMillis();

    public Player(Bitmap bitmap) {
        super(bitmap);
        //초기 위치 값을 설정
        int displayWidth = AppManager.getInstance().getDisplayWidth();
        int displayHeight = AppManager.getInstance().getDisplayHeight();
        this.setPosition((displayWidth-bitmap.getWidth())/2, (int)(displayHeight*0.8)); //중앙 하단에 위치
    }

    //프레임워크 update에서 지속적으로 호출할 메서드
    public void update(long gameTime) {
        attack();
        m_boundBox.set(m_x+20, m_y+20, m_x +width-20, m_y+height); //이동할 때마다 박스 영역의 값을 갱신
    }

    //캐릭터별 오버라이딩
    public abstract void attack();
    public abstract Player evolve(); //진화 전: 진화 / 진화 후: Special attack 충전
    public abstract Bitmap getMsBitmap(); //미사일 이미지

    public int getLife(){ return m_life; }
    public void addLife(){ m_life++; }

    public void destroyPlayer() {
        if (System.currentTimeMillis() - lastHittedTime >= 1000) { // 재히트 판정은 1s뒤에. 그 동안 무적
            lastHittedTime = System.currentTimeMillis();
            m_life--;
        }
    }

    public Rect getBoundBox() {
        return m_boundBox;
    }

    public boolean isEvolved() {
        return evolved;
    }

    public abstract SpecialAttack getSpecial();

    public int getSpeed() {
        return m_speed;
    }

    public int getMsSpeed() {
        return m_msSpeed;
    }

    public int getPower() {
        return m_power;
    }
}