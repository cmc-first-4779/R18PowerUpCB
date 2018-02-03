package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.DriveJoystick;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	Spark frontLeftDrive = new Spark(RobotMap.frontLeftDrive);
	Spark frontRightDrive = new Spark(RobotMap.frontRightDrive);
	Spark rearLeftDrive = new Spark(RobotMap.rearLeftDrive);
	Spark rearRightDrive = new Spark(RobotMap.rearRightDrive);
	SpeedControllerGroup myDriveLeft = new SpeedControllerGroup(frontLeftDrive, rearLeftDrive);
	SpeedControllerGroup myDriveRight = new SpeedControllerGroup (frontRightDrive, rearRightDrive);
	
	DifferentialDrive myDrive = new DifferentialDrive(myDriveLeft, myDriveRight);


  

    public void initDefaultCommand() {
        setDefaultCommand(new DriveJoystick());
    }
    public void arcadeDrive(double yAxis, double xAxis) {
    	myDrive.arcadeDrive(yAxis, -xAxis);
    }
    public void stop() {
    	myDrive.arcadeDrive(0,0);
    }
}

