
package org.usfirst.frc.team649.robot;

import org.usfirst.frc.team649.robot.commandgroups.AutoContainerOnly;
import org.usfirst.frc.team649.robot.commandgroups.AutoFullContainerAndToteSequence;
import org.usfirst.frc.team649.robot.commandgroups.AutoWinchAndDrive;
import org.usfirst.frc.team649.robot.commandgroups.Debug;
import org.usfirst.frc.team649.robot.commandgroups.FullContainerAndFirstToteSequence;
import org.usfirst.frc.team649.robot.commandgroups.FullRaiseTote;
import org.usfirst.frc.team649.robot.commandgroups.ScoreTotesOnPlatform;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.grabbercommands.RunRoller;
import org.usfirst.frc.team649.robot.commands.grabbercommands.SetIntakeArmPosition;
import org.usfirst.frc.team649.robot.commands.lift.ChangeOffsetHeight;
import org.usfirst.frc.team649.robot.subsystems.AutoWinchSubsystem;
import org.usfirst.frc.team649.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ContainerGrabberSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeLeftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeRightSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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

	public static OI oi;
	public static DrivetrainSubsystem drivetrainSubsystem;
	public static ChainLiftSubsystem chainLiftSubsystem;
	public static IntakeLeftSubsystem intakeLeftSubsystem;
	public static IntakeRightSubsystem intakeRightSubsystem;
	public static AutoWinchSubsystem autoWinchSubsystem;
	public static ContainerGrabberSubsystem containerGrabberSubsystem;
	public static CameraSubsystem cameraSubsystem;

	
	public SendableChooser autoChooser;
	public Command autoCommand;
	public String autoMode;
	public boolean driveLeftEncoderState, driveRightEncoderState, chainEncoderState;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	drivetrainSubsystem = new DrivetrainSubsystem();
    	chainLiftSubsystem = new ChainLiftSubsystem();
    	intakeLeftSubsystem = new IntakeLeftSubsystem();
    	intakeRightSubsystem = new IntakeRightSubsystem();
    //	autoWinchSubsystem = new AutoWinchSubsystem();
    	containerGrabberSubsystem = new ContainerGrabberSubsystem();
    	//cameraSubsystem = new CameraSubsystem();
    	oi = new OI();
    	
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
//        // schedule the autonomous command (example)
    	autoMode = (String) autoChooser.getSelected();
    	driveLeftEncoderState = false;
    	driveRightEncoderState = false;
    	chainEncoderState = false;
    	
    	//obviously names will be changed
    	switch (autoMode){
    	case "debugger mode":
    		autoCommand = new Debug();
    		break;
    	case "winch in totes":
    		autoCommand = new AutoWinchAndDrive();
    		break;
    	case "container and totes":
    		autoCommand = new AutoFullContainerAndToteSequence();
    		break;
    	case "just tote":
    		autoCommand = new AutoContainerOnly();
    		break;
    	case "none":
    		autoCommand = null;
    		break;
    	}
////    	
//    	
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
        	if (drivetrainSubsystem.encoders[0].get() != 0){
        		driveLeftEncoderState = true;
        	}
        	if (drivetrainSubsystem.encoders[1].get() != 0){
        		driveRightEncoderState = true;
        	}
        	if (chainLiftSubsystem.getHeight() != 0){
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
        SmartDashboard.putNumber("Chain Height", chainLiftSubsystem.getHeight());
        new DriveForwardRotate(oi.driver.getDriveForward(), oi.driver.getDriveRotation()).start();
        if(oi.operator.purgeButton.get()) {
        	new RunRoller(IntakeLeftSubsystem.PURGE_ROLLER_SPEED).start();;
        }
        if(oi.operator.intakeButton.get()) {
        	new IntakeTote().start();
        }
        if(oi.operator.scoreAllButton.get()) {
        	new ScoreTotesOnPlatform().start();
        }
        if(oi.operator.raiseToteButton.get()) {
        	new FullRaiseTote(ChainLiftSubsystem.PIDConstants.UP).start();
        }
        if(oi.operator.lowerToteButton.get()) {
        	new FullRaiseTote(ChainLiftSubsystem.PIDConstants.DOWN).start();
        }
        if(oi.operator.containerButton.get()) {
        	new FullContainerAndFirstToteSequence().start();
        }
        if(oi.operator.stepButton.get()) {
        	new ChangeOffsetHeight(ChainLiftSubsystem.PIDConstants.STEP_HEIGHT);
        }
        if(oi.operator.storeButton.get()) {
        	new ChangeOffsetHeight(ChainLiftSubsystem.PIDConstants.PLATFORM_HEIGHT);
        }
        //if(oi.operator.)
        if(oi.operator.isGrabArmState()) {
        	new SetIntakeArmPosition(IntakeLeftSubsystem.PIDConstants.GRABBING_STATE);
        }
        if(oi.operator.isReleaseArmState()) {
        	new SetIntakeArmPosition(IntakeLeftSubsystem.PIDConstants.RELEASING_STATE);
        }
        if(oi.operator.isStoreArmState()) {
        	new SetIntakeArmPosition(IntakeLeftSubsystem.PIDConstants.STORE_STATE);
        }
        
        
        /****************MANUAL**********************/
        
        chainLiftSubsystem.setPower((oi.manualJoystick.getAxis(Joystick.AxisType.kY)));
        
        if(oi.manual.moveArmsIn.get()) {
        	intakeLeftSubsystem.arm.set(0.4);
        	intakeRightSubsystem.arm.set(0.4);
        	//setIntakeArmsPower(INTAKE_ARM_IN_POWER);
        } else if(oi.manual.moveArmsOut.get()) {
        	intakeLeftSubsystem.arm.set(-0.5);
        	intakeRightSubsystem.arm.set(-0.5);
        } else {
        	intakeLeftSubsystem.arm.set(0.0);
        	intakeRightSubsystem.arm.set(0.0);
        }
        
        if(oi.manual.runAutoWinch.get()) {
        	autoWinchSubsystem.setPower(1.0);
        } else {
        	autoWinchSubsystem.setPower(0);
        }
        
        if(oi.manual.runRollersIn.get()) {
        	intakeLeftSubsystem.roller.set(IntakeLeftSubsystem.INTAKE_ROLLER_SPEED);
        	intakeRightSubsystem.roller.set(IntakeRightSubsystem.INTAKE_ROLLER_SPEED);
        } else if(oi.manual.runRollersOut.get()) {
        	intakeLeftSubsystem.roller.set(IntakeLeftSubsystem.PURGE_ROLLER_SPEED);
        	intakeRightSubsystem.roller.set(IntakeRightSubsystem.PURGE_ROLLER_SPEED);
        } else {
        	intakeLeftSubsystem.roller.set(IntakeLeftSubsystem.INTAKE_ROLLER_OFF_SPEED);
        	intakeRightSubsystem.roller.set(IntakeRightSubsystem.INTAKE_ROLLER_OFF_SPEED);

        }
        
        if(oi.manual.togglePiston.get() && oi.manual.togglePiston.get() != containerGrabberSubsystem.grabberStateBooleanForManualOnly) {
        	containerGrabberSubsystem.grabberStateBooleanForManualOnly = !containerGrabberSubsystem.grabberStateBooleanForManualOnly;
        	containerGrabberSubsystem.setGrabberState(containerGrabberSubsystem.grabberStateBooleanForManualOnly ? Value.kForward: Value.kReverse);
        }
        
        SmartDashboard.putData("Chain Encoder", chainLiftSubsystem.encoders[0]);
        SmartDashboard.putData("Drive Encoder Left", drivetrainSubsystem.encoders[0]);
        SmartDashboard.putData("Drive Encoder Right", drivetrainSubsystem.encoders[1]);
    }
    

	/**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        
    }
}
