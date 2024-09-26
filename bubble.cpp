#include<iostream>
using namespace std;
int main()
{
    int a[]={6,5,4,3,2,1};
    int n=sizeof(a)/sizeof(a[0]);
    for(int i=0;i<n-1;i++)
    {
        for(int j=i+1;j<n;j++)
        {
            if(a[i]>a[j])
            {
                int t=a[i];
                a[i]=a[j];
                a[j]=t;
            }
        }
    }
    for(auto i:a)
    {
     cout<<i<<endl;
    }
}
