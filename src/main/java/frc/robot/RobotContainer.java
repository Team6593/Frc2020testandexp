/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autonomous.Autonomous;
import frc.robot.commands.Drive.ArcadeControl;
import frc.robot.commands.Drive.DriveStraightAuto;
import frc.robot.commands.Drive.HighGear;
import frc.robot.commands.Drive.LowGear;
import frc.robot.commands.LimeLight.Drive_limeLight_Aim;
import frc.robot.commands.LimeLight.Drive_limeLight_Aim_n_Range;
import frc.robot.commands.LimeLight.Drive_limeLight_Range;
import frc.robot.commands.LimeLight.Drive_limelight_Seeking;
import frc.robot.commands.LimeLight.LimeLED_ON;
import frc.robot.commands.TurnTable.RGB_BackM;
import frc.robot.commands.TurnTable.RGB_StartM;
import frc.robot.commands.TurnTable.ReachColorWheel;
import frc.robot.commands.TurnTable.UnreachColorWheel;
import frc.robot.subsystems.Cameras;
import frc.robot.subsystems.ColorWheel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX_Gyro;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  //SUBSYSTEMS
  private final DriveTrain m_drive = new DriveTrain();
  private final NavX_Gyro gyro = new NavX_Gyro();
  private final Cameras cam = new Cameras();
  private final ColorWheel cw = new ColorWheel();


  //LIMELIGHT
  private final Drive_limeLight_Aim aim = new Drive_limeLight_Aim(m_drive);
  private final Drive_limeLight_Range range = new Drive_limeLight_Range(m_drive);
  private final Drive_limeLight_Aim_n_Range aim_range = new Drive_limeLight_Aim_n_Range(m_drive);
  private final Drive_limelight_Seeking seeking = new Drive_limelight_Seeking(m_drive);
  private final LimeLED_ON ledON = new LimeLED_ON(m_drive);


  //AUTONOMUS
  private final DriveStraightAuto auto = new DriveStraightAuto(m_drive);
  private final CommandBase m_autonomousCommand = new Autonomous(m_drive);


  //TURN TABLE
  private final RGB_StartM rgb_StartM = new RGB_StartM(cw, .5);
  private final RGB_BackM rgb_BackM = new RGB_BackM(cw, -.5);
  private final ReachColorWheel reachColorWheel = new ReachColorWheel(cw);
  private final UnreachColorWheel unreachColorWheel = new UnreachColorWheel(cw);

  
  //JOYSTICK
  private final XboxController x_stick = new XboxController(0);


  //SHIFTER
  private final HighGear highGear = new HighGear(m_drive);
  private final LowGear lowGear = new LowGear(m_drive);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drive.setDefaultCommand(new ArcadeControl(m_drive, () -> x_stick.getRawAxis(1), () -> x_stick.getRawAxis(4)));
   //s m_drive.setDefaultCommand(new TankdriveControl(m_drive, () -> x_stick.getRawAxis(1), () -> x_stick.getRawAxis(4)));

    m_drive.log();
    gyro.log();
   // SmartDashboard.putBoolean("6r", rgbsub.alogStartRotation());
   // SmartDashboard.putNumber("rotate", rgbsub.countEachRotation());
    cam.log();
    // Configure the button bindings
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    //BUTTON DECLARATION
    final JoystickButton b_Button = new JoystickButton(x_stick, Constants.B_BUTTON);
    final JoystickButton a_Button = new JoystickButton(x_stick, Constants.A_BUTTON);
    final JoystickButton x_Button = new JoystickButton(x_stick, Constants.X_BUTTON);
    final JoystickButton y_Button = new JoystickButton(x_stick, Constants.Y_BUTTON);
    final JoystickButton LEFT_TRIGGER_BUTTON = new JoystickButton(x_stick, Constants.LEFT_TRIGGER_BUTTON);
    final JoystickButton RIGHT_TIGGER_BUTTON = new JoystickButton(x_stick, Constants.RIGHT_TRIGGER_BUTTON);
    final JoystickButton right_small_Button = new JoystickButton(x_stick, Constants.RIGHT_SMALL_BUTTON);

    //BUTTON MAPPING
     a_Button.whileHeld(rgb_StartM);
     b_Button.whileHeld(rgb_BackM);
     LEFT_TRIGGER_BUTTON.whenPressed(highGear);
     RIGHT_TIGGER_BUTTON.whenPressed(lowGear);
     x_Button.whenPressed(reachColorWheel);
     y_Button.whenPressed(unreachColorWheel);
    // a_Button.whileHeld(aim_range);
    // y_Button.whileHeld(seeking);

    // LEFT_TRIGGER_BUTTON.whenActive(auto);

    // right_small_Button.whenActive(ledON);
    // right_small_Button.cancelWhenActive(seeking);
    // right_small_Button.cancelWhenActive(aim);
    // right_small_Button.cancelWhenActive(range);
    // right_small_Button.cancelWhenActive(aim_range);
  
  }

/**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }
}
