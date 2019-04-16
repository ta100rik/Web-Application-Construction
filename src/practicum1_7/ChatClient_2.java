package practicum1_7;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class ChatClient_2 extends Application {
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
        chatBox.setEditable(false);
        chatField.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    sendMessage();
                }
            }
        });
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
        MessageListener listener = new MessageListener(verbinding,chatBox);
        listener.start();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
    private class MessageListener extends Thread{
        Socket socket;
        TextArea chatscreen;
        public MessageListener(Socket soc,TextArea textscr){
            socket = soc;
            chatscreen = textscr;
        }
        public void run(){
            while(true){
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                    String resp = in.readLine();
                    this.chatscreen.appendText(resp + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public void sendMessage(){
        if((!chatName.getText().trim().isEmpty()) && (!chatField.getText().trim().isEmpty()) ){
            chatName.setDisable(true);

            String chatname = chatName.getText();
            String message = chatField.getText();
//            System.out.println(chatname);
//            System.out.println(message);
            writer.println(chatname + ": " + message);
            chatField.setText("");

        }else{
            chatBox.appendText("Vul u gebruikernaam in\n");
        }
    }

}