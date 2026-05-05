public class Tree {

   protected double baseGrowth;
   protected double heatLimit;
   protected double sensitivity;
   protected double waterNeed;
   protected double currentTreeHeight = 0.0;
   protected double maxTreeHeight;
   
   public Tree(double baseGrowth, double heatLimit, double sensitivity, double waterNeed) {
      this.baseGrowth = baseGrowth;
      this.heatLimit = heatLimit;
      this.sensitivity = sensitivity;
      this.waterNeed = waterNeed;
      this.currentTreeHeight = currentTreeHeight;
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
      // 1. Find P (Heat Penalty)
      double pHeat = 0;
      if (currentTemp > this.heatLimit) {
         pHeat = (currentTemp - this.heatLimit) * this.sensitivity;
      }

        // 2.H(yearly) formula
        // H = ( G * R_eff / R_need ) - P
        double yearlyHeightGain = (this.baseGrowth * (effectiveRain / this.waterNeed)) - pHeat;

        // 3. Safety Check: Don't let the tree shrink or grow past its max height
        if (this.currentTreeHeight + yearlyHeightGain > this.maxTreeHeight) {
            yearlyHeightGain = this.maxTreeHeight - this.currentTreeHeight;
        }

        return Math.max(0, yearlyHeightGain);
    }
}