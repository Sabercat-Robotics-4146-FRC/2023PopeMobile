package frc.robot.commands;
import frc.robot.subsystems.TurretSubsystem;

import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateTurret extends CommandBase {
  
  private final TurretSubsystem turretSubsystem;

  DoubleSupplier rightTrigger;
  DoubleSupplier leftTrigger;

  public RotateTurret(TurretSubsystem subsystem, DoubleSupplier rightTrigger, DoubleSupplier leftTrigger) {
    this.turretSubsystem = subsystem;

    this.rightTrigger = rightTrigger;
    this.leftTrigger = leftTrigger;

    addRequirements(subsystem);
  }

  @Override
  public void execute() {
    turretSubsystem.Manually_Rotate(rightTrigger.getAsDouble(), leftTrigger.getAsDouble()); 
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.Manually_Rotate(0.0, 0.0);
  }

}