package org.example;


//בניית מחלקה לחילוץ פרמטר מסויים שאנחנו רוצים
public class TaskModel {
    //כדי לדעת איזה שדות נכנס לגיסון פרומטר על מנת לסדר את התוצאה של הבקשה ולראות טוב יותר את השדות
    private String title;
    private String date;
    private Boolean done;

    //הוספת גטרים וסטרים

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
