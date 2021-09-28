import com.sun.xml.internal.bind.v2.runtime.output.UTF8XmlOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Action {

    private Connection con;
    private PreparedStatement pr;
    private Statement stm;
    private ResultSet rs;

    private final String[] actionWords = {"0.Назад","1.Посмотреть информацию по всем пользователям","2.Посмотреть информацию по id","3.Добавить пользователя","4.Редактировать информацию о пользователе",
            "5.Удалить пользователя из БД","Информация по БД","Посмотреть информацию по id","Приложите карту: ","Добавление пользователя.","Введите ФИО: ","Введите название факультета: ",
            "Введите название кафедры: ","Курс: ","Редактирование пользователя.","Какую колонку отредактировать?","1.ФИО","2.Факультет","3.Кафедра","4.Курс","Введите отредактированную информацию:","Вы ввели неверную цифру","Удаление пользователя."};

    public void choseAction() {
        System.out.println(actionWords[1]+"\n" + actionWords[2]+"\n" + actionWords[3]+"\n" +
                actionWords[4]+"\n" + actionWords[5]);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            int idOfCard;
            String fullName;
            String department;
            String faculty;
            int course;

            int a = Integer.parseInt(reader.readLine());
            switch (a){
                case 1 :
                    System.out.println(actionWords[7] + "\n");
                    checkInformationAboutDB();
                    break;
                case 2:
                    System.out.println(actionWords[7]+"\n"+actionWords[8]+"\n");
                    idOfCard = Integer.parseInt(reader.readLine());
                    System.out.println("\n"+actionWords[0]);
                    if(idOfCard == 0){
                        choseAction();
                    }
                    checkInfoAboutStudent(idOfCard);
                    break;
                case 3:
                    System.out.println(actionWords[9]+"\n"+actionWords[8]);
                    idOfCard = Integer.parseInt(reader.readLine());
                    System.out.println(actionWords[10]+"\n");
                    fullName = reader.readLine();
                    System.out.println(actionWords[11]+"\n");
                    faculty = reader.readLine();
                    System.out.println(actionWords[12]+"\n");
                    department = reader.readLine();
                    System.out.println(actionWords[13]+"\n");
                    course = Integer.parseInt(reader.readLine());
                    Student student = new Student(idOfCard, fullName, department, faculty, course);
                    createNewStudent(student);
                    break;
                case 4:
                    System.out.println(actionWords[14]+"\n"+ actionWords[15]+"\n"+actionWords[16]+"\n"+actionWords[17]+"\n"+
                            actionWords[18]+"\n"+actionWords[19]+"\n"+actionWords[0]+"\n");
                    int b = Integer.parseInt(reader.readLine());
                    switch (b){
                        case 0: choseAction();
                        break;
                        case 1:
                            System.out.println(actionWords[20]);
                            fullName = reader.readLine();
                            System.out.println(actionWords[8]);
                            idOfCard = Integer.parseInt(reader.readLine());
                            editInformationFullName(idOfCard, fullName);
                            break;
                        case 2:
                            System.out.println(actionWords[20]);
                            faculty = reader.readLine();
                            System.out.println(actionWords[8]);
                            idOfCard = Integer.parseInt(reader.readLine());
                            editInformationFaculty(idOfCard, faculty);
                            break;
                        case 3:
                            System.out.println(actionWords[20]);
                            department = reader.readLine();
                            System.out.println(actionWords[8]);
                            idOfCard = Integer.parseInt(reader.readLine());
                            editInformationDepartment(idOfCard, department);
                            break;
                        case 4:
                            System.out.println(actionWords[20]);
                            course = Integer.parseInt(reader.readLine());
                            System.out.println(actionWords[8]);
                            idOfCard = Integer.parseInt(reader.readLine());
                            editInformationCourse(idOfCard, course);
                            break;
                    }
                case 5:
                    System.out.println("\n"+actionWords[0]);
                    System.out.println(actionWords[22]+"\n"+ actionWords[8]);
                    idOfCard = Integer.parseInt(reader.readLine());
                    if(idOfCard == 0){
                        choseAction();
                    }
                    deleteStudent(idOfCard);
                    break;

            }
        }catch (IOException | SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void checkInformationAboutDB(){
        String query = "SELECT * FROM students ";
        try {
            con = ConnectionManager.open();
            stm = ConnectionManager.createStatement(con);
            rs = stm.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt("id");
                int id_of_card = rs.getInt("id_of_card");
                String name = rs.getString("full_name");
                String faculty = rs.getString("faculty");
                String department = rs.getString("department");
                int course = rs.getInt("course");
                System.out.printf("%d. ФИО: %s\n Факультет: %s\n Кафедра: %s\n Курс:%d\n id Карты: %d\n\n",id,name,faculty,department,course,id_of_card);
            }
            stm.close();
            con.close();
            if (con.isClosed()){
                System.out.println("Соединение с базой данных закрыто.\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        choseAction();
    }

    public void checkInfoAboutStudent(int a){
        String query = "SELECT * FROM students WHERE id_of_card = " + a;
        try {
            con = ConnectionManager.open();
            stm = ConnectionManager.createStatement(con);
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
            stm = ConnectionManager.createStatement(con);
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
