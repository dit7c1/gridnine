package com.gridnine.testing;

import java.util.List;

public interface FiltrationRules {
    // вылет до текущего момента времени.
    List<Flight> departureToCurrentTime(List<Flight> flightsList);

    // сегменты с датой прилёта раньше даты вылета.
    List<Flight> segmentsWithArrivalDateBeforeDepartureDate(List<Flight> flightsList);

    // общее время, проведённое на земле превышает два часа.
    // (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним).
    List<Flight> totalTimeOnGroundExceedsTwoHours(List<Flight> flightsList);

    // просмотр полётов.
    void showFlights(List<Flight> flightsList);
}
