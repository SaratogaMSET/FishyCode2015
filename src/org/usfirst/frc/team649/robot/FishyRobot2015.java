
package org.usfirst.frc.team649.robot;

import org.usfirst.frc.team649.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.IterativeRobot;
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
 */
public class FishyRobot2015 extends IterativeRobot {

	public static CommandBase commandBase = new CommandBase();

	public SendableChooser autoChooser;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	NetworkTable n;
    	autoChooser = new SendableChooser();
    	autoChooser.addObject("Some option 1", "option1");
    	autoChooser.addObject("Some option 2", "option2");
    	autoChooser.addObject("Some option 3", "option3");
    	
    	SmartDashboard.putData("Autonomous", autoChooser);
    	// instantiate the command used for the autonomous period
     //   autonomousCommand = new ExampleCommand();
    	SmartDashboard.putData(Scheduler.getInstance());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	String autoMode = (String) autoChooser.getSelected();
    	
    	
    	//obviously names will be changed
    	switch(autoMode){
    	case "option1":
    		break;
    	case "option2":
    		break;
    	case "option3":
    		break;
    	}
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
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
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
