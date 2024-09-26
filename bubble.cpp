#include<iostream>
#include<vector>
#include<cmath>
using namespace std;
vector<int> v(100);
void merge_imp(int,int,int);
void merge_sort(int,int);
void merge_sort(int i,int j)
{
    if(i<j){
        int mid=floor((i+j)/2);
        merge_sort(i,mid);
        merge_sort(mid+1,j);
        merge_imp(i,j,mid);
    }
}
void merge_imp(int i,int j,int mid)
{
    int n1=mid-i-1;
    int n2=j-mid;
    vector<int> A(n1+1);
    vector<int> B(n2+1);
    for(int k=0;i<n1;i++)
    {
        A[k]=v[k+i];
    }
    for(int k=0;k<n2;k++)
    {
        B[k]=v[mid+k];
    }
    A[n1]=2147483647;
    B[n2]=2147483647;
    int p=0,q=0;
    for(int k=i;k<j;k++)
    {
        if(B[p]>A[p])
        {
            v[k]=A[p];
            p++;
        }
        else{
            v[k]=B[q];
            q++;
        }
    }

}
int main()
{
    int q=0;
    for(int i=10;i>0;i++)
    {
        v[q]=i;
        q++;
    }
    merge_sort(0,9);
    for(int i=0;i<10;i++)
    {
        cout<<v[i]<<endl;
    }
}
