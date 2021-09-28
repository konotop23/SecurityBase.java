public class Student {

    private int idOfCard;
    private String fullName;
    private String department;
    private String faculty;
    private int course;

    public Student(int idOfCard, String fullName, String department, String faculty, int course) {
        this.idOfCard = idOfCard;
        this.fullName = fullName;
        this.department = department;
        this.faculty = faculty;
        this.course = course;
    }

    public int getIdOfCard() {
        return idOfCard;
    }

    public void setIdOfCard(int idOfCard) {
        this.idOfCard = idOfCard;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
