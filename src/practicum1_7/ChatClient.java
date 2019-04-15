package practicum1_7;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient extends Application {
    Socket verbinding;
    OutputStream output;
    {
        try {
            verbinding = new Socket("localhost",8080);
            output = verbinding.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    PrintWriter writer = new PrintWriter(output,true);

    TextArea chatBox = new TextArea();
    TextField chatField = new TextField();
    Button submit = new Button();
    TextField chatName = new TextField();
    public void start(Stage primaryStage) {
        VBox messagearea = new VBox(40);
        messagearea.getChildren().addAll(chatBox);
        messagearea.setAlignment(Pos.TOP_CENTER);
        Label placeholderName = new Label("Name");

        Label placeholderMessage = new Label("Message");
        placeholderName.setPrefWidth(50);
        chatName.setPrefWidth(100);
        placeholderMessage.setPrefWidth(50);
        chatField.setPrefWidth(200);
        submit.setPrefWidth(50);
        submit.setText("Send");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                sendMessage();
            }
        });
        HBox lovebox = new HBox(placeholderName, chatName,placeholderMessage,chatField,submit);
        VBox chatbox = new VBox(10);
//        chatbox.getChildren().addAll(messagearea,placeholderName, chatName,placeholderMessage,chatField,submit);
        chatbox.setAlignment(Pos.BOTTOM_LEFT);
        chatbox.getChildren().addAll(messagearea,lovebox);
        FlowPane root = new FlowPane();

        root.getChildren().add(chatbox);
//        root.setHgap(100);
//        root.setVgap(100);

        root.setPadding(new Insets(10, 0, 0,10));
        Scene scene = new Scene(root, 525, 250);
        primaryStage.setTitle("");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    public void sendMessage(){
        if((!chatName.getText().trim().isEmpty()) && (!chatField.getText().trim().isEmpty()) ){
            chatName.setDisable(true);
            String chatname = chatName.getText();
            String message = chatField.getText();
//            System.out.println(chatname);
//            System.out.println(message);



            writer.println(chatname + ": " + message);

        }else{
            chatBox.appendText("Vul u gebruikernaam in\n");
        }
    }

}