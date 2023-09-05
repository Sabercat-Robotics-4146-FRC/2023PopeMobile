package frc.robot.subsystems;

import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticsSubsystem extends SubsystemBase {
 
  public Compressor pcmCompressor;
  public DoubleSolenoid doublePCM;
  
  public boolean compressorIsToggled;
  public boolean pistonIsToggled;

  public PneumaticsSubsystem() {
    pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    doublePCM =  new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    compressorIsToggled = false;
    pistonIsToggled = false;
  }

  public void toggleCompressor(){
    if(!compressorIsToggled){
      pcmCompressor.enableDigital();
      compressorIsToggled = !compressorIsToggled;
    }
    else if (compressorIsToggled){
      pcmCompressor.disable();
      compressorIsToggled = !compressorIsToggled;
    }
  }

  public void togglePiston(){
    if(!pistonIsToggled){
      doublePCM.set(Value.kForward);
      pistonIsToggled = !pistonIsToggled;
    }
    else if (pistonIsToggled){
      doublePCM.set(Value.kReverse);
      pistonIsToggled = !pistonIsToggled;
    }
  }



}