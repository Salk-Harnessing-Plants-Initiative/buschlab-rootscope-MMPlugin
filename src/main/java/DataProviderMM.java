import grpc.server.DataProvider;
import mmcorej.CMMCore;
import mmcorej.StrVector;
import org.micromanager.api.ScriptInterface;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class DataProviderMM implements DataProvider {

    private CMMCore core;
    private ScriptInterface si;

    public DataProviderMM(CMMCore core, ScriptInterface si) {
        this.si = si;
        this.core = core;
    }

    @Override
    public byte[] getImgData1() {

        try {
            ByteArrayOutputStream baos = null;
            BufferedImage bi = si.getSnapLiveWin().getCanvas().getImage().getBufferedImage();
            try {
                baos = new ByteArrayOutputStream();
                ImageIO.write(bi, "png", baos);
            } finally {
                try {
                    baos.close();
                } catch (Exception e) {
                }
            }
            return  baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public String getCameraProperties() {
        String camera = core.getCameraDevice();

        StringBuilder builder;
        StrVector props;

        try {
            props = core.getDevicePropertyNames(camera);
            builder = new StringBuilder();

            builder.append(camera);

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

    @Override
    public boolean autoScaleContrast() {
        //si.setContrastBasedOnFrame(1,1);
        return true;
    }
}
