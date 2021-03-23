package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code heree

        Battleship bs2 = new Battleship();
        bs2.setHit(0);
        bs2.addHit();
        System.out.println(bs2.hit);
        bs2.addHit();
        System.out.println(bs2.hit);
        bs2.addHit();
        System.out.println(bs2.hit);
    }
}
