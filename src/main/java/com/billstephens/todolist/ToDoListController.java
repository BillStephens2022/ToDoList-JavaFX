package com.billstephens.todolist;

import com.billstephens.todolist.datamodel.TodoData;
import com.billstephens.todolist.datamodel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoListController {
    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea itemDetailsTextArea;

    @FXML
    private Label deadlineLabel;

    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
//        TodoItem item1 = new TodoItem("Mail birthday card",
//                "Buy a 30th birthday card for John",
//                LocalDate.of(2024, Month.JANUARY, 24));
//        TodoItem item2 = new TodoItem("Doctor's Appointment",
//                "See Dr. Smith at 123 Main St. Bring paperwork.",
//                LocalDate.of(2024, Month.FEBRUARY, 23));
//        TodoItem item3 = new TodoItem("Finish design proposal for client",
//                "I promised Mike I'd email website mockups by Friday JANUARY 26.",
//                LocalDate.of(2024, Month.JANUARY, 26));
//        TodoItem item4 = new TodoItem("Pickup Doug at the train station", "Doug's arriving on December 10 on the 5:00 train",
//                LocalDate.of(2023, Month.DECEMBER, 10));
//        TodoItem item5 = new TodoItem("Pick up dry cleaning", "The clothes should be ready by Wednesday",
//                LocalDate.of(2024, Month.JANUARY, 31));
//
//        todoItems = new ArrayList<TodoItem>();
//        todoItems.add(item1);
//        todoItems.add(item2);
//        todoItems.add(item3);
//        todoItems.add(item4);
//        todoItems.add(item5);
//
//        TodoData.getInstance().setTodoItems(todoItems);

        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem oldValue, TodoItem newValue) {
                if(newValue != null) {
                    TodoItem item = todoListView.getSelectionModel().getSelectedItem();
                    itemDetailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                    deadlineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

    }

    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Use this dialog to create a new todo item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load the dialog");
            e.printStackTrace();
            return;
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();
            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            todoListView.getSelectionModel().select(newItem);
            System.out.println("OK pressed");
        } else {
            System.out.println("Cancel pressed");
        }
    }

    public void handleClickListView() {
        TodoItem item = todoListView.getSelectionModel().getSelectedItem();
//        System.out.println("The selected item is: " + item);
//        StringBuilder sb = new StringBuilder(item.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Due: ");
//        sb.append(item.getDeadline().toString());
//        itemDetailsTextArea.setText(sb.toString());
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }

}