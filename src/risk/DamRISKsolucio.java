package risk;

public class DamRISKsolucio {
	/**
	 * DEFINICIÓ DE CONSTANTS
	 */
	
	/**
	 * Véctor (array) amb els noms dels continents. La posició del continent dins del vector l'identifica en les diferents matrius o arrays on es relaciona. Seria la seva clau primària.
	 */
	public static final String[] continents = {"Amèrica Nord","Amèrica Sud","Àfrica","Europa","Àsia","Oceania"};
	
	/**
	 * Véctor (array) amb els noms dels territoris. Es relacionen amb el seu continent ja que el nombre de fila correspon a la posició del array continents. 
	 *
	 * territoris[0] -> Correspondrà als territoris del continent[0] Amèrica del Nord
	 * territoris[1] -> Correspondrà als territoris del continent[1] Amèrica Sud
	 */
	public static final String[] territoris={"Alaska","Territorio del nor-oeste","Groenlandia","Alberta","Ontario","Labrador","Territorio del oeste","Territorio del este","America central",
			"Venezuela","Perú","Argentina","Brasil","África del norte","Egipto","Africa Oriental","Congo","África del sur","Magadascar","Europa Occidental","Gran Bretaña","Islandia","Escandinavia","Europa del norte","Europa del sur","Ucrania",
			"Ural","Afganistan","Oriente Medio","Siberia","Yakutia","Chita","Kamchatka","Mongolia","Japon","China","Siam","India","Indonesia","Nueva Guinea","Australia Occidental","Australia Oriental"};

	/**
	 * Matriu (array de dues dimensions) que ens permet identificar els païssos veïns i així poder moure exèrcits entre ells o atacar. Segons moment de la partida.
	 */
	//public static final String[][] fronteres={{"Alaska,Kamchatka"}};
	public static final String[][] veins={{"Alaska,Kamchatka"}};
	
	
	
	/**
	 * Véctor (array) amb els objectius del joc. La posició de l'objectiu dins del vector l'identifica en les diferents matrius o arrays on es relaciona. Seria la seva clau primària.
	 */	
	public static final String[] objectius = {"Amèrica sur i Àfrica","Amèrica del nord i Oceania","Àfrica i Àsia"};
	
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
		 * {Id jugador, Número exercits}
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
	    
		/**
		 * Java.util.Scanner ens permet llegir de consola les dades dels usuaris
		 */
		//TODO Codi per demanar el nombre de jugadors
		java.util.Scanner lector = new java.util.Scanner(System.in);
		
		int numJugadors=0;
		System.out.println("Quants jugueu (mínim 3 i màxim 6)?");
		do{
			numJugadors=(lector.hasNextInt()?lector.nextInt():0);
		}while(numJugadors<3 && numJugadors>6);	
		
		//TODO Dimensionar els vectors nomsJugadors i infoJugadors
		nomsJugadors= new String[numJugadors];
		infoJugadors= new int[numJugadors][5];
		
		//TODO Calcular nombre d'exèrcits inicials
		int exercitsInici = quantsExercicitsInicials(exercicitsInicials,numJugadors);
		
		
		//TODO Demanar les dades als jugadors i preparar-los per poder iniciar el joc.
		for(int jugador=0;jugador<numJugadors;jugador++){
			//Demanar el nom i guardar-ho en el vector
			String nom="";
			System.out.println("Quin és el nom del jugador "+(jugador+1)+"?");
			do{
				nom=(lector.hasNext()?lector.next():"");
			}while(nom.length()<=0);	
			nomsJugadors[jugador]=nom;
			
			//Assignar objetiu
			infoJugadors[jugador][0]=assignarObjectiu(objectius);
			System.out.println("L'objectiu és "+objectius[infoJugadors[jugador][0]]);
			//Repartir territorios
			assignarTerritoris(tauler, territoris, numJugadors, jugador);
			//Assignar tropes
			int repartir = (territoris.length%numJugadors!=0 && territoris.length%numJugadors>jugador?territoris.length/numJugadors+1:territoris.length/numJugadors);
			assignarTropes(tauler,jugador,exercitsInici-repartir,territoris);
		}	
		//TODO Decidir qui comença a jugar
		torn=aleatori(0,nomsJugadors.length);
		System.out.println("Comença el jugador "+nomsJugadors[torn]);
		
		/**
		 * Pinta el tauler com ha quedat 
		 */
		for(int territori=0;territori<tauler.length;territori++){
			System.out.println(territoris[territori]+"-"+nomsJugadors[tauler[territori][0]]+"-"+tauler[territori][1]);
		}
	}	
	
	public static int quantsExercicitsInicials(int[] exercits, int numero){
		return exercits[numero-3];
	}

	public static int assignarObjectiu(String[] objectius){
		return aleatori(0,objectius.length);
	}
	
	public static void assignarTerritoris(int[][] tauler, String[] territoris, int numJugadors, int jugadorActual){
		//Calculem exèrcits a repartir
		int repartir = (territoris.length%numJugadors!=0 && territoris.length%numJugadors>jugadorActual?territoris.length/numJugadors+1:territoris.length/numJugadors);
		
		//Diferenciem si és el primer o darrer jugador
		if (numJugadors>jugadorActual+1){
			for(int territori=0;territori<=repartir;territori++){
				int posicio=0;
				do{
					posicio=aleatori(0,territoris.length);
				}while(tauler[posicio][0]>-1);
				tauler[posicio][0]= jugadorActual;
				//Assignem un exèrcit per defecte
				tauler[posicio][1]=1;
			}
		}
		else{
			for(int territori=0;territori<tauler.length;territori++){
				if(tauler[territori][0]==-1){
					tauler[territori][0]= jugadorActual;
					//Assignem un exèrcit per defecte
					tauler[territori][1]=1;
				}	
			}
		}
		System.out.println("T'han tocat els següents territoris...");
		//Al final es mostren els territoris assignats a l'usuari
		for(int territori=0;territori<tauler.length;territori++){
			if(tauler[territori][0]==jugadorActual){
				System.out.print(territoris[territori]+"-");
			}	
		}
		System.out.println();
	}
	
	
	public static void assignarTropes(int[][] tauler, int jugadorActual, int exercits, String[] territoris){	   
		java.util.Scanner lector = new java.util.Scanner(System.in);
		for(int territori=0;territori<tauler.length;territori++){
			//Assignem un exèrcit per defecte
		   if (tauler[territori][0]==jugadorActual){
			System.out.println("Actualment tens "+tauler[territori][1]+" exèrcits en el territori "+territoris[territori]);
			System.out.println("Indica quants exèrcits vols afegir més (mínim 0 i màxim "+exercits+")");
			int mesExercits=0;
			do{
				mesExercits=(lector.hasNextInt()?lector.nextInt():0);
			}while(mesExercits<0 || mesExercits>exercits);
			tauler[territori][1]+=mesExercits;
			exercits-=mesExercits;
			if (exercits==0){
				System.out.println("Ja no et queden exèrcits per assignar.");
				break;
			}
		   }
		}
	}
	
	public static int aleatori(int inici, int darrer){
		java.util.Random rnd = new java.util.Random();
		return rnd.nextInt(darrer)+inici;
	}
}
