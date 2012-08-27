/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.media.server.impl.resource.bridge;

import org.mobicents.media.Component;
import org.mobicents.media.ComponentType;
import org.mobicents.media.MediaSink;
import org.mobicents.media.MediaSource;
import org.mobicents.media.server.BaseEndpointImpl;
import org.mobicents.media.server.spi.MediaType;

/**
 *
 * @author kulikov
 */
public class BridgeEndpointImpl extends BaseEndpointImpl {

    public BridgeEndpointImpl(String localName) {
        super(localName,BaseEndpointImpl.ENDPOINT_LOCAL_TO_REMOTE);
    }

    @Override
    public MediaSink getSink(MediaType media) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public MediaSource getSource(MediaType media) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void unblock() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void block() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Component getResource(MediaType mediaType, ComponentType componentType) {
    	throw new UnsupportedOperationException("Not supported yet.");
    }        
}
