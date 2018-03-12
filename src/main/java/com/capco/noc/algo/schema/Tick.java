package com.capco.noc.algo.schema;

public class Tick {

    private Ticker ticker;
    private double value;

    public Tick() {}

    public Tick(Ticker ticker, double value) {
        this.ticker = ticker;
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}
