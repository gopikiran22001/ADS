#include<iostream>
#include<set>
#include<cstring>
using namespace std;
class Student
{
    public:
    int roll;
    string name,branch;
    double cgpa;
    Student(int roll,string name,string branch,double cgpa)
    {
        this->roll=roll;
        this->name=name;
        this->branch=branch;
        this->cgpa=cgpa;
    }
    friend bool operator<(Student,Student);
};
bool operator<(Student s1,Student s2)
{
    return s1.roll<s2.roll;
}
int main()
{
    set<Student> s;
    s.insert(Student(8,"Gopi","CSE",84.7));
    s.insert(Student(5,"Apparao","CSE",92.6));
    s.insert(Student(1,"Pranavi","CSE",79.7));
    s.insert(Student(7,"Sai","CSE",70.5));
    s.insert(Student(16,"Prem","CSE",95.8));
    for(auto i:s)
    {
        cout<<"Roll NO: "<<i.roll<<endl;
        cout<<"Name: "<<i.name<<endl;
        cout<<"Branch: "<<i.branch<<endl;
        cout<<"CGPA: "<<i.cgpa<<endl;
        cout<<endl;
    }
}
