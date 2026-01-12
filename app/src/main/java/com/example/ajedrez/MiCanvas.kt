package com.example.ajedrez


import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent

class MiCanvas {
    private var tablero:Tablero?=null
    private var blancas:Jugador?=null
    private var negras:Jugador?=null
    private var listaSprites: ArrayList<Sprite> = ArrayList()
    private var movimientos:ArrayList<Movimiento> = ArrayList()
    //para seleccionar la pieza
    private var fsel:Int?=null
    private var csel:Int?=null
    private var psel:Int?=null
    //para decidir el turno
    private var turno:Boolean=false
    //para saber si hay jaque
    private var jaque:Boolean=false

    constructor(tablero: Tablero) {
        this.tablero = tablero
        inicializa()
    }

    private fun inicializa(){
        blancas=Jugador()
        negras= Jugador()
        turno =true
    }

    //metodo que se mandaa llamar cuando se toca la pantalla desde la clase VistaJuego
    public fun onTouchEvent(event: MotionEvent) {
        //hacemos lo posible para mover
        if(fsel!=null){
            if(turno){
                var tmp:Int=negras!!.get(event.y.toInt()/(tablero!!.height/8),
                    event.x.toInt()/(tablero!!.width/8))
                if(jaque){
                    if(blancas!!.isPosible(fsel!!,csel!!,
                            7-event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),negras!!,movimientos,turno,false)){
                        if(tmp!=0){
                            blancas!!.set(fsel!!,csel!!,false,psel!!)
                            blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),true,psel!!)
                            negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),false,
                                tmp)
                            jaque=false
                            if(isCheck()){
                                blancas!!.set(fsel!!,csel!!,true,psel!!)
                                blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),false,psel!!)
                                negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),true,
                                    tmp)
                                jaque=true
                            }else {
                                movimientos.add(Movimiento(fsel!!,csel!!,
                                    7 -event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),psel!!,turno,
                                    false,false,false))
                                turno = false
                            }
                        }else{
                            blancas!!.set(fsel!!,csel!!,false,psel!!)
                            blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),true,psel!!)
                            jaque=false
                            if(isCheck()){
                                blancas!!.set(fsel!!,csel!!,true,psel!!)
                                blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),false,psel!!)
                                jaque=true
                            }else {
                                movimientos.add(Movimiento(fsel!!,csel!!,
                                    7 -event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),psel!!,turno,
                                    false,false,false))
                                turno = false
                            }
                        }
                    }
                }else{
                    //mueven blancas
                    if(blancas!!.isPosible(fsel!!,csel!!,
                            7-event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),negras!!,movimientos,turno,false)){
                        blancas!!.set(fsel!!,csel!!,false,psel!!)
                        blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),true,psel!!)
                        negras!!.set(event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),false,
                            tmp)
                        if(isCheck()){
                            blancas!!.set(fsel!!,csel!!,true,psel!!)
                            blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),false,psel!!)
                            negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),true,
                                tmp)
                        }else {
                            movimientos.add(Movimiento(fsel!!,csel!!,
                                7 -event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),psel!!,turno,
                                false,false,false))
                            turno = false
                        }
                    }
                }
            }else{
                var tmp:Int=blancas!!.get(7-event.y.toInt()/(tablero!!.height/8),
                    event.x.toInt()/(tablero!!.width/8))
                if(jaque){
                    if(negras!!.isPosible(fsel!!,csel!!,
                            event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),blancas!!,movimientos,turno,false)){
                        if(tmp!=0){
                            negras!!.set(fsel!!,csel!!,false,psel!!)
                            negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),true,psel!!)
                            blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),false,
                                tmp)
                            jaque=false
                            if(isCheck()){
                                negras!!.set(fsel!!,csel!!,true,psel!!)
                                negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),false,psel!!)
                                blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),true,
                                    tmp)
                                jaque=true
                            }else{
                                movimientos.add(Movimiento(fsel!!,csel!!,
                                    event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),psel!!,turno,
                                    false,false,false))
                                turno=true
                            }
                        }else{
                            negras!!.set(fsel!!,csel!!,false,psel!!)
                            negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),true,psel!!)
                            jaque=false
                            if(isCheck()){
                                negras!!.set(fsel!!,csel!!,true,psel!!)
                                negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),false,psel!!)
                                jaque=true
                            }else{
                                movimientos.add(Movimiento(fsel!!,csel!!,
                                    event.y.toInt()/(tablero!!.height/8),
                                    event.x.toInt()/(tablero!!.width/8),psel!!,turno,
                                    false,false,false))
                                turno=true
                            }
                        }
                    }
                }else{
                    //mueven negras
                    if(negras!!.isPosible(fsel!!,csel!!,
                            event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),blancas!!,movimientos,turno,false)){
                        negras!!.set(fsel!!,csel!!,false,psel!!)
                        negras!!.set(event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),true,psel!!)
                        blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                            event.x.toInt()/(tablero!!.width/8),false,
                            tmp)
                        if(isCheck()){
                            negras!!.set(fsel!!,csel!!,true,psel!!)
                            negras!!.set(event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),false,psel!!)
                            blancas!!.set(7-event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),true,
                                tmp)
                        }else {
                            movimientos.add(Movimiento(fsel!!,csel!!,
                                event.y.toInt()/(tablero!!.height/8),
                                event.x.toInt()/(tablero!!.width/8),psel!!,turno,
                                false,false,false))
                            turno=true
                        }
                    }
                }
            }
            fsel=null
        }else{
            //seleccionamos
            csel=event.x.toInt()/(tablero!!.width/8)
            if(turno){
                fsel= 7-event.y.toInt()/(tablero!!.height/8)
                psel=blancas!!.get(fsel!!,csel!!)
                if(blancas!!.get(fsel!!,csel!!)==0){
                    fsel=null
                }
            }else{
                fsel= event.y.toInt()/(tablero!!.height/8)
                psel=negras!!.get(fsel!!,csel!!)
                if(negras!!.get(fsel!!,csel!!)==0){
                    fsel=null
                }
            }
        }
        if(isCheck()){
            jaque=true
        }
        //dibujamos de nuevo
        onDraw()
    }

    public fun onDraw(){
        var canvas: Canvas? = null
        var paints: Paint = Paint();
        if(!listaSprites.isEmpty()){
            listaSprites.removeAll(listaSprites)
        }
        for(i in 0..7){
            for(j in 0..7) {
                if(blancas!!.get(7-i,j)!=0){
                    listaSprites.add(Sprite(tablero!!,blancas!!.get(7-i,j)-1,0,j,i))
                }
                if(negras!!.get(i,j)!=0){
                    listaSprites.add(Sprite(tablero!!,negras!!.get(i,j)-1,1,j,i))
                }
            }
        }
        paints.setColor(Color.GREEN)
        try {
            canvas = tablero!!.getHolder().lockCanvas()
            synchronized(tablero!!.getHolder()) {
                //pinta el fondo del canvas de color blanco
                canvas.drawColor(Color.WHITE)
                //pinta las fichas
                for(sprites:Sprite in listaSprites){
                    sprites.dibujar(canvas)
                }
                /*//pinta los posibles movimientos
                if(fsel!=null){
                    for(i in 0..7){
                        for(j in 0..7){
                            if(turno){
                                if(blancas!!.isPosible(fsel!!,csel!!,7-i,j,negras!!,
                                        movimientos,turno,true)) {
                                    canvas.drawCircle(
                                        j * tablero!!.width / 8.toFloat() + tablero!!.width / 16f,
                                        i * tablero!!.height / 8.toFloat() + tablero!!.height / 16f,
                                        (tablero!!.width / 8) / 2.5f, paints
                                    )
                                }
                            }else{
                                if(negras!!.isPosible(fsel!!,csel!!,i,j,blancas!!,
                                        movimientos,turno,true)){
                                    canvas.drawCircle(j*tablero!!.width/8.toFloat()+tablero!!.width/16f,
                                        i*tablero!!.height/8.toFloat()+tablero!!.height/16f,
                                        (tablero!!.width/8)/2.5f,paints)
                                }
                            }
                        }
                    }
                }*/
                //pinta si hay jaque
                if(jaque){
                    for(i in 0..7){
                        for(j in 0..7){
                            if(turno){
                                if(blancas!!.isCheck(7-i,j,negras!!,movimientos,turno,true)){
                                    paints.setColor(Color.RED)
                                    canvas.drawCircle(j*tablero!!.width/8.toFloat()+tablero!!.width/16f,
                                        i*tablero!!.height/8.toFloat()+tablero!!.height/16f,
                                        (tablero!!.width/8)/2.5f,paints)
                                    paints.setColor(Color.GREEN)
                                }
                            }else{
                                if(negras!!.isCheck(i,j,blancas!!,movimientos,turno,true)){
                                    paints.setColor(Color.RED)
                                    canvas.drawCircle(j*tablero!!.width/8.toFloat()+tablero!!.width/16f,
                                        i*tablero!!.height/8.toFloat()+tablero!!.height/16f,
                                        (tablero!!.width/8)/2.5f,paints)
                                    paints.setColor(Color.GREEN)
                                }
                            }
                        }
                    }
                }
                //pinta las lineas
                for(i in 0..8){
                    canvas.drawLine(i*tablero!!.width/8.toFloat(),0f,
                        i*tablero!!.width/8.toFloat(),tablero!!.height.toFloat(),Paint())
                    canvas.drawLine(0f,i*tablero!!.height/8.toFloat(),
                        tablero!!.width.toFloat(),i*tablero!!.height/8.toFloat(),Paint())
                }
            }
        } finally {
            if (canvas != null) {
                tablero!!.getHolder().unlockCanvasAndPost(canvas)
            }
        }
        listaSprites.removeAll(listaSprites)
    }

    private fun isCheck():Boolean{
        if(turno){
            for(i in 0..7){
                for(j in 0..7){
                    if(blancas!!.isCheck(i,j,negras!!,movimientos,turno,true)){
                        return true
                    }
                }
            }
        }else{
            for(i in 0..7){
                for(j in 0..7){
                    if(negras!!.isCheck(i,j,blancas!!,movimientos,turno,true)){
                        return true
                    }
                }
            }
        }
        return false
    }
}