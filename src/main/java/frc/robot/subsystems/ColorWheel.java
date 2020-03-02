/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorSensorV3.RawColor;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorWheel extends SubsystemBase {

  private final I2C.Port i2c = Constants.i2cPort;
  private final ColorSensorV3 colorSensorV3 = new ColorSensorV3(Constants.i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  public final PWMSparkMax rgb_m = new PWMSparkMax(Constants.rgb_motor_ID);
  public final DoubleSolenoid TTLeft = Constants.TTLeft_SOLENOID;
  public final DoubleSolenoid TTRight = Constants.TTRight_SOLENOID;
  public final DoubleSolenoid TT_RGB = Constants.TTRGB_SOLENOID;

  public void start_rgbMotor(double s){
  rgb_m.set(s);
  }

  public void back_rgbMotor(double s){
  rgb_m.set(-s);
  }

  public void stop_rgbMotor(){
  rgb_m.set(0);
  }

  public void reachColorWheel(){
    TTLeft.set(Value.kForward);
    TTRight.set(Value.kForward);
    TT_RGB.set(Value.kForward);
  }

  public void unreachColorWheel(){
    TTLeft.set(Value.kReverse);
    TTRight.set(Value.kReverse);
    TT_RGB.set(Value.kReverse);
  }




  public Boolean alogStartRotation(){
    double counter = 0;
    boolean done = false;
    Color detectedColor = colorSensorV3.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    while(counter != 6){
      if(match.color == kBlueTarget){
      counter = counter + .5;
      }
      }
      if(counter == 6){
        done = true;
      }
    return done;
  }

  public Double countEachRotation(){
      Color detectedColor = colorSensorV3.getColor();
      ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
      double counter = 0;
      
      for (int i = 0; i < 10; i++) {
        if(match.color == kBlueTarget)
        {
          counter += .5;
        }
      }

      return counter; 
  }

  public ColorWheel() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);   
  }

  @Override
  public void periodic() {
  String gameData;
  gameData = DriverStation.getInstance().getGameSpecificMessage();
  if(gameData.length() > 0)
  {
    switch (gameData.charAt(0))
    {
      case 'B' :
        //Blue case code
        System.out.println("Blue is send");
        break;
      case 'G' :
        //Green case code
        System.out.println("Green is send");
        break;
      case 'R' :
        //Red case code
        System.out.println("Red is send");
        break;
      case 'Y' :
        //Yellow case code
        System.out.println("Yellow is send");
        break;
      default :
        //This is corrupt data
        System.out.println("Corrupt data is send");
        break;
    }
  } else {
    //Code for no data received yet
  }
    Color detectedColor = colorSensorV3.getColor();
    RawColor rawColor = colorSensorV3.getRawColor();

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == kRedTarget) {
      colorString = "Red";
    } else if (match.color == kGreenTarget) {
      colorString = "Green";
    } else if (match.color == kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    double IR = colorSensorV3.getIR();

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the 
     * sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
    SmartDashboard.putNumber("IR", IR);

    //SmartDashboard.putBoolean("6R success", alogStartRotation());
    SmartDashboard.putNumber("Count Each Rotation", countEachRotation());
    
    /**
     * The sensor returns a raw IR value of the infrared light detected.
     */
  
    /**
     * In addition to RGB IR values, the color sensor can also return an 
     * infrared proximity value. The chip contains an IR led which will emit
     * IR pulses and measure the intensity of the return. When an object is 
     * close the value of the proximity will be large (max 2047 with default
     * settings) and will approach zero when the object is far away.
     * 
     * Proximity can be used to roughly approximate the distance of an object
     * or provide a threshold for when an object is close enough to provide
     * accurate color values.
     */
    int proximity = colorSensorV3.getProximity();

    SmartDashboard.putNumber("Proximity", proximity);
    // This method will be called once per scheduler run
  }
}
