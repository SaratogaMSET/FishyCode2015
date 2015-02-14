package org.usfirst.frc.team649.robot.commandgroups;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.commands.containergrabbercommands.ClampContainerGrabber;
import org.usfirst.frc.team649.robot.commands.grabbercommands.IntakeTote;
import org.usfirst.frc.team649.robot.commands.lift.ChangeLiftHeight;
import org.usfirst.frc.team649.robot.commands.lift.FinishRaiseTote;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class FullContainerAndFirstToteSequence extends CommandGroup {

	public FullContainerAndFirstToteSequence() {
		
		//TODO: STILL NEED?
//		if (pickUpContainer() == null) {
//			// Do nothings
//		}
		addSequential(new PickUpContainer());
		// NOW CHECK FOR BUTTONS

		// wait until intake button pressed...check for problems with multiple
		// systems TODO
		while (!FishyRobot2015.oi.operator.purgeButton.get()) {

		}

		// pull in tote and unclamp
		addSequential(new IntakeTote());
		addSequential(new WaitCommand(.2));
		addSequential(new ClampContainerGrabber(false));
		addSequential(new WaitCommand(.1));
		// go down and regrip
		addSequential(new ChangeLiftHeight(ChainLiftSubsystem.PIDConstants.CONTAINER_REGRIP_LOWER_HEIGHT));
		addSequential(new WaitCommand(.2));
		addSequential(new ClampContainerGrabber(true));
		// continue to drive height offset (you should be at intermediate step
		// here), hopefully you catch the tote
		addSequential(new WaitCommand(.2));
		addSequential(new FinishRaiseTote(ChainLiftSubsystem.PIDConstants.UP));
	}
}
