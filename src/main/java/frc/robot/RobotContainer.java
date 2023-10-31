// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.TankDrive;
import frc.robot.commands.TurretHandler;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;

public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
  private final VisionSubsystem aimingSubsystem = new VisionSubsystem();
  private final PneumaticsSubsystem pneumaticsSubsystem = new PneumaticsSubsystem();

  
  public static CommandXboxController joystick = new CommandXboxController(0);

  public RobotContainer() {
    CommandScheduler.getInstance().registerSubsystem(driveSubsystem);
    CommandScheduler.getInstance().registerSubsystem(turretSubsystem);
    CommandScheduler.getInstance().registerSubsystem(intakeSubsystem);
    CommandScheduler.getInstance().registerSubsystem(flywheelSubsystem);
    CommandScheduler.getInstance().registerSubsystem(aimingSubsystem);
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, new ArcadeDrive(driveSubsystem, joystick::getLeftY, joystick::getRightX));
    CommandScheduler.getInstance().setDefaultCommand(turretSubsystem, new TurretHandler(turretSubsystem, joystick::getRightTriggerAxis, joystick::getLeftTriggerAxis, aimingSubsystem));

    configureCommands();
  }

  public void configureCommands() {
    joystick.a().toggleOnTrue(Commands.runOnce(intakeSubsystem::toggleExtension));
    joystick.b().toggleOnTrue(Commands.runOnce(intakeSubsystem::toggleRotation));
    joystick.y().toggleOnTrue(Commands.runOnce(flywheelSubsystem::toggleFlywheel));
    joystick.leftBumper().toggleOnTrue(Commands.runOnce(pneumaticsSubsystem::toggleCompressor));
    joystick.rightTrigger().toggleOnTrue(Commands.runOnce(pneumaticsSubsystem::togglePiston));
    joystick.x().toggleOnTrue(Commands.runOnce(turretSubsystem::toggleAutomaticAim));
  }

  /*  A = extend arm, 
      B = rotate intake, 
      X = toggle auto aim, 
      Y = flywheel, 
      LBumper = compressor toggle, 
      RTrigger = piston toggle */

  public Command getAutonomousCommand() {
    return null;
  }
}