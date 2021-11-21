.class public Sum

.super java/lang/Object
.method public <init>()V
aload_0
invokenonvirtual java/lang/Object/<init>()V
return
.end method

;sum function
.method public static sum(I)I
.limit stack 32
.limit locals 8
ldc 1
istore 1

;write your code
ldc 0       ;int sum = 0
istore 2

;############### Loop ###############
Loop:
iload 0
iload 2
iadd        ;sum = sum + count
istore 2

iload 0
iload 1
isub        ;count = count - 1
istore 0

iload 0
ifgt Loop   ;Check count > 0
;############### Loop ###############

iload 2
ireturn     ;load sum & return sum
.end method

.method public static main([Ljava/lang/String;)V
.limit stack 32
.limit locals 8
ldc 100     ;int count = 100
istore 0
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 0
invokestatic Sum/sum(I)I
invokevirtual java/io/PrintStream/println(I)V
return
.end method