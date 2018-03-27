# SolarPanelController
## A fuzzy logic system with membership functions

This program is an implementation of a solar panel controller that uses Fuzzy Logic for the implementation. 
The solar panel has an angle that it is set at for each given season and also rotates through the 
hours of the day following the sun. The fuzzy membership functions are implemented with the time 
of day (rated on max sun we can get) along with the current watts that the solar panel is outputting 
(200 being the maximum amount). With these two fuzzy values, I used Fuzzy Logic to OR these two and 
through the implication of these fuzzy sets, I came up with hard values (the change in angles to 
maximize the sun to regain the most wattage output).
