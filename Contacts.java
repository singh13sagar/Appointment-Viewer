import java.util.LinkedList;
import java.util.*;
import java.util.List;
import java.io.*;

/**
 * 
 * @author Sagar Singh Punn
 * 		 
 *
 */
public class Contacts
{
    private List<Person> pList;

    public Contacts()
    {
        pList= new LinkedList<Person>();

    }
    public void addContact(Person person)
    {
        pList.add(person);
    }
	public void deleteContact(Person person)
	{
		pList.remove(person);
	}
    public Person FindPerson(String firstname, String lastname)
    {
    	ListIterator<Person> iter= pList.listIterator();
    	Person temp= new Person();
    	Person comp=new Person();
    	comp.setFname(firstname);
    	comp.setLname(lastname);
    	
    	while(iter.hasNext())
    	{
    		temp=iter.next();
			if(temp.compareTo(comp)==0)
    			{	
					break;}
    		else
    			temp=null;
    	}
    	return temp;
    }

    public Person FindPerson(String email)
    {
    	ListIterator<Person> iter= pList.listIterator();
    	Person temp= new Person();
    	Comparator<Person> emComp= new emailComp();
		Person comp=new Person();
		comp.setEmail(email);
		while(iter.hasNext())
    	{
    		temp=iter.next();
    		if(emComp.compare(temp, comp)==0)
				break;
			else
    			temp=null;
    	}
    	return temp;
    }
	public Person FindTel(String telephone)
    {
    	ListIterator<Person> iter= pList.listIterator();
    	Person temp= new Person();
    	Comparator<Person> emComp= new teleComp();
		Person comp=new Person();
		comp.setTelephone(telephone);
		while(iter.hasNext())
    	{
    		temp=iter.next();
    		if(emComp.compare(temp, comp)==0)
				break;
			else
    			temp=null;
    	}
    	return temp;
    }


    public void readContactfiles()
	{//List<Person> pList= new LinkedList<Person>();
        try{
		Scanner in= new Scanner(new File("contacts.txt"));
		int records=Integer.parseInt(in.nextLine());
		Person temp;
		String bin;
		//in.useDelimiter("\\R+");
        while(in.hasNextLine())
		{
			temp= new Person();
			temp.setLname(in.nextLine());
			temp.setFname(in.nextLine());
			temp.setaddress(in.nextLine());
			temp.setTelephone(in.nextLine());
			temp.setEmail(in.nextLine());
			//System.out.println(temp.toString());
            //bin= in.nextLine();
			pList.add(temp);

		}
		Collections.sort(pList);}
		catch(FileNotFoundException e)
		{
			System.out.println("file isnt there");
		}

	

	}
	/*public void tryingthing()
	{
		try{
		Scanner in= new Scanner(new File("contacts.txt"));
		int records=in.nextInt();
		Person temp;
		String bin;
		while(in.hasNextLine())
		{
			temp= new Person();
			temp.setLname(in.nextLine());
			temp.setFname(in.nextLine());
			temp.setaddress(in.nextLine());
			temp.setTelephone(in.nextLine());
			temp.setEmail(in.nextLine());
			bin= in.nextLine();
			pList.add(temp);

		}
		Collections.sort(pList);}
		catch(FileNotFoundException e)
		{
			System.out.println("file isnt there");
		}

	}*/
	}















