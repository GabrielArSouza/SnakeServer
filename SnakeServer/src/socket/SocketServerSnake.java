package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import controller.EnumSnakeDirection;
import controller.Game;
import controller.SharedSnakeDirection;
import domain.Snake;

public class SocketServerSnake
{
	private DatagramSocket socket;
	private Map<InetAddress, ClientInfo> clientInfos;
	private Game game;
	
	public SocketServerSnake(Game game)
	{
		this.game = game;
		clientInfos = new HashMap<InetAddress, ClientInfo>();
	}
	
	public void init()
	{
		Thread threadUpdate = new Thread(new Runnable() {
			
			@Override
			public void run()
			{
				try
				{
					while(true)
					{
						Thread.sleep(2000);
						update();
						game.printBoardMatrix();
					}
					
				}
				
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		threadUpdate.start();
		
		try
		{
			socket = new DatagramSocket(6666);
			receiveFromClients();
		} 
		
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void update()
	{
		System.out.println("updating snakes...");
		killInactiveClients();
		game.moveSnakes();
	}
	
	private void killInactiveClients()
	{
		Iterator<Map.Entry<InetAddress, ClientInfo>> entryIterator = clientInfos.entrySet().iterator();
		
		while(entryIterator.hasNext())
		{
			ClientInfo clientInfo = entryIterator.next().getValue();
			
			if(! clientInfo.isActive() )
			{
				System.out.println("killing an inactive client...");
				game.killInactiveSnake(clientInfo.getSnake());
				entryIterator.remove();
			}
		}
	}

	public void receiveFromClients() 
	{
		try 
		{
			// TODO change buff size
			byte[] dataBuffFromClient = new byte[1024];

			while (true) 
			{
				DatagramPacket packFromClient = new DatagramPacket(dataBuffFromClient, dataBuffFromClient.length);
				socket.receive(packFromClient);
				String snakeDirectionFromClient = new String(packFromClient.getData());
				
				InetAddress clientIP = packFromClient.getAddress();
				ClientInfo clientInfo = clientInfos.get(clientIP);
				
				System.out.println("Received direction from client " + clientIP+ ": " + snakeDirectionFromClient);
				
				// new player
				if(clientInfo == null)
				{
					EnumSnakeDirection directionAsEnum = EnumSnakeDirection.getValue(snakeDirectionFromClient);
					SharedSnakeDirection sharedDirection = new SharedSnakeDirection(directionAsEnum);
					
					Snake snake = game.createSnake(sharedDirection);
					
					if(snake != null)
					{
						clientInfo = new ClientInfo(sharedDirection, snake);
						clientInfos.put(clientIP, clientInfo);
					}
					
					else
					{
						System.out.println("for some reason a snake couldn't be created");
					}
				}
				
				// player already exists: update the direction of his/her snake if it wasn't updated yet
				else
				{
					clientInfo.updateDirection(snakeDirectionFromClient);
				}
				
				
			}
		}
		
		catch (SocketException e) 
		{
			socket.close();
			e.printStackTrace();
		}
		
		catch (IOException i)
		{
			socket.close();
			i.printStackTrace();
		}
	}
}