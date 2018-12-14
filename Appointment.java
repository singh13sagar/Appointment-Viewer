

import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * 
 * @author Sagar Singh Punn 
 * This class create objects which contain date and time at which it occurs
 * and it also takes in description describing the event.
 * @param date is the an calendar object that takes in year, month, date and time
 * 
 *
 */


public class Appointment implements Comparable<Appointment>
{
    private Calendar date;
    private String description;
    private Person person;

    public Appointment(int year, int month, int day, int hour, int minute, String description, Person person)
    {
        date= new GregorianCalendar(year,month,day,hour,minute);
        this.description= description;
        this.person=person;
    }

    public String getDescription()
    {
        return description;
    }
    public Calendar getDate()
    {
        return date;
    }
    public Person getPerson()
    {
        return person;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public void setPerson(Person person)
    {
        this.person=person;
    }
    public String toString()
    {
        return String.format("%02d:%02d", date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE))+" "+ this.description+" with "+person.toString();
        
    }
    /**
     * This method checks if the appointment object occurs on same date time that are passed
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @return boolean value
     */
    public boolean occurOn(int year, int month, int day, int hour, int minute)
    {
        Calendar tempdate = new GregorianCalendar(year,month,day,hour,minute);
        if(getDate().equals(tempdate))
            return true;
        else
            return false;
    }
    
    public int compareTo(Appointment abc)
    {
        if(date.get(Calendar.HOUR_OF_DAY)>abc.getDate().get(Calendar.HOUR_OF_DAY))
            return 1;
        else if(date.get(Calendar.HOUR_OF_DAY)==abc.getDate().get(Calendar.HOUR_OF_DAY)&&date.get(Calendar.MINUTE)>abc.getDate().get(Calendar.MINUTE))
            return 0;
        else
            return -1;
    }

}