package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {

  public TalonSRX rotMotor; //Turret rotation motor
  public Boolean rotationEnabled = true; //Redundant at this point
  public DigitalInput leftLimitSwitch;
  public DigitalInput rightLimitSwitch;

  public PIDController pid;
  public double kP;
  public double kI;
  public double kD;

  private boolean autoAimEnabled = false;

  boolean flag = false;

  boolean posativeRotationDirection = CanRotate(0.1);

  public TurretSubsystem() {
    rotMotor = new TalonSRX(TurretConstants.ROTATION_MOTOR_ID);
    rotMotor.setNeutralMode(NeutralMode.Brake); 

    leftLimitSwitch = new DigitalInput(TurretConstants.LEFT_LIMIT_ID);
    rightLimitSwitch = new DigitalInput(TurretConstants.RIGHT_LIMIT_ID);

    pid = new PIDController(kP, kI, kD);
    pid.enableContinuousInput(-10000, 10000);
  }

  public void Manually_Rotate(double rightTrigger, double leftTrigger){
    double rot = 0;
    rot = leftTrigger - rightTrigger; //First term should be direction posative on motor and second should be negative on motor
    Rotate(rot);
  }

  private void Rotate(double rot) {
    if (CanRotate(rot) && rotationEnabled) {
      rotMotor.set(ControlMode.PercentOutput, rot);
    } else {
      rotMotor.set(ControlMode.PercentOutput, 0);
    }
  }

  public boolean CanRotate(double rot){
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

  public void lockOn(double x){
    if (true){
      System.out.println(pid.calculate(x, 0));
    }
  }

  // public void search(boolean bool) {
  //   if(leftLimitSwitch.get() && flag) flag = false;
  //   if(bool) Rotate(flag ? .5 : -.5);
  //   else Rotate(0);
  // }

  @Override
  public void periodic() {

  }
}