package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    //יצירת משתנה לשמירת תגובה לבקשה ואתחול להתחלתי
    private static CloseableHttpClient client = HttpClients.createDefault();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            while(true) {
                System.out.println("What do you want to do?\n1 - Register\n2 - Get Tasks\n3 - Add Task\n4 - Set Task Done\n");
                int choice = (new Scanner(System.in)).nextInt();
                String id;
                String task;
                switch (choice) {
                    case 1:
                        System.out.println("Enter ID:");
                        id = scanner.nextLine();
                        register(id);
                        break;
                    case 2:
                        System.out.println("Enter ID:");
                        id = scanner.nextLine();
                        getTasks(id);
                        break;
                    case 3:
                        System.out.println("Enter ID:");
                        id = scanner.nextLine();
                        System.out.println("Enter the task: ");
                        task = scanner.nextLine();
                        addTask(id, task);
                        break;
                    case 4:
                        System.out.println("Enter ID:");
                        id = scanner.nextLine();
                        System.out.println("Enter the task: ");
                        task = scanner.nextLine();
                        setTaskDone(id, task);
                }
            }
        }

    }

    public static void register(String id) {
        try {
            URI uri = new URIBuilder("https://app.seker.live/fm1/register")
                    .setParameter("id", id)
                    .build();
            HttpPost request = new HttpPost(uri);
            CloseableHttpResponse response = client.execute(request);
            String myResponse = EntityUtils.toString(response.getEntity());
            //שורת מעבר מסטרינג לתצורה של רספונס
            Response responseObject = (Response)(new ObjectMapper()).readValue(myResponse, Response.class);
            if (responseObject.isSuccess()) {
                System.out.println("You have successfully registered");
            } else if (responseObject.getErrorCode() == 1003) {
                System.out.println("You are already in the system");
            }
        } catch (Exception e){
        throw new RuntimeException(e);
    }

        /*(URISyntaxException e) {
            throw new RuntimeException();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
         */
    }

    public static void getTasks(String id) {
        try {
            //שמירת הנתיב הנתון ושרשור גט טסקס
            //סט פרמטר מטרתו להוסיף את האיידי לבקשה שנשלחת - בלי זה הבקשה לא תזהה את האייידי
            URI uri = new URIBuilder("https://app.seker.live/fm1/get-tasks")
                    .setParameter("id", id)
                    .build();
            // שליחת בקשת אייפיאיי לכתובת למעלה
            HttpGet request = new HttpGet(uri);
            //שמירת התגובה של הבקשה
            CloseableHttpResponse response = client.execute(request);
            //חילוץ התגובה בעזרת המחלקות רספונס וטסקמודל
        String myResponse = EntityUtils.toString(response.getEntity());
        //שורה שמטרתה לקחת את מה שיש בסטרינג מיירספונס ולהפוך אותו למשהו ממחלקת רספונס שיצרנו
        Response responseObject = new ObjectMapper().readValue(myResponse, Response.class);
        //אם התשובה חיובית - הבוליאן
        if (responseObject.isSuccess()) {
            System.out.println("you have " + responseObject.getTasks().size() + " tasks");
            //מעבר על כל המערך של המשימות והדפסת הכותרת של המשימות
            for (int i = 0; i < responseObject.getTasks().size(); i++) {
                System.out.println((i + 1) + ": " + responseObject.getTasks().get(i).getTitle());
            }
        }
        else {
            if (responseObject.getErrorCode() == 1001){
                System.out.println("A user with id " +id + " has not been registered");
            }
        }
        System.out.println();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTask(String id,String text) {
        try {
            URI uri = new URIBuilder("https://app.seker.live/fm1/add-task")
                    .setParameter("id", id)
                    .setParameter("text", text)
                    .build();
            HttpPost request = new HttpPost(uri);
            CloseableHttpResponse response = client.execute(request);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setTaskDone(String id,String text){
        try {
            URI uri = (new URIBuilder("https://app.seker.live/fm1/set-task-done")).setParameter("id", id).setParameter("text", text).build();
            HttpPost request = new HttpPost(uri);
            CloseableHttpResponse response = client.execute(request);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}