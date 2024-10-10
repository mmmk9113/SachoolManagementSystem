package model;

import java.util.Date;

public class Exam {
    private int exam_id;
    private Date exam_date;
    private float exam_grade;

    public Exam(int exam_id, Date exam_date, float exam_grade) {
        this.exam_id = exam_id;
        this.exam_date = exam_date;
        this.exam_grade = exam_grade;
    }

    public Exam() {
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public Date getExam_date() {
        return exam_date;
    }

    public void setExam_date(Date exam_date) {
        this.exam_date = exam_date;
    }

    public float getExam_grade() {
        return exam_grade;
    }

    public void setExam_grade(float exam_grade) {
        this.exam_grade = exam_grade;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "exam_id=" + exam_id +
                ", exam_date=" + exam_date +
                ", exam_grade=" + exam_grade +
                '}';
    }
}
