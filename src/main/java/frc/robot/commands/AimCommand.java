package frc.robot.commands;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TurretSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AimCommand extends CommandBase {
  
  private TurretSubsystem turretSubsystem;
  private LimelightSubsystem limelightSubsystem;

  public AimCommand(TurretSubsystem turretSubsystem) {
    this.turretSubsystem = turretSubsystem;



    addRequirements(turretSubsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    //turretSubsystem.lockOn(limelightSubsystem.getLimelightX());
  }

  @Override
  public void end(boolean interrupted) {
  }

}