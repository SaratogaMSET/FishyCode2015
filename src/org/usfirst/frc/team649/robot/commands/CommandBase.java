package org.usfirst.frc.team649.robot.commands;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.autowinchcommands.WinchTotesIn;
import org.usfirst.frc.team649.robot.commands.containerGrabberCommands.ClampContainerGrabber;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveSetDistanceWithPID;
import org.usfirst.frc.team649.robot.commands.grabbercommands.GrabberArmPosition;
import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.grabbercommands.RunRoller;
import org.usfirst.frc.team649.robot.commands.lift.ChangeLiftHeight;
import org.usfirst.frc.team649.robot.commands.lift.FinishRaiseTote;
import org.usfirst.frc.team649.robot.commands.lift.RaiseToteToIntermediateLevel;
import org.usfirst.frc.team649.robot.commands.lift.ChangeOffsetHeight;
import org.usfirst.frc.team649.robot.commands.lift.ScoreAllTotesAndResetEncoders;
import org.usfirst.frc.team649.robot.subsystems.AutoWinchSubsystem;
import org.usfirst.frc.team649.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ContainerGrabberSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.EncoderBasedDriving;
import org.usfirst.frc.team649.robot.subsystems.IntakeLeftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeRightSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;


/**
 *
 */
public class CommandBase {

	public OI oi = new OI();
	public DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
	public ChainLiftSubsystem chainLiftSubsystem = new ChainLiftSubsystem();
	public IntakeLeftSubsystem intakeLeftSubsystem = new IntakeLeftSubsystem();
	public IntakeRightSubsystem intakeRightSubsystem = new IntakeRightSubsystem();
	public AutoWinchSubsystem autoWinchSubsystem = new AutoWinchSubsystem();
	public ContainerGrabberSubsystem containerGrabberSubsystem = new ContainerGrabberSubsystem();
	public CameraSubsystem cameraSubsystem = new CameraSubsystem();
	
	public CommandBase() {
		
	}
	
	//******** BASIC TELEOP COMMAND GROUPS ********//
	
	public Command releaseAllAndGetOut(){
		CommandGroup sequence = new CommandGroup();
		
		sequence.addSequential(new ScoreAllTotesAndResetEncoders());
    	sequence.addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.UNHOOK_BACKWARDS_DISTANCE));
    	
    	return sequence;
	}
	
	//getting the robot to be set up for scoring on step 
	//(not part of command) followed by manual drive forward, followed by manual release
    public Command goToTotesOnStepPosition(){
    	CommandGroup sequence = new CommandGroup();
    	//change to step offset if not already there
    	if (chainLiftSubsystem.platformOrStepOffset){
    		sequence.addSequential(new ChangeOffsetHeight(false)); //automatically updates the internal state variable
    	}
    	sequence.addParallel(new GrabberArmPosition(2));
    	//do whatever
    	return sequence;
    }
    
    //scoring on platform when ready, all in one,
    public Command scoreTotesOnPlatform(){
    	CommandGroup sequence = new CommandGroup();
    	
    	//change to platform offset if not already there
    	if (!chainLiftSubsystem.platformOrStepOffset){
    		sequence.addSequential(new ChangeOffsetHeight(true)); //automatically updates the internal state variable
    	}
    	sequence.addParallel(new GrabberArmPosition(1));
    	sequence.addSequential(releaseAllAndGetOut());
    	
    	return sequence;
    }
    
    //assumes you are there
    //DO A NULL POINTER CHECK
    public Command pickUpContainer(){
    	CommandGroup sequence = new CommandGroup();
    	//if not at base, exit because we cant pick up a container
    	//NULL POINTER CHECK BECAUSE OF THIS
    	if (!FishyRobot2015.commandBase.chainLiftSubsystem.isAtBase){
    		return null;
    	}
    	
    	//otherwise actually raise it
    	sequence.addSequential(new ClampContainerGrabber(true));
    	sequence.addSequential(new WaitCommand(200));
    	sequence.addSequential(new ChangeLiftHeight(ChainLiftSubsystem.PIDConstants.CONTAINER_PICK_UP_RAISE_HEIGHT));
    	return sequence;
    }
    
    //called for the full sequence from picking up container to picking up tote afterwards
    public Command fullContainerAndFirstToteSequence(){
    	CommandGroup sequence = new CommandGroup();
    	//exit if we are not in the right place...put debugs in here pls
    	if (pickUpContainer() == null){
    		return sequence;
    	}
    	sequence.addSequential(pickUpContainer());
    	//NOW CHECK FOR BUTTONS
    	
    	//wait until intake button pressed...check for problems with multiple systems TODO
    	while (!FishyRobot2015.commandBase.oi.operator.isIntakeButtonPressed()){
    		
    	}
    	//pull in tote and unclamp
    	sequence.addSequential(new IntakeTote());
    	sequence.addSequential(new WaitCommand(200));
    	sequence.addSequential(new ClampContainerGrabber(false));
    	sequence.addSequential(new WaitCommand(100));
    	//go down and regrip
    	sequence.addSequential(new ChangeLiftHeight(ChainLiftSubsystem.PIDConstants.CONTAINER_REGRIP_LOWER_HEIGHT));
    	sequence.addSequential(new WaitCommand(200));
    	sequence.addSequential(new ClampContainerGrabber(true));
    	//continue to drive height offset (you should be at intermediate step here), hopefully you catch the tote
    	sequence.addSequential(new WaitCommand(200));
    	sequence.addSequential(new FinishRaiseTote(true));
    	return sequence;
    }

    //******** AUTONOMOUS ********//
    
    //runs all motors and displays their encoder values for debugging
    public Command debug(){
    	CommandGroup sequence = new CommandGroup();
    	//for raw motor
    	sequence.addSequential(new RawMotor(0.4, 0.4));
    	sequence.addSequential(new WaitCommand(1000));
    	sequence.addSequential(new RawMotor(0,0));
    	
    	sequence.addSequential(new WaitCommand(1000));
    	
    	//for chain
    	sequence.addSequential(new RawMotor(0.5));
    	sequence.addSequential(new WaitCommand(1000));
    	sequence.addSequential(new RawMotor(0));
    	return sequence;
    	//suneel was here...and everywhere
    }
    
    // winch all in and then run out of there man!!!! LOLOLOLOLOLOLOLOL k bye
    public Command autoWinchAndDrive(){
    	CommandGroup sequence = new CommandGroup();
    	
    	//move arms out (state position), winch in the three containers, drive forward, all sequential
    	sequence.addSequential(new GrabberArmPosition(2));
    	sequence.addSequential(new WinchTotesIn());
    	sequence.addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_WINCH_DRIVE_DISTANCE));
    	return sequence;
    	
    } 
    
    //part of full sequence
    //pick up container and tote only, separation need bc of parallel v sequential issues...fix this TODO
    public Command autoContainerAndTotePickUp(){
    	CommandGroup sequence = new CommandGroup();
    	//drive to Container and clamp after making sure its not already clamped
    	sequence.addSequential(new ClampContainerGrabber(false));
    	sequence.addSequential(new ScoreAllTotesAndResetEncoders());
    	sequence.addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_START_TO_CONTAINER));
    	sequence.addSequential(new WaitCommand(300));
    	sequence.addSequential(fullContainerAndFirstToteSequence());
    	//go forward and pick up the tote
    	sequence.addParallel(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_CONTAINER_TO_TOTE));
    	//god loves you
    	return sequence;
    }
    
    //pick up container and then tote and go away
    //this is the one you call, split up for reasons above
    public Command autoFullContainerAndToteSequence(){
    	CommandGroup sequence = new CommandGroup();
    	
    	sequence.addSequential(autoContainerAndTotePickUp());
    	sequence.addSequential(new WaitCommand(400));
    	//insert turn command TODO
    	sequence.addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_CONTAINER_TO_AUTO_ZONE));
    	//drop the tote, but keep holding container up
    	sequence.addSequential(new FinishRaiseTote(false)); //check this TODO
    	return sequence;
    }
    
    public Command autoContainerOnly(){
    	CommandGroup sequence = new CommandGroup();
    	
    	sequence.addSequential(new ClampContainerGrabber(false));
    	sequence.addSequential(new ScoreAllTotesAndResetEncoders());
    	sequence.addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_START_TO_CONTAINER));
    	sequence.addSequential(new WaitCommand(300));
    	sequence.addSequential(pickUpContainer());
    	//insert turn command
    	sequence.addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_CONTAINER_TO_AUTO_ZONE));
    	//keep holding tote
    	
    	return sequence;
    }
    
    //******** COMMANDS ********//
    
    //DRIVERTRAIN
	public  Command driveForwardRotate(double forward, double rotate){
		return new DriveForwardRotate(forward, rotate);
	}
	
	public Command driveSetDistanceWithPID(double distance) {
		return new DriveSetDistanceWithPID(distance);
	}
	
	public Command driveSetDistanceWithPID(double distance, double minDriveSpeed) {
		return new DriveSetDistanceWithPID(distance, minDriveSpeed);
	}
	
	public Command driveSetDistanceWithPID(double distance, double minDriveSpeed, boolean checker) {
		return new DriveSetDistanceWithPID(distance, minDriveSpeed, checker);
	}
	
	//LIFT
	public Command raiseToteToIntermediateLevel(boolean up) {
		return new RaiseToteToIntermediateLevel(up);
	}
	
	public Command finishRaiseTote(boolean up) {
		return new FinishRaiseTote(up);
	}
	
	public Command changeLiftHeight(double height) {
		return new ChangeLiftHeight(height);
	}
	
	public Command changeOffSetHeight(boolean storeHeight) {
		return new ChangeOffsetHeight(storeHeight);
	}
	
	public Command scoreAllTotesAndResetEncoders() {
		return new ScoreAllTotesAndResetEncoders();
	}
	
	//ARM
	public Command setArmPosition(int state){
		return new GrabberArmPosition(state);
	}
	
	public Command intakeTote(){
		return new IntakeTote();
	}
	
	public Command purgeTote(double power){
		return new RunRoller(power);
	}
	

}
