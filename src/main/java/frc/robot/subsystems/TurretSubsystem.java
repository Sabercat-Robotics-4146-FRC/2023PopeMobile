package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {

  public TalonSRX rotMotor; //Turret rotation motor
  public DigitalInput leftLimitSwitch;
  public DigitalInput rightLimitSwitch;

  public boolean autoAimEnabled;

  boolean posativeRotationDirection = CanRotate(0.1);

  public TurretSubsystem() {
    autoAimEnabled = false;
    rotMotor = new TalonSRX(TurretConstants.ROTATION_MOTOR_ID);
    rotMotor.setNeutralMode(NeutralMode.Brake); 

    leftLimitSwitch = new DigitalInput(TurretConstants.LEFT_LIMIT_ID);
    rightLimitSwitch = new DigitalInput(TurretConstants.RIGHT_LIMIT_ID);
  }

  // To be used when the driver is manually rotating with triggers
  public void Manually_Rotate(double rightTrigger, double leftTrigger){
    double rot = 0;
    rot = leftTrigger - rightTrigger; //First term should be direction posative on motor and second should be negative on motor
    Rotate(rot);
  }

  // To be used by other systems to directly rotate a given percent output
  public void Rotate(double rot) {
    if (CanRotate(rot)) {
      rotMotor.set(ControlMode.PercentOutput, rot);
    } else {
      rotMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public void toggleAutomaticAim(boolean toggle){
    autoAimEnabled = toggle;
  }

  public void toggleAutomaticAim(){
    toggleAutomaticAim(!autoAimEnabled);
  }

  private boolean CanRotate(double rot){
    //if (leftLimitSwitch.get() && rot > 0.0){
      //return false;
    //}

    //if (rightLimitSwitch.get() && rot < 0.0) {
      //return false;
    //} //For testing, need to implement other limit switch when it exists

    return true;
  }

  // public void oscillate(){
  //   if(autoAimEnabled){
  //     if(posativeRotationDirection){
  //       if(!CanRotate(0.1)) posativeRotationDirection = !posativeRotationDirection;
  //       else{
  //         //rotate positive direction
  //       }
  //     }
  //     else{
  //       if(!CanRotate(-0.1)) posativeRotationDirection = !posativeRotationDirection;
  //       else{
  //         //rotate negative direction
  //       }
  //     }
  //   }
  // }

  @Override
  public void periodic() {

  }
}