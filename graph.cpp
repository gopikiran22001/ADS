#include<iostream>
#include<queue>
#include<stack>
#include<vector>
using namespace std;
class Graph{
public:
    vector<vector<int>> g;
    vector<bool> visited;
    Graph(int s):g(s+1),visited(s+1,false){}
    void bfs(int);
    void dfs();
    void dfs_ip(int);
};
void Graph::bfs(int sr)
{
    queue<int> q;
    fill(visited.begin(), visited.end(), false);
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
void Graph::dfs()
{
fill(visited.begin(), visited.end(), false);
 for(int i=1;i<visited.size();i++)
    {
        if(visited[i]==false)
        {
            dfs_ip(i);
        }
    }
}
void Graph::dfs_ip(int sr)
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
}
int main()
{
    int n;
    cout<<"Number of Vertices:";
    cin>>n;
    cout<<endl;
    Graph gr(n);
    int ne,ele;
    for(int i=1;i<=n;i++)
    {
        cout<<"Number of Neighbors for vertex "<<i<<":";
        cin>>ne;
        cout<<endl<<"Enter Neighbors for vertex "<<i<<":"<<endl;
        for(int j=0;j<ne;j++)
        {
            cin>>ele;
            gr.g[i].push_back(ele);
        }
    }
    cout<<endl;
    for(int i=1;i<gr.g.size();i++)
    {
        cout<<i<<": ";
        for(auto j:gr.g[i])
        {
            cout<<j<<" ";
        }
        cout<<endl;
    }
    int sr;
    cout<<"Enter Source vertex to start for BFS search:";
    cin>>sr;
    cout<<endl;
    gr.bfs(sr);
    cout<<"Enter Source vertex to start for DFS search:";
    cin>>sr;
    cout<<endl;
    gr.dfs();
    return 0;
}

