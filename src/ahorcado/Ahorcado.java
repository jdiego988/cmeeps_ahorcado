/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcado;

import java.util.Scanner;

/**
 *
 * @author jdieg
 */
public class Ahorcado {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        final int puntuacion_ganador=5;
        final int NUM_ERRORES=6;
        Scanner sn = new Scanner(System.in);
        String jugadores[] = new String[2];
        int puntos[]= new int[2];
        int turno=0;
        String palabra_elegida="";
        String palabra_adivinar;
        String palabraUsuario;
        int reemplazos;
        int nErrores=6;
        char abecedario[]=generaCaracteres();
        boolean caracteresInsertados[]=new boolean[26];
        //pedimos nombre jugadores
        for(int i=0; i<jugadores.length;i++){
            jugadores[i]=introducirString(sn,"Escribe el nombre del jugador: "+(i+1));
        }
        
        
        
        while(!fin(puntos, puntuacion_ganador)){
            palabra_elegida=introducirString(sn, "Escribe una palabra jugador "+jugadores[turno]);
            palabra_adivinar=guiones(palabra_elegida);
            espacios(30);
            while(!palabraCorrecta(palabra_elegida,palabra_adivinar)&&nErrores>0){
                mostrarPalabra(palabra_adivinar);
                mostrarRepetidos(abecedario,caracteresInsertados);
                
                palabraUsuario=introducirString(sn,"Escribe una letra");
                System.out.println("");
                
                if(Character.isAlphabetic(palabraUsuario.charAt(0))){
                    if(numRemplazos(palabra_elegida,palabraUsuario.charAt(0))>0){
                        palabra_adivinar=reemplazar(palabra_elegida,palabra_adivinar,palabraUsuario.charAt(0));
                    }else{
                        nErrores--;
                        System.out.println("Error, te quedan "+nErrores+ " errores");
                    }
                }
            }
           
            if(nErrores>0){
                puntos[turno]++;
            }else{
                sumaPuntosOtroJugador(puntos, 1, turno);
            }
             mostrarPuntuaciones(jugadores,puntos);
            turno=cambiaTurno(turno,jugadores.length-1);
            nErrores=NUM_ERRORES;
        }
    }
    
    public static String introducirString(Scanner sn, String n){
        System.out.println(n);
        return sn.next();
    }
    public static char[] generaCaracteres(){
        char[] caracteres=new char[26];
        for(int i=0,j=97;i<caracteres.length;i++,j++){
            caracteres[i]=(char)j;
        }
        return caracteres;
    }
    public static boolean fin(int puntos[], int puntuacion){
        for(int i=0;i<puntos.length;i++){
            if(puntos[i]>=puntuacion){
                return true;
            }
        }
        return false;
    }
    
    public static void espacios(int numSaltos){
        for(int i=0;i<numSaltos;i++){
            System.out.println("");
        }
    }
    
    public static String guiones(String cadena){
        String palabra="";
        for(int i=0; i<cadena.length();i++){
            palabra+="_";
        }
        return palabra;
    }
    
    public static void mostrarPalabra(String cadena){
        for(int i=0;i<cadena.length();i++){
            System.out.println(cadena.charAt(i)+" ");
        }
        System.out.println("");
    }
    
    public static boolean palabraCorrecta(String palabraOriginal, String palabraUsuario){
        return palabraOriginal.equals(palabraUsuario);
    }
    
    public static void mostrarRepetidos(char[] abecedario,boolean insertados[]){
        System.out.println("");
        for(int i=0;i<insertados.length;i++){
            if(insertados[i]){
                System.out.println(abecedario[i]);
            }
        }
        System.out.println("");
    }
    
    public static void sumaPuntosOtroJugador(int [] puntos, int puntosSuperar, int pos_excluida){
        for(int i=0;i<puntos.length;i++){
            if(i!=pos_excluida){
                puntos[i]++;
            }
        }
    }
    
    public static String reemplazar(String cadenaOriginal,String cadenaReemplazar,char caracter){
        String cadenaRemplazo="";
        char caracterCadena;
        for(int i=0;i<cadenaOriginal.length();i++){
            caracterCadena=cadenaOriginal.charAt(i);
            if(caracterCadena==caracter){
                cadenaRemplazo+=caracter;
            }else{
                cadenaRemplazo+=cadenaReemplazar.charAt(i);
            }
        }
        return cadenaRemplazo;
    }
    
    public static int numRemplazos(String cadenaOriginal, char caracter){
        int remplazos=0;
        char caracterCadena;
        for(int i=0;i<cadenaOriginal.length();i++){
            caracterCadena=cadenaOriginal.charAt(i);
            if(caracterCadena==caracter){
                remplazos++;
            }
        }
        return remplazos;
    }
    
    public static int cambiaTurno(int turnoActual,int limite){
        if(turnoActual==limite){
            return 0;
        }else{
            turnoActual++;
            return turnoActual;
        }
    }
    
    public static void mostrarPuntuaciones(String[] jugadores,int[] puntos){
        for(int i=0;i<jugadores.length;i++){
            System.out.println(jugadores[i]+ ": "+puntos[i]+" puntos");
        }
    }
}
