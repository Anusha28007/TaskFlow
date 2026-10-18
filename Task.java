public class Task {
    private String name;
    private String priority;
    private String category;
    private boolean completed;

    public Task(String name, String priority, String category) {
        this.name = name;
        this.priority = priority;
        this.category = category;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public String getPriority() {
        return priority;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public void setName(String name) {
    this.name = name;
    }

    public void setPriority(String priority) {
    this.priority = priority;
    }

    public void setCategory(String category) {
    this.category = category;
    }

}
