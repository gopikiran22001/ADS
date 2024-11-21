#include<iostream>
#include <iostream>
using namespace std;
// B-tree node structure
class BTreeNode {
public:
    bool leaf;
    int n;
    int *keys;
    BTreeNode **children;
    int t;
    // constructor to create a B-Tree node with 2*t-1 keys and
    // 2*t childern
    BTreeNode(int t,bool leaf){
        this->leaf=leaf;
        n=0;
        this->t=t;
        keys=new int[(2*t)-1];
        children = new BTreeNode*[2*t]; 
    }
};

// B-tree class
class BTree {
public:
    BTreeNode* root;
    int t; // Minimum degree

public:
    BTree(int t1) {
        root=NULL;
        t=t1;
    }
    void insert(int key); // insert an element
    void remove(int key); // delete an element
    void traverse(); // inorder traversal of B-Tree
    BTreeNode* search(int key);
    // auxillary functions/ helper functions
public:
    void insertNonFull(BTreeNode* &x, int key);
    void splitChild(BTreeNode* x, int i);
    void traverse(BTreeNode* node);
    BTreeNode* search(BTreeNode *x,int key);
    void removeFromLeaf(BTreeNode* x, int idx);
    void removeFromNonLeaf(BTreeNode* x, int idx);
    int findKey(BTreeNode* x, int key);
    void borrowFromPrev(BTreeNode* x, int idx);
    void borrowFromNext(BTreeNode* x, int idx);
    void merge(BTreeNode* x, int idx);
    void fill(BTreeNode* x, int idx);
};
void BTree::splitChild(BTreeNode *x,int i){
    //create a new B-Tree node
    BTreeNode *z=new BTreeNode(t,true);
    // take the childern of x to split
    BTreeNode* y = x->children[i];
    z->leaf=y->leaf;
    z->n=t-1;
    // split y and copy the keys from t onwards to z
    for(int j=0;j<t-1;j++)
        z->keys[j]= y->keys[j+t];
    // if y is not leaf copy the childern of y to z
    if (!y->leaf){
        for(int j=0;j<t;j++)
            z->children[j]=y->children[j+t];
    }
    y->n=t-1;
    //after spliting y to y and z adjust the childern of x 
    for(int j=x->n ; j>i+1 ; j--)
        x->children[j+1]=x->children[j];
    x->children[i+1]=z;
    // make a room to insert a key in x
    for(int j=(x->n)-1; j>i; j--)
        x->keys[j+1]=x->keys[j];
    // copy the key from y to x
    x->keys[i]=y->keys[t-1];
    // increase the number of node of x
    x->n=x->n+1;
}
void BTree::insertNonFull(BTreeNode* &x, int key){
    int i=x->n-1;
    if(x->leaf){
        while (i>=0 && key < x->keys[i] ){
            x->keys[i+1]=x->keys[i];
            i=i-1;
        }
        x->keys[i+1]=key;
        x->n=x->n+1;
    }
    else{
        while (i >=0 && x->keys[i]>key)
            i--;
        i++;
        if((x->children[i]->n) == (2*t-1)){
            splitChild(x,i);
            if(x->keys[i]<key)
                i++;
        }
        insertNonFull(x->children[i],key);
    }
}
void BTree::insert(int k){
    
    if(root==NULL){
        root = new BTreeNode(t,true);
        root->keys[0]=k;
        root->n=root->n+1;
    }
    else{
        BTreeNode *r=root;
        if((r->n) == 2*t-1){
            BTreeNode *s=new BTreeNode(t,false);
            root=s;
            s->n=0;       
            s->children[0]=r;
            splitChild(s,0);
            insertNonFull(s,k);
        }
        else{
            insertNonFull(r,k);
        }
    }
}
 void BTree:: traverse(){
    traverse(root);
    cout<<endl;
 }
 void BTree::traverse(BTreeNode *x){
    int i;
    for (i = 0; i < x->n; i++)
    {
        if (x->leaf == false)
            traverse(x->children[i]);
        cout << " " << x->keys[i];
    }
    if (x->leaf == false)
        traverse(x->children[i]);
}
BTreeNode *BTree::search(int k){
    return search(root,k);
}
// Function to search key k in subtree rooted with this node

BTreeNode *BTree::search(BTreeNode *x, int k)
{
    // Find the first key greater than or equal to k
    int i = 0;
    while (i < x->n && k > x->keys[i])
        i++;

    // If the found key is equal to k, return this node
    if (i<x->n && x->keys[i] == k)
        return x;

    // If key is not found here and this is a leaf node
    if (x->leaf == true)
        return NULL;

    // Go to the appropriate child
    return search(x->children[i],k);
}
int main(){
    BTree t(2); // A B-Tree with minimum degree 2
    t.insert(10);
    t.insert(20);
    t.insert(5);
    t.insert(6);
    t.insert(12);
    cout<<"B-Tree elements are ..."<<endl;
    t.traverse();
    t.insert(30);
    cout<<"After inserting element 30"<<endl;
    t.traverse();
    t.insert(7);
    cout<<"After inserting element 7"<<endl;
    t.traverse();
    t.insert(17);
    cout<<"After inserting element 17"<<endl;
    t.traverse();
    t.insert(16);
    cout<<"After inserting element 16"<<endl;
    t.traverse();
    int ele;
    cout<<"enter an element to search"<<endl;
    cin>>ele;
    if(t.search(ele)!=NULL){
        cout<<"element is found"<<endl;
    }
    else
        cout<<"element is not found"<<endl;
    return 0;
}