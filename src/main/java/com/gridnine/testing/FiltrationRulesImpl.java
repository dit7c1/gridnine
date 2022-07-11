package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FiltrationRulesImpl implements FiltrationRules {

    // вылет до текущего момента времени.
    @Override
    public List<Flight> departureToCurrentTime(List<Flight> flightsList) {
        List<Flight> resultFlightsList = new ArrayList<>();

        LocalDateTime nowDateTime = LocalDateTime.now();
        LocalDateTime departureDateTime;

        if (!flightsList.isEmpty()) {
            for (Flight flightLoop : flightsList) {
                resultFlightsList.add(flightLoop);
                for (int currentIndex = 0; currentIndex < flightLoop.getSegments().size(); currentIndex++) {
                    departureDateTime = flightLoop.getSegments().get(currentIndex).getDepartureDate();
                    if (departureDateTime.isBefore(nowDateTime)) {
                        resultFlightsList.remove(flightLoop);
                    }
                }
            }
        }

        return resultFlightsList;
    }

    // сегменты с датой прилёта раньше даты вылета.
    @Override
    public List<Flight> segmentsWithArrivalDateBeforeDepartureDate(List<Flight> flightsList) {
        List<Flight> resultFlightsList = new ArrayList<>();

        LocalDateTime departureDateTime;
        LocalDateTime arrivaleDateTime;

        if (!flightsList.isEmpty()) {
            for (Flight flightLoop : flightsList) {
                resultFlightsList.add(flightLoop);
                for (int currentIndex = 0; currentIndex < flightLoop.getSegments().size(); currentIndex++) {
                    departureDateTime = flightLoop.getSegments().get(currentIndex).getDepartureDate();
                    arrivaleDateTime = flightLoop.getSegments().get(currentIndex).getArrivalDate();
                    if (departureDateTime.isAfter(arrivaleDateTime)) {
                        resultFlightsList.remove(flightLoop);
                    }
                }
            }
        }

        return resultFlightsList;
    }

    // общее время, проведённое на земле превышает два часа.
    // (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним).
    @Override
    public List<Flight> totalTimeOnGroundExceedsTwoHours(List<Flight> flightsList) {
        List<Flight> resultFlightsList = new ArrayList<>();

        Duration duration = Duration.ZERO;
        LocalDateTime departureDateTime;
        LocalDateTime arrivaleDateTime;

        if (!flightsList.isEmpty()) {
            for (Flight flightLoop : flightsList) {
                resultFlightsList.add(flightLoop);
                for (int currentIndex = 1; currentIndex < flightLoop.getSegments().size(); currentIndex++) {
                    departureDateTime = flightLoop.getSegments().get(currentIndex).getDepartureDate();
                    arrivaleDateTime = flightLoop.getSegments().get(currentIndex - 1).getArrivalDate();
                    duration = duration.plus(Duration.between(arrivaleDateTime, departureDateTime).abs());
                    if (duration.toHours() > 2) {
                        resultFlightsList.remove(flightLoop);
                    }
                }
            }
        }

        return resultFlightsList;
    }

    // просмотр полётов.
    @Override
    public void showFlights(List<Flight> flightsList) {
        if (!flightsList.isEmpty()) {
            for (Flight flightsLoop : flightsList) {
                System.out.println(flightsLoop);
            }
        }
    }
}
