package dev.luan.trashcan.utils.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.luan.trashcan.configuration.background.gradient.BackgroundGradient;
import dev.luan.trashcan.configuration.background.gradient.GradientType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundGradientTypeAdapter extends TypeAdapter<BackgroundGradient> {

    @Override
    public void write(JsonWriter jsonWriter, BackgroundGradient backgroundGradient) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("gradientType").value(backgroundGradient.getGradientType().toString());

        jsonWriter.name("colors").beginArray();
        for (String color : backgroundGradient.getColors()) {
            jsonWriter.value(color);
        }
        jsonWriter.endArray();

        jsonWriter.endObject();
    }

    @Override
    public BackgroundGradient read(JsonReader jsonReader) throws IOException {
        BackgroundGradient backgroundGradient = new BackgroundGradient();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "colors":
                    List<String> colors = new ArrayList<>();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        colors.add(jsonReader.nextString());
                    }
                    jsonReader.endArray();
                    backgroundGradient.setColors(colors);
                    break;
                case "gradientType":
                    backgroundGradient.setGradientType(GradientType.valueOf(jsonReader.nextString()));
                    break;
            }
        }
        jsonReader.endObject();
        return backgroundGradient;
    }
}