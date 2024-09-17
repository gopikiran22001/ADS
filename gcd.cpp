#include <iostream>
using namespace std;
int main() {
    int n1,i,n2,gcd;
    cout<<"Enter two numbers:\n";
    cin>>n1>>n2;
    if(n1<n2)
    {
        int temp=n1;
        n1=n2;
        n2=temp;
    }
    for(i=1;i<=n2;i++)
    {
        if(n1%i==0 && n2%i==0)
        {
            gcd=i;
        }
    }
    cout<<"Greatest factor is:"<<gcd;
    return 0;
}