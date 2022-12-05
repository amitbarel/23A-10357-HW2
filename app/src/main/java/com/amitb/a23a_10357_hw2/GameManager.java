package com.amitb.a23a_10357_hw2;

public class GameManager {
    private int wrong = 0;
    private int life;

    public GameManager(int life) {
        this.life = life;
    }

    public int getWrong() {
        return wrong;
    }

    public void updateWrong(){
        if(wrong<life)
            this.wrong++;
    }

    public void obtainLife(){
        if(wrong > 0){
            this.wrong--;
        }
    }

    public boolean isLose(){
        return life == wrong;
    }
}
