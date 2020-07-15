package org.firstinspires.ftc.teamcode.shared;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.TelemetryMessage;

import static org.firstinspires.ftc.teamcode.shared.FTCUtil.hardwareMap;

public class Chassis {
    private static final double COUNTS_PER_INCH = 0;
    private DcMotor front_left_motor, front_right_motor, back_left_motor, back_right_motor;
    public Chassis(){
        // Tell the driver that initialization is complete.
        FTCUtil.telemetry.addData("Status", "Initialized");

        // Initialize the hardware variables
        front_left_motor = hardwareMap.get(DcMotor.class,"front_left_motor");
        front_right_motor = hardwareMap.get(DcMotor.class,"front_right_motor");
        back_left_motor = hardwareMap.get(DcMotor.class,"back_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class,"back_right_motor");

        front_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



    }
    public void drive(double inches, double motorPower){


        int newFLTarget = front_left_motor.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
        int newBLTarget = back_left_motor.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
        int newFRTarget = front_right_motor.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);
        int newBRTarget = back_right_motor.getCurrentPosition() + (int) (inches * COUNTS_PER_INCH);

       front_right_motor.setTargetPosition(newFLTarget);
       back_left_motor.setTargetPosition(newBLTarget);
       front_right_motor.setTargetPosition(newFRTarget);
       front_left_motor.setTargetPosition(newBRTarget);
       front_left_motor.setPower(motorPower);
       back_left_motor.setPower(motorPower);
       front_right_motor.setPower(motorPower);
       back_right_motor.setPower(motorPower);

       while(front_right_motor.isBusy() && front_left_motor.isBusy() && back_left_motor.isBusy() && back_right_motor.isBusy()){
           // do nothing
           try {
               Thread.sleep(20);
           } catch (InterruptedException e) {
           }
       }

    }
}
