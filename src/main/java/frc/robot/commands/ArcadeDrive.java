package frc.robot.commands;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDrive extends CommandBase {
  
  private final DriveSubsystem driveSubsystem;

  DoubleSupplier leftY;
  DoubleSupplier rotation;

  public ArcadeDrive(DriveSubsystem subsystem, DoubleSupplier leftY, DoubleSupplier rotation) {
    this.driveSubsystem = subsystem;

    this.leftY = leftY;
    this.rotation = rotation;

    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    driveSubsystem.ArcadeDrive(leftY.getAsDouble(), rotation.getAsDouble()); //LeftY = left y, rotation = right x of joystick
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.ArcadeDrive(0.0, 0.0);
  }

}