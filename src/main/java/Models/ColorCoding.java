package Models;

import java.awt.*;

public class ColorCoding {
    public static final Color EASY_COLOR = new Color(119, 221, 119); // Green
    public static final Color MEDIUM_COLOR = new Color(255, 255, 153); // Yellow
    public static final Color HARD_COLOR = new Color(255, 153, 153); // Red

    public static Color getColorForDifficultyLevel(String difficultyLevel) {
        switch (difficultyLevel.toLowerCase()) {
            case "easy":
                return EASY_COLOR;
            case "medium":
                return MEDIUM_COLOR;
            case "hard":
                return HARD_COLOR;
            default:
                return Color.WHITE;
        }
    }
}

