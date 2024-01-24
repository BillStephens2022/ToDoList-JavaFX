package com.billstephens.todolist;

import com.billstephens.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ToDoListController {
    private List<TodoItem> todoItems;

    public void initialize() {
        TodoItem item1 = new TodoItem("Mail birthday card",
                "Buy a 30th birthday card for John",
                LocalDate.of(2024, Month.JANUARY, 24));
        TodoItem item2 = new TodoItem("Doctor's Appointment",
                "See Dr. Smith at 123 Main St. Bring paperwork.",
                LocalDate.of(2024, Month.FEBRUARY, 23));
        TodoItem item3 = new TodoItem("Finish design proposal for client",
                "I promised Mike I'd email website mockups by Friday JANUARY 26.",
                LocalDate.of(2024, Month.JANUARY, 26));
        TodoItem item4 = new TodoItem("Pickup Doug at the train station", "Doug's arriving on December 10 on the 5:00 train",
                LocalDate.of(2023, Month.DECEMBER, 10));
        TodoItem item5 = new TodoItem("Pick up dry cleaning", "The clothes should be ready by Wednesday",
                LocalDate.of(2024, Month.JANUARY, 31));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(item1);
        todoItems.add(item2);
        todoItems.add(item3);
        todoItems.add(item4);
        todoItems.add(item5);

    }

}