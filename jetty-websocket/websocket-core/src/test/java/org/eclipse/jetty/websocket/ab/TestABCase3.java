package org.eclipse.jetty.websocket.ab;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jetty.websocket.api.ProtocolException;
import org.eclipse.jetty.websocket.generator.Generator;
import org.eclipse.jetty.websocket.protocol.CloseInfo;
import org.eclipse.jetty.websocket.protocol.UnitGenerator;
import org.eclipse.jetty.websocket.protocol.WebSocketFrame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class TestABCase3
{

    @Parameters
    public static Collection<WebSocketFrame[]> data()
    {
        List<WebSocketFrame[]> data = new ArrayList<>();
        // @formatter:off
        data.add(new WebSocketFrame[]
                { WebSocketFrame.ping().setFin(false) });
        data.add(new WebSocketFrame[]
                { WebSocketFrame.ping().setRsv1(true) });
        data.add(new WebSocketFrame[]
                { WebSocketFrame.ping().setRsv2(true) });
        data.add(new WebSocketFrame[]
                { WebSocketFrame.ping().setRsv3(true) });
        data.add(new WebSocketFrame[]
                { WebSocketFrame.pong().setFin(false) });
        data.add(new WebSocketFrame[]
                { WebSocketFrame.ping().setRsv1(true) });
        data.add(new WebSocketFrame[]
                { WebSocketFrame.pong().setRsv2(true) });
        data.add(new WebSocketFrame[]
                { WebSocketFrame.pong().setRsv3(true) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setFin(false) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setRsv1(true) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setRsv2(true) });
        data.add(new WebSocketFrame[]
                { new CloseInfo().asFrame().setRsv3(true) });
        // @formatter:on
        return data;
    }

    private WebSocketFrame invalidFrame;

    public TestABCase3(WebSocketFrame invalidFrame)
    {
        this.invalidFrame = invalidFrame;
    }

    @Test(expected = ProtocolException.class)
    public void testGenerateInvalidControlFrame()
    {
        Generator generator = new UnitGenerator();

        generator.generate(invalidFrame);
    }


}