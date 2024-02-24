package cs3500.pa05.view;

import cs3500.pa05.controller.ChooseDirectoryEventHandler;
import cs3500.pa05.controller.ChooseFileEventHandler;
import cs3500.pa05.controller.PrimaryController;
import cs3500.pa05.controller.SceneChangeEventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * SplashScreenView class
 */
public class SplashScreenView implements SceneView {
  private Stage stage;
  private PrimaryController primaryController;

  /**
   * constructor
   *
   * @param stage Stage
   * @param primaryController PrimaryController
   */
  public SplashScreenView(Stage stage, PrimaryController primaryController) {
    this.stage = stage;
    this.primaryController = primaryController;
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
    mainScreenContents.setStyle("-fx-padding: 10; "
        + "-fx-background-color: #fffce0; "
        + "-fx-background-image: url('https://media.istockphoto.com/id/519847559/"
        + "vector/lined-paper-from-a-notebook.jpg?s=612x612&w=0&k=20&c=PrlbBZFrU6Vfrre8"
        + "-sdmucKI3AGBZ8AwxVWIepxFD34='); "
        + "-fx-background-radius: 5; "
        + "-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.3), 10, 0, 0, 0); "
        + "-fx-background-size: cover; "
        + "-fx-background-position: left;");

    //main title
    Label title = new Label("Bullet Journal");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 35));
    String styleString = "-fx-background-color: #ffffff;\n"
        + "    -fx-text-fill: #000000;\n"
        + "    -fx-font-size: 14px;\n"
        + "    -fx-font-family: \"Arial\";\n"
        + "    -fx-padding: 10px 20px;\n"
        + "    -fx-border-radius: 5px;";

    //create new bujo button
    Button createBuJo = new Button("Create new");
    createBuJo.setOnAction(new ChooseDirectoryEventHandler(stage, primaryController));
    createBuJo.setStyle(styleString);

    //file selector button
    Button fileSelector = new Button("Load from file");
    fileSelector.setOnAction(new ChooseFileEventHandler(stage, primaryController));
    fileSelector.setStyle(styleString);

    mainVerticalContent.getChildren().addAll(title, createBuJo, fileSelector);
    mainScreenContents.getChildren().add(mainVerticalContent);

    return new Scene(mainScreenContents, 500, 300);
  }
}
