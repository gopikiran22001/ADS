#include <stdio.h>
void maxHeapily(int a[],int n,int i)
{
    int largest,left,right;
  largest=i;
 left=2*i+1;
   right=2*i+2;
 if(left<n && a[left]>a[largest])
   largest=left;
if(right<n && a[right>a[largest]])
    largest=right;
if(largest!=i)
{
  int temp=a[i];
  a[i]=a[largest];
  a[largest]=temp;
maxHeapily(a,n,largest);
}
}
void maxHeap(int a[],int n)
{
for( int i=n/2-1;i>=0;i--)
{
maxHeapily(a,n,i);
}
}
int  main()
{
int a[10],n,i;
printf(" elements n");
scanf("%d",&n);
printf("enter the elements");
for(i=0;i<n;i++)
{
    scanf("%d",&a[i]);
}
    maxHeap(a,n);
    printf("maxheap array\n");
    for(i=0;i<n;i++)
{
    printf("%d",a[i]);
}
    return 0;
}
