
package org.firstinspires.ftc.teamcode.shared;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.concurrent.BrokenBarrierException;

/**
 * Demonstrates empty OpMode
 */
@TeleOp(name = "ProblemSet5Tele")
//@Disabled
public class ProblemSet5Tele extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor front_left_motor, front_right_motor, back_left_motor, back_right_motor;
    private Servo ws, gs, fs;


    @Override
    public void init() {
        // Initialize the hardware variables
        front_left_motor = hardwareMap.get(DcMotor.class,"front_left_motor");
        front_right_motor = hardwareMap.get(DcMotor.class,"front_right_motor");
        back_left_motor = hardwareMap.get(DcMotor.class,"back_left_motor");
        back_right_motor = hardwareMap.get(DcMotor.class,"back_right_motor");

        front_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //ws = hardwareMap.get(Servo.class,"ws");
        // gs = hardwareMap.get(Servo.class,"gs");
        // fs = hardwareMap.get(Servo.class, "fs");



        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

    }


    @Override
    public void init_loop() {
    }

    /*
     * This method will be called ONCE when start is pressed
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * This method will be called repeatedly in a loop
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#loop()
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        front_left_motor.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
        front_right_motor.setPower(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
        back_left_motor.setPower(-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
        back_right_motor.setPower(-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);


        //Power settings for the robot to strafe
        //front_left_motor.setPower(-gamepad1.right_stick_x);
        //front_right_motor.setPower(gamepad1.right_stick_x);
        //back_left_motor.setPower(gamepad1.right_stick_x);
        // back_right_motor.setPower(-gamepad1.right_stick_x);

    }
}

