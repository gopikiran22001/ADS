#include <iostream>
using namespace std;
int main() {
    int n,i,q=0;
    cout<<"Enter a Digit:";
    cin>>n;
    for(i=2;i<n;i++)
    {
        if(n%i==0)
        {
            q+=1;
        }
    }
    if(q==0)
    {
        cout<<n<<" is a prime Number\n";
    }
    else
    {
        cout<<n<<" is a not prime Number\n";
    }
    return 0;
}