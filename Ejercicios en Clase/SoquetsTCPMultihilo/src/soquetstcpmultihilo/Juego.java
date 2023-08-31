/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soquetstcpmultihilo;

/**
 *
 * @author CHRISTIAN
 */
public class Juego {

    String[] preguntas;
    double[] respuestas;
    int nroPreguntas;
    int preguntaActual;
    int preguntasCorrectas;

    public Juego() {
        this.nroPreguntas = 10;

        this.preguntas = new String[10];
        this.preguntas[0] = "1+1= ";
        this.preguntas[1] = "1+2= ";
        this.preguntas[2] = "1+3= ";
        this.preguntas[3] = "1+4= ";
        this.preguntas[4] = "1+5= ";
        this.preguntas[5] = "1+6= ";
        this.preguntas[6] = "1+7= ";
        this.preguntas[7] = "1+8= ";
        this.preguntas[8] = "1+9= ";
        this.preguntas[9] = "1+10= ";

        this.respuestas = new double[10];
        this.respuestas[0] = 2;
        this.respuestas[1] = 3;
        this.respuestas[2] = 4;
        this.respuestas[3] = 5;
        this.respuestas[4] = 6;
        this.respuestas[5] = 7;
        this.respuestas[6] = 8;
        this.respuestas[7] = 9;
        this.respuestas[8] = 10;
        this.respuestas[9] = 11;

    }

    public void iniciar() {
        this.preguntasCorrectas = 0;
        this.preguntaActual = 1;
    }

    public String getPregunta() {
        if (this.preguntaActual != this.nroPreguntas) {
            String pregunta = this.preguntas[this.preguntaActual - 1];
            this.preguntaActual++;
            return pregunta;
        }
        return "no existen preguntas";
    }
    
    public String verificarRespuesta(int nroPregunta, double respuesta){
        if(nroPregunta > this.nroPreguntas){
            return "Numero de preguntas incorrecta";
        }
        if(respuesta == this.respuestas[nroPregunta - 1]) {
            this.preguntasCorrectas++;
            return "Respuesta Correcta";
        }
        return "Respuesta Incorrecta";
    }

    public int getPreguntaActual() {
        return preguntaActual;
    }

    public int getPreguntasCorrectas() {
        return preguntasCorrectas;
    }
    
    
}
