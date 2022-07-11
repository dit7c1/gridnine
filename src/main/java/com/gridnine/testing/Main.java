package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightsList = FlightBuilder.createFlights();
        FiltrationRules filtrationRules = new FiltrationRulesImpl();

        System.out.println("*****************");
        System.out.println("* Все перелёты: *");
        System.out.println("*****************");
        filtrationRules.showFlights(flightsList);
        System.out.println("------------------------------end------------------------------\n\n");

        System.out.println("*****************************************************************");
        System.out.println("* Исключаем перелёты когда - вылет до текущего момента времени: *");
        System.out.println("*****************************************************************");
        filtrationRules.showFlights(filtrationRules.departureToCurrentTime(flightsList));
        System.out.println("------------------------------end------------------------------\n\n");

        System.out.println("***********************************************************************************");
        System.out.println("* Исключаем перелёты когда - имеются сегменты с датой прилёта раньше даты вылета: *");
        System.out.println("***********************************************************************************");
        filtrationRules.showFlights(filtrationRules.segmentsWithArrivalDateBeforeDepartureDate(flightsList));
        System.out.println("------------------------------end------------------------------\n\n");

        System.out.println("************************************************************************************");
        System.out.println("* Исключаем перелёты когда - общее время, проведённое на земле превышает два часа: *");
        System.out.println("************************************************************************************");
        filtrationRules.showFlights(filtrationRules.totalTimeOnGroundExceedsTwoHours(flightsList));
        System.out.println("------------------------------end------------------------------");
    }
}
