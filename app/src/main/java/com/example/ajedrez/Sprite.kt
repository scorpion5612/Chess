package com.example.ajedrez

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class Sprite {
    private var tablero:Tablero?=null
    private var sprite: Bitmap?=null
    private var anchoFrame: Int? = null //ancho del frame
    private var altoFrame: Int? = null //alto del frame
    private var n:Int? = null //indica la columna del frame actual del sprite
    private var t:Int? = null //indica la fila del frame actual del sprite
    private var x: Int? = null //posicion en x del sprite
    private var y: Int? = null //pocicion en y del sprite

    constructor(tablero: Tablero,n:Int,t:Int,x:Int,y:Int){
        this.tablero=tablero
        inicializa(n,t,x,y)
    }

    private fun inicializa(n:Int,t:Int,x:Int,y:Int){
        var imagen: Drawable? = ContextCompat.getDrawable(
            tablero!!.context,
            R.drawable.piezas
        )
        //convertimos el drawable a bitmap y redimencionamos
        // la imagen a una achura y altura especificos
        this.sprite = redimensionarImagen(drawableToBitmap(imagen),
            tablero!!.width.toFloat()/8, tablero!!.height.toFloat()/8)
        this.anchoFrame = this.sprite!!.width / 6;
        this.altoFrame = this.sprite!!.height / 2;
        this.n=n
        this.t=t
        this.x=x*tablero!!.width/8
        this.y=y*tablero!!.height/8
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(
            drawable!!.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    private fun redimensionarImagen(mBitmap: Bitmap, newWidth: Float, newHeigth: Float): Bitmap {
        //Redimensionamos
        val anchoFrame = mBitmap.width
        val altoFrame = mBitmap.height
        val scaleWidth = newWidth / anchoFrame
        val scaleHeight = newHeigth / altoFrame
        // ccreamos una matriz para la manipulacion
        val matrix = Matrix()
        // redimiencinamos el bitmap
        matrix.postScale(scaleWidth, scaleHeight)
        // recreamos el nuevo Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, anchoFrame, altoFrame, matrix, false)
    }

    public fun dibujar(canvas:Canvas){
        //obtine las pociciones iniciales del frame actual
        val srcX = n!! * anchoFrame!!
        val srcY = t!! * altoFrame!!
        //crea un rectangulo fuente con las dimensiones del frame
        val src = Rect(srcX, srcY, srcX + anchoFrame!!, srcY + altoFrame!!)
        //define el rectangulo destino donde se va a dibujar el frame actual
        val dst = Rect(x!!, y!!, x!! + tablero!!.width/8, y!! + tablero!!.height/8)
        //dibuja el frame actual
        canvas.drawBitmap(sprite!!, src, dst, null)
    }
}