package tf.lehroptimierung.controllers;

import tf.lehroptimierung.TimeOptaApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServiceController {

    @Autowired
    private TimeOptaApplication timeOptimizer;
    //controller methods

    @PostMapping("/optimize")
    public ResponseEntity<Object> optimize(@RequestBody String data){
        String result = timeOptimizer.optimize(data);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
