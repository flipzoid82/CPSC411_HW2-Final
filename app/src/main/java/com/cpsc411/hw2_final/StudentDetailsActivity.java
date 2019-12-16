package com.cpsc411.hw2_final;

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

public class StudentDetailsActivity extends AppCompatActivity {

    protected Menu detailMenu;
    protected int studentIndex;
    protected Student studentObj;

    ListView lv;
    protected CourseListAdapter ad;
    protected CourseEnrollment courseObj;

    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_personal_details);


        studentIndex = getIntent().getIntExtra("StudentIndex", 0);

        studentObj = StudentDB.getInstance().getStudentList().get(studentIndex);

        EditText editView = findViewById(R.id.s_first_name_id);
        editView.setText(studentObj.getFirstName());
        editView.setEnabled(false);
        editView = findViewById(R.id.s_last_name_id);
        editView.setText(studentObj.getLastName());
        editView.setEnabled(false);
        editView = findViewById(R.id.s_cwid_id);
        editView.setText(studentObj.getCWID());
        editView.setEnabled(false);


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
                studentObj.setCourses(studentObj.getCourses());

                idText.setText(" ");
                gradeText.setText(" ");
                ad.notifyDataSetChanged();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_screen_menu, menu);
        menu.findItem(R.id.action_edit).setVisible(true);
        menu.findItem(R.id.action_done).setVisible(false);
        detailMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            EditText editView = findViewById(R.id.s_first_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.s_last_name_id);
            editView.setEnabled(true);
            editView = findViewById(R.id.s_cwid_id);
            editView.setEnabled(true);
            //
            item.setVisible(false);
            detailMenu.findItem(R.id.action_done).setVisible(true);
        } else if (item.getItemId() == R.id.action_done) {
            EditText editView = findViewById(R.id.s_first_name_id);
            StudentDB.getInstance().getStudentList().get(studentIndex).setFirstName(editView.getText().toString());
            editView.setEnabled(false);
            editView = findViewById(R.id.s_last_name_id);
            StudentDB.getInstance().getStudentList().get(studentIndex).setLastName(editView.getText().toString());
            editView.setEnabled(false);
            editView = findViewById(R.id.s_cwid_id);
            StudentDB.getInstance().getStudentList().get(studentIndex).setCWID(editView.getText().toString());
            editView.setEnabled(false);
            item.setVisible(false);
            detailMenu.findItem(R.id.action_edit).setVisible(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
