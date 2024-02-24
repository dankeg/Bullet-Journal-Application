package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * To represent a view that returns a scene to be placed on the main stage,
 * abstracting functionality from lower modules
 */
public interface SceneView {

  /**
   * To display this class' intended scene
   *
   * @return scene
   */
  Scene display();
}
