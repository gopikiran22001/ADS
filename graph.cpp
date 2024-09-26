#include<iostream>
#include<queue>
#include<stack>
#include<vector>
using namespace std;
void dfs(vector<vector<int>>&,int);
void dfs(vector<vector<int>> &);
void dfs_ip(vector<vector<int>> &,vector<bool> &,int);
void bfs(vector<vector<int>> &g,int sr)
{
    vector<bool> visited(g.size(),false);
    queue<int> q;
    visited[sr]=true;
    cout<<sr<<" ";
    q.push(sr);
    while(!q.empty())
    {
        int u=q.front();
        q.pop();
        for(auto i:g[u])
        {
            if(visited[i]==false)
            {
                cout<<i<<" ";
                visited[i]=true;
                q.push(i);
            }
        }
    }
    cout<<endl;
}

int main()
{
    int n;
    cout<<"Number of Vertices:";
    cin>>n;
    cout<<endl;
    vector<vector<int>> graph(n+1);
    int ne,ele;
    for(int i=1;i<=n;i++)
    {
        cout<<"Number of Neighbors for vertex "<<i<<":";
        cin>>ne;
        cout<<endl<<"Enter Neighbors for vertex "<<i<<":"<<endl;
        for(int j=0;j<ne;j++)
        {
            cin>>ele;
            graph[i].push_back(ele);
        }
    }
    cout<<endl;
    for(int i=1;i<graph.size();i++)
    {
        cout<<i<<": ";
        for(auto j:graph[i])
        {
            cout<<j<<" ";
        }
        cout<<endl;
    }
    int sr;
    cout<<"Enter Source vertex to start for BFS search:";
    cin>>sr;
    cout<<endl;
    bfs(graph,sr);
    cout<<"Enter Source vertex to start for DFS search:";
    cin>>sr;
    cout<<endl;
    dfs(graph);
    return 0;
}
void dfs(vector<vector<int>> &g)
{
    vector<bool> visited(g.size(),false);
    for(int i=1;i<visited.size();i++)
    {
        if(visited[i]==false)
        {
            visited=dfs_ip(g,visited,i);
        }
    }
}
vector<bool> dfs_ip(vector<vector<int>> &g,vector<bool> &visited,int sr)
{
    stack<int> s;
    visited[sr]=true;
    cout<<sr<<" ";
    s.push(sr);
    int i;
    while(!s.empty())
    {
        int u=s.top();
        for(i=0;i<g[u].size();i++)
        {
            if(visited[g[u][i]]==false)
            {
                cout<<g[u][i]<<" ";
                visited[g[u][i]]=true;
                s.push(g[u][i]);
                break;
            }
        }
        if(i==g[u].size())
        {
            s.pop();
        }
    }
    cout<<endl;
    return visited;
}
