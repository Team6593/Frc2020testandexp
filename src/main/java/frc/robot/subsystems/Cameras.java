/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Cameras extends SubsystemBase {
  /**
   * Creates a new Cameras.
   */
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
