package org.usfirst.frc.team4779.robot.commands;

import org.usfirst.frc.team4779.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartDashboardInit extends Command {

    public SmartDashboardInit() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putNumber("My Scale Side:  ", Robot.myScaleSide);
    	SmartDashboard.putNumber("My Switch Side:  ", Robot.mySwitchSide);
    	SmartDashboard.putNumber("Opponent Switch Side:  ", Robot.opponentSwitchSide);
    	SmartDashboard.putData(Robot.driveTrain.gyro);
    	SmartDashboard.putData("DriveTrain State:  ", Robot.driveTrain);
    	SmartDashboard.putNumber("Left Encoder Position: ", Robot.driveTrain.getLeftEncoderPosition());
    	SmartDashboard.putNumber("Right Encoder Position", Robot.driveTrain.getRightEncoderPosition());
    	SmartDashboard.putNumber("Average Encoder Position:  ", Robot.driveTrain.getAvgEncoderPosition());
    	SmartDashboard.putNumber("Drive Speed:  ", Robot.driveTrain.getSpeed());
    	SmartDashboard.putNumber("Drive Direction:  ", Robot.driveTrain.direction);
    	SmartDashboard.putData("Lift State:  ", Robot.lift);
    	SmartDashboard.putNumber("Lift Encoder Position:  ", Robot.lift.getDistance());
    	SmartDashboard.putData("VacCube State:  ", Robot.vacCube);
    	    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
