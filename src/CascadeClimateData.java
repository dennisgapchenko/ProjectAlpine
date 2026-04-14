class CascadeClimateData {

    // static arrays represent 12 months: Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
    // temperatures in Fahrenheit (mean monthly = (high + low) / 2)
    // precipitation in inches (total monthly)
    // source: NOAA 1991-2020 Climate Normals, anchored to:
    // Source: WeatherSpark.com average weather data (based on NOAA 1991-2020 normals)
    //   ZONE 1 : Snoqualmie WA (394 ft)
    //             https://weatherspark.com/y/1246/Average-Weather-in-Snoqualmie-Washington-United-States-Year-Round
    //   ZONE 2 : got from other weather stations just overall research goes for other zones as well
    //   ZONE 3 : Stampede Pass WA (3606 ft)
    //             https://weatherspark.com/y/145279/Average-Weather-at-Stampede-Pass-Washington-United-States-Year-Round
    // collections of data but overall pretty accurate to the cascades

    // ZONE 1: lowland (0 to 1500 ft) - matches Snoqualmie 1991-2020 normals closely
    static final double[] ZONE1_TEMP = {41.0, 42.5, 45.5, 49.5, 55.0, 60.0, 65.0, 65.5, 59.5, 52.0, 44.5, 41.0};
    static final double[] ZONE1_RAIN = {8.6, 5.8, 6.7, 5.3, 4.0, 3.2, 1.2, 1.2, 3.0, 6.2, 9.5, 8.4};

    // ZONE 2: montane (1500 to 3000 ft) - precipitation rises with elevation
    static final double[] ZONE2_TEMP = {34.0, 35.5, 38.5, 43.0, 49.0, 54.0, 60.0, 61.0, 55.0, 46.0, 37.0, 33.0};
    static final double[] ZONE2_RAIN = {12.5, 9.0, 9.5, 6.5, 4.8, 3.5, 1.5, 1.5, 4.0, 9.0, 13.5, 13.0};

    // ZONE 3: sub-alpine (3000 to ~5500 ft) - Stampede Pass and around (3606 ft)
    // upper range causes colder/wetter. annual precipitation is ~92-100
    static final double[] ZONE3_TEMP = {28.5, 30.5, 34.5, 39.5, 45.5, 51.5, 59.0, 60.0, 55.0, 43.5, 33.5, 28.0};
    static final double[] ZONE3_RAIN = {11.7, 7.9, 8.8, 6.5, 4.7, 3.3, 1.3, 1.6, 4.0, 9.0, 13.7, 12.6};

    // helper method: summer average temperature (Jun/Jul/Aug) - key growth season indicator
    static double getSummerTemp(double[] zoneTempArray) {
        return (zoneTempArray[5] + zoneTempArray[6] + zoneTempArray[7]) / 3.0;
    }

    // helper method: total annual precipitation
    static double getAnnualRain(double[] zoneRainArray) {
        double total = 0;
        for (double rain : zoneRainArray) {
            total += rain;
        }
        return total;
    }
}