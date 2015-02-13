package org.usfirst.frc.team649.robot.commandgroups;

import org.usfirst.frc.team649.robot.FishyRobot2015;
import org.usfirst.frc.team649.robot.commands.lift.FinishRaiseTote;
import org.usfirst.frc.team649.robot.commands.lift.RaiseToteToIntermediateLevel;
import org.usfirst.frc.team649.robot.subsystems.ChainLiftSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FullRaiseTote extends CommandGroup {
    
    public  FullRaiseTote(boolean upOrDown) {
    	addSequential(new RaiseToteToIntermediateLevel(upOrDown));
    	while(!FishyRobot2015.intakeRightSubsystem.isLimitPressed() && !FishyRobot2015.intakeLeftSubsystem.isLimitPressed()) {
    		
    	}
    	addSequential(new FinishRaiseTote(upOrDown));
    }
}
