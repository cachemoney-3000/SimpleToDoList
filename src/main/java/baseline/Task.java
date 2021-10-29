package baseline;

import javafx.scene.Node;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate date;

    private String description;

    public Task(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    //public String getDate() {
        //return date;
    //}

    public String getDescription() {
        return description;
    }



    // this makes LocalEvents dispaly nicely in GUI
    @Override
    public String toString() {
        return "At: " + date + description;
    }

}
