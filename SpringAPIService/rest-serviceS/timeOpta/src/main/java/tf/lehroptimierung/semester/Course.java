package tf.lehroptimierung.semester;
public class Course {

    private AspectContent content = new AspectContent();

    private AspectDidactic didactic = new AspectDidactic();

    private AspectPresentation presentation = new AspectPresentation();

    private String name = new String();


    Course() {
    }

    // *** Setzt den Faktor f�r den Inhalt f�r einen Kurs
    void setContent ( double familiarity, double complexity ) {
        content.set ( familiarity, complexity );
    }

    // *** Setzt den Faktor f�r die Didaktik f�r einen Kurs
    void setDidactic ( double familiarity, double complexity ) {
        didactic.set ( familiarity, complexity );
    }

    // *** Setzt den Faktor f�r die Pr�sentation f�r einen Kurs
    void setPresentation ( double finished, double time0, double pres0, double complexity ) {
        presentation.set ( finished, time0, pres0, complexity );
    }

    // *** Setzt den Kursnamen
    void setName (String name) {
        this.name = name;
    }

    // *** Gibt den Kursnamen zur�ck
    String getName() {
        return this.name;
    }

    // *** Berechnet das Produkt der Werte f�r einen Kurs
    double calcProduct ( double timeContent, double timeDidactic, double timePresentation ) {
        return( content.calc( timeContent ) * didactic.calc( timeDidactic ) * presentation.calc( timePresentation ));
    }

    // *** Berechnet die Summe der Werte f�r einen Kurs
    double calcSum ( double timeContent, double timeDidactic, double timePresentation ) {
        return( content.calc( timeContent ) + didactic.calc( timeDidactic ) + presentation.calc( timePresentation ));
    }

    // *** Berechnet die Quadratsumme der Werte f�r einen Kurs
    double calcSqrt ( double timeContent, double timeDidactic, double timePresentation ) {
        return( Math.sqrt( content.calc( timeContent ) ) + Math.sqrt( didactic.calc( timeDidactic ) ) + Math.sqrt( presentation.calc( timePresentation ) ) );
    }

    // *** Berechnet das Minimum der Werte f�r einen Kurs
    double calcMin ( double timeContent, double timeDidactic, double timePresentation ) {
        return( Math.min(content.calc( timeContent ), Math.min( didactic.calc( timeDidactic ) , presentation.calc( timePresentation ) ) ) );
    }

}
