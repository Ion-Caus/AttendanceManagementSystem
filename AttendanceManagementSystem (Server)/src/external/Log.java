package external;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Log {

    private ArrayList<LogLine> lines;
    private static Log log;
    private static Object lock = new Object();

    private Log() {
        this.lines = new ArrayList<>();
    }

    public static Log getLog() {
        if (log == null) {
            synchronized (lock) {
                if (log == null)
                    log = new Log();
            }
        }
        return log;
    }


    public void addLog(String text) {
        var logLine = new LogLine(text);
        lines.add(logLine);
        addToFile(logLine);
        System.out.println(logLine);
    }

    public ArrayList<LogLine> getLines() {
        return lines;
    }

    @Override
    public String toString() {
        var strings = new StringBuilder();
        for (LogLine logLine : lines) {
            strings.append(logLine).append("\n");
        }
        return strings.toString();
    }

    private void addToFile(LogLine log) {
        if (log == null) {
            return;
        }
        BufferedWriter out = null;
        try {
            String filename = "Log-" + log.getDateTime().getSortableDate() + ".txt";
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                assert out != null;
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
