package cn.wizool.android.com.google.code.or;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.wizool.android.com.google.code.or.binlog.BinlogEventListener;
import cn.wizool.android.com.google.code.or.binlog.BinlogParser;
import cn.wizool.android.com.google.code.or.binlog.BinlogParserListener;
import cn.wizool.android.com.google.code.or.binlog.impl.ReplicationBasedBinlogParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.DeleteRowsEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.DeleteRowsEventV2Parser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.FormatDescriptionEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.IncidentEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.IntvarEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.QueryEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.RandEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.RotateEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.StopEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.TableMapEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.UpdateRowsEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.UpdateRowsEventV2Parser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.UserVarEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.WriteRowsEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.WriteRowsEventV2Parser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.XidEventParser;
import cn.wizool.android.com.google.code.or.binlog.impl.parser.GtidEventParser;
import cn.wizool.android.com.google.code.or.common.glossary.column.StringColumn;
import cn.wizool.android.com.google.code.or.io.impl.SocketFactoryImpl;
import cn.wizool.android.com.google.code.or.net.Packet;
import cn.wizool.android.com.google.code.or.net.Transport;
import cn.wizool.android.com.google.code.or.net.TransportException;
import cn.wizool.android.com.google.code.or.net.impl.AuthenticatorImpl;
import cn.wizool.android.com.google.code.or.net.impl.TransportImpl;
import cn.wizool.android.com.google.code.or.net.impl.packet.ErrorPacket;
import cn.wizool.android.com.google.code.or.net.impl.packet.command.ComBinlogDumpPacket;


/**
 * 
 * @author Jingqi Xu
 * @author darnaut
 */
public class OpenReplicator {
	//
	 private static final Logger logger = LoggerFactory.getLogger(OpenReplicator.class);
	protected int port;
	protected String host;
	protected String user;
	protected String password;
	protected int serverId ;
	protected String binlogFileName;
	protected long binlogPosition ;
	protected String encoding = "utf-8";
	protected int level1BufferSize = 1024 * 1024;
	protected int level2BufferSize = 8 * 1024 * 1024;
	protected int socketReceiveBufferSize = 512 * 1024;
	
	//
	protected Transport transport;
	protected BinlogParser binlogParser;
	protected BinlogEventListener binlogEventListener;
	protected final AtomicBoolean running = new AtomicBoolean(false);
//	private volatile boolean autoRestart = true;
	
	/**
	 * 
	 */
	public boolean isRunning() {
		return this.running.get();
	}
	
	public void start() throws Exception {
		//
		if(!this.running.compareAndSet(false, true)) {
			return;
		}
		
		//
		if(this.transport == null) this.transport = getDefaultTransport();
		this.transport.connect(this.host, this.port);
		
		//
		dumpBinlog();
		
		//
		if(this.binlogParser == null) this.binlogParser = getDefaultBinlogParser();
		this.binlogParser.setEventListener(this.binlogEventListener);
		this.binlogParser.addParserListener(new BinlogParserListener.Adapter() {
			@Override
			public void onStop(BinlogParser parser) {
				stopQuietly(0, TimeUnit.MILLISECONDS);
			}
		});
		this.binlogParser.start();
	}

	public void stop(long timeout, TimeUnit unit) throws Exception {
		//
		if(!this.running.compareAndSet(true, false)) {
			return;
		}
		
		//
		this.transport.disconnect();
		this.binlogParser.stop(timeout, unit);
	}
	
	public void stopQuietly(long timeout, TimeUnit unit) {
		 stopQuietly(timeout, unit);
	       /* if(autoRestart){
	            try {
	                TimeUnit.SECONDS.sleep(10);
	                logger.error("Restart OpenReplicator");
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }*/
	}
	
	/**
	 * 
	 */
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	
	public long getBinlogPosition() {
		return binlogPosition;
	}

	public void setBinlogPosition(long binlogPosition) {
		this.binlogPosition = binlogPosition;
	}
	
	public String getBinlogFileName() {
		return binlogFileName;
	}

	public void setBinlogFileName(String binlogFileName) {
		this.binlogFileName = binlogFileName;
	}
	
	public int getLevel1BufferSize() {
		return level1BufferSize;
	}

	public void setLevel1BufferSize(int level1BufferSize) {
		this.level1BufferSize = level1BufferSize;
	}

	public int getLevel2BufferSize() {
		return level2BufferSize;
	}

	public void setLevel2BufferSize(int level2BufferSize) {
		this.level2BufferSize = level2BufferSize;
	}
	
	public int getSocketReceiveBufferSize() {
		return socketReceiveBufferSize;
	}

	public void setSocketReceiveBufferSize(int socketReceiveBufferSize) {
		this.socketReceiveBufferSize = socketReceiveBufferSize;
	}

	/**
	 * 
	 */
	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
	
	public BinlogParser getBinlogParser() {
		return binlogParser;
	}

	public void setBinlogParser(BinlogParser parser) {
		this.binlogParser = parser;
	}
	
	public BinlogEventListener getBinlogEventListener() {
		return binlogEventListener;
	}

	public void setBinlogEventListener(BinlogEventListener listener) {
		this.binlogEventListener = listener;
	}

	/**
	 * 
	 */
	protected void dumpBinlog() throws Exception {
		//
		final ComBinlogDumpPacket command = new ComBinlogDumpPacket();
		command.setBinlogFlag(0);
		command.setServerId(this.serverId);
		command.setBinlogPosition(this.binlogPosition);
		command.setBinlogFileName(StringColumn.valueOf(this.binlogFileName.getBytes(this.encoding)));
		this.transport.getOutputStream().writePacket(command);
		this.transport.getOutputStream().flush();
		
		//
		final Packet packet = this.transport.getInputStream().readPacket();
		if(packet.getPacketBody()[0] == ErrorPacket.PACKET_MARKER) {
			final ErrorPacket error = ErrorPacket.valueOf(packet);
			throw new TransportException(error);
		} 
	}
	
	protected Transport getDefaultTransport() throws Exception {
		//
		final TransportImpl r = new TransportImpl();
		r.setLevel1BufferSize(this.level1BufferSize);
		r.setLevel2BufferSize(this.level2BufferSize);
		
		//
		final AuthenticatorImpl authenticator = new AuthenticatorImpl();
		authenticator.setUser(this.user);
		authenticator.setPassword(this.password);
		authenticator.setEncoding(this.encoding);
		r.setAuthenticator(authenticator);
		
		//
		final SocketFactoryImpl socketFactory = new SocketFactoryImpl();
		socketFactory.setKeepAlive(true);
		socketFactory.setTcpNoDelay(false);
		socketFactory.setReceiveBufferSize(this.socketReceiveBufferSize);
		r.setSocketFactory(socketFactory);
		return r;
	}
	
	protected ReplicationBasedBinlogParser getDefaultBinlogParser() throws Exception {
		//
		final ReplicationBasedBinlogParser r = new ReplicationBasedBinlogParser();
		r.registerEventParser(new StopEventParser());
		r.registerEventParser(new RotateEventParser());
		r.registerEventParser(new IntvarEventParser());
		r.registerEventParser(new XidEventParser());
		r.registerEventParser(new RandEventParser());
		r.registerEventParser(new QueryEventParser());
		r.registerEventParser(new UserVarEventParser());
		r.registerEventParser(new IncidentEventParser());
		r.registerEventParser(new TableMapEventParser());
		r.registerEventParser(new WriteRowsEventParser());
		r.registerEventParser(new UpdateRowsEventParser());
		r.registerEventParser(new DeleteRowsEventParser());
		r.registerEventParser(new WriteRowsEventV2Parser());
		r.registerEventParser(new UpdateRowsEventV2Parser());
		r.registerEventParser(new DeleteRowsEventV2Parser());
		r.registerEventParser(new FormatDescriptionEventParser());
		r.registerEventParser(new GtidEventParser());
		
		//
		r.setTransport(this.transport);
		r.setBinlogFileName(this.binlogFileName);
		return r;
	}
}
