
package org.usfirst.frc.team649.robot;

import org.usfirst.frc.team649.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * @param <driveLeftEncoderState>
 */
public class FishyRobot2015 extends IterativeRobot {

	public static CommandBase commandBase = new CommandBase();

	public SendableChooser autoChooser;
	public Command autoCommand;
	public String autoMode;
	public boolean driveLeftEncoderState, driveRightEncoderState, chainEncoderState;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	NetworkTable table;
    	Preferences prefs;
    	autoChooser = new SendableChooser();
    	autoChooser.addObject("Debugger Mode", "debugger mode");
    	autoChooser.addObject("Winch Autonomous", "winch in totes");
    	autoChooser.addObject("Get Container and Tote", "container and tote");
    	autoChooser.addObject("Get Just Tote", "just tote");
    	//with container grabber open go right up to container, run intake, clamp on container, run chain up, get a tote, take it into autozone, just drop tote off, turn right
    	autoChooser.addObject("Do Nothing Autonomous", "none");
    	
    	SmartDashboard.putData("Autonomous Mode", autoChooser);
    	// instantiate the command used for the autonomous period
    	//autonomousCommand = new ExampleCommand();
    	SmartDashboard.putData(Scheduler.getInstance());
    	
    	//idk if this works
    	//SmartDashboard.putData("Cam", (Sendable) commandBase.cameraSubsystem.cam);
    	//cam must be configured from smartdashboard
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	autoMode = (String) autoChooser.getSelected();
    	driveLeftEncoderState = false;
    	driveRightEncoderState = false;
    	chainEncoderState = false;
    	
    	//obviously names will be changed
    	switch (autoMode){
    	case "debugger mode":
    		autoCommand = commandBase.debug();
    		break;
    	case "winch in totes":
    		autoCommand = commandBase.autoWinchAndDrive();
    		break;
    	case "container and totes":
    		autoCommand = commandBase.autoFullContainerAndToteSequence();
    		break;
    	case "just tote":
    		autoCommand = commandBase.autoContainerOnly();
    		break;
    	case "none":
    		autoCommand = null;
    		break;
    	}
    	
    	
    	if (autoCommand != null){ //for the case of none
    		autoCommand.start();
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        if (autoMode.equals("debugger mode") && !autoCommand.isRunning()){
        	if (commandBase.drivetrainSubsystem.encoders[0].get() != 0){
        		driveLeftEncoderState = true;
        	}
        	if (commandBase.drivetrainSubsystem.encoders[1].get() != 0){
        		driveRightEncoderState = true;
        	}
        	if (commandBase.chainLiftSubsystem.getHeight() != 0){
        		chainEncoderState = true;
        	}
        }
        
        SmartDashboard.putBoolean("Left Drive Encoder", driveLeftEncoderState);
        SmartDashboard.putBoolean("Right Drive Encoder", driveRightEncoderState);
        SmartDashboard.putBoolean("Chain Encoder", chainEncoderState);
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	
    	//FIGURE OUT HOW TO CLEAR SMARTDASHBOARD REMOTELY
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Chain Height", commandBase.chainLiftSubsystem.getHeight());
        commandBase.driveForwardRotate(commandBase.oi.driver.getDriveForward(), commandBase.oi.driver.getDriveRotation()).start();
        
        SmartDashboard.putData("Chain Encoder", commandBase.chainLiftSubsystem.encoders[0]);
        SmartDashboard.putData("Drive Encoder Left", commandBase.drivetrainSubsystem.encoders[0]);
        SmartDashboard.putData("Drive Encoder Right", commandBase.drivetrainSubsystem.encoders[1]);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
