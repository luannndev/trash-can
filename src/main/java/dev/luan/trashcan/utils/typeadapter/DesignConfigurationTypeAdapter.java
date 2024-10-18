package dev.luan.trashcan.utils.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.luan.trashcan.configuration.DesignConfiguration;
import dev.luan.trashcan.configuration.background.Background;

import java.io.IOException;

public class DesignConfigurationTypeAdapter extends TypeAdapter<DesignConfiguration> {

    @Override
    public void write(JsonWriter out, DesignConfiguration value) throws IOException {
        out.beginObject();
        out.name("background");
        new BackgroundTypeAdapter().write(out, value.getBackground());
        out.endObject();
    }

    @Override
    public DesignConfiguration read(JsonReader in) throws IOException {
        DesignConfiguration config = new DesignConfiguration();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "background":
                    Background background = new BackgroundTypeAdapter().read(in);
                    config.setBackground(background);
                    break;
            }
        }
        in.endObject();
        return config;
    }
}