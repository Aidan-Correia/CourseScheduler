/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursescheduler1;

/**
 *
 * @author aidan correia
 */
public class StudentEntry {
    private String studentID, firstName, lastName;
    
    public StudentEntry(String id, String fn, String ln){
        studentID = id;
        firstName = fn;
        lastName = ln;
    }
    public String getID(){
        return studentID;
    }
    public String getfn(){
        return firstName;
    }
    public String getln(){
        return lastName;
    }
    @Override
    public String toString(){
        return studentID;
    }
}
