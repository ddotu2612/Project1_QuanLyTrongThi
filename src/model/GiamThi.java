package model;

public class Supervisor {
    private String nameLecturer;
    private String faculty;
    private String phoneNumber;
    private String email;
    private String workPlace;
    private int soBuoiToiDa;

    public Supervisor() {
    }

    public int getSoBuoiToiDa() {
        return soBuoiToiDa;
    }

    public void setSoBuoiToiDa(int soBuoiToiDa) {
        this.soBuoiToiDa = soBuoiToiDa;
    }

    public Supervisor(String nameLecturer, String faculty, String phoneNumber, String email, String workPlace, int soBuoiToiDa) {
        this.nameLecturer = nameLecturer;
        this.faculty = faculty;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workPlace = workPlace;
        this.soBuoiToiDa =soBuoiToiDa;
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
}
