package org.firstinspires.ftc.teamcode.shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GridLogger {

    private static final String TIME = "Time";
    private LogWriter writer;
    private Clock clock;


    private boolean firstLine = true;

    private HashSet<String> categorySet = new HashSet<>();
    private HashMap<String, String> rowData = new HashMap<>();
    private ArrayList<String> categories = new ArrayList<String>();

    public GridLogger(LogWriter writer, Clock clock) {
        this.writer = writer;
        this.clock = clock;
        categorySet.add("Time");
        categories.add(TIME);


    }

    /**
     * Add a value to the logger under the category.  Categories are lazily added to the logger
     * in the order encountered.
     *
     * @param category
     * @param value
     */
    public void add(String category, String value) {
        if (firstLine && !categorySet.contains(category)) {
            categorySet.add(category);
            categories.add(category);
        }
        rowData.put(category, value);
    }
    public void add(String category, long value) {
        rowData.put(category,Long.toString(value));
    }
    public void add(String category, double value) {
        rowData.put(category,Double.toString(value));

    }

    /**
     * Write a line of data to the log.  If this is the first call to writeLn, categories are
     * written first, followed by the line of data.  Once the data is written, the logger is reset
     * and calls to add() will add values to the next line of data.
     */
    public void writeLn() {
        if (firstLine == true) {
            StringBuffer line = new StringBuffer();
            for (int i = 0; i < categories.size(); i++) {
                line.append(categories.get(i));
                if (i < categories.size() - 1) {
                    line.append(",");
                }
            }
            writer.writeLine(line.toString());
            firstLine = false;
        }
        rowData.put("Time", Long.toString(clock.getCurrentTime()));
        StringBuffer finalTitleLine = new StringBuffer();
        for (int a = 0; a < categories.size(); a++) {
            String category = categories.get(a);
            String value = rowData.get(category);
            if (value != null) {
                finalTitleLine.append(value);
            }
            if (a < categories.size() - 1) {
                finalTitleLine.append(",");
            }
        }
        writer.writeLine(finalTitleLine.toString());
        rowData.clear();


    }


    public void stop() {
    }

}

