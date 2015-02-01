package org.usfirst.frc.team649.robot.commands;
import java.awt.ContainerOrderFocusTraversalPolicy;

import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.autowinchcommands.WinchTotesIn;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveSetDistanceWithPIDCommand;
import org.usfirst.frc.team649.robot.commands.grabbercommands.GrabberArmPosition;
import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.grabbercommands.RunRoller;
import org.usfirst.frc.team649.robot.commands.lift.ChangeLevelOfTotes;
import org.usfirst.frc.team649.robot.commands.lift.ChangeOffsetHeight;
import org.usfirst.frc.team649.robot.commands.lift.ScoreAllTotesAndResetEncoders;
import org.usfirst.frc.team649.robot.subsystems.AutoWinchSubsystem;
import org.usfirst.frc.team649.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.DriveDistanceConstants;
import org.usfirst.frc.team649.robot.subsystems.GrabberLeftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.GrabberRightSubsystem;

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
	public GrabberLeftSubsystem grabberLeftSubsystem = new GrabberLeftSubsystem();
	public GrabberRightSubsystem grabberRightSubsystem = new GrabberRightSubsystem();
	public AutoWinchSubsystem autoWinchSubsystem = new AutoWinchSubsystem();
	//probably wont end up using this at all
	public CameraSubsystem cameraSubsystem = new CameraSubsystem();
	
	public CommandBase() {
	}
	
	public  Command driveForwardRotate(double forward, double rotate){
		return new DriveForwardRotate(forward, rotate);
	}
	
	public Command changeLevelOfTotes(boolean up) {
		return new ChangeLevelOfTotes(up);
	}
	
	public Command changeOffSetHeight(boolean storeHeight) {
		return new ChangeOffsetHeight(storeHeight);
	}
	
	//Suneel was here
	public Command setArmPosition(int state){
		return new GrabberArmPosition(state);
	}
	
	public Command intakeTote(){
		return new IntakeTote();
	}
	
	public Command purgeTote(int power){
		return new RunRoller(power);
	}
	
	public Command releaseAndGetOut(){
		CommandGroup sequence = new CommandGroup();
		
		sequence.addSequential(new ScoreAllTotesAndResetEncoders());
    	sequence.addSequential(new DriveSetDistanceWithPIDCommand(DriveDistanceConstants.UNHOOK_BACKWARDS_DISTANCE));
    	
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
    	sequence.addSequential(releaseAndGetOut());
    	
    	return sequence;
    }
    
    //idk if this will happen
    public Command pickUpFirstToteAfterContainer(){
    	CommandGroup sequence = new CommandGroup();
    	return sequence;
    }
    
    public Command debug(){
    	CommandGroup sequence = new CommandGroup();
    	//for raw motor
    	sequence.addSequential(new RawMotor(0.4, 0.4));
    	sequence.addSequential(new WaitCommand(1000));
    	sequence.addSequential(new RawMotor(0,0));
    	//for chain
    	sequence.addSequential(new RawMotor(0.5));
    	sequence.addSequential(new WaitCommand(1000));
    	sequence.addSequential(new RawMotor(0));
    	return sequence;
    }
    
    public Command autoWinchAndDrive(){
    	CommandGroup sequence = new CommandGroup();
    	
    	//move arms out (state position), winch in the three containers, drive forward, all sequential
    	sequence.addSequential(new GrabberArmPosition(2));
    	sequence.addSequential(new WinchTotesIn());
    	sequence.addSequential(new DriveSetDistanceWithPIDCommand(DriveDistanceConstants.AUTO_DRIVE_DISTANCE));
    	return sequence;
    	
    } 
}
