package com.example.ajedrez


class Jugador{
    private var peon:Peon?=null
    private var caballo:Caballo?=null
    private var torre:Torre?= null
    private var rey:Rey?= null
    private var dama:Dama?= null
    private var alfil:Alfil?=null

    constructor(){
        peon= Peon()
        caballo=Caballo()
        torre=Torre()
        rey=Rey()
        dama=Dama()
        alfil=Alfil()
    }

    public fun get(f:Int,c:Int):Int{
        if(peon!!.get(f,c)){
            return 6
        }
        if(caballo!!.get(f,c)){
            return 4
        }
        if(alfil!!.get(f,c)){
            return 3
        }
        if(torre!!.get(f,c)){
            return 5
        }
        if(dama!!.get(f,c)){
            return 2
        }
        if(rey!!.get(f,c)){
            return 1
        }
        return 0
    }

    public fun set(f:Int,c:Int,v:Boolean,p:Int){
        when(p){
            1->rey!!.set(f, c, v)
            2->dama!!.set(f, c, v)
            3->alfil!!.set(f,c, v)
            4->caballo!!.set(f, c, v)
            5->torre!!.set(f, c, v)
            6->peon!!.set(f,c,v)
            else->return
        }
    }

    public fun isPosible(f1:Int,c1:Int,f2:Int,c2:Int,jugador: Jugador,
                         movimientos:ArrayList<Movimiento>,turno:Boolean,isOnCanvas:Boolean):Boolean{
        when(get(f1,c1)){
            1->return rey!!.isPosible(f1,c1,f2,c2,this,jugador,movimientos,turno,isOnCanvas)
            2->return dama!!.isPosible(f1,c1,f2,c2,this,jugador)
            3->return alfil!!.isPosible(f1,c1,f2,c2,this,jugador)
            4->return caballo!!.isPosible(f1,c1,f2,c2,this)
            5->return torre!!.isPosible(f1,c1,f2,c2,this,jugador)
            6->return peon!!.isPosible(f1,c1,f2,c2,this,jugador,movimientos,isOnCanvas)
            else->return false
        }
    }

    public fun isCheck(f:Int,c:Int,jugador: Jugador,movimientos: ArrayList<Movimiento>,
                       turno: Boolean,isOnCanvas: Boolean):Boolean{
        if(get(f, c)==1){
            return rey!!.isCheck(f,c,this,jugador,movimientos,turno,isOnCanvas)
        }
        return false
    }
}