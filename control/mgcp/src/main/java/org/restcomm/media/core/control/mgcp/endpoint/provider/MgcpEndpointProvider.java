/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2016, Telestax Inc and individual contributors
 * by the @authors tag. 
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

package org.restcomm.media.core.control.mgcp.endpoint.provider;

import org.restcomm.media.core.control.mgcp.endpoint.MgcpEndpoint;

/**
 * @author Henrique Rosa (henrique.rosa@telestax.com)
 *
 */
public interface MgcpEndpointProvider<T extends MgcpEndpoint> {

    /**
     * Gets the name space associated with the provided endpoints.
     * 
     * @return The name space.
     */
    String getNamespace();

    /**
     * Gets the domain of the media gateway.
     * 
     * @return The domain of the media gateway
     */
    String getDomain();

    /**
     * Provides a new endpoint.
     * 
     * @return The newly created endpoint
     */
    T provide();

}
