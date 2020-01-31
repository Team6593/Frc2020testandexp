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
import frc.robot.commands.ArcadeControl;
import frc.robot.commands.Autonomous;
import frc.robot.commands.Drive_limeLight_Aim;
import frc.robot.commands.Drive_limeLight_Aim_n_Range;
import frc.robot.commands.Drive_limeLight_Range;
import frc.robot.commands.Drive_limelight_Seeking;
import frc.robot.commands.LimeLED_ON;
import frc.robot.commands.testSpeedController;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.NavX_Gyro;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_drive = new DriveTrain();
  private final NavX_Gyro gyro = new NavX_Gyro();

  private final Drive_limeLight_Aim aim = new Drive_limeLight_Aim(m_drive);
  private final Drive_limeLight_Range range = new Drive_limeLight_Range(m_drive);
  private final Drive_limeLight_Aim_n_Range aim_range = new Drive_limeLight_Aim_n_Range(m_drive);
  private final Drive_limelight_Seeking seeking = new Drive_limelight_Seeking(m_drive);
  private final LimeLED_ON ledON = new LimeLED_ON(m_drive);
  private final testSpeedController left_right_test = new testSpeedController(m_drive, 0.5, 0, 3);

  private final CommandBase m_autonomousCommand = new Autonomous(m_drive);

  private final XboxController x_stick = new XboxController(0);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_drive.setDefaultCommand(new ArcadeControl(m_drive, () -> x_stick.getRawAxis(1), () -> x_stick.getRawAxis(4)));
   //s m_drive.setDefaultCommand(new TankdriveControl(m_drive, () -> x_stick.getRawAxis(1), () -> x_stick.getRawAxis(4)));

    m_drive.log();
    gyro.log();
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final JoystickButton b_Button = new JoystickButton(x_stick, Constants.B_BUTTON);
    final JoystickButton a_Button = new JoystickButton(x_stick, Constants.A_BUTTON);
    final JoystickButton x_Button = new JoystickButton(x_stick, Constants.X_BUTTON);
    final JoystickButton y_Button = new JoystickButton(x_stick, Constants.Y_BUTTON);
    final JoystickButton LEFT_TRIGGER_BUTTON = new JoystickButton(x_stick, Constants.LEFT_TRIGGER_BUTTON);
    final JoystickButton right_small_Button = new JoystickButton(x_stick, Constants.RIGHT_SMALL_BUTTON);

    b_Button.whileHeld(aim);
    x_Button.whileHeld(range);
    a_Button.whileHeld(aim_range);
    y_Button.whileHeld(seeking);
    LEFT_TRIGGER_BUTTON.whileHeld(left_right_test);

    right_small_Button.whenActive(ledON);
    right_small_Button.cancelWhenActive(seeking);
    right_small_Button.cancelWhenActive(aim);
    right_small_Button.cancelWhenActive(range);
    right_small_Button.cancelWhenActive(aim_range);
  
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
