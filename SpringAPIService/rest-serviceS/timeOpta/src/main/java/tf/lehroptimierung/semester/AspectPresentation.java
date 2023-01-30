package tf.lehroptimierung.semester;

public class AspectPresentation {

    private double o; // Wert zwischen 0 und 1 für den Anteil, der schon fertig ist

    private double t0; // Wert zwischen 0 und 1 für den Zeitwert des Wendepunkts der Kurve

    private double p0; // Wert zwischen 0 und 1 für den Präsentationswert des Wendepunktes der Kurve

    private double k; // Wert zwischen 1 und etwa 20 für die Komplexität der Präsentation

    AspectPresentation () {

        o = 0;

        t0 = 0;

        p0 = 0;

        k = 1;
    }

    AspectPresentation (double finished, double time0, double pres0, double complexity) {

        o = finished;

        t0 = time0;

        p0 = pres0;

        k = complexity;
    }

    void set (double finished, double time0, double pres0, double complexity) {

        o = finished;

        t0 = time0;

        p0 = pres0;

        k = complexity;
    }

    double calc (double time) {

        if (time <= t0)

            return( p0 - (p0 - o) / Math.pow(t0, k) * Math.pow(t0 - time, k));

        else

            return( ( 1 - p0 ) / ( Math.pow( (1 - t0) , k) ) * Math.pow( (time - t0), k) + p0);

    }


}
