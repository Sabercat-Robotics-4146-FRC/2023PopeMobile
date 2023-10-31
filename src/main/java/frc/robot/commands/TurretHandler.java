package frc.robot.commands;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TurretHandler extends CommandBase {
  
  private final TurretSubsystem turretSubsystem;
  private final VisionSubsystem limelightSubsystem;

  DoubleSupplier rightTrigger;
  DoubleSupplier leftTrigger;

  public TurretHandler(TurretSubsystem tSubsystem, DoubleSupplier rTrigger, DoubleSupplier lTrigger, VisionSubsystem vSubsystem) {
    this.turretSubsystem = tSubsystem;
    this.limelightSubsystem = vSubsystem;

    this.rightTrigger = rTrigger;
    this.leftTrigger = lTrigger;
    addRequirements(tSubsystem);
  }

  // Turret Handler is set to the default command so that it is run periodically
  // This system allows you to toggle the "default command" between Automaic aiming and manual aiming

  @Override
  public void execute() {
    if(turretSubsystem.autoAimEnabled){
      new AimCommand(turretSubsystem, limelightSubsystem).execute();
    } else {
      new RotateTurret(turretSubsystem, rightTrigger, leftTrigger).execute();
    }
  }

  @Override
  public void end(boolean interrupted) {

  }

}