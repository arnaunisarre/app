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
import com.example.arnau.loggindemo.R;

public class GameView  extends SurfaceView  {

    private Context mContext;

    //Rect buttonDreta = new Rect(0,0,1200,h); // Define the dimensions of the button here

    boolean buttonClicked;

    private Bitmap bHierba, bAgua,bHierba2,bHierba3,bMuro1,bSuelo1,bMuro2;
    private Bitmap bSueloBiblio, bEstanteria, bMuroBiblio, bPuertaBiblioAbj, bPuertaBiblioArrb;
    private Bitmap bPuertaBiblioDrch, bSueloAula, bMuroAula;
    private Bitmap bEstanteriaAtras, bEstanteriaLateralDrch,bEstanteriaLateralIzq;
    private Bitmap bMostradorLateral, bMostrador, bMostradorEsqIzqAbj, bMostradorEsqIzqArrb;
    private Bitmap bMostradorDrchArrb, bMostradorDrchAbj;
    private Bitmap bMuroBIzq, bMuroBDrch, bMuroBIzqArrb, bMuroBDrchArrb;
    private Bitmap bMuroBArrb, bMuroBLatIzq, bMuroBLatDrch;
    private Bitmap personaje;
    private Bitmap malo1,malo2,malo3,malo4,malo5,malo6,malo7,malo8,malo9,malo10;
    private Bitmap botonDerecha, botonIzquierda, botonArriba, botonAbajo;
    private Bitmap instrucciones;
    //private Escenario esc  = new Escenario("a",22,12);
    private Escenario esc  = new Escenario("a",22,12);
    private Escenario esc1  = new Escenario("a",22,12);
    private Escenario esc2  = new Escenario("b",22,12);
    private Escenario esc3  = new Escenario("c",22,12);

    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private Sprite sprite;
 private int longitudx = 0;
    private int longitudy = 0;
    private int mostrarInstrucciones = 0;
    private Usuario jugador;



    public GameView(Context context) {
        super(context);
        esc = esc1;
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
        //bitmaps de suelos
        bHierba = BitmapFactory.decodeResource(getResources(), R.drawable.hierba);
        bHierba2 = BitmapFactory.decodeResource(getResources(), R.drawable.hierba2);
        bHierba3 = BitmapFactory.decodeResource(getResources(), R.drawable.hierba3);
        bAgua = BitmapFactory.decodeResource(getResources(), R.drawable.agua);
        bSueloBiblio = BitmapFactory.decodeResource(getResources(), R.drawable.suelo_biblio);
        bEstanteria = BitmapFactory.decodeResource(getResources(),R.drawable.estanteria);
        bSueloAula = BitmapFactory.decodeResource(getResources(),R.drawable.suelo_clase);


        //bitmaps de Muros
        bMuro1 = BitmapFactory.decodeResource(getResources(), R.drawable.muro1);
        bMuro2 = BitmapFactory.decodeResource(getResources(), R.drawable.muro2);
        bSuelo1 = BitmapFactory.decodeResource(getResources(), R.drawable.suelo1);
        bMuroBiblio = BitmapFactory.decodeResource(getResources(),R.drawable.pared_biblio);
        bMuroBIzq = BitmapFactory.decodeResource(getResources(),R.drawable.murobiblioizq);
        bMuroBIzqArrb = BitmapFactory.decodeResource(getResources(),R.drawable.murobiblioizqarrb);
        bMuroBDrch = BitmapFactory.decodeResource(getResources(),R.drawable.murobibliodrch);
        bMuroBDrchArrb = BitmapFactory.decodeResource(getResources(),R.drawable.murobibliodrcharrb);
        bMuroBArrb= BitmapFactory.decodeResource(getResources(),R.drawable.pared_biblioarrb);
        bMuroBLatDrch= BitmapFactory.decodeResource(getResources(),R.drawable.pared_bibliodrch);
        bMuroBLatIzq= BitmapFactory.decodeResource(getResources(),R.drawable.pared_biblioizq);

        //añadir muro aula bMuroAula = BitmapFactory.decodeResource(getResources(), R.drawable.)
        //bitmaps de Puertas
        bPuertaBiblioAbj = BitmapFactory.decodeResource(getResources(),R.drawable.puerta_biblio_abajo);
        bPuertaBiblioArrb= BitmapFactory.decodeResource(getResources(),R.drawable.puerta_biblio_arriba);
        bPuertaBiblioDrch = BitmapFactory.decodeResource(getResources(),R.drawable.puerta_biblio_derecha);
        //Bitmaps mobiliario biblio

        bEstanteriaAtras = BitmapFactory.decodeResource(getResources(),R.drawable.estanteria_atras);
        bEstanteriaLateralDrch = BitmapFactory.decodeResource(getResources(),R.drawable.estanteria_lado);
        bMostradorLateral = BitmapFactory.decodeResource(getResources(),R.drawable.mostrador_lateral);
        bMostrador = BitmapFactory.decodeResource(getResources(),R.drawable.mostrador_centro);
        bMostradorEsqIzqAbj= BitmapFactory.decodeResource(getResources(),R.drawable.mostrador_esquina_izquierda);
        bMostradorEsqIzqArrb= BitmapFactory.decodeResource(getResources(),R.drawable.mostrador_arriba_izquierda);
        bMostradorDrchArrb= BitmapFactory.decodeResource(getResources(),R.drawable.mostrador_arriba_derecha);
        bMostradorDrchAbj= BitmapFactory.decodeResource(getResources(),R.drawable.mostrador_esquina_derecha);


        instrucciones = BitmapFactory.decodeResource(getResources(), R.drawable.fons);

        botonDerecha = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_derecha);
        botonIzquierda = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_izquierda);
        botonArriba = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_arriba);
        botonAbajo = BitmapFactory.decodeResource(getResources(),R.drawable.flecha_gris_abajo);


        //ESCENARIO1 con celdas tipo Y para muros
        for(int p=0;p<esc1.getNumVerticales();p++) {
            esc1.celdas[0][p].setTipo("y");
            esc1.celdas[0][p].setFalsePuedopasar();
        }
        for(int p=0;p<esc1.getNumVerticales();p++) {
            esc1.celdas[esc1.getNumHorizontales()-1][p].setTipo("y");
            esc1.celdas[esc1.getNumHorizontales()-1][p].setFalsePuedopasar();
        }
        for(int p=0;p<esc1.getNumHorizontales();p++) {
            esc1.celdas[p][0].setTipo("y");
            esc1.celdas[p][0].setFalsePuedopasar();
        }
        for(int p=0;p<esc1.getNumHorizontales();p++) {
            esc1.celdas[p][esc1.getNumVerticales()-1].setTipo("y");
            esc1.celdas[p][esc1.getNumVerticales()-1].setFalsePuedopasar();
        }
        //ESCENARIO2 con celdas tipo Z para muros
        for(int p=0;p<esc2.getNumVerticales();p++) {
            esc2.celdas[0][p].setTipo("z");
            esc2.celdas[0][p].setFalsePuedopasar();
        }
        for(int p=0;p<esc2.getNumVerticales();p++) {
            esc2.celdas[esc2.getNumHorizontales()-1][p].setTipo("z");
            esc2.celdas[esc2.getNumHorizontales()-1][p].setFalsePuedopasar();
        }
        for(int p=0;p<esc2.getNumHorizontales();p++) {
            esc2.celdas[p][0].setTipo("z");
            esc2.celdas[p][0].setFalsePuedopasar();
        }
        for(int p=0;p<esc2.getNumHorizontales();p++) {
            esc2.celdas[p][esc2.getNumVerticales()-1].setTipo("z");
            esc2.celdas[p][esc2.getNumVerticales()-1].setFalsePuedopasar();
        }
        //        //ESCENARIO3 con celdas tipo W para muros

        esc2.celdas[9][1].setTipo("malo1");
        esc2.celdas[9][1].setFalsePuedopasar();
        esc2.celdas[1][6].setTipo("malo9");
        esc2.celdas[1][6].setFalsePuedopasar();
        esc2.celdas[7][10].setTipo("malo4");
        esc2.celdas[7][10].setFalsePuedopasar();

        esc1.celdas[5][2].setTipo("malo2");
        esc1.celdas[5][2].setFalsePuedopasar();
        esc1.celdas[4][5].setTipo("malo3");
        esc1.celdas[4][5].setFalsePuedopasar();
        esc1.celdas[15][4].setTipo("malo5");
        esc1.celdas[15][4].setFalsePuedopasar();
        esc1.celdas[20][1].setTipo("malo6");
        esc1.celdas[20][1].setFalsePuedopasar();
        esc1.celdas[17][9].setTipo("malo7");
        esc1.celdas[17][9].setFalsePuedopasar();
        esc1.celdas[3][7].setTipo("malo8");
        esc1.celdas[3][7].setFalsePuedopasar();
        esc1.celdas[1][6].setTipo("malo9");
        esc1.celdas[1][6].setFalsePuedopasar();

        //mobiliario de la biblioteca ESC2
        // columna/fila
        //parte superior
        for( int i = 0; i < 21; i++){
            esc2.celdas[i][0].setTipo("pared_biblioarrb");
            esc2.celdas[i][0].setFalsePuedopasar();
        }
        //parte inferios
        for( int i = 0; i < 21; i++) {
            esc2.celdas[i][11].setTipo("pared_biblio");
            esc2.celdas[i][11].setFalsePuedopasar();
        }

       //laterales biblioteca derecha
        for( int j = 0; j < 11; j++) {
            esc2.celdas[21][j].setTipo("pared_bibliodrch");
            esc2.celdas[21][j].setFalsePuedopasar();
        }

        //laterales biblioteca izquierda
        for( int j = 0; j < 11; j++) {
            esc2.celdas[0][j].setTipo("pared_biblioizq");
            esc2.celdas[0][j].setFalsePuedopasar();
        }
        //esquinas biblio
        esc2.celdas[0][0].setTipo("murobiblioizqarrb");
        esc2.celdas[0][0].setFalsePuedopasar();
        esc2.celdas[0][11].setTipo("murobiblioizq");
        esc2.celdas[0][11].setFalsePuedopasar();
        esc2.celdas[21][11].setTipo("murobibliodrch");
        esc2.celdas[21][11].setFalsePuedopasar();
        esc2.celdas[21][0].setTipo("murobibliodrcharrb");
        esc2.celdas[21][0].setFalsePuedopasar();
        esc2.celdas[11][0].setTipo("puerta_biblio_arriba");
        esc2.celdas[11][0].setFalsePuedopasar();

        esc1.celdas[11][0].setTipo("puerta_biblio_arriba");
        esc1.celdas[11][0].setFalsePuedopasar();

        //        bMuroBDrch = BitmapFactory.decodeResource(getResources(),R.drawable.muroBibliodrch);
        //mobiliario de la clase


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
        //he cambiado a esc2
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
        Log.d("HOLA", "HOLA");

        canvas.drawColor(Color.WHITE);

        int width = getWidth();
        int height = getHeight();

        int l = 0;
        l = width / escenario.getNumHorizontales();
        int h = 0;
        h = height / escenario.getNumVerticales();
        setLongitudy(h);
        setLongitudx(l);
        int l2 = 0;
        l2 = (int) (l * 1.1);
        int h2 = 0;
        h2 = h;

        for (int j = 0; j < escenario.getNumHorizontales(); j++) {

            for (int i = 0; i < escenario.getNumVerticales(); i++) {
                //int x = (l/1)*j;
                //int y = (h/1)*i;
                int x = (90) * j;
                int y = (90) * i;

                //para escenario 1
                if (escenario.celdas[j][i].getTipo().equals("x")&&escenario.equals(esc1)) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                }else if (escenario.celdas[j][i].getTipo().equals("x")&&escenario.equals(esc2)) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSueloBiblio, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                }
                else if (escenario.celdas[j][i].getTipo().equals("malo1")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo1, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo2")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo2, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo3")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo3, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo4")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo4, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo5")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo5, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo6")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo6, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo7")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo7, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo8")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo8, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("malo9")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bSuelo1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                    Bitmap b3 = Bitmap.createScaledBitmap(malo9, h2, l2, false);
                    canvas.drawBitmap(b3, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("y")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bMuro1, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                } else if (escenario.celdas[j][i].getTipo().equals("puerta_biblio_arriba")) {
                    Bitmap b2 = Bitmap.createScaledBitmap(bPuertaBiblioArrb, h2, l2, false);
                    canvas.drawBitmap(b2, x, y, null);
                }
                    //para escenario 2
                   else if (escenario.celdas[j][i].getTipo().equals("suelob")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bSueloBiblio, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("malo1")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bSueloBiblio, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                        Bitmap b3 = Bitmap.createScaledBitmap(malo1, h2, l2, false);
                        canvas.drawBitmap(b3, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("pared_biblioarrb")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBArrb, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("pared_biblio")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBiblio, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("malo4")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bSueloBiblio, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                        Bitmap b3 = Bitmap.createScaledBitmap(malo4, h2, l2, false);
                        canvas.drawBitmap(b3, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("pared_bibliodrch")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBLatDrch, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("pared_biblioizq")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBLatIzq, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("murobiblioizqarrb")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBIzqArrb, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("murobiblioizq")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBIzq, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("murobibliodrch")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBDrch, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("murobibliodrcharrb")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bMuroBDrchArrb, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                    } else if (escenario.celdas[j][i].getTipo().equals("malo10")) {
                        Bitmap b2 = Bitmap.createScaledBitmap(bSueloBiblio, h2, l2, false);
                        canvas.drawBitmap(b2, x, y, null);
                        Bitmap b3 = Bitmap.createScaledBitmap(malo10, h2, l2, false);
                        canvas.drawBitmap(b3, x, y, null);
                    }
                    //para escenario 3
                    // System.out.println("");
                }
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
        //he cambiado a esc2
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
public  void setEscenario(){

        if (esc.equals(esc1)){
            esc = esc2;
            int o = 0;
            mostrarInstrucciones = 0;
        }
    else if (esc.equals(esc2)){
        esc = esc3;
        int o = 0;
        mostrarInstrucciones = 0;
    }
}
}