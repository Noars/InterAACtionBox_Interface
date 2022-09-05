package main.process;

import lombok.extern.slf4j.Slf4j;
import main.UI.menu.GraphicalMenus;
import main.process.xdotoolProcess.GazePlayXdotoolProcessCreator;
import main.utils.NamedProcess;
import main.utils.UtilsOS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

@Slf4j
public class GazePlayNamedProcessCreator implements AppNamedProcessCreator {
    ProcessBuilder processBuilder;

    public GazePlayNamedProcessCreator() {
        super();
    }

    @Override
    public void setUpProcessBuilder() {
        processBuilder = createGazePlayBuilder();
    }

    private ProcessBuilder createGazePlayBuilder() {

        ProcessBuilder processBuilder;
        if (UtilsOS.isUnix()){
            processBuilder = new ProcessBuilder(
                    "sh",
                    "../../Launcher/gazeplayAfsrLauncher.sh"
            );
        } else {
            processBuilder = new ProcessBuilder(
                    "C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\launchGazePlay.bat"
            );
        }

        return processBuilder;
    }

    @Override
    public NamedProcess start(GraphicalMenus graphicalMenus) {
        processBuilder = createGazePlayBuilder();
        return AppNamedProcessCreator.createProcress(new GazePlayXdotoolProcessCreator(), processBuilder, graphicalMenus, "GazePlay");
    }
}
