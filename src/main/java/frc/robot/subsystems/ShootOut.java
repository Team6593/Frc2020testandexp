/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShootOut extends SubsystemBase {
  //private final Spark SO_Motor = new Spark(Constants.SHOOTOUT_ID);
  private final Spark SO_Motor_high = new Spark(17);
  
 // private final CANSparkMax SO_Motor = new CANSparkMax(Constants.SHOOTOUT_ID);

  public void start_rolling_high (double s){
    SO_Motor_high.setSpeed(s);
  }

  public void stop_rolling() {
    SO_Motor_high.stopMotor();
  }
  /**
   * Creates a new ShootOut.
   */
  public ShootOut() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
