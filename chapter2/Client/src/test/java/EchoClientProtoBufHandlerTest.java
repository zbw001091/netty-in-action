import org.junit.Test;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import nia.chapter2.echoclient.protobuf.EchoClientProtoBufHandler;
import nia.chapter2.echoclient.protobuf.MessageProto;

public class EchoClientProtoBufHandlerTest {

	@Test
	public void testEchoClientProtoBufHandler() {

		EmbeddedChannel channel = new EmbeddedChannel(new ProtobufEncoder(),
				new ProtobufDecoder(MessageProto.Message.getDefaultInstance()));

		MessageProto.Message message = MessageProto.Message.newBuilder().setId(111).setContent(234)
				.build();
		channel.writeOutbound(message);
		System.out.println(channel.readInbound());
	}
}
