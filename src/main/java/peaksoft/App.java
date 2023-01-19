package peaksoft;

import peaksoft.enums.Gender;
import peaksoft.models.Student;
import peaksoft.services.StudentService;
import peaksoft.services.StudentServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        StudentService studentService = new StudentServiceImpl();
//        System.out.println(studentService.createTable());
//        System.out.println(studentService.saveStudents(new Student("Kurstan", (byte) 19)));
//        System.out.println(studentService.saveStudents(new Student("Azimbek", (byte) 18)));
//        System.out.println(studentService.saveStudents(new Student("Baiysh", (byte) 22)));
//        System.out.println(studentService.saveStudents(new Student("Osmon", (byte) 18)));
//        System.out.println(studentService.findByStudentId(4L));

//        System.out.println(studentService.findAllStudents());
//        System.out.println(studentService.updateStudent(4L, new Student("Jyidegul", (byte) 18)));
//        studentService.findAllStudents().forEach(System.out::println);
//        System.out.println(studentService.deleteByStudentId(6L));
//        studentService.findAllStudents().forEach(System.out::println);
//        System.out.println(studentService.addColumnGender());
//        for (Map.Entry<Gender, List<Student>> genderListEntry : studentService.gruopByGender().entrySet()) {
//            System.out.println(genderListEntry);
//        }
        System.out.println(studentService.deleteAllStudents());

    }
}
