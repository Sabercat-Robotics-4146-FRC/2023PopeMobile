package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry tid = table.getEntry("tid");
    NetworkTableEntry targetpose_robotspace = table.getEntry("targetpose_robotspace");

  public double[] defaultArray;

  public LimelightSubsystem() {
    //SmartDashboard.putNumber("April Tag Id", getAprilTagID());

  }

  @Override
  public void periodic() {
    System.out.println(getSeesTarget());
    SmartDashboard.putNumber("April Tag Id", getAprilTagID());
  }

  public double getHorizontalOffset(){
    return tx.getDouble(0.0);
  }

  public double getVerticalOffset(){
    return ty.getDouble(0.0);
  }

  public boolean getSeesTarget(){
    return tv.getBoolean(false);
  }

  public double getTargetArea(){
    return ta.getDouble(0.0);
  }

  public double getAprilTagID (){
    return tid.getInteger(0);
  }

  public double[] getAprilTagTransform(){ // Returns x, y, z, roll, pitch, yaw
    return targetpose_robotspace.getDoubleArray(new double[6]);
  }

}