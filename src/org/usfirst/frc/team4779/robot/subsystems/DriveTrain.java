package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * The DriveTrain Subsystem is where we drive power to the Spark Controllers to
 * move the robot.
 */
public class DriveTrain extends PIDSubsystem {

	// Declare the four Spark controllers for each of the motors on the chassis.
	Spark frontLeftDrive = new Spark(RobotMap.frontLeftDrivePWMPort);
	Spark frontRightDrive = new Spark(RobotMap.frontRightDrivePWMPort);
	Spark rearLeftDrive = new Spark(RobotMap.rearLeftDrivePWMPort);
	Spark rearRightDrive = new Spark(RobotMap.rearRightDrivePWMPort);

	private double currentSpeedSetting = 0;
	private double speedStep = RobotMap.SPEED_STEP;
	private double minStartingSpeed = RobotMap.STARTING_SPEED;

	// Declare and Initialize our Gyro.
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	// Declare the variable we are using to set the forward or backward direction of
	// the robot for Auton Comamnd Groups.
	public int direction; // forward 1 or backward -1

	// The current distance we are trying to go. Used for auton
	public double distance;

	// Declare the variable we are using for speed for our Auton Command Groups.
	private double speed;

	// Declare the two speed control groups. (Left side and Right Side)
	SpeedControllerGroup myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
	SpeedControllerGroup myDriveRight = new SpeedControllerGroup(frontRightDrive, rearRightDrive);

	// Using the two speed controller groups, Declare our Differential Drive.
	DifferentialDrive myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);

	// Declare and initialize our DriveTrain Encoders and Encoder Channels on the
	// proper DIO ports.
	private Encoder dTEncoderLeft = new Encoder(RobotMap.dTEncoderLeftChannelA, RobotMap.dTEncoderLeftChannelB);
	private Encoder dTEncoderRight = new Encoder(RobotMap.dTEncoderRightChannelA, RobotMap.dTEncoderRightChannelB);

	public DriveTrain() {
		// OUR GYRO PID SETTINGS. WE GOT THIS THROUGH A LOT OF TESTING AND
		// CALIBRATION!!!
		super("DriveTrain", RobotMap.dTPValue, RobotMap.dtIValue, RobotMap.dtDValue);
		// Set the Absolute Tolerance for error in our Gyro. (In Degrees)
		setAbsoluteTolerance(RobotMap.dTEncoderAbsoluteTolerance);
		// Set our Encoder Ouput Min and Max. This will limit the speed of our motors
		// while the PID is running.
		setOutputRange(RobotMap.dTEncoderOutputMin, RobotMap.dTEncoderOutputMax);

		// Set the distance per pulse per Rotary Encoder. Got this through calibration
		// and testing.
		// Check to see if we are using the Mule robot, and if so, use it's distance per
		// pulse. Otherwise, use CuBert's value
		if (Robot.getWhichRobot() == RobotMap.MULE) {
			dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse_mule);
			dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse_mule);
		} else {
			dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse_cubert);
			dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse_cubert);
		}

	}

	public void setOutputRangeOfEncoders(double min, double max) {
		setOutputRange(min, max);
	}

	public void initDefaultCommand() {
		// Our Default Command is to Drive using the Joystick.
		// NOTE: WE INITIALLY HAD THE DriveJoystick COMMAND AS THE DEFAULT, BUT THIS
		// CAUSES A HERKRY JERKY ROBOT AS
		// EVERYTIME THE PID RUNS THROUGH A CYCLE, IT RELEASES CONTROL TO THE JOYSTICK
		// ONLY TO TAKE IT BACK AGAIN.
		// THE TEAM NEEDS TO FIX THIS BY WAITING UNTIL teleop init() in Robot.java.
		// setDefaultCommand(new DriveJoystick());
	}

	public void arcadeDrive(double yAxis, double xAxis) {
		// This is our where we define arcadeDrive within the Subsystem
		// NOTE: the xAxis off of the Joystick below is INVERTED.

		// Check to see how high the lift is.. If it's under out threashhold.
		if (Robot.lift.getDistance() < RobotMap.liftDTThrottleHeight) {
			currentSpeedSetting = -yAxis;
			myDrive.arcadeDrive(-yAxis, xAxis * RobotMap.dtTurnThrottle);
		}
		// if it's over our threshhold, throttle down the driveTrain.
		else {
			// myDrive.arcadeDrive(-yAxis*RobotMap.dTLiftThrottleDown,
			// xAxis*RobotMap.dtTurnLiftedThrottle);
			myDrive.arcadeDrive(calculateStepSpeed(-yAxis), xAxis * RobotMap.dtTurnThrottle);
		}

		SmartDashboard.putData(gyro);

		// Send ENCODER information to the Smart Dashboard.
		SmartDashboard.putNumber("Left Encoder Position: ", getLeftEncoderPosition());
		SmartDashboard.putNumber("Right Encoder Position", getRightEncoderPosition());
		SmartDashboard.putNumber("Average Encoder Position:  ", getAvgEncoderPosition());
		SmartDashboard.putNumber("Drive Speed:  ", getSpeed());
		SmartDashboard.putNumber("Drive Direction:  ", getDirection());
		SmartDashboard.putNumber("Drive Angle:  ", getDriveAngle());
	}

	private double calculateStepSpeed(double requestedSpeed) {
		double speed = 0;
		// Determine if desired direction is forward or backward.
		// Positive number is forward and negative number is backwards
		if (requestedSpeed > 0 && requestedSpeed > minStartingSpeed) {
			// Requested to move forward
			// Check to see if requested direction is opposite of current direction
			// If so, check to see if current speed is less than start speed, if so it's
			// safe to switch
			// If not, need to reduce current speed by the step value
			if (currentSpeedSetting < 0) {
				// Currently moving backwards, so we don't want to change direction to quickly
				if (currentSpeedSetting > -minStartingSpeed) {
					// Currently going slow, so it safe to go the opposite direction
					speed = minStartingSpeed;
					currentSpeedSetting = minStartingSpeed;
				} else {
					// Was moving fast in reverse so want to step the speed down
					speed = currentSpeedSetting + speedStep;
					currentSpeedSetting = speed;
				}
			} else {
				// Currently moving forwards so we can ramp it up
				// Check to see if CurrentSpeed is less than starting speed
				// If yes, set to starting speed
				// If not, check to see if requested speed is greater than current speed
				// If Yes, ramp it up
				// If not, ramp it down
				if (currentSpeedSetting < minStartingSpeed) {
					// Must be going real slow or stopped
					speed = minStartingSpeed;
					currentSpeedSetting = speed;
				} else {
					if (requestedSpeed == currentSpeedSetting) {
						// Can just let it stay
						speed = currentSpeedSetting;
					} else if (requestedSpeed > currentSpeedSetting) {
						// Need to speed it up a step
						speed = currentSpeedSetting + speedStep;
						currentSpeedSetting = speed;
					} else {
						// Need to slow it down a step
						speed = currentSpeedSetting - speedStep;
						currentSpeedSetting = speed;
					}
				}
			}
		} else if (requestedSpeed < 0 && requestedSpeed < -minStartingSpeed) {
			// Requesting to go backwards at a rate greater than our minimal moving speed
			// Check to see if requested direction is opposite of current direction
			// If so, check to see if current speed is less than start speed, if so it's
			// safe to switch
			// If not, need to reduce current speed by the step value
			if (currentSpeedSetting > 0) {
				// Currently moving forwards, so we don't want to change direction to quickly
				if (currentSpeedSetting < minStartingSpeed) {
					// Currently going slow, so it safe to go the opposite direction
					speed = -minStartingSpeed;
					currentSpeedSetting = -minStartingSpeed;
				} else {
					// Was moving forward fast so want to step the speed down
					speed = currentSpeedSetting - speedStep;
					currentSpeedSetting = speed;
				}
			} else {
				// Currently moving backwards so we can ramp it up
				// Check to see if CurrentSpeed is less than starting speed
				// If yes, set to starting speed
				// If not, check to see if requested speed is greater than current speed
				// If Yes, ramp it up
				// If not, ramp it down
				if (currentSpeedSetting > -minStartingSpeed) {
					// Must be going real slow or stopped
					speed = -minStartingSpeed;
					currentSpeedSetting = speed;
				} else {
					//Going faster than the minimal speed in reverse
					if (requestedSpeed == currentSpeedSetting) {
						// Can just let it stay
						speed = currentSpeedSetting;
					} else if (requestedSpeed < currentSpeedSetting) {
						// Need to speed it up a step by going more negative
						speed = currentSpeedSetting - speedStep;
						currentSpeedSetting = speed;
					} else {
						// Need to slow it down a step by increasing the negative speed 
						speed = currentSpeedSetting + speedStep;
						currentSpeedSetting = speed;
					}
				}
			}
		}

		SmartDashboard.putNumber("Throttled Speed", speed);
		// Going to return negated speed since the arcade drive expects negative to move
		// forward but I wanted positive
		// to mean forward to make the logic easier to follow
		return -speed;
	}

	@Override
	protected double returnPIDInput() {
		// The Gyro Angle is the INPUT into the PID
		return gyro.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// The ArcadeDrive command with our Speed and OUTPUT as our x-axis rotation is
		// how we use the PID OUTPUT

		myDrive.arcadeDrive(speed, output);

		// Send GYRO information to the SMART DASHBOARD
		SmartDashboard.putData(gyro);
		SmartDashboard.putNumber("Gryo Angle:  ", gyro.getAngle());
		SmartDashboard.putNumber("Gyro PID Output:  ", output);
		SmartDashboard.putNumber("Left Encoder Position: ", getLeftEncoderPosition());
		SmartDashboard.putNumber("Right Encoder Position", getRightEncoderPosition());
		SmartDashboard.putNumber("Average Encoder Position:  ", getAvgEncoderPosition());
		SmartDashboard.putNumber("Drive Speed:  ", getSpeed());
		SmartDashboard.putNumber("Drive Direction:  ", getDirection());
		SmartDashboard.putNumber("Drive Angle:  ", getDriveAngle());
	}

	public void resetGyro() {
		// This method exists so that our commands can call on the subsystem to reset
		// the Gyro.
		// Usually, we call this each time we initialize a drivetrain command.
		gyro.reset();
	}

	public void calibrateGyro() {
		// This method exists so that we can calibrate our gyro during robotInit() in
		// Robot.java.
		// Only gets called once.
		System.out.println("Calibrating Gyro");
		gyro.calibrate();
	}

	public void stop() {
		// If needed, we can stop the driveTrain by sending 0's to arcadeDrive.
		myDrive.arcadeDrive(0, 0);
	}

	public void setSpeed(double speed) {
		// Set our Speed for y-axis arcade drive.
		// Gets called above.
		this.speed = speed;
	}

	public void setDirection(int dir) {
		// Set our Direction to either "1" for forward or "-1" for reverse.
		// Gets called above.
		this.direction = dir;
	}

	public void resetDTEncoders() {
		// Reset both of our rotary encoders. We call this usually at the start of every
		// drivetrain command.
		dTEncoderLeft.reset();
		dTEncoderRight.reset();
	}

	public double getAvgEncoderPosition() {
		// Average our two rotary encoders together to account for slippage and turning.
		return (-dTEncoderLeft.getDistance() + dTEncoderRight.getDistance()) / 2;
	}

	public double getLeftEncoderPosition() {
		// Get the Position of the Left encoder
		return dTEncoderLeft.getDistance();
	}

	public double getRightEncoderPosition() {
		// Get the Position of the Right encoder
		return dTEncoderRight.getDistance();
	}

	public double getSpeed() {
		// Return the speed of the drivetrain
		return speed;
	}

	public int getDirection() {
		// Return the direction of the drivetrain.
		return direction;
	}

	public double getDriveAngle() {
		// Return the angle of the Gyro
		return gyro.getAngle();
	}

	public void setDistance(Double dis) {
		this.distance = dis;
	}

	/**
	 * Set that state of the SafetyEnable mode of the drive train.
	 * 
	 * @param enabled
	 *            setting to false will remove the annoying error messages
	 */
	public void setMotorSafety(boolean enabled) {
		myDrive.setSafetyEnabled(enabled);
	}

	/**
	 * Sets the encoder distance per pulse correctly based on which robot is
	 * selected
	 */
	public void setEncoderDistancePerPulse() {
		// Set the distance per pulse per Rotary Encoder. Got this through calibration
		// and testing.
		// Check to see if we are using the Mule robot, and if so, use it's distance per
		// pulse. Otherwise, use CuBert's value
		if (Robot.getWhichRobot() == RobotMap.MULE) {
			dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse_mule);
			dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse_mule);
		} else {
			dTEncoderLeft.setDistancePerPulse(RobotMap.dTDistancePerPulse_cubert);
			dTEncoderRight.setDistancePerPulse(RobotMap.dTDistancePerPulse_cubert);
		}

	}

}
