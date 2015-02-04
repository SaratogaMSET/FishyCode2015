package org.usfirst.frc.team649.robot.commands;
import java.awt.ContainerOrderFocusTraversalPolicy;

import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.commands.grabbercommands.GrabberArmPosition;
import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.grabbercommands.RunRoller;
import org.usfirst.frc.team649.robot.commands.lift.RaiseToteToIntermediateLevel;
import org.usfirst.frc.team649.robot.commands.lift.ChangeOffsetHeight;
import org.usfirst.frc.team649.robot.subsystems.AutoWinchSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.ContainerGrabberSubsystem;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeLeftSubsystem;
import org.usfirst.frc.team649.robot.subsystems.IntakeRightSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CommandBase {

	public OI oI = new OI();
	public DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
	public ChainLiftSubsystem chainLiftSubsystem = new ChainLiftSubsystem();
	public IntakeLeftSubsystem grabberLeftSubsystem = new IntakeLeftSubsystem();
	public IntakeRightSubsystem grabberRightSubsystem = new IntakeRightSubsystem();
	public AutoWinchSubsystem autoWinchSubsystem = new AutoWinchSubsystem();
	public ContainerGrabberSubsystem containerGrabberSubsystem = new ContainerGrabberSubsystem();
	
	public CommandBase() {
		
	}
	
	public  Command driveForwardRotate(double forward, double rotate){
		return new DriveForwardRotate(forward, rotate);
	}
	
	public Command changeLevelOfTotes(boolean up) {
		return new RaiseToteToIntermediateLevel(up);
	}
	
	//lol
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
