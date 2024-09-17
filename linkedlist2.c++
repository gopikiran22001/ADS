#include<iostream>
using namespace std;
class node {
    public:
    int data;
    node *next;
    node(int ele){
        data=ele;next=NULL;
        cout<<"node created\n";
    }
 };
class linkedlist{
    public:
    node *head,*tail;
    linkedlist():head(NULL),tail(NULL){}
    void insert_at_beg(int ele){
        node* newNode = new node(ele);
        newNode->next=head;
        head=newNode;
    }
    void insert_at_end(int ele){
        node* newNode = new node(ele);
      /*  tail->next=newNode;
        tail=newNode; */
        node *temp=head;
        while(temp->next!=NULL)
            temp=temp->next;
        temp->next=newNode;
    }
    void insert(int ele,int pos){
        node *newNode=new node(ele);
        int i=1;
        if(head==NULL){
            head=tail=newNode;
        }
        else {
            node *temp=head;
            while(i<pos-1){
                temp=temp->next;
                i=i+1;
            }
            newNode->next=temp->next;
            temp->next=newNode;
        }
    }
    void display(){
        node *temp=head;
        while(temp!=NULL){
            cout<<temp->data<<" ";
            temp=temp->next; 
        }
        cout<<"\n";
    }
};
int main(){
  linkedlist l1;
  l1.insert_at_beg(10);
  l1.insert_at_beg(20);
  l1.insert_at_beg(30);
  l1.display();
  l1.insert(15,2);
 // l1.display();
  l1.insert_at_end(45);
//  l1.display();
  linkedlist l2;
  l2.insert_at_beg(60);
  l2.insert_at_beg(70);
  l2.insert_at_beg(80);
  l2.display();
}