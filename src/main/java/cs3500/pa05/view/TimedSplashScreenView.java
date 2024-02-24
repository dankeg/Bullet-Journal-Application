package cs3500.pa05.view;

import cs3500.pa05.controller.PrimaryController;
import cs3500.pa05.controller.ScreenState;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * TimeSplashScreenView class
 */
public class TimedSplashScreenView implements SceneView {
  private PrimaryController controller;

  /**
   * constructor
   *
   * @param controller PrimaryController
   */
  public TimedSplashScreenView(PrimaryController controller) {
    this.controller = controller;
  }

  /**
   * display method
   *
   * @return Scene
   */
  public Scene display() {
    VBox mainVerticalContent = new VBox();
    HBox mainScreenContents = new HBox();
    mainVerticalContent.setSpacing(20);
    mainScreenContents.setAlignment(Pos.CENTER);
    mainVerticalContent.setAlignment(Pos.CENTER);
    mainScreenContents.setStyle("-fx-padding: 20; -fx-background-color: cornsilk");

    //main title
    Label title = new Label("Welcome to Bullet Journal!");
    Label subtitle = new Label("Click anywhere to begin :)");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 35));
    subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 25));
    mainVerticalContent.getChildren().addAll(title, subtitle);
    mainScreenContents.getChildren().add(mainVerticalContent);
    mainScreenContents.setStyle("-fx-padding: 10; "
        + "-fx-background-color: #fffce0; "
        + "-fx-background-image: url('https://media.istockphoto.com/id/519847559/"
        + "vector/lined-paper-from-a-notebook.jpg?s=612x612&w=0&k=20&c=PrlbBZFrU6Vfrre8"
        + "-sdmucKI3AGBZ8AwxVWIepxFD34='); "
        + "-fx-background-radius: 5; "
        + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.3), 10, 0, 0, 0); "
        + "-fx-background-size: cover; "
        + "-fx-background-position: left;");
    Scene scene = new Scene(mainScreenContents, 500, 300);

    scene.setOnMouseClicked(new EventHandler<>() {
      /**
       * handle method
       *
       * @param event the event which occurred
       */
      @Override
      public void handle(MouseEvent event) {
        controller.run(ScreenState.SPLASHSCREEN);
      }
    });

    return scene;
  }
}
