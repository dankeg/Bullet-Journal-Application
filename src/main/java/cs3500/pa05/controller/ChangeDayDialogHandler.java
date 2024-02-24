package cs3500.pa05.controller;

import cs3500.pa05.model.record.DayEnum;
import cs3500.pa05.view.SetDayDialog;
import cs3500.pa05.view.SetTaskMaxesDialog;
import java.util.Optional;
import javafx.event.Event;

/**
 * ChangeDayDialogHandler
 */
public class ChangeDayDialogHandler extends AbstractHandler {
  /**
   * handle method
   *
   * @param event the event which occurred
   */
  @Override
  public void handle(Event event) {
    SetDayDialog dayDialog = new SetDayDialog();
    Optional<DayEnum> result = dayDialog.showAndWait();;
    if (result.isPresent()) {
      DayEnum day = result.get();
      staticModel.setWeekStart(day);
      dayDialog.close();
    }
  }
}
