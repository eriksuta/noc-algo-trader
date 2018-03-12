package com.capco.noc.algo.schema.strategy;

import com.capco.noc.algo.schema.Ticker;

import java.util.Map;

public class StrategyUnitTakeProfit extends StrategyUnit{

    private ValueType valueType;
    private PositionSellType positionSellType;

    private double triggerPrice;
    private double triggerPercentage;

    //Last value of any movement on position - bot BUY & SELL will update this
    private double lastValue;

    /*
     * key   - price rise of unit
     * value - percentage to sell
     * */
    private Map<Double, Double> triggerPriceMap;

    /*
     * key   - percentage rise of price rise of unit
     * value - percentage to sell
     * */
    private Map<Double, Double> triggerPercentageMap;

    public StrategyUnitTakeProfit(Ticker ticker) {
        super(ticker);
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public PositionSellType getPositionSellType() {
        return positionSellType;
    }

    public void setPositionSellType(PositionSellType positionSellType) {
        this.positionSellType = positionSellType;
    }

    public double getTriggerPrice() {
        return triggerPrice;
    }

    public void setTriggerPrice(double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public double getTriggerPercentage() {
        return triggerPercentage;
    }

    public void setTriggerPercentage(double triggerPercentage) {
        this.triggerPercentage = triggerPercentage;
    }

    public Map<Double, Double> getTriggerPriceMap() {
        return triggerPriceMap;
    }

    public void setTriggerPriceMap(Map<Double, Double> triggerPriceMap) {
        this.triggerPriceMap = triggerPriceMap;
    }

    public Map<Double, Double> getTriggerPercentageMap() {
        return triggerPercentageMap;
    }

    public void setTriggerPercentageMap(Map<Double, Double> triggerPercentageMap) {
        this.triggerPercentageMap = triggerPercentageMap;
    }

    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }

    /*
         * STATIC  - take profit contains static trigger value, e.g. sell when price rises to a certain value
         * PERCENT - take profit contains percentage trigger, e.g. sell when price rises 10%
         * */
    public enum ValueType {
        STATIC,
        PERCENT
    }

    /*
     * ENTIRE_POSITION - when take profit is triggered, sell entire position
     * POSITION_MAP    - ability to set a position sell map, e.g.:
     *                      - sell 20% of owned asset, if price rises 5%
     *                      - sell 50% of owned asset, if price rises 10% etc.
     * */
    public enum PositionSellType {
        ENTIRE_POSITION,
        POSITION_MAP
    }
}
