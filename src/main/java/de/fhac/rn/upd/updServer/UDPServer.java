package de.fhac.rn.upd.updServer;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {



	public static void main(String args[]) throws Exception {

		int portserver = 9876 ;
		//static int port = args[0]
		System.out.println("Server is on, waiting for message from the client");
		//Start des UDP-Servers auf einem freien Port
		DatagramSocket serverSocket = new DatagramSocket(portserver);
		//Paketgr��e 1024 Bytes
		byte[] receive = new byte[1024];
		byte[] send = new byte[1024] ;
		while(true) {
			//Einlesen eines DatagramPackets
			DatagramPacket receivePacket = new DatagramPacket(receive, receive.length);
			serverSocket.receive(receivePacket);
			//�bermittelter String
			String sentence = new String( receivePacket.getData());
			System.out.println("received: " + sentence);
			
			//�bermittelter Client-Port
			int port = receivePacket.getPort();
			//�bermittelter Client-Adresse
			InetAddress IPAddress = receivePacket.getAddress();
			//Den �bermittelten Text in Gro�buchstaben
			String capitalizedSentence = sentence.toUpperCase();
			send = capitalizedSentence.getBytes();
			//Zur�ck schicken
			DatagramPacket sendPacket = new DatagramPacket(send, send.length, IPAddress, port);
			serverSocket.send(sendPacket);
		}
		
		//not 'exit' function just yet; server socket never closing : serversocket.close();
	}
}
