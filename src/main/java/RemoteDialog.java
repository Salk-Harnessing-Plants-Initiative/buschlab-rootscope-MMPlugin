import grpc.rootscopeserver.DataProvider;
import grpc.rootscopeserver.MMServer;
import mmcorej.CMMCore;
import org.micromanager.api.ScriptInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.*;

class RemoteDialog extends javax.swing.JFrame{

    private MicroManagerPlugin plugin;

    private DataProvider dataProvider;
    private ScriptInterface app;
    private CMMCore core;

    private JButton stopButton = new JButton("stop");

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private MMServer mmServer;

    RemoteDialog(MicroManagerPlugin plugin, ScriptInterface app) {
        this.plugin = plugin;
        this.app = app;
        this.core = app.getMMCore();

        init();
    }

    private void init() {
        this.setTitle("remote control");

        int port = 50051;
        InetAddress addr;

        this.dataProvider = new DataProviderMM(core, app);

        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            addr = null;
        }

        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        stopButton.setPreferredSize(new Dimension(40,40));

        this.getContentPane().setSize(500,200);
        this.getContentPane().add(new JTextArea("starting server on " + addr + "\n" +
                "listening on port " + port), BorderLayout.NORTH);
        this.getContentPane().add(stopButton, BorderLayout.CENTER);

        this.pack();

        this.setVisible(true);

        stopButton.setEnabled(true);
        stopButton.addActionListener(this::stopButtonActionPerformed);

        Callable<Void> task = () -> {
            mmServer.SetServiceDataProvider(dataProvider);
            try {
                mmServer = new MMServer();
                mmServer.start(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        Future serverStop = executor.submit(task);
    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent ev)  {
        System.out.println("stop button pressed");
        mmServer.stop();
        executor.shutdown();
        plugin.dispose();
        this.dispose();
    }
}
