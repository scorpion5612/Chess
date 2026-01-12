package com.example.ajedrez

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlinx.android.synthetic.main.activity_main.view.*

class Tablero: SurfaceView, SurfaceHolder.Callback {
    private var canvas:MiCanvas?=null

    //Constructor  de la clase y se manda a llamar
    // el costructor de la clase super SurfaceView
    constructor(context: Context) : super(context) {
        inicializa()
    }

    constructor(context: Context?, attrs: AttributeSet?) :
            super(context, attrs) {
        inicializa()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        inicializa()
    }

    //funcion para inicializar las variables
    private fun inicializa() {
        canvas= MiCanvas(this)
        val holder = this.holder
        holder.addCallback(this)
    }

    //Evento de cuando se toca la pantalla
    public override fun onTouchEvent(event: MotionEvent): Boolean {
        synchronized(this.holder) {
            this.canvas!!.onTouchEvent(event)
        }
        return super.onTouchEvent(event)
    }

    /*
    * Funciones que sobrescribimos de SurfaceHolder.Callback
    * */
    override fun surfaceCreated(p0: SurfaceHolder?) {
        canvas!!.onDraw()
    }

    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
        //escribir codigo cuando cambia el contenido
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {

    }
}