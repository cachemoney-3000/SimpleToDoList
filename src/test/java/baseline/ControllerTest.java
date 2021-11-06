/*
 * UCF COP3330 Fall 2021 Application Assignment 1 Solution
 * Copyright 2021 Joshua Samontanez
 */
package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ControllerTest {

    @Test
    void addItem() {
        // Adding one item into the list
        BottomBarFunctions b = new BottomBarFunctions();

        String date = "01/01/2020";
        String description = "Test";

        Task addedItem = b.addItemHelper(date, description);
        String actual = addedItem.toString();
        String expected = "Task: Test   |Due Date: 01/01/2020";

        assertEquals(expected, actual);
    }

    @Test
    void addItemNoDate() {
        // Adding one item into the list with no date
        BottomBarFunctions b = new BottomBarFunctions();

        String date = " ";
        String description = "Test";

        Task addedItem = b.addItemHelper(date, description);
        String actual = addedItem.toString();
        String expected = "Task: Test";

        assertEquals(expected, actual);
    }

    @Test
    void addItemNoDescription() {
        // This will not add item into the list since the description is empty
        BottomBarFunctions b = new BottomBarFunctions();

        String date = "2020/01/01";
        String description = " ";

        Task addedItem = b.addItemHelper(date, description);
        String actual = addedItem.toString();

        assertNull(actual);
    }

    @Test
    void replaceItem(){
        BottomBarFunctions b = new BottomBarFunctions();
        ObservableList<Task> item = FXCollections.observableArrayList();

        /* Before replacing an item from the list:
        Task: Test1   |Due Date: 2020/01/01
        Task: Test2   |Due Date: 2021/01/10
        Task: Test3                          (replace this)
         */

        item.add(new Task("2020/01/01", "Test1"));
        item.add(new Task("2021/01/10", "Test2"));
        item.add(new Task(" ", "Test3"));

        // Replace the 3rd item with:
        String date = "2022/01/01";
        String description = "Test66";
        int index = 2;
        Task replace = b.addItemHelper(date, description);

        item = b.replaceItemHelper(index, item, replace);

        StringBuilder sb = new StringBuilder();

        for (Task task : item) {
            sb.append(task);
            sb.append("\n");
        }

        String actual = sb.toString();
        String expected = """
                Task: Test1   |Due Date: 2020/01/01
                Task: Test2   |Due Date: 2021/01/10
                Task: Test66   |Due Date: 2022/01/01
                """;

        assertEquals(expected, actual);
    }

    @Test
    void removeItem() {
        BottomBarFunctions b = new BottomBarFunctions();
        ObservableList<Task> item = FXCollections.observableArrayList();

        /* Before removing an item from the list:
        Task: Test1   |Due Date: 2020/01/01
        Task: Test2   |Due Date: 2021/01/01 (remove this)
        Task: Test3   |Due Date: 2022/01/01
         */

        item.add(new Task("2020/01/01", "Test1"));
        item.add(new Task("2021/01/01", "Test2"));
        item.add(new Task("2022/01/01", "Test3"));
        // Index we want to remove
        int index = 1;

        item = b.removeItem(index, item);

        StringBuilder sb = new StringBuilder();

        for (Task task : item) {
            sb.append(task);
            sb.append("\n");
        }

        String actual = sb.toString();
        String expected = """
                Task: Test1   |Due Date: 2020/01/01
                Task: Test3   |Due Date: 2022/01/01
                """;

        assertEquals(expected, actual);
    }

    @Test
    void clearList(){
        TopBarFunctions t = new TopBarFunctions();
        ObservableList<Task> item = FXCollections.observableArrayList();

        /* Before clearing all items from the list:
        Task: Test1   |Due Date: 2020/01/01
        Task: Test2   |Due Date: 2021/01/01
        Task: Test3   |Due Date: 2022/01/01
         */

        item.add(new Task("2020/01/01", "Test1"));
        item.add(new Task("2021/01/01", "Test2"));
        item.add(new Task("2022/01/01", "Test3"));

        item = t.clearList(item);

        StringBuilder sb = new StringBuilder();

        for (Task task : item) {
            sb.append(task);
            sb.append("\n");
        }

        String actual = sb.toString();
        // It is now empty
        String expected = "";

        assertEquals(expected, actual);
    }


    @Test
    void saveTest() {
        File file = new File();
        java.io.File fileName = new java.io.File("docs/test.txt");
        ObservableList<Task> item = FXCollections.observableArrayList();
        item.add(new Task("2020/01/25", "test"));
        item.add(new Task("2012/05/05", "test Task"));
        item.add(new Task("2060/12/25", "testing"));

        String actual = file.saveToText(fileName, item);
        String expected = """
                test/2020/01/25
                test Task/2012/05/05
                testing/2060/12/25
                """;

        assertEquals(expected, actual);
    }

    @Test
    void loadTest() {
        File file = new File();

        List<Task> tasks = file.loadText("docs/test.txt");
        StringBuilder sb = new StringBuilder();

        for(Task t: tasks){
            sb.append(t);
            sb.append("\n");
        }

        String actual = sb.toString();
        String expected = """
                Task: test   |Due Date: 2020
                Task: test Task   |Due Date: 2012
                Task: testing   |Due Date: 2060
                """;

        assertEquals(expected, actual);
    }
}