package cs3500.pa05.controller;

import cs3500.pa05.model.PrimaryModel;

/**
 * ModelHolder class
 */
public abstract class ModelHolder {

  /**
   * Holds the current PrimaryModel of the Bujo application
   */
  protected static PrimaryModel staticModel;

  /**
   * setter for Model
   *
   * @param model PrimaryModel
   */
  public static void setModel(PrimaryModel model) {
    staticModel = model;
  }
}
