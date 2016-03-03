package risk;

import java.util.Random;
import java.util.Scanner;

public class DamRISK {
    /**
     * DEFINICIÓ DE CONSTANTS
     */
    
    /**
     * Véctor (array) amb els noms dels continents. La posició del continent dins del vector l'identifica en les diferents matrius o arrays on es relaciona. Seria la seva clau primària.
     */
    public static final String[] continents = {"Amèrica Nord","Amèrica Sud","Àfrica","Europa","Àsia","Oceania"};
    
    /**
     * Vector (array) amb els noms dels territoris. Es relacionen amb el seu continent ja que el nombre de fila correspon a la posició del array continents. 
     *
     * territoris[0] -> Correspondrà als territoris del continent[0] Amèrica del Nord
     * territoris[1] -> Correspondrà als territoris del continent[1] Amèrica Sud
     */
    public static final String[] territoris={"Alaska","Territorio del nor-oeste","Groenlandia","Alberta","Ontario","Labrador","Territorio del oeste","Territorio del este","America central",
            "Venezuela","Perú","Argentina","Brasil","África del norte","Egipto","Africa Oriental","Congo","África del sur","Magadascar","Europa Occidental","Gran Bretaña","Islandia","Escandinavia","Europa del norte","Europa del sur","Ucrania",
            "Ural","Afganistan","Oriente Medio","Siberia","Yakutia","Chita","Kamchatka","Mongolia","Japon","China","Siam","India","Indonesia","Nueva Guinea","Australia Occidental","Australia Oriental"};
    /**
     * Matriu (array de dues dimensions) que ens permet identificar a quin continent pertany cada païs. Com sempre juguem amb la posició dels arrays com a clau primària
     *
     * paissosContinent[0] són els païssos d'Amèrica del Nord
     *
     * paissosContinent[0][0] -> hi ha el territoris[0]
     */
    public static final int[][] paissosContinent= {{0,1,2,3,4,5,6,7,8},{9,10,11,12},{13,14,15,16,17,18},{19,20,21,22,23,24,25},{26,27,28,29,30,31,32,33,34,35,36,37},{38,39,40,41}};
    /**
         * Matriu (array de dues dimensions) que ens permet identificar els païssos veïns i així poder moure exèrcits entre ells o atacar. Segons moment de la partida.
         *
         * veins[0] ens llista els territoris fronteres amb Alaska.
         */
    public static final int[][] veins={{1,3,32},{0,2,3,4},{1,4,5,21},{0,1,4,6},{1,2,3,5,6,7},{2,4,7},{3,4,5,7,8},{6,4,5,8},{6,7,9},{8,10,12},{9,11,12},{10,12},{9,10,11,13},{12,14,15,16,19},{13,15,24,28},{13,14,16,17,18},{13,15,18},{15,17},{20,13,23,24},{19,21,23,22},{20,23,2},{21,25,23,20},{19,20,22,24,25},{19,20,22,24,25},{13,14,19,23,25},{22,23,24,26,27,28},{25,27,28,29},{25,26,28,37,35},{27,37,14},{28,31,33,34,35},{29,31,32},{30,31,32,33,34,0},{29,30,32,33},{29,31,32,34,35},{32,33},{29,28,27,37,36,35},{35,37,38},{35,36,27,28},{40,39,36},{38,40,41},{38,39,41},{40,39}};
    /**
     * Véctor (array) amb els objectius del joc. La posició de l'objectiu dins del vector l'identifica en les diferents matrius o arrays on es relaciona. Seria la seva clau primària.
      */    
     public static final String[] objectius = {"Amèrica sur i Àsia","Amèrica sur, Europa i un tercer continent","24 territoris","18 territoris amb dos exèrcits a cadascun com a mínim","Oceania, Europa i un tercer continent","Amèrica del nord i Àfrica","Amèrica del nord i Oceania","Àfrica i Àsia"};
        
    /**
     * Véctor (array) amb la quantitat d'exèrcits inicials. En la posició 0 correspon a 3 jugadors i la posició 3 a 6 jugadors.
     */
    public static final int[] exercicitsInicials = {35,30,25,20};
    
    /**
     * Véctor (array) amb la quantitat d'exèrcits que guanyes per continent conquistat. En la posició 0 correspon a Amèrica del Nord i la 5 a Oceania.
     */
    public static final int[] continentsComplets = {5,2,3,5,7,2};
    
    /**
     * Nombre que divideix els païssos conquerits per saber les unitats de reforç.
     */
    public static final int divisioTerritori = 3;
    /**
     * Nombre màxims de jugadors que poden jugar al DamRISK.
     */
    public static final int maxJugadors = 6;
    /**
     * Nombre mínim de jugadors que poden jugar al DamRISK.
     */
    public static final int minJugadors = 3;
    
    public static void main (String[] args){
        /**
         * Matriu que representa el tauler de joc. Cada posició té un array on es guarda la informació següent
         * {Id jsugador, Número exercits}
         * Cada posició és un territori.
         */
        int[][] tauler = new int[42][2];
        /**
         * Inicialitzem el tauler sense jugadors, valor -1. Doncs el jugador 0 existeix  
         */
        for(int territori=0;territori<tauler.length;territori++){
            tauler[territori][0]=-1;
        }
        /**
         * Vector per guardar els noms dels jugadors. La posició dins del vector és l'identificador de jugador.
         */
        String[] nomsJugadors=null;
        /**
         * Matriu on guardem la informació del joc per a cada jugador. On guardem la informació del jugador. Per a cada jugador guardem
         * nomsJugadors[0] li correspon la infoJugadors
         * {objectiu, cavalleria, artilleria, cano, comodi }
         */
        int[][]  infoJugadors =null;
        
        /**
         * Enter que indicarà el nombre de jugador que li toca tirar. 
         */
        int torn=0;
        Scanner lector = new Scanner(System.in);
        /**
         * Java.util.Scanner ens permet llegir de consola les dades dels usuaris
         */
        // Codi per demanar el nombre de jugadors
        int numJugadors = 0;
        String numJugadorstr;
        boolean numerico = true;
        do{
        System.out.println("Introduce el numero de jugadores (Minimo "+minJugadors+" - Maximo "+maxJugadors+")");
        numJugadorstr = lector.next();
        numerico = comprobar(numJugadorstr);
        if (numerico){
            System.out.println("");
        }
        else{
        numJugadors = Integer.parseInt(numJugadorstr);
        }
        if(numJugadors<minJugadors || numJugadors>maxJugadors){
            System.out.println("El valor introducido no es valido");
        }
        }while (numJugadors<minJugadors || numJugadors>maxJugadors);
        

        // Dimensionar els vectors nomsJugadors i infoJugadors
        nomsJugadors = new String [numJugadors];
        infoJugadors = new int [numJugadors][5];
        
        // Calcular nombre d'exèrcits inicials
        int tropasJugadors = 0;
        int paisesJugador = 0;        
        switch(numJugadors){
        case 3: 
            tropasJugadors = exercicitsInicials[0];
            paisesJugador = 14;
            break;
        case 4: 
            tropasJugadors = exercicitsInicials[1];
            paisesJugador = 10;
            break;
        case 5: 
            tropasJugadors = exercicitsInicials[2];
            paisesJugador = 8;
            break;
        case 6: 
            tropasJugadors = exercicitsInicials[3];
            paisesJugador = 7;
            break;
        }
        
        
        // Demanar les dades als jugadors i preparar-los per poder iniciar el joc.
        for(int jugador=0;jugador<numJugadors;jugador++){
            // Demanar el nom i guardar-ho en el vector
            System.out.println("");
            System.out.println("----------------------------------------------");
            String nombrejugador;
            System.out.println("Jugador"+(jugador+1)+", introdueix el teu nom");
            nomsJugadors[jugador] = lector.next();
            // Assignar objetiu
            infoJugadors[jugador][0] = randomizer(objectius.length);
            System.out.println("Tu objetivo es "+objectius[infoJugadors[jugador][0]]);
            // Repartir territorios
            Repartir(numJugadors,tauler,jugador,paisesJugador);
            // Assignar tropes
            String[] paisjugador;
            if ((numJugadors==4 || numJugadors==5)&&(numJugadors-jugador <= 2)){
            paisjugador = new String[paisesJugador+1];
            }
            else{
            paisjugador = new String[paisesJugador];
            }
            int tropasactuales = tropasJugadors-paisesJugador;
            int a = 0;
            for (int i = 0; i < tauler.length; i++) {
                if (tauler[i][0]==jugador){
                    paisjugador[a]=territoris[i];
                    a++;
                }
            }
            System.out.println("Tus territorios son:");
            mostrarTerritoris(paisjugador);
            tropasactuales = primeraAsig(tauler,jugador,tropasactuales);
            System.out.println("");
            mostrarTropas(tauler,jugador);
            boolean salir = false;
            do{
                System.out.println("");
                System.out.println("-------------------------------------------------");
                System.out.println("");
                System.out.println("Tienes "+tropasactuales+" ejercitos por colocar.");
                System.out.println("Que quieres hacer?");
                System.out.println("1. Asignar");
                System.out.println("2. Asignar aleatoriamente");
                System.out.println("3. Retirar");
                System.out.println("4. Observar tus territorios");
                System.out.println("5. Acabar de repartir");
                int opcio = lector.nextInt();
                String aspais = "";
                int acierto = 0;
                boolean correcto = false;
                int numex = 0;
                switch(opcio){
                // Menu para asignar tropas
                case 1:
                    tropasactuales = asignarTropas(tauler,aspais,acierto,correcto,numex,paisjugador,tropasactuales);
                    break;
                    
                // Menu para retirar tropas
                case 2:
                    if(tropasactuales!=0){
                        do{
                        int paisrand;
                        int ejercitorand;
                        paisrand = randomizer(paisjugador.length);
                        for (int j = 0; j < territoris.length; j++) {    
                        if (paisjugador[paisrand].equals(territoris[j])){
                            ejercitorand = randomizer(tropasactuales+1);
                            tauler[j][1] = tauler[j][1] + ejercitorand;
                            if (tropasactuales - ejercitorand != tropasactuales){
                                System.out.println("Se han añadido "+ejercitorand+" tropas a "+territoris[j]);
                            }
                            tropasactuales = tropasactuales - ejercitorand;
                        }
                    }
                    }while(tropasactuales!=0);
                    }
                    else{
                        System.out.println("No te quedan tropas para repartir!");
                    }
                    
                    break;
                case 3:
                    tropasactuales = retirarTropas(tauler,aspais,acierto,correcto,numex,paisjugador,tropasactuales);
                    break;
                case 4:
                    mostrarTropas(tauler,jugador);
                    break;
                case 5:
                    if (tropasactuales==0){
                        salir = true;
                    }
                    else{
                        System.out.println("No puedes acabar mientras te queden tropas!");
                    }
                    break;
                default:
                    System.out.println("No has elegido una opcion correcta!");
                    break;
                }
            }while(tropasactuales>=0 && salir==false);
            
        }    
        
        // Funció mostrar tot el tauler
        
        risk.TotElTauler(tauler,territoris,nomsJugadors);
        
        
        
        // Decidir qui comença a jugar i dir-ho per pantalla
        torn = randomizer(numJugadors);
        System.out.println("Li toca al jugador "+(nomsJugadors[torn]));
        
        /**
         * Pinta el tauler com ha quedat 
         */
        mostrarTauler(tauler,nomsJugadors);
    }    
    public static int randomizer(int num){
        Random rdn = new Random();
        int numerorandom = rdn.nextInt(num);
        return numerorandom;
    }
    public static boolean comprobar(String a) {
        if (a == null || a.isEmpty()){
            System.out.println("Tiene que ser un numero! introduce otra vez");
            return true;
        }
        int i = 0;
        if (a.charAt(0) == '-'){
            if (a.length() > 1){
                i++;
            } else {
                System.out.println("Tiene que ser un numero! introduce otra vez");
                return true;
            }
        }
        for (; i < a.length(); i++) {
            if (!Character.isDigit(a.charAt(i))){
                System.out.println("Tiene que ser un numero! introduce otra vez");
                return true;
            }
        }
            return false;
    }
    public static void mostrarTauler(int tauler[][], String nomsJugadors[]) {
        System.out.println("");
        System.out.println("Asi queda el tablero:");
        for(int territori=0;territori<tauler.length;territori++){
            System.out.println(territoris[territori]+"-"+nomsJugadors[tauler[territori][0]]+"-"+tauler[territori][1]);
        }
    }
    public static int primeraAsig(int tauler[][],int jugador,int tropasactuales){
        Scanner lector = new Scanner(System.in);
        System.out.println("");
        System.out.println("PRIMERA ASIGNACION");
        System.out.println("");
        for (int i = 0; i < tauler.length; i++) {
            if (tropasactuales==0){
                System.out.println("No te quedan tropas para repartir!");
                break;
            }
            else{
            if (tauler[i][0]==jugador){
            String numtropstr;
            int numtrop;
            do{
            do{
            System.out.println("Te quedan "+tropasactuales+" tropas.");
                System.out.print("Cuantas tropas quieres poner en "+territoris[i]+"?");
            numtropstr = lector.next();
            }while(comprobar(numtropstr));
            numtrop = Integer.parseInt(numtropstr);
            if(numtrop>tropasactuales){
                System.out.println("no tienes tantas tropas!");
            }
            }while(numtrop>tropasactuales);
            tauler[i][1]= tauler[i][1] + numtrop;
            tropasactuales = tropasactuales - numtrop;
            }
            }
        }
        return tropasactuales;
    }
    public static void mostrarTerritoris(String paisjugador[]) {
        for (int i = 0; i < paisjugador.length; i++) {
            System.out.print(paisjugador[i]+", ");
        }
    }
    public static void mostrarTropas(int tauler[][],int jugador) {
        System.out.println("");
        System.out.println("Asi estan distribuidas tus tropas:");
        System.out.println("");
        for(int territori=0;territori<tauler.length;territori++){
            if(tauler[territori][0]==jugador){
            System.out.println(territoris[territori]+"- Tropas - "+tauler[territori][1]);
            }
            }
    }
    public static void Repartir(int numJugadors, int tauler[][],int jugador, int paisesJugador){
        if (numJugadors-jugador==1){
            for (int i = 0; i < tauler.length; i++) {
                if (tauler[i][0]==-1){
                    tauler[i][0]=jugador;
                    tauler[i][1]=1;
                }
            }
        }
        else{
        if (numJugadors != 4 && numJugadors != 5){
        for (int i = 0; i < paisesJugador; i++) {
            int pais;
            do{
            pais = randomizer(41);
            }while(tauler[pais][0] != -1);
            tauler[pais][0] = jugador;
            tauler[pais][1] = 1;
        }
        }
        else{
            if(numJugadors-jugador <= 2){
            for (int i = 0; i < paisesJugador+1; i++) {
                int pais;
                do{
                pais = randomizer(41);
                }while(tauler[pais][0] != -1);
                tauler[pais][0] = jugador;
                tauler[pais][1] = 1;
            }
            }
            else{
                for (int i = 0; i < paisesJugador; i++) {
                    int pais;
                    do{
                    pais = randomizer(41);
                    }while(tauler[pais][0] != -1);
                    tauler[pais][0] = jugador;
                    tauler[pais][1] = 1;
            }
            }
        }
        }
    }
    public static int asignarTropas(int tauler[][], String aspais, int acierto, boolean correcto, int numex, String paisjugador[], int tropasactuales){
        Scanner lector = new Scanner(System.in);
        acierto = -2;
        correcto = false;
        numex = 0;
        System.out.println("En que territorio quieres poner tropas?");
        aspais = lector.nextLine();
        for (int i = 0; i < paisjugador.length; i++) {
            if (aspais.equalsIgnoreCase(paisjugador[i])){
                acierto= i;
            }
        }
        if (acierto != -2){
            do{
            System.out.println("Cuantos ejercitos quieres poner?");
            numex = lector.nextInt();
            if (numex<=tropasactuales){
                correcto = true;
                for (int i = 0; i < tauler.length; i++) {
                    if (aspais.equalsIgnoreCase(territoris[i])){
                        tauler[i][1] = tauler[i][1] + numex; 
                        tropasactuales = tropasactuales - numex;
                        acierto= i;
                    }
                } 
            }
            else{
                System.out.println("No tienes tantas tropas!");
            }
            }while(correcto == false);
        }
        else{
            System.out.println("Ese territorio no es tuyo o no existe!");
        }
        return tropasactuales;
    }
    public static int retirarTropas(int tauler[][], String aspais, int acierto, boolean correcto, int numex, String paisjugador[], int tropasactuales){
        Scanner lector = new Scanner(System.in);
        acierto = -2;
        correcto = false;
        numex = 0;
        System.out.println("De que territorio quieres quitar tropas?");
        aspais = lector.nextLine();
        for (int i = 0; i < paisjugador.length; i++) {
            if (aspais.equalsIgnoreCase(paisjugador[i])){
                acierto= i;
            }
        }
        if (acierto != -2){
            do{
            System.out.println("Cuantos ejercitos quieres quitar?");
            numex = lector.nextInt();
            for (int i = 0; i < tauler.length; i++) {
                if (aspais.equalsIgnoreCase(territoris[i])){
                    if(tauler[i][1] == 1){
                    correcto = true;
                    break;
                    }
                    else{
                        if (aspais.equalsIgnoreCase(territoris[i]) && tauler[i][1]>numex){
                            tauler[i][1] = tauler[i][1] - numex;
                            tropasactuales= tropasactuales + numex; 
                            acierto= i;
                            correcto = true;
                        }
                        else{
                            System.out.println("No puedes retirar tantas tropas (tiene que haber 1 en cada territorio)");
                        }
                    }
            } 
            }
            }while(correcto == false);
            }
        
        else{
            System.out.println("Ese territorio no es tuyo o no existe!");
        }
        return tropasactuales;
    }
    public static int pasarTurno(int torn,int numJugadors){
        switch (numJugadors){
        case 3:
            if (torn<3){
                torn++;
            }
            else{
                torn = 0;
            }
            break;
        case 4:
            if (torn<4){
                torn++;
                torn = 0;
            }
            break;
        case 5:
            if (torn<5){
                torn++;
                torn = 0;
            }
            break;
        case 6:
            if (torn<6){
                torn++;
                torn = 0;
            }
            break;
        }
        return torn;
    }
}