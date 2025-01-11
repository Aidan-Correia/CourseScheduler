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
import java.sql.Timestamp;

/**
 *
 * @author acv/aidan correia
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addSchedule;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static ResultSet scheduleByStudentSet;
    private static ResultSet countSet;
  
    public static void addScheduleEntry(ScheduleEntry schedule)
    {
        connection = DBConnection.getConnection();
        try
        {
            addSchedule = connection.prepareStatement("insert into app.schedules (semester, courseCode, studentID, status, time) values (?, ?, ?, ?, ?)");
            addSchedule.setString(1, schedule.getSem());
            addSchedule.setString(2, schedule.getcc());
            addSchedule.setString(3, schedule.getid());
            addSchedule.setString(4, schedule.getStatus());
            addSchedule.setString(5, schedule.getTime().toString());
            addSchedule.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<ScheduleEntry>();
        
        try
        {
            getScheduleByStudent = connection.prepareStatement("select * from app.schedules where semester = ? and studentID = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            scheduleByStudentSet = getScheduleByStudent.executeQuery();
            
            while(scheduleByStudentSet.next())
            {
                schedules.add(new ScheduleEntry(scheduleByStudentSet.getString(1), scheduleByStudentSet.getString(2), scheduleByStudentSet.getString(3), scheduleByStudentSet.getString(4), scheduleByStudentSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return schedules;
        
    }
    
    public static int getScheduledStudentCount(String sem, String cc){
        connection = DBConnection.getConnection();
        int count = -1;
        try{
            getScheduledStudentCount = connection.prepareStatement("select count(studentID) from app.schedules where semester = ? and courseCode = ?");
            getScheduledStudentCount.setString(1, sem);
            getScheduledStudentCount.setString(2, cc);
            countSet = getScheduledStudentCount.executeQuery();
            
            while (countSet.next()){
                count = countSet.getInt(1);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return count;
    }
    
    
}
