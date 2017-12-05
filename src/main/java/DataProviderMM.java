import grpc.server.DataProvider;
import mmcorej.CMMCore;
import mmcorej.StrVector;

public class DataProviderMM implements DataProvider {

    private CMMCore core;

    public DataProviderMM(CMMCore core) {
        this.core = core;
    }

    @Override
    public byte[] getImgData1() {

        try {
//            core.loadDevice("Camera", "DemoCamera", "DCam");
//            core.initializeDevice("Camera");
//            core.setExposure(50);
//            core.snapImage();
//            return (byte[]) core.getImage();

            System.out.println("data from MMCore");
            return new byte[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getCameraProperties() {
        String camera = core.getCameraDevice();

        StringBuilder builder;
        StrVector props;

        try {
            props = core.getDevicePropertyNames(camera);
            builder = new StringBuilder();

            for (String entry : props) {
                String val = core.getProperty(camera, entry);
                builder.append('/' + entry + '-' + val);
            }
        }
        catch (Exception e) {
            return "exception thrown";
        }
        return builder.toString();
    }
}
