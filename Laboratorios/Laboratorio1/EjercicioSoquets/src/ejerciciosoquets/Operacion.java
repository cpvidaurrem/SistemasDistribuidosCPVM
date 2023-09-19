/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosoquets;

/**
 *
 * @author CHRISTIAN
 */
public class Operacion {
    public int a;
    public int b;
    public int c;

    public Operacion(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }
    
    public int mayor(){
        int numMayor = this.a;
        
        if (this.a > this.b && this.a > this.c) {
            numMayor = this.a;
        }else{
            if(this.b > this.a && this.b > this.c){
                numMayor = this.b;
            } else {
                numMayor = this.c;
            }
        }
        
        return numMayor;
    }
    
    
    public int menor(){
        int numMenor = this.a;
        
        if (this.a < this.b && this.a < this.c) {
            numMenor = this.a;
        }else{
            if(this.b < this.a && this.b < this.c){
                numMenor = this.b;
            } else {
                numMenor = this.c;
            }
        }
        
        return numMenor;
    }
}
