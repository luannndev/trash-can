package dev.luan.trashcan.logger;

import dev.luan.trashcan.logger.ansi.AnsiColorCodes;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleLogger {

    public static final String LOG_INFO = "INFO";
    public static final String LOG_WARNING = "WARNING";
    public static final String LOG_ERROR = "ERROR";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");

    public static void logInfo(@NonNull String msg, @Nullable Object... objects) {
        System.out.println(AnsiColorCodes.ANSI_WHITE + "[" + DATE_FORMAT.format(new Date()) + "] " + AnsiColorCodes.ANSI_RESET + LOG_INFO + " -> " + msg.formatted(objects));
    }

    public static void logWarning(@NonNull String msg, @Nullable Object... objects) {
        System.out.println(AnsiColorCodes.ANSI_WHITE + "[" + DATE_FORMAT.format(new Date()) + "] " + AnsiColorCodes.ANSI_YELLOW + LOG_WARNING + " -> " + msg.formatted(objects));
    }

    public static void logError(@NonNull String msg, @Nullable Object... objects) {
        System.err.println(AnsiColorCodes.ANSI_WHITE + "[" + DATE_FORMAT.format(new Date()) + "] " + AnsiColorCodes.ANSI_RED + LOG_ERROR + " -> " + msg.formatted(objects));
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearLines(int lines) {
        System.out.print("\033[" + lines + "A\033[2K");
    }

}