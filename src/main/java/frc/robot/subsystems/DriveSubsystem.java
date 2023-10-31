package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  public TalonSRX rightFront;
  public TalonSRX leftFront;
  public TalonSRX rightBack;
  public TalonSRX leftBack;

  public DriveSubsystem() {

    rightFront = new TalonSRX(DriveConstants.RIGHT_FRONT_ID);
    leftFront = new TalonSRX(DriveConstants.LEFT_FRONT_ID);
    leftBack = new TalonSRX(DriveConstants.LEFT_BACK_ID);
    rightBack = new TalonSRX(DriveConstants.RIGHT_BACK_ID);

    rightFront.configPeakCurrentLimit(20);
    rightFront.configContinuousCurrentLimit(15);

    rightBack.configPeakCurrentLimit(20);
    rightBack.configContinuousCurrentLimit(15);

    leftFront.configPeakCurrentLimit(20);
    leftFront.configContinuousCurrentLimit(15);

    leftBack.configPeakCurrentLimit(20);
    leftBack.configContinuousCurrentLimit(15);

    rightFront.setInverted(InvertType.InvertMotorOutput);
    rightBack.setInverted(InvertType.InvertMotorOutput);
    leftBack.set(ControlMode.Follower, 3);
    rightBack.set(ControlMode.Follower, 1);
    
    rightFront.setNeutralMode(NeutralMode.Coast);
    rightBack.setNeutralMode(NeutralMode.Coast);
    leftFront.setNeutralMode(NeutralMode.Coast);
    leftBack.setNeutralMode(NeutralMode.Coast);
  }

  // Forward/Back controlled by left stick, rotation controlled by right stick
  public void ArcadeDrive(double leftY, double rotation){  //LeftY = left y, rotation = right x of joystick
    rightFront.set(ControlMode.PercentOutput, (leftY + rotation));
    leftFront.set(ControlMode.PercentOutput, (leftY - rotation));
  }

  // Left side of wheels controlled by left stick, right side controlled by right stick
  public void TankDrive(double leftY, double rightY){
    rightFront.set(ControlMode.PercentOutput, rightY);
    leftFront.set(ControlMode.PercentOutput, leftY);
  }

  // Forward/Back controlled by right/left triggers, steering controlled with left stick
  public void VideoGameDrive(double rightTrigger, double leftTrigger, double leftX){
    rightFront.set(ControlMode.PercentOutput, rightTrigger - leftTrigger + leftX);
    leftFront.set(ControlMode.PercentOutput, rightTrigger - leftTrigger - leftX);
  }

  

}