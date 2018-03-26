import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

// This class uses a dependency through Maven that calculates sunrise, sunset, and works
// together with the Date and Calendar classes to calculate time of day. This is important
// to ensure the panel is facing the sun during sunrise, is at a 180° degree angle at high
// noon and follows it until sunset. Then resetting to face east once the time is greater
// than 20:00.
public class SunriseSunset {

    // Reference variables used
    private String officialSunrise;
    private String officialSunset;
    private Date today;
    private SunriseSunsetCalculator calculator;
    private Location location;
    private String direction;

    // SunriseSunset constructor that is a no arg constructor but initializes the fields since a lot
    // of them don't require input and are creating instances of classes.
    public SunriseSunset() {
    this.location = new Location("39.033117", "-84.451885"); // Highland Heights, KY
    this.calculator = new SunriseSunsetCalculator(location, "America/New_York");
    setDate();
    setOfficialSunrise();
    setOfficialSunset();
    }

    // Setters and getters
    public void setDate() {
        this.today = Calendar.getInstance().getTime();
    }
    public Date getDate() {
        return today;
    }
    public void setOfficialSunrise() {
        this.officialSunrise = calculator.getOfficialSunriseForDate(Calendar.getInstance());
    }
    public String getOfficialSunrise() {
        return officialSunrise;
    }
    public void setOfficialSunset() {
        this.officialSunset = calculator.getOfficialSunsetForDate(Calendar.getInstance());
    }
    public String getOfficialSunset() {
        return officialSunset;
    }
    public BigDecimal getLatitude() {
        return location.getLatitude();
    }
    public void setDirection(String direction) { this.direction = direction; }
    public String getDirection() { return direction; }

    // Checks for the northern hemisphere so see if the overall angle of the panel is correct per the given season
    // by checking the current date compared to stored dates.
    public String checkCalenderDate() {
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        Calendar current = Calendar.getInstance();
        min.set(2017, Calendar.APRIL,18);
        max.set(2017, Calendar.AUGUST, 24);
        if (current.before(max) && current.after(min)) {
            // Adjust to summer angle
            return "summer";
        }
        min.set(2017, Calendar.AUGUST, 24);
        max.set(2017, Calendar.OCTOBER, 7);
        if (current.before(max) && current.after(min)) {
            // Adjust to fall/spring angle
            return "spring";
        }
        min.set(2017, Calendar.OCTOBER, 7);
        max.set(2017, Calendar.MARCH, 5);
        if (current.before(max) && current.after(min)) {
            // Adjust to winter angle
            return "fall";
        }
        min.set(2017, Calendar.MARCH, 7);
        max.set(2017, Calendar.APRIL, 5);
        if (current.before(max) && current.after(min)) {
            // Adjust to spring angle
        }
        return "spring";
    }

    // Membership function that returns a fuzzy value based on the time of day where
    // we can get the greatest output from the panel.
    public double fuzzificationOfTimeOfDay() {
        // Gets the hour only and returns a fuzzy value
        Calendar c = Calendar.getInstance();
        c.setTime(getDate());
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour >= 5) {
            return 0.0;
        } else if (hour >= 6) {
            return 0.1;
        } else if (hour >= 7) {
            return 0.3;
        } else if (hour >= 8) {
            return 0.5;
        } else if (hour >= 10) {
            return 0.7;
        } else if (hour >= 11) {
            return 0.9;
        } else if (hour >= 12) {
            return 1.0;
        } else if (hour >= 13) {
            return 0.9;
        } else if (hour >= 14) {
            return 0.8;
        } else if (hour >= 15) {
            return 0.7;
        } else if (hour >= 16) {
            return 0.6;
        } else if (hour >= 17) {
            return 0.4;
        } else if (hour >= 18) {
            return 0.3;
        } else if (hour >= 19) {
            return 0.2;
        } else if (hour >= 20) {
            return 0.1;
        } else {
            return 0.0;
        }
    }

    // These degrees are calculated as if the solar panel is flat at 180°.
    public int calculateOptimalTimeOfDayAngle() {
        Calendar c = Calendar.getInstance();
        c.setTime(getDate());
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour == 5) {
            setDirection("east");
            return 145;
        } else if (hour == 6) {
            setDirection("east");
            return 150;
        } else if (hour == 7) {
            setDirection("east");
            return 155;
        } else if (hour == 8) {
            setDirection("east");
            return 160;
        } else if (hour == 10) {
            setDirection("east");
            return 165;
        } else if (hour == 11) {
            setDirection("east");
            return 170;
        } else if (hour == 12) {
            setDirection("north");
            return 180;
        } else if (hour == 13) {
            setDirection("west");
            return 175;
        } else if (hour == 14) {
            setDirection("west");
            return 170;
        } else if (hour == 15) {
            setDirection("west");
            return 165;
        } else if (hour == 16) {
            setDirection("west");
            return 160;
        } else if (hour == 17) {
            setDirection("west");
            return 155;
        } else if (hour == 18) {
            setDirection("west");
            return 150;
        } else if (hour == 19) {
            setDirection("west");
            return 145;
        } else if (hour == 20) {
            setDirection("west");
            return 140;
        } else {
            setDirection("east");
            return 145;
        }
    }

    // Outputs the sunrise and the sunset of the SunriseSunset constructor
    public void output() {
        System.out.println(getDate() + " officialSunrise : " + getOfficialSunrise());
        System.out.println(getDate() + " officialSunset  : " + getOfficialSunset());
    }
}