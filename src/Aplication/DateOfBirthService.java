package Aplication;

import Person.DateOfBirth;

import java.util.Vector;

public class DateOfBirthService {
    protected Vector<DateOfBirth> date ;
    private int MaxiNrDate;

    public DateOfBirthService(){
        this.date = new Vector<>();

    }

    public void addDateOfBirth(DateOfBirth data) {
        this.date.add(new DateOfBirth(data));
        //System.out.println("S-a adaugat data de nastere " + data);
    }

    public DateOfBirth elementAt(int id){
        return date.elementAt(id);
    }

    public DateOfBirth lastElement(){
        return date.elementAt(date.size()-1);
    }

    public void addDateOfBirth(int day,int month,int year){
        this.date.add(new DateOfBirth(day,month,year));
        //System.out.println("S-a adaugat data de nastere " + day + "-" + month + "-" + year);
    }

    @Override
    public String toString() {
        return " "+date ;
    }
}
