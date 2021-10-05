import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class DoAction extends Action {

    private Connection con;
    private PreparedStatement pr;
    private Statement stm;
    private ResultSet rs;

    public void checkInformationAboutDB(){
        String query = "SELECT * FROM students ";
        String s = "C:\\Users\\USER\\Desktop\\tester\\text.txt";
        boolean x = Files.exists(Paths.get(s));
        OutputStream os = null;
        try {
            con = ConnectionManager.open();
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                int id_of_card = rs.getInt("id_of_card");
                String name = rs.getString("full_name");
                String faculty = rs.getString("faculty");
                String department = rs.getString("department");
                int course = rs.getInt("course");
                System.out.printf("%d. ФИО: %s\n Факультет: %s\n Кафедра: %s\n Курс:%d\n id Карты: %d\n\n",id,name,faculty,department,course,id_of_card);
                if(!x){
                    Files.createFile(Paths.get(s));
                }
                os = new FileOutputStream(s);
                os.write(name.getBytes(StandardCharsets.UTF_8),0,name.length());
                System.out.println("файл записан");
            }
            stm.close();
            con.close();
            if (con.isClosed()){
                System.out.println("Соединение с базой данных закрыто.\n\n");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        choseAction();
    }

    public void checkInfoAboutStudent(int a){
        String query = "SELECT * FROM students WHERE id_of_card = " + a;
        try {
            con = ConnectionManager.open();
            stm = con.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()){
                String name = rs.getString("full_name");
                String faculty = rs.getString("faculty");
                String department = rs.getString("department");
                int course = rs.getInt("course");
                System.out.printf(" ФИО: %s\n Факультет: %s\n Кафедра: %s\n Курс:%d\n",name,faculty,department,course);
            }
            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        choseAction();
    }

    public void createNewStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (id_of_card, full_name, department, faculty, course) VALUES (?,?,?,?,?)";
        try {
            con = ConnectionManager.open();
            pr = con.prepareStatement(query);
            pr.setInt(1, student.getIdOfCard());
            pr.setString(2,student.getFullName());
            pr.setString(3,student.getDepartment());
            pr.setString(4,student.getFaculty());
            pr.setInt(5,student.getCourse());
            pr.executeUpdate();

            pr.close();
            con.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        choseAction();

    }

    public void editInformationFullName(int a, String b){
        String insert = "UPDATE students set full_name=? WHERE id_of_card = ?";
        try{

            con = ConnectionManager.open();
            pr = con.prepareStatement(insert);
            pr.setString(1,b);
            pr.setInt(2,a);
            pr.executeUpdate();


            System.out.println("%d row,s edit");

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        choseAction();
    }

    public void editInformationFaculty(int a, String b){
        String insert = "UPDATE students set faculty=? WHERE id_of_card = ?";
        try{

            con = ConnectionManager.open();
            pr = con.prepareStatement(insert);
            pr.setString(1,b);
            pr.setInt(2,a);
            pr.executeUpdate();


            System.out.println("%d row,s edit");

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        choseAction();
    }

    public void editInformationDepartment(int a, String b){
        String insert = "UPDATE students set department=? WHERE id_of_card = ?";
        try{

            con = ConnectionManager.open();
            pr = con.prepareStatement(insert);
            pr.setString(1,b);
            pr.setInt(2,a);
            pr.executeUpdate();


            System.out.println("%d row,s edit");

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        choseAction();
    }

    public void editInformationCourse(int a, int b){
        String insert = "UPDATE students set course=? WHERE id_of_card = ?";
        try{

            con = ConnectionManager.open();
            pr = con.prepareStatement(insert);
            pr.setInt(1,b);
            pr.setInt(2,a);
            pr.executeUpdate();


            System.out.println("%d row,s edit");

            pr.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        choseAction();
    }

    public void deleteStudent(int a ){
        String query = "DELETE FROM students WHERE id_of_card = " + a;

        try{
            con = ConnectionManager.open();
            stm = con.createStatement();
            int d = stm.executeUpdate(query);
            System.out.printf("%d row,s deleted\n",d);

            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        choseAction();
    }
}
