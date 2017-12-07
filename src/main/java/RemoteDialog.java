import grpc.server.DataProvider;
import grpc.server.MMServer;
import mmcorej.CMMCore;
import org.micromanager.api.ScriptInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RemoteDialog extends javax.swing.JFrame{

    MicroManagerPlugin plugin;

    private DataProvider dataProvider;
    private ScriptInterface app;
    private CMMCore core;

    JButton stopButton = new JButton("stop");

    RemoteDialog(MicroManagerPlugin plugin, ScriptInterface app) {
        this.plugin = plugin;
        this.app = app;
        this.core = app.getMMCore();

        init();
    }

    private void init() {
        JFrame frame = new JFrame("remote control");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setSize(500,200);
        frame.getContentPane().add(new JLabel("Header"), BorderLayout.NORTH);
        frame.getContentPane().add(stopButton, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);

        int port = 50051;
        InetAddress addr;

        this.dataProvider = new DataProviderMM(core, app);

        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            addr = null;
        }

        stopButton.setEnabled(true);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent ev) {
                stopButtonActionPerformed(ev);
            }
        });

//        try {
//            MMServer.SetServiceDataProvider(dataProvider);
//            MMServer.main(new String[] { Integer.toString(port)});
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent ev) {
        plugin.dispose();
    }
}
