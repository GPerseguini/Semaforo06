package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {
	private double tempo;
	private String nome;
	private int id;
	private Semaphore semaforoCozinha;
	private Semaphore semaforoEntrega;
	
	public ThreadCozinha (int id, String nome,double tempo,Semaphore semaforoCozinha, Semaphore semaforoEntrega){
		this.id = id;
		this.nome = nome;
		this.tempo = tempo;
		this.semaforoCozinha = semaforoCozinha;
		this.semaforoEntrega = semaforoEntrega;
	}
	
	public void run(){
		try{
			semaforoCozinha.acquire();
			cozinhar();
			semaforoEntrega.acquire();
			entregar();
		} catch (Exception e){
			
		} finally {
			semaforoEntrega.release();
			semaforoCozinha.release();
		}
	}
	public void cozinhar() {
		double aux=0;
		System.out.println("O prato #"+id+ " "+nome+" acabou de começar");
		while (aux < tempo)
		try {
			aux+= 100;
			Thread.sleep(100);
			if (aux > tempo){
				aux = tempo;
			}
			System.out.println("O prato #"+id+ " "+nome+" está "+((int)((aux/tempo)*100))+" pronto ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void entregar() {
		try {
			Thread.sleep(500);
			System.out.println("O prato #"+id+ " "+nome+" foi entregue");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
