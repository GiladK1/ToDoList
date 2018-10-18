package com.gilad.todoList;

import com.gilad.todoList.datamodel.TodoData;
import com.gilad.todoList.datamodel.TodoIteam;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<TodoIteam> todoIteams;
    @FXML
    private ListView<TodoIteam> todoListView;
    @FXML
    private TextArea iteamDeatailsTextArea;
    @FXML
    private Label deadLineLabel;
    @FXML
    private BorderPane mainBorderPane;



    public void initialize(){
        /*TodoIteam iteam1=new TodoIteam("Gilad birthday card","But a 30th birthday card for john",
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
        TodoData.getInstance().setTodoIteams(todoIteams);
*/




        todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoIteam>() {
            @Override
            public void changed(ObservableValue<? extends TodoIteam> observable, TodoIteam oldValue, TodoIteam newValue) {
                if(newValue != null ){
                    TodoIteam item=todoListView.getSelectionModel().getSelectedItem();
                    iteamDeatailsTextArea.setText(item.getDetails());
                    DateTimeFormatter df = DateTimeFormatter.ofPattern( "d/M/yy ");//"MMM d,yyy");
                    deadLineLabel.setText(df.format(item.getDeadline()));
                }
            }
        });

        todoListView.getItems().setAll(TodoData.getInstance().getTodoIteams());
        todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListView.getSelectionModel().selectFirst();

    }

    @FXML
    public void  showNewItemDialog(){
      Dialog<ButtonType> dialog =new Dialog<>();
      dialog.initOwner(mainBorderPane.getScene().getWindow());
      dialog.setTitle("Add new TodoItem");
      dialog.setHeaderText("Use this dialog to create a new Todo item");
      FXMLLoader fxmlLoader = new FXMLLoader();
      fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
      try{
        dialog.getDialogPane().setContent(fxmlLoader.load());
      }catch (IOException e){e.printStackTrace();return;}

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add((ButtonType.CANCEL));

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            DialogController controller=fxmlLoader.getController();
            TodoIteam newItem = controller.processResults();
            todoListView.getItems().setAll(TodoData.getInstance().getTodoIteams());
            todoListView.getSelectionModel().select(newItem);
            System.out.println("OK");
        }
        else {
            System.out.println("CANCEL");
        }
    }

    @FXML
    public void handleClickListView(){
        TodoIteam iteam =  todoListView.getSelectionModel().getSelectedItem();
        iteamDeatailsTextArea.setText(iteam.getDetails());
        deadLineLabel.setText(iteam.getDeadline().toString());

    }

}
