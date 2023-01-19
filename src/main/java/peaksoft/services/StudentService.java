package peaksoft.services;

import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.util.List;
import java.util.Map;

/**
 * @author kurstan
 * @created at 19.01.2023 11:52
 */
public interface StudentService {
    //create table

    String  createTable();

    //save student

    String  saveStudents(Student student);

    //find by studentsId

    Student findByStudentId(Long studentId);

    //find all

    List<Student> findAllStudents();

    //update students

    String updateStudent(Long studentId, Student newStudent);

    //delete

    String deleteByStudentId(Long studentId);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void addColumnGender(Gender gender);

    Map<Gender, List<Student>> gruopByGender();

    void deleteAllStudents(); //ddl
}
