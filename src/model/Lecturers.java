package model;

public class Lecturers {
    private String nameLecturer;
    private String faculty;
    private String phoneNumber;
    private String email;
    private String workPlace;
    private int maLop;

    public Lecturers() {
    }

    public Lecturers(String nameLecturer, String faculty, String phoneNumber, String email, String workPlace, int maLop) {
        this.nameLecturer = nameLecturer;
        this.faculty = faculty;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workPlace = workPlace;
        this.maLop = maLop;
    }

    public String getNameLecturer() {
        return nameLecturer;
    }

    public void setNameLecturer(String nameLecturer) {
        this.nameLecturer = nameLecturer;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public int getMaLop() {
        return maLop;
    }

    public void setMaLop(int maLop) {
        this.maLop = maLop;
    }
}
