package view;
import java.util.Random;
import java.util.concurrent.Semaphore;
import controller.ThreadCozinha;

public class Principal {

	public static void main(String[] args) {
		Random random = new Random();
		double tempo;
		String nome;
		Semaphore semaforoCozinha = new Semaphore(5);
		Semaphore semaforoEntrega = new Semaphore(1);
		
		for(int idThread=0;idThread<5;idThread++){
			switch(idThread%2){
			case 0:
				nome = "Lasanha a Bolonhesa";
				tempo = random.nextInt(600)+600;
				Thread t0 = new ThreadCozinha(idThread, nome, tempo,semaforoCozinha,semaforoEntrega);
				t0.start();
				break;
			case 1:
				nome = "Sopa de Cebola";
				tempo = random.nextInt(500)+300;
				Thread t1 = new ThreadCozinha(idThread,nome, tempo,semaforoCozinha,semaforoEntrega);
				t1.start();
				break;
			}
		}
	}

}
