package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.ramp.RampOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ramp extends Subsystem {

	Spark rampMotor = new Spark(RobotMap.rampMotorPWMPort);

	public Ramp() {
		super("Ramp");
	}
    public void initDefaultCommand() {
setDefaultCommand(new RampOff());
    }
    
    public void rampLift() {
    	rampMotor.set(RobotMap.rampMotorPowerUp);
    }
    
    public void rampLower() {
    	rampMotor.set(RobotMap.rampMotorPowerDown);
    }
    
    public void rampOff( ) {
    	rampMotor.set(0);
    }
}

