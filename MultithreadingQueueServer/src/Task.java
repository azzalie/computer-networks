public class Task {
    private final int start;
    private final int end;
    private boolean completed;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
        this.completed = false;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
