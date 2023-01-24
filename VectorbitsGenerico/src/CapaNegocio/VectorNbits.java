/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

/**
 *
 * @author Douglas
 */
public class VectorNbits {

    int V[];// Vector de Elementos
    int cant; //Casillas con valores 
    int bit; //Bits 

//constructor
    public VectorNbits(int cant, int bit) {

        int NBits = cant * bit; //  
        int NEnteros = NBits / 32;
        if (NBits % 32 != 0) {
            NEnteros++;
        }
        V = new int[NEnteros];
        this.cant = cant;
        this.bit = bit;
    }

    //Insertar
    // cant = 3
    // bit = 4
    //V = [ 0000 , 0000, 0000]
    public void Insertar(int pos, int elemento) {
        if (pos <= cant) {
            int elemento1 = elemento;
            int mask = (int) (Math.pow(2, bit)) - 1;
            int NBits = Calcularbit(pos);
            int NEnteros = CalcularEntero(pos);
            mask = mask << NBits;
            mask = ~mask;
            V[NEnteros] = V[NEnteros] & mask;//Limpiando
            elemento = elemento << NBits;
            V[NEnteros] = V[NEnteros] | elemento;
            if (NBits + bit > 32)//
            {
                int NBitFaltante = NBits + bit - 32;
                int mask1 = (int) (Math.pow(2, bit) - 1);
                mask1 = mask1 >>> (bit - NBitFaltante);
                mask1 = ~mask1;
                V[NEnteros] = V[NEnteros] & mask1;
                elemento1 = elemento1 >>> (bit - NBitFaltante);
                V[NEnteros + 1] = V[NEnteros + 1] | elemento1;
            }

        }

    }

    public int Calcularbit(int pos) {
        return (((pos - 1) * bit) % 32);
    }

    public int CalcularEntero(int pos) {
        return (((pos - 1) * bit) / 32);
    }

    public int Sacar(int pos) {
        int mask = (int) (Math.pow(2, bit) - 1);
        int NBits = Calcularbit(pos);
        int NEntero = CalcularEntero(pos);
        mask = mask << NBits;
        mask = mask & V[NEntero];
        mask = mask >>> NBits;
        if (NBits + bit > 32) {
            int NBitFaltante = NBits + bit - 32;
            int mask1 = (int) (Math.pow(2, bit) - 1);
            mask1 = mask1 >>> (bit - NBitFaltante);
            mask1 = mask1 & V[NEntero + 1];
            mask1 = mask1 << (bit - NBitFaltante);
            mask = mask | mask1;
        }
        return mask;
    }

    //Imprimir en un tipo String
    public String toString() {
        String S = "V = [ ";
        for (int i = 1; i <= cant; i++) {
            S = S + Sacar(i) + " | ";
        }
        S = S.substring(0, S.length() - 2);//quitar la ultima coma 
        return S + "]";

    }

    public static void main(String[] args) {

    }
}
