import static org.junit.Assert.assertFalse;

import org.junit.Test;

import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;
import nia.chapter2.echoserver.http.EchoServerHttpHandler;

public class EchoServerHttpHandlerTest {

	@Test
	public void testEchoServerHttpHandler() {
//		ByteBuf buf = Unpooled.buffer();
//		buf.writeByte(9);
		
		FullHttpRequest fhr = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,HttpMethod.POST,"/test");
		
		EmbeddedChannel channel = new EmbeddedChannel(new EchoServerHttpHandler());
		assertFalse(channel.writeInbound(fhr));
		System.out.println(((DefaultFullHttpResponse)channel.readOutbound()).content());
	}
}
