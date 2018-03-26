// This class embodies nearly all the Fuzzy Logic and optimizes the overall angle during said season.

public class Fuzzy {
    // 1. Fuzzification
    // 2. Inference
    // 3. Defuzzification

    // private reference variables
    private String hemisphere;
    private String season;
    private double latitude;

    // The fuzzy constructor that sets the hemisphere, season, and latitude.
    public Fuzzy(String hemisphere, String season, double latitude) {
        this.hemisphere = hemisphere;
        this.season = season;
        this.latitude = latitude;
    }

    // Setters and getters
    public String getHemisphere() {
        return hemisphere;
    }
    public void setHemisphere(String hemisphere) {
        this.hemisphere = hemisphere;
    }
    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude (double latitude) {
        this.latitude = latitude;
    }

    // Will be used to change overall angle via certain dates
    //                         Northern hemisphere	Southern hemisphere
    // Adjust to summer angle on	April 18	October 18
    // Adjust to autumn angle on	August 24	February 23
    // Adjust to winter angle on	October 7	April 8
    // Adjust to spring angle on	March 5	    September 4
    public double calculateOptimalSeasonalAngle(Fuzzy input) {
        double angle;
        // Checks the season and calculates the angle the solar panel will stay while it rotates through the
        // daytime hours to maximize sun coverage.
        if (input.getSeason().equals("summer")) {
            angle = (input.getLatitude() * 0.92) - 24.3;
            System.out.println("The current angle has been adjusted to " + angle + "° to optimize for the summer season.");
            return angle;
        } else if (input.getSeason().equals("spring") || (input.getSeason().equals("fall"))) {
            angle = (input.getLatitude() * 0.98) - 2.3;
            System.out.println("The current angle has been adjusted to " + angle + "° to optimize for the spring/fall season.");
            return angle;
        } else
            angle = (input.getLatitude() * 0.89) + 24;
            System.out.println("The current angle has been adjusted to " + angle + "° to optimize for the winter season.");
        return angle;
    }

    // If the fuzzy value of current watts or time of day is not one,
    // there are improvements that can be made to optimize the sun.
    // 1. Check the optimalAngle and adjust accordingly
    // 2. Check the time of day and determine rotation
    public String implicationOfFuzzySet(double fuzzyValue, SunriseSunset s, Fuzzy f) {
        if (fuzzyValue != 1.0) {
            // Check to see if the current season angle is correct
            String currentSeasonAngle = s.checkCalenderDate();
            if (f.getSeason().equals(currentSeasonAngle)) {
                // Angled correctly for the given season
            } else {
                // Set the current season to f
                f.setSeason(currentSeasonAngle);
                // Calculate and se the new seasonal angle
                calculateOptimalSeasonalAngle(f);
                return "The panel has been re-adjusted for the current season.";
            }
            // Re-adjust rotation of the panel according to time of day
            int rotationDegree = s.calculateOptimalTimeOfDayAngle();
            return "With the current time: " + s.getDate() + ", the panel has been adjusted to " + rotationDegree + "° due " + s.getDirection() +
            " so that the panel is rotated for maximum sun optimization.";
        } else {
            return "There are currently no adjustments of the panel, maximized optimization is underway.";
        }
    }

    // Arbitrary solar panel output of 200 watts per hour, the max output said solar panel
    // can output being maximized.
    // Will be used to rotate to the angle of the sun to ensure optimization of the sun.
    public double fuzzificationOfWatts(int watts) {
        if (watts == 200) {
            return 1.0;
        } else if (watts >= 180) {
            return 0.9;
        } else if (watts >= 160) {
            return 0.8;
        } else if (watts >= 140) {
            return 0.7;
        } else if (watts >= 120) {
            return 0.6;
        } else if (watts >= 100) {
            return 0.5;
        } else if (watts >= 80) {
            return 0.4;
        } else if (watts >= 60) {
            return 0.3;
        } else if (watts >= 40) {
            return 0.2;
        } else if (watts >= 20) {
            return 0.1;
        } else {
            return 0.0;
        }
    }

    // Uses the fuzzy OR logic on the two fuzzy membership functions
    public double inferenceFuzzyLogic(double fuzzyWatts, double fuzzyTime) {
        return (fuzzyWatts>fuzzyTime?fuzzyWatts:fuzzyTime);
    }

}
