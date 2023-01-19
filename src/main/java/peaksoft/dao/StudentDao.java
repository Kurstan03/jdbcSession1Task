package peaksoft.dao;

import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.util.List;
import java.util.Map;

/**
 * @author kurstan
 * @created at 19.01.2023 11:51
 */
public interface StudentDao {
    //create table

    void createTable();

    //save student

    void saveStudents(Student student);

    //find by studentsId

    Student findByStudentId(Long studentId);

    //find all

    List<Student> findAllStudents();

    //update students

    void updateStudent(Long studentId, Student newStudent);

    //delete

    void deleteByStudentId(Long studentId);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void addColumnGender();

    Map<Gender, List<Student>> gruopByGender();

    void deleteAllStudents(); //ddl
}
