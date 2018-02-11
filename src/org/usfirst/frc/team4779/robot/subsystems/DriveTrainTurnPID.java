package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainTurnPID extends PIDSubsystem {
	
    // Initialize your subsystem here
    public DriveTrainTurnPID() {
    	super("DriveTrainTurnPID", .5, 10, 0.1);
		setAbsoluteTolerance(RobotMap.dTTurnAbsoluteTolerance);
		getPIDController().setOutputRange(RobotMap.dTEncoderOutputMin, RobotMap.dTEncoderOutputMax);
		SmartDashboard.putNumber("GyroPID", getPIDController().get());
		//LiveWindow.addActuator("Gyro PID", "Gyro", getPIDController());
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return RobotMap.gyro.getAngle();
    }

    public void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	RobotMap.myDrive.arcadeDrive(0, output);
    }
    
    public void calibrateGyro () {
    	RobotMap.gyro.calibrate();
    	System.out.println("Calibration of Gyro Complete");
    }
    
    public void resetGyro() {
    	RobotMap.gyro.reset();
    	System.out.println("Reset of Gyro Complete");
    }
    
    public double getGyroAngle() {
    	return RobotMap.gyro.getAngle();
    }
    
}
