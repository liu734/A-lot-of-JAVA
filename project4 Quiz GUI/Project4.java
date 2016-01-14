/*
 *Project 4 CS Quiz System 
 * 
 * This project requires you to create a program that administers a quiz. Students, and any other
 *users, will be able to use this program to create new accounts or log in to their current accounts
 *to take quizzes.
 * 
 * @auther Jingye Liu, Wei Hao Tan, Jun Xiang Tee
 * 
 * @cslogin liu734, tan85, jtee
 * 
 * @Recitation RM3, RM6,R3
 * 
 * @data 10 Nov 2011
 * 
 * @C_data 18 Nov 2011
 * 
 */

import javax.swing.*; //import package
import java.io.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.String;

public class Project4 implements ActionListener,MenuListener,MouseListener{
    JFrame f; //variables
    JPanel p,p1,p2;
    JMenuBar mbar;
    JMenu user, start, turnin;
    JMenuItem newuser,login,logout;

    JButton[] b;
    JLabel[] l;
    String q[][]; 
    int count;

    String answerTemp="";
    String currentUser;
    
    int quest[]; 
    String sol[];
    String sol1[];
    int grade;
    int counter=0;
    boolean sb;
    int questionNum;
    
    public Project4(){ //constructor
        createButton();
        createLabel();
        createMenus();
        createPanel();
        createFrame();
    }

    public void createMenus(){ //create Jmenus
        user=new JMenu("User"); //menus
        start=new JMenu("Start");
        turnin=new JMenu("Turnin");

        start.addMenuListener(this); //add menulistener
        turnin.addMouseListener(this);

        start.setEnabled(false); //inactive start and turnin
        turnin.setEnabled(false);
        sb=false;

        newuser=new JMenuItem("New User"); //jmenuitems
        login=new JMenuItem("Log In"); 
        logout= new JMenuItem("Log Out");
        logout.setEnabled(false); 
        newuser.addActionListener(this); //ad Actionlisteners
        login.addActionListener(this);
        logout.addActionListener(this);

        user.add(newuser); //add items to menu
        user.add(login);
        user.add(logout);

        mbar=new JMenuBar();
        mbar.add(user); //add menus to bar
        mbar.add(start);
        mbar.add(turnin);

    }

    public void createButton(){ //creat buttons
        b=new JButton[5];
        b[0]=new JButton("A");
        b[1]=new JButton("B");
        b[2]=new JButton("C");
        b[3]=new JButton("D");
        b[4]=new JButton("Next");

        for (int i=0;i<b.length;i++){ //add listeners
            b[i].addActionListener(this);
            b[i].setEnabled(false);
        } 
    }

    public void createLabel(){ //create label
        l=new JLabel[5];
        for (int i=0;i<l.length;i++){
            l[i]=new JLabel();
        } 
    }

    public void createPanel(){ //create Panel, and components and set layout
        p=new JPanel();
        p1=new JPanel();
        p2=new JPanel();
        p1.setLayout(new BorderLayout());

        p.setLayout(new GridLayout(4,2,15,15));  

        for (int i=0;i<4;i++){ 
            p.add(l[i]);
            p.add(b[i]);
        }
        p.add(l[4]);
        p2.add(b[4]);
        p1.add(p2,BorderLayout.SOUTH);
        p1.add(p,BorderLayout.CENTER);
        p1.add(l[0],BorderLayout.NORTH);
    }

    public void createFrame(){  //create frame
        f=new JFrame("CS Quiz System");
        f.setJMenuBar(mbar);
        f.add(p1);

        f.setSize(400,400);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

    }

    public void createLoginPane(){ //createlogin Pane to get username and password
        JPanel panel=new JPanel();  //create a panel
        panel.setLayout(new GridLayout(4,1));  

        JLabel username=new JLabel("USERNAME");  
        JLabel password=new JLabel("PASSWORD");  

        JTextField textField=new JTextField(12);  

        JPasswordField passwordField=new JPasswordField(12);  

        panel.add(username);  
        panel.add(textField);  
        panel.add(password);  
        panel.add(passwordField);  

        int a=JOptionPane.showConfirmDialog(null,panel,"Put username and password",JOptionPane.OK_CANCEL_OPTION);  //show a joptionpane contains panel created
        if(a==JOptionPane.OK_OPTION)  
        {  
            if(checkPassword(textField.getText(),passwordField.getPassword()))  //check user name and password
            {
                JOptionPane.showMessageDialog(null,"You have successfully logged in!","Welcome",JOptionPane.INFORMATION_MESSAGE);
                currentUser=textField.getText(); //get the current user
                if(checkTurnin(currentUser)){  //check if current user have turned in 
                  start.setEnabled(false); //set enable 
                  logout.setEnabled(true);
                }
                else{
                  start.setEnabled(true);
                }
                newuser.setEnabled(false);
                login.setEnabled(false); 
                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Wrong User Name or Wrong Password","Error",JOptionPane.ERROR_MESSAGE); //if wrong user name show error
            }
        }  

    }

    public void actionPerformed(ActionEvent e){ //action of button 
        Object o=e.getSource();
        if (o.equals(newuser)){

            JPasswordField pwd = new JPasswordField(10);
            JTextField tf=new JTextField(12);  

            int  action = JOptionPane.showConfirmDialog(null,tf,"Enter User Name",JOptionPane.OK_CANCEL_OPTION);  //prompt new user to enter username

            if(action==0) //if ok
            {
                if(tf.getText().equals("")||tf.getText().indexOf(" ")!=-1) //check if have blank of is balck
                    JOptionPane.showMessageDialog(null,"The user name is inappropriate.");

                else if(checkUserName(tf.getText()))  //check if the user exists
                    JOptionPane.showMessageDialog(null,"The user name exists in the database!");

                else {
                    action = JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
                    String password= new String (pwd.getPassword());

                    if(password.equals("")||password.indexOf(" ")!=-1)JOptionPane.showMessageDialog(null,"The password is inappropriate.");  //check the format  of the password
                    else if(action==0) {
                        saveNewUser(tf.getText(),password); 
                        JOptionPane.showMessageDialog(null,"You have successfully created your account!");
                    }  
                }
            }
        }   

        else if(o.equals(login)){ //login
            createLoginPane(); //loginpane

        }

        if(o.equals(b[0])) //buttons a b c d to get answer
        {
            answerTemp = "a";

        }
        else if(o.equals(b[1]))
        {
            answerTemp = "b";

        }
        else if(o.equals(b[2]))
        {
            answerTemp = "c";

        }
        else if(o.equals(b[3]))
        {
            answerTemp = "d";

        }
        if(o.equals(b[4])) //next button
        {   

            if(count<2) 
            {
                if (answerTemp.equals(sol[count]))  //check answer and get current grade
                grade++;
                count++;
                setQuestions(); 
                answerTemp="N";
            }
        }
         if(o.equals(logout)) //logout
        {
            f.dispose();
            new Project4();

        }
    
    }

    public boolean checkPassword(String name, char[] pass){ //check the name and password
        File inFile=new File("Data.txt");
        Scanner s=null;

        try
        {
            s= new Scanner(inFile);
        }
        catch(Exception e){}

        while(s.hasNext()){
            String temp= s.next();
            if (temp.equals(name)) 
            {
                temp=new String(pass); 
                temp=temp+s.next(); //passeord add N
                if(Integer.toString(temp.hashCode()).equals(s.next())){ //make and check  the hashcode
                    return true;
                }

            }

        }
        return false;
    }

    public void list() //get question list and answer list
    {
        File f = new File("Questions.txt");

        Scanner s = null;
        q = new String[questionNum][5];//2d arrary to store question and choice  
        String text="";
        try
        {
            s = new Scanner(f);
        }
        catch(Exception e){}
        while(s.hasNext())
        {
          for(int i=0;i<questionNum;i++)
            for(int j=0;j<5;j++)
            {     
                q[i][j]=s.nextLine();
            }
        }
        
        File f2= new File("Sol.txt");
        try{
          s= new Scanner(f2); 
        }
        
        catch(Exception e){}
        sol=new String [questionNum];
        for(int i=0;i<questionNum;i++){
            {
              sol[i]=s.next();
              
            }
        }

    }

    public void setQuestions() //set question and choice to label
    {
          if(count==2) b[4].setEnabled(false);
      
        for(int j=0;j<5;j++)
        {
            l[j].setText(q[quest[count]][j]);
        }
        f.pack();
       
    }

    public void randomNumber() //get three random number and solution ad store in quest[] and sol1[]
    {
        Random r = new Random();
        quest = new int[3]; 
        sol1=new String[3];
        int temp=r.nextInt(questionNum);
        int x=0;
        while(x<3)
        {
            while(quest[1]==temp||quest[2]==temp||quest[0]==temp)
            {
                temp=r.nextInt(questionNum);

            }
            quest[x] = temp;
              sol[x] = sol [quest[x]];

            x++;

        }

    }

    public void grade() //store the grade to grade.txt
    {
        File outFile = new File("Grades.txt");
        PrintWriter out = null;
        Scanner s= null;
        try {
          s=new Scanner(outFile);
        }
        catch(Exception e){}
        
        String text="";
        while(s.hasNext()){
          text=text+s.next()+" ";
          text=text+s.next()+"\n";
        }

        text=text+currentUser+" ";
        text=text+grade;  
        
        try {
            out=new PrintWriter(outFile);
        }
        catch(Exception e){}
        out.print(text);
        
        out.close();
    }

    public void menuCanceled(MenuEvent e){
    }

    public void menuDeselected(MenuEvent e){
    }
    
    public void mouseExited(MouseEvent e){
    }
    
    public void mousePressed(MouseEvent e){ //get the action from turnin 
    Object ob=e.getSource();
      if(counter==0&&sb==true&&ob.equals(turnin))
        {
          execute();     
        }
    }
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    
    public void menuSelected(MenuEvent e){ //get the action from the start
	
        Object ob=e.getSource();
        if(ob.equals(start)){
	  grade=0;
          count=0;
            start.setEnabled(false);
            turnin.setEnabled(true);
            sb=true;
            b[4].setEnabled(true);
           
            for(int i=0;i<4;i++)
            {
                b[i].setEnabled(true);
            }
            getQuestionNum();
	    list();
            randomNumber();
            setQuestions();
        }
    }
    public void  getQuestionNum() //get Question number of the question 
    {
      questionNum=0;
       File f = new File("Sol.txt");

        Scanner s = null;
        try
        {
            s = new Scanner(f);
        }
        catch(Exception e){}
        while(s.hasNext())
        {
          String temp=s.nextLine();
          questionNum++;
         
        }
    }
    
    public void execute(){ //excute the following steps after calling turnin
                      counter++;
      if (answerTemp.equals(sol[2]))
      {grade++;}
                JOptionPane.showMessageDialog(null,grade+" out of 3 Correct!");   
                turnin.setEnabled(false);
                sb=false;
                logout.setEnabled(true);
                for(int i=0;i<5;i++)
                l[i].setText("");
                grade();

    
    }

    public boolean checkUserName(String un){ // method to check the user name  if exists
        File inFile=new File("Data.txt");
        Scanner s=null;

        try{s= new Scanner(inFile);
        }
        catch(Exception e){}

        while(s.hasNext()){
            String temp= s.next();
            if (temp.equals(un)) return true;

        }

        return false;
    }
    
    public boolean checkTurnin(String un){ //method to check if user have turned in before
      File inFile= new File("Grades.txt");
    Scanner s=null;
    try{s= new Scanner(inFile);}
    catch(Exception e){}
    
    while(s.hasNext()){
      String temp=s.next();
      if(temp.equals(un))
       return true;
    }
    return false;
     
   }
    public void saveNewUser(String name ,String pass){ //save username, generate N and hashcode and save them to data.txt
        File inFile=new File("Data.txt");
        Scanner s=null;
        String P=pass;

        String text="";

        try{s= new Scanner(inFile);
        }
        catch(Exception e){}

        while(s.hasNext()){
            for (int i=0;i<3;i++)
            {
                if (i==2)
                {
                    text=text+s.next()+"\n";
                }
                else text=text+s.next()+" ";

            }

        }

        Random r=new Random();
        String N=Integer.toString(10+r.nextInt(90));
        String PN=P+N;
        String H=Integer.toString(PN.hashCode());

        text=text+name+" "+N+" "+H+"\n";

        File outFile=new File("Data.txt");
        PrintWriter p=null;
        try{
            p=new PrintWriter(outFile);
        }catch(Exception e){
        }

        p.print(text);
        p.close();
    }

    public static void main(String [] args){ //main
        Project4 pro=new Project4();
    }

}