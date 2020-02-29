package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Cameras extends SubsystemBase {
 // UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture();

  public Cameras() {
  }

  public void log(){
   // SmartDashboard.putNumber("Cam fps", camera1.getActualFPS());
  }

  @Override
  public void periodic() {
    log();
    // This method will be called once per scheduler run
    
  }
}
