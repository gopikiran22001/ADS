#include <iostream>
using namespace std;
int main() {
    int n,i,sum=0;
    cout<<"Enter a Digit:";
    cin>>n;
    for(i=1;i<n;i++)
    {
        if(n%i==0)
        {
            sum+=i;
        }
    }
    if(sum==n)
    {
        cout<<n<<" is a perfect Number\n";
    }
    else
    {
        cout<<n<<" is a not perfect Number\n";
    }
    return 0;
}