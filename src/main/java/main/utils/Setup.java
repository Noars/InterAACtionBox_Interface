package main.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class Setup {

    Process [] listPortServer = new Process[3];

    boolean portSceneOpen = false;
    boolean portAugComOpen = false;
    boolean portPlayerOpen = false;

    public void setup(){
        if (UtilsOS.isWindows()){
            this.createFolderWindows();
            this.createFolderVersion();
            this.installGoogle();
            this.installGaze();
        }
    }

    public void createFolderWindows(){
        File boxFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR");
        boolean createFolder = boxFolder.mkdirs();
        System.out.println("Folder Box created ! -> " + createFolder);
    }

    public void createFolderVersion(){
        File versionFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version");
        if (versionFolder.mkdirs()){
            this.createFileVersion();
        }
    }

    public void createFileVersion(){
        File sceneVersion = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version\\InterAACtionSceneVersion.txt");
        File playerVersion = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version\\InterAACtionPlayerVersion.txt");
        File augcomVersion = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version\\AugComVersion.txt");
        File gazeVersion = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version\\InterAACtionGazeVersion.txt");
        File gazeplayVersion = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version\\GazePlayVersion.txt");
        File interfaceVersion = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\Version\\InterAACtionBox_InterfaceVersion.txt");
        try {
            boolean createSceneFile = sceneVersion.createNewFile();
            boolean createPlayerFile = playerVersion.createNewFile();
            boolean createAugComFile = augcomVersion.createNewFile();
            boolean createGazeFile = gazeVersion.createNewFile();
            boolean createGazePlayFile = gazeplayVersion.createNewFile();
            boolean createInterfaceFile = interfaceVersion.createNewFile();
            System.out.println("Files version created ! " +
                    "Scene -> " + createSceneFile +
                    ", Player -> " + createPlayerFile +
                    ", AugCom -> " + createAugComFile +
                    ", Gaze -> " + createGazeFile +
                    ", GazePlay -> " + createGazePlayFile +
                    ", Interface -> " + createInterfaceFile);
            this.writeVersion(sceneVersion);
            this.writeVersion(playerVersion);
            this.writeVersion(augcomVersion);
            this.writeVersion(gazeVersion);
            this.writeVersion(gazeplayVersion);
            this.writeVersion(interfaceVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void installGoogle(){
        File googleFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\GoogleChromePortable");
        boolean createGoogleFolder = googleFolder.mkdirs();
        if (createGoogleFolder){
            Path source = Paths.get("./../lib/google");
            try{
                Files.walk(source).forEach(elem -> this.copyElemToDest(source, elem));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.extractGoogle();
            }
        }
    }

    public void installGaze(){
        File gazeFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\interAACtionGaze-windows");
        if (!gazeFolder.exists()){
            try {
                ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\gazeDownload.bat");
                pb.redirectErrorStream(true);
                Process p = pb.start();
                p.onExit().thenRun(() -> {
                    try {
                        p.getInputStream().close();
                        p.getOutputStream().close();
                        p.getErrorStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.destroy();
                });
                this.showOutPutCmd(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void extractGoogle(){
        try {
            Process runtime = Runtime.getRuntime().exec("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\extractGoogleChromePortable.bat");
            this.showOutPutCmd(runtime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyElemToDest(Path source, Path elem){
        Path destination = Paths.get("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\GoogleChromePortable", elem.toString().substring(source.toString().length()));
        try{
            Files.copy(elem, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOutPutCmd(Process process) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = "";
        while ((output = bufferedReader.readLine()) != null){
            System.out.println(output);
        }
    }

    public void writeVersion(File file){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            fileWriter.write("null");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openAllPorts(){
        File sceneFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\InterAACtionScene");
        File augcomFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\AugCom");
        File playerFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\InterAACtionPlayer");

        if (sceneFolder.exists()){
            try{
                ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\sceneServer.bat");
                Process scene = pb.start();
                listPortServer[0] = scene;
                this.portSceneOpen = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (augcomFolder.exists()){
            try{
                ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\augcomServer.bat");
                Process augcom = pb.start();
                listPortServer[1] = augcom;
                this.portAugComOpen = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (playerFolder.exists()){
            try{
                ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\playerServer.bat");
                Process player = pb.start();
                listPortServer[2] = player;
                this.portPlayerOpen = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void openOnePort(String name){
        switch (name){
            case "Scene":
                try{
                    ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\sceneServer.bat");
                    Process scene = pb.start();
                    listPortServer[0] = scene;
                    this.portSceneOpen = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "AugCom":
                try{
                    ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\augcomServer.bat");
                    Process augcom = pb.start();
                    listPortServer[1] = augcom;
                    this.portAugComOpen = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Player":
                try{
                    ProcessBuilder pb = new ProcessBuilder("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\playerServer.bat");
                    Process player = pb.start();
                    listPortServer[2] = player;
                    this.portPlayerOpen = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
    }

    public void closeAllPort() {
        this.closeOnePort("Scene");
        this.closeOnePort("AugCom");
        this.closeOnePort("Player");
    }

    public void closeOnePort(String name){
        switch (name){
            case "Scene":
                if (this.portSceneOpen){
                    try {
                        listPortServer[0].getInputStream().close();
                        listPortServer[0].getOutputStream().close();
                        listPortServer[0].getErrorStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    listPortServer[0].destroy();
                    this.portSceneOpen = false;
                }
                break;

            case "AugCom":
                if (this.portAugComOpen){
                    try {
                        listPortServer[1].getInputStream().close();
                        listPortServer[1].getOutputStream().close();
                        listPortServer[1].getErrorStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    listPortServer[1].destroy();
                    this.portAugComOpen = false;
                }
                break;

            case "Player":
                if (this.portPlayerOpen){
                    try {
                        listPortServer[2].getInputStream().close();
                        listPortServer[2].getOutputStream().close();
                        listPortServer[2].getErrorStream().close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    listPortServer[2].destroy();
                    this.portPlayerOpen = false;
                }
                break;

            default:
                break;
        }
    }
}
