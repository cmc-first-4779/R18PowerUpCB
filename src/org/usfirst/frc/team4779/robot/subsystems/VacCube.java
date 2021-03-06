package org.usfirst.frc.team4779.robot.subsystems;

import org.usfirst.frc.team4779.robot.Robot;
import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.vaccube.VacCubeOff;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	//adding in proximity sensor
	//  NOTE:  NOT USED IN COMPETITION.
	AnalogInput proxy = new AnalogInput(0);
	
	//  Local variable used for power in this method.
	private double m_power;
	
    public void initDefaultCommand() {
    	//By Default, we want the VacCube off to not drain the battery.
    	setDefaultCommand(new VacCubeOff());
    	
        }
    
    //  This PRIVATE Method is convenient as it allows us to have one method that jointly sets the power
    //    to both the left and right Spark controllers.  We use this method below quite often.
    private void setMotorPower(double power) {
    	vacCubeLeftMotor.set(power);
    	//inverted right motor power, assumes identical wiring 
    	vacCubeRightMotor.set(power);
    	//Set the m_power to power so that we can call it in the getPower() method for the SmartDashboard.
    	m_power = power;
    	SmartDashboard.putNumber("VacCube Power:  ", getPower());
    }
    
    public void vacCubeOff() {
    	//Turn the VacCube Off
    	setMotorPower(RobotMap.vacCubeMotorPowerOff);
    	SmartDashboard.putNumber("Proximity Voltage: ", proxy.getVoltage());
    }
    
    public void vacCubeIntake() {
    	//Take in a Power Cube
    	setMotorPower(RobotMap.vacCubeMotorPowerIntake);
    }
    
    public void vacCubeHold() {
    	//Hold a Power Cube in place with minimal power.
    	//  NOTE:  NEVER USED IN COMPETITION
    	setMotorPower(RobotMap.vacCubeMotorPowerHold);
    }
    
    public void vacCubeEject() {
    	//  Eject a Cube Full Power.  Good for shooting far distances or when the scale or switch is empty.
    	setMotorPower(RobotMap.vacCubeMotorPowerEject);
    }
    
    public void vacCubeLowEject() {
    	//  Eject a Cube with LOW Power.   Used with the scale is full or if we want to try and put a power
    	//    cube on the end of the scale to get leverage.
    	setMotorPower(RobotMap.vacCubeMotorPowerLowEject);
    }
    
    public void vacCubeMediumEject() {
    	//  Eject a Cube with Medium Power.
    	setMotorPower(RobotMap.vacCubeMotorPowerMediumEject);
    }
    
    public double getPower()  {
    	return m_power;
    }

	public boolean hasCube() {
		//We were attempting to use the proximity sensor to determine whether we had a block.
		//  NOTE:  NOT USED IN COMPETITION.
		if(proxy.getVoltage() < RobotMap.GOT_CUBE_VOLTAGE) {
			return true;
		}
		else {
			return false;
		}
	}

}

