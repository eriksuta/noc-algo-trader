package com.capco.noc.algo.schema.strategy;

import com.capco.noc.algo.schema.Ticker;

public class StrategyUnit {
    private Ticker ticker;

    public StrategyUnit() {}

    public StrategyUnit(Ticker ticker) {
        this.ticker = ticker;
    }

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }
}
