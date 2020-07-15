package org.firstinspires.ftc.teamcode.shared;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveWheel {
    private static final double ticksPerInch = 360;
    private DcMotor dcMotor;
    private String deviceName;

    public void setPower(double power){
        dcMotor.setPower(power);
    }

    public void setTarget(double inches){
        int newFLTarget = dcMotor.getCurrentPosition() + (int) (inches * ticksPerInch);
        dcMotor.setTargetPosition(newFLTarget);
    }

}
