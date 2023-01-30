package tf.lehroptimierung.semester;

public class SemesterTimes {

    private int courseNumber; // Anzahl der Kurse in dem Semester

    private double[] timeContent; // Array mit den Zeitanteilen f?r die wissenschaftliche Vorbereitung

    private double[] timeDidactic; // Array mit den Zeitanteilen f?r die didaktische Vorbereitung

    private double[] timePresentation; // Array mit den Zeitanteilen f?r die Vorbereitung der Pr?sentation


    // *** Konstruktor
    public SemesterTimes ( int number )	{

        this.courseNumber = number;

        timeContent = new double [ courseNumber ];
        timeDidactic = new double [ courseNumber ];
        timePresentation = new double [ courseNumber ];

    }


    // *** Klonen des Objektes
    @Override
    public SemesterTimes clone() {
        SemesterTimes semT = new SemesterTimes( this.courseNumber );
        for ( int i = 0; i < this.courseNumber; i++ ) {
            semT.setTimes( i, this.timeContent[i], this.timeDidactic[i], this.timePresentation[i] );
        }
        return semT;
    }



    // *** Gibt die Anzahl der Kurse zur?ck
    public int getCourseNumber() {
        return this.courseNumber;
    }

    // *** Setzt die Semesterzeiten f?r einen Kurs
    public void setTimes ( int courseNo, double timeContent, double timeDidactic, double timePresentation ) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        if (timeContent < 0) {
            System.err.println("Error: Content time out of range - negative!");
            return;
        }

        if (timeDidactic < 0) {
            System.err.println("Error: Didactic time out of range - negative!");
            return;
        }

        if (timePresentation < 0) {
            System.err.println("Error: Presentation time out of range - negative!");
            return;
        }

        this.timeContent [ courseNo ] = timeContent;
        this.timeDidactic [ courseNo ] = timeDidactic;
        this.timePresentation [ courseNo ] = timePresentation;
    }

    // *** Setzt die Zeit f?r die Inhaltsvorbereitung f?r einen Kurs
    public void setTimeContent ( int courseNo, double timeContent ) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        if (timeContent < 0) {
            System.err.println("Error: Content time out of range - negative!");
            return;
        }

        this.timeContent [ courseNo ] = timeContent;
    }

    // *** Setzt die ZEit f?r die Didaktikvorbereitung f?r einen Kurs
    public void setTimeDidactic ( int courseNo, double timeDidactic ) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        if (timeDidactic < 0) {
            System.err.println("Error: Didactic time out of range - negative!");
            return;
        }

        this.timeDidactic [ courseNo ] = timeDidactic;
    }

    // *** Setzt die Zeit f?r die Pr?sentationsvorbereitung f?r einen Kurs
    public void setTimePresentation ( int courseNo, double timePresentation ) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        if (timePresentation < 0) {
            System.err.println("Error: Presentation time out of range - negative!");
            return;
        }

        this.timePresentation [ courseNo ] = timePresentation;
    }

    // *** Gibt die Zeit f?r die Inhaltsvorbereitung zur?ck
    public double getTimeContent ( int courseNo ) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return (double) -1;
        }

        return timeContent [ courseNo ];
    }

    // *** Gibt die Zeit f?r die Didaktikvorbereitung zur?ck
    public double getTimeDidactic ( int courseNo ) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return (double) -1;
        }

        return timeDidactic [ courseNo ];
    }

    // *** Gibt die Zeit f?r die Pr?sentationsvorbereitung zur?ck
    public double getTimePresentation ( int courseNo ) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return (double) -1;
        }

        return timePresentation [ courseNo ];
    }

    // *** Gibt die Zeiten f?r einen Kurs auf dem Bildschirm aus
    public void print ( int courseNo ) {
        java.lang.System.out.println( "Kursnummer: " + courseNo );
        java.lang.System.out.println( "Zeit Inhaltsvorbereitung: " + this.timeContent[ courseNo ] );
        java.lang.System.out.println( "Zeit Didaktikvorbereitung: " + this.timeDidactic[ courseNo ] );
        java.lang.System.out.println( "Zeit Pr?sentationsvorbereitung: " + this.timePresentation[ courseNo ] );
    }

    // *** Gibt die Zeiten f?r einen Kurs auf dem Bildschirm aus
    public void print () {
        for ( int i=0; i<courseNumber; i++) {
            java.lang.System.out.println( "Kursnummer: " + i );
            java.lang.System.out.println( "Zeit Inhaltsvorbereitung: " + this.timeContent[ i ] );
            java.lang.System.out.println( "Zeit Didaktikvorbereitung: " + this.timeDidactic[ i ] );
            java.lang.System.out.println( "Zeit Pr?sentationsvorbereitung: " + this.timePresentation[ i ] );
        }
    }

}
