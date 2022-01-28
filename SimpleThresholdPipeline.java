import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class SimpleThresholdPipeline extends OpenCvPipeline {

    public Scalar lower = new Scalar(0, 0, 0);
    public Scalar upper = new Scalar(255, 255, 255);

    public ColorSpace colorSpace = ColorSpace.YCrCb;

    private Mat ycrcbMat = new Mat();
    private Mat binaryMat = new Mat();
    private Mat maskedInputMat = new Mat();

    private Telemetry telemetry = null;

    enum ColorSpace {

        RGB(Imgproc.COLOR_RGBA2RGB),
        HSV(Imgproc.COLOR_RGB2HSV),
        YCrCb(Imgproc.COLOR_RGB2YCrCb),
        Lab(Imgproc.COLOR_RGB2Lab);

        public int cvtCode = 0;

        ColorSpace(int cvtCode) {
            this.cvtCode = cvtCode;
        }
    }


    public SimpleThresholdPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, ycrcbMat, colorSpace.cvtCode);

        Core.inRange(ycrcbMat, lower, upper, binaryMat);

        maskedInputMat.release();

        Core.bitwise_and(input, input, maskedInputMat, binaryMat);

        telemetry.addData("[>]", "Change these values in tuner menu");
        telemetry.addData("[Color Space]", colorSpace.name());
        telemetry.addData("[Lower Scalar]", lower);
        telemetry.addData("[Upper Scalar]", upper);
        telemetry.update();

        return maskedInputMat;
    }
}