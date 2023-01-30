package tf.lehroptimierung.optimization;

import tf.lehroptimierung.semester.*;

import java.lang.Math;

public class SemOpti_Random {

    private SemOpti_Random () {	}

    public static SemesterTimes optimize ( Semester semester, int anzahlSearch, String method ) {

        int courseNumber = semester.getNumber();

        SemesterTimes times = new SemesterTimes ( courseNumber );
        SemesterTimes timesOpti = new SemesterTimes ( courseNumber );

        double resultOpti = 0;

        // *** Schleife f?r Anzahl der Versuche f?r die statistische Optimierung
        for (int versuch = 0; versuch < anzahlSearch; versuch++ ) {

            // *** Hilfsvariablen zur Initialisierung der Semesterzeiten
            double timeContent;
            double timeDidactic;
            double timePresentation;
            double timeSum = 0;

            // *** Schleife zur zuf?lligen Initialisierung der Semesterzeiten
            for (int course = 0; course < courseNumber; course++ ) {

                timeContent = Math.random();
                timeDidactic = Math.random();
                timePresentation = Math.random();
                timeSum += (timeContent + timeDidactic + timePresentation);

                times.setTimes(course, timeContent, timeDidactic, timePresentation);

            }

//			System.out.println("Summe Semesterzeiten roh " + timeSum);


            // *** Schleife zur Normierung der Semesterzeiten auf Summe 1
            for (int course = 0; course < courseNumber; course++ ) {

                times.setTimeContent(course, times.getTimeContent(course) / timeSum);
                times.setTimeDidactic(course, times.getTimeDidactic(course) / timeSum);
                times.setTimePresentation(course, times.getTimePresentation(course) / timeSum);

            }




//			System.out.println("Runde " + versuch);

            // *** Pr?fung, ob neue L?sung am besten ist mit Fallunterscheidung, welches Berechnungsverfahren verwendet werden soll

            double result = 0;

            switch (method) {

                case "product":
                    result = semester.calcProduct(times);
                    break;

                case "sum":
                    result = semester.calcSum(times);
                    break;

                case "sqrt":
                    result = semester.calcSqrt(times);
                    break;

                case "min":
                    result = semester.calcMin(times);
                    break;

                default:
                    System.err.println("Error: No valid optimization method!");
                    break;

            }

//			System.out.println("Ergebnis der Semesterberechnung " + result);


            if ( result > resultOpti) {
                resultOpti = result;
                timesOpti = times.clone();
            }

        }

        System.out.println("Bestes Ergebnis der Semesterberechnung zuf?llige Suche: " + resultOpti);


        return timesOpti;
    }

}
