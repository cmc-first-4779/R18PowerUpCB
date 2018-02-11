package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 *   The DriveTrain Subsystem is where we drive power to the Spark Controllers to move the robot.
 */
public class DriveTrain extends Subsystem {

	double Kp = 0.03;

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
    	RobotMap.myDrive.arcadeDrive(-yAxis, xAxis);
    }
    
    public void arcadeDriveWithGryo() {
		SmartDashboard.putNumber("Gryo Angle", RobotMap.gyro.getAngle());
    	double angle = RobotMap.gyro.getAngle();
    	System.out.println("Angle: " + angle);
    	RobotMap.myDrive.arcadeDrive(-.4, Kp*-angle );
    }
    
    public void resetGyro() {
    	RobotMap.gyro.reset();
    }
    
    public void stop() {
    	//  If needed, we can stop the driveTrain by sending 0's to arcadeDrive.
    	RobotMap.myDrive.arcadeDrive(0,0);
    }
//    public AnalogGyro getGyro() {
        public ADXRS450_Gyro getGyro() {
    	return RobotMap.gyro;
    }
}

