
/*
Ejercicio:

Con base en la descripción general del siguiente proyecto, y considerando los lineamientos
generales del uso y nombramiento de variables para la construcción de software, identifica y
corrige (justificando claramente tu respuesta) los códigos de la clase DataSourse.

Descripción general del proyecto:

Se requiere desarrollar un proyecto bancario para el manejo de sus clientes con sus
correspondientes transacciones bancarias. Por lo mismo, considera que las especificaciones
que se listan a continuación son suficientes para satisfacer sus necesidades del momento:
a. El banco requiere guardar su nombre, dirección y teléfono; y puede servir hasta a diez
clientes.
b. Cada cliente se registra con su nombre y apellido.
c. Los clientes pueden tener de una a cinco cuentas bancarias.
d. Toda cuenta bancaria tiene un balance, dato que contiene el valor monetario de dicha
cuenta bancaria, y que nunca debe ser menor que cero. Por otro lado, toda cuenta
bancaria mantiene actualizado su balance a través de dos operaciones: retirando
dinero de la cuenta y depositando dinero a la cuenta
e. Existen dos tipos de cuentas bancarias: Cuentas de cheques y Cuentas de ahorros.
f. La información de las transacciones bancarias se guarda en un archivo de texto, al
cual se tiene acceso para la generación de los reportes correspondientes.
 */
package com.mybank.data;
import com.mybank.domain.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DataSource {

    private final File DATAFILE;
    
    public DataSource(String dataFilePath) {
        this.DATAFILE = new File(dataFilePath);
    }

    public void loadData(String f) throws IOException {
        Scanner input = new Scanner(DATAFILE);
        // => Eliminamos estas lineas por el tiempo de vida inecesario 
        /*String fn;
        String ln;*/
        
        // => Esto es un if redundante
        /*if (!DATAFILE.exists())
        else
            System.out.println("Existe");*/
        if (DATAFILE.exists())
            System.out.println("Existe");

        int numOfAccounts;
        char accountType;
        Customer customer;
        
        //idx es remplazado por customerId ya que hay otro bucle anidado y idx no me da una clara referencia de que es lo que se esta contando.
        int numOfCustomers = input.nextInt();
        for ( int customerId = 0; customerId < numOfCustomers; customerId++ ) {
            String fn = input.next();
            String ln = input.next();
            Bank.addCustomer(fn, ln);
            customer = Bank.getCustomer(customerId);
            numOfAccounts = input.nextInt();
            System.out.println(numOfCustomers);
            while ( numOfAccounts-- > 0 ) {
                accountType = input.next().charAt(0);
                switch ( accountType ) {
                    case 'S': {
                        double initBalance = Float.parseFloat(input.next());
                        double interestRate = Float.parseFloat(input.next());
                        customer.addAccount(new SavingsAccount(initBalance,interestRate));
                        break;
                    }
                    case 'C': {
                        float initBalance = Float.parseFloat(input.next());
                        float overdraftProtection = Float.parseFloat(input.next());
                        customer.addAccount(new CheckingAccount(initBalance, overdraftProtection));
                        break;
                    }
                }
            }
        }
    }
    public void generateCustomerReport() {
        System.out.println("\t\t\tCUSTOMERS REPORT");
        System.out.println("\t\t\t================");
        for ( int cust_idx = 0; cust_idx < Bank.getNumOfCustomers(); cust_idx++ ) {
            Customer customer = Bank.getCustomer(cust_idx);
            System.out.println("Customer: " + customer.getLastName() + ", " + customer.getFirstName());
        }
    }
}