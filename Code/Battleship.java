package com.company;

public class Battleship {
    public int amount;
    public int length;
    public int hit;

    public void setAmount(int x)
    {
        this.amount=x;
    }

    public void setLength(int x)
    {
        this.length=x;
    }

    public void setHit(int x)
    {
        this.hit=x;
    }
    public void addHit()
    {
        this.hit=this.hit+1;
    }

    public int sinkCheck()
    {
        if(this.length==this.hit)
                                 { return 1;}
                                 else{ return 0; }
    }
}
