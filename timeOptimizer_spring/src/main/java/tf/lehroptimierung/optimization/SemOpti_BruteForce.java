package tf.lehroptimierung.optimization;

import tf.lehroptimierung.semester.*;

public class SemOpti_BruteForce {

    private SemOpti_BruteForce () {	}

    static SemesterTimes optimize ( Semester semester ) {

        int courseNumber = semester.getNumber();

        SemesterTimes times = new SemesterTimes ( courseNumber );
        SemesterTimes timesOpti = new SemesterTimes ( courseNumber );


        return timesOpti;
    }

}
