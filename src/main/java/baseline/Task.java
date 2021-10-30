package baseline;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable{

    private static final long serialVersionUID = 1L;

    private LocalDate date;

    private String description;

    public Task(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }



    // this makes LocalEvents dispaly nicely in GUI
    @Override
    public String toString() {
        if(date == null){
            return "|Task: " + description;
        }
        else{
            return "Due Date: " + date + " |Task: " + description;
        }

    }
}
