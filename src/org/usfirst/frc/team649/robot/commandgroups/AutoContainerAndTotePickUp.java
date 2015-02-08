package org.usfirst.frc.team649.robot.commandgroups;

import org.usfirst.frc.team649.robot.commands.containergrabbercommands.ClampContainerGrabber;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveSetDistanceWithPID;
import org.usfirst.frc.team649.robot.commands.lift.ScoreAllTotesAndResetEncoders;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.EncoderBasedDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoContainerAndTotePickUp extends CommandGroup {
    
	 //part of full sequence
    //pick up container and tote only, separation need bc of parallel v sequential issues...fix this TODO
    public AutoContainerAndTotePickUp() {
    	addSequential(new ClampContainerGrabber(false));
    	addSequential(new ScoreAllTotesAndResetEncoders());
    	addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_START_TO_CONTAINER));
    	addSequential(new WaitCommand(300));
    	addSequential(new FullContainerAndFirstToteSequence());
    	//go forward and pick up the tote
    	addParallel(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_CONTAINER_TO_TOTE));
    	//tofinish?
    }
}
