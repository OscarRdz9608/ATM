package com.mx.ATM;
import java.util.Scanner;


public class AT {

		// TODO Auto-generated method stub

	
	
		    // Cantidades iniciales de cada denominación
		    private static int billetes1000 = 2;
		    private static int billetes500 = 5;
		    private static int billetes200 = 10;
		    private static int billetes100 = 20;
		    private static int billetes50 = 30;
		    private static int billetes20 = 40;
		    private static int monedas10 = 50;
		    private static int monedas5 = 100;
		    private static int monedas2 = 200;
		    private static int monedas1 = 300;
		    private static int monedas0_5 = 100;

		    private static double saldo = calcularSaldo();

		    public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);
		        int opcion;

		        do {
		            mostrarMenu();
		            System.out.print("Seleccione una opción: ");
		            opcion = scanner.nextInt();

		            switch (opcion) {
		                case 1:
		                    consultarSaldo();
		                    break;
		                case 2:
		                    depositarDinero(scanner);
		                    break;
		                case 3:
		                    retirarDinero(scanner);
		                    break;
		                case 4:
		                    System.out.println("Gracias por usar el cajero automático. ¡Hasta luego!");
		                    break;
		                default:
		                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
		            }
		        } while (opcion != 4);

		        scanner.close();
		    }

		    private static void mostrarMenu() {
		        System.out.println("\nCajero Automático");
		        System.out.println("1. Consultar saldo");
		        System.out.println("2. Depositar dinero");
		        System.out.println("3. Retirar dinero");
		        System.out.println("4. Salir");
		    }

		    private static void consultarSaldo() {
		        System.out.println("Su saldo actual es: $" + saldo);
		        System.out.println("Denominaciones disponibles:");
		        System.out.println("Billetes de 1000: " + billetes1000);
		        System.out.println("Billetes de 500: " + billetes500);
		        System.out.println("Billetes de 200: " + billetes200);
		        System.out.println("Billetes de 100: " + billetes100);
		        System.out.println("Billetes de 50: " + billetes50);
		        System.out.println("Billetes de 20: " + billetes20);
		        System.out.println("Monedas de 10: " + monedas10);
		        System.out.println("Monedas de 5: " + monedas5);
		        System.out.println("Monedas de 2: " + monedas2);
		        System.out.println("Monedas de 1: " + monedas1);
		        System.out.println("Monedas de 0.5: " + monedas0_5);
		    }

		    private static void depositarDinero(Scanner scanner) {
		        System.out.println("Ingrese la cantidad de billetes y monedas a depositar:");

		        System.out.print("Billetes de 1000: ");
		        int b1000 = scanner.nextInt();
		        System.out.print("Billetes de 500: ");
		        int b500 = scanner.nextInt();
		        System.out.print("Billetes de 200: ");
		        int b200 = scanner.nextInt();
		        System.out.print("Billetes de 100: ");
		        int b100 = scanner.nextInt();
		        System.out.print("Billetes de 50: ");
		        int b50 = scanner.nextInt();
		        System.out.print("Billetes de 20: ");
		        int b20 = scanner.nextInt();
		        System.out.print("Monedas de 10: ");
		        int m10 = scanner.nextInt();
		        System.out.print("Monedas de 5: ");
		        int m5 = scanner.nextInt();
		        System.out.print("Monedas de 2: ");
		        int m2 = scanner.nextInt();
		        System.out.print("Monedas de 1: ");
		        int m1 = scanner.nextInt();
		        System.out.print("Monedas de 0.5: ");
		        int m0_5 = scanner.nextInt();

		        billetes1000 += b1000;
		        billetes500 += b500;
		        billetes200 += b200;
		        billetes100 += b100;
		        billetes50 += b50;
		        billetes20 += b20;
		        monedas10 += m10;
		        monedas5 += m5;
		        monedas2 += m2;
		        monedas1 += m1;
		        monedas0_5 += m0_5;

		        saldo = calcularSaldo();
		        System.out.println("Monto depositado exitosamente. Su nuevo saldo es: $" + saldo);
		    }

		    private static void retirarDinero(Scanner scanner) {
		        System.out.print("Ingrese el monto a retirar: $");
		        double monto = scanner.nextDouble();
		        
		        if (monto > 0 && monto <= saldo) {
		            if (distribuirDinero(monto)) {
		                saldo = calcularSaldo();
		                System.out.println("Monto retirado exitosamente. Su nuevo saldo es: $" + saldo);
		            } else {
		                System.out.println("No hay suficientes denominaciones para completar esta transacción.");
		            }
		        } else if (monto > saldo) {
		            System.out.println("Fondos insuficientes. Su saldo actual es: $" + saldo);
		        } else {
		            System.out.println("Monto no válido. Intente de nuevo.");
		        }
		    }

		    private static boolean distribuirDinero(double monto) {
		        int[] billetes = {1000, 500, 200, 100, 50, 20};
		        int[] monedas = {10, 5, 2, 1};
		        double[] centavos = {0.5};

		        int[] cantidadBilletes = {billetes1000, billetes500, billetes200, billetes100, billetes50, billetes20};
		        int[] cantidadMonedas = {monedas10, monedas5, monedas2, monedas1};
		        int[] cantidadCentavos = {monedas0_5};

		        double montoRestante = monto;

		        for (int i = 0; i < billetes.length; i++) {
		            int numBilletes = (int) (montoRestante / billetes[i]);
		            if (numBilletes > cantidadBilletes[i]) {
		                numBilletes = cantidadBilletes[i];
		            }
		            montoRestante -= numBilletes * billetes[i];
		            cantidadBilletes[i] -= numBilletes;
		        }

		        for (int i = 0; i < monedas.length; i++) {
		            int numMonedas = (int) (montoRestante / monedas[i]);
		            if (numMonedas > cantidadMonedas[i]) {
		                numMonedas = cantidadMonedas[i];
		            }
		            montoRestante -= numMonedas * monedas[i];
		            cantidadMonedas[i] -= numMonedas;
		        }

		        for (int i = 0; i < centavos.length; i++) {
		            int numCentavos = (int) (montoRestante / centavos[i]);
		            if (numCentavos > cantidadCentavos[i]) {
		                numCentavos = cantidadCentavos[i];
		            }
		            montoRestante -= numCentavos * centavos[i];
		            cantidadCentavos[i] -= numCentavos;
		        }

		        if (montoRestante > 0) {
		            // Revertir cambios
		            for (int i = 0; i < billetes.length; i++) {
		                cantidadBilletes[i] = cantidadBilletes[i] + (int) (montoRestante / billetes[i]);
		            }
		            for (int i = 0; i < monedas.length; i++) {
		                cantidadMonedas[i] = cantidadMonedas[i] + (int) (montoRestante / monedas[i]);
		            }
		            for (int i = 0; i < centavos.length; i++) {
		                cantidadCentavos[i] = cantidadCentavos[i] + (int) (montoRestante / centavos[i]);
		            }
		            return false;
		        }

		        // Actualizar cantidades si la transacción es exitosa
		        billetes1000 = cantidadBilletes[0];
		        billetes500 = cantidadBilletes[1];
		        billetes200 = cantidadBilletes[2];
		        billetes100 = cantidadBilletes[3];
		        billetes50 = cantidadBilletes[4];
		        billetes20 = cantidadBilletes[5];
		        monedas10 = cantidadMonedas[0];
		        monedas5 = cantidadMonedas[1];
		        monedas2 = cantidadMonedas[2];
		        monedas1 = cantidadMonedas[3];
		        monedas0_5 = cantidadCentavos[0];

		        return true;
		    }

		    private static double calcularSaldo() {
		        return billetes1000 * 1000 + billetes500 * 500 + billetes200 * 200 + billetes100 * 100 + billetes50 * 50 + billetes20 * 20
		            + monedas10 * 10 + monedas5 * 5 + monedas2 * 2 + monedas1 * 1 + monedas0_5 * 0.5;
		    }
		}
