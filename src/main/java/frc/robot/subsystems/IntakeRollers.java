/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.SparkMax;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeRollers extends SubsystemBase {
  private final PWMSparkMax Low_v_Intake;
  private final PWMSparkMax H_Band;
  private final PWMSparkMax High_v_Intake;
  private final PWMSparkMax Main_Intake;

  private Spark highSO = new Spark(Constants.HIGH_SHOOTOUT_ID);


  // private final Spark Low_v_Intake = new Spark(Constants.LEFT_LOW_V_ROLLER_ID);
  // private final Spark H_Band = new Spasrk(Constants.ROLLERS_H_ID);
  // private final Spark High_v_Intake = new Spark(Constants.RIGHT_HIGH_V_ROLLER_ID);
  // private final Spark Main_Intake = new Spark(Constants.ROLLERS_V_INTAKE_MAIN_ID);

  public IntakeRollers() {
    Low_v_Intake = new PWMSparkMax(Constants.LEFT_LOW_V_ROLLER_ID);
    H_Band = new PWMSparkMax(Constants.ROLLERS_H_ID);
    High_v_Intake = new PWMSparkMax(Constants.RIGHT_HIGH_V_ROLLER_ID);
    Main_Intake = new PWMSparkMax(Constants.ROLLERS_V_INTAKE_MAIN_ID);
  }

  public void soSetSpeed(double s){
    highSO.setSpeed(s);
  }
  public double getSo_Speed(){
    return highSO.getSpeed();
  }

  public void mainIntake(double s){
    Main_Intake.setSpeed(s);
  }
  public void rightMec_lowShoot(double hs, double v){
    H_Band.setSpeed(hs);
    High_v_Intake.setSpeed(v);
  }
  public void leftMec_highShoot(double hs, double v)
  {
    H_Band.setSpeed(hs);
    Low_v_Intake.setSpeed(v);
  }

  public void stopMainRoller(){
    Main_Intake.stopMotor();
    H_Band.stopMotor();
    High_v_Intake.stopMotor();
    Low_v_Intake.stopMotor();
  }

  /**
   * Creates a new IntakeRollers.
   */
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
