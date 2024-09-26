#include<iostream>
#include<cstring>
#include<queue>
using namespace std;
class Job
{
    public:
    int id;
    string name;
    int pri;
    Job(int id,string name,int pri)
    {
        this->id=id;
        this->name=name;
        this->pri=pri;
    }
    friend bool operator<(Job,Job);
};
bool operator<(Job j1,Job j2)
{
    return j1.pri<j2.pri;
}
int main()
{
    priority_queue<Job> j;
    j.push(Job(1,"Job 1",4));
    j.push(Job(2,"Job 2",6));
    j.push(Job(3,"Job 3",8));
    j.push(Job(4,"Job 4",7));
    j.push(Job(5,"Job 5",5));
    cout<<"Executing according to Priority.."<<endl;
    while(!j.empty())
    {
        cout<<j.top().id<<" "<<j.top().name<<" "<<j.top().pri<<endl;
        j.pop();
    }
}
