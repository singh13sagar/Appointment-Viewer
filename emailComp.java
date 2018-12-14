import java.util.*;
/**
 * 
 * @author Sagar Singh Punn
 * 		   
 *
 */
public class emailComp implements Comparator<Person>
{
    public int compare(Person p1, Person p2)
    {
        return p1.getEmail().compareTo(p2.getEmail());
    }

}