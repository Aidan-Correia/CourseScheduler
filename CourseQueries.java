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
public class CourseQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addCourse;
    private static PreparedStatement getCourseList;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static ResultSet courseSet;
    private static ResultSet codeSet;
    private static ResultSet seatSet;
    
    public static void addCourse(CourseEntry course)
    {
        connection = DBConnection.getConnection();
        try
        {
            addCourse = connection.prepareStatement("insert into app.courses (semester, courseCode, description, seats) values (?, ?, ?, ?)");
            addCourse.setString(1, course.getSemester());
            addCourse.setString(2, course.getCourseCode());
            addCourse.setString(3, course.getDescription());
            addCourse.setInt(4, course.getSeats());
            addCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<CourseEntry> getAllCourses(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        
        try
        {
            getCourseList = connection.prepareStatement("select * from app.courses where semester = ?");
            getCourseList.setString(1, semester);
            courseSet = getCourseList.executeQuery();
            
            while(courseSet.next())
            {
                courses.add(new CourseEntry(courseSet.getString(1), courseSet.getString(2), courseSet.getString(3),courseSet.getInt(4)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return courses;
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> ccs = new ArrayList<String>();
        try
        {
            getAllCourseCodes = connection.prepareStatement("select (courseCode) from app.courses where semester = ?");
            getAllCourseCodes.setString(1, semester);
            codeSet = getAllCourseCodes.executeQuery();
            
            while(codeSet.next())
            {
                ccs.add(codeSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return ccs;
    }
    
    public static int getCourseSeats(String sem, String cc){
        connection = DBConnection.getConnection();
        int count = 0;
        try
        {
            getCourseSeats = connection.prepareStatement("select (seats) from app.courses where semester = ? and courseCode = ?");
            getCourseSeats.setString(1, sem);
            getCourseSeats.setString(2, cc);
            seatSet = getCourseSeats.executeQuery();
            
            while(seatSet.next())
            {
                count = seatSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }

    
}
