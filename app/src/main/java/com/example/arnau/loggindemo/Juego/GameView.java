package com.example.arnau.loggindemo.Juego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.arnau.loggindemo.Clases.Escenario;
import com.example.arnau.loggindemo.Clases.Objeto;
import com.example.arnau.loggindemo.Clases.Usuario;
import com.example.arnau.loggindemo.Proves;
import com.example.arnau.loggindemo.R;

public class GameView  extends SurfaceView  {


    private Context mContext;





    //Rect buttonDreta = new Rect(0,0,1200,h); // Define the dimensions of the button here

    boolean buttonClicked;

    private Bitmap bHierba, bAgua,bHierba2,bHierba3,bMuro1,bSuelo1,bMuro2;
    private Bitmap personaje;
    private Bitmap malo1,malo2,malo3,malo4,malo5,malo6,malo7,malo8,malo9,malo10;
    private Bitmap botonDerecha, botonIzquierda, botonArriba, botonAbajo;
    private Bitmap instrucciones;
    //private Escenario esc  = new Escenario("a",22,12);
    private Escenario esc  = new Escenario("a",22,12);
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;
 private int longitudx = 0;
    private int longitudy = 0;
    private int mostrarInstrucciones = 0;
    private Usuario jugador;



    public GameView(Context context) {
        super(context);
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }


            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //Canvas c = holder.lockCanvas(null);
               //dibujar(c,esc);
                //holder.unlockCanvasAndPost(c);

                gameLoopThread.setRunning(true);
                gameLoopThread.start();

            }


            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });

        bHierba = BitmapFactory.decodeResource(getResources(), R.drawable.hierba);
        bHierba2 = BitmapFactory.decodeResource(getResources(), R.drawable.hierba2);
        bHierba3 = BitmapFactory.decodeResource(getResources(), R.drawable.hierba3);
        bMuro1 = BitmapFactory.decodeResource(getResources(), R.drawable.muro1);
        bMuro2 = BitmapFactory.decodeResource(getResources(), R.drawable.muro2);
        bSuelo1 = BitmapFactory.decodeResource(getResources(), R.drawable.suelo1);

        bAgua = BitmapFactory.decodeResource(getResources(), R.drawable.agua);

        instrucciones = BitmapFactory.decodeResource(getResources(), R.drawable.fons);
        botonDerecha = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_derecha);
        botonIzquierda = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_izquierda);
        botonArriba = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_arriba);
        botonAbajo = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_abajo);



        for(int p=0;p<esc.getNumVerticales();p++) {
            esc.celdas[0][p].setTipo("y");
            esc.celdas[0][p].setFalsePuedopasar();
        }
        for(int p=0;p<esc.getNumVerticales();p++) {
            esc.celdas[esc.getNumHorizontales()-1][p].setTipo("y");
            esc.celdas[esc.getNumHorizontales()-1][p].setFalsePuedopasar();
        }
        for(int p=0;p<esc.getNumHorizontales();p++) {
            esc.celdas[p][0].setTipo("y");
            esc.celdas[p][0].setFalsePuedopasar();
        }
        for(int p=0;p<esc.getNumHorizontales();p++) {
            esc.celdas[p][esc.getNumVerticales()-1].setTipo("y");
            esc.celdas[p][esc.getNumVerticales()-1].setFalsePuedopasar();
        }

        esc.celdas[9][1].setTipo("malo1");
        esc.celdas[9][1].setFalsePuedopasar();
        esc.celdas[5][2].setTipo("malo2");
        esc.celdas[5][2].setFalsePuedopasar();
        esc.celdas[4][5].setTipo("malo3");
        esc.celdas[4][5].setFalsePuedopasar();
        esc.celdas[7][10].setTipo("malo4");
        esc.celdas[7][10].setFalsePuedopasar();
        esc.celdas[15][4].setTipo("malo5");
        esc.celdas[15][4].setFalsePuedopasar();
        esc.celdas[20][1].setTipo("malo6");
        esc.celdas[20][1].setFalsePuedopasar();
        esc.celdas[17][9].setTipo("malo7");
        esc.celdas[17][9].setFalsePuedopasar();
        esc.celdas[3][7].setTipo("malo8");
        esc.celdas[3][7].setFalsePuedopasar();
        esc.celdas[1][6].setTipo("malo9");
        esc.celdas[1][6].setFalsePuedopasar();


        personaje = BitmapFactory.decodeResource(getResources(), R.drawable.bad5);

        sprite = new Sprite(this,personaje);
        malo1 = BitmapFactory.decodeResource(getResources(), R.drawable.malo1);
        malo2 = BitmapFactory.decodeResource(getResources(), R.drawable.malo2);
        malo3 = BitmapFactory.decodeResource(getResources(), R.drawable.malo3);
        malo4 = BitmapFactory.decodeResource(getResources(), R.drawable.malo4);
        malo5 = BitmapFactory.decodeResource(getResources(), R.drawable.malo5);
        malo6 = BitmapFactory.decodeResource(getResources(), R.drawable.malo6);
        malo7 = BitmapFactory.decodeResource(getResources(), R.drawable.malo7);
        malo8 = BitmapFactory.decodeResource(getResources(), R.drawable.malo8);
        malo9 = BitmapFactory.decodeResource(getResources(), R.drawable.malo9);
        malo10 = BitmapFactory.decodeResource(getResources(), R.drawable.malo10);





    }



    protected void dibujarPersonaje(Canvas canvas){


        dibujar(canvas,esc);
        sprite.dibujar(canvas,esc);
        dibujarBotones(canvas);
        if (mostrarInstrucciones<30) {
            dibujarInstrucciones(canvas);
            mostrarInstrucciones=mostrarInstrucciones+1 ;
        }
//        if (buttonClicked) {
//            Intent intent = new Intent(mContext, Proves.class);
//            mContext.startActivity(intent);
//        }
//        Boolean a;
//        a= false;




    }

    protected void dibujarBotones(Canvas canvas){

        int height = getHeight();
        botonAbajo.getWidth();
        botonAbajo.getHeight();
        canvas.drawBitmap(botonIzquierda, 100, height-300, null);
        canvas.drawBitmap(botonArriba, 250, height-450, null);
        canvas.drawBitmap(botonAbajo, 250, height-150, null);
        canvas.drawBitmap(botonDerecha, 400, height-300, null);

    }



    protected void dibujar(Canvas canvas, Escenario escenario) {
        Log.d("HOLA","HOLA");

        canvas.drawColor(Color.WHITE);

        int width = getWidth();
        int height = getHeight();

        int l= 0;
        l= width/escenario.getNumHorizontales();
        int h =0;
        h= height/escenario.getNumVerticales();
        setLongitudy(h);
        setLongitudx(l);
        int l2 =0;
        l2= (int) (l*1.1);
        int h2 =0;
        h2= h;


        // l2= l+20;
        //h = h+2;
        for(int j=0;j<escenario.getNumHorizontales();j++) {

            for (int i = 0; i < escenario.getNumVerticales(); i++) {
                //int x = (l/1)*j;
                //int y = (h/1)*i;
                int x = (90)*j;
               int  y = (90)*i;
                if (escenario.celdas[j][i].getTipo().equals("x")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo1")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo1, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo2")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo2, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo3")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo3, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo4")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo4, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo5")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo5, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo6")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo6, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo7")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo7, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo8")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo8, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo9")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo9, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                }
                else if(escenario.celdas[j][i].getTipo().equals("y")){
                    Bitmap b2 = Bitmap.createScaledBitmap(bMuro2, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                }

            }
           // System.out.println("");
        }



    }
    protected void dibujarInstrucciones(Canvas canvas){

        Bitmap b2 = Bitmap.createScaledBitmap(instrucciones,getWidth(), getHeight(), false);
        canvas.drawBitmap(b2, 0, 0, null);

    }

    @Override
    public boolean onTouchEvent( MotionEvent event) {



        int h = getHeight();
        int a = h-450;
        int a2 = a+96;
        int b = h -150;
        int b2 = b+96;
        h = h-300;
        int h2 = h+96;
        //h2 = 20000;
        //h =20000;
        Rect buttonEsquerra = new Rect(100,h,196,h2);
        Rect buttonDalt = new Rect(250,a,346,a2);
        Rect buttonBaix = new Rect(250,b,346,b2);
        Rect buttonDreta = new Rect(400,h,496,h2); // Define the dimensions of the button here


        mContext = getContext();
       float p= event.getX();
        float l = event.getY();
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
//            if (buttonDreta.contains((int) event.getX(), (int) event.getY())) {
//                buttonClicked = true;
//                sprite.setDirection(3);
//                Log.d("pos", "derecha");
//            } else if (buttonEsquerra.contains((int) event.getX(), (int) event.getY())) {
//                sprite.setDirection(1);
//                Log.d("pos", "izquierda");
//            } else if (buttonDalt.contains((int) event.getX(), (int) event.getY())) {
//                sprite.setDirection(0);
//                Log.d("pos", "adalt");
//            } else if (buttonBaix.contains((int) event.getX(), (int) event.getY())) {
//                sprite.setDirection(2);
//                Log.d("pos", "baix");
//            } else {
//
//                Log.d("pos", "nada");
//            }
        }
        if(event.getAction()==MotionEvent.ACTION_UP){
            if (buttonDreta.contains((int) event.getX(), (int) event.getY())) {
                buttonClicked = true;
                sprite.setDirection(3,esc);
                Log.d("pos", "derecha");
            } else if (buttonEsquerra.contains((int) event.getX(), (int) event.getY())) {
                sprite.setDirection(1,esc);
                Log.d("pos", "izquierda");
            } else if (buttonDalt.contains((int) event.getX(), (int) event.getY())) {
                sprite.setDirection(0,esc);
                Log.d("pos", "adalt");
            } else if (buttonBaix.contains((int) event.getX(), (int) event.getY())) {
                sprite.setDirection(2,esc);
                Log.d("pos", "baix");
            } else {

                Log.d("pos", "nada");
            }
        }

        return true;
    }

    public int getLongitudx() {
        return longitudx;
    }

    public void setLongitudx(int longitudx) {
        this.longitudx = longitudx;
    }

    public int getLongitudy() {
        return longitudy;
    }

    public void setLongitudy(int longitudy) {
        this.longitudy = longitudy;
    }

    public void startActProva(String malo){

        Intent intent1 = new Intent(getContext(), Preguntas.class);
        intent1.putExtra("usuario", jugador);
        intent1.putExtra("malo", malo);
        getContext().startActivity(intent1);
    }

    public int añadirObjeto(Objeto obj){
        Objeto p = jugador.getObjeto(obj.getNombreObjeto());
        if (p.equals(null)){
            jugador.miInventario.add(p);
            return 1;
        }
        else
            return 2;

    }
public void setJugador(Usuario usuario){

        jugador = usuario;
}

}