import org.junit.Test;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import nia.chapter2.echoserver.protobuf.EchoServerProtoBufHandler;
import nia.chapter2.echoserver.protobuf.MessageProto;

public class EchoServerProtoBufHandlerTest {

	@Test
	public void testEchoServerProtoBufHandler() {

		EmbeddedChannel channel = new EmbeddedChannel(new ProtobufEncoder(),
				new ProtobufDecoder(MessageProto.Message.getDefaultInstance()),
				new EchoServerProtoBufHandler());

		MessageProto.Message message = MessageProto.Message.newBuilder().setId(111).setContent(234)
				.build();
		channel.writeOutbound(message);
		System.out.println(channel.readOutbound());
	}
}
