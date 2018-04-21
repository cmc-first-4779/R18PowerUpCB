package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.ClimberOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *   NOTE:   WE DID NOT USE THE CLIMBER DURING COMPETITION
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Declare our Spark controller for our Climber motor
	Spark climberMotor = new Spark(RobotMap.climberPWMPort);


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ClimberOff());
    }


public void climberOff() {
	//Turn the Climber off
	climberMotor.set(RobotMap.CLIMBER_MOTOR_OFF);
	}

public void climberOn() {
	//Turn the CLimber on
	climberMotor.set(RobotMap.CLIMBER_MOTOR_SPEED);
	}

public void climberReverse() {
	climberMotor.set(-RobotMap.CLIMBER_MOTOR_SPEED);
}

}