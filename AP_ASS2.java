import java.util.*;
import java.io.*;
import java.lang.*;
class AP_ASS2
{
    public static void main(String[] args) 
    {
       Scanner inpu=new Scanner(System.in);
       ArrayList<Company> companies = new ArrayList<Company>();
       ArrayList<Students> students = new ArrayList<Students>();
       ArrayList<Integer> placed_stud=new ArrayList<Integer>();
       System.out.print("Enter No. of Students ");
       int no_stud=inpu.nextInt();
       for(int i=0;i<no_stud;i++)
       {
           System.out.print("Enter CGPA ");
           float cgpa=inpu.nextFloat();
           inpu.nextLine();
           System.out.print("Enter Branch ");
           String branch=inpu.nextLine();
           Students s1=new Students(branch, cgpa);
           students.add(s1);
          
           
       }
   
       while(true)
       {
   

           if(no_stud==placed_stud.size())
           {
               break;
           }
           System.out.print("Enter Query and Required Attributes");
           int quer_var=inpu.nextInt();
           //Add code to check if all studs placed
           if (quer_var==1)
           {
            System.out.println("New Company Addition");
            System.out.print("Enter Name ");
            inpu.nextLine();
            String name=inpu.nextLine();
            System.out.print("Enter no. of Reqd. Students ");
            int no_stud_reqd=inpu.nextInt();
            inpu.nextLine();
            ArrayList<String> Coursereq=new ArrayList<String>();
            System.out.print("Enter No. of Reqd Courses ");
            int no_elg_courses=inpu.nextInt();
            inpu.nextLine();
            for (int j=0;j<no_elg_courses;j++)
            {
                Coursereq.add(inpu.nextLine());
            }
            Company c1=new Company(name, no_elg_courses, Coursereq, no_stud_reqd);
            companies.add(c1);
            
            c1.disp();
            for(int j1=0;j1<students.size();j1++)
            {
                for(int j2=0;j2<Coursereq.size();j2++)
                {
                    if(students.get(j1).branch.equals(Coursereq.get(j2)))
                    {
                        c1.elg_stud.add(students.get(j1));
                        System.out.println("Enter Marks for Tech. Round for Student "+students.get(j1).rno);
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
            System.out.println(" Studets Placed are ");
            for (int k=0;k<placed_stud.size();k++)
            {
                System.out.println(placed_stud.get(k));
            }
            
           }
           else if (quer_var==3)
           {
            for (int l=0;l<companies.size();l++)
            {
                if (companies.get(l).app_stat==false)
                {
                    System.out.print(companies.get(l).name+"  ");
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
                if (companies.get(m).app_stat==true)
                {
                    System.out.println(companies.get(m).name);
                }
            }
            

           }
           else if (quer_var==6)
           {
            System.out.println("Enter Name of Company ");
            inpu.nextLine();
            String temp=inpu.nextLine();
            System.out.println("Students Placed Here");
            for (int n=0;n<companies.size();n++)
            {
                if (companies.get(n).name.equals(temp))
                {
                    
                    Collections.sort(companies.get(n).sel_student_here);
                    int cnt=0;
                    for(int n1=0;n1<companies.get(n).sel_student_here.size();n1++)
                    {
                        if (companies.get(n).sel_student_here.get(n1).s1.plac_stat==false)
                        {
                            cnt++;
                        }
                    }
                    if (companies.get(n).no_stud_reqd>cnt)
                    {
                        for(int n2=0;n2<cnt;)
                        {
                            if (companies.get(n).sel_student_here.get(n2).s1.plac_stat==false)
                            {
                                placed_stud.add(companies.get(n).sel_student_here.get(n2).s1.rno);
                                companies.get(n).stud_sel.add(companies.get(n).sel_student_here.get(n2).s1.rno);
                                students.get(companies.get(n).sel_student_here.get(n2).s1.rno-1).plac_stat=true;
                                n2++;
                            }
                        }
                    }
                    else 
                    {
                        for(int n3=0;n3<companies.get(n).no_stud_reqd;)
                        {
                            if (companies.get(n).sel_student_here.get(n3).s1.plac_stat==false)
                            {
                                placed_stud.add(companies.get(n).sel_student_here.get(n3).s1.rno);
                                companies.get(n).stud_sel.add(companies.get(n).sel_student_here.get(n3).s1.rno);
                                students.get(companies.get(n).sel_student_here.get(n3).s1.rno-1).plac_stat=true;
                                n3++;
                            }

                        }
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
                if (companies.get(o).name.equals(temp1))
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
                if (students.get(p).rno==rno1)
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
                    if(companies.get(q).sel_student_here.get(q1).s1.rno==rno1)
                    {
                        System.out.println(companies.get(q).name+" "+companies.get(q).sel_student_here.get(q1).score);
                    }
                }
            }
           }

           else
           {
            System.out.println("Wrong Input, Please try Again");
           }
           
       }
       inpu.close(); 
    }
}
class Company
{
    String name;
    int no_elg_courses;
    ArrayList<String> Coursereq=new ArrayList<String>();
    ArrayList<Integer> stud_sel=new ArrayList<Integer>();
    ArrayList<Students> elg_stud=new ArrayList<Students>();
    ArrayList<Integer> tech_scores=new ArrayList<Integer>();
    ArrayList<Place_Students> sel_student_here=new ArrayList<Place_Students>();
    int no_stud_reqd;
    Boolean app_stat;
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
    int rno;
    static int rno_var=1;
    String branch;
    float cgpa;
    Boolean plac_stat=false;
    String company;
    
    
    Students(String branch, float cgpa)
    {
        this.rno=rno_var++;
        this.branch=branch;
        this.cgpa=cgpa;
        
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
    Students s1;
    int score;
    Place_Students(Students s1,int score)
    {
        this.s1=s1;
        this.score=score;
    }
    @Override
    public int compareTo(Place_Students p1)
    {
        if(this.score==p1.score)
        {
            if(this.s1.cgpa>p1.s1.cgpa)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else if (this.score>p1.score)
        {
            return 1;
        }
        else 
        {
            return -1;
        }
    }
}