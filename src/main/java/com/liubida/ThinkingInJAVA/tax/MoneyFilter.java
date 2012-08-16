package com.liubida.ThinkingInJAVA.tax;

/**
 * @author liubida
 */
public class MoneyFilter {
    public static void main(String[] args) {
        UnderWorld u = new Government(10);
        u = new BigBoss(u);
        u = new SecondBoss(u);
        System.out.println(u.takeIn());
    }
}

abstract class UnderWorld {
    abstract int takeIn();
}

class Government extends UnderWorld {
    int tax;
    int waste = 0;

    public Government(int tax) {
        this.tax = tax;
    }

    @Override
    int takeIn() {
        this.waste++;
        return --this.tax;
    }
}

abstract class Corrupt extends UnderWorld {
    UnderWorld littleWorld;
    int        privateWallet = 0;

    Corrupt(UnderWorld littleWorld) {
        this.littleWorld = littleWorld;
    }
}

class BigBoss extends Corrupt {
    BigBoss(UnderWorld littleWorld) {
        super(littleWorld);
    }

    @Override
    int takeIn() {
        privateWallet++;
        int t = littleWorld.takeIn();
        return --t;
    }
}

class SecondBoss extends Corrupt {
    SecondBoss(UnderWorld littleWorld) {
        super(littleWorld);
    }

    @Override
    int takeIn() {
        privateWallet++;
        int t = littleWorld.takeIn();
        return --t;
    }
}
