package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  The DriveTrainTurnPID Subsystem is used to use the gyro to turn the robot to a certain angle.
 */
public class DriveTrainTurnPID extends PIDSubsystem {
	
	//ALL OF OUR SPARK CONTROLLERS, GYRO, AND ENCODER ARE DECLARED AND INITIALIZED IN RobotMap.
	//  WE HAD TO DO THIS SO THAT THREE SUBSYSTEMS COULD SHARE THEM.
	
    // Initialize your subsystem here
    public DriveTrainTurnPID() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("DriveTrainTurnPID", .5, 10, 0.1);  // NEED TO TUNE THE PID VALUES
    	
    	//Set the "yaw" that we are willing to be off of during the turn.
		setAbsoluteTolerance(RobotMap.dTTurnAbsoluteTolerance);
		//Set our Min and Max values for the motor power while we use this subsystem.
		getPIDController().setOutputRange(RobotMap.dTEncoderOutputMin, RobotMap.dTEncoderOutputMax);
		//Send the Gyro info to the Smart Dashboard.
		SmartDashboard.putNumber("GyroPID", getPIDController().get());
		//LiveWindow.addActuator("Gyro PID", "Gyro", getPIDController());

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	//   
    	//  Get the angle of our gyro for the PID Input
        return RobotMap.gyro.getAngle();
    }

    public void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	//
    	//  Send the output of the PID to the arcade drive to turn the robot to the appropriate angle.
    	//  Note:   y-axis is 0 in arcadeDrive here so that we only turn and not move forward.
    	RobotMap.myDrive.arcadeDrive(0, output);
    }
}
