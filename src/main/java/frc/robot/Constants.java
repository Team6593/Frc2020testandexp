package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.XboxController;

public final class Constants {

	//GYRO
	public static final AHRS navx_gyro = new AHRS(SPI.Port.kMXP);

	//DRIVETRAIN PORT NUMBERS
	public static final int m_MasterLeftID = 6;
	public static final int m_MasterRightID = 7;
	public static final int m_SlaveLeftID = 8;
	public static final int m_SlaveRightID = 9;

	//SHOOT OUT MOTOR PORT NUMBER
	public static final int SHOOTOUT_ID = 0;

	//ROLLERS INTAKE [ V(Main) ], LEFT [H V(Shorter)] AND RIGHT ROLLERS [H V(Longer)]
	public static final int ROLLERS_V_INTAKE_MAIN_ID = 4;
	public static final int ROLLERS_H_ID = 2;
	public static final int RIGHT_HIGH_V_ROLLER_ID = 1;
	public static final int LEFT_LOW_V_ROLLER_ID = 3;

	//XBOX BUTTON MAPPING
	public static final int XBOX_PORT_ID = 0;
	public static final XboxController XBOX_CONTROLLER = new XboxController(XBOX_PORT_ID);
	public static final int L_X_AXIS = 0;
	public static final int L_Y_AXIS = 1;
	public static final int L_TRIGGER = 2;
	public static final int R_TRIGGER = 3;
	public static final int R_X_AXIS = 4;
	public static final int R_Y_AXIS = 5;
	public static final int A_BUTTON = 1;
	public static final int B_BUTTON = 2;
	public static final int X_BUTTON = 3;
	public static final int Y_BUTTON = 4;
	public static final int LEFT_TRIGGER_BUTTON = 5;
	public static final int RIGHT_TRIGGER_BUTTON = 6;
	public static final int LEFT_SMALL_BUTTON = 7;
	public static final int RIGHT_SMALL_BUTTON = 8;
	public static final int LEFT_JOYSTICK_BUTTON = 9;
	public static final int RIGHT_JOYSTICK_BUTTON = 10;

	// COLOR SENSOR PORT
	public static final I2C.Port i2cPort = I2C.Port.kOnboard;

	//COLOR SENSOR MOTOR
	public static final int rgb_motor_ID = 8;

	//COLOR SENSOR PISTONS 
	public static final DoubleSolenoid TTLeft_SOLENOID = new DoubleSolenoid(2 , 3);
	public static final DoubleSolenoid TTRight_SOLENOID = new DoubleSolenoid(4 , 5);
	public static final DoubleSolenoid TTRGB_SOLENOID = new DoubleSolenoid(6 , 7);
	
	//SHIFTER
	public static final DoubleSolenoid SHIFTER_SOLENOID = new DoubleSolenoid(0 , 1);

	//COMPRESSOR
	public static final Compressor COMPRESSOR = new Compressor();
}
