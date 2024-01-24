module com.billstephens.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.billstephens.todolist to javafx.fxml;
    exports com.billstephens.todolist;
}