import grpc.server.MMServer;
import mmcorej.CMMCore;
import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RootScopeMMPlugin implements
        MMPlugin {
    public static final String menuName = "rootscope remote control";
    public static final String tooltipDescription = "rootscope remote control";

    // Provides access to the Micro-Manager Java API (for GUI control and high-
    // level functions).
    private ScriptInterface app_;
    // Provides access to the Micro-Manager Core API (for direct hardware
    // control)
    private CMMCore core_;

    @Override
    public void dispose() {

    }

    @Override
    public void setApp(ScriptInterface app) {
        this.app_ = app;
        this.core_ = app.getMMCore();
        System.out.println("setApp");
    }

    @Override
    public void show() {

        int port = 50051;
        InetAddress addr;

        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            addr = null;
        }

        JOptionPane.showMessageDialog(null, "remote rootscope server plugin started, listening on" +
                        " port " + port + "\non machine " + addr, "remote rootscope server plugin",
                JOptionPane.PLAIN_MESSAGE);

        try {
            MMServer.main(new String[] { Integer.toString(port)});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

