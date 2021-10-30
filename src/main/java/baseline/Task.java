package baseline;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable{

    private static final long serialVersionUID = 1L;

    private String date;

    private String description;

    public Task(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }



    // this makes LocalEvents dispaly nicely in GUI
    @Override
    public String toString() {
        if(date == ""){
            return "|Task: " + description;
        }
        else{
            return "Due Date: " + date + " |Task: " + description;
        }

    }
}
