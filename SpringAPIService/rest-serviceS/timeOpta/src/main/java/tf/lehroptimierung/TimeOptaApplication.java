package tf.lehroptimierung;

//import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import tf.lehroptimierung.optimization.SemOpti_Local;
import tf.lehroptimierung.optimization.SemOpti_Random;
import tf.lehroptimierung.semester.Semester;
import org.json.JSONObject;
import tf.lehroptimierung.semester.SemesterTimes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class TimeOptaApplication {
    static Semester sem2022WiSe;  //sem2022WiSe because that's the class_name used in original code
    public static void init2022WiSe(String jsonString){
        JSONObject myJSON = new JSONObject(jsonString);
        System.out.println("String transformed to JSON: " + myJSON);

        String SemName = myJSON.getString("semesterName");
        Integer NumCourses = myJSON.getJSONObject("courses").length();

        sem2022WiSe = new Semester(NumCourses);
        sem2022WiSe.setSemesterName(SemName);

        Integer counter = 0;
        for (String key : myJSON.getJSONObject("courses").keySet()){

            sem2022WiSe.setCourseName(counter, key);
            //each_course = myJSON.getJSONObject(getString(key));

            Double courseFamCont = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("courseContent").getDouble("CourseFamiliarity");
            Double courseCompCont = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("courseContent").getDouble("CourseComplexity");
            Double courseFamDidac = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("courseDidactic").getDouble("CourseFamiliarity");
            Double courseCompDidac = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("courseDidactic").getDouble("CourseComplexity");
            Double coursePresFinished = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("coursePresentation").getDouble("finished");
            Double coursePresTime0 = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("coursePresentation").getDouble("time0");
            Double coursePres0 = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("coursePresentation").getDouble("pres0");
            Double coursePresComp = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("coursePresentation").getDouble("complexity");
            Double courseImpact = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("courseImpact").getDouble("d");
            Double courseLectTime = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("courseLectTime").getDouble("d");
            Integer courseStudentCount = myJSON.getJSONObject("courses").getJSONObject(key).getJSONObject("courseStudentCount").getInt("students");

            for(String nkey : myJSON.getJSONObject("courses").getJSONObject(key).keySet()){
                sem2022WiSe.setCourseContent(counter, courseFamCont, courseCompCont);
                sem2022WiSe.setCourseDidactic(counter, courseFamDidac, courseCompDidac);
                sem2022WiSe.setCoursePresentation(counter, coursePresFinished, coursePresTime0, coursePres0, coursePresComp);
                sem2022WiSe.setImpact(counter, courseImpact);
                sem2022WiSe.setLecTime(counter, courseLectTime);
                sem2022WiSe.setStudents(counter, courseStudentCount);

            }
            counter += 1;
        }
    }

    public String optimize(String StringContent) {

        // TODO Auto-generated method stub

        JSONObject results = new JSONObject();

        // ** Zu berechnende Semester werden initialisiert
        init2022WiSe(StringContent);

        // ** Das Semester, das optimiert werden soll
        Semester semOpti = sem2022WiSe;


        SemesterTimes times = new SemesterTimes ( semOpti.getNumber() ); // aktuell beste Semesterzeiten

        String method;

        //	method = "product";
        //	method = "sum";
        //	method = "sqrt";
        method = "min";


        // *** Iteriert mehrmals, um nach Möglichkeit das Optimum zu finden
        for (int iterate = 0; iterate < 20; iterate++) {

            SemesterTimes timesLocal = SemOpti_Local.optimize(semOpti, SemOpti_Random.optimize(semOpti, 10000000, method), 0.05, 200000, method);



            // *** Prüfung, ob neue Lösung am besten ist

            double result = 0;
            double resultOld = 0;

            switch (method) {

                case "product":
                    result = semOpti.calcProduct(timesLocal);
                    resultOld = semOpti.calcProduct(times);
                    break;

                case "sum":
                    result = semOpti.calcSum(timesLocal);
                    resultOld = semOpti.calcSum(times);
                    break;

                case "sqrt":
                    result = semOpti.calcSqrt(timesLocal);
                    resultOld = semOpti.calcSqrt(times);
                    break;

                case "min":
                    result = semOpti.calcMin(timesLocal);
                    resultOld = semOpti.calcMin(times);
                    break;

                default:
                    System.err.println("Error: No valid optimization method!");
                    break;

            }

            // *** Berechnung, ob die neue Optimierung besser ist
            if ( result > resultOld) {
                times = timesLocal.clone();
            }

        }


        //	SemesterTimes timesOptiRandom = SemOpti_Random.optimize(sem2021WiSe, 10000000);

        //	SemesterTimes timesOptiLocal = SemOpti_Local.optimize(sem2021WiSe, timesOptiRandom, 0.02, 200000);

        // The json result data



        double stundensumme = 282.5;

        int courseNo = times.getCourseNumber();

        System.out.println();

        System.out.println("*************************");

        System.out.println();

        System.out.println("Optimierungsmethode: " + method);
        results.put("Optimierungsmethode", method);

        System.out.println();

        System.out.println("Semester: " + semOpti.getSemesterName());
        results.put("Semester", semOpti.getSemesterName());

        System.out.println();

        System.out.println("Zahl der Kurse: " + courseNo);
        results.put("Zahl der Kurse", courseNo);

        System.out.println();

        System.out.println("Stundensumme: " + stundensumme);
        results.put("Stundensumme", stundensumme);

        System.out.println();

        double res = 0;

        switch (method) {

            case "product":
                res = semOpti.calcProduct(times);
                break;

            case "sum":
                res = semOpti.calcSum(times);
                break;

            case "sqrt":
                res = semOpti.calcSqrt(times);
                break;

            case "min":
                res = semOpti.calcMin(times);
                break;

            default:
                System.err.println("Error: No valid optimization method!");
                break;

        }

        System.out.println("Optimaler Wert: " + res);
        results.put("Optimaler Wert", res);


        System.out.println();

        System.out.println("Optimale Zeiten nach lokaler Optimierung: ");

        JSONObject JSONcourses = new JSONObject();

        for ( int i = 0; i < courseNo; i++ ) {

            Integer CourseCount = i+1; //count number of Courses starting from 1

            JSONObject everycourse = new JSONObject();

            everycourse.put("Zeit Präsentation Stunden" , ( times.getTimePresentation(i) * stundensumme ));
            everycourse.put("Zeit Präsentation Anteil" , times.getTimePresentation(i));
            everycourse.put("Zeit Didaktik Stunden" , ( times.getTimeDidactic(i) * stundensumme ));
            everycourse.put("Zeit Didaktik Anteil" , times.getTimeDidactic(i));
            everycourse.put("Zeit Inhalt Stunden" , ( times.getTimeContent(i) * stundensumme ));
            everycourse.put("Zeit Inhalt Anteil" , times.getTimeContent(i));
            everycourse.put("Course name", semOpti.getCourseName(i));


            System.out.println("course number: " + (i+1));
            System.out.println(semOpti.getCourseName(i));

            System.out.println("Zeit Inhalt Anteil: " + times.getTimeContent(i) + "   Zeit Inhalt Stunden: " + ( times.getTimeContent(i) * stundensumme ) );

            System.out.println("Zeit Didaktik Anteil: " + times.getTimeDidactic(i) + "  Zeit Didaktik Stunden: " + ( times.getTimeDidactic(i) * stundensumme ) ) ;

            System.out.println("Zeit Präsentation Anteil: " + times.getTimePresentation(i) + "  Zeit Präsentation Stunden: " + ( times.getTimePresentation(i) * stundensumme ) );

            JSONcourses.put(CourseCount.toString(), everycourse);
        }

        results.put("courses", JSONcourses);



        System.out.println();

        System.out.println("*************************");

        System.out.println();


        return results.toString();
    }
}