import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;



public class DuckPipeline extends OpenCvPipeline {
    
    //points
    static final Point REGION1_TOPLEFT_ANCHOR_POINT = new Point(550, 75);
    static final Point REGION2_TOPLEFT_ANCHOR_POINT = new Point(460, 75);
    static final Point REGION3_TOPLEFT_ANCHOR_POINT = new Point(162.5, 84);
    static final int REGION_WIDTH = 30;
    static final int REGION_HEIGHT = 30;
    
    Point region1_pA = new Point(
        REGION1_TOPLEFT_ANCHOR_POINT.x,
        REGION1_TOPLEFT_ANCHOR_POINT.y);
    Point region1_pB = new Point(
        REGION1_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
        REGION1_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region2_pA = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x,
            REGION2_TOPLEFT_ANCHOR_POINT.y);
    Point region2_pB = new Point(
            REGION2_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION2_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    Point region3_pA = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x,
            REGION3_TOPLEFT_ANCHOR_POINT.y);
    Point region3_pB = new Point(
            REGION3_TOPLEFT_ANCHOR_POINT.x + REGION_WIDTH,
            REGION3_TOPLEFT_ANCHOR_POINT.y + REGION_HEIGHT);
    
    
    
    
    //telemetry
    Telemetry telemetry;
    
    public DuckPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    //duckposition enum
    public enum DuckPosition {
        LEFT,
        CENTER,
        RIGHT
    }

    //variable tuners
    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);


    //mat declarations
    private Mat YCrCb       = new Mat();
    private Mat Cb          = new Mat();
    private Mat region1, region2, region3;
    int avg1, avg2, avg3;

    public volatile DuckPosition position = DuckPosition.LEFT;

    //colorspace
    public ColorSpace colorSpace = ColorSpace.YCrCb;
    enum ColorSpace {
        
        RGB(Imgproc.COLOR_RGBA2RGB),
        HSV(Imgproc.COLOR_RGB2HSV),
        YCrCb(Imgproc.COLOR_RGB2YCrCb),
        Lab(Imgproc.COLOR_RGB2Lab);
        int avg1, avg2, avg3;

        public int cvtCode = 0;

        ColorSpace(int cvtCode) {
            this.cvtCode = cvtCode;
        }
    }


    public void inputToCb(Mat input) {
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cb, 2);
    }


    @Override
    public void init(Mat firstFrame) {
        inputToCb(firstFrame);

        region1 = Cb.submat(new Rect(region1_pA, region1_pB));
        region2 = Cb.submat(new Rect(region2_pA, region2_pB));
        region3 = Cb.submat(new Rect(region3_pA, region3_pB));
    }

    @Override
    public Mat processFrame(Mat input) {
        inputToCb(input);

        avg1 = (int) Core.mean(region1).val[0];
        avg2 = (int) Core.mean(region2).val[0];
        avg3 = (int) Core.mean(region3).val[0];

        Imgproc.rectangle(
            input,
            region1_pA,
            region1_pB,
            lower,
            2);

        Imgproc.rectangle(
            input,
            region2_pA,
            region2_pB,
            lower,
            2);

        Imgproc.rectangle(
            input,
            region3_pA,
            region3_pB,
            lower,
            2);

        return input;
    }

    public DuckPosition getAnalysis() {
        return position;
    }
}