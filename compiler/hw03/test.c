int max=500;void main(){
 int i;    int j;    int k;    int rem;    int sum;
    i = 2;
    while(i <= max){
        sum = 0;
        k = i/2;
        j = i;
 while(j<=k){            rem = i%j;            if(rem ==0){
       sum = sum + j;
         ++j;
        }
     }if(i==sum){
            write(i);
        }
        ++i;
    }return 0;
}





/*
y = 8;
c[2];
c[10]= 30;
x = x + y;

while(true){
    a = a+b;
    --a;
    while(true){
        b=13;
        b = a+b;
    }
}

if(a<b){
          a = 1;
          ++a;
          b = a+b;
      }
      else{
          b = 1;
          --b;
          a = b-a;
      }

int a;
int b=2;
int c[30];
int main(int x, int y){
    int i;
    int j = 0;
    int k[10];
    if (a==b) a=b;
    if(a<b){
        a = 1;
        ++a;
        b = a+b;
    }
    else{
        b = 1;
        --b;
        a = b-a;
    }
    return i;
}

int test1(void){
    a(a, a+1, a+2);
    b[i+1] = a+1+2+a+b+3;
    return;
}

int iread(void);

int isqrt(int a, int guess) { // tail recursive integer square root
int x;
if (guess == (x = (guess + a/guess)/2)) // guess via Newton’s method
return guess;
return isqrt(a, x); // tail recurse
}
int num;
void main(void) {
num = iread();
iprint(isqrt(num, num/2));
}

int test1(void){
}
int test2(int x){
}
int test3(int x[]){}
int main(){int x;x = 0;if(x > 5)x = x * 2;elsex = x + 1;return 0;}
*/