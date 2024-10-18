package dev.luan.trashcan.utils.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.luan.trashcan.configuration.background.Background;

import java.io.IOException;

public class BackgroundTypeAdapter extends TypeAdapter<Background> {
    @Override
    public void write(JsonWriter jsonWriter, Background background) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("isImageBackground").value(background.isImageBackground());
        jsonWriter.name("backgroundImage").value(background.getBackgroundImage());
        jsonWriter.name("backgroundColor").value(background.getBackgroundColor());
        jsonWriter.name("isBlurred").value(background.isBlurred());
        jsonWriter.name("blurryness").value(background.getBlurryness());
        jsonWriter.name("isGradient").value(background.isGradient());
        if (background.isGradient()) {
            jsonWriter.name("backgroundGradient");
            new BackgroundGradientTypeAdapter().write(jsonWriter, background.getBackgroundGradient());
        }
        jsonWriter.endObject();
    }

    @Override
    public Background read(JsonReader jsonReader) throws IOException {
        Background background = new Background();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String name = jsonReader.nextName();
            switch (name) {
                case "isImageBackground":
                    background.setImageBackground(jsonReader.nextBoolean());
                    break;
                case "backgroundImage":
                    background.setBackgroundImage(jsonReader.nextString());
                    break;
                case "backgroundColor":
                    background.setBackgroundColor(jsonReader.nextString());
                    break;
                case "isBlurred":
                    background.setBlurred(jsonReader.nextBoolean());
                    break;
                case "blurryness":
                    background.setBlurryness((float) jsonReader.nextDouble());
                    break;
                case "isGradient":
                    boolean isGradient = jsonReader.nextBoolean();
                    background.setGradient(isGradient);
                    if (isGradient) {
                        jsonReader.nextName();
                        background.setBackgroundGradient(new BackgroundGradientTypeAdapter().read(jsonReader));
                    }
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        return background;
    }
}