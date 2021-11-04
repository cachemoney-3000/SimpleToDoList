package baseline;

import java.util.Objects;

public record Task(String date, String description){

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if (Objects.equals(date, "")) {
            return "|Task: " + description;
        } else {
            return "Due Date: " + date + " |Task: " + description;
        }

    }
}
