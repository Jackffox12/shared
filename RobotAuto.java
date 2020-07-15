
package org.firstinspires.ftc.teamcode.shared;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "RobotAuto")
//@Disabled
public class RobotAuto extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor front_left_motor, front_right_motor, back_left_motor, back_right_motor;
    private Servo ws, gs, fs;

    private Robot robot;


    @Override
    public void init() {
         robot = new Robot();
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

    }
}

