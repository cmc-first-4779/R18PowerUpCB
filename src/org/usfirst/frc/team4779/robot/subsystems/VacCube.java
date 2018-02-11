package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *   The VacCube aka Grabber is what we use to grab power cubes and move them around.
 */
public class VacCube extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	//We have two motors on the grabber, each defined by a separate Spark Controller.
	//  Declaring and Initiating the two Sparks.
	Spark vacCubeLeftMotor = new Spark(RobotMap.vacCubeLeftMotorPWMPort);
	Spark vacCubeRightMotor = new Spark(RobotMap.vacCubeRightMotorPWMPort); 
	
    public void initDefaultCommand() {
    	//By Default, we want the VacCube off to not drain the battery.
    	setDefaultCommand(new VacCubeOff());

    }
    
    //  This PRIVATE Method is convenient as it allows us to have one method that jointly sets the power
    //    to both the left and right Spark controllers.  We use this method below quite often.
    private void setMotorPower(double power) {
    	vacCubeLeftMotor.set(power);
    	//inverted right motor power, assumes identical wiring 
    	vacCubeRightMotor.set(-power);
    }
    
    public void vacCubeOff() {
    	//Turn the VacCube Off
    	setMotorPower(RobotMap.vacCubeMotorPowerOff);
    }
    
    public void vacCubeIntake() {
    	//Take in a Power Cube
    	setMotorPower(RobotMap.vacCubeMotorPowerIntake);
    }
    
    public void vacCubeHold() {
    	//Hold a Power Cube in place with minimal power.
    	setMotorPower(RobotMap.vacCubeMotorPowerHold);
    }
    
    public void vacCubeEject() {
    	//  Eject a Cube.
    	setMotorPower(RobotMap.vacCubeMotorPowerEject);
    }
}

