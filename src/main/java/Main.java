/*
Matthew Stulz
CSC 425
Programming Assignment #3
Due April 3, 2017
This program is an implementation of a solar panel controller that uses Fuzzy Logic for the implementation. The solar
panel has an angle that it is set at for each given season and also rotates through the hours of the day following the
sun. The fuzzy membership functions are implemented with the time of day (rated on max sun we can get) along with the
current watts that the solar panel is outputting (200 being the maximum amount). With these two fuzzy values, I used
Fuzzy Logic to OR these two and through the implication of these fuzzy sets, I came up with hard values (the change in
angles to maximize the sun to regain the most wattage output.
*/

public class Main {
    public static void main(String[] args) {

        // Used current time for this solar panel
        SunriseSunset s = new SunriseSunset();
        s.output();
        Fuzzy f = new Fuzzy("northern", "spring", s.getLatitude().doubleValue());
        double fuzzyWatts = f.fuzzificationOfWatts(160);          // membership function calls
        double fuzzyTime = s.fuzzificationOfTimeOfDay();                // membership function calls
        double fuzzyValue = f.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime); // Fuzzy logic call
        String fuzzyImplication = f.implicationOfFuzzySet(fuzzyValue, s, f); // Gets hard numbers
        System.out.println(fuzzyImplication+"\n");

        // Re-adjusts the season angle since the input is for winter.
        Fuzzy f2 = new Fuzzy("northern", "winter", 47.551493);
        fuzzyWatts = f2.fuzzificationOfWatts(40);
        fuzzyTime = s.fuzzificationOfTimeOfDay();
        fuzzyValue = f2.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime);
        fuzzyImplication = f2.implicationOfFuzzySet(fuzzyValue, s, f2);
        System.out.println(fuzzyImplication+"\n");

        // Third example ignores any fuzzy logic calculations because the input watts are currently at 200 (maximum).
        Fuzzy f3 = new Fuzzy("northern", "summer", 56.130366);
        fuzzyWatts = f3.fuzzificationOfWatts(200);
        fuzzyTime = s.fuzzificationOfTimeOfDay();
        fuzzyValue = f3.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime);
        fuzzyImplication = f3.implicationOfFuzzySet(fuzzyValue, s, f3);
        System.out.println(fuzzyImplication+"\n");

        // Rotated to 145° to be at the prime angle for when the sun rises.
        Fuzzy f4 = new Fuzzy("northern", "spring", 40.712784);
        fuzzyWatts = f4.fuzzificationOfWatts(160);
        fuzzyTime = s.fuzzificationOfTimeOfDay();
        fuzzyValue = f4.inferenceFuzzyLogic(fuzzyWatts, fuzzyTime);
        fuzzyImplication = f4.implicationOfFuzzySet(fuzzyValue, s, f4);
        System.out.println(fuzzyImplication+"\n");
    }
}