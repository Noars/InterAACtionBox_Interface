package main.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
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
        try {
            boolean createSceneFile = sceneVersion.createNewFile();
            System.out.println("Scene Version File created ! -> " + createSceneFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void installGoogle(){
        File googleFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBoxAFSR\\GoogleChromePortable");
        boolean createGoogleFolder = googleFolder.mkdirs();
        System.out.println("Google Chrome Portable folder created ! -> " + createGoogleFolder);
        if (createGoogleFolder){
            Path source = Paths.get("GoogleChromePortable");
            try{
                Files.walk(source).forEach(elem -> copyElemToDest(source, elem));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.extractGoogle();
            }
        }
    }

    public void extractGoogle(){
        ProcessBuilder pb = new ProcessBuilder(
                "cmd.exe",
                "./scriptsWindows/extractGoogleChromePortable.bat"
        );
        try {
            pb.start();
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
}
