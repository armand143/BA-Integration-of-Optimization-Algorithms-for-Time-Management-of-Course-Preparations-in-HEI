package tf.lehroptimierung.optimization;

import tf.lehroptimierung.semester.*;

import java.util.Arrays;

public class SemOpti_Local {

    private SemOpti_Local () {	}

    public static SemesterTimes optimize ( Semester semester, SemesterTimes timesStart, double maxStep, int anzahlSearch, String method ) {

        int courseNumber = semester.getNumber();

        double step = maxStep; // *** Schrittweite f?r lokale Suche

        SemesterTimes times = new SemesterTimes ( courseNumber ); // aktuelle Semesterzeiten
        SemesterTimes timesOld = new SemesterTimes (courseNumber); // alte Semesterzeiten, zwischengespeichert
        SemesterTimes timesOpti = new SemesterTimes ( courseNumber ); // Semesterzeiten f?r optimales Ergebnis

        double result = 0;
        double resultOld = 0;
        double resultOpti = 0;

        times = timesStart; // Startwert f?r die Zeiten

        // *** Schleife f?r Anzahl der Versuche f?r die lokale Optimierung
        for (int versuch = 0; versuch < anzahlSearch; versuch++ ) {


            // *** Schleife durch alle Kurse, so dass jeder Kurs nacheinander optimiert wird.
            for (int course = 0; course < courseNumber; course++ ) {


                // *** Hilfsvariablen zur Initialisierung der Semesterzeiten
                double timeContent;
                double timeDidactic;
                double timePresentation;
                double timeSum = 0;

                double x;

                // *****
                // *** Zuf?llige Erh?hung einer Zeit f?r den Inhalt
                // *****

                timesOld = times.clone(); // aktuelle Zeiten werden gerettet, falls die neue L?sung schlechter ist als die alte


                times.setTimeContent(course, times.getTimeContent(course) + Math.random() * step); // Zeit wird um einen Wert erh?ht

                // *** Durch die ?nderung einer Zeit ist die Zeitsumme nicht mehr normiert 1, deshalb muss neu normiert werden
                timeSum = 0;

                // *** Alle Zeiten werden abgefragt und aufsummiert
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    timeSum += times.getTimeContent(courseInt) + times.getTimeDidactic(courseInt) + times.getTimePresentation(courseInt);

                }

                // *** Schleife zur Normierung der Semesterzeiten auf Summe 1
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    times.setTimeContent(courseInt, times.getTimeContent(courseInt) / timeSum);
                    times.setTimeDidactic(courseInt, times.getTimeDidactic(courseInt) / timeSum);
                    times.setTimePresentation(courseInt, times.getTimePresentation(courseInt) / timeSum);

                }

//			System.out.println("Runde " + versuch);

                // *** Pr?fung, ob neue L?sung am besten ist

                switch (method) {

                    case "product":
                        result = semester.calcProduct(times);
                        resultOld = semester.calcProduct(timesOld);
                        break;

                    case "sum":
                        result = semester.calcSum(times);
                        resultOld = semester.calcSum(timesOld);
                        break;

                    case "sqrt":
                        result = semester.calcSqrt(times);
                        resultOld = semester.calcSqrt(timesOld);
                        break;

                    case "min":
                        result = semester.calcMin(times);
                        resultOld = semester.calcMin(timesOld);
                        break;

                    default:
                        System.err.println("Error: No valid optimization method!");
                        break;

                }

//			System.out.println("Ergebnis der Semesterberechnung " + result);

                // *** Wenn das alte Ergebnis besser ist als das neue, dann wird das alte Ergebnis beibehalten
                if ( resultOld > result) {
                    result = resultOld;
                    times = timesOld.clone();
                }

                if ( result > resultOpti) {
                    resultOpti = result;
                    timesOpti = times.clone();
                }

                // *****
                // *** Zuf?llige Verringerung einer Zeit f?r den Inhalt
                // *****

                timesOld = times.clone(); // aktuelle Zeiten werden gerettet, falls die neue L?sung schlechter ist als die alte

                times.setTimeContent(course, (x = times.getTimeContent(course) - Math.random() * step) > 0 ? x : 0); // Zeit wird um einen Wert verringert

                // *** Durch die ?nderung einer Zeit ist die Zeitsumme nicht mehr normiert 1, deshalb muss neu normiert werden
                timeSum = 0;

                // *** Alle Zeiten werden abgefragt und aufsummiert
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    timeSum += times.getTimeContent(courseInt) + times.getTimeDidactic(courseInt) + times.getTimePresentation(courseInt);

                }

                // *** Schleife zur Normierung der Semesterzeiten auf Summe 1
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    times.setTimeContent(courseInt, times.getTimeContent(courseInt) / timeSum);
                    times.setTimeDidactic(courseInt, times.getTimeDidactic(courseInt) / timeSum);
                    times.setTimePresentation(courseInt, times.getTimePresentation(courseInt) / timeSum);

                }

//			System.out.println("Runde " + versuch);

                // *** Pr?fung, ob neue L?sung am besten ist

                switch (method) {

                    case "product":
                        result = semester.calcProduct(times);
                        resultOld = semester.calcProduct(timesOld);
                        break;

                    case "sum":
                        result = semester.calcSum(times);
                        resultOld = semester.calcSum(timesOld);
                        break;

                    case "sqrt":
                        result = semester.calcSqrt(times);
                        resultOld = semester.calcSqrt(timesOld);
                        break;

                    case "min":
                        result = semester.calcMin(times);
                        resultOld = semester.calcMin(timesOld);
                        break;

                    default:
                        System.err.println("Error: No valid optimization method!");
                        break;

                }

//			System.out.println("Ergebnis der Semesterberechnung " + result);

                // *** Wenn das alte Ergebnis besser ist als das neue, dann wird das alte Ergebnis beibehalten
                if ( resultOld > result) {
                    result = resultOld;
                    times = timesOld.clone();
                }

                if ( result > resultOpti) {
                    resultOpti = result;
                    timesOpti = times.clone();
                }

                // *****
                // *** Zuf?llige Erh?hung einer Zeit f?r die Didaktik
                // *****

                timesOld = times.clone(); // aktuelle Zeiten werden gerettet, falls die neue L?sung schlechter ist als die alte

                times.setTimeDidactic(course, times.getTimeDidactic(course) + Math.random() * step); // Zeit wird um einen Wert erh?ht

                // *** Durch die ?nderung einer Zeit ist die Zeitsumme nicht mehr normiert 1, deshalb muss neu normiert werden
                timeSum = 0;

                // *** Alle Zeiten werden abgefragt und aufsummiert
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    timeSum += times.getTimeContent(courseInt) + times.getTimeDidactic(courseInt) + times.getTimePresentation(courseInt);

                }

                // *** Schleife zur Normierung der Semesterzeiten auf Summe 1
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    times.setTimeContent(courseInt, times.getTimeContent(courseInt) / timeSum);
                    times.setTimeDidactic(courseInt, times.getTimeDidactic(courseInt) / timeSum);
                    times.setTimePresentation(courseInt, times.getTimePresentation(courseInt) / timeSum);

                }

//			System.out.println("Runde " + versuch);

                // *** Pr?fung, ob neue L?sung am besten ist

                switch (method) {

                    case "product":
                        result = semester.calcProduct(times);
                        resultOld = semester.calcProduct(timesOld);
                        break;

                    case "sum":
                        result = semester.calcSum(times);
                        resultOld = semester.calcSum(timesOld);
                        break;

                    case "sqrt":
                        result = semester.calcSqrt(times);
                        resultOld = semester.calcSqrt(timesOld);
                        break;

                    case "min":
                        result = semester.calcMin(times);
                        resultOld = semester.calcMin(timesOld);
                        break;

                    default:
                        System.err.println("Error: No valid optimization method!");
                        break;

                }

//			System.out.println("Ergebnis der Semesterberechnung " + result);

                // *** Wenn das alte Ergebnis besser ist als das neue, dann wird das alte Ergebnis beibehalten
                if ( resultOld > result) {
                    result = resultOld;
                    times = timesOld.clone();
                }

                if ( result > resultOpti) {
                    resultOpti = result;
                    timesOpti = times.clone();
                }

                // *****
                // *** Zuf?llige Verringerung einer Zeit f?r die Didaktik
                // *****

                timesOld = times.clone(); // aktuelle Zeiten werden gerettet, falls die neue L?sung schlechter ist als die alte

                times.setTimeDidactic(course, (x = times.getTimeDidactic(course) - Math.random() * step) > 0 ? x : 0); // Zeit wird um einen Wert verringert

                // *** Durch die ?nderung einer Zeit ist die Zeitsumme nicht mehr normiert 1, deshalb muss neu normiert werden
                timeSum = 0;

                // *** Alle Zeiten werden abgefragt und aufsummiert
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    timeSum += times.getTimeContent(courseInt) + times.getTimeDidactic(courseInt) + times.getTimePresentation(courseInt);

                }

                // *** Schleife zur Normierung der Semesterzeiten auf Summe 1
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    times.setTimeContent(courseInt, times.getTimeContent(courseInt) / timeSum);
                    times.setTimeDidactic(courseInt, times.getTimeDidactic(courseInt) / timeSum);
                    times.setTimePresentation(courseInt, times.getTimePresentation(courseInt) / timeSum);

                }

//			System.out.println("Runde " + versuch);

                // *** Pr?fung, ob neue L?sung am besten ist

                switch (method) {

                    case "product":
                        result = semester.calcProduct(times);
                        resultOld = semester.calcProduct(timesOld);
                        break;

                    case "sum":
                        result = semester.calcSum(times);
                        resultOld = semester.calcSum(timesOld);
                        break;

                    case "sqrt":
                        result = semester.calcSqrt(times);
                        resultOld = semester.calcSqrt(timesOld);
                        break;

                    case "min":
                        result = semester.calcMin(times);
                        resultOld = semester.calcMin(timesOld);
                        break;

                    default:
                        System.err.println("Error: No valid optimization method!");
                        break;
                }

//			System.out.println("Ergebnis der Semesterberechnung " + result);

                // *** Wenn das alte Ergebnis besser ist als das neue, dann wird das alte Ergebnis beibehalten
                if ( resultOld > result) {
                    result = resultOld;
                    times = times.clone();
                }

                if ( result > resultOpti) {
                    resultOpti = result;
                    timesOpti = times.clone();
                }

                // *****
                // *** Zuf?llige Erh?hung einer Zeit f?r die Pr?sentation
                // *****

                timesOld = times.clone(); // aktuelle Zeiten werden gerettet, falls die neue L?sung schlechter ist als die alte

                times.setTimePresentation(course, times.getTimePresentation(course) + Math.random() * step); // Zeit wird um einen Wert erh?ht

                // *** Durch die ?nderung einer Zeit ist die Zeitsumme nicht mehr normiert 1, deshalb muss neu normiert werden
                timeSum = 0;

                // *** Alle Zeiten werden abgefragt und aufsummiert
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    timeSum += times.getTimeContent(courseInt) + times.getTimeDidactic(courseInt) + times.getTimePresentation(courseInt);

                }

                // *** Schleife zur Normierung der Semesterzeiten auf Summe 1
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    times.setTimeContent(courseInt, times.getTimeContent(courseInt) / timeSum);
                    times.setTimeDidactic(courseInt, times.getTimeDidactic(courseInt) / timeSum);
                    times.setTimePresentation(courseInt, times.getTimePresentation(courseInt) / timeSum);

                }

//			System.out.println("Runde " + versuch);

                // *** Pr?fung, ob neue L?sung am besten ist

                switch (method) {

                    case "product":
                        result = semester.calcProduct(times);
                        resultOld = semester.calcProduct(timesOld);
                        break;

                    case "sum":
                        result = semester.calcSum(times);
                        resultOld = semester.calcSum(timesOld);
                        break;

                    case "sqrt":
                        result = semester.calcSqrt(times);
                        resultOld = semester.calcSqrt(timesOld);
                        break;

                    case "min":
                        result = semester.calcMin(times);
                        resultOld = semester.calcMin(timesOld);
                        break;

                    default:
                        System.err.println("Error: No valid optimization method!");
                        break;
                }

//			System.out.println("Ergebnis der Semesterberechnung " + result);

                // *** Wenn das alte Ergebnis besser ist als das neue, dann wird das alte Ergebnis beibehalten
                if ( resultOld > result) {
                    result = resultOld;
                    times = timesOld.clone();
                }

                if ( result > resultOpti) {
                    resultOpti = result;
                    timesOpti = times.clone();
                }

                // *****
                // *** Zuf?llige Verringerung einer Zeit f?r die Pr?sentation
                // *****

                timesOld = times.clone(); // aktuelle Zeiten werden gerettet, falls die neue L?sung schlechter ist als die alte

                times.setTimePresentation(course, (x = times.getTimePresentation(course) - Math.random() * step) > 0 ? x : 0); // Zeit wird um einen Wert verringert

                // *** Durch die ?nderung einer Zeit ist die Zeitsumme nicht mehr normiert 1, deshalb muss neu normiert werden
                timeSum = 0;

                // *** Alle Zeiten werden abgefragt und aufsummiert
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    timeSum += times.getTimeContent(courseInt) + times.getTimeDidactic(courseInt) + times.getTimePresentation(courseInt);

                }

                // *** Schleife zur Normierung der Semesterzeiten auf Summe 1
                for (int courseInt = 0; courseInt < courseNumber; courseInt++ ) {

                    times.setTimeContent(courseInt, times.getTimeContent(courseInt) / timeSum);
                    times.setTimeDidactic(courseInt, times.getTimeDidactic(courseInt) / timeSum);
                    times.setTimePresentation(courseInt, times.getTimePresentation(courseInt) / timeSum);

                }

//			System.out.println("Runde " + versuch);

                // *** Pr?fung, ob neue L?sung am besten ist

                switch (method) {

                    case "product":
                        result = semester.calcProduct(times);
                        resultOld = semester.calcProduct(timesOld);
                        break;

                    case "sum":
                        result = semester.calcSum(times);
                        resultOld = semester.calcSum(timesOld);
                        break;

                    case "sqrt":
                        result = semester.calcSqrt(times);
                        resultOld = semester.calcSqrt(timesOld);
                        break;

                    case "min":
                        result = semester.calcMin(times);
                        resultOld = semester.calcMin(timesOld);
                        break;

                    default:
                        System.err.println("Error: No valid optimization method!");
                        break;
                }

//			System.out.println("Ergebnis der Semesterberechnung " + result);

                // *** Wenn das alte Ergebnis besser ist als das neue, dann wird das alte Ergebnis beibehalten
                if ( resultOld > result) {
                    result = resultOld;
                    times = timesOld.clone();
                }

                if ( result > resultOpti) {
                    resultOpti = result;
                    timesOpti = times.clone();
                }


            } // *** Ende Schleife durch alle Kurse

            step *= 0.999; // *** Reduktion der Variation, um die Suche immer weiter zu verfeinern

        } // *** Ende Schleife f?r Anzahl der Versuche

        System.out.println("Bestes Ergebnis der Semesterberechnung - lokale Optimierung: " + resultOpti);


        return timesOpti;
    }

}
