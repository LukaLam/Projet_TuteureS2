package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Menu Snake !");
        Group root = new Group();
        Scene scene = new Scene(root, 1280, 720);

        Instru mon_instru = new Instru();
        Button btn = new Button();

        Clavier mon_clavier = new Clavier(mon_instru);//on créé un objet clavier

        Image image = new Image("file:Background.png");

        ImageView iv = new ImageView();
         iv.setImage(image);
         iv.setFitWidth(1280);
         iv.setFitHeight(720);

        root.getChildren().add(iv);





        root.getChildren().add(mon_clavier);//on l'ajoute à notre groupe root

     /*   btn.setLayoutX(550);
        btn.setLayoutY(300);
        btn.setText("Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                System.out.println("Hello World");
            }
        });
        root.getChildren().add(btn); */





        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
