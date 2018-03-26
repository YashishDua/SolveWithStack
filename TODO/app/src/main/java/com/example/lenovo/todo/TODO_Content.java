package com.example.lenovo.todo;

/**
 * Created by Lenovo on 23-06-2016.
 */
public class TODO_Content {
   private String TODO_Task_Name ;
   private String TODO_Task_Date ;

    public TODO_Content(String TODO_Task_Name, String TODO_Task_Date) {
        this.TODO_Task_Name = TODO_Task_Name;
        this.TODO_Task_Date = TODO_Task_Date;
    }

    public String getTODO_Task_Name() {
        return TODO_Task_Name;
    }

    public void setTODO_Task_Name(String TODO_Task_Name) {
        this.TODO_Task_Name = TODO_Task_Name;
    }

    public String getTODO_Task_Date() {
        return TODO_Task_Date;
    }

    public void setTODO_Task_Date(String TODO_Task_Date) {
        this.TODO_Task_Date = TODO_Task_Date;
    }
}









