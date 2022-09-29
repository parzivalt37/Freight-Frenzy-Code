package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous
public class AutoWithCamera extends LinearOpMode{
    //camera declarations
    private OpenCvCamera webcam;
    private DuckPipeline pipeline;
    DuckPipeline.DuckPosition snapshotAnalysis = DuckPipeline.DuckPosition.LEFT;
    
    //motor declarations
    private DcMotor fl;
    private DcMotor fr;
    private DcMotor bl;
    private DcMotor br;
    private DcMotor Carousel;
    
    @Override
    public void runOpMode() {
        //camera initializations
        WebcamName webcamName = hardwareMap.get(WebcamName.class, "webcam");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
        pipeline = new DuckPipeline(telemetry);
        webcam.setPipeline(pipeline);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError (int errorCode) { }
        });
        
        //motor initializations
        fl = hardwareMap.get(DcMotor.class, "frontLeft");
        fr = hardwareMap.get(DcMotor.class, "frontRight");
        bl = hardwareMap.get(DcMotor.class, "rearLeft");
        br = hardwareMap.get(DcMotor.class, "rearRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        //setting motor directions
        fr.setDirection(DcMotor.Direction.REVERSE);
        br.setDirection(DcMotor.Direction.REVERSE);
        
        
        while(!isStarted() && !isStopRequested()) {
            //shows real time analysis of the image on the camera
            telemetry.addData("Real time analysis: ", pipeline.getAnalysis());
            telemetry.update();
            
            sleep(400);
        }
        
        snapshotAnalysis = pipeline.getAnalysis();
        
        telemetry.addData("Final analysis: ", snapshotAnalysis);
        telemetry.update();
        
        
        switch(snapshotAnalysis) {
            case LEFT:
                //auton code
                break;
            case CENTER:
                //auton code
                break;
            case RIGHT:
                //auton code
                break;
        }
    }
}
