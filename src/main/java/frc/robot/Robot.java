package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.limelightvision.ControlMode.LedMode;
import frc.robot.subsystems.DriveTrain;


public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
 
  @Override
  public void robotInit() {
    
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
   
    CommandScheduler.getInstance().run();
  }

  
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
   
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
