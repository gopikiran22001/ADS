#include<iostream>
#include<cmath>
using namespace std;
class Priority{
    public:
    int size_heap;
    int A[100];
    Priority()
    {
        size_heap=0;
    }
    void maxHeapfy(int);
    void buildMaxHeap();
    void heap_insert(int);
    int extract_max();
    void increase_key(int,int);
    int maximum();
    void heap_swap(int*,int*);
    void display();
};
void Priority::heap_swap(int* x,int* y)
{
    int t=*x;
    *x=*y;
    *y=t;
}
void Priority::maxHeapfy(int i)
{
    int largest=i;
    int l=2*i+1;
    int r=2*i+2;
    if(l<=size_heap && A[l]>A[largest])
    {
        largest=l;
    }
    if(r<=size_heap && A[r]>A[largest])
    {
        largest=r;
    }
    if(i!=largest)
    {
        heap_swap(&A[largest],&A[i]);
        maxHeapfy(largest);
    }
}
void Priority::buildMaxHeap()
{
    for(int i=floor(size_heap/2)-1;i>0;i--)
    {
        maxHeapfy(i);
    }
}
void Priority::heap_insert(int key)
{
    size_heap+=1;
    int i=size_heap;
    A[i]=key;
    int parent=floor(i/2)-1;
    while(i>0 && A[parent]<A[i])
    {
        heap_swap(&A[parent],&A[i]);
        i=parent;
        parent=floor(i/2)-1;
    }
}
void Priority::increase_key(int i,int key)
{
    if(key<A[i])
    {
        cout<<"New Key must be larger than current key.."<<endl;
        return;
    }
    A[i]=key;
    int parent=floor(i/2)-1;
    while(i>0 && A[parent]<A[i])
    {
        heap_swap(&A[parent],&A[i]);
        i=parent;
        parent=floor(i/2)-1;
    }
}
int Priority::extract_max()
{
    int max_key=A[1];
    heap_swap(&A[1],&A[size_heap]);
    size_heap-=1;
    maxHeapfy(1);
    return max_key;
}
int Priority::maximum()
{
    return A[1];
}
void Priority::display()
{
    for(int i=1;i<=size_heap;i++)
    {
        cout<<A[i]<<" ";
    }
    cout<<endl;
}
int main()
{
    Priority pq;
    pq.heap_insert(10);
    pq.heap_insert(12);
    pq.heap_insert(8);
    pq.heap_insert(15);
    pq.heap_insert(5);
    pq.display();
    cout<<"Maxium Key: "<<pq.maximum()<<endl;
    pq.increase_key(3,50);
    pq.display();
    cout<<pq.extract_max()<<endl;
    pq.display();
    return 0;
}
