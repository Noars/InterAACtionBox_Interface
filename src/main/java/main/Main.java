package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import main.UI.DefaultTranslator;
import main.UI.Translator;
import main.UI.menu.GraphicalMenus;
import main.utils.StageUtils;
import main.utils.UtilsOS;
import main.utils.multilinguism.Multilinguism;

import java.io.File;

public class Main extends Application {

    @Getter
    private static Main instance;

    @Getter
    private Translator translator;

    public static void main(String[] args) {instance = getInstance();
        launch(args);}

    @Override
    public void start(Stage primaryStage) {

        this.createFolderWindows();

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("InteraactionBox-AFSR");
        primaryStage.setWidth(Screen.getPrimary().getBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getBounds().getHeight());
        Scene scene = new Scene(new Pane(new Rectangle(0,0,Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight())), Color.TRANSPARENT);

        final Configuration config = ConfigurationBuilder.createFromPropertiesResource().build();
        config.language = ConfigurationBuilder.createFromPropertiesResource().language;
        final Multilinguism multilinguism = Multilinguism.getSingleton();

        translator = new DefaultTranslator(config, multilinguism);

        GraphicalMenus graphicalMenus = new GraphicalMenus(primaryStage, this);
        scene.setRoot(graphicalMenus.getHomeScreen());
        graphicalMenus.getConfiguration().setScene(scene);

        primaryStage.setScene(scene);

        graphicalMenus.getHomeScreen().showCloseProcessButtonIfProcessNotNull();
        StageUtils.displayUnclosable(primaryStage);

        scene.setOnMouseMoved((e) -> {
            if (graphicalMenus.getConfiguration().isGazeInteraction()) {
                graphicalMenus.getConfiguration().analyse(e.getScreenX(), e.getScreenY());
            }
        });
    }

    public void createFolderWindows(){
        if (UtilsOS.isWindows()){
            File boxFolder = new File("C:\\Users\\" + UtilsOS.getUserNameFromOS() + "\\Documents\\InterAACtionBox");
            boolean createFolder = boxFolder.mkdirs();
            System.out.println("Folder Box created ! -> " + createFolder);
        }
    }
}
