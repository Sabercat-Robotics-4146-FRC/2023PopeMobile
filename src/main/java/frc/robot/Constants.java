// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int driverControllerPort = 0;
  }
  public static final class DriveConstants{
    public static final int RIGHT_FRONT_ID = 1;
    public static final int LEFT_FRONT_ID = 3;
    public static final int RIGHT_BACK_ID = 2;
    public static final int LEFT_BACK_ID = 4;
  }
  public static final class TurretConstants{
    public static final int ROTATION_MOTOR_ID = 8;

    public static final int LEFT_LIMIT_ID = 2;
    public static final int RIGHT_LIMIT_ID = 3;
  }
  public static final class FlywheelConstants{
    public static final int FLYWHEEL_MOTOR_ID = 12;
  }
  public static final class IntakeConstants{
    static public final int ARM_MOTOR_ID = 7;
    static public final int ROTATION_MOTOR_ID = 6;

    public static final int EXTENSION_LIMIT_ID = 1;
    public static final int RETRACTION_LIMIT_ID = 0;
  }
}
