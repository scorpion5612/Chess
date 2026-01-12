package com.example.ajedrez

class Movimiento{
    private var turno:Boolean= false
    //posicion origen
    private var of:Int = 0
    private var oc:Int = 0
    //posicion destino
    private var df:Int = 0
    private var dc:Int = 0
    //tipo de pieza
    private var t:Int = 0
    private var captura:Boolean = false
    private var jaque:Boolean=false
    private var jaquemate:Boolean=false

    constructor(of:Int,oc:Int,df:Int,dc:Int,t:Int,turno:Boolean,captura:Boolean,jaque:Boolean,jaquemate:Boolean){
        this.of=of
        this.oc=oc
        this.df=df
        this.dc=dc
        this.t=t
        this.turno=turno
        this.captura=captura
        this.jaque=jaque
        this.jaquemate=jaquemate
    }

    public fun getOF():Int{
        return of
    }

    public fun getOC():Int{
        return oc
    }

    public fun getDF():Int{
        return df
    }

    public fun getDC():Int{
        return dc
    }

    public fun getT():Int{
        return t
    }

    public fun getTurno():Boolean{
        return turno
    }

    public fun getCaptura():Boolean{
        return captura
    }

    public fun getJaque():Boolean{
        return jaque
    }

    public fun getJaqueMate():Boolean{
        return jaquemate
    }
}