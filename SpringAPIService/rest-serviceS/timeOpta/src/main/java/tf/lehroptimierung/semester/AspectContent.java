package tf.lehroptimierung.semester;

public class AspectContent {

    private double m; // Wert zwischen 0 und 1 für Familiarität mit dem Stoff

    private double i; // Wert zwischen 1 und etwa 20 für die Komplexität des Kurses

    AspectContent () {

        m = 0;

        i = 1;
    }

    AspectContent (double familiarity, double complexity) {

        m = familiarity;

        i = complexity;
    }

    void set (double familiarity, double complexity) {

        m = familiarity;

        i = complexity;
    }

    double calc (double time) {

        return( ( 1 - m ) * Math.pow(time, (1.0 / i) ) + m );
    }



}
