#include<iostream>
using namespace std;
int main()
{
    int a[]={6,5,4,3,2,1};
    int n=sizeof(a)/sizeof(a[0]);
    for(int i=1;i<n;i++)
    {
        int key=a[i];
        int j=i-1;
        while(j>=0 && a[j]>key)
        {
            a[j+1]=a[j];
            j--;
        }
        a[j+1]=key;
    }
    for(int i:a)
    {
        cout<<i<<endl;
    }
}
