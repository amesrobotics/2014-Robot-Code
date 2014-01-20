/*
 */

//Blarg

package edu.wpi.first.wpilibj.templates;

/**
 * This class should hold all code, classes and methods for managing all inputs into the system, this includes buttons, joysticks, and other user interface devices. 
 * <p>The class should handle, and manipulate these inputs into data to be sent to other parts of the robot. In most cases this class should only manage and create instructions for hardware, these instructions should be sent elsewhere before being fed to hardware.
 * <p>For example, this class should get input from the joysticks, organize it, prepare it, but never call on an actual motor to do any action. 
 * Instead, send it to a motor controlling class that will take that data and use it appropriately.
 * @author Erin Turnley
 */

public class InputManager {
    public final int LEFT_X=1,LEFT_Y=2,RIGHT_X=3,RIGHT_Y=4,SHOOT=1,PASS=2; //Placeholder pin number
    public double getAxis(int pin) {
        return 0;
    }
    public boolean getButton(int pin) {
        return false;
    }
}
