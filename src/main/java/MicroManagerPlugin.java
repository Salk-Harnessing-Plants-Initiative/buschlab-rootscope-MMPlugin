import grpc.server.DataProvider;
import grpc.server.MMServer;
import mmcorej.CMMCore;
import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MicroManagerPlugin implements
        MMPlugin {
    public static final String menuName = "rootscope remote control";
    public static final String tooltipDescription = "rootscope remote control";

    // Provides access to the Micro-Manager Java API (for GUI control and high-
    // level functions).
    private ScriptInterface app;
    // Provides access to the Micro-Manager Core API (for direct hardware
    // control)
    private CMMCore core;

    @Override
    public void dispose() {

    }

    @Override
    public void setApp(ScriptInterface app) {
        this.app = app;
        this.core = app.getMMCore();
    }

    @Override
    public void show() {

        RemoteDialog dialog = new RemoteDialog(this, app);

//        int port = 50051;
//        InetAddress addr;
//
//        this.dataProvider = new DataProviderMM(core, app);
//
//        try {
//            addr = InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            addr = null;
//        }
//
//
//
//        try {
//            MMServer.SetServiceDataProvider(dataProvider);
//            MMServer.main(new String[] { Integer.toString(port)});
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public String getDescription() {
        return "rootscope testing";
    }

    @Override
    public String getInfo() {
        return "rootscope remote controller";
    }

    @Override
    public String getVersion() {
        return "0.1";
    }

    @Override
    public String getCopyright() {
        return null;
    }
}
