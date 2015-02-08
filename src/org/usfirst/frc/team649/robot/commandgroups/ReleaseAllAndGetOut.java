package org.usfirst.frc.team649.robot.commandgroups;

import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveSetDistanceWithPID;
import org.usfirst.frc.team649.robot.commands.lift.ScoreAllTotesAndResetEncoders;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.EncoderBasedDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ReleaseAllAndGetOut extends CommandGroup {
    
    public  ReleaseAllAndGetOut() {
    	addSequential(new ScoreAllTotesAndResetEncoders());
    	addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.UNHOOK_BACKWARDS_DISTANCE));

    }
}
