package org.usfirst.frc.team649.robot.commands;
import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public abstract class CommandBase extends Command {

	public static OI oi;
	public static DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
	
	
	public static Command driveForwardRotate(double forward, double rotate){
		return new DriveForwardRotate(forward, rotate);
	}
	
	//pick up totes with as many calls as you need
    public static Command pickUpTote(){
    	Command sequence = new CommandGroup();
    	//do whatever
    	return sequence;
    }
    
    //might just be a simple drive command or more based on final design
    public static Command releaseTote(){
    	//using crate design
    	Command sequence = new CommandGroup();
    	//add whatever
    	//unlatch and drive backwards
    	return sequence;
    }
}
