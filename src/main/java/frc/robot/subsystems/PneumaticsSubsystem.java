package frc.robot.subsystems;

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

  public void toggleCompressor(boolean toggle){
    compressorIsToggled = toggle;
    if(!compressorIsToggled){
      pcmCompressor.disable();
    } else {
      pcmCompressor.enableDigital();
    }
  }

  public void toggleCompressor() {
    toggleCompressor(!compressorIsToggled);
  }

  public void togglePiston(boolean toggle){
    pistonIsToggled = toggle;
    if(!pistonIsToggled){
      doublePCM.set(Value.kReverse);
    } else {
      doublePCM.set(Value.kForward);
    }
  }

  public void togglePiston(){
    togglePiston(!pistonIsToggled);
  }



}