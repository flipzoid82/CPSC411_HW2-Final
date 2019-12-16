package com.cpsc411.hw2_final;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.cpsc411.hw2_final.adapter.CourseListAdapter;
import com.cpsc411.hw2_final.model.CourseEnrollment;
import com.cpsc411.hw2_final.model.Student;
import com.cpsc411.hw2_final.model.StudentDB;
import java.util.ArrayList;

public class BlankStudentDetailsActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected String mFirstName;
    protected String mLastName;
    protected String mCWID;
    protected Student studentObj;
    protected CourseEnrollment courseObj;

    Button addBtn;

    ListView lv;
    protected CourseListAdapter ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_personal_details);

        studentObj = new Student(" "," ", " ");
        ArrayList<CourseEnrollment> courses = new ArrayList<>();
        studentObj.setCourses(courses);


        addBtn = findViewById(R.id.add_course_button);
        lv = findViewById(R.id.courseListView);
        ad = new CourseListAdapter(studentObj);
        lv.setAdapter(ad);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText idText = findViewById(R.id.edit_course_id);
                EditText gradeText = findViewById(R.id.edit_grade_id);

                courseObj = new CourseEnrollment(idText.getText().toString(), gradeText.getText().toString());
                studentObj.addCourse(courseObj);

                idText.setText(" ");
                gradeText.setText(" ");
                ad.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.blank_screen_menu, menu);
        menu.findItem(R.id.blank_action_edit).setVisible(false);
        menu.findItem(R.id.blank_action_add).setVisible(false);
        menu.findItem(R.id.blank_action_done).setVisible(true);
        detailMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.blank_action_add) {

            EditText fName = findViewById(R.id.s_first_name_id);
            EditText lName = findViewById(R.id.s_last_name_id);
            EditText cwid = findViewById(R.id.s_cwid_id);

            mFirstName = fName.getText().toString();
            mLastName = lName.getText().toString();
            mCWID = cwid.getText().toString();

            //Create NEW student
            studentObj = new Student(mFirstName,mLastName,mCWID);
            //add NEW student to DB
            StudentDB.getInstance().getStudentList().add(studentObj);


            detailMenu.findItem(R.id.blank_action_edit).setVisible(false);
            detailMenu.findItem(R.id.blank_action_add).setVisible(false);
            detailMenu.findItem(R.id.blank_action_done).setVisible(true);

            Intent intent = new Intent(this, SummaryLVActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.blank_action_done) {
            EditText editView = findViewById(R.id.s_first_name_id);
            editView.setEnabled(false);
            editView = findViewById(R.id.s_last_name_id);
            editView.setEnabled(false);
            editView = findViewById(R.id.s_cwid_id);
            editView.setEnabled(false);
            detailMenu.findItem(R.id.blank_action_edit).setVisible(true);
            detailMenu.findItem(R.id.blank_action_add).setVisible(true);
            detailMenu.findItem(R.id.blank_action_done).setVisible(false);

        } else if (item.getItemId() == R.id.blank_action_edit) {
            EditText editView = findViewById(R.id.s_first_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.s_last_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.s_cwid_id);
            editView.setEnabled(true);
            detailMenu.findItem(R.id.blank_action_edit).setVisible(false);
            detailMenu.findItem(R.id.blank_action_add).setVisible(false);
            detailMenu.findItem(R.id.blank_action_done).setVisible(true);
        }
        return super.onOptionsItemSelected(item);
    }
}

