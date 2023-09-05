package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public abstract class PIDReadout extends SubsystemBase {

  String tab; // Tab Name

  //Generic entries for each var
  public GenericEntry m_kP;
  public GenericEntry m_kI;
  public GenericEntry m_kD;
  public GenericEntry m_setpoint;
  
  public PIDReadout(String tabName) { //Put super(tabName) in constructor
    ShuffleboardInit(tabName);
    tab = tabName;
  }

  private void ShuffleboardInit(String tabName) {
    Shuffleboard.getTab(tabName).add("Initialized", true).withWidget(BuiltInWidgets.kBooleanBox);

    CameraServer.startAutomaticCapture();
  }

  public GenericEntry NewEntry(String name, WidgetType type, int defaultEntry, int... size) {
    int width = size.length > 0 ? size[0] : 1;
    int height = size.length > 1 ? size[1] : 1;
    return Shuffleboard.getTab(tab)
        .add(name, defaultEntry)
        .withWidget(type)
        .withSize(width, height)
        .getEntry();
  }

  public void PIDConfig() { //PID config goes in constructor, make a private function that updates local values with entries.GetDouble() and put in periodic
    ShuffleboardLayout pidLayout =
        Shuffleboard.getTab(tab).getLayout("PID Config", BuiltInLayouts.kList).withSize(1, 3);

    m_kP =
        pidLayout
            .add("kP", 40)
            .withWidget(BuiltInWidgets.kTextView)
            .withSize(1, 1)
            .getEntry();

    m_kI =
        pidLayout
            .add("kI", 0.1)
            .withWidget(BuiltInWidgets.kTextView)
            .withSize(1, 1)
            .getEntry();

    m_kD =
        pidLayout
            .add("kD", 1.1)
            .withWidget(BuiltInWidgets.kTextView)
            .withSize(1, 1)
            .getEntry();

    m_setpoint =
        pidLayout
            .add("setpoint", 6000)
            .withWidget(BuiltInWidgets.kTextView)
            .withSize(1, 1)
            .getEntry();
  }
}