package main.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
            Path source = Paths.get("./../lib/google");
            try{
                Files.walk(source).forEach(elem -> this.copyElemToDest(source, elem));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Je dezippe Google");
                this.extractGoogle();
            }
        }
    }

    public void extractGoogle(){
        try {
            System.out.println("Je start");
            Process runtime = Runtime.getRuntime().exec( "C:\\Program Files (x86)\\InterAACtionBoxAFSR\\lib\\scriptsWindows\\extractGoogleChromePortable.bat");
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
            System.out.println("Element -> " + elem);
            System.out.println("Destination -> " + destination);
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
}
