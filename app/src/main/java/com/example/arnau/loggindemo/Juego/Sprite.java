package com.example.arnau.loggindemo.Juego;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.arnau.loggindemo.Clases.Escenario;

public class Sprite {
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int x = 90;
    private int y = 90;

    private GameView gameView;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;
    private  int direction = 1;
    private int posx = 1;
    private int posy =1;
    private int speed;
    private final int defSpeed = 15;


    public Sprite(GameView gameView, Bitmap bmp) {
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.gameView = gameView;
        this.bmp = bmp;

//        Random rnd = new Random(System.currentTimeMillis());
//        xSpeed = rnd.nextInt(50) - 5;
//        ySpeed = rnd.nextInt(50) - 5;
    }

    private void update() {

        if(speed!=0) {
            currentFrame = ++currentFrame % BMP_COLUMNS;
        }

        switch (direction) {

            case 0:     //up
                if (y>posy*90) {
                    y -= 15;



                }
                else
                    stop();
                break;
            case 1:     //left
                if (x>posx*90) {

                    x -= 15;
                }
                else
                    stop();
                break;
            case 2:     //down

                if (y<(posy*90)) {
                    y += 15;

                }
                else
                    stop();
                break;
            case 3:     //right
                if (x<posx*90) {

                    x += 15;

                }
                else
                    stop();
                break;
            case 4:
                break;

        }
    }
    public void dibujar(Canvas canvas, Escenario esc) {
        update();
        int srcX = currentFrame * width;
        int srcY = DIRECTION_TO_ANIMATION_MAP[direction] * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public void setDirection(int direction, Escenario esc){
        this.direction = direction;
        boolean valido ;
        speed = defSpeed;
        switch (direction) {
            case 0://up

                valido = esc.celdas[posx][(posy-1)].getPuedopasar();
               if (valido==true )
                posy =  posy-1;
               else {

                   if (esc.celdas[posx][(posy - 1)].getTipo().contains("malo")==true) {
                       //esc.celdas[posx][(posy - 1)].getTipo().equals("malo1")
                       int o = 1;
                       gameView.startActProva(esc.celdas[posx][(posy - 1)].getTipo());
                   }
                   else if(esc.celdas[posx][(posy-1)].getTipo().contains("puerta")==true){
                       gameView.setEscenario();

                   }
                   else if(esc.celdas[posx][(posy-1)].getTipo().contains("retroceso")==true){
                       gameView.setEscenarioMenor();

                   }
                   speed = 0;
               }

                break;
            case 1:     //left
                valido = esc.celdas[posx-1][(posy)].getPuedopasar();

                if (valido ==true )
                posx = posx-1;
                else{
                    if (esc.celdas[posx-1][(posy)].getTipo().contains("malo")==true) {
                        int o = 1;
                        gameView.startActProva((esc.celdas[posx - 1][(posy)].getTipo()));
                    }
                    else if(esc.celdas[posx -1][(posy)].getTipo().contains("puerta")==true){
                        gameView.setEscenario();
                    }
                    else if(esc.celdas[posx -1][(posy)].getTipo().contains("retroceso")==true){
                        gameView.setEscenarioMenor();
                    }
                    speed = 0;

                }

                break;
            case 2:     //down
                int q = posy+1;
                valido = esc.celdas[posx][(posy+1)].getPuedopasar();
                if (valido==true )
                posy = posy+1;
                else {

                    if (esc.celdas[posx][(posy + 1)].getTipo().contains("malo")==true) {
                        //esc.celdas[posx][(posy + 1)].getTipo().equals("malo1")
                        int o = 1;
                        gameView.startActProva(esc.celdas[posx][(posy + 1)].getTipo());
                    }
                    else if(esc.celdas[posx][(posy +1)].getTipo().contains("puerta")==true){
                        gameView.setEscenario();

                    }
                    else if(esc.celdas[posx][(posy +1)].getTipo().contains("retroceso")==true){
                        gameView.setEscenarioMenor();

                    }
                    speed = 0;
                }

                break;

            case 3:     //right
                valido = esc.celdas[posx+1][(posy)].getPuedopasar();
                if (valido==true ){

                    posx = posx+1;
                }

                else{
                    if (esc.celdas[posx+1][(posy)].getTipo().contains("malo")==true){
                        //esc.celdas[posx+1][(posy)].getTipo().equals("malo1")

                        gameView.startActProva(esc.celdas[posx+1][(posy)].getTipo());
                    }

                    else if(esc.celdas[posx+1][(posy)].getTipo().contains("puerta")==true){
                        gameView.setEscenario();

                    }
                    else if(esc.celdas[posx+1][(posy)].getTipo().contains("retroceso")==true){
                        gameView.setEscenarioMenor();

                    }


                    speed = 0;
                }



                break;
            case 4:
                break;

        }

    }
    // direction = 0 up, 1 left, 2 down, 3 right,
    // animation = 3 back, 1 left, 0 front, 2 right
    private int getAnimationRow() {
        return DIRECTION_TO_ANIMATION_MAP[direction];
    }

    public void stop(){
        speed =0 ;
    }
}