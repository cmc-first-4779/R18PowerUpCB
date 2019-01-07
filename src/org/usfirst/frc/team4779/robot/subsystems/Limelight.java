package org.usfirst.frc.team4779.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4779.robot.RobotMap;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 *
 */
public class Limelight extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = limelightTable.getEntry("tx");
    NetworkTableEntry ty = limelightTable.getEntry("ty");
    NetworkTableEntry ta = limelightTable.getEntry("ta");
    NetworkTableEntry tv = limelightTable.getEntry("tv");

    //read values periodically
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double valid = tv.getDouble(0.0);

    //post to smart dashboard periodically
   // SmartDashboard.putNumber("LimelightX", x);
    //SmartDashboard.putNumber("LimelightY", y);
    //SmartDashboard.putNumber("LimelightArea", area);
    
    public void limelightOff() {
    	//Turn the Limelight off
    	//NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(<value>);
    	//SET LED to OFF (1)
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    	//Turn Camera Mode to driver cam
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
    	
    	}
    public void limelightOn() {
    	//Turn the Limelight on
    	//NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(<value>);
    	//SET LED to on (3)
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    	//Turn Camera Mode to driver cam
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
    }
    
    public void limelightVestPipeline() {
    	//Turn the Limelight on
    	//NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(<value>);
    	//SET Vision Pipeline to track the Vest
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    }
    
    public void limelightSetPipeline(int pipelineNumber)  {
    	//Set the Pipeline to the target we want..
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipelineNumber);
    }
    
    public double limelightGetTX()  {
    	//Seek our target..   Keep turning until the robot finds it..
    	return x;
    }
    
    public double limelightGetTY()  {
    	//Seek our target..   Keep turning until the robot finds it..
    	return y;
    }    

    public double limelightGetTA()  {
    	//Seek our target..   Keep turning until the robot finds it..
    	return area;
    }
    
    public double limelightGetTV()  {
    	//Seek our target..   Keep turning until the robot finds it..
    	return valid;
    }
    
}

