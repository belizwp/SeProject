package kmitl.afinal.seproject.model;

public class SubjectView extends Subject {

    private String facultyName;
    private String departmentName;
    private String branchName;
    private int facultyId;
    private int departmentId;
    private int branchId;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public int getBranchId() {
        return branchId;
    }

    @Override
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }
}
