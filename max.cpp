#include<stdio.h>
int maxheapily(int a,int n,int i)
{
 int largest=i;
 int left=2*i+1;
 int right=2*i+2;
 if(left<n && a[left]>a[largest])
   largest=left;
elseif(right<n && a[right>a[largest]])
    largest=right;
if(largest!=i)
{
  int temp=a[i];
  a[i]=a[largest];
  a[largest]=temp;
maxheapily(a,n,largest);
}
algorithm maxheap(a,n)
{
for(i=n/2-1;i>=0;i--)
{
maxheapily(a,n,i);
}
}
int main()
{
int a[]={1,2,3,4,5};
print("array elements");
for(i=0;i>=5;i++)
{
print("array");
}
}
}
}


