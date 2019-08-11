import java.util.*;
import java.io.*;
import java.lang.*;

/* 

Name: Aditya Rastogi
Roll No. 2018273

The Code consists of 4 Classes in total

Class AP_ASS2: It is the class which contains of the main function of the program
                The menu driven functionality is added in the mai  function itself

Class Students: This class is used for storing the data of any particular Student.
                All variables of the class are private in nature and appropriate getters and setters are provided
                wherever required.
                Variables:
                        rno: Integer variable which stores the value of Unique Roll Number
                        rno_var: Static variable used to compute rno
                        branch: String variable, stores the branch
                        cgpa: float variable, stores gpa
                        company: string variable, stores where the student is finally placed
                        plac_stat: Boolean Variable, true if student is placed and false otherwise
Class Place_Students:
              This class overrides the in-built collections.sort function using the CompareTo function.
              The class takes two variables one of type Students and one of type integer.
              An ArrayList of this class is created to then place students.
              Variables:
                        s1: Students type object
                        score: Integer datatype, stores marks scored by students in the technical round

Class Company:
                This class used for storing the data of any particular company.
                All variables are private whereas the ArrayLists are public.
                Variables:
                    name: String datatype, store the name of the company
                    no_elg_course: Integer data type, stores the number of different course criteria
                    app_stat:Boolean datatype, true if Application Status is Open and false otherwise
                    no_stud_reqd: Integer Datatype to store number of openings in the company
                ArrayLists:
                    Coursereq: String Type, stores course criteria as individual string elements
                    stud_sel:Integer Type,Roll Numbers of finally placed Students for a particular company
                    elg_stud:Students Type, All students which satisfy the course criteria are stored here
                    tech_scores:Integer Type, Score of all students who sat for the Technical round, Roll No. wise
                    sel_student_here:Place_Students Type, Combines elg_stud and tech_scores ArrayList

*/
class AP_ASS2
{
    public static void main(String[] args) 
    {
       Scanner inpu=new Scanner(System.in);// Scanner Variable Initialisation
       ArrayList<Company> companies = new ArrayList<Company>();// Array List for all Companies
       ArrayList<Students> students = new ArrayList<Students>();// Array List for all Students
       ArrayList<Integer> placed_stud=new ArrayList<Integer>();// Array List for all students who are placed anywhere, Only RollNo
       System.out.print("Enter No. of Students ");
       int no_stud=inpu.nextInt();
       for(int i=0;i<no_stud;i++)
       {
           System.out.print("Enter CGPA ");
           float cgpa=inpu.nextFloat();
           inpu.nextLine();// Consumes Extra \n
           System.out.print("Enter Branch ");
           String branch=inpu.nextLine();
           Students s1=new Students(branch, cgpa);
           students.add(s1);
          
           
       }
   
       while(true)//Infinte Loop which gets broken only when all students are placed
       {
   

           if(no_stud==placed_stud.size())//Condition for All students being placed
           {
               System.out.println("***********Thank You, All Students are Placed***********");
               break;
           }
           System.out.print("Enter Query and Required Attributes: ");
           int quer_var=inpu.nextInt();
            if (quer_var==1)
           {
            System.out.println("New Company Addition");
            System.out.print("Enter Name ");
            inpu.nextLine();// Consumes Extra \n
            String name=inpu.nextLine();
            System.out.print("Enter no. of Reqd. Students ");
            int no_stud_reqd=inpu.nextInt();
            inpu.nextLine();// Consumes Extra \n
            ArrayList<String> Coursereq=new ArrayList<String>();
            System.out.print("Enter No. of Reqd Courses ");
            int no_elg_courses=inpu.nextInt();
            inpu.nextLine();// Consumes Extra \n
            for (int j=0;j<no_elg_courses;j++)
            {
                Coursereq.add(inpu.nextLine());
            }
            Company c1=new Company(name, no_elg_courses, Coursereq, no_stud_reqd);
            companies.add(c1);
            
            c1.disp();
            //Loops to check and compute all eligible Students
            for(int j1=0;j1<students.size();j1++)
            {
                for(int j2=0;j2<Coursereq.size();j2++)
                {
                    if(students.get(j1).getBranch().equals(Coursereq.get(j2)))
                    {
                        c1.elg_stud.add(students.get(j1));
                        System.out.println("Enter Marks for Tech. Round for Student "+students.get(j1).getRno());
                        int marks=inpu.nextInt();
                        c1.tech_scores.add(marks);
                        Place_Students obj=new Place_Students(students.get(j1), marks);
                        c1.sel_student_here.add(obj);
                    }
                }
            }


            }
           else if (quer_var==2)
           {
            System.out.println("Removed Students are ");
            for (int k=0;k<placed_stud.size();k++)
            {
                System.out.println(placed_stud.get(k));
            }
            
           }
           else if (quer_var==3)
           {
               System.out.println("Removed Companies Are ");
            for (int l=0;l<companies.size();l++)
            {
                if (companies.get(l).getApp_stat()==false)
                {
                    System.out.print(companies.get(l).getName()+"  ");
                }
            }
            System.out.println();
           }
           else if (quer_var==4)
           {
            int temp=no_stud-placed_stud.size(); 
            System.out.print("No. Students Left to Be Placed: "+temp);
            System.out.println();
           }
           else if (quer_var==5)
           {
            for (int m=0;m<companies.size();m++)
            {
                if (companies.get(m).getApp_stat()==true)
                {
                    System.out.println(companies.get(m).getName());
                }
                else
                {
                    System.out.println("Account already removed or does not exist");
                }
            }
            

           }
           else if (quer_var==6)
           {
            System.out.println("Enter Name of Company ");
            inpu.nextLine();// Consumes Extra \n
            String temp=inpu.nextLine();
            System.out.println("Students Placed Here Are: ");
            for (int n=0;n<companies.size();n++)
            {
                if (companies.get(n).getName().equals(temp))
                {
                    
                    Collections.sort(companies.get(n).sel_student_here);
                    int cnt=0;
                    // Counter to calculate number of unplaced students from
                    //total eligible students
                    for(int n1=0;n1<companies.get(n).sel_student_here.size();n1++)
                    {
                        if (companies.get(n).sel_student_here.get(n1).getS1().getPlac_stat()==false)
                        {
                            cnt++;
                        }
                    }
                    if (companies.get(n).getNo_stud_reqd()>cnt)
                    {
                        for(int n2=0;n2<cnt;)
                        {
                            if (companies.get(n).sel_student_here.get(n2).getS1().getPlac_stat()==false)
                            {
                                System.out.print(companies.get(n).sel_student_here.get(n2).getS1().getRno()+" ");
                                placed_stud.add(companies.get(n).sel_student_here.get(n2).getS1().getRno());
                                companies.get(n).stud_sel.add(companies.get(n).sel_student_here.get(n2).getS1().getRno());
                                students.get(companies.get(n).sel_student_here.get(n2).getS1().getRno()-1).setPlac_stat(true);
                                n2++;
                            }
                        }
                    }
                    else 
                    {
                        for(int n3=0;n3<companies.get(n).getNo_stud_reqd();)
                        {
                            if (companies.get(n).sel_student_here.get(n3).getS1().getPlac_stat()==false)
                            {
                                System.out.print(companies.get(n).sel_student_here.get(n3).getS1().getRno()+" ");
                                placed_stud.add(companies.get(n).sel_student_here.get(n3).getS1().getRno());
                                companies.get(n).stud_sel.add(companies.get(n).sel_student_here.get(n3).getS1().getRno());
                                students.get(companies.get(n).sel_student_here.get(n3).getS1().getRno()-1).setPlac_stat(true);
                                n3++;
                            }

                        }
                    }

                    if(companies.get(n).getNo_stud_reqd()==companies.get(n).stud_sel.size())
                    {
                        companies.get(n).setApp_stat(false);;
                    }
                
                }
            }
            System.out.println();
           }
           else if (quer_var==7)
           {
            System.out.println("Enter Name of Company ");
            inpu.nextLine();
            String temp1=inpu.nextLine();
            for (int o=0;o<companies.size();o++)
            {
                if (companies.get(o).getName().equals(temp1))
                {
                    companies.get(o).disp();
                }
            }
            System.out.println();
           }
           else if (quer_var==8)
           {
            System.out.println("Enter Roll No. ");
            int rno1=inpu.nextInt();
            
            for (int p=0;p<students.size();p++)
            {
                if (students.get(p).getRno()==rno1)
                {
                    students.get(p).disp();
                }
            }
            System.out.println();

           }
           else if (quer_var==9)
           {
            System.out.println("Enter Roll No. ");
            int rno1=inpu.nextInt();
            for(int q=0;q<companies.size();q++)
            {
                for (int q1=0;q1<companies.get(q).sel_student_here.size();q1++)
                {
                    if(companies.get(q).sel_student_here.get(q1).getS1().getRno()==rno1 && companies.get(q).sel_student_here.get(q1).getS1().getPlac_stat()==false)
                    {
                        System.out.println(companies.get(q).getName()+" "+companies.get(q).sel_student_here.get(q1).getScore());
                    }
                    else
                    {
                        System.out.println("Student already Placed or does not Exist");
                    }
                }
            }
           }

           else
           {
            System.out.println("Wrong Input, Please try Again");
           }
           
       }
       inpu.close(); //Close Scanner
    }
}
class Company
{
    private String name;
    private int no_elg_courses;
    ArrayList<String> Coursereq=new ArrayList<String>();
    ArrayList<Integer> stud_sel=new ArrayList<Integer>();
    ArrayList<Students> elg_stud=new ArrayList<Students>();
    ArrayList<Integer> tech_scores=new ArrayList<Integer>();
    ArrayList<Place_Students> sel_student_here=new ArrayList<Place_Students>();
    private int no_stud_reqd;
    private Boolean app_stat;
    Company(String name, int no_elg_courses, ArrayList<String> Coursereq, int no_stud_reqd)
    {
        this.name=name;
        this.no_elg_courses=no_elg_courses;
        this.no_stud_reqd=no_stud_reqd;
        for(int i=0;i<this.no_elg_courses;i++)
        {
            this.Coursereq.add(Coursereq.get(i));
        }
        app_stat=true;

    }
    /**
     * @return the app_stat
     */
    public Boolean getApp_stat() {
        return app_stat;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @return the no_elg_courses
     */
    public int getNo_elg_courses() {
        return no_elg_courses;
    }
    /**
     * @return the no_stud_reqd
     */
    public int getNo_stud_reqd() {
        return no_stud_reqd;
    }
    /**
     * @param app_stat the app_stat to set
     */
    public void setApp_stat(Boolean app_stat) {
        this.app_stat = app_stat;
    }
    void disp()
    {
        System.out.println("Name: "+this.name);
        System.out.println("No. of Students Reqd: "+this.no_stud_reqd);
        System.out.print("Course Criteria ");
        for(int i=0;i<this.no_elg_courses;i++)
        {
            System.out.print(this.Coursereq.get(i)+" ");
        }    
        System.out.println();   
        String s="";
        if (this.app_stat==true){
            s="Open";
        }
        else{
            s="Closed";
        }
        System.out.println("Application Status "+s);
    }

}
class Students
{
    private int rno;
    private static int rno_var=1;
    private String branch;
    private float cgpa;
    private Boolean plac_stat=false;
    private String company;
    
    
    Students(String branch, float cgpa)
    {
        this.rno=rno_var++;
        this.branch=branch;
        this.cgpa=cgpa;
        
    }
    /**
     * @param branch the branch to set
     */
    /**
     * @return the branch
     */
    public String getBranch() {
        return branch;
    }
    /**
     * @return the cgpa
     */
    public float getCgpa() {
        return cgpa;
    }
    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }
    /**
     * @return the plac_stat
     */
    public Boolean getPlac_stat() {
        return plac_stat;
    }
    /**
     * @return the rno
     */
    public int getRno() {
        return rno;
    }
    /**
     * @param plac_stat the plac_stat to set
     */
    public void setPlac_stat(Boolean plac_stat) {
        this.plac_stat = plac_stat;
    }
    void disp()
    {
        System.out.println("Roll No: "+this.rno);
        System.out.println("Branch: "+this.branch);
        System.out.println("CGPA: "+this.cgpa); 
        String s="";
        if (this.plac_stat==true){
            s="Placed";
            s=s+"Company is "+this.company;
        }
        else{
            s="Not Placed";
        }
        System.out.println("Placement Status: "+s);
        
    }
}
class Place_Students implements Comparable<Place_Students>
{
    private Students s1;
    private int score;
    Place_Students(Students s1,int score)
    {
        this.s1=s1;
        this.score=score;
    }
     /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
    /**
     * @return the s1
     */
    public Students getS1() {
        return s1;
    }
    @Override
    public int compareTo(Place_Students p1)
    {
        if(this.score==p1.score)
        {
            if(this.s1.getCgpa()>p1.s1.getCgpa())
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
        else if (this.score>p1.score)
        {
            return -1;
        }
        else 
        {
            return 1;
        }
    }
}