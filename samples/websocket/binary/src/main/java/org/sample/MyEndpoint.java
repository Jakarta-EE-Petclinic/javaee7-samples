/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.sample;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Arun Gupta
 */
@ServerEndpoint("/websocket")
public class MyEndpoint {
    @OnMessage
    public void echoBinary(ByteBuffer data, Session session) {
        System.out.println("echoBinary: " + data);
        for (byte b : data.array()) {
            System.out.print(b);
        }
        try {
            session.getBasicRemote().sendBinary(data);
        } catch (IOException ex) {
            Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @WebSocketMessage
//    public void echoBinary(byte[] data, Session session) throws IOException {
//        System.out.println("echoBinary: " + data);
//        StringBuilder builder = new StringBuilder();
//        for (byte b : data) {
//            builder.append(b);
//        }
//        System.out.println(builder);
//        session.getRemote().sendBytes(ByteBuffer.wrap(data));
//    }
    
//    @WebSocketMessage
//    public void echoStream(InputStream stream, Session session) {
//        System.out.println("echoStream: " + stream);
//        byte[] b = new byte[20];
//        try {
//            int n = stream.read(b);
//            System.out.println("read " + n + " bytes");
//            System.out.println(new String(b));
//            session.getRemote().sendString(new String(b));
//        } catch (IOException ex) {
//            Logger.getLogger(MyEndpoint.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
