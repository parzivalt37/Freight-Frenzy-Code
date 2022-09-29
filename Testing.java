package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class Testing extends LinearOpMode {
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor rl;
    private DcMotor rr;
    
    @Override
    public void runOpMode() {
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        rl = hardwareMap.get(DcMotor.class, "rearLeft");
        rr = hardwareMap.get(DcMotor.class, "rearRight");
        
        fr.setDirection(DcMotor.Direction.REVERSE);
        rr.setDirection(DcMotor.Direction.REVERSE);
        
        waitForStart();
        
        while(opModeIsActive()) {
            fl.setPower(-gamepad1.left_stick_y);
            fr.setPower(-gamepad1.left_stick_y);
            rr.setPower(-gamepad1.left_stick_y);
            rl.setPower(-gamepad1.left_stick_y);
            
            fl.setPower(gamepad1.left_stick_x);
            fr.setPower(-gamepad1.left_stick_x);
            rr.setPower(gamepad1.left_stick_x);
            rl.setPower(-gamepad1.left_stick_x);
            
            //Right Stick: Robot rotation
            fl.setPower(gamepad1.right_stick_x);
            fr.setPower(-gamepad1.right_stick_x);
            rr.setPower(-gamepad1.right_stick_x);
            rl.setPower(gamepad1.right_stick_x); 
        }
        
    }
    
}