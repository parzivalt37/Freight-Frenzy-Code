package org.firstinspires.ftc.teamcode;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class CameraTestPipeline extends OpenCvPipeline {
    
    public enum DuckPosition {
        LEFT,
        CENTER,
        RIGHT
    }
    
    public final Scalar BLUE = new Scalar(0, 0, 255);
    public final Scalar GREEN = new Scalar(0, 255, 0);
    
    public static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(109,98);
    public static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(181,98);
    public static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(253,98);
    public static final int REGION_WIDTH = 20;
    public static final int REGION_HEIGHT = 20;
    
    Point region1_pointA = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x,
            REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pointB = new Point(
            REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region2_pointA = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x,
            REGION2_TOPLEFT_ANCHOR_POINT.y);
    Point region2_pointB = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region3_pointA = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x,
            REGION3_TOPLEFT_ANCHOR_POINT.y);
    Point region3_pointB = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
            
            
    Mat region1_Cb, region2_Cb, region3_Cb;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    int avg1, avg2, avg3;
    
    private volatile DuckPosition position = DuckPosition.LEFT;
    
    private void inputToCb(Mat input) {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }
    
    @Override
    public void init(Mat firstFrame) {
        inputToCb(firstFrame);
        
        region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        region2_Cb = Cb.submat(new Rect(region2_pointA, region2_pointB));
        region3_Cb = Cb.submat(new Rect(region3_pointA, region3_pointB));
    }
    
    @Override
    public Mat processFrame(Mat input) {
        
        inputToCb(input);
        
        avg1 = (int) Core.mean(region1_Cb).val[0];
        avg2 = (int) Core.mean(region2_Cb).val[0];
        avg3 = (int) Core.mean(region3_Cb).val[0];
        
        Imgproc.rectangle(
            input,
            region1_pointA,
            region1_pointB,
            BLUE,
            2);
            
        Imgproc.rectangle(
            input,
            region2_pointA,
            region2_pointB,
            BLUE,
            2);
            
        Imgproc.rectangle(
            input,
            region3_pointA,
            region3_pointB,
            BLUE,
            2);
            
        int max = Math.max(Math.max(avg1, avg2), avg3);
                
        if (max == avg1) {
            position = DuckPosition.LEFT;
            Imgproc.rectangle(
                input,
                region1_pointA,
                region1_pointB,
                GREEN,
                -1);
        } else if (max == avg2) {
            position = DuckPosition.CENTER;
            Imgproc.rectangle(
                input,
                region2_pointA,
                region2_pointB,
                GREEN,
                -1);
        } else if (max == avg3) {
            position = DuckPosition.RIGHT;
            Imgproc.rectangle(
                input,
                region3_pointA,
                region3_pointB,
                GREEN,
                -1);
        }
        
        return input;
    }
    
    
    public DuckPosition getAnalysis() {
        return position;
    }
    
}
