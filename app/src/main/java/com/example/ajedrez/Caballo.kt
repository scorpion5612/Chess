package com.example.ajedrez

class Caballo {
    private var piezas:Array<Array<Boolean>>?=null

    constructor(){
        piezas=Array<Array<Boolean>>(8, {i->Array<Boolean>(8,{j->false})})
        piezas!![0][1]=true
        piezas!![0][6]=true
    }

    public fun get(f:Int,c:Int):Boolean{
        return piezas!![f][c]
    }

    public fun set(f:Int,c:Int,v:Boolean){
        piezas!![f][c]=v
    }

    public fun isPosible(f1:Int,c1:Int,f2:Int,c2:Int,jugador: Jugador):Boolean{
        if(jugador.get(f2,c2)!=0){
            return false
        }
        if((f1+2==f2&&c1+1==c2)||(f1+2==f2&&c1-1==c2)||(f1+1==f2&&c1+2==c2)
            ||(f1+1==f2&&c1-2==c2)||(f1-2==f2&&c1+1==c2)||(f1-2==f2&&c1-1==c2)||
            (f1-1==f2&&c1+2==c2)||(f1-1==f2&&c1-2==c2)){
            return true
        }else{
            return false
        }
    }
}