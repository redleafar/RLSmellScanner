from sage.all import *

# Laboratory ECC (second part)
# Implemented by: Rafael Bermudez

# Note: The method to_z1() was implemented in order to convert to coordinates with Z=1 and easily compare
# against the Sagemath implementation 


class ProjectivePCurve:
    def __init__(self,x,y,z,c):
        self.x = x
        self.y = y
        self.z = z
        self.a = int(c.a_invariants()[3])
        self.b = c.a6()
        self.c = c
        self.prime = int(c.base_ring().order())
        
        self.x = int(x)
        self.y = int(y)
        self.z = int(z)
        
        if not self.is_zero():            
            if self.z == 0:
                print self.x
                print self.y
                print self.z
                raise ValueError("Z can't be zero")         
            elif not self.is_point_in_curve():
                raise ValueError("The point " + str(self) + " doesn't belong to the curve")        

    def __str__(self):
        return ('({}:{}:{})'.format(self.x,self.y,self.z))


    def __add__(self,Q):
        T_0 = (self.y * Q.z) % self.prime
        T_1 = (Q.y * self.z) % self.prime
        U_0 = (self.x * Q.z) % self.prime
        U_1 = (Q.x * self.z) % self.prime
        
        U = (U_0 - U_1) % self.prime
        T = (T_0 - T_1) % self.prime
        U_2 = U**2 % self.prime
        U_3 = (U * U_2) % self.prime
        V = (self.z * Q.z) % self.prime
        W = ((T**2 * V) - (U_2 * (U_0 + U_1))) % self.prime
        
        if not self.is_the_same_curve(Q):
            raise ValueError("The points are from different curves")             
        elif self.is_zero():
            return Q
        elif Q.is_zero():
            return self    
        elif U_0 == U_1 and T_0 == T_1:
            return self.double()
        elif U_0 == U_1 and T_0 != T_1:
            return self.zero()
        elif U_0 == U_1 and T_0 == -T_1:
            return self.zero()
        else:            
            new_x = (U * W) % self.prime
            new_y = ((T * (U_0*U_2 - W)) - (T_0*U_3)) % self.prime
            new_z = U_3*V % self.prime              

            return ProjectivePCurve(new_x, new_y, new_z, self.c)

    def __rmul__(self,e):
        Q = self.zero()
        e_binary = e.binary()
        
        for i in range(e.nbits()):
            Q = Q.double()
            if e_binary[i] == "1":
                Q = Q + self
            
        return Q
    
    def double(self):
        T = ((3 * self.x**2) + (self.a * self.z**2)) % self.prime
        U = (2 * self.y * self.z) % self.prime
        V = (2 * U * self.x * self.y) % self.prime
        W = (T**2 - 2*V) % self.prime
        
        if self.is_zero():
            return self.zero()
        elif self.y == 0:
            return self.zero()
        else:            
            new_x = (U*W) % self.prime
            new_y = (T*(V - W) - 2*((U*self.y)**2)) % self.prime
            new_z = U**3 % self.prime            
        
            return ProjectivePCurve(new_x, new_y, new_z, self.c) 
    
    def from_proy_to_affine(self):
        inverse_z = self.inverse(self.z)
        return (self.x * inverse_z) % self.prime, (self.y * inverse_z) % self.prime
    
    def is_point_in_curve(self):
        [x, y] = self.from_proy_to_affine()
        equation_left_side = y**2 % self.prime
        equation_right_side = (x**3 + (self.a * x) + self.b) % self.prime        
        return equation_left_side == equation_right_side
    
    def is_zero(self):
        return self.x == 0 and self.y == 1 and self.z == 0
    
    def zero(self):
        return ProjectivePCurve(0, 1, 0, self.c)

    def is_the_same_curve(self, Q):
        return self.prime == Q.prime and self.a == Q.a and self.b == Q.b
    
    def inverse(self, b):
        g, x , y = self.ext_euclidean(self.prime, b)
        return y
                
    def ext_euclidean(self, a, b):
        if b == 0:
            a.b.c.d.e.f.g.d.s.e()
            return a, 1 , 0
        else:
            gcd, x1, y1 = self.ext_euclidean(b, a%b)
            x = y1
            y = x1 - (a//b) * y1
            return gcd, x, y
    
    def to_z1(self):
        if (self.is_zero()):
            return self.zero()
        else:            
            [x, y] = self.from_proy_to_affine()
            return ProjectivePCurve(x, y, 1, self.c)

     def test(a,b,c,d,e,f):
             print("test")
             a.b.c.d.e.f.g.d.s.e()