package cs3500.pa05.view;

import cs3500.pa05.controller.ChooseDirectoryEventHandler;
import cs3500.pa05.controller.ChooseFileEventHandler;
import cs3500.pa05.controller.EventDialogHandler;
import cs3500.pa05.controller.EventMaxHandler;
import cs3500.pa05.controller.PrimaryController;
import cs3500.pa05.controller.SaveEventHandler;
import cs3500.pa05.controller.TaskDialogHandler;
import cs3500.pa05.controller.TaskMaxDialogHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * TopBar class
 */
public class TopBar extends MenuBar {

  /**
   * TopBar constuctor
   *
   * @param controller PrimaryController
   */
  public TopBar(PrimaryController controller) {
    //generates the menu bar used in the application
    MenuItem saveMenu = new Menu("Save");
    MenuItem openMenu = new Menu("Open");
    MenuItem newWeekMenu = new Menu("New Week");
    MenuItem addTaskMenu = new Menu("Add UserTask");
    MenuItem addEventMenu = new Menu("Add UserEvent");
    MenuItem setTaskMaxMenu = new Menu("Set UserTask Max");
    MenuItem setEventMaxMenu = new Menu("Set UserEvent Max");
    Menu menu = new Menu("Commands");

    menu.getItems().addAll(saveMenu, newWeekMenu, openMenu,
        addTaskMenu, addEventMenu, setTaskMaxMenu, setEventMaxMenu);
    this.getMenus().add(menu);

    saveMenu.setOnAction(new SaveEventHandler());
    newWeekMenu.setOnAction(new ChooseDirectoryEventHandler(controller.getStage(), controller));
    openMenu.setOnAction(new ChooseFileEventHandler(controller.getStage(), controller));
    addTaskMenu.setOnAction(new TaskDialogHandler());
    addEventMenu.setOnAction(new EventDialogHandler());
    setEventMaxMenu.setOnAction(new EventMaxHandler());
    setTaskMaxMenu.setOnAction(new TaskMaxDialogHandler());
    saveMenu.setOnAction(new SaveEventHandler());

    String styleString = "-fx-background-color: rgba(220,206,228,255);\n"
        + "    -fx-text-fill: #000000;\n"
        + "    -fx-font-size: 12px;\n"
        + "    -fx-font-family: \"Arial\";\n"
        + "    -fx-padding: 5px 5px;\n"
        + "    -fx-border-radius: 300px;";

    String styleString2 = "-fx-background-color: rgba(220,206,228,255);\n"
        + "    -fx-text-fill: #000000;\n"
        + "    -fx-font-size: 12px;\n"
        + "    -fx-font-family: \"Arial\";\n"
        + "    -fx-padding: 5px 5px;\n"
        + "    -fx-border-color: rgb(14,1,1);\n"
        + "    -fx-border-radius: 300px;";

    this.setStyle(styleString);
    menu.setStyle(styleString2);

  }
}
