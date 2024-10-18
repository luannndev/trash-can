package dev.luan.trashcan.configuration.background;

import dev.luan.trashcan.configuration.background.gradient.BackgroundGradient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Background {

    boolean isImageBackground = false;
    String backgroundImage = null;
    String backgroundColor = "#111111";
    boolean isBlurred = false;
    float blurryness = 0.0f;
    boolean isGradient = false;
    BackgroundGradient backgroundGradient = null;

    public static Background getDefaultBackground() {
        Background background = new Background();
        background.setImageBackground(false);
        background.setBackgroundImage(null);
        background.setBackgroundColor("#111111");
        background.setBlurred(false);
        background.setBlurryness(0.0f);
        background.setGradient(false);
        background.setBackgroundGradient(null);
        return background;
    }
}