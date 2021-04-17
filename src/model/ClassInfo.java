package model;

public class ClassInfo {
    private String name;
    private int startingLine;
    private int linesOfCode;

    public ClassInfo(
        String name,
        int startingLine,
        int linesOfCode
    ) {
        this.name = name;
        this.startingLine = startingLine;
        this.linesOfCode = linesOfCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }

    public int getStartingLine() {
        return startingLine;
    }

    public void setStartingLine(int startingLine) {
        this.startingLine = startingLine;
    }
}
