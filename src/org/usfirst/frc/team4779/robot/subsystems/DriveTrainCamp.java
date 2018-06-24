package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.drivetrain.DriveJoystick;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *   The DriveTrain Subsystem is where we drive power to the Spark Controllers to move the robot.
 */
public class DriveTrainCamp extends Subsystem{
	
	//  Declare the four Spark controllers for each of the motors on the chassis.
	
	//  Declare the two speed control groups.  (Left side and Right Side) 
	
	//  Using the two speed controller groups, Declare our Differential Drive.

	
	public DriveTrainCamp() {	
	}
	
    public void initDefaultCommand() {
    	//  Our Default Command is to Drive using the Joystick.
    	//NOTE:  WE INITIALLY HAD THE DriveJoystick COMMAND AS THE DEFAULT, BUT THIS CAUSES A HERKRY JERKY ROBOT AS
    	//  EVERYTIME THE PID RUNS THROUGH A CYCLE, IT RELEASES CONTROL TO THE JOYSTICK ONLY TO TAKE IT BACK AGAIN.
    	//  THE TEAM NEEDS TO FIX THIS BY WAITING UNTIL teleop init() in Robot.java.

    }
    
    public void arcadeDrive(double yAxis, double xAxis) {
    	//  This is our where we define arcadeDrive within the Subsystem
    	//  NOTE:  the xAxis off of the Joystick below is INVERTED.
    	
    	//Check to see how high the lift is..   If it's under our threashhold.
    	if (Robot.lift.getDistance() < RobotMap.liftDTThrottleHeight) {

    	}
    	//if it's over our threshhold, throttle down the driveTrain.  DONT WANT THE ROBOT TO TIP!!
    	else   {

    	}
     }
        

    public void stop() {
    	//  If needed, we can stop the driveTrain by sending 0's to arcadeDrive.

    }


    /**
     * Set that state of the SafetyEnable mode of the drive train.  
     * @param enabled setting to false will remove the annoying error messages
     */
    public void setMotorSafety(boolean enabled ) {
    	myDrive.setSafetyEnabled(enabled);
    }
    
}
