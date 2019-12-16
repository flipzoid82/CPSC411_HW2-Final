package com.cpsc411.hw2_final.model;

import java.util.ArrayList;

public class StudentDB { //This is a singleton
    private static final StudentDB ourInstance = new StudentDB();
    public static StudentDB getInstance() {
        return ourInstance;
    }

    private ArrayList<Student> mStudentList;
    private ArrayList<CourseEnrollment> mCourses;
    private Student tempStudent;

    private StudentDB() {
        createStudentObjects();
    }

    public ArrayList<Student> getStudentList() {return mStudentList; }

    public void setCourses(ArrayList<CourseEnrollment> c) { mCourses = c; }


    protected void createStudentObjects() {
        Student student = new Student("Joesh", "Bautista", "221323");
        mCourses = new ArrayList<CourseEnrollment>();
        mCourses.add(new CourseEnrollment("CPSC 349","A"));
        mCourses.add(new CourseEnrollment("CPSC 456", "A"));
        mCourses.add(new CourseEnrollment("CPSC 411", "B"));
        student.setCourses(mCourses);
        mStudentList = new ArrayList<Student>();
        mStudentList.add(student);

        student = new Student("Hector", "Rodriguez","222222");
        mCourses = new ArrayList<CourseEnrollment>();
        mCourses.add(new CourseEnrollment("CPSC 456", "A"));
        mCourses.add(new CourseEnrollment("CPSC 454", "C+"));
        mCourses.add(new CourseEnrollment("CPSC 411", "A+"));
        mStudentList.add(student);
    }

    public void addStudentObject(Student student) {
        tempStudent = student;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        mStudentList = studentList;
    }
}
