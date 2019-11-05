package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    HashMap<Long,TimeEntry> timeEntries = new HashMap<Long,TimeEntry>();

    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
      TimeEntry newTimeEntry =  new TimeEntry(id, timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());


        timeEntries.put(id,newTimeEntry);
        return newTimeEntry;

    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {

        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public void delete(Long id) {

        timeEntries.remove(id);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {

        if(timeEntries.get(id)==null){

            return null;
        }else{
            TimeEntry newTimeEntry = new TimeEntry(id,timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
            timeEntries.replace(id,newTimeEntry);
            return newTimeEntry;
        }

    }
}
