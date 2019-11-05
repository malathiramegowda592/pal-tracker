package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;


    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry){


        TimeEntry newtimeEntry = timeEntryRepository.create(timeEntry);
        ResponseEntity responseEntity = new ResponseEntity<>(newtimeEntry, HttpStatus.CREATED);
        return responseEntity;

    }

    @GetMapping("{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId){

        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);

        if(timeEntry!=null){
            return new ResponseEntity<>(timeEntry,HttpStatus.OK);
        }else{

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list(){

       List<TimeEntry> timeEntryList =  timeEntryRepository.list();
        return new ResponseEntity<>(timeEntryList,HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry entry){

        TimeEntry newtimeEntry = timeEntryRepository.update(id,entry);
        if (newtimeEntry != null) {
            return new ResponseEntity<>(newtimeEntry,HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){

        timeEntryRepository.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
