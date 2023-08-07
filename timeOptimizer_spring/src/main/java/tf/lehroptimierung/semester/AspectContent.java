package tf.lehroptimierung.semester;

public class AspectContent {

    private double m; // Wert zwischen 0 und 1 f�r Familiarit�t mit dem Stoff

    private double i; // Wert zwischen 1 und etwa 20 f�r die Komplexit�t des Kurses

    private double w;

    AspectContent () {

        m = 0;

        i = 1;

        w = 1;
    }

    AspectContent (double familiarity, double complexity, double weight) {

        m = familiarity;

        i = complexity;

        w = weight;
    }

    void set (double familiarity, double complexity, double weight) {

        m = familiarity;

        i = complexity;

        w = weight;
    }

    public double getWeight() {
        return w;
    }

    double calc (double time) {

        return( ( 1 - m ) * Math.pow(time, (1.0 / i) ) + m );
    }



}
