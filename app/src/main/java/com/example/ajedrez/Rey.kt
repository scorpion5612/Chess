package com.example.ajedrez

class Rey {
    private var piezas:Array<Array<Boolean>>?=null

    constructor(){
        piezas=Array<Array<Boolean>>(8, {i->Array<Boolean>(8,{j->false})})
        piezas!![0][4]=true
    }

    public fun get(f:Int,c:Int):Boolean{
        return piezas!![f][c]
    }

    public fun set(f:Int,c:Int,v:Boolean){
        piezas!![f][c]=v
    }

    public fun isPosible(f1:Int,c1:Int,f2:Int,c2:Int,jugador1: Jugador,
                         jugador2:Jugador,movimientos: ArrayList<Movimiento>,
                         turno:Boolean,isOnCanvas: Boolean):Boolean{
        var l:Boolean=true
        var s:Boolean=true
        //revisa que no haya una pieza suya en ese lugar
        if(jugador1.get(f2,c2)!=0){
            return false
        }
        //revisa que ese lugar no lo ponga en jaque
        set(f2,c2,true)
        if(isCheck(f2,c2,jugador1,jugador2,movimientos,turno,isOnCanvas)){
            set(f2,c2,false)
            return false
        }
        set(f2,c2,false)
        //mover en una direccion un espacio
        if(f2-f1<=1&&f1-f2<=1&&c2-c1<=1&&c1-c2<=1){
            return true
        }
        //checamos condiciones para el enroque corto y largo
        if(jugador1.get(0,0)!=5||jugador1.get(0,3)!=0||jugador2.get(7,3)!=0||
            jugador1.get(0,2)!=0||jugador2.get(7,2)!=0||
            jugador1.get(0,1)!=0||jugador2.get(7,1)!=0){
            l=false
        }
        if(jugador1.get(0,7)!=5||jugador1.get(0,5)!=0||jugador2.get(7,5)!=0||
            jugador1.get(0,6)!=0||jugador2.get(7,6)!=0){
            s=false
        }
        if((l||s)&&jugador1.get(0,4)!=1){
            l=false
            s=false
        }
        if(l||s){
            for(movimiento in movimientos){
                if(movimiento.getT()==1&&movimiento.getTurno()==turno){
                    l=false
                    s=false
                    break
                }else{
                    if(movimiento.getT()==5&&movimiento.getTurno()==turno&&
                        (movimiento.getDC()==7||movimiento.getOC()==7)){
                        s=false
                    }
                    if(movimiento.getT()==5&&movimiento.getTurno()==turno&&
                        (movimiento.getDC()==0||movimiento.getOC()==0)){
                        l=false
                    }
                }
            }
        }
        //enroque corto
        if(s&&!isCheck(0,4,jugador1,jugador2,movimientos,turno,isOnCanvas)){
            set(0,5,true)
            set(0,6,true)
            if(!isCheck(0,5,jugador1, jugador2, movimientos, turno,isOnCanvas)&&
                !isCheck(0,6,jugador1, jugador2, movimientos, turno,isOnCanvas)){
                set(0,5,false)
                if(f2==0&&c2==6&&!isOnCanvas){
                    set(0,4,false)
                    jugador1.set(0,5,true,5)
                    jugador1.set(0,7,false,5)
                    return true
                }
                set(0,6,false)
            }
        }
        //enroque largo
        if(l&&!isCheck(0,4,jugador1,jugador2,movimientos,turno,isOnCanvas)){
            set(0,3,true)
            set(0,2,true)
            if(!isCheck(0,3,jugador1, jugador2, movimientos, turno,isOnCanvas)&&
                !isCheck(0,2,jugador1, jugador2, movimientos, turno,isOnCanvas)){
                set(0,3,false)
                if(f2==0&&c2==2&&!isOnCanvas){
                    set(0,4,false)
                    jugador1.set(0,3,true,5)
                    jugador1.set(0,0,false,5)
                    return true
                }
                set(0,2,false)
            }
        }

        return false
    }

    public fun isCheck(f:Int,c:Int,jugador1: Jugador,jugador2:Jugador,
                       movimientos:ArrayList<Movimiento>,turno: Boolean,isOnCanvas:Boolean):Boolean{
        for(i in 0..7){
            for(j in 0..7){
                if(jugador2.get(i,j)==1&&i-(7-f)<=1&&(7-f)-i<=1&&j-(7-c)<=1&&(7-c)-j<=1){
                    return true
                }else{
                    if(jugador2.isPosible(i,j,7-f,c,jugador1,movimientos,turno,isOnCanvas)){
                        return true
                    }
                }
            }
        }
        return false
    }
}