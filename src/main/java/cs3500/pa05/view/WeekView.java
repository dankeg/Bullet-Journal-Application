package cs3500.pa05.view;

import cs3500.pa05.controller.AbstractHandler;
import cs3500.pa05.controller.ChooseDirectoryEventHandler;
import cs3500.pa05.controller.ChooseFileEventHandler;
import cs3500.pa05.controller.EventDialogHandler;
import cs3500.pa05.controller.EventMaxHandler;
import cs3500.pa05.controller.ModelHolder;
import cs3500.pa05.controller.PrimaryController;
import cs3500.pa05.controller.SaveEventHandler;
import cs3500.pa05.controller.TaskDialogHandler;
import cs3500.pa05.controller.TaskMaxDialogHandler;
import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.model.record.UserEvent;
import cs3500.pa05.model.record.UserTask;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * WeekView class
 */
public class WeekView extends ModelHolder implements SceneView {
  private PrimaryController primaryController;

  /**
   * constructor
   *
   * @param primaryController PrimaryController
   */
  public WeekView(PrimaryController primaryController) {
    this.primaryController = primaryController;
  }

  /**
   * display method
   *
   * @return Scene
   */
  public Scene display() {
    //create main week view box
    VBox mainWeekViewBox = new VBox();
    mainWeekViewBox.setStyle("-fx-padding: 20; -fx-background-color: rgba(222,178,241,0.17); "
        + "-fx-background-image: url('https://png.pngtree.com/png-clipart/20220123"
        + "/original/pngtree-purple-floating-petals-png-image_7158115.png'); "
        + "-fx-background-repeat: repeat;");
    mainWeekViewBox.setSpacing(10);

    //generate a holder for the main view box, to create room for a menu bar
    VBox spaceForTopBar = new VBox();
    spaceForTopBar.getChildren().add(new TopBar(primaryController));

    //topBar
    Text title = new Text("Bullet Journal");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

    HBox topBar = new HBox();
    topBar.setSpacing(20);
    topBar.getChildren().addAll(title, new MenuButtons());

    //horizontal screen components
    HBox horiztonalMainComponents = new HBox();
    horiztonalMainComponents.setSpacing(20);

    //create list of days
    HBox daysContainer = new HBox();

    ListChangeListener<DayEnum> dayListener = change -> {
      daysContainer.getChildren().clear();
      for (DayEnum day : staticModel.getWeekStart()) {
        DayView newDay = new DayView(day);
        daysContainer.getChildren().add(newDay.getDayView());
      }
    };

    //lister to populate queue with list of Events
    staticModel.getWeekStart().addListener(dayListener);
    Platform.runLater(() -> dayListener.onChanged(null)); //trick list into populating
    daysContainer.setSpacing(10);

    // Create week overview and listeners to update the view
    HBox tasksOverview = new HBox();
    tasksOverview.setSpacing(12);
    HBox eventsOverview = new HBox();
    ListChangeListener<UserTask> taskListChangeListener = change -> {
      tasksOverview.getChildren().clear();
      Label overview = new Label("Overview: ");
      overview.setStyle("-fx-font-size: 20px; -fx-text-fill: #000000; -fx-font-weight: bold;");
      Label totalTasks = new Label("Total tasks: " + staticModel.getUserTasks().size());
      Label percentCompleted =
          new Label("Tasks completed: " + staticModel.calculatePercentCompleted() + " %");
      totalTasks.setStyle("-fx-font-size: 20px; "
          + "-fx-text-fill: #7B2F92; "
          + "-fx-font-weight: bold;");
      percentCompleted.setStyle("-fx-font-size: 20px; "
          + "-fx-text-fill: #7B2F92; "
          + "-fx-font-weight: bold;");
      tasksOverview.getChildren().addAll(overview, totalTasks, percentCompleted);
    };

    ListChangeListener<UserEvent> eventListChangeListener = change -> {
      eventsOverview.getChildren().clear();
      Label totalEvents = new Label("Total events: " + staticModel.getUserEvents().size());
      totalEvents.setStyle("-fx-font-size: 20px; "
          + "-fx-text-fill: #7B2F92; "
          + "-fx-font-weight: bold;");
      eventsOverview.getChildren().add(totalEvents);
    };

    //lister to populate queue with list of Events
    staticModel.getUserEvents().addListener(eventListChangeListener);
    Platform.runLater(() -> eventListChangeListener.onChanged(null));

    //lister to populate queue with list of Events
    staticModel.getUserTasks().addListener(taskListChangeListener);
    Platform.runLater(() -> taskListChangeListener.onChanged(null));

    // add week overview to the view
    HBox weekOverview = new HBox();
    weekOverview.getChildren().addAll(tasksOverview, eventsOverview);
    weekOverview.setSpacing(12);
    mainWeekViewBox.getChildren().add(weekOverview);

    //create and add side task queue
    TaskQueue taskQueue = new TaskQueue();
    horiztonalMainComponents.getChildren().addAll(daysContainer, taskQueue.getTaskQueue());

    //add components to the main view, and the holder
    mainWeekViewBox.getChildren().addAll(topBar, horiztonalMainComponents);
    spaceForTopBar.getChildren().add(mainWeekViewBox);

    Scene scene = new Scene(spaceForTopBar, 1200, 500);

    addKeyEvents(new KeyCodeCombination(KeyCode.E,
        KeyCombination.SHORTCUT_DOWN), new EventDialogHandler(), scene);
    addKeyEvents(new KeyCodeCombination(KeyCode.T,
        KeyCombination.SHORTCUT_DOWN), new TaskDialogHandler(), scene);
    addKeyEvents(new KeyCodeCombination(KeyCode.S,
        KeyCombination.SHORTCUT_DOWN), new SaveEventHandler(), scene);
    addKeyEvents(new KeyCodeCombination(KeyCode.O,
        KeyCombination.SHORTCUT_DOWN),
        new ChooseFileEventHandler(primaryController.getStage(), this.primaryController), scene);
    addKeyEvents(new KeyCodeCombination(KeyCode.N,
        KeyCombination.SHORTCUT_DOWN),
        new ChooseDirectoryEventHandler(primaryController.getStage(),
            this.primaryController), scene);
    addKeyEvents(new KeyCodeCombination(KeyCode.L,
        KeyCombination.SHORTCUT_DOWN), new EventMaxHandler(), scene);
    addKeyEvents(new KeyCodeCombination(KeyCode.P,
        KeyCombination.SHORTCUT_DOWN), new TaskMaxDialogHandler(), scene);

    return scene;
  }

  /**
   * addKeyEvent method
   *
   * @param combo KeyCombination
   * @param handler AbstractHandler
   * @param scene Scene
   */
  private void addKeyEvents(KeyCombination combo, AbstractHandler handler, Scene scene) {
    scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if (combo.match(event)) {
          handler.handle(event);
        }
      }
    });
  }
}
