package dev.luan.trashcan.configuration;

import com.google.gson.TypeAdapter;
import dev.luan.trashcan.TrashCan;
import dev.luan.trashcan.configuration.background.Background;
import dev.luan.trashcan.logger.ConsoleLogger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class DesignConfiguration {

    Background background;


    public static DesignConfiguration getDefaultDesignConfiguration() {
        DesignConfiguration designConfiguration = new DesignConfiguration();
        designConfiguration.setBackground(Background.getDefaultBackground());
        return designConfiguration;
    }

    public void load(Path configPath) {
        try (FileReader reader = new FileReader(configPath.toFile())) {
            DesignConfiguration config = TrashCan.GSON.fromJson(reader, DesignConfiguration.class);
            if (config == null) {
                this.setBackground(Background.getDefaultBackground());
                ConsoleLogger.logError("Failed to load design configuration. Using default configuration.");
                save(configPath);
                ConsoleLogger.logInfo("Default design configuration saved successfully.");
                return;
            }
            this.setBackground(config.getBackground());
            ConsoleLogger.logInfo("Design configuration loaded successfully. Using custom configuration.");
        } catch (IOException ignored) {
            this.setBackground(Background.getDefaultBackground());
            ConsoleLogger.logError("Failed to load design configuration. Using default configuration.");
            save(configPath);
            ConsoleLogger.logInfo("Default design configuration saved successfully.");
        }
    }

    public void save(Path configPath) {
        try (FileWriter writer = new FileWriter(configPath.toFile())) {
            TrashCan.GSON.toJson(this, writer);
            ConsoleLogger.logInfo("Design configuration saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            ConsoleLogger.logError("Failed to save design configuration.");
        }
    }

}