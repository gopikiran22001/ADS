#include <iostream>
using namespace std;

typedef struct node {
    int data, height;
    struct node *left, *right;
    node(int ele) {
        data = ele;
        height = 1;
        left = right = NULL;
    }
} node;

int getHeight(node* t) {
    if (t == NULL)
        return 0;
    return t->height;
}

int max(int x, int y) {
    return x > y ? x : y;
}

void updateHeight(node* t) {
    if (t != NULL) {
        int hr = getHeight(t->right);
        int hl = getHeight(t->left);
        t->height = max(hr, hl) + 1;
    }
}

node* rotate_left(node* t) {
    node* temp = t->right;
    t->right = temp->left;
    temp->left = t;
    updateHeight(t);
    updateHeight(temp);
    return temp;
}

node* rotate_right(node* t) {
    node* temp = t->left;
    t->left = temp->right;
    temp->right = t;
    updateHeight(t);
    updateHeight(temp);
    return temp;
}

node* rebalance_left(node* root) {
    if (getHeight(root->right) - getHeight(root->left) == -2) {
        if (getHeight(root->left->right) > getHeight(root->left->left)) {
            root->left = rotate_left(root->left);
        }
        root = rotate_right(root);
    } else {
        updateHeight(root);
    }
    return root;
}

node* rebalance_right(node* root) {
    if (getHeight(root->right) - getHeight(root->left) == 2) {
        if (getHeight(root->right->left) > getHeight(root->right->right)) {
            root->right = rotate_right(root->right);
        }
        root = rotate_left(root);
    } else {
        updateHeight(root);
    }
    return root;
}

node* avl_insert(node* root, int key) {
    if (root == NULL)
        return new node(key);
    if (key < root->data) {
        root->left = avl_insert(root->left, key);
        root = rebalance_left(root);
    } else {
        root->right = avl_insert(root->right, key);
        root = rebalance_right(root);
    }
    return root;
}

node* findMin(node* t) {
    while (t->left != NULL) {
        t = t->left;
    }
    return t;
}

node* avl_delete(node* t, int ele) {
    if (t == NULL) {
        return t;
    } else if (ele < t->data) {
        t->left = avl_delete(t->left, ele);
        t = rebalance_right(t); // Correction: rebalance the right side here
    } else if (ele > t->data) {
        t->right = avl_delete(t->right, ele);
        t = rebalance_left(t); // Correction: rebalance the left side here
    } else {
        if (t->left == NULL || t->right == NULL) {
            node* temp = t->left ? t->left : t->right;
            if (temp == NULL) { // No child case
                temp = t;
                t = NULL;
            } else { // One child case
                *t = *temp; // Copy the contents of the non-empty child
            }
            delete temp;
        } else {
            node* successor = findMin(t->right);
            t->data = successor->data;
            t->right = avl_delete(t->right, successor->data);
            t = rebalance_left(t); // Rebalance the left side here
        }
    }
    if (t != NULL)
        updateHeight(t); // Update height after all operations
    return t;
}

void inorder(node* root) {
    if (root == NULL)
        return;
    inorder(root->left);
    cout << "(" << root->height << ")" << root->data << " ";
    inorder(root->right);
}

int main() {
    node* root = NULL;
    root = avl_insert(root, 10);
    root = avl_insert(root, 20);
    root = avl_insert(root, 30);
    root = avl_insert(root, 40);
    root = avl_insert(root, 50);
    root = avl_insert(root, 25);
    inorder(root);
    cout << endl;
    root = avl_delete(root, 30);
    inorder(root);
    return 0;
}
