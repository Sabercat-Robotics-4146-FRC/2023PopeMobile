package frc.robot.commands;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AimCommand extends CommandBase {
  
  private TurretSubsystem turretSubsystem;
  private VisionSubsystem limelightSubsystem;
  
  public PIDController pid;
  public double kP;
  public double kI;
  public double kD;

  public AimCommand(TurretSubsystem turret, VisionSubsystem limelight) {
    this.turretSubsystem = turret;
    this.limelightSubsystem = limelight;

    kP = 0.3;
    kI = 0;
    kD = 0;

    pid = new PIDController(kP, kI, kD);
    pid.enableContinuousInput(-0.5, 0.5);
    pid.setSetpoint(0);

    addRequirements(turretSubsystem, limelightSubsystem);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    if (limelightSubsystem.getSeesTarget()){
      double offset = limelightSubsystem.getHorizontalOffset();
      turretSubsystem.Rotate(pid.calculate(offset));
    } else {
      turretSubsystem.Rotate(0);
    }
  }

  @Override
  public void end(boolean interrupted) {
    turretSubsystem.Rotate(0);
  }
}