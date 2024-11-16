def sub(s,k,r):
    global x,w,m
    x[k]=1
    if s+w[k]==m:
        print(x[:k+1])
    elif s+w[k]+w[k+1]<=m:
        sub(s+w[k],k+1,r-w[k])
    if s+r-w[k]>=m and s+w[k+1]<=m:
        x[k]=0
        sub(s,k+1,r-w[k])
w=[5,7,10,12,15,18,20]
m=35
x=[0]*len(w)
sub(0,0,sum(w))
