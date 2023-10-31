package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PIDReadout;
import frc.robot.Constants.FlywheelConstants;

public class FlywheelSubsystem extends PIDReadout {

  public TalonSRX flywheelMotor; // Flywheel Rotation Motor

  public boolean flywheelEnabled = false;

  public PIDController pid;
  public double kP = 40;
  public double kI = .1;
  public double kD = 1.1;
  public double setpoint;

  public FlywheelSubsystem() {
    super("flywheel");
    PIDConfig();

    flywheelMotor = new TalonSRX(FlywheelConstants.FLYWHEEL_MOTOR_ID);
    flywheelMotor.setNeutralMode(NeutralMode.Coast); 
    flywheelMotor.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.QuadEncoder, 0, 0);
  
    setpoint = 6000;
    pid = new PIDController(kP, kI, kD);
    pid.enableContinuousInput(-10000, 10000);
  }

  public void toggleFlywheel(boolean toggle) {
    flywheelEnabled = toggle;
  }

  public void toggleFlywheel(){
    toggleFlywheel(!flywheelEnabled);
  }
  
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Flywheel Velocity", flywheelMotor.getSelectedSensorVelocity());
    updatePID();

    if(flywheelEnabled) flywheelMotor.set(ControlMode.Velocity, -pid.calculate(flywheelMotor.getSelectedSensorVelocity(), 6000)); 
    else flywheelMotor.set(ControlMode.PercentOutput, 0);
  }

  // Updates values based upon Shuffleboard inputs
  public void updatePID(){
    kP = m_kP.getDouble(40);
    kI = m_kI.getDouble(0.1);
    kD = m_kD.getDouble(1.1);
    setpoint = m_setpoint.getDouble(6000);
  }
}