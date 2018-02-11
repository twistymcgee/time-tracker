package net.moshr.timetracker.exceptions;

import lombok.Getter;
import lombok.Setter;
import net.moshr.timetracker.entities.WorkEntry;

public class ConcurrentActiveException extends Exception {

    @Getter @Setter
    private WorkEntry workEntry;

    public ConcurrentActiveException(String message, WorkEntry workEntry) {
        super(message);
        this.workEntry = workEntry;
    }


}
