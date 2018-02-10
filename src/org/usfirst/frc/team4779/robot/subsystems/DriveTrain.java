package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveJoystick;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 *   The DriveTrain Subsystem is where we drive power to the Spark Controllers to move the robot.
 */
public class DriveTrain extends Subsystem {
	
	//  Declare the four Spark controllers for each of the motors on the chassis.
	Spark frontLeftDrive = new Spark(RobotMap.frontLeftDrive);
	Spark frontRightDrive = new Spark(RobotMap.frontRightDrive);
	Spark rearLeftDrive = new Spark(RobotMap.rearLeftDrive);
	Spark rearRightDrive = new Spark(RobotMap.rearRightDrive);
	
	//AnalogGyro gyro = new AnalogGyro(1);
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	double Kp = 0.03;

	//  Declare the two speed control groups.  (Left side and Right Side) 
	SpeedControllerGroup myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
	SpeedControllerGroup myDriveRight = new SpeedControllerGroup (frontRightDrive, rearRightDrive);
	
	//  Using the two speed controller groups, Declare our Differential Drive.
	DifferentialDrive myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);
	

	public DriveTrain() {
		super("DriveTrain");
	}
	
    public void initDefaultCommand() {
    	//  Our Default Command is to Drive using the Joystick.
        setDefaultCommand(new DriveJoystick());
        //gyro.reset();
    }
    
    public void arcadeDrive(double yAxis, double xAxis) {
    	//  This is our where we define arcadeDrive within the Subsystem
    	//  NOTE:  the xAxis off of the Joystick below is INVERTED.
    	myDrive.arcadeDrive(-yAxis, xAxis);
    }
    
    public void arcadeDriveWithGryo() {
		SmartDashboard.putNumber("Gryo Angle", gyro.getAngle());
    	double angle = gyro.getAngle();
    	System.out.println("Angle: " + angle);
    	myDrive.arcadeDrive(-.4, Kp*-angle );
    }
    
    public void resetGyro() {
    	gyro.reset();
    }
    
    public void stop() {
    	//  If needed, we can stop the driveTrain by sending 0's to arcadeDrive.
    	myDrive.arcadeDrive(0,0);
    }
//    public AnalogGyro getGyro() {
        public ADXRS450_Gyro getGyro() {
    	return gyro;
    }
}

