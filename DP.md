# DP

top - down / buttom - up



> **Compute solution from Computed information**



## Memoization Approach [Top-down Approach]

lookup table

```java
public int[] fic(int n,int[] lookup){
    if(n==0 || n==1){
        lookup[n] = 1;
    }else{
        lookup[n] = fib(n-1,lookup)+fib(n-1,lookup);
    }
   return lookup;
}
```

All of the elements are filled one by one.

## Tabulation Approach

   Tabulated Version

```java
public int[] fib(n){
    int[] f  = new int[n] f;
    f[1] = 1;
    for(int i = 2;i<n+1;i++){
        f[i] = f[i-1] + f[i-2];
    }
    return f;
}
```

