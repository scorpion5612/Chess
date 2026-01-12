package com.example.ajedrez

class Dama {
    private var piezas:Array<Array<Boolean>>?=null

    constructor(){
        piezas=Array<Array<Boolean>>(8, {i->Array<Boolean>(8,{j->false})})
        piezas!![0][3]=true
    }

    public fun get(f:Int,c:Int):Boolean{
        return piezas!![f][c]
    }

    public fun set(f:Int,c:Int,v:Boolean){
        piezas!![f][c]=v
    }

    public fun isPosible(f1:Int,c1:Int,f2:Int,c2:Int,jugador1: Jugador,jugador2:Jugador):Boolean{
        var op:Int?=null
        var tmp1:Int=f2
        var tmp2:Int=c2
        //revisa si el espacio no esta ocupado por una de sus piezas
        if(jugador1.get(tmp1,tmp2)!=0){
            return false
        }
        if(c1==c2){
            if (f1<f2){
                op=1
            }else{
                op=2
            }
        }else{
            if(c1<c2){
                op=3
            }else{
                op=0
            }
        }
        do{
            when (op) {
                0 ->tmp2++
                1 -> tmp1--
                2 -> tmp1++
                3 -> tmp2--
            }
            if(tmp1<0||tmp2<0||tmp1>7||tmp2>7){
                break
            }
            if(tmp1==f1&&tmp2==c1){
                return true
            }
            if(jugador1.get(tmp1,tmp2)!=0||jugador2.get(7-tmp1,tmp2)!=0){
                break
            }
        }while(true)
        tmp1=f2
        tmp2=c2
        if(c1<c2){
            if(f1<f2){
                op=1
            }else{
                op=2
            }
        }else{
            if(f1<f2){
                op=3
            }else{
                op=0
            }
        }
        do{
            when (op) {
                0 -> {
                    tmp1++
                    tmp2++
                }
                1 -> {
                    tmp1--
                    tmp2--
                }
                2 -> {
                    tmp1++
                    tmp2--
                }
                3 -> {
                    tmp1--
                    tmp2++
                }
            }
            if(tmp1<0||tmp2<0||tmp1>7||tmp2>7){
                return false
            }
            if(tmp1==f1&&tmp2==c1){
                return true
            }
            if(jugador1.get(tmp1,tmp2)!=0||jugador2.get(7-tmp1,tmp2)!=0){
                return false
            }
        }while(true)
    }
}