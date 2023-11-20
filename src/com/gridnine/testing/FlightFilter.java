package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FlightFilter {
    public static List<Flight> sortOutFlightsBeferoNow(List<Flight> flights) {
        List<Flight> outputList = new LinkedList<>(flights);
        for (Flight flight : flights) {
            int ammountsOfSegment = flight.getSegments().size();
            while (ammountsOfSegment > 0) {
                if (flight.getSegments().get(ammountsOfSegment - 1).getDepartureDate().isAfter(flight.getSegments().get(ammountsOfSegment - 1).getArrivalDate())) {
                    outputList.remove(flight);
                }
                ammountsOfSegment--;
            }
        }
        return outputList;
    }
    public static List<Flight> sortOutFlightsBySegment(List<Flight> flights) {
        List<Flight> outputList = new LinkedList<>(flights);

        for (Flight flight : flights) {
            int amountOfSegments = flight.getSegments().size();
            while (amountOfSegments > 0) {
                if ((flight.getSegments().get(amountOfSegments-1).getDepartureDate()).isAfter(flight.getSegments().get(amountOfSegments-1).getArrivalDate())) {
                    outputList.remove(flight);
                }
                amountOfSegments--;
            }
        }

        return outputList;
    }



    public static List<Flight> sortOutFlightsTwoHoursGround(List<Flight> flights) {
        List<Flight> outputList = new ArrayList<>(flights);
        for (Flight flight : flights) {
            List<Segment> segments = new ArrayList<>(flight.getSegments());
            int ammountOfSegment = segments.size();
            long totalGroundTime = 0;
            while (ammountOfSegment >= 2) {
                LocalDateTime nextTakeOff = flight.getSegments().get(ammountOfSegment - 1).getDepartureDate();
                LocalDateTime lastArrival = flight.getSegments().get(ammountOfSegment - 2).getArrivalDate();
                long minutes = ChronoUnit.MINUTES.between(lastArrival, nextTakeOff);
                totalGroundTime = totalGroundTime + minutes;
                ammountOfSegment--;
            }
            if (totalGroundTime >= 120) {
                outputList.remove(flight);
            }
        }
            return outputList;
        }

}
