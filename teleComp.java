import java.util.Comparator;
/**
 * 
 * @author Sagar Singh Punn
 * 		 
 *
 */
public class teleComp implements Comparator<Person>
{
    public int compare(Person p1, Person p2)
    {
        return p1.getTelephone().compareTo(p2.getTelephone());
    }
}