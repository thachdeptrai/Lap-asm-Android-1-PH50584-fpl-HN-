package com.meomeo.lap5lap6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.SearchView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.meomeo.lap5lap6.R; // Đảm bảo import từ đúng package
import com.meomeo.lap5lap6.Adapter.ListStudentAdapter;
import com.meomeo.lap5lap6.model.Student;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ListView lvStudents;
    ArrayList<Student> studentList;
    ListStudentAdapter adapter;
    private static final int ADD_STUDENT_REQUEST_CODE = 1;
    private static final int UPDATE_STUDENT_REQUEST_CODE = 2; // Mã yêu cầu cho cập nhật

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Thiết lập Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hiển thị nút "Back" trên Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Xử lý sự kiện quay lại với onBackPressedDispatcher()
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Chuyển về màn hình LoginActivity khi nhấn nút "Back"
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Đóng HomeActivity
            }
        });

        lvStudents = findViewById(R.id.lvStudents);
        studentList = new ArrayList<>();

        // Thêm dữ liệu ban đầu vào danh sách sinh viên
        studentList.add(new Student("FPoly Hà Nội", "Nguyễn Văn A", "Lào Cai"));
        studentList.add(new Student("FPoly Đà Nẵng", "Nguyễn Văn B", "Quảng Nam"));

        adapter = new ListStudentAdapter(this, studentList);
        lvStudents.setAdapter(adapter);

        // Thêm mới sinh viên bằng Intent
        Button btnAddNew = findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE);
        });

        // Thiết lập sự kiện nhấn vào sinh viên để cập nhật thông tin
        lvStudents.setOnItemClickListener((parent, view, position, id) -> {
            Student selectedStudent = studentList.get(position);
            Intent intent = new Intent(HomeActivity.this, UpdateStudentActivity.class);
            // Gửi thông tin sinh viên để sửa
            intent.putExtra("name", selectedStudent.getName());
            intent.putExtra("address", selectedStudent.getAddress());
            intent.putExtra("branch", selectedStudent.getBranch());
            startActivityForResult(intent, UPDATE_STUDENT_REQUEST_CODE); // Mở Activity cập nhật
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Lấy dữ liệu từ Intent trả về khi thêm mới
            String name = data.getStringExtra("name");
            String address = data.getStringExtra("address");
            String branch = data.getStringExtra("branch");

            // Thêm sinh viên mới vào danh sách
            studentList.add(new Student(branch, name, address));
            adapter.notifyDataSetChanged();

        } else if (requestCode == UPDATE_STUDENT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Lấy dữ liệu từ Intent trả về khi cập nhật
            String updatedName = data.getStringExtra("name");
            String updatedAddress = data.getStringExtra("address");
            String updatedBranch = data.getStringExtra("branch");

            // Cập nhật thông tin sinh viên trong danh sách
            for (int i = 0; i < studentList.size(); i++) {
                Student student = studentList.get(i);
                if (student.getName().equals(updatedName)) {
                    student.setAddress(updatedAddress);
                    student.setBranch(updatedBranch);
                    adapter.notifyDataSetChanged(); // Cập nhật lại giao diện
                    break; // Ngừng vòng lặp sau khi cập nhật
                }
            }
        }
    }
    private void searchStudent(String query) {
        ArrayList<Student> filteredList = new ArrayList<>();

        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(student);
            }
        }

        // Cập nhật lại adapter với danh sách sinh viên đã lọc
        adapter = new ListStudentAdapter(this, filteredList);
        lvStudents.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Lấy item "Tìm kiếm"
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        // Kiểm tra searchItem có null không
        if (searchItem != null) {
            // Đảm bảo sử dụng đúng SearchView của AndroidX
            androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();

            // Kiểm tra searchView có null không
            if (searchView != null) {
                // Xử lý sự kiện khi người dùng nhập vào SearchView
                searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        searchStudent(query);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        searchStudent(newText);
                        return false;
                    }
                });
            } else {
                // Thông báo nếu searchView bị null
                Log.e("HomeActivity", "SearchView is null");
            }
        } else {
            // Thông báo nếu searchItem bị null
            Log.e("HomeActivity", "MenuItem for search is null");
        }
        return true;
    }



    private void openSchedule() {
        // Code điều hướng tới màn hình lịch học
    }

    private void openGrades() {
        // Code điều hướng tới màn hình bảng điểm
    }

    private void openAttendance() {
        // Code điều hướng tới màn hình điểm danh
    }

    private void performLogout() {
        // Thực hiện thao tác đăng xuất
        Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed(); // Gọi lại sự kiện xử lý khi nhấn nút "Back"
            return true;
        }
        if (item.getItemId() == R.id.menu_add_student) {
            // Mở Activity thêm sinh viên
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menu_logout) {
            // Thực hiện đăng xuất
            performLogout();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }}
