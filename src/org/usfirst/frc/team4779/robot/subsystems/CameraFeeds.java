package org.usfirst.frc.team4779.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
//import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class CameraFeeds extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    	//UsbCamera cameraHigh;
    	//UsbCamera cameraLow;
    	//CvSource currentFeed;
    	
     	private CameraServer cameraServer;
   
    	
    	public CameraFeeds()
    	{
           
    	}
    
    	public void run()
    	{
    		
    	}

		@Override
		protected void initDefaultCommand() {
			  //cameraLow = cameraServer.getInstance().startAutomaticCapture("CAMERA_LOW", 1);
			  //cameraHigh = cameraServer.getInstance().startAutomaticCapture("CAMERA_HIGH", 1);
			  //cameraServer.putVideo("CAMERA_LOW", 640, 480);
			  //currentFeed.setResolution(640, 480);
			  //currentFeed.setDescription("Current Feed");
			  
		}
		
		public void setCameraLow()  {
			//currentFeed.setResolution(160, 120);
			cameraServer.getInstance().startAutomaticCapture("CAMERA_LOW", 0);
			System.out.println("Trying to set the low camera");
		}
		
		public void setCameraHigh()  {
			cameraServer.getInstance().startAutomaticCapture("CAMERA_HIGH", 1);
			System.out.println("Trying to set the high camera");
		}

    	
        
}

