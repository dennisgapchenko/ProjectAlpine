public class GrowthSimulation {
    private int currentYear;
    private double currentHeight;
    private double survivalRate;

    private double calculateEffectiveRain(double zoneRain, double deltaT) {
        // R(effective) = R(zone) - (deltaT * 2.0)
        return zoneRain - (deltaT * 2.0);
    }

    public void runSimulation(Tree tree, double startTemp, double zoneRain, int years) {
        this.survivalRate = 100.0; // assume 100% at start

        for (int year = 0; year < years; year++) {
            this.currentYear = year;

            // 1. calculate the climate variables for growth
            double currentTemp = startTemp + (year * 0.04);
            double deltaT = currentTemp - startTemp;
            double rainfallEffective = calculateEffectiveRain(zoneRain, deltaT);

            // if survival is above 1%, the forest is still "alive" enough to grow
            if (this.survivalRate > 1.0) {
                tree.calculateYearlyGrowth(currentTemp, rainfallEffective);
            } else {
                // set survival to 0 to clean up the GUI
                this.survivalRate = 0.0;
            }

            this.currentHeight = tree.getCurrentTreeHeight();

            // We pass deltaT to find the mortality rate 'm'
            double m = calculateMortality(deltaT);
            this.survivalRate = this.survivalRate * (1 - m);
        }
    }

    public void resetSimulation() {
        this.currentYear = 0;
        this.currentHeight = 0.0;
        this.survivalRate = 100.0; // assume 100% at start
    }

    public String determineStatus(double deltaT) {
        if (deltaT >= 6.0) return "Collapse";
        if (deltaT >= 5.0) return "Critical";
        if (deltaT >= 4.0) return "Strained";
        if (deltaT >= 3.0) return "Stressed";
        return "Healthy";
    }

    public double calculateMortality(double deltaT) {
        if (deltaT >= 6.0) return 0.150; // Collapse
        if (deltaT >= 5.0) return 0.070; // Critical
        if (deltaT >= 4.0) return 0.030; // Strained
        if (deltaT >= 3.0) return 0.015; // Stressed
        return 0.01; // Healthy (Normal turnover)
    }

    public double getFinalHeight() {
        // delivers the height stored after the x-year loop finished
        return this.currentHeight;
    }

    public double getFinalSurvivalRate() {
        // delivers the percentage calculated by the decay formula
        return this.survivalRate;
    }

    public String getFinalStatus() {
        // 1. calculate the final temp change (e.g., 150 years * 0.04 = 6.0)
        double finalDelta = (this.currentYear * 0.04);

        // 2. hand that number to the determineStatus method to the value
        return determineStatus(finalDelta);
    }
}