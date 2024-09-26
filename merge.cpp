#include<iostream>
#include<vector>
#include<cmath>
using namespace std;
void merge_imp(int a[],int i,int j,int mid)
{
    int n1=mid-i+1;
    int n2=j-mid;
    vector<int> A(n1);
    vector<int> B(n2);
    for(int k=0;k<n1;k++)
    {
        A[k]=a[k+i];
    }
    for(int k=0;k<n2;k++)
    {
        B[k]=a[mid+k+1];
    }
    A.push_back(2147483647);
    B.push_back(2147483647);
    int p=0,q=0;
    for(int k=i;k<=j;k++)
    {
        if(B[q]>A[p])
        {
            a[k]=A[p];
            p++;
        }
        else{
            a[k]=B[q];
            q++;
        }
    }

}

void merge_sort(int a[],int i,int j)
{
    if(i<j){
        int mid=floor((i+j)/2);
        merge_sort(a,i,mid);
        merge_sort(a,mid+1,j);
        merge_imp(a,i,j,mid);
    }
}
int main()
{
    int a[]={6,5,4,3,2,1};
    merge_sort(a,0,5);
    for(auto i:a)
    {
     cout<<i<<endl;
    }
}
