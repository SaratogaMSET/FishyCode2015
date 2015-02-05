package org.usfirst.frc.team649.robot.commands;

import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.autowinchcommands.WinchTotesIn;
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
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ContainerGrabberSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.DriveDistanceConstants;
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
	
	public CommandBase() {
		
	}
	
	public Command releaseAndGetOut(){
		CommandGroup sequence = new CommandGroup();
		
		sequence.addSequential(new ScoreAllTotesAndResetEncoders());
    	sequence.addSequential(new DriveSetDistanceWithPID(DriveDistanceConstants.UNHOOK_BACKWARDS_DISTANCE));
    	
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
    	sequence.addSequential(new DriveSetDistanceWithPID(DriveDistanceConstants.AUTO_DRIVE_DISTANCE));
    	return sequence;
    	
    } 
    
	//DRIVETRAIN
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
