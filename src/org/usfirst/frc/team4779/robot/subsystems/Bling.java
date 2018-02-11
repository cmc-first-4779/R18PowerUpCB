package org.usfirst.frc.team4779.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4779.robot.commands.bling.BlingOff;

import com.mindsensors.CANLight;

/**
 *  The Bling Subsystem is where we will control our LED Lights.
 */
public class Bling extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//  Declare our Lights
	CANLight lights = new CANLight(3);;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	 	setDefaultCommand(new BlingOff());  	
    }
    
 
    
    public void blingSolidColor(int red, int green, int blue) {
        //  Turn the Lights "white".
        lights.showRGB(red, green, blue);
    }
    
    
    
    public void blingOff()  {
    	lights.showRGB(0, 0, 0);
    }
}

