package frc.robot.commands;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TankDrive extends CommandBase {
  
  private final DriveSubsystem driveSubsystem;

  DoubleSupplier leftY;
  DoubleSupplier rightY;

  public TankDrive(DriveSubsystem subsystem, DoubleSupplier leftY, DoubleSupplier rightY) {
    this.driveSubsystem = subsystem;

    this.leftY = leftY;
    this.rightY = rightY;

    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    driveSubsystem.TankDrive(leftY.getAsDouble(), rightY.getAsDouble()); //LeftY = left y, right Y = right y of joystick
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.TankDrive(0.0, 0.0);
  }

}