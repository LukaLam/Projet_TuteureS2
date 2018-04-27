import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Test extends Application {
    public static void main(String[] args){
        Application.launch(Test.class, args);
    }

    //Appelée par la fonction launch
    //Stage c'est la fenêtre vide
    public void start(Stage primaryStage){
        primaryStage.setTitle("Hello World");
        //Groupe root contient tous les objets graphiques
        Group root = new Group();
        //Contient groupe root
        Scene scene = new Scene(root, 300, 250, Color.LIGHTBLUE);
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello World !");
            }
        });
        root.getChildren().add(btn);
        //Ajoute la scène au stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

