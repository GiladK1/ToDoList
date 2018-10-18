package com.gilad.todoList;

import com.gilad.todoList.datamodel.TodoData;
import com.gilad.todoList.datamodel.TodoIteam;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class DialogController {
    @FXML
    public TextField shortDescriptionFiled;
    @FXML
    private TextArea detailArea;
    @FXML
    private DatePicker deadlinePicker;

    public TodoIteam processResults(){
        String shortDescription = shortDescriptionFiled.getText().trim();
        String details = detailArea.getText().trim();
        LocalDate deadLineVale = deadlinePicker.getValue();

        TodoIteam newItem= new TodoIteam(shortDescription,details,deadLineVale);
        TodoData.getInstance().addTodoItem(newItem);
        return newItem;

    }

}
