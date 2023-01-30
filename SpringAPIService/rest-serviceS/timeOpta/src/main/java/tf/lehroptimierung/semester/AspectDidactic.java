package tf.lehroptimierung.semester;

public class AspectDidactic {

    private double n; // Wert zwischen 0 und 1 f�r Familiarit�t mit der Didaktik

    private double j; // Wert zwischen 1 und etwa 20 f�r die Komplexit�t der Didaktik

    AspectDidactic () {

        n = 0;

        j = 1;
    }

    AspectDidactic (double familiarity, double complexity) {

        n = familiarity;

        j = complexity;
    }

    void set (double familiarity, double complexity) {

        n = familiarity;

        j = complexity;
    }

    double calc (double time) {

        return( ( 1 - n ) * Math.pow(time, (1.0 / j) ) + n );
    }



}
