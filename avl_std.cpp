#include<iostream>
#include <cstring>
using namespace std;
typedef struct node {
    int data,height;
    char name[50],branch[10];
    float cgpa;
    struct node *left,*right;
    node(int ele,const char* na,const char* br,float cg){
        left=right=NULL;
        data=ele;
        strcpy(name, na);
        strcpy(branch, br);
        cgpa=cg;
        height=1;
    }
}node;
node* avl_insert(node*,int,const char[],const char[],float);
node* avl_delete(node*,int);
node* rotate_left(node*);
node* rotate_right(node*);
node* rebalance_left(node*);
node* rebalance_right(node*);
int avl_search(node*,int);
int getHeight(node*);
int max(int ,int);
void inorder(node*);
node* find_minimum(node*);
void updateHeight(node*);
int main(){
    node *root=NULL;
    root=avl_insert(root,1,"Pranavi","CSE",89.8);
    root=avl_insert(root,2,"Siva","CSE",98.6);
    root=avl_insert(root,3,"Pavan","CSE",85.2);
    root=avl_insert(root,4,"Nayak","CSE",65.2);
    root=avl_insert(root,5,"Aryan","CSE",69.9);
    root=avl_insert(root,6,"Apparao","CSE",98.9);
    inorder(root);
    cout<<endl;
    root=avl_delete(root,4);
    inorder(root);
    cout<<endl;
    if(avl_search(root,10)!=-1)
        cout<<"element is found";
    else
        cout<<"element is not found";
}
node* avl_insert(node* t,int ele,const char name[],const char branch[],float cgpa){
    if(t==NULL)
        return new node(ele,name,branch,cgpa);
    else if(ele<t->data)
    {
        t->left=avl_insert(t->left,ele,name,branch,cgpa);
        t=rebalance_left(t);
    }
    else if(ele>t->data)
    {
        t->right=avl_insert(t->right,ele,name,branch,cgpa);
        t=rebalance_right(t);
    }
    return t;
}
node* find_minimum(node *t){
    if(t->left!=NULL)
        t=t->left;
    return t;
}
node* avl_delete(node* t,int ele)
{
    if(t==NULL)
        return t;
    else if(ele<t->data)
    {
        t->left=avl_delete(t->left,ele);
        t=rebalance_left(t);
    }
    else if(ele>t->data)
    { 
        t->right=avl_delete(t->right,ele);
        t=rebalance_right(t);
    }
    else
    {
        if(t->left==NULL && t->right==NULL)
            t=NULL;
        else if(t->left==NULL || t->right==NULL )
        {
           if(t->left==NULL)
                *t=*(t->right);
            else
                *t=*(t->left);
        }
        else
        {
            node* successor=find_minimum(t->right);
            t->data=successor->data;
            t->right=avl_delete(t->right,successor->data);
            t=rebalance_right(t);
        }
    }
    return t;
}
node* rotate_left(node* t){
    node* newRoot = t->right;
    t->right = t->right->left;
    newRoot->left=t;
    updateHeight(t);
    updateHeight(newRoot);
    return newRoot;
}
node* rotate_right(node* t){
    node* newRoot = t->left;
    t->left = t->left->right;
    newRoot->right=t;
    updateHeight(t);
    updateHeight(newRoot);
    return newRoot;
}
node* rebalance_right(node* t)
{
    if( getHeight(t->right) - getHeight(t->left) == 2){
        if(getHeight(t->right->left) > getHeight(t->right->right))
        {
            t->right=rotate_right(t->right);
            t=rotate_left(t);
        }
        else
            t=rotate_left(t);
    }
    else
        updateHeight(t);
    return t;
}
node* rebalance_left(node* t){
    if(getHeight(t->right) - getHeight(t->left) == -2)
    {
        if(getHeight(t->left->right) > getHeight(t->left->left))
        {
            t->left=rotate_left(t->left);
            t=rotate_right(t);
        }
        else
            t=rotate_right(t);
    }
    else
        updateHeight(t);
    return t;
}
int getHeight(node *t){
    return t == NULL ? 0 : t->height;
}
void updateHeight(node* t){
    int tl=getHeight(t->left);
    int th=getHeight(t->right);
    t->height = tl > th ? tl+1 : th+1;
}
int max(int x,int y){
    return x>y?x:y;
}
void inorder(node* t){
    if(t==NULL)
        return;
    inorder(t->left);
    cout<<"Roll Number:"<<t->data<<endl;
    cout<<"Name:"<<t->name<<endl;
    cout<<"Branch:"<<t->branch<<endl;
    cout<<"CGPA:"<<t->cgpa<<endl;
    cout<<endl;
    inorder(t->right);
}
int avl_search(node* t,int key){
    if(t==NULL)
        return -1;
    else if(key == t->data)
        return 1;
    else if(key < t->data)
        return avl_search(t->left,key);
    else
        return avl_search(t->right,key);
}