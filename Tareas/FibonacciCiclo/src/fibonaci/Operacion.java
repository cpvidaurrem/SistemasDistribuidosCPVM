package fibonaci;

/**
 *
 * @author CHRISTIAN
 */
public class Operacion {
    public static int fibonaci(int n){
        
        int num1 = 0;
        int num2 = 1;
        int suma = 1;
        for(int i = 1; i<n; i++){
            suma = num1 + num2;
            num1 = num2;
            num2 = suma;
        }
        return suma;
    };
}
