package org.usfirst.frc.team649.robot.commandgroups;

import org.usfirst.frc.team649.robot.commands.drivetraincommands.DriveSetDistanceWithPID;
import org.usfirst.frc.team649.robot.commands.drivetraincommands.TurnWithPIDCommand;
import org.usfirst.frc.team649.robot.commands.lift.FinishRaiseTote;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.EncoderBasedDriving;
import org.usfirst.frc.team649.robot.subsystems.DrivetrainSubsystem.GyroBasedDriving;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoFullContainerAndToteSequence extends CommandGroup {
    
    public  AutoFullContainerAndToteSequence() {
    	addSequential(new AutoContainerAndTotePickUp());
    	addSequential(new WaitCommand(400));
    	addSequential(new TurnWithPIDCommand(GyroBasedDriving.AUTO_GRYO_TURN_ANGLE));
    	addSequential(new DriveSetDistanceWithPID(EncoderBasedDriving.AUTO_CONTAINER_TO_AUTO_ZONE));
    	//drop the tote, but keep holding container up
    	//Say what? We have the Tote at this point?
    	addSequential(new FinishRaiseTote(false)); //check this TODO

    }
}
