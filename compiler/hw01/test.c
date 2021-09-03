#include <stdio.h>
#include <stdlib.h>

int main(){
printf("Hello world!");
system("ls -al");
printf("delete test.txt");
system("ls -al > res1");
printf("*********test.txt********");
system("cat test1.txt");
system("echo Hello World > res2");
system("ls -al > res3");
system("cat test1.txt > res4");
system("rm test2.txt");
system("rm test3.txt > res5");
return 0;
}