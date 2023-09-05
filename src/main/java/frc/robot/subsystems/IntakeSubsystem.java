package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {

  public TalonSRX armMotor; //Arm motor for intake
  public TalonSRX rotationMotor; //Rotation motor for intake

  public DigitalInput extensionLimitSwitch;
  public DigitalInput retractionLimitSwitch;

  public boolean extended = false;
  public boolean rotating = false;

  public IntakeSubsystem() {
    armMotor = new TalonSRX(IntakeConstants.ARM_MOTOR_ID);
    rotationMotor = new TalonSRX(IntakeConstants.ROTATION_MOTOR_ID);
    armMotor.setNeutralMode(NeutralMode.Brake); 

    extensionLimitSwitch = new DigitalInput(IntakeConstants.EXTENSION_LIMIT_ID);
    retractionLimitSwitch = new DigitalInput(IntakeConstants.RETRACTION_LIMIT_ID);
  }

  @Override
  public void periodic() {
    //Arm Extention
    if(extended && extensionLimitSwitch.get()) {
      armMotor.set(ControlMode.PercentOutput, 0.3);  
    } else if (!extended && retractionLimitSwitch.get()) {
      armMotor.set(ControlMode.PercentOutput, -.7);
    } else armMotor.set(ControlMode.PercentOutput, 0);

    //Rotation 
    if (rotating){
      rotationMotor.set(ControlMode.PercentOutput, 1); 
    }
    else{
      rotationMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void toggleExtension() {
    extended = !extended;
  }

  public void toggleRotation() {
    rotating = !rotating;
  }

}