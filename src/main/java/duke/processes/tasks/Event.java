package duke.processes.tasks;

public class Event extends Task {
    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String toString() {
        return description;
    }

    public String getTaskType() {
        return "event";
    }

    public String getTaskID() {
        return "E";
    }

    public String getDate() {
        return date;
    }
}