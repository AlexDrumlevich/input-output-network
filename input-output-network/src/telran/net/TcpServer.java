package telran.net;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
public class TcpServer implements Runnable {
	private int port;
	private ApplProtocol protocol;
	private ServerSocket serverSocket;
	
	private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	
	public TcpServer(int port, ApplProtocol protocol) throws IOException {
		this.port = port;
		this.protocol = protocol;
		serverSocket = new ServerSocket(port);
	}

	@Override
	public void run() {
		System.out.println("Server is listening on port " + port);
		try {
			while(!executorService.isShutdown()) {
				Socket socket = serverSocket.accept();
				TcpClientServer clientServer = new TcpClientServer(socket, protocol);
				executorService.execute(clientServer);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		executorService.shutdown();
	}

}
