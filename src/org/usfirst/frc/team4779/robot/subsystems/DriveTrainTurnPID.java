package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainTurnPID extends PIDSubsystem {
	
	Spark frontLeftDrive = new Spark(RobotMap.frontLeftDrive);
	Spark frontRightDrive = new Spark(RobotMap.frontRightDrive);
	Spark rearLeftDrive = new Spark(RobotMap.rearLeftDrive);
	Spark rearRightDrive = new Spark(RobotMap.rearRightDrive);
	SpeedControllerGroup myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
	SpeedControllerGroup myDriveRight = new SpeedControllerGroup (frontRightDrive, rearRightDrive);
	
	DifferentialDrive myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);

	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
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
        return gyro.getAngle();
    }

    public void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	myDrive.arcadeDrive(0, output);
    }
    
    public void calibrateGyro () {
    	gyro.calibrate();
    	System.out.println("Calibration of Gyro Complete");
    }
    
    public void resetGyro() {
    	gyro.reset();
    	System.out.println("Reset of Gyro Complete");
    }
    
    public double getGyroAngle() {
    	return gyro.getAngle();
    }
    
}
