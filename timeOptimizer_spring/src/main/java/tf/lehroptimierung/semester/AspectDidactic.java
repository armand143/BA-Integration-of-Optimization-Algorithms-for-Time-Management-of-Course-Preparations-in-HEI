package tf.lehroptimierung.semester;

public class AspectDidactic {

    private double n; // Wert zwischen 0 und 1 f�r Familiarit�t mit der Didaktik

    private double j; // Wert zwischen 1 und etwa 20 f�r die Komplexit�t der Didaktik

    private double w;

    AspectDidactic () {

        n = 0;

        j = 1;

        w = 1;
    }

    AspectDidactic (double familiarity, double complexity, double weight) {

        n = familiarity;

        j = complexity;

        w = weight;
    }

    void set (double familiarity, double complexity, double weight) {

        n = familiarity;

        j = complexity;

        w = weight;
    }

    public double getWeight() {
        return w;
    }

    double calc (double time) {

        return( ( 1 - n ) * Math.pow(time, (1.0 / j) ) + n );
    }



}
