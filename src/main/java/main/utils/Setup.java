package main.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class Setup {

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
                System.out.println("");
            } finally {
                this.extractGoogle();
            }
        }
    }

    public void installGaze(){
        File gazeFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\interaactionGaze");
        if (!gazeFolder.exists()){
            try {
                Process runtime = Runtime.getRuntime().exec("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\gazeDownload.bat");
                this.showOutputCmd(runtime);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void extractGoogle(){
        try {
            Process runtime = Runtime.getRuntime().exec("C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\extractGoogleChromePortable.bat");
            this.showOutputCmd(runtime);
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

    public void showOutputCmd(Process process) throws IOException {
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
}
