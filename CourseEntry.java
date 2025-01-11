/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursescheduler1;

/**
 *
 * @author Aidan Correia
 */
public class CourseEntry {
    private String semester, courseCode, description;
    private int seats;
    public CourseEntry(String s, String c, String d, int spots){
        semester = s;
        courseCode = c;
        description = d;
        seats = spots;
    }
    public String getSemester(){
        return semester;
    }
    public String getCourseCode(){
        return courseCode;
    }
    public String getDescription(){
        return description;
    }
    public int getSeats(){
        return seats;
    }
    @Override
    public String toString(){
        return courseCode;
    }
}
