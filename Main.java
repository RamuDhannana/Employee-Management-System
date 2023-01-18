import java.sql.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  

import java.util.Scanner;  
class Main{
    Scanner in =new Scanner(System.in);  
    public void addEmployee()
    {
      String fname1,lname1,dob1,title1,eid1,sdate="",edate="9999-12-31";
      int ed1=0;
      sdate+=java.time.LocalDate.now();
      Scanner in =new Scanner(System.in);
      System.out.println("Enter Employee First Name:");
      fname1=in.nextLine();
      System.out.println("Enter Employee Last Name:");
      lname1=in.nextLine();
      System.out.println("Enter Employee Date Of Birth:");
      dob1=in.nextLine();
      System.out.println("Enter Employee Title(like Analyst,MD):");
      title1=in.nextLine();
      eid1=lname1+fname1.substring(0,2);
      try{    
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/mydb","root","Ramu2002");  
        Statement stmt=con.createStatement();  
        String sql = "INSERT INTO emp(eid,fname,lname,dob,sdate,edate,title)VALUES(?,?,?,?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,eid1);
        statement.setString(2,fname1);
        statement.setString(3, lname1);
        statement.setString(4, dob1);
        statement.setString(5, sdate);
        statement.setString(6,edate);
        statement.setString(7, title1);
        statement.executeUpdate();
        System.out.println("Employee Kerberos Id: "+eid1);
            con.close();  
        }catch(Exception e){ System.out.println(e);}
    }
    public void search()
    {
       String eid1;
       System.out.println("Enter Employee Kerberos Id:");
       eid1=in.nextLine();
       try{   
        Connection con=DriverManager.getConnection(  
        "jdbc:mysql://localhost:3306/mydb","root","Ramu2002");  
        Statement stmt=con.createStatement(); 
        PreparedStatement statement = con.prepareStatement("select * from emp where eid = ?");    
        statement.setString(1, eid1);    
        ResultSet rs = statement.executeQuery();
        System.out.println("----------------EMPLOYEE Details------------------");
        while(rs.next())  
        {
            System.out.println("Employee Name   "+rs.getString(2));
            System.out.println("Employee Date Of Birth:  "+rs.getString(4));
            System.out.println("Employee Position :"+rs.getString(7));
            System.out.println("Employee Joing Date:  "+rs.getString(5));
            System.out.println("Employee End Date "+rs.getString(6));


        }  
        System.out.println("----------------------------------------------------");
        con.close();  
        }catch(Exception e){ System.out.println(e);}

    }
    public void filters()
    {
            int ft;
            System.out.println("Select Filter 1.IsActive or 2.Title\nEnter Option:");
            ft=in.nextInt();
            if(ft==1)
            {
                Scanner in =new Scanner(System.in);
                String a ;
                System.out.println("Enter Active Date:");
                a=in.nextLine();
                try
                {  
                      
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb","root","Ramu2002");  
                    Statement stmt=con.createStatement(); 
                    PreparedStatement statement = con.prepareStatement("select * from emp where edate <> 0 and  edate > ? ");    
                    statement.setString(1, a); 
                    ResultSet rs = statement.executeQuery();
                    System.out.println("EMPLOYEE Details\n\n");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("|Employee Name      |Title             |Join Date");
                    while(rs.next())  
                    {
                        System.out.println("|"+rs.getString(2)+"     |"+rs.getString(7)+"      |"+rs.getString(5));
                    }
                    System.out.println("--------------------------------------------------------");
                    con.close();  
                }catch(Exception e){ System.out.println(e);}

            } 

            else
            {
                Scanner in =new Scanner(System.in);
                String a ,name="";
                System.out.println("Enter Title:");
                a=in.nextLine();
                try
                {  
                     
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb","root","Ramu2002");  
                    Statement stmt=con.createStatement(); 
                    PreparedStatement statement = con.prepareStatement("select * from emp where  title= ? ");    
                    statement.setString(1, a); 
                    ResultSet rs = statement.executeQuery();
                    System.out.println("EMPLOYEE Details\n\n");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("|Employee Name      |Title             |Join Date");
                    while(rs.next())  
                    {
                        System.out.println("|"+rs.getString(2)+"     |"+rs.getString(7)+"      |"+rs.getString(5));
                    }
                    System.out.println("--------------------------------------------------------");
                    con.close();  
                }catch(Exception e){ System.out.println(e);}
            }

    }
    public void terminate()
    {
        Scanner in =new Scanner(System.in);
        String edate ,name="",id;
        System.out.println("Enter Kerberos Id:");
        id=in.nextLine();
        System.out.println("Enter End Date:");
        edate=in.nextLine();
        try
        { 
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/mydb","root","Ramu2002");  
            Statement stmt=con.createStatement(); 
            PreparedStatement statement = con.prepareStatement("update emp set edate=? where eid =?");    
            statement.setString(1, edate);
            statement.setString(2, id);
            int x = statement.executeUpdate();
            PreparedStatement statement1 = con.prepareStatement("Select * From emp where eid=?");    
            statement1.setString(1, id);
            ResultSet rs = statement1.executeQuery();
            System.out.println("EMPLOYEE Details\n\n");
            System.out.println("--------------------------------------------------------");
            System.out.println("|Employee Name      |Title             |End  Date");
            while(rs.next())  
            {
                name+=rs.getString(2)+"  "+rs.getString(3);
                System.out.println("|"+name+"     |"+rs.getString(7)+"      |"+rs.getString(6));
            }
            System.out.println("--------------------------------------------------------");
          con.close();  
        }catch(Exception e){ System.out.println(e);}
    }

    public static void main(String args[])
    {
        int choice=1,option;
        Main em= new Main();
        Scanner in =new Scanner(System.in);
        while(true)
        {
            System.out.println( "Select Choice:\n1.Add Employee\n2.Search Employee\n3.Filter\n4.Terminate Employee");
            option=in.nextInt();
            switch(option)
            {
                case 1:em.addEmployee();
                       break;
                case 2:em.search();
                       break;
                case 3:em.filters();
                       break;
                case 4:em.terminate();
                break;
            }
            System.out.println("Enter Choice U want Continue 1 or 0:");
            choice=in.nextInt();
            if (choice==0)
            {
                break;
            }

        }
    }
}