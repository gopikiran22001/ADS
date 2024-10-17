
#include<iostream>
using namespace std;
int parti(int a[],int i,int j) {
    int p=a[i];
    int low=i;
    while(i<j) {
        while(a[i]<=p)
            i++;
        while(a[j]>p)
            j--;
        if(i<j) {
            int temp=a[i];
            a[i]=a[j];
            a[j]=temp;
        }
    }
    a[low]=a[j];
    a[j]=p;
    return j;
}
void quick(int a[],int i,int j) {
    if(i<j) {
        int p=parti(a,i,j);
        quick(a,i,p-1);
        quick(a,p+1,j);
    }
}
int main() {
    int a[]={9,8,7,6,5,4,3,2,1};
    quick(a,0,8);
    for(int i:a) {
        cout<<i<<endl;
    }
    return 0;
}
