/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursescheduler1;
import java.util.Date;
import java.sql.Timestamp;
/**
 *
 * @author aidan correia
 */
public class ScheduleEntry {
    private String semester, courseCode, studentID, status;
    private Timestamp time;
    
    public ScheduleEntry(String s, String c, String id, String stat, Timestamp t){
        semester = s;
        courseCode = c;
        studentID = id;
        status = stat;
        time = t;
    }
    
    public String getSem(){
        return semester;
    }
    public String getcc(){
        return courseCode;
    }
    public String getid(){
        return studentID;
    }
    public String getStatus(){
        return status;
    }
    public Timestamp getTime(){
        return time;
    }
}
