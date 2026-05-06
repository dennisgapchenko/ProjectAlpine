public class Tree {

    protected double baseGrowth;
    protected double heatLimit;
    protected double sensitivity;
    protected double waterNeed;
    protected double currentTreeHeight = 0.0;
    protected double maxTreeHeight;

    public Tree(double baseGrowth, double heatLimit, double sensitivity, double waterNeed, double maxTreeHeight) {
        this.baseGrowth = baseGrowth;
        this.heatLimit = heatLimit;
        this.sensitivity = sensitivity;
        this.waterNeed = waterNeed;
        this.maxTreeHeight = maxTreeHeight;
    }

    public double getBaseGrowth() {
        return baseGrowth;
    }

    public double getHeatLimit() {
        return heatLimit;
    }

    public double getSensitivity() {
        return sensitivity;
    }

    public double getWaterNeed() {
        return waterNeed;
    }

    public double getCurrentTreeHeight() {
        return currentTreeHeight;
    }

    public double getMaxTreeHeight() {
        return maxTreeHeight;
    }

    public double calculateYearlyGrowth(double currentTemp, double effectiveRain) {
        // 1. find p value (Heat Penalty)
        double pHeat = 0;
        if (currentTemp > this.heatLimit){
            pHeat = (currentTemp - this.heatLimit) * this.sensitivity;
        }

        //
        // 2. formula H(yearly) = ( G (base growth) * R (effective) / R (need) ) - P (heat)
        double yearlyHeightGain = (this.baseGrowth * (effectiveRain / this.waterNeed)) - pHeat;


        // 3. do not let the tree shrink or grow past its max height
        if (this.currentTreeHeight + yearlyHeightGain > this.maxTreeHeight) {
            yearlyHeightGain = this.maxTreeHeight - this.currentTreeHeight;
        }

        // ensures the tree cannot have negative growth
        // (trees don't shrink even in extreme stress) its a floor
        return Math.max(0, yearlyHeightGain);
    }

}