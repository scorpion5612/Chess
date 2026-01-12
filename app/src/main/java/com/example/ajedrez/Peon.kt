package com.example.ajedrez

class Peon{
    private var piezas:Array<Array<Boolean>>?=null

    constructor(){
        piezas=Array<Array<Boolean>>(8, {_->Array<Boolean>(8,{_->false})})
        for(i in 0..7){
            piezas!![1][i]=true
        }
    }

    public fun get(f:Int,c:Int):Boolean{
        return piezas!![f][c]
    }

    public fun set(f:Int,c:Int,v:Boolean){
        piezas!![f][c]=v
    }

    public fun isPosible(f1:Int,c1:Int,f2:Int,c2:Int,jugador1: Jugador,
                         jugador2:Jugador,movimientos:ArrayList<Movimiento>,isOnCanvas:Boolean):Boolean{
        //revisa que el espacio a mover no este usado por uno de sus propias piezas
        if(jugador1.get(f2,c2)!=0){
            return false
        }
        if(c1==c2){
            //revisa si se puede mover hacia delante
            if(jugador2.get(7-f2,c2)!=0){
                return false
            }
            if(f2-f1==1){
                return true
            }
            if(f2-f1==2&&f1==1){
                if(jugador1.get(f2-1,c2)==0){
                    return true
                }
            }
        }else{
            //revisa si puede capturar
            if(f2-f1!=1||jugador2.get(7-f2,c2)==0){
                //revisa si  puede comer al paso
                if(!movimientos.isEmpty()){
                    val movimiento:Movimiento=movimientos.get(movimientos.size-1)
                    if(movimiento.getT()==6&&
                        movimiento.getDF() - movimiento.getOF() ==2&&f1==4&&
                        f2-1==7-movimiento.getDF()&&(c2-c1==1||c1-c2==1)){
                        if(c1-movimiento.getDC()==1&&c2==movimiento.getDC()&&!isOnCanvas){
                            jugador2.set(3,c2,false,6)
                            return true
                        }else{
                            if(movimiento.getDC()-c1==1&&c2==movimiento.getDC()&&!isOnCanvas){
                                jugador2.set(3,c2,false,6)
                                return true
                            }
                        }
                    }
                }
                return false
            }
            if(c2-c1==1||c1-c2==1){
                return true
            }
        }
        return false
    }
}