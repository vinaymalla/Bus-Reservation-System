import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.sql.*;
class global{
        public static String u_name;
        public static String u_password;
        public static String busname;
        public static String busfrom;
        public static String busto;
        public static String busdate;
    }
class mainFrame extends Frame
{
   
   Button b1,b2;
   mainFrame()	
   {
      Label ml=new Label("welcome to bus registration potral", Label.CENTER);
      Font mf = new Font("Arial", Font.PLAIN, 30);
      ml.setFont(mf);
      ml.setBounds(30,110,450,40);
      add(ml);
	b1 = new Button("register");
	b1.setBounds(100,250,70,40);
	b2 = new Button("login");
	b2.setBounds(250,250,70,40);
	
	add(b1);
        add(b2);		
        mainListener al=new mainListener(this);
	b1.addActionListener(al);
        b2.addActionListener(al);

	this.setLayout(new FlowLayout());
	this.setSize(500,500);
	this.setTitle("main page");
        this.setLayout(null);
	this.setVisible(true);
	this.addWindowListener(new NsrListener());	
  }
}
class mainListener implements ActionListener
{  
   mainFrame x; 
   boolean flag=false; 
   mainListener(mainFrame x)
   {  
	this.x=x;  
   }  
   
   public void actionPerformed(ActionEvent e)
   {
	try
        {
            String s = e.getActionCommand();
               
	   Graphics g = x.getGraphics();
           if(s.equals("register"))
           {
                try{
                        g.drawString("Login successful", 20, 250);
                        Thread.currentThread().sleep(1000);
                        new RegisterFrame();
                        x.dispose();
                }catch(Exception x){};
           }
	     if(s.equals("login"))
             { 
                try{
                        g.drawString("registration successful", 20, 250);
                        Thread.currentThread().sleep(1000);
                        new LoginFrame();
                        x.dispose();
                }catch(Exception x){};
             }
          
	      
           
        }
        catch(Exception x){};
   }
} 

class mainmenu extends Frame
{
   
   Button b1,b2,b3;
   mainmenu()	
   {
      Label ml=new Label("menu page", Label.CENTER);
      Font mf = new Font("Arial", Font.PLAIN, 30);
      ml.setFont(mf);
      ml.setBounds(30,130,450,40);
      add(ml);
	b1 = new Button("my_details");
	b1.setBounds(100,250,100,40);
	b2 = new Button("book_seat");
	b2.setBounds(200,250,100,40);
   b3 = new Button("booked_seats");
	b3.setBounds(300,250,100,40);
	
	add(b1);
        add(b2);		
        add(b3);
   mainmenuListener al=new mainmenuListener(this);
	b1.addActionListener(al);
        b2.addActionListener(al);
        b3.addActionListener(al);

	this.setLayout(new FlowLayout());
	this.setSize(500,500);
	this.setTitle("main page");
        this.setLayout(null);
	this.setVisible(true);
	this.addWindowListener(new NsrListener());	
  }
}
class mainmenuListener implements ActionListener
{  
   mainmenu x; 
   boolean flag=false; 
   mainmenuListener(mainmenu x)
   {  
	this.x=x;  
   }  
   
   public void actionPerformed(ActionEvent e)
   {
	try
        {
            String s = e.getActionCommand();
               
	   Graphics g = x.getGraphics();
           if(s.equals("my_details"))
           {
                try{
                        new show_details();
                        x.dispose();
                }catch(Exception x){};
           }
	       if(s.equals("book_seat"))
             { 
                try{
                        new show_bus();
                        x.dispose();
                }catch(Exception x){};
             }
             if(s.equals("booked_seats"))
             { 
                try{   
                     new seats_booked();
                     x.dispose();
                }catch(Exception x){};
             }
          
	      
           
        }
        catch(Exception x){};
   }
} 
class LoginFrame extends Frame
{
   TextField name, pw;
  // String pass;
   Button b;
   LoginFrame()	
   {
	Label ml=new Label("Login Form", Label.CENTER);
	Font mf = new Font("Arial", Font.PLAIN, 30);
	ml.setFont(mf);
	Label l1 = new Label("Name: ");
        Label l2 = new Label("Password: ");
	ml.setBounds(90,30,200,40);
        l1.setBounds(20,90,70,30);
        l2.setBounds(20,140,70,30);

	name = new TextField(10);
	Font f = new Font("Arial", Font.PLAIN, 25);
	name.setFont(f);

	pw = new TextField(10);
	pw.setEchoChar('*'); 

        name.setBounds(100,90,250,30);
        pw.setBounds(100,140,250,30);
 
	b = new Button("Submit");
	b.setBounds(150,300,70,40);
	
	add(ml);add(l1);add(l2);
 	add(name);	add(pw);
	add(b);		
        LoginListener al=new LoginListener(this);
	b.addActionListener(al);

	this.setLayout(new FlowLayout());
	this.setSize(500,500);
	this.setTitle("Login form");
        this.setLayout(null);
	this.setVisible(true);
	this.addWindowListener(new NsrListener());	
  }
}
 class LoginListener implements ActionListener
{  
        LoginFrame x; 
   boolean flag=false; 
   String pass;
   LoginListener(LoginFrame x)
   {  
	this.x=x;  
   }  
   public void getconnection()
   {
        global.u_name=x.name.getText();
         pass=x.pw.getText();
	Connection con=null;
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url= "jdbc:mysql://localhost:3306/dup22"; 
                String user = "root"; 
                String pwd = "vinayvini";
                
            con = DriverManager.getConnection(url, user, pwd);

            
            String q="select * from login where l_name=?";
	PreparedStatement  pst = con.prepareStatement(q);
  
	pst.setString(1,global.u_name);
        ResultSet rs=pst.executeQuery();
	Graphics g = x.getGraphics();
        if(rs.next())
	{	
		String pass_word = rs.getString(2);
		System.out.println(pass_word);
                System.out.println(pass);
		//g.drawString("Department:", 20, 250);	
                if(pass.equals(pass_word))
                flag=true;		
	}
        else
		g.drawString("No Student with the given name",20,150);
 } catch(Exception x)
         {
          System.out.println(x);
         };        
        
        
                
   }
   public void actionPerformed(ActionEvent e)
   {
	try
        {
           getconnection();
	   Graphics g = x.getGraphics();
           if(flag)
	     { g.drawString("Login successful", 20, 250);
              Thread.currentThread().sleep(1000);
              new mainmenu();


       
         x.dispose();
        }
           else
           {
              g.drawString("Login unsuccessful.Try again", 20, 250);
	      Thread.currentThread().sleep(1000);
              //x.repaint();//we can try this instead of following two lines
	      new LoginFrame();
	      x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 
class RegisterFrame extends Frame
{
   TextField name, pw,DOB,aadhar_no;
   Choice genderChoice ;
   Button b;
   RegisterFrame()	
   {
	Label ml=new Label("Registration Form", Label.CENTER);
	Font mf = new Font("Arial", Font.PLAIN, 30);
	ml.setFont(mf);
	Label l1 = new Label("Name: ");
        Label l2 = new Label("Password: ");
        Label l3 = new Label("aadhar_no ");
        Label l4 = new Label("DOB ");

	ml.setBounds(90,30,300,40);
        l1.setBounds(20,90,70,30);
        l2.setBounds(20,140,70,30);
        l3.setBounds(20,180,70,30);
        l4.setBounds(20,220,70,30);


	name = new TextField("name");
	Font f = new Font("Arial", Font.PLAIN, 25);
	name.setFont(f);
        DOB = new TextField("dob");
	
	DOB.setFont(f);
        aadhar_no = new TextField("aadhar");
	
	aadhar_no.setFont(f);

	pw = new TextField("pass");
	pw.setEchoChar('*'); 

        name.setBounds(100,90,250,30);
        pw.setBounds(100,140,250,30);
        aadhar_no.setBounds(100,180,250,30);
        DOB.setBounds(100,220,250,30);

       
 
	b = new Button("Submit");
	b.setBounds(150,320,70,40);
	
	add(ml);add(l1);add(l2);
 	add(name);	add(pw);
	add(b);	
        add(l3);
        add(l4);
      //  add(l5);
        add(DOB);
        add(aadhar_no);
       // add(genderChoice);

        RegisterListener al=new RegisterListener(this);
	b.addActionListener(al);

	this.setLayout(new FlowLayout());
	this.setSize(500,500);
	this.setTitle("registration form");
        this.setLayout(null);
	this.setVisible(true);
	this.addWindowListener(new NsrListener());	
  }
}
class RegisterListener implements ActionListener
{  
   RegisterFrame x; 
   boolean flag=false; 
   RegisterListener(RegisterFrame x)
   {  
	this.x=x;  
   }  
   public void getconnection()
   {    global.u_name=x.name.getText();
        global.u_password=x.pw.getText();
       // String n_ame=x.name.getText();
        String pass=x.pw.getText();
        String dob=x.DOB.getText();
        String aadhar=x.aadhar_no.getText();
       // String gende=x.genderChoice.getSelectedItem();

	Connection con=null;
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url= "jdbc:mysql://localhost:3306/dup22"; 
                String user = "root"; 
                String pwd = "vinayvini";
                
        con = DriverManager.getConnection(url, user, pwd);

        flag=true;
        String q="insert into login values(?,?,?,?)";
        PreparedStatement  pst = con.prepareStatement(q);
      
        pst.setString(1,global.u_name);
        pst.setString(2,pass);
        pst.setString(3,aadhar);
        pst.setString(4,dob);
      //  pst.setString(5,gende);
        pst.executeUpdate();
        flag=true;
        }
        catch(Exception ex){
	    System.out.println(ex);
        } 
            
   }
   public void actionPerformed(ActionEvent e)
   {
	try
        {
           getconnection();
	   Graphics g = x.getGraphics();
           if(flag)
	   {   g.drawString("rigestration successful", 20, 250);
             new LoginFrame();
	      x.dispose();
          }

           else
           {
              g.drawString("rigestration unsuccessful.Try again", 20, 250);
	      Thread.currentThread().sleep(1000);
              //x.repaint();//we can try this instead of following two lines
	      new RegisterFrame();
	      x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 
class show_details extends Frame 
{ String name,pass,dob,id,gender;
        String names;
  
   Button b,b1;
   show_details()	
   {
	Label ml=new Label("Details Form", Label.CENTER);
	Font mf = new Font("Arial", Font.PLAIN, 30);
	ml.setFont(mf);
	Label l1 = new Label("Name: ");
        Label l2 = new Label("Password: ");
        Label l3 = new Label("aadhar_no ");
        Label l4 = new Label("DOB ");
      //  Label l5 = new Label("gender");
	ml.setBounds(90,30,300,40);
        l1.setBounds(20,50,70,30);
        l2.setBounds(20,70,70,30);
        l3.setBounds(20,90,70,30);
        l4.setBounds(20,110,70,30);
       // l5.setBounds(20,140,70,30);

	
 
	b = new Button("view");
	b.setBounds(150,300,70,40);
        b1= new Button("menu");
	b1.setBounds(250,300,70,40);
	
	add(ml);add(l1);add(l2);
 	add(l3);	add(l4);
	add(b);		add(b1);
      //  add(l5);
       show_detailsListener al=new show_detailsListener(this);
	b.addActionListener(al);
        d_menuListener bl=new  d_menuListener(this);
	b1.addActionListener(bl);

   
             this.setSize(500,500);
             this.setTitle("my details");
             this.setLayout(null);
             this.setVisible(true);

             this.addWindowListener(new NsrListener());

  }
}
class d_menuListener implements ActionListener
{  
        show_details  x; 
     
   int k=0;
   d_menuListener(show_details x)
   {  
	this.x=x;  
   }  
   
   public void actionPerformed(ActionEvent e)
   {
	try
        {
	      new mainmenu();
	      x.dispose();
           
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 
class show_detailsListener implements ActionListener
{  
        show_details x; 
        String name,pass,dob,id,gender;
        String names;
   boolean flag=false; 
   show_detailsListener(show_details x)
   {  
	this.x=x;  
   }  
   public void getconnection()
   {names=global.u_name;
	Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/dup22"; 
           String user = "root"; 
           String pwd = "vinayvini";
           con = DriverManager.getConnection(url, user, pwd);
           flag=true;
           String q="select * from login where l_name=?";
           PreparedStatement  pst = con.prepareStatement(q);
     
           pst.setString(1,names);
           ResultSet rs=pst.executeQuery();
           Graphics g = x.getGraphics();
         //  name = rs.getString(1);
          
           if(rs.next())
           {	
                 //  name = rs.getString(1);
                   pass = rs.getString(2);
                
                   id = rs.getString(3);
                   dob = rs.getString(4);
               
                   System.out.println(name);
                   System.out.println(pass);
                   System.out.println(id);
                   System.out.println(dob);
               
                   g.drawString(global.u_name, 200, 90);
                   g.drawString(pass, 200, 110);
                   g.drawString(id, 200, 130);	
                   g.drawString(dob, 200, 150);			
           }
           else
                   g.drawString("No Student with the given name",20,150);
                
        }
        catch(Exception ex){
	    System.out.println(ex);
        } 
            
   }
   public void actionPerformed(ActionEvent e)
   {
	try
        {
           getconnection();
	   Graphics g = x.getGraphics();
           if(flag)
	    {  g.drawString("Login successful", 20, 250);
            Thread.currentThread().sleep(1000);
        }
           else
           {
              g.drawString("Login unsuccessful.Try again", 20, 250);
	      Thread.currentThread().sleep(1000);
              //x.repaint();//we can try this instead of following two lines
	      new LoginFrame();
	      x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 







//bus
class show_bus extends Frame
{
   Label sub,s,d;
   Choice ch,c,a;
   Button b;
   show_bus()	
   {
	sub = new Label("Select Date ");
        sub.setBounds(20,50,110,30);
	ch = new Choice();
	ch.add("01/06/2023");        ch.add("02/06/2023");    
        //ch.add("03/06/2023");        //ch.add("04/06/2023");
     //   ch.add("05/06/2023");        ch.add("06/06/2023");

        ch.setBounds(150,55, 110, 30);
	add(sub);add(ch);
    s = new Label("From ");
        s.setBounds(20,100,110,30);
	c = new Choice();
	c.add("kurnool");        c.add("Nellore");    
      //  c.add("Vijaywada");       // c.add("Chittor");
        

        c.setBounds(150,105, 110, 30);
	add(s);add(c);

    d = new Label("To ");
        d.setBounds(20,150,110,30);
	a = new Choice();
	a.add("kurnool");          
    //    a.add("Vijaywada");       // a.add("Chittor");
       a.add("Nellore"); 
        

        a.setBounds(150,155, 110, 30);
	add(d);add(a);
    b = new Button("Submit");
    
	b.setBounds(180,300,70,40);
    add(b);
    show_busListener al=new show_busListener(this);
	b.addActionListener(al);


	this.setLayout(new FlowLayout());
	this.setSize(800,800);
	this.setTitle("Book Ticket");
        this.setLayout(null);
	this.setVisible(true);
	this.addWindowListener(new NsrListener());	
  }
}
class show_busListener implements ActionListener
{  
        show_bus x; 
     
        String names;
   boolean flag=false; 
   
   show_busListener(show_bus x)
   {  
	this.x=x;  
   }  
  
    public void getconnection()
     { 
    
        String d_date=x.ch.getSelectedItem() ;
        String   d_from=x.c.getSelectedItem();
         String d_to=x.a.getSelectedItem();
         global.busfrom=d_from;
         global.busto=d_to;
         global.busdate=d_date;
        
        Connection con=null;
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url= "jdbc:mysql://localhost:3306/dup22"; 
                String user = "root"; 
                String pwd = "vinayvini";
                
            con = DriverManager.getConnection(url, user, pwd);

          // flag=true;
           String q="select * from buses where l_date=? and l_from=? and l_to=?";
           PreparedStatement  pst = con.prepareStatement(q);
     
           pst.setString(1,d_date);
           pst.setString(2,d_from);
           pst.setString(3,d_to);
           ResultSet rs=pst.executeQuery();
           Graphics g = x.getGraphics();
        
         
         
        // p.execute();
        if(rs.next())
           {	
           
                 
                 String bus= rs.getString(2);
                 String from=rs.getString(3);
                 String to =rs.getString(4);
               // System.out.print(bus);
              if(bus.equals("ganga"))
              {
                System.out.println("ganga");
                flag=true;
                global.busname="ganga";
                new gangabus();
                
              }
              else if(bus.equals("krishna"))
              {
               global.busname="Krishna";
               
               flag=true;
               new krishnabus();

              }
              else if(bus.equals("garuda"))
              {
               global.busname="garuda";
               
               flag=true;
               new garudabus();
               
              }
              else if(bus.equals("kaveri"))
              {
               global.busname="kaveri";
               
               flag=true;
               new kaveribus();
               
              }
              
            

                  
           }
          
        }
        catch(Exception ex){
	    System.out.println(ex);
        } 
            
   }
   public void actionPerformed(ActionEvent e)
   {
	try
        {
           getconnection();
	   Graphics g = x.getGraphics();
         
           if(!flag)
           {
              g.drawString("Buses not found.", 250, 250);
	      Thread.currentThread().sleep(1000);
             
	      new  show_bus();
	      x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
}

class gangabus extends Frame{
   Choice rtc,rnc,bnc;
   List ls;
  
   CheckboxGroup cbg;
   gangabus()
   {
       
       
      Label l=new Label("ganga",Label.CENTER);
      Font mf = new Font("Arial", Font.PLAIN, 30);
     l.setFont(mf);
 
      l.setBounds(250,50,300,50);
     
      add(l);
      
       
      Label rt=new Label("select");
      // rt.setFont(f);
       rt.setBounds(150,150,100,30);
       add(rt);
      
        rtc = new Choice();
        rtc.add(" ");
       rtc.add("not_booked");
      
       rtc.setBounds(250,150,200,30);
    
       add(rtc);

       Label sub = new Label("not booked ");
       sub.setBounds(150,200,110,30);
      add(sub);
      ls = new List(10,true);
      ls.setBounds(280,200, 75, 75);
      ls.add("no_seat");
      
       add(ls);
       
       gangaListner rl=new gangaListner(this);
       rtc.addItemListener(rl);
      
       Button b=new Button("Submit");
       b.setBounds(250,400,100,50);
        add(b);
        //b.setFont(f);
      //  b.addActionListener(new submitListener(this));
      gangabuttonListener al=new gangabuttonListener(this);
      b.addActionListener(al);
       
       this.setLayout(new FlowLayout());
       this.setSize(800,800);
      this.setLayout(null);
       this.setVisible(true);
     
      
       
       this.addWindowListener(new NsrListener());
   }
}


class gangaListner implements ItemListener
{
gangabus x;
boolean flag=false;
gangaListner(gangabus x)
{
   this.x=x;
}
public void getConnection()
{
   Connection con=null;
   try{  
      

      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/dup22"; 
      String user = "root"; 
      String pwd = "vinayvini";
      con = DriverManager.getConnection(url, user, pwd);
      String s=x.rtc.getSelectedItem();
      if(s=="not_booked")
      {
         x.ls.removeAll();
         Statement st = con.createStatement();
        String query="select * from ganga where status='f'"; 
        ResultSet rs=st.executeQuery(query);
         while(rs.next())
         {
           String temp=rs.getString(1);
           x.ls.add(temp);
           System.out.println(temp);
         }

      }
     
      
   }
   catch(Exception e)
   {
       System.out.println(e);
   }

}
public void itemStateChanged(ItemEvent ie)
{
  getConnection();
}
}






class gangabuttonListener implements ActionListener
{  
        gangabus x; 
   boolean flag=false; 
   String pass;
   gangabuttonListener(gangabus x)
   {  
	this.x=x;  
   }  
   public void getconnection()
   {   // String busname;
       // global.u_name=x.name.getText();
       //  pass=x.pw.getText();
	Connection con=null;
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url= "jdbc:mysql://localhost:3306/dup22"; 
                String user = "root"; 
                String pwd = "vinayvini";
                
            con = DriverManager.getConnection(url, user, pwd);
            String ans[]=x.ls.getSelectedItems();
            for(int i=0;i<ans.length;i++)
            {    
               String q1="update ganga set status='t' where seatno=?";
               PreparedStatement  pst = con.prepareStatement(q1);
               pst.setString(1,ans[i]);
               pst.executeUpdate();

            }
            for(int i=0;i<ans.length;i++)
            {
            String q="insert into conform values(?,?,?,?,?,?)";
            PreparedStatement  pst = con.prepareStatement(q);
          
            pst.setString(1,global.u_name);
            pst.setString(2,global.busname);
            pst.setString(3,ans[i]);
            pst.setString(4,global.busfrom);
            pst.setString(5,global.busto);
            pst.setString(6,global.busdate);


            pst.executeUpdate();
            flag=true;
            }
            } 
         catch(Exception x)
       {
         System.out.println(x);
         };        
                
        
                
   }
   public void actionPerformed(ActionEvent e)
   {
	try
        {
           getconnection();
	   Graphics g = x.getGraphics();
           if(flag)
	     { g.drawString("booking succesful", 20, 250);
              Thread.currentThread().sleep(1000);
               new seats_booked();


       
         x.dispose();
        }
           else
           {
              g.drawString("unsuccessful.Try again", 20, 250);
	      Thread.currentThread().sleep(1000);
              
	      new show_bus();
	      x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 




class garudabus extends Frame{
   Choice rtc,rnc,bnc;
   List ls;
  
   CheckboxGroup cbg;
   garudabus()
   {
       
       
      Label l=new Label("garuda",Label.CENTER);
      Font mf = new Font("Arial", Font.PLAIN, 30);
     l.setFont(mf);
 
      l.setBounds(250,50,300,50);
     
      add(l);
      
       
      Label rt=new Label("select");
      // rt.setFont(f);
       rt.setBounds(150,150,100,30);
       add(rt);
      
        rtc = new Choice();
        rtc.add(" ");
       rtc.add("not_booked");
      
       rtc.setBounds(250,150,200,30);
    
       add(rtc);

       Label sub = new Label("not booked ");
       sub.setBounds(150,200,110,30);
      add(sub);
      ls = new List(10,true);
      ls.setBounds(280,200, 75, 75);
      ls.add("no_seat");
      
       add(ls);
       
       garudaListner rl=new garudaListner(this);
       rtc.addItemListener(rl);
      
       Button b=new Button("Submit");
       b.setBounds(250,400,100,50);
        add(b);
        //b.setFont(f);
      //  b.addActionListener(new submitListener(this));
      garudabuttonListener al=new garudabuttonListener(this);
      b.addActionListener(al);
       
       this.setLayout(new FlowLayout());
       this.setSize(800,800);
      this.setLayout(null);
       this.setVisible(true);
     
      
       
       this.addWindowListener(new NsrListener());
   }
}


class garudaListner implements ItemListener
{
garudabus x;
boolean flag=false;
garudaListner(garudabus x)
{
   this.x=x;
}
public void getConnection()
{
   Connection con=null;
   try{  
      

      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/dup22"; 
      String user = "root"; 
      String pwd = "vinayvini";
      con = DriverManager.getConnection(url, user, pwd);
      String s=x.rtc.getSelectedItem();
      if(s=="not_booked")
      {
         x.ls.removeAll();
         Statement st = con.createStatement();
        String query="select * from garuda where status='f'"; 
        ResultSet rs=st.executeQuery(query);
         while(rs.next())
         {
           String temp=rs.getString(1);
           x.ls.add(temp);
           System.out.println(temp);
         }

      }
     
      
   }
   catch(Exception e)
   {
       System.out.println(e);
   }

}
public void itemStateChanged(ItemEvent ie)
{
  getConnection();
}
}





class kaveribus extends Frame{
   Choice rtc,rnc,bnc;
   List ls;
  
   CheckboxGroup cbg;
   kaveribus()
   {
       
       
      Label l=new Label("kaveri",Label.CENTER);
      Font mf = new Font("Arial", Font.PLAIN, 30);
     l.setFont(mf);
 
      l.setBounds(250,50,300,50);
     
      add(l);
      
       
      Label rt=new Label("select");
      // rt.setFont(f);
       rt.setBounds(150,150,100,30);
       add(rt);
      
        rtc = new Choice();
        rtc.add(" ");
       rtc.add("not_booked");
      
       rtc.setBounds(250,150,200,30);
    
       add(rtc);

       Label sub = new Label("not booked ");
       sub.setBounds(150,200,110,30);
      add(sub);
      ls = new List(10,true);
      ls.setBounds(280,200, 75, 75);
      ls.add("no_seat");
      
       add(ls);
       
       kaveriListner rl=new kaveriListner(this);
       rtc.addItemListener(rl);
      
       Button b=new Button("Submit");
       b.setBounds(250,400,100,50);
        add(b);
      kaveributtonListener al=new kaveributtonListener(this);
      b.addActionListener(al);
       
       this.setLayout(new FlowLayout());
       this.setSize(800,800);
      this.setLayout(null);
       this.setVisible(true);
     
      
       
       this.addWindowListener(new NsrListener());
   }
}


class kaveriListner implements ItemListener
{
kaveribus x;
boolean flag=false;
kaveriListner(kaveribus x)
{
   this.x=x;
}
public void getConnection()
{
   Connection con=null;
   try{  
      

      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/dup22"; 
      String user = "root"; 
      String pwd = "vinayvini";
      con = DriverManager.getConnection(url, user, pwd);
      String s=x.rtc.getSelectedItem();
      if(s=="not_booked")
      {
         x.ls.removeAll();
         Statement st = con.createStatement();
        String query="select * from kaveri where status='f'"; 
        ResultSet rs=st.executeQuery(query);
         while(rs.next())
         {
           String temp=rs.getString(1);
           x.ls.add(temp);
           System.out.println(temp);
         }

      }
     
      
   }
   catch(Exception e)
   {
       System.out.println(e);
   }

}
public void itemStateChanged(ItemEvent ie)
{
  getConnection();
}
}

class kaveributtonListener implements ActionListener
{  
        kaveribus x; 
   boolean flag=false; 
   String pass;
   kaveributtonListener(kaveribus x)
   {  
    this.x=x;  
   }  
   public void getconnection()
   {  
    Connection con=null;
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url= "jdbc:mysql://localhost:3306/dup22"; 
                String user = "root"; 
                String pwd = "vinayvini";
                
            con = DriverManager.getConnection(url, user, pwd);
            String ans[]=x.ls.getSelectedItems();
            for(int i=0;i<ans.length;i++)
            {    
               String q1="update kaveri set status='t' where seatno=?";
               PreparedStatement  pst = con.prepareStatement(q1);
               pst.setString(1,ans[i]);
               pst.executeUpdate();

            }
            for(int i=0;i<ans.length;i++)
            {
            String q="insert into conform values(?,?,?,?,?,?)";
            PreparedStatement  pst = con.prepareStatement(q);
          
            pst.setString(1,global.u_name);
            pst.setString(2,global.busname);
            pst.setString(3,ans[i]);
            pst.setString(4,global.busfrom);
            pst.setString(5,global.busto);
            pst.setString(6,global.busdate);


            pst.executeUpdate();
            flag=true;
            }
            } 
         catch(Exception x)
       {
         System.out.println(x);
         };        
                
        
                
   }
   public void actionPerformed(ActionEvent e)
   {
    try
        {
           getconnection();
       Graphics g = x.getGraphics();
           if(flag)
         { g.drawString("booking succesful", 20, 250);
              Thread.currentThread().sleep(1000);
               new seats_booked();


       
         x.dispose();
        }
           else
           {
              g.drawString("unsuccessful.Try again", 20, 250);
          Thread.currentThread().sleep(1000);
              
          new show_bus();
          x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
}
class garudabuttonListener implements ActionListener
{  
        garudabus x; 
   boolean flag=false; 
   String pass;
   garudabuttonListener(garudabus x)
   {  
    this.x=x;  
   }  
   public void getconnection()
   {  
    Connection con=null;
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url= "jdbc:mysql://localhost:3306/dup22"; 
                String user = "root"; 
                String pwd = "vinayvini";
                
            con = DriverManager.getConnection(url, user, pwd);
            String ans[]=x.ls.getSelectedItems();
            for(int i=0;i<ans.length;i++)
            {    
               String q1="update garuda set status='t' where seatno=?";
               PreparedStatement  pst = con.prepareStatement(q1);
               pst.setString(1,ans[i]);
               pst.executeUpdate();

            }
            for(int i=0;i<ans.length;i++)
            {
            String q="insert into conform values(?,?,?,?,?,?)";
            PreparedStatement  pst = con.prepareStatement(q);
          
            pst.setString(1,global.u_name);
            pst.setString(2,global.busname);
            pst.setString(3,ans[i]);
            pst.setString(4,global.busfrom);
            pst.setString(5,global.busto);
            pst.setString(6,global.busdate);


            pst.executeUpdate();
            flag=true;
            }
            } 
         catch(Exception x)
       {
         System.out.println(x);
         };        
                
        
                
   }
   public void actionPerformed(ActionEvent e)
   {
    try
        {
           getconnection();
       Graphics g = x.getGraphics();
           if(flag)
         { g.drawString("booking succesful", 20, 250);
              Thread.currentThread().sleep(1000);
               new seats_booked();


       
         x.dispose();
        }
           else
           {
              g.drawString("unsuccessful.Try again", 20, 250);
          Thread.currentThread().sleep(1000);
              
          new show_bus();
          x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
}
class krishnabus extends Frame{
   Choice rtc,rnc,bnc;
   List ls;
  
   CheckboxGroup cbg;
   krishnabus()
   {
       
       
      Label l=new Label("krishna",Label.CENTER);
      Font mf = new Font("Arial", Font.PLAIN, 30);
     l.setFont(mf);
 
      l.setBounds(250,50,300,50);
     
      add(l);
      
       
       Label rt=new Label("book");
       rt.setBounds(150,150,100,30);
       add(rt);
      
        rtc = new Choice();
        rtc.add(" ");
       rtc.add("not_booked");
       rtc.setBounds(250,150,200,30);
    
       add(rtc);

       Label sub = new Label("not booked ");
        sub.setBounds(150,200,110,30);
      add(sub);
      ls = new List(10,true);
      ls.setBounds(280,200, 75, 75);
      ls.add("no_seat");
      add(ls);
       
       krishnaListner rl=new krishnaListner(this);
       rtc.addItemListener(rl);
      
       Button b=new Button("Submit");
       b.setBounds(250,400,100,50);
       add(b);
      krishnabuttonListener al=new krishnabuttonListener(this);
      b.addActionListener(al);
       
       this.setLayout(new FlowLayout());
       this.setSize(800,800);
      this.setLayout(null);
       this.setVisible(true);
     
      
       
       this.addWindowListener(new NsrListener());
   }
}




class krishnaListner implements ItemListener
{
krishnabus x;
boolean flag=false;
krishnaListner(krishnabus x)
{
   this.x=x;
}
public void getConnection()
{
   Connection con=null;
   try{  
      

      Class.forName("com.mysql.cj.jdbc.Driver");

      String url= "jdbc:mysql://localhost:3306/dup22"; 
      String user = "root"; 
      String pwd = "vinayvini";
      con = DriverManager.getConnection(url, user, pwd);
      String s=x.rtc.getSelectedItem();
      if(s=="not_booked")
      {
         x.ls.removeAll();
         Statement st = con.createStatement();
        String query="select * from Krishna where status='f'"; 
        ResultSet rs=st.executeQuery(query);
         while(rs.next())
         {
           String temp=rs.getString(1);
           x.ls.add(temp);
           System.out.println(temp);
         }

      }
     
      
   }
   catch(Exception e)
   {
       System.out.println(e);
   }

}
public void itemStateChanged(ItemEvent ie)
{
  getConnection();
}
}






class krishnabuttonListener implements ActionListener
{  
        krishnabus x; 
   boolean flag=false; 
   String pass;
   krishnabuttonListener(krishnabus x)
   {  
	this.x=x;  
   }  
   public void getconnection()
   {   
	Connection con=null;
        try{  
                Class.forName("com.mysql.cj.jdbc.Driver");

                String url= "jdbc:mysql://localhost:3306/dup22"; 
                String user = "root"; 
                String pwd = "vinayvini";
                
            con = DriverManager.getConnection(url, user, pwd);
            String ans[]=x.ls.getSelectedItems();
            for(int i=0;i<ans.length;i++)
            {    
               String q1="update Krishna set status='t' where seatno=?";
               PreparedStatement  pst = con.prepareStatement(q1);
               pst.setString(1,ans[i]);
               pst.executeUpdate();

            }
            for(int i=0;i<ans.length;i++)
            {
            String q="insert into conform values(?,?,?,?,?,?)";
            PreparedStatement  pst = con.prepareStatement(q);
          
            pst.setString(1,global.u_name);
            pst.setString(2,global.busname);
            pst.setString(3,ans[i]);
            pst.setString(4,global.busfrom);
            pst.setString(5,global.busto);
            pst.setString(6,global.busdate);


            pst.executeUpdate();
            flag=true;
            }
            } 
         catch(Exception x)
       {
         System.out.println(x);
         };        
                
        
                
   }
   public void actionPerformed(ActionEvent e)
   {
	try
        {
           getconnection();
	   Graphics g = x.getGraphics();
           if(flag)
	     { g.drawString("successful", 20, 250);
              Thread.currentThread().sleep(1000);
               new seats_booked();


       
         x.dispose();
        }
           else
           {
              g.drawString("Login unsuccessful.Try again", 20, 250);
	      Thread.currentThread().sleep(1000);
              
	      new show_bus();
	      x.dispose();
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 



class seats_booked extends Frame 
{ 
   Button b,b1;
   seats_booked()	
   {
	b = new Button("view");
	b.setBounds(100,400,70,40);
   b1 = new Button("menu");
	b1.setBounds(300,400,70,40);
	
	add(b);
   add(b1);		
    
   seats_bookedListener al=new seats_bookedListener(this);
	b.addActionListener(al);
   m1buttonListener bl=new m1buttonListener(this);
	b1.addActionListener(bl); 

             this.setSize(500,500);
             this.setTitle("seats booked");
             this.setLayout(null);
             this.setVisible(true);

             this.addWindowListener(new NsrListener());

  }
}
class seats_bookedListener implements ActionListener
{  
        seats_booked x; 
      String nam,bu,sea,fro,too,dat;  
   boolean flag=false; 
   int k=0;
   seats_bookedListener(seats_booked x)
   {  
	this.x=x;  
   }  
   public void getconnection()
   {
	Connection con=null;
        try{  
           Class.forName("com.mysql.cj.jdbc.Driver");
     
           String url= "jdbc:mysql://localhost:3306/dup22"; 
           String user = "root"; 
           String pwd = "vinayvini";
           con = DriverManager.getConnection(url, user, pwd);
           flag=true;
           String q="select * from conform where c_name=?";
           PreparedStatement  pst = con.prepareStatement(q);
     
           pst.setString(1,global.u_name);
           ResultSet rs=pst.executeQuery();
           Graphics g = x.getGraphics();
          int yy=110;
           while(rs.next())
           {	
                  nam = rs.getString(1);
                  bu = rs.getString(2);
                
                   sea = rs.getString(3);
                   fro = rs.getString(4);
                   too=rs.getString(5);
                  dat=rs.getString(6);
                  
                   g.drawString(nam, 80, yy);
                   g.drawString(bu, 135, yy);
                   g.drawString(sea, 190, yy);	
                   g.drawString(fro, 245, yy);
                 g.drawString(too, 300, yy);
                 g.drawString(dat, 355, yy);	
                 k=1;
                	flag=true;
                  yy=yy+50;
           }
           if(k==0)
                   g.drawString("No booked seats ",150,200);
                
        }
        catch(Exception ex){
	    System.out.println(ex);
        } 
            
   }
   public void actionPerformed(ActionEvent e)
   {
	try
        {
           getconnection();
	   Graphics g = x.getGraphics();
          
           if(!flag)
           {
              g.drawString(" unsuccessful.Try again", 20, 250);
	      Thread.currentThread().sleep(10000);
              //x.repaint();//we can try this instead of following two lines
	     
           }
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 
class m1buttonListener implements ActionListener
{  
        seats_booked x; 
     
   int k=0;
   m1buttonListener(seats_booked x)
   {  
	this.x=x;  
   }  
   
   public void actionPerformed(ActionEvent e)
   {
	try
        {
       
	      new mainmenu();
	      x.dispose();
           
        }catch(Exception x)
         {
          System.out.println(e);
           };
   }
} 




class NsrListener extends WindowAdapter{
  public void windowClosing(WindowEvent e){
	System.exit(0); 
  }
}
class LoginFrame_demo
{	
   public static void main(String args[]) throws Exception
   {
	 new mainFrame();
   } 
}