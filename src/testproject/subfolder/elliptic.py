from sage.all import *

# Laboratory ECC (first part)
# Implemented by: Rafael Bermudez

# Note: The point Zero or "Point at infinity" is represented
# with "O":"O" (the letter "O" in upper case) you can initialize it with P = PCurve("O","O",C)

class PCurve:

    def __init__(self,x,y,c):
        self.x = x
        self.y = y
        self.a = c.a4()
        self.b = c.a6()
        self.prime = int(c.base_ring().order()) 
        self.c = c
        
        if not self.is_zero():
            self.x = int(x)
            self.y = int(y)
            if not self.is_point_in_curve():
                raise ValueError("The point " + str(self) + " doesn't belong to the curve")        
        
        
    def __str__(self):
        return ('({}:{})'.format(self.x,self.y))


    def __add__(self,Q):
        if not self.is_the_same_curve(Q):
            raise ValueError("The points are from different curves")             
        elif self.is_zero():
            return Q
        elif Q.is_zero():
            return self    
        elif self.x == Q.x and self.y == Q.y:
            return self.double()
        elif self.x == Q.x and self.y != Q.y:
            return self.zero()
        elif self.x == Q.x and self.y == -self.y:
            return self.zero()
        else:
            s = self.calculate_s(Q)
            new_x = (s**2 - self.x - Q.x) % self.prime
            new_y = (s*(self.x - new_x) - self.y) % self.prime
            return PCurve(int(new_x), int(new_y), self.c)


    def __rmul__(self,e):
        Q = self.zero()
        e_binary = e.binary()
        
        for i in range(e.nbits()):
            Q = Q.double()
            if e_binary[i] == "1":
                Q = Q + self
            
        return Q
        
        
    def double(self):
        if self.is_zero():
            return self.zero()
        elif self.y == 0:
            return self.zero()
        else:            
            s = self.calculate_s_for_doubling()
            new_x = (s**2 - 2*self.x) % self.prime
            new_y = (s*(self.x - new_x) - self.y) % self.prime
        
            return PCurve(new_x, new_y, self.c) 
    
    
    def calculate_s_for_doubling(self):
        s_1 = ((3*(self.x**2)) + self.a) % self.prime
        s_2 = (2*(self.y)) % self.prime
        s_2_inv = self.inverse(s_2) % self.prime
        
        return (s_1 * s_2_inv) % self.prime
       
        
    def calculate_s(self, Q):
        s_1 = (self.y - Q.y) % self.prime
        s_2 = (self.x - Q.x) % self.prime
        s_2_inv = self.inverse(s_2) % self.prime

        return (s_1 * s_2_inv) % self.prime
            
    
    def inverse(self, b):
        g, x , y = self.ext_euclidean(self.prime, b)
        return y
        
        
    def ext_euclidean(self, a, b):
        if b == 0:
            return a, 1 , 0
        else:
            gcd, x1, y1 = self.ext_euclidean(b, a%b)
            x = y1
            y = x1 - (a//b) * y1
            return gcd, x, y
        
        
    def is_point_in_curve(self):
        equation_left_side = self.y**2 % self.prime
        equation_right_side = (self.x**3 + (self.a * self.x) + self.b) % self.prime        
        return equation_left_side == equation_right_side
            
        
    def is_zero(self):
        return self.x == "O" and self.y == "O"
    
    def zero(self):
        return PCurve("O", "O", self.c)
    
    def is_the_same_curve(self, Q):
        return self.prime == Q.prime and self.a == Q.a and self.b == Q.b