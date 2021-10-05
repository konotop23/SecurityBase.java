
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Action {

    private final String[] actionWords = {"0.Назад","1.Посмотреть информацию по всем пользователям","2.Посмотреть информацию по id","3.Добавить пользователя","4.Редактировать информацию о пользователе",
            "5.Удалить пользователя из БД","Информация по БД","Посмотреть информацию по id","Приложите карту: ","Добавление пользователя.","Введите ФИО: ","Введите название факультета: ",
            "Введите название кафедры: ","Курс: ","Редактирование пользователя.","Какую колонку отредактировать?","1.ФИО","2.Факультет","3.Кафедра","4.Курс","Введите отредактированную информацию:","Вы ввели неверную цифру","Удаление пользователя."};


    public void choseAction() {
        System.out.println(actionWords[1]+"\n" + actionWords[2]+"\n" + actionWords[3]+"\n" +
                actionWords[4]+"\n" + actionWords[5]);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){

            DoAction doAction = new DoAction();
            int idOfCard;
            String fullName;
            String department;
            String faculty;
            int course;

            int a = Integer.parseInt(reader.readLine());
            switch (a){
                case 1 :
                    System.out.println(actionWords[6] + "\n");
                    doAction.checkInformationAboutDB();
                    break;
                case 2:
                    System.out.println(actionWords[7]+"\n"+actionWords[8]+"\n");
                    idOfCard = Integer.parseInt(reader.readLine());
                    System.out.println("\n"+actionWords[0]);
                    if(idOfCard == 0){
                        choseAction();
                    }
                    doAction.checkInfoAboutStudent(idOfCard);
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
                    doAction.createNewStudent(student);
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
                            doAction.editInformationFullName(idOfCard, fullName);
                            break;
                        case 2:
                            System.out.println(actionWords[20]);
                            faculty = reader.readLine();
                            System.out.println(actionWords[8]);
                            idOfCard = Integer.parseInt(reader.readLine());
                            doAction.editInformationFaculty(idOfCard, faculty);
                            break;
                        case 3:
                            System.out.println(actionWords[20]);
                            department = reader.readLine();
                            System.out.println(actionWords[8]);
                            idOfCard = Integer.parseInt(reader.readLine());
                            doAction.editInformationDepartment(idOfCard, department);
                            break;
                        case 4:
                            System.out.println(actionWords[20]);
                            course = Integer.parseInt(reader.readLine());
                            System.out.println(actionWords[8]);
                            idOfCard = Integer.parseInt(reader.readLine());
                            doAction.editInformationCourse(idOfCard, course);
                            break;
                    }
                case 5:
                    System.out.println("\n"+actionWords[0]);
                    System.out.println(actionWords[22]+"\n"+ actionWords[8]);
                    idOfCard = Integer.parseInt(reader.readLine());
                    if(idOfCard == 0){
                        choseAction();
                    }
                    doAction.deleteStudent(idOfCard);
                    break;
            }
        }catch (IOException | SQLException e){
            throw new RuntimeException(e);
        }
    }

}
