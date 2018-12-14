
/**
 * 
 * @author Sagar Singh Punn
 * 		 
 *
 */
public class Person implements Comparable<Person>
{

    private String lastName;
    private String firstName;
    private String telephone;
    private String address;
    private String email;

    public Person()
    {
        lastName=null;
        firstName=null;
        telephone=null;
        address=null;
        email=null;
    }

    public Person(String lastName, String firstName, String telephone, String address, String email)
    {
        this.lastName=lastName;
        this.firstName=firstName;
        this.telephone=telephone;
        this.address=address;
        this.email=email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
    	this.email=email;
    }
    
    public String getTelephone()
    {
        return telephone;
    }
    public void setTelephone(String telephone)
    {
    	this.telephone=telephone;
    }

    public String getaddress()
    {
        return address;
    }
    public void setaddress(String address)
    {
        this.address=address;
    }
 
    public String toString()
    {
        //return "Name: "+firstName+" "+lastName+"\n"+"Telephone: "+telephone+"\n"+"Email: "+email;
        return firstName+" "+lastName+" "+telephone+" "+email;
    }
    public String getFname()
    {
        return firstName;
    }
    public void setFname(String firstname)
    {
    	this.firstName=firstname;
    }
    public String getLname()
    {
        return lastName;
        
    }
    public void setLname(String lastname)
    {
    	this.lastName=lastname;
    }

    public int compareTo(Person p)
    {
        if(this.getLname().equals(p.getLname()))
        {
            if(this.getFname().compareTo(p.getFname())>0)
                return 1;
            else if(this.getFname().compareTo(p.getFname())<0)
                return -1;
            else
                return 0;
        }
        else
        {
            if(this.getLname().compareTo(p.getLname())>0)
                return 1;
            else if(this.getLname().compareTo(p.getLname())<0)
                return -1;
            else
                return 0;
        }

    }




















}