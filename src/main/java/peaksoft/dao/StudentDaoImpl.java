package peaksoft.dao;

import peaksoft.config.DatabaseConnection;
import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.sql.*;
import java.util.*;

/**
 * @author kurstan
 * @created at 19.01.2023 11:51
 */
public class StudentDaoImpl implements StudentDao{

    private Connection connection;

    public StudentDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void createTable() {
        String query = """
                create table if not exists students(
                id serial primary key ,
                name varchar(50) not null,
                age smallint not null
                )
                """;

        try (Statement statement = connection.createStatement()){
            statement.execute(query);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveStudents(Student student) {
        String query = "INSERT INTO students(name, age) VALUES (?,?)";
        try (Statement statement = connection.createStatement();){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setByte(2,student.getAge());
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Student findByStudentId(Long studentId) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,studentId);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            Student student = new Student();
//            while (resultSet.next()) {
            if(!resultSet.next()){
                System.out.println("Does not exist!");
            }
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
//            }
            resultSet.close();
            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        String query = " SELECT * FROM students;";

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));

                allStudents.add(student);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allStudents;
    }

    @Override
    public void updateStudent(Long studentId, Student newStudent) {
        String query = "UPDATE students set name = ?, age = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setInt(2, newStudent.getAge());
            preparedStatement.setLong(3, studentId);
            preparedStatement.executeUpdate();
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println(newStudent.getName() + " - successfully updated!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByStudentId(Long studentId) {
        String query = "DELETE FROM students WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);){
            preparedStatement.setLong(1, studentId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        List<Student> studentList = new ArrayList<>();
        String query = null;
        System.out.println("Enter select command : (age / name");
        String word = new Scanner(System.in).nextLine();
        switch (word){
            case "age"->{
                if (ascOrDesc.equals("asc")){
                    query = "select * from students order by age";
                }
                else {
                    query = "select * from students order by age desc ";
                }
            }case "name"-> {
                if (ascOrDesc.equals("asc")){
                    query = "select * from students order by name";
                }
                else {
                    query = "select * from students order by name desc ";
                }
            }
        }
        try( Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getByte("age"));
                studentList.add(student);
            }
            resultSet.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean checkByAge() {
//        String query = "SELECT age > 18 FROM students";
//        Statement statement = null;
//        try {
//            statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return false;
        String s = """
                SELECT * FROM students WHERE age > 18;
                """;
        try (Statement statement =connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(s);
            boolean isTrue = false;
            isTrue = resultSet.next();
            resultSet.close();
            return isTrue;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addColumnGender(Gender gender) {
        String cre = "create type gender us enum ('FEMALE', 'MALE');";
        Statement statement1 = null;
        try {
            statement1 = connection.createStatement();
            statement1.executeQuery(cre);
            statement1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String sql= """
                alter table students add column gender gender;
                """;
        try(Statement statement=connection.createStatement()){
            statement.executeQuery(sql);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
            Map<Gender,List<Student>> result=new HashMap<>();
            List<Student>girls=new ArrayList<>();
            List<Student>boys=new ArrayList<>();


            String sql= """
                select * from students where gender='FEMALE'; 
                """;
            try(Statement statement=connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString(2));
                    student.setAge(resultSet.getByte(3));
                    girls.add(student);
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }

            String sql1= """
                select * from students where gender='MALE'; 
                """;
            try(Statement statement1=connection.createStatement()){
                ResultSet resultSet1 = statement1.executeQuery(sql1);
                while (resultSet1.next()) {
                    Student student = new Student();
                    student.setId(resultSet1.getLong("id"));
                    student.setName(resultSet1.getString(2));
                    student.setAge(resultSet1.getByte(3));
                    boys.add(student);
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            result.put(Gender.FEMALE, girls);
            result.put(Gender.MALE, boys);

            return result;

    }

    @Override
    public void deleteAllStudents() {
        String sql = """
                truncate table students;
                """;
        try(Statement statement=connection.createStatement()){
            statement.executeQuery(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
