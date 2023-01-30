package tf.lehroptimierung.semester;

public class Semester {

    private final int courseNumber; // Anzahl der Kurse in dem Semester

    private String name; // Name des Semesters

    private Course[] course; // Array mit allen Kursen f?r das Semester

    private int[] students; // Array mit Studis in jedem Kurs

    private double[] lecTime; // Array mit Stunden pro Woche f?r jeden Kurs

    private double[] impact; // Array mit dem Impactfaktor f?r jeden Kurs


    // *** Konstruktor
    public Semester( int number ) {
        this.courseNumber = number;

        course = new Course[ this.courseNumber ];
        students = new int[ this.courseNumber ];
        lecTime = new double[ this.courseNumber ];
        impact = new double[ this.courseNumber ];

        // Initialisierung des Arrays course
        for (int i = 0; i < this.courseNumber; i++) {
            course [ i ] = new Course ();
        }

    }

    // *** Gibt die Anzahl der Kurse in dem Semester zur?ck
    public int getNumber () {

        return courseNumber;

    }

    // *** Setzt die Anzahl der Studis in einem Kurs
    public void setStudents ( int courseNo, int students) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        if (students < 0) {
            System.err.println("Error: Student number out of range!");
            return;
        }

        this.students [ courseNo ]= students;
    }

    // *** Setzt die Anzahl der Semesterwochenstunden in einem Kurs
    public void setLecTime ( int courseNo, double d) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        if (d < 0) {
            System.err.println("Error: Lecture time out of range!");
            return;
        }

        this.lecTime [ courseNo ]= d;
    }

    // *** Setzt einen Impact-Facor in einem Kurs.
// *** Dieser h?ngt vom Semester der Studis ab
    public void setImpact ( int courseNo, double d) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        if (d < 0) {
            System.err.println("Error: Impact out of range!");
            return;
        }

        this.impact [ courseNo ]= d;
    }

    // *** Setzt die Faktoren f?r den Kursinhalt eines Kurses
    public void setCourseContent ( int courseNo, double familiarity, double complexity) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        this.course [ courseNo ].setContent( familiarity, complexity );
    }

    // *** Setzt die Faktoren f?r die Didaktik in einem Kurs
    public void setCourseDidactic ( int courseNo, double familiarity, double complexity) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        this.course [ courseNo ].setDidactic( familiarity, complexity );
    }

    // *** Setzt die Faktoren f?r die Pr?sentation in einem Kurs
    public void setCoursePresentation ( int courseNo, double finished, double time0, double pres0, double complexity) {
        if ( courseNo < 0 || courseNo >= courseNumber ) {
            System.err.println("Error: Course number out of range!");
            return;
        }

        this.course [ courseNo ].setPresentation( finished, time0, pres0, complexity );
    }

    // *** Setzt den Namen eines Kurses
    public void setCourseName ( int courseNo, String name ) {
        this.course [ courseNo ].setName(name);
    }


    // *** Gibt den Namen eines Kurses zur?ck
    public String getCourseName ( int courseNo ) {
        return this.course [ courseNo ].getName();
    }

    // *** Setzt den Namen des Semesters
    public void setSemesterName ( String name ) {
        this.name = name;
    }


    // *** Gibt den Namen des Semesters zur?ck
    public String getSemesterName () {
        return this.name;
    }


    // *** Berechnet den Ergebnisfaktor f?r ein Semester in Abh?ngigkeit von den Vorbereitungszeiten mit Produkt
    public double calcProduct ( SemesterTimes times) {
        double sum = 0;
        for ( int i = 0; i < courseNumber; i++) {
            sum += course [ i ].calcProduct( times.getTimeContent ( i ), times.getTimeDidactic ( i ), times.getTimePresentation ( i ) ) * (double) students [ i ] * (double) lecTime [ i ] * impact [ i ];
        }
        return sum;
    }

    // *** Berechnet den Ergebnisfaktor f?r ein Semester in Abh?ngigkeit von den Vorbereitungszeiten mit Summe
    public double calcSum ( SemesterTimes times) {
        double sum = 0;
        for ( int i = 0; i < courseNumber; i++) {
            sum += course [ i ].calcSum( times.getTimeContent ( i ), times.getTimeDidactic ( i ), times.getTimePresentation ( i ) ) * (double) students [ i ] * (double) lecTime [ i ] * impact [ i ];
        }
        return sum;
    }

    // *** Berechnet den Ergebnisfaktor f?r ein Semester in Abh?ngigkeit von den Vorbereitungszeiten mit Wurzelsumme
    public double calcSqrt ( SemesterTimes times) {
        double sum = 0;
        for ( int i = 0; i < courseNumber; i++) {
            sum += course [ i ].calcSqrt( times.getTimeContent ( i ), times.getTimeDidactic ( i ), times.getTimePresentation ( i ) ) * Math.sqrt( (double) students [ i ] * (double) lecTime [ i ] * impact [ i ] );
        }
        return sum;
    }

    // *** Berechnet den Ergebnisfaktor f?r ein Semester in Abh?ngigkeit von den Vorbereitungszeiten mit Wurzelsumme
    public double calcMin ( SemesterTimes times) {
        double sum = 1e33;
        for ( int i = 0; i < courseNumber; i++) {
            sum = Math.min( sum, course [ i ].calcMin( times.getTimeContent ( i ), times.getTimeDidactic ( i ), times.getTimePresentation ( i ) ) );
        }
        return sum;
    }



}
