package org.usfirst.frc.team649.robot.commands;
import java.awt.ContainerOrderFocusTraversalPolicy;

import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.commands.grabbercommands.GrabberArmPosition;
import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.grabbercommands.RunRoller;
import org.usfirst.frc.team649.robot.commands.lift.ChangeLevelOfTotes;
import org.usfirst.frc.team649.robot.commands.lift.ChangeOffsetHeight;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.GrabberLeftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.GrabberRightSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CommandBase {

	public OI oi;
	public DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
	public ChainLiftSubsystem chainLiftSubsystem = new ChainLiftSubsystem();
	public GrabberLeftSubsystem grabberLeftSubsystem = new GrabberLeftSubsystem();
	public GrabberRightSubsystem grabberRightSubsystem = new GrabberRightSubsystem();
	
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
    public Command ScoreTotesOnPlatform(){
    	Command sequence = new CommandGroup();
    	//do whatever
    	return sequence;
    }
    
    //might just be a simple drive command or more based on final design
    public Command ScoreTotesOnStep(){
    	//using crate design
    	Command sequence = new CommandGroup();
    	//add whatever
    	//unlatch and drive backwards
    	return sequence;
    }
}
