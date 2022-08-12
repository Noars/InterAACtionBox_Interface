package main.process;

import main.UI.menu.GraphicalMenus;
import main.process.xdotoolProcess.GoogleChromeXdotoolProcessCreator;
import main.utils.NamedProcess;
import main.utils.UtilsOS;

import java.io.IOException;

public class InterAACtionPlayerNamedProcessCreator implements AppNamedProcessCreator {

    ProcessBuilder processBuilder;

    @Override
    public void setUpProcessBuilder() {
        processBuilder = new ProcessBuilder(AppNamedProcessCreator.getBrowser(),
                "--kiosk",
                "--window-position=0,0",
                "--fullscreen",
                "--disable-component-update",
                "--simulate-outdated-no-au='Tue, 31 Dec 2099 23:59:59 GMT'",
                "--disable-features=Translate",
                "--autoplay-policy=no-user-gesture-required",
                "http://localhost:4202/#/fr/connect/" + UtilsOS.getUserNameFromOSForPWA());

        this.openPort();
    }

    public void openPort(){
        try{
            Runtime.getRuntime().exec("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\launchServerPlayer.bat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NamedProcess start(GraphicalMenus graphicalMenus) {
        return AppNamedProcessCreator.createProcressAndWaitForClose(new GoogleChromeXdotoolProcessCreator(), new CloseGoogleChromeProcessCreator(), processBuilder, graphicalMenus, "GazeMediaPlayer");
    }
}
