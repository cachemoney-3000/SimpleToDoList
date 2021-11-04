package baseline;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class DeleteList {

    public ObservableList<Task> deleteMain(ListView itemList, ObservableList<Task> item){
        itemList.getItems().clear();
        item.clear();

        return item;
    }

    public void deleteNewList(AnchorPane Pane, VBox listBox, Button button){
        Pane.getChildren().clear();
        listBox.getChildren().remove(button);
    }
}
