package org.usfirst.frc.team4779.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4779.robot.RobotMap;
import org.usfirst.frc.team4779.robot.commands.bling.BlingOff;

import com.mindsensors.CANLight;

/**
 *  The Bling Subsystem is where we will control our LED Lights.
 */
public class Bling extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//  Declare our Lights
	CANLight lights = new CANLight(RobotMap.BLING_CANLIGHT_PORT);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	 	setDefaultCommand(new BlingOff());  	
    }
    
 
    
    public void blingSolidColor(int Red, int Green, int Blue) {
        //  Turn the Lights a specific color using red/green/blue values.
        lights.showRGB(Red, Green, Blue);
    }
    
    
    
    public void blingOff()  {
    	lights.showRGB(0, 0, 0);
    }
}

