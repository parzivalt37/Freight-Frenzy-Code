package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import java.lang.Thread;

//THIS CLASS IS PROGRAMMED FOR MECANUM WHEELS

@TeleOp
public class StrafeTest extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor rearLeft;
    private DcMotor rearRight;
    private DcMotor Carousel;
    private DcMotor arm;

    private double power;
    @Override
    public void runOpMode() {
        
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        arm = hardwareMap.get(DcMotor.class, "arm");
        
        
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        rearRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        
        while(opModeIsActive()) {
            
        
            
            
            //Left Stick: General driving, includes straffing
            frontLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(gamepad1.left_stick_y);
            rearRight.setPower(-gamepad1.left_stick_y);
            rearLeft.setPower(-gamepad1.left_stick_y);
            
            frontLeft.setPower(-gamepad1.left_stick_x);
            frontRight.setPower(gamepad1.left_stick_x);
            rearRight.setPower(-gamepad1.left_stick_x);
            rearLeft.setPower(gamepad1.left_stick_x);
            
            //Right Stick: Robot rotation
            frontLeft.setPower(-gamepad1.right_stick_x);
            frontRight.setPower(gamepad1.right_stick_x);
            rearRight.setPower(gamepad1.right_stick_x);
            rearLeft.setPower(-gamepad1.right_stick_x); 
            
            //Carousel functions
            if (gamepad1.a) {
                Carousel.setPower(0.4);
            } else if (gamepad1.b) {
                Carousel.setPower(-0.4);
            } else {
                Carousel.setPower(0);
            }
            
            
            //update telemetry
            telemetry.update();
        }
    }
}