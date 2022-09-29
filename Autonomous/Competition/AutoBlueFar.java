package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.OpenCvInternalCamera;


@Autonomous
public class AutoBlueFar extends LinearOpMode {
    
    //hardware declarations
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor rr;
    private DcMotor rl;
    private DcMotor Carousel;
    private CRServo intArm;
    
    @Override
    public void runOpMode() {
        
        //motor initalizations
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        rl = hardwareMap.get(DcMotor.class, "rearLeft");
        rr = hardwareMap.get(DcMotor.class, "rearRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        
        fr.setDirection(DcMotor.Direction.REVERSE);
        rr.setDirection(DcMotor.Direction.REVERSE);
        intArm = hardwareMap.get(CRServo.class, "intArm");
        
        
        
        
        waitForStart();
        if (opModeIsActive()) {
            fl.setPower(-0.5);
            fr.setPower(0.5);
            rr.setPower(-0.5);
            rl.setPower(0.5);
                
            sleep(325);
                
            fl.setPower(0);
            fr.setPower(0);
            rr.setPower(0);
            rl.setPower(0);
            
            sleep(100);
            
            fl.setPower(-0.5);
            fr.setPower(-0.5);
            rl.setPower(-0.5);
            rr.setPower(-0.5);
            
            sleep(2250);
            
            fl.setPower(0);
            fr.setPower(0);
            rl.setPower(0);
            rr.setPower(0);
            
            Carousel.setPower(-0.35);
            sleep(2250);
            Carousel.setPower(0);
            
            fl.setPower(-0.5);
            fr.setPower(0.5);
            rr.setPower(-0.5);
            rl.setPower(0.5);
            
            sleep(1470);
            
            fl.setPower(-0.5);
            fr.setPower(-0.5);
            rr.setPower(-0.5);
            rl.setPower(-0.5);
            
            sleep(300);
            
            fl.setPower(0);
            fr.setPower(0);
            rr.setPower(0);
            rl.setPower(0);
        }
    }
}
