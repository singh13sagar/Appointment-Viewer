
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.text.DateFormat;
import javax.swing.*;
import java.util.*;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import com.sun.glass.events.MouseEvent;

import java.io.*;
import javafx.scene.layout.GridPane;
import java.awt.color.*;
import java.awt.event.*;
/**
 * 
 * @author Sagar Singh Punn
 * 		   Student number: 500761087
 *
 */
public class AppointmentFrame extends JFrame
{
    
	private Calendar calendar;
    private SimpleDateFormat sdformat;
    private ArrayList<Appointment> appointment;
    private JLabel datelabel;
    private JTextArea curdate;
    private JPanel controlpanel;
    private JPanel datesubp;
    private JButton incDB;
    private JButton decDB;
    private JPanel showPanel;
    private JButton showbutton;
    private JTextField showdate;
    private JTextField showmonth;
    private JTextField showyear;
    private JTextField lnText;
    private JTextField fnText;
    private JTextField telText;
    private JTextField emText;
    private JTextField addText;
    
    private JButton findB;
    private JButton clearB;
    
    private Contacts cont;
    
    private Person extraperson;

    private Calendar cal;
    private Date now;
    private JLabel label;
    private JPanel centerpanel;
    private JPanel contactPanel;
    private JPanel calpanel;
    private JPanel actionsubp;
    private JLabel entertime;
    private JTextField timein;
    private JLabel entermin;
    private JTextField minin;
    private JButton createButton;
    private JTextArea description;
    private JPanel desc;
    private JButton cancelButton;
    private Date currDate;
    private final int length= 800;
    private final int width=900;
    private JButton calB;
    private JButton nextM;
    private JButton prevM;
    private Calendar tempc;
    private Stack<Appointment> appStack;
    /**
     * This is the constructar where main components are made 
     */
    public AppointmentFrame()
    {
        //This sets up the layout and calendar object is created to set up date on frame by
    	//using @param label.
    	this.setLayout(new GridLayout(3,2));
        calendar= new GregorianCalendar();
        calendar= Calendar.getInstance();
        currDate = calendar.getTime();
        sdformat= new SimpleDateFormat("EEE, MMM d, yyyy");
        
        cal = new GregorianCalendar();
        cal = Calendar.getInstance();
        tempc= new GregorianCalendar();
        tempc= Calendar.getInstance();
        label = new JLabel();
        label.setText(sdformat.format(currDate));
        //this.add(label);/* ************************************************* */

        appointment = new ArrayList<Appointment>();		//appointment arraylist contains Appointment class objects.
        
        appStack= new Stack<Appointment>();
        


        curdate = new JTextArea(13,35);						//This is the text area where appointments are displayed
        JScrollPane scrollpane = new JScrollPane(curdate);
        centerpanel=new JPanel();
        centerpanel.add(scrollpane);
        //add(centerpanel); /* ************************************************* */
        
        JPanel scrolling= new JPanel(new BorderLayout());
        scrolling.add(label, BorderLayout.NORTH);
        scrolling.add(centerpanel, BorderLayout.CENTER);
        




       
        controlpanel= new JPanel();
        
        makecontrolP();
        
        
        
        setSize(width,length);
        
        
        
        cont = new Contacts();
        cont.readContactfiles();
        contactPanel= new JPanel();
        makecontactPanel();
       
        
        calpanel= new JPanel();
         
        makeCalP();
        settingDate();
        refreshcalB();
        
        
       

       // ADDING**********************************
       add(scrolling); /* ************************************************* */
       this.add(calpanel); /* ************************************************* */
       add(datesubp); /* ************************************************* */
       this.add(contactPanel); /* ************************************************* */
       add(actionsubp); /* ************************************************* */
       this.add(controlpanel); /* ************************************************* */

       randomAppointment();
       printAppointments();

    }
    
    /**
     * This method prints sorted appointment arraylist and appends them to the textarea
     * this method is called from different buttons 
     * and when date is changed this method automatically check appointment
     * on those date and append them to text area.
     */
    public void printAppointments()
    {
       curdate.setText(null);
      
        Collections.sort(appointment);
        for(int i=0; i<appointment.size();i++)
        {
            
            if(calendar.get(Calendar.DATE)==(appointment.get(i).getDate().get(Calendar.DATE))&&
                calendar.get(Calendar.MONTH)==(appointment.get(i).getDate().get(Calendar.MONTH))&&
                calendar.get(Calendar.YEAR)==(appointment.get(i).getDate().get(Calendar.YEAR)))
            {curdate.append(appointment.get(i).toString()+"\n");}
            
        }
    }

   
    public void makeCalP()
    {
        
        
        calpanel.setBorder(new TitledBorder("Calendar"));
        calpanel.setLayout(new GridLayout(8,7));
        calpanel.add(new JLabel("Su"));
        calpanel.add(new JLabel("Mo"));
        calpanel.add(new JLabel("Tu"));
        calpanel.add(new JLabel("We"));
        calpanel.add(new JLabel("Th"));
        calpanel.add(new JLabel("Fr"));
        calpanel.add(new JLabel("Sa"));
        
        
    }
    class datechange implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            // calendar.set(Calendar.YEAR, Calendar.MONTH, Integer.parseInt(((JButton) event.getSource()).getText()));
            
            int date= Integer.parseInt(((JButton) event.getSource()).getText());
            //((JButton) event.getSource()).setForeground(Color.blue);
            //((JButton) event.getSource()).repa
            // System.out.println(date);
            calendar.set(Calendar.DATE,date);
            now = calendar.getTime();
            //System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
            //System.out.println(calendar.get(CALENDAR));
            //label.setText(new SimpleDateFormat("EEE, MMM d, yyyy").format(now));
            label.setText(sdformat.format(calendar.getTime()));
            label.repaint();
            printAppointments();
            
            
        }
    }
    
   
    public void settingDate()
    {
        int prev= (calendar.get(Calendar.MONTH))-1;
        //System.out.println("Prev variable                   used "+prev);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, prev);
    }
    public void refreshcalB()
    {
        String name="";
       
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
      
        int lastm=cal.get(Calendar.DAY_OF_WEEK);
       
        int max= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+lastm;
       
        for(int i=0; i<42;i++)
        {
            name=""+(i-(lastm-1));
            if(i>=0&&i<lastm)
            {
                
                
                calpanel.add(new JButton());
            }
            else if(i>=max&&i<42)
            {
                calpanel.add(new JButton());
            }
            else
            {
                calB= new JButton(name);
                ActionListener changeD= new datechange();
                calB.addActionListener(changeD);
                calpanel.add(calB);
            }
        }
    }

    
    
    
    
     public void makecontactPanel()
    {
        contactPanel.setLayout(new GridLayout(7,2));

        lnText= new JTextField(10);
        fnText= new JTextField(10);
        telText= new JTextField(10);
        emText= new JTextField(10);
        addText= new JTextField(15);

        contactPanel.add(new JLabel("Last Name"));
        contactPanel.add(new JLabel("First Name"));
        contactPanel.add(lnText);
        contactPanel.add(fnText);
        contactPanel.add(new JLabel("Telephone"));
        contactPanel.add(new JLabel("Email"));
        contactPanel.add(telText);
        contactPanel.add(emText);

        contactPanel.add(new JLabel("Address"));
        contactPanel.add(addText);

        
        findB=new JButton("FIND");
        ActionListener findit = new findlistener();
        findB.addActionListener(findit);
        
        contactPanel.add(findB);
        
        clearB= new JButton("CLEAR");
        ActionListener clearit= new clearlistener();
        clearB.addActionListener(clearit);
        contactPanel.add(clearB);
























    }
   
    
   
    class findlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) 
        {
            //((JButton) event.getSource()).setForeground(Color.red);
            extraperson=new Person();
            if(!emText.getText().equals(""))
            {
               extraperson= cont.FindPerson(emText.getText());
               lnText.setText(extraperson.getLname());
               fnText.setText(extraperson.getFname());
               telText.setText(extraperson.getTelephone());
               emText.setText(extraperson.getEmail());
               addText.setText(extraperson.getaddress());
                
            }
            //if(!fnText.getText().equals("")&& !lnText.getText().equals(""))
            else
            {
               extraperson= cont.FindPerson(fnText.getText(), lnText.getText());
               //System.out.println(extraperson.toString());
               lnText.setText(extraperson.getLname());
               fnText.setText(extraperson.getFname());
               telText.setText(extraperson.getTelephone());
               emText.setText(extraperson.getEmail());
               addText.setText(extraperson.getaddress()); 
               
            }
            
            
        }
    }
   
    class clearlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            extraperson=new Person();
            if(!telText.getText().equals(""))
            {
               extraperson= cont.FindTel(telText.getText());
               cont.deleteContact(extraperson);
               telText.setText("");
               
                
            }
            else
            {
               extraperson= cont.FindPerson(fnText.getText(), lnText.getText());
               cont.deleteContact(extraperson);
               fnText.setText("");
               lnText.setText("");
                
            }
        }
    }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    /**
     * This is the method that create components of controlpanel
     * first date subpanel is created with its own subcomponents
     * then actions subpanel is created with its own subcomponents
     * after that Description subpanel is created with its own subcomponents
     * at the end all subpanels are added to control panel 
     */
    private void makecontrolP()
    {
        //controlpanel.setLayout(new GridLayout(3, 1));
        //Date subpanel and its sub components
            datesubp = new JPanel(new GridLayout(3,1));
            datesubp.setBorder(new TitledBorder("Date"));         

            JPanel changing= new JPanel();
            //changing.setLayout(new GridLayout(1,2));
            cal = new GregorianCalendar();
            cal = Calendar.getInstance();
            datelabel= new JLabel();
            incDB= new JButton(">");
            ActionListener increaseD= new nextDate();
            incDB.addActionListener(increaseD);

            decDB= new JButton("<");
            ActionListener decreaseD = new previousDate();
            decDB.addActionListener(decreaseD);
            changing.add(decDB);
            changing.add(incDB);
            datesubp.add(changing);
            showPanel = new JPanel();
            //showPanel.setLayout(new GridLayout(1,6));
            showdate = new JTextField(2);
            showmonth = new JTextField(2);
            showyear= new JTextField(3);
            
            showbutton = new JButton("Show");
            ActionListener showlisten= new showDateButton();
            showbutton.addActionListener(showlisten);
            showPanel.add(new JLabel("Day"));
            showPanel.add(showdate);
            showPanel.add(new JLabel("Month"));
            showPanel.add(showmonth);
            showPanel.add(new JLabel("Year"));
            showPanel.add(showyear);
            datesubp.add(showPanel);
            JPanel showbuttonP= new JPanel();
            showbuttonP.add(showbutton);
            datesubp.add(showbuttonP);
        
        
        //datesubp.add(showPanel);
        //controlpanel.add(datesubp, BorderLayout.NORTH);

        


// action subpanel and its components 
            actionsubp= new JPanel();
            actionsubp.setBorder(new TitledBorder("Appointment"));
            
            JPanel entering= new JPanel(new GridLayout(1, 4));
            entertime= new JLabel("Hour");
            timein = new JTextField(5);
            entermin= new JLabel("Minute");
            minin = new JTextField(5);
            entering.add(entertime);
            entering.add(timein);
            entering.add(entermin);
            entering.add(minin);




            JPanel creatingB= new JPanel(new GridLayout(1, 3));
            createButton= new JButton("CREATE");
            ActionListener createapp= new createlistener();
            createButton.addActionListener(createapp);
            creatingB.add(createButton,BorderLayout.SOUTH);
            
            cancelButton= new JButton("CANCEL");
            ActionListener cancelapp= new cancellistener();
            cancelButton.addActionListener(cancelapp);
            creatingB.add(cancelButton, BorderLayout.CENTER);
            actionsubp.add(entering);
            //actionsubp.add(creatingB);
        
            JButton recallB= new JButton("RECALL");
            ActionListener recallit= new recalllistener();
            recallB.addActionListener(recallit);
            creatingB.add(recallB);
            actionsubp.add(creatingB);

        //controlpanel.add(actionsubp, BorderLayout.CENTER);
// description subpanel
            desc= new JPanel();
            desc.setBorder(new TitledBorder("Description"));
            description= new JTextArea(10,20);
            desc.add(description);
            controlpanel.add(desc);  



            
    }

    class recalllistener implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
        Appointment app= appStack.peek();
        calendar= app.getDate();
        printAppointments();

        extraperson = app.getPerson();

                lnText.setText(extraperson.getLname());
               fnText.setText(extraperson.getFname());
               telText.setText(extraperson.getTelephone());
               emText.setText(extraperson.getEmail());
               addText.setText(extraperson.getaddress());
        description.setText(app.getDescription());
        timein.setText(""+app.getDate().get(Calendar.HOUR_OF_DAY));
        minin.setText(""+app.getDate().get(Calendar.MINUTE));


    }
}







    /**
     * this is class for create button
     * it takes on hour and mintue and create appointment with current date and given time
     * then it calls other method findAppointment which check the conflict 
     * if found then it doesnt create it and if not then it creates
     * new appointment object and call printAppointment methods which appends appointments on
     * display text area.
     *
     */
    private void randomAppointment()
    {
        appointment.add(new Appointment(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),8,0,"going to university",new Person()));
        appointment.add(new Appointment(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),12,0,"CPS 209 class",new Person()));
        appointment.add(new Appointment(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),15,15,"Lunch with friends",new Person()));
        appointment.add(new Appointment(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),17,30,"meeting with professor",new Person()));
    }
    class createlistener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int hour= Integer.parseInt(timein.getText());
            int minute;
            if(minin.getText().isEmpty())
            {
                minute= 0;
            }
            else
            {
                minute= Integer.parseInt(minin.getText());
            }
            String des= description.getText();
            Person testp= new Person(lnText.getText(), fnText.getText(), telText.getText(), addText.getText(), emText.getText()); // newly added.
            Appointment appcreate= new Appointment(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),hour,minute,des, testp);
            
            appcreate.getDate().set(Calendar.HOUR_OF_DAY, hour);
            Calendar testdate= appcreate.getDate();
          
            
        if(findAppointment(testdate.get(Calendar.YEAR),testdate.get(Calendar.MONTH),testdate.get(Calendar.DATE),testdate.get(Calendar.HOUR_OF_DAY),testdate.get(Calendar.MINUTE)))
            {
                description.setText("CONFLICT!!");
            }
            else
            {
                addList(appcreate);
                appStack.push(appcreate);
                description.setText(null);
            }

           
           timein.setText(null);
           minin.setText(null);
           
           printAppointments();
        }
    }

    /**
     * This method find the appointment object that occurs on same date and time that are passed
     * @param y year
     * @param m month
     * @param d date
     * @param h hour
     * @param min minute
     * @return returns boolean value
     */
    private boolean findAppointment(int y, int m, int d, int h, int min)
    {
        Calendar testdate= new GregorianCalendar(y,m,d,h,min);
        Calendar storedate= new GregorianCalendar();
        for(int i=0; i<appointment.size();i++)
        {
         storedate= appointment.get(i).getDate();
         if(testdate.equals(storedate))
         {
             return true;
         }
          
        }
        return false;  
    }
    


    /**
     * This method adds the Appintment object that is passed to method to appointment arraylist
     * @param app Appointment object
     */
    private void addList(Appointment app)
    {

        appointment.add(app);
        printAppointments();
    }

    /**
     * This method delete the appointment object from arraylist if it matches with the 
     * appontment object that is passed to the method
     * @param app Apppointment object
     */
    private void deleteList(Appointment app)
    {
        
        
           
            for(int i=0; i<appointment.size();i++)
            {
                if(app.getDate().get(Calendar.HOUR_OF_DAY)==(appointment.get(i).getDate().get(Calendar.HOUR_OF_DAY))
                &&app.getDate().get(Calendar.MINUTE)==(appointment.get(i).getDate().get(Calendar.MINUTE)))
                {
                    appointment.remove(i);
                }
            }
        
        printAppointments();
    }
    
    
    /**
     * this class is for cancel button
     * it takes hour and mintue and uses deleteList method which then
     * deletes the appointment that matches the time given
     * @author Owner
     *
     */
    class cancellistener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int hour= Integer.parseInt(timein.getText());
            int minute= Integer.parseInt(minin.getText());
            String des= description.getText();
            Person testp= new Person(lnText.getText(), fnText.getText(), telText.getText(), addText.getText(), emText.getText());
            Appointment appcreate= new Appointment(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),hour,minute,des,testp);
           appStack.pop();
           deleteList(appcreate);
           
           
           timein.setText(null);
           minin.setText(null);
           printAppointments();
        }
    }






    /**
     * this is for Show button
     * it takes in date and then uses changes date and then uses printappointment method
     *
     */
    class showDateButton implements ActionListener
            {
                public void actionPerformed(ActionEvent event)
                {
                    int date= Integer.parseInt(showdate.getText());
                    int month= Integer.parseInt(showmonth.getText())-1;
                    int year= Integer.parseInt(showyear.getText());
                    
                   
                    calendar.set(Calendar.YEAR, year);    
                    calendar.set(Calendar.MONTH,month);
                    calendar.set(Calendar.DATE,date);
                    now = calendar.getTime();
                    label.setText(new SimpleDateFormat("EEE, MMM d, yyyy").format(now));
                    
                    
                    printAppointments();
                    showdate.setText(null);
                    showmonth.setText(null);
                    showyear.setText(null);
                }
            }
    // this is used in button for setting date one day before 
    class previousDate implements ActionListener
            {
                public void actionPerformed( ActionEvent event)
                {
                    int prev= calendar.get(Calendar.MONTH);
                    calendar.add(Calendar.DATE,-1);
                    now = calendar.getTime();
                   
                    label.setText(new SimpleDateFormat("EEE, MMM d, yyyy").format(now));
                    
                    printAppointments();
                    if(calendar.get(Calendar.MONTH)<prev)
                    {
                        calpanel.removeAll();
                calpanel.revalidate();
                calpanel.repaint();
                makeCalP();
                settingDate();
                refreshcalB();
               
                    }
                }
            }
   //this is used in button that increment date
    class nextDate implements ActionListener
            {
                public void actionPerformed( ActionEvent event)
                {
                    
                    int prev= calendar.get(Calendar.MONTH);
                    calendar.add(Calendar.DATE,1);
                    now = calendar.getTime(); 
                   
                    label.setText(new SimpleDateFormat("EEE, MMM d, yyyy").format(now));
                  
                    printAppointments();
                    if(calendar.get(Calendar.MONTH)>prev)
                    {
                        calpanel.removeAll();
                calpanel.revalidate();
                calpanel.repaint();
                makeCalP();
                settingDate();
                refreshcalB();
                
                    }
                }
            }
}
