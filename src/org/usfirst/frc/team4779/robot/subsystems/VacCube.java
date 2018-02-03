package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.VacCubeOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VacCube extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	Spark vacCubeLeftMotor = new Spark(RobotMap.vacCubeLeftMotor);
	Spark vacCubeRightMotor = new Spark(RobotMap.vacCubeRightMotor); 
	
    public void initDefaultCommand() {
    	setDefaultCommand(new VacCubeOff());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    private void setMotorPower(double power) {
    	vacCubeLeftMotor.set(power);
    	//inverted right motor power, assumes identical wiring 
    	vacCubeRightMotor.set(-power);
    }
    
    public void vacCubeOff() {
    	setMotorPower(RobotMap.vacCubeMotorPowerOff);
    }
    
    public void vacCubeIntake() {
    	setMotorPower(RobotMap.vacCubeMotorPowerIntake);
    }
    
    public void vacCubeHold() {
    	setMotorPower(RobotMap.vacCubeMotorPowerHold);
    }
    
    public void vacCubeEject() {
    	setMotorPower(RobotMap.vacCubeMotorPowerEject);
    }
}

