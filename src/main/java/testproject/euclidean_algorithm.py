#!/usr/bin/env python
# coding: utf-8

# In[15]:


# Euclidean Algorithm
# Simple
def Euclidean(a,b):
    if b == 0:
        return a
    else:
        return Euclidean(b, a%b)
    
print "gcd(43,64) = ", Euclidean(43,64)
print "gcd(15,26) = ", Euclidean(15,26)
print "gcd(50,71) = ", Euclidean(50,71)


# Extended gcd(a,b) = ax + by

def ExtEuclidean(a,b):
    if b == 0:
        return a, 1 , 0
    else:
        gcd, x1, y1 = ExtEuclidean(b, a%b)
        x = y1
        y = x1 - (a//b) * y1
        return gcd, x, y

a,b = 43, 64
g, x , y = ExtEuclidean(a, b)
print "gcd(",a, ",", b, ")=", g, "=", a, "*", x, "+", b, "*", y

a,b = 15, 26
g, x , y = ExtEuclidean(a, b)
print "gcd(",a, ",", b, ")=", g, "=", a, "*", x, "+", b, "*", y

a,b = 50, 71
g, x , y = ExtEuclidean(a, b)
print "gcd(",a, ",", b, ")=", g, "=", a, "*", x, "+", b, "*", y


# In[104]:


# Euclidean Algorithm
# Simple
import time;


def Euclidean(a,b,timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, duration, iterations,  
    else:
        return Euclidean(b, a%b, timestamp, iterations)

print "[gcd(43,64) , duration, iterations] = ", Euclidean(43,64)
print "[gcd(15,26) , duration, iterations] = ", Euclidean(15,26)
print "[gcd(50,71) , duration, iterations] = ", Euclidean(50,71)


# Extended gcd(a,b) = ax + by

def ExtEuclidean(a,b, timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp
        dt = np.dtype([('top', [('tiles', ('>f4′, (64, 64)), (1,)), ('rtile', '>f4′, (64, 36))], (3,)), ('bottom', [('bleft', ('>f4′, (8, 64)), (1,)), ('bright', '>f4′, (8, 36))])])
        return a, 1 , 0, duration, iterations
    else:
        gcd, x1, y1 , duration , iterations = ExtEuclidean(b, a%b, timestamp, iterations)
        x = y1
        y = x1 - (a//b) * y1
        return gcd, x, y, duration, iterations

    
    
a,b = 43, 64
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 15, 26
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"

a,b = 50, 71
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"


# In[112]:


# Euclidean Algorithm
# Simple
import time;


def Euclidean(a,b,timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        dt = np.dtype([('top', [('tiles', ('>f4′, (64, 64)), (1,)), ('rtile', '>f4′, (64, 36))], (3,)),
        ('bottom', [('bleft', ('>f4′, (8, 64)), (1,)), ('bright', '>f4′, (8, 36))])])
        return a, duration, iterations,  
    else:
        return Euclidean(b, a%b, timestamp, iterations)

print "[gcd(43,64) , duration, iterations] = ", Euclidean(43,64)
print "[gcd(15,26) , duration, iterations] = ", Euclidean(15,26)
print "[gcd(50,71) , duration, iterations] = ", Euclidean(50,71)
print "[gcd(42823,6409) , duration, iterations] = ", Euclidean(42823,6409)
print "[gcd(1109,4999) , duration, iterations] = ", Euclidean(1109,4999)

print ""

# Extended gcd(a,b) = ax + by

def ExtEuclidean(a,b, timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, 1 , 0, duration, iterations
    else:
        gcd, x1, y1 , duration , iterations = ExtEuclidean(b, a%b, timestamp, iterations)
        x = y1
        y = x1 - (a//b) * y1
        return gcd, x, y, duration, iterations

    
    
a,b = 43, 64
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 15, 26
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"

a,b = 50, 71
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 42823,6409
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 1109,4999
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"


# In[118]:


# Euclidean Algorithm
# Simple
import time;


def Euclidean(a,b,timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, duration, iterations,  
    else:
        return Euclidean(b, a%b, timestamp, iterations)

# GCD Calculation for each pair (m,n) with m from 5 to 7 and n from 1 to 20

for i in range (5,8):
    for j in range (1,21):
        print "[gcd(",i,",",j,") , duration, iterations] = ", Euclidean(i,j)


# In[119]:


# Extended gcd(a,b) = ax + by

def ExtEuclidean(a,b, timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, 1 , 0, duration, iterations
    else:
        gcd, x1, y1 , duration , iterations = ExtEuclidean(b, a%b, timestamp, iterations)
        x = y1
        y = x1 - (a//b) * y1
        return gcd, x, y, duration, iterations

# GCD Calculation for each pair (m,n) with m from 5 to 7 and n from 1 to 20

for i in range (5,8):
    for j in range (1,21):
        g, x , y , duration, iterations  = ExtEuclidean(i, j)
        print "[gcd(",i, ",", j, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"


# In[135]:


# Euclidean Algorithm
# Simple
import time;


def Euclidean(a,b,timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, duration, iterations,  
    else:
        return Euclidean(b, a%b, timestamp, iterations)

print "[gcd(13,21) , duration, iterations] = ", Euclidean(13,21)
print "[gcd(13,34) , duration, iterations] = ", Euclidean(13,34)
print "[gcd(13,55) , duration, iterations] = ", Euclidean(13,55)
print "[gcd(13,89) , duration, iterations] = ", Euclidean(13,89)

print ""

print "[gcd(8,21) , duration, iterations] = ", Euclidean(8,21)
print "[gcd(8,34) , duration, iterations] = ", Euclidean(8,34)
print "[gcd(8,55) , duration, iterations] = ", Euclidean(8,55)
print "[gcd(8,89) , duration, iterations] = ", Euclidean(8,89)
print "[gcd(8,144) , duration, iterations] = ", Euclidean(8,144)
print "[gcd(8,233) , duration, iterations] = ", Euclidean(8,233)
print "[gcd(8,1597) , duration, iterations] = ", Euclidean(8,1597)

print ""

print "[gcd(3,21) , duration, iterations] = ", Euclidean(3,21)
print "[gcd(3,34) , duration, iterations] = ", Euclidean(3,34)
print "[gcd(3,55) , duration, iterations] = ", Euclidean(3,55)
print "[gcd(3,89) , duration, iterations] = ", Euclidean(3,89)
print "[gcd(3,144) , duration, iterations] = ", Euclidean(3,144)
print "[gcd(3,233) , duration, iterations] = ", Euclidean(3,233)
print "[gcd(3,1597) , duration, iterations] = ", Euclidean(3,1597)

print ""

print "[gcd(5,21) , duration, iterations] = ", Euclidean(5,21)
print "[gcd(5,34) , duration, iterations] = ", Euclidean(5,34)
print "[gcd(5,55) , duration, iterations] = ", Euclidean(5,55)
print "[gcd(5,89) , duration, iterations] = ", Euclidean(5,89)
print "[gcd(5,144) , duration, iterations] = ", Euclidean(5,144)
print "[gcd(5,610) , duration, iterations] = ", Euclidean(5,610)
print "[gcd(5,1597) , duration, iterations] = ", Euclidean(5,1597)


# Extended gcd(a,b) = ax + by

def ExtEuclidean(a,b, timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, 1 , 0, duration, iterations
    else:
        gcd, x1, y1 , duration , iterations = ExtEuclidean(b, a%b, timestamp, iterations)
        x = y1
        y = x1 - (a//b) * y1
        return gcd, x, y, duration, iterations

print ""    
    
a,b = 13, 21
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 13, 34
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"

a,b = 13, 55
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 13,89
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

print ""

a,b = 8, 21
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 8, 34
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"

a,b = 8, 55
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 8,89
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 8, 144
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 8,233
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 8,1597
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

print ""

a,b = 3, 21
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 3, 34
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"

a,b = 3, 55
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 3,89
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 3, 144
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 3,233
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 3,1597
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

print ""

a,b = 5, 21
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 5, 34
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"

a,b = 5, 55
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 5,89
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 5, 144
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 5,610
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 5,1597
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"


# In[139]:


# Euclidean Algorithm
# Simple
import time;


def Euclidean(a,b,timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, duration, iterations,  
    else:
        return Euclidean(b, a%b, timestamp, iterations)

print "[gcd(433494437,2971215073) , duration, iterations] = ", Euclidean(433494437,2971215073)
print "[gcd(701408733,2971215073) , duration, iterations] = ", Euclidean(701408733,2971215073)

print ""

# Consecutive

print "[gcd(433494437,701408733) , duration, iterations] = ", Euclidean(433494437,701408733)
print "[gcd(701408733,1134903170) , duration, iterations] = ", Euclidean(701408733,1134903170)
print "[gcd(1134903170,1836311903) , duration, iterations] = ", Euclidean(1134903170,1836311903)
print "[gcd(1836311903,2971215073) , duration, iterations] = ", Euclidean(1836311903,2971215073)

print ""


# Extended gcd(a,b) = ax + by

def ExtEuclidean(a,b, timestamp=time.time(), iterations=0):
    iterations = iterations + 1
    if b == 0:
        duration = time.time() - timestamp;
        return a, 1 , 0, duration, iterations
    else:
        gcd, x1, y1 , duration , iterations = ExtEuclidean(b, a%b, timestamp, iterations)
        x = y1
        y = x1 - (a//b) * y1
        return gcd, x, y, duration, iterations

print ""    


# Consecutive

print ""

a,b = 433494437,2971215073
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 701408733,2971215073
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"


a,b = 433494437,701408733
g, x , y , duration, iterations = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y ,",", duration, ",",iterations, "]"

a,b = 701408733,1134903170
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[" , g, ",", a, "*", x, "+", b, "*", y,",", duration, ",",iterations, "]"

a,b = 1134903170,1836311903
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

a,b = 1836311903,2971215073
g, x , y , duration, iterations  = ExtEuclidean(a, b)
print "[gcd(",a, ",", b, "), ax + by, duration, iterations] =", "[", g, ",", a, "*", x, "+", b, "*", y, ",",duration, ",",iterations, "]"

print ""


# In[ ]:




