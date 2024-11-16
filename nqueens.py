def place(k1,i1):
    for j in range(k1):
        if x[j]==i1 or abs(x[j]-i1)==abs(j-k1):
            return False
    return True
def nqueens(k,n):
    global x,co
    for i in range(n+1):
        if place(k,i):
            x[k]=i
            if k==n:
                co+=1
                print(x)
            else:
                nqueens(k+1,n)
        
co=0
n=int(input())
x=[0]*n
nqueens(0,n-1)
print(co)
