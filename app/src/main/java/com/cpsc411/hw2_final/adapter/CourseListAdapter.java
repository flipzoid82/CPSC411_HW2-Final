package com.cpsc411.hw2_final.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cpsc411.hw2_final.R;
import com.cpsc411.hw2_final.model.CourseEnrollment;
import com.cpsc411.hw2_final.model.Student;
import com.cpsc411.hw2_final.model.StudentDB;

import java.util.ArrayList;

public class CourseListAdapter extends BaseAdapter {
    protected ArrayList<CourseEnrollment> mCourseEnrollments;

    public CourseListAdapter(Student student) {
        mCourseEnrollments = student.getCourses();
    }

    @Override
    public int getCount() {
        return mCourseEnrollments.size();
    }

    @Override
    public Object getItem(int i) {
        return StudentDB.getInstance().getStudentList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row_view;
        // inflate the layout for each list row
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.course_row, viewGroup, false);
        } else {row_view =view;}


        ((TextView) row_view.findViewById(R.id.course_id_row)).setText(mCourseEnrollments.get(i).getCourseID());
        ((TextView) row_view.findViewById(R.id.grade_id_row)).setText(mCourseEnrollments.get(i).getGrade());


        return row_view;
    }
}