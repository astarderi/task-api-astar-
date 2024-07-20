package org.example;

import java.util.List;

//hיצירת מחלקה לטובת כל השדות של הבקשה עצמה
public class Response {
    private boolean success;
    private Integer errorCode;
    private String extra;
    private List<TaskModel> tasks;

    //הוספת גטרים וסטרים


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public List<TaskModel> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskModel> tasks) {
        this.tasks = tasks;
    }
}
