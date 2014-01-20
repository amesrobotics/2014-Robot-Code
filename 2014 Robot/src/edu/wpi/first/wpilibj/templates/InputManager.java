/*This class should hold all code, classes and methods for managing all inputs into the system, this includes buttons, joysticks, and other
 * user interface devices. The class should handle, and manipulate these inputs into data to be sent to other parts of the robot. In most
 * cases this class should only manage and create instructions for hardware, these instructions should be sent elswhere before being fed to hardware.
 * For example, this class should get input from the joysticks, organize it, prepare it, but never call on an actuall motor to do any action. Instead
 * send it to a motor controlling class that will take that data and use it appropriately
 */
package edu.wpi.first.wpilibj.templates;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Joystick;

public class InputManager {
   protected static RobotMap R;
    protected static Joystick ps2cont;
    protected static button Stop;
    protected static button rb;
    protected static button lb;
    protected static button m9;
    protected static button m10;
    protected static button R2;
    
    void init(){
        ps2cont = new Joystick(1);
        Stop = new button(R.topb, true);
        rb = new button(R.rb, true);
        lb = new button(R.lb,true);
        m9 = new button(R.M9,true);
        m10 = new button(R.M10, true);
        R2 = new button(R.rf, true);
        RobotMap R = new RobotMap();
        
        
        
    }
    
    public double[] getFinalAxis(boolean turbo){
        double[] drv = new double[4];
       drv = ramp(getPureAxis(), turbo);
        return (drv);
    }
    public double[] getPureAxis() {
        double[] dir = new double[4];
        dir[0] = -ps2cont.getRawAxis(2);// Y1
        dir[1] = ps2cont.getRawAxis(4);// Y2

        dir = deadZone(dir);
        return (dir); // Returns axis data to the caller.
    }
    
    protected static double[] deadZone(double[] axis) {// Checks for deadzone
        //This is a skeleton of the deadzone funtion. Mark should fill this in.

        for (byte si = 0; si < axis.length; si++) {//loops through the array.
            if (axis[si] <= .05 && axis[si] >= -.05) {
                axis[si] = 0;
            }
        }
        return (axis);
    }
    protected static double[] ramp(double [] axis, boolean turbo){
        for (byte ri = 0; ri < axis.length; ri++) {
            //axis[ri] = MathUtils.pow(axis[ri], rm.expo_ramp);
            if(turbo){
            axis[ri] = (((.666) * MathUtils.pow(axis[ri], R.expo_ramp)) + ((.333) * axis[ri]))*1;
            }
            else{
                axis[ri] = (((.666) * MathUtils.pow(axis[ri], R.expo_ramp)) + ((.333) * axis[ri]))*.5;
            }
        }
        return(axis);
    }
    protected static double[] normalize(double [] axis){
        if((Math.abs((axis[0] - axis[1])) <= R.normalThresh) & (axis[0] * axis[1]) > 0){
            double tinydbl = (axis[0] + axis[1])/2;
            axis[0] = (tinydbl);
            axis[1] = (tinydbl);
        }
        return axis;
    }
    
    protected static class button {

        boolean state;
        boolean laststate;
        boolean bjoystick;
        int bpin;

        public button(int pin, boolean joystick) {
            bjoystick = joystick;
            bpin = pin;
        }

        public boolean getState() {
            // true: first joystick, false: second joystick
            laststate = state;
            if (bjoystick) {
                state = ps2cont.getRawButton(this.bpin);
            } else {
                //state = monoJoystick.getRawButton(this.bpin);
            }
            return state;
        }
    }
    
    
}
