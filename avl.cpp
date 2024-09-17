#include<iostream>
using namespace std;
typedef struct node {
    int data,height;
    char name[50]
    struct node *left,*right;
    node(int ele){
        left=right=NULL;
        data=ele;
        height=1;
    }
}node;
node* avl_insert(node*,int);
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
    root=avl_insert(root,10);
    root=avl_insert(root,20);
    root=avl_insert(root,5);
    root=avl_insert(root,9);
    root=avl_insert(root,25);
    inorder(root);
    cout<<endl;
    root=avl_delete(root,9);
    inorder(root);
    cout<<endl;
    root=avl_insert(root,19);
    inorder(root);
    cout<<endl;
    root=avl_delete(root,10);
    inorder(root);
    cout<<endl;
    if(avl_search(root,10)!=-1)
        cout<<"element is found";
    else
        cout<<"element is not found";
}
node* avl_insert(node* t,int ele){
    if(t==NULL)
        return new node(ele);
    else if(ele<t->data)
    {
        t->left=avl_insert(t->left,ele);
        t=rebalance_left(t);
    }
    else if(ele>t->data)
    {
        t->right=avl_insert(t->right,ele);
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
        //node to be deleted is leaf node
        if(t->left==NULL && t->right==NULL)
            t=NULL;
        else if(t->left==NULL || t->right==NULL ) // node to be deleted have one child
        {
           if(t->left==NULL)
                *t=*(t->right);
            else
                *t=*(t->left);
        }
        else // node to be deleted have both left and right child
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
    cout<<t->data<<"( "<<t->height<<")"<<" ";
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