package org.usfirst.frc.team4779.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4779.robot.RobotMap;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 *
 */
public class Limelight extends Subsystem {

	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableEntry tx = table.getEntry("tx");
	NetworkTableEntry ty = table.getEntry("ty");
	NetworkTableEntry ta = table.getEntry("ta");

	//read values periodically
	double x = tx.getDouble(0.0);
	double y = ty.getDouble(0.0);
	double area = ta.getDouble(0.0);

	//post to smart dashboard periodically
//	SmartDashboard.putNumber("LimelightX", x);
//	SmartDashboard.putNumber("LimelightY", y);
//	SmartDashboard.putNumber("LimelightArea", area);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void setCameraMode(double camMode) {
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(camMode);
    }
    
    
    public void setLEDMode(double ledMode) {
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledMode);
    }
    
    public void setPipeline(double pipeline) {
    	NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipeline);
    } 
    
	public double getTX() {
		return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
	}
    
	public double getTY() {
		return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
	}
		
	public double getTV() {
		return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
	}
	
	public double getTA() {
		return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
	}
	
		
	public double getNX()  {
		double NX = (1/(RobotMap.LIMELIGHT_X_PIXEL_COUNT/2))*(getTX() - ((RobotMap.LIMELIGHT_X_PIXEL_COUNT/2)+0.5));
		return NX;
	}
	
	public double getNY()  {
		double NY = (1/(RobotMap.LIMELIGHT_Y_PIXEL_COUNT/2))*(((RobotMap.LIMELIGHT_Y_PIXEL_COUNT/2)+0.5)- getTY());
		return NY;
	}
	
	public double getViewPlaneWidth()  {
		double ViewPlaneWidth = 2.0 * Math.tan(RobotMap.LIMELIGHT_X_PIXEL_COUNT/2);
		return ViewPlaneWidth;
	}
	
	public double getViewPlaneHeight()  {
		double ViewPlaneHeight = 2.0 * Math.tan(RobotMap.LIMELIGHT_Y_PIXEL_COUNT/2);
		return ViewPlaneHeight;
	}
	
	public double getX()  {
		double x = getViewPlaneWidth() / 2 * getNX();
		return x;
	}
	
	public double getY()  {
		double y = getViewPlaneHeight() / 2 * getNY();
		return y;
	}
	
    public double getAX()  {
    	double AX = Math.atan2(RobotMap.LIMELIGHT_DISTANCE_FROM_TARGET, getX());
    	return AX;
    }	
    
    public double getAY()  {
    	double AY = Math.atan2(RobotMap.LIMELIGHT_DISTANCE_FROM_TARGET, getY());
    	return AY;
    }	
    
    public double getDistance()  {
    	//double distance = (RobotMap.LIMELIGHT_CARGO_HEIGHT - RobotMap.LIMELIGHT_CAMERA_HEIGHT) / Math.tan(getAY() - RobotMap.LIMELIGHT_CAMERAMOUNT_ANGLE);
    	double distance =  4*Math.sqrt(getTA());
    	return distance;
    }
    
    
}



