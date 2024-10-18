package dev.luan.trashcan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.luan.trashcan.configuration.DesignConfiguration;
import dev.luan.trashcan.utils.FileUtils;
import dev.luan.trashcan.utils.OSUtils;
import dev.luan.trashcan.utils.typeadapter.DesignConfigurationTypeAdapter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

public class TrashCan extends Application {

    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(DesignConfiguration.class, new DesignConfigurationTypeAdapter())
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    DesignConfiguration designConfiguration;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlFile = FileUtils.copyResourceToFileAndGetURL("main_page.fxml", "/internal/fxml/main_page.fxml");
        DesignConfiguration designConfiguration = new DesignConfiguration();
        designConfiguration.load(Paths.get(OSUtils.getAppDataFolder() + "/folder-cleaner/design.json"));
        Parent root = FXMLLoader.load(fxmlFile.toURI().toURL());
        primaryStage.setTitle("Folder Cleaner");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}