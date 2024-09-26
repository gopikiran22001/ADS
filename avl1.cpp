#include<iostream>
using namespace std;

typedef struct node{
    int data,height;
    node *left,*right;
    node(int key)
    {
        data=key;
        height=1;
        right=left=NULL;
    }
};
node* avl_insert(node*,int);
node* avl_delete(node*,int);
node* rebalance_left(node*);
node* rebalance_right(node*);
node* rotate_right(node*);
node* rotate_left(node*);
int getHeight(node*);
node* find_min(node*);
void updateHeight(node*);
void avl_search(node*,int);
void inorder(node*);
int main()
{
    node* n1;
    n1=avl_insert(n1,10);
    n1=avl_insert(n1,5);
    n1=avl_insert(n1,15);
    n1=avl_insert(n1,20);
    inorder(n1);
    cout<<endl;
    n1=avl_insert(n1,26);
    n1=avl_insert(n1,6);
    inorder(n1);
    cout<<endl;
    n1=avl_delete(n1,15);
    inorder(n1);
    cout<<endl;
    avl_search(n1,26);
    return 0;
}
node* avl_insert(node* t,int key)
{
    if(t==NULL)
    {
        return new node(key);
    }
    else if(key<t->data)
    {
        t->left=avl_insert(t->left,key);
        t=rebalance_left(t);
    }
    else if(key>t->data)
    {
        t->right=avl_insert(t->right,key);
        t=rebalance_right(t);
    }
    updateHeight(t);
    return t;
}
node* avl_delete(node *t,int key)
{
    if(t==NULL)
    {
        return t;
    }
    else if(key<t->data)
    {
        t->left=avl_delete(t->left,key);
        t=rebalance_left(t);
    }
    else if(key>t->data)
    {
        t->right=avl_delete(t->right,key);
        t=rebalance_right(t);
    }
    else{
        if(t->left==NULL && t->right==NULL)
        {
            t=NULL;
        }
        else if(t->left==NULL || t->right==NULL)
        {
            if(t->left==NULL)
            {
                *t=*t->right;
            }
            else{
                *t=*t->left;
            }
        }
        else
        {
            node* successor=find_min(t->right);
            t->data=successor->data;
            t->right=avl_delete(t->right,successor->data);
            t=rebalance_right(t);
        }
        }
    return t;
}
node* rotate_left(node *t)
{
    node* newNode=t->right;
    t->right=t->right->left;
    newNode->left=t;
    updateHeight(t);
    updateHeight(newNode);
    return newNode;
}
node* rotate_right(node *t)
{
    node* newNode=t->left;
    t->left=t->left->right;
    newNode->right=t;
    updateHeight(t);
    updateHeight(newNode);
    return newNode;
}
node* rebalance_left(node* t)
{
    if(getHeight(t->right)-getHeight(t->left)==-2)
    {
        if(getHeight(t->left->right)>getHeight(t->left->left))
            {
                t->left=rotate_left(t->left);
                t=rotate_right(t);
            }
            else
            {
                t=rotate_right(t);
            }
    }
    updateHeight(t);
    return t;
}
node* rebalance_right(node* t)
{
    if(getHeight(t->right)-getHeight(t->left)==2)
    {
        if(getHeight(t->right->left)>getHeight(t->right->right))
            {
                t->right=rotate_right(t->right);
                t=rotate_left(t);
            }
            else
            {
                t=rotate_left(t);
            }
    }
    updateHeight(t);
    return t;
}
void inorder(node* t)
{
    if(t==NULL)
        return;
    inorder(t->left);
    cout<<"("<<t->height<<")"<<t->data<<" ";
    inorder(t->right);
}
int getHeight(node* t)
{
    return t==NULL?0:t->height;
}
void updateHeight(node* t)
{
    int hr=getHeight(t->right);
    int hl=getHeight(t->left);
    t->height=(hr>hl?hr:hl)+1;
}
node* find_min(node* t)
{
    while(t->left!=NULL)
    {
        t=t->left;
    }
    return t;
}
void avl_search(node *t,int ele)
{
    if(t==NULL)
        cout<<"Can not find the element\n";
    else if(ele==t->data)
    {
        cout<<"Element found\n";
        cout<<t->data<<endl;
        cout<<t->height<<endl;
    }
    else if(ele<t->data)
    {
        avl_search(t->left,ele);
    }
    else if(ele>t->data)
    {
        avl_search(t->right,ele);
    }
}


