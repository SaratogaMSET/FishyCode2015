package org.usfirst.frc.team649.robot.commands;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public static class CommandBase{

    public static void init(){
    }
	
    //pick up totes with one call (based on one level pick up design which i love)
    public static Command pickUpTote(int numTotes){
    	Command sequence = new CommandGroup();
    	for(int i = 0; i< numTotes; i++){
    		//pick each individual tote
    	}
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
    
=======
import org.usfirst.frc.team649.robot.OI;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveForwardRotate;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandBase extends Command {

	public static OI oi;
	public static DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
	
	
	public Command driveForwardRotate(double forward, double rotate){
		return new DriveForwardRotate(forward, rotate);
	}
	
	
    public CommandBase() {
        super();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
>>>>>>> branch 'master' of https://github.com/SaratogaMSET/FishyCode2015.git
}
