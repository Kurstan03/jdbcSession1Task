package peaksoft.services;

import peaksoft.dao.StudentDao;
import peaksoft.dao.StudentDaoImpl;
import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.util.List;
import java.util.Map;

/**
 * @author kurstan
 * @created at 19.01.2023 11:52
 */
public class StudentServiceImpl implements StudentService{
    StudentDao studentDao = new StudentDaoImpl();
    @Override
    public String createTable() {
        studentDao.createTable();
        return "Successfully created table!";
    }

    @Override
    public String saveStudents(Student student) {
        studentDao.saveStudents(student);
        return student.getName() + " - successfully saved!!!";
    }

    @Override
    public Student findByStudentId(Long studentId) {
        return studentDao.findByStudentId(studentId);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentDao.findAllStudents();
    }

    @Override
    public String updateStudent(Long studentId, Student newStudent) {
        studentDao.updateStudent(studentId, newStudent);
        return newStudent.getName() + " - successfully updated!";
    }

    @Override
    public String deleteByStudentId(Long studentId) {
        studentDao.deleteByStudentId(studentId);
        return studentId + " is successfully deleted";
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
       return studentDao.getAllStudentsSortByAge(ascOrDesc);

    }

    @Override
    public boolean checkByAge() {
        return studentDao.checkByAge();
    }

    @Override
    public void addColumnGender(Gender gender) {
        studentDao.addColumnGender(gender);
    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
        return studentDao.gruopByGender();

    }

    @Override
    public void deleteAllStudents() {
        studentDao.deleteAllStudents();
    }
}
