package org.usfirst.frc.team649.robot.commands;

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
    
}
