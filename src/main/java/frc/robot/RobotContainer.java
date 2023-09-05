// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AimCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.RotateTurret;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.TurretSubsystem;

public class RobotContainer {

  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final TurretSubsystem turretSubsystem = new TurretSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();
  private final LimelightSubsystem aimingSubsystem = new LimelightSubsystem();
  private final PneumaticsSubsystem pneumaticsSubsystem = new PneumaticsSubsystem();
  
  public static CommandXboxController joystick = new CommandXboxController(0);

  public RobotContainer() {
    CommandScheduler.getInstance().registerSubsystem(driveSubsystem);
    CommandScheduler.getInstance().registerSubsystem(turretSubsystem);
    CommandScheduler.getInstance().registerSubsystem(intakeSubsystem);
    CommandScheduler.getInstance().registerSubsystem(flywheelSubsystem);
    CommandScheduler.getInstance().registerSubsystem(aimingSubsystem);
    
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, new ArcadeDrive(driveSubsystem, joystick::getLeftY, joystick::getRightX));
    CommandScheduler.getInstance().setDefaultCommand(turretSubsystem, new AimCommand(turretSubsystem /* joystick::getRightTriggerAxis, joystick::getLeftTriggerAxis) */));

    configureCommands();
  }

  public void configureCommands() {
    joystick.a().toggleOnTrue(Commands.runOnce(intakeSubsystem::toggleExtension));
    joystick.b().toggleOnTrue(Commands.runOnce(intakeSubsystem::toggleRotation));
    //joystick.y().toggleOnTrue(Commands.runOnce(flywheelSubsystem::runFlywheel));
    joystick.x().toggleOnTrue(Commands.runOnce(pneumaticsSubsystem::toggleCompressor));
    joystick.y().toggleOnTrue(Commands.runOnce(pneumaticsSubsystem::togglePiston));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}