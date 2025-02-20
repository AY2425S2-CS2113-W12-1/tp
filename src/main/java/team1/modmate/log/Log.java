package team1.modmate.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple logging utility that allows logging messages to the console and a file.
 * Logging can be enabled or disabled globally.
 *
 * <p>Logs are stored in the project's {@code logs/} directory.</p>
 */
public class Log {
    private static volatile boolean enableLogging = false;
    private static final String LOG_DIRECTORY = "src/main/java/com/team1/modmate/log/";
    private static final String LOG_FILE = LOG_DIRECTORY + "log.txt";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static boolean sessionStarted = false;

    /**
     * Private constructor to prevent instantiation.
     */
    private Log() {}

    /**
     * Enables or disables logging.
     *
     * @param enabled {@code true} to enable logging, {@code false} to disable it.
     */
    public static void setLoggingEnabled(boolean enabled) {
        enableLogging = enabled;
        if (enabled) {
            ensureSessionHeader();
        }
    }


    /**
     * Prints a log message to the console.
     *
     * @param message The message to print.
     */
    public static void printLog(String message) {
        System.out.println(message);
    }

    /**
     * Saves a log message to the log file.
     * If the log directory does not exist, it will be created.
     *
     * @param message The message to save to the log file.
     */
    public static void saveLog(String message) {
        try {
            File directory = new File(LOG_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
                out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Failed to write log to file: " + e.getMessage());
        }
    }

    /**
     * Ensures that the session header is written at the start of the log file for a new session.
     */
    private static synchronized void ensureSessionHeader() {
        if (!sessionStarted) {
            sessionStarted = true;
            String sessionHeader = "\n\n~~~~ SESSION " + LocalDateTime.now().format(TIMESTAMP_FORMAT) + " ~~~~\n";
            saveLog(sessionHeader);
        }
    }

    /**
     * Checks if logging is currently enabled.
     *
     * @return {@code true} if logging is enabled, {@code false} otherwise.
     */
    public static boolean isLoggingEnabled() {
        return enableLogging;
    }
}
