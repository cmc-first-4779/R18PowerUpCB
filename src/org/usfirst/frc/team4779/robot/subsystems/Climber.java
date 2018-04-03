package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.ClimberOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
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

}