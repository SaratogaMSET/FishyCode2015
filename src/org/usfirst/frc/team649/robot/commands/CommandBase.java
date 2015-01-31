package org.usfirst.frc.team649.robot.commands;
import java.awt.ContainerOrderFocusTraversalPolicy;

import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.commands.grabbercommands.GrabberArmPosition;
import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.grabbercommands.RunRoller;
import org.usfirst.frc.team649.robot.commands.lift.ChangeLevelOfTotes;
import org.usfirst.frc.team649.robot.commands.lift.ChangeOffsetHeight;
import org.usfirst.frc.team649.robot.subsystems.AutoWinchSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
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
	
	public Command purgeTote(){
		return new RunRoller(-1.0);
	}
	
	//pick up totes with as many calls as you need
    public Command scoreTotesOnPlatform(){
    	CommandGroup sequence = new CommandGroup();
    	//do whatever
    	return sequence;
    }
    
    //might just be a simple drive command or more based on final design
    public Command scoreTotesOnStep(){
    	//using crate design
    	CommandGroup sequence = new CommandGroup();
    	//add whatever
    	//unlatch and drive backwards
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
}
