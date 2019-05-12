package nia.chapter2.echoserver.protobuf;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.net.InetSocketAddress;

/**
 * Listing 2.2 EchoServer class
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class EchoServerProtoBuf {
    private final int port;

    public EchoServerProtoBuf(int port) {
        this.port = port;
    }

    public static void main(String[] args)
        throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServerProtoBuf.class.getSimpleName() +
                " <port>"
            );
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServerProtoBuf(port).start();
    }

    public void start() throws Exception {
        final EchoServerProtoBufHandler serverHandler = new EchoServerProtoBufHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));
                        ch.pipeline().addLast(new ProtobufEncoder());
                        ch.pipeline().addLast(serverHandler);
                    }
                });

            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServerProtoBuf.class.getName() +
                " started and listening for connections on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
