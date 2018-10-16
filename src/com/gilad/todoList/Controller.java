package com.gilad.todoList;

import com.gilad.todoList.datamodel.TodoIteam;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private List<TodoIteam> todoIteams;
    @FXML
    private ListView todoListView;




    public void initialize(){
        TodoIteam iteam1=new TodoIteam("Gilad birthday card","But a 30th birthday card for john",
                LocalDate.of(2018, Month.OCTOBER,16));
        TodoIteam iteam2=new TodoIteam("Gilad Doctor Appointment","See Dr.Smith at 123 MAin Street",
                LocalDate.of(2018, Month.MAY,23));
        TodoIteam iteam3=new TodoIteam("Finish design proposal for client ","I promised it to myself until the Friday 22nd April",
                LocalDate.of(2018, Month.APRIL,22));
        TodoIteam iteam4=new TodoIteam("Pickup Gilad at train station ","Gilad's arriving on March 23 on the 5:00 train",
                LocalDate.of(2018, Month.MARCH,23));
        TodoIteam iteam5=new TodoIteam("Pick up dry cleaning","The clothes should be ready by Wednesday",
                LocalDate.of(2018, Month.APRIL,20));

        todoIteams=new ArrayList<TodoIteam>();
        todoIteams.add(iteam1);
        todoIteams.add(iteam2);
        todoIteams.add(iteam3);
        todoIteams.add(iteam4);
        todoIteams.add(iteam5);

        todoListView.getItems().setAll(todoIteams);
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }


}
