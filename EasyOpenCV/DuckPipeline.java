package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Disabled
public class DuckPipeline extends OpenCvPipeline {

    //position enum
    public enum DuckPosition
    {
        LEFT,
        CENTER,
        RIGHT
    }

    //color constants
    public final Scalar BLUE = new Scalar(0, 0, 255);
    public final Scalar GREEN = new Scalar(0, 255, 0);

    //top left points of each box
    //x is measured from left 0 to right 320, y is measured from top 0 to bottom 240
    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(92,95);
    static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(163,95);
    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(215,95);
    
    //box dimensions
    static final int REGION_WIDTH = 25;
    static final int REGION_HEIGHT = 25;

    //top left and bottom right points
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

    //image objects and average ints
    Mat region1_Cb, region2_Cb, region3_Cb;
    Mat YCrCb = new Mat();
    Mat Cb = new Mat();
    int avg1, avg2, avg3;

    //position variable
    private volatile DuckPosition position = DuckPosition.LEFT;

    //telemetry, allows telemetry in EOCVSim
    private Telemetry telemetry;

    //constructor for telemetry
    public DuckPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    //converts RGB to YCrCb, extracts the Cb channel to Cb variable
    void inputToCb(Mat input)
    {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }


    //initializes the image
    @Override
    public void init(Mat firstFrame)
    {
        //initializes the image
        inputToCb(firstFrame);

        //subimages of what's inside each box
        region1_Cb = Cb.submat(new Rect(region1_pointA, region1_pointB));
        region2_Cb = Cb.submat(new Rect(region2_pointA, region2_pointB));
        region3_Cb = Cb.submat(new Rect(region3_pointA, region3_pointB));
    }


    //image processor
    @Override
    public Mat processFrame(Mat input)
    {
        //refreshes new input
        inputToCb(input);

        //averages each box
        avg1 = (int) Core.mean(region1_Cb).val[0];
        avg2 = (int) Core.mean(region2_Cb).val[0];
        avg3 = (int) Core.mean(region3_Cb).val[0];

        //draws rectangle for each box
        //region 1
        Imgproc.rectangle(
                input, //image to draw on
                region1_pointA, //top left of the box
                region1_pointB, //bottom right of the box
                BLUE, //rectangle color
                2); //thickness of rectangle

        //region 2
        Imgproc.rectangle(
                input, //image to draw on
                region2_pointA, //top left of the box
                region2_pointB, //bottom right of the box
                BLUE, //rectangle color
                2); //thickness of rectangle

        //region 3
        Imgproc.rectangle(
                input, //image to draw on
                region3_pointA, //top left of the box
                region3_pointB, //bottom right of the box
                BLUE, //rectangle color
                2); //thickness of rectangle


        //maximum average
        int max = Math.min(Math.min(avg1, avg2), avg3);

        //fills in whichever box has the greatest average
        if(max == avg1)
        {
            position = DuckPosition.LEFT;

            //fills in rectangle
            Imgproc.rectangle(
                    input, //image to draw on
                    region1_pointA, //top left of the box
                    region1_pointB, //bottom right of the box
                    GREEN, //rectangle color
                    -1); //thickness of rectangle (-1 is solid)
        }
        else if(max == avg2)
        {
            position = DuckPosition.CENTER;

            //fills in rectangle
            Imgproc.rectangle(
                    input, //image to draw on
                    region2_pointA, //top left of the box
                    region2_pointB, //bottom right of the box
                    GREEN, //rectangle color
                    -1); //thickness of rectangle (-1 is solid)
        }
        else if(max == avg3)
        {
            position = DuckPosition.RIGHT;

            //fills in rectangle
            Imgproc.rectangle(
                    input, //image to draw on
                    region3_pointA, //top left of the box
                    region3_pointB, //bottom right of the box
                    GREEN, //rectangle color
                    -1); //thickness of rectangle (-1 is solid)
        }

        telemetry.addData("Pattern", position);
        telemetry.addData("avg1", avg1);
        telemetry.addData("avg2", avg2);
        telemetry.addData("avg3", avg3);
        telemetry.update();

        //sends the image to EOCVsim
        return input;
    }

    //function called in opmode to get the position
    public DuckPosition getAnalysis()
    {
        return position;
    }
}