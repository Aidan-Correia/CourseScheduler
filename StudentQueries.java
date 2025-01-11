/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursescheduler1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author acv/aidan correia
 */
public class StudentQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addStudent;
    private static PreparedStatement getStudentList;
    private static PreparedStatement getStudentIDList;
    private static ResultSet idSet;
    private static ResultSet studentSet;
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.students (studentID, firstName, lastName) values (?, ?, ?)");
            addStudent.setString(1, student.getID());
            addStudent.setString(2, student.getfn());
            addStudent.setString(3, student.getln());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getStudentList = connection.prepareStatement("select (studentID, firstName, lastName) from app.students");
            studentSet = getStudentList.executeQuery();
            
            while(studentSet.next())
            {
                students.add(new StudentEntry(studentSet.getString(1), studentSet.getString(2), studentSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
        
    }
    
     public static ArrayList<String> getAllStudentID()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> studentIDs = new ArrayList<String>();
        try
        {
            getStudentIDList = connection.prepareStatement("select studentID from app.students");
            idSet = getStudentIDList.executeQuery();
            
            while(idSet.next())
            {
                studentIDs.add(idSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentIDs;
        
    }
    
    
}
