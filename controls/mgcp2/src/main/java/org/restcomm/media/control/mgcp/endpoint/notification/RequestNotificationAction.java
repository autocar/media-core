/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2017, Telestax Inc and individual contributors
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

package org.restcomm.media.control.mgcp.endpoint.notification;

import static org.restcomm.media.control.mgcp.endpoint.notification.NotificationCenterTransitionParameter.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.apache.log4j.Logger;
import org.restcomm.media.control.mgcp.command.param.NotifiedEntity;
import org.restcomm.media.control.mgcp.pkg.MgcpRequestedEvent;
import org.restcomm.media.control.mgcp.signal.BriefSignal;
import org.restcomm.media.control.mgcp.signal.MgcpSignal;
import org.restcomm.media.control.mgcp.signal.TimeoutSignal;

/**
 * Overrides information about Requested Notification in the endpoint.
 * 
 * <p>
 * Input parameters:
 * <ul>
 * <li>REQUEST_IDENTIFIER</li>
 * <li>NOTIFIED_ENTITY</li>
 * <li>REQUESTED_EVENTS</li>
 * <li>REQUESTED_SIGNALS</li>
 * </ul>
 * </p>
 * <p>
 * Output parameters:
 * <ul>
 * <li>n/a</li>
 * </ul>
 * </p>
 * 
 * @author Henrique Rosa (henrique.rosa@telestax.com)
 *
 */
class RequestNotificationAction extends NotificationCenterAction {

    private static final Logger log = Logger.getLogger(RequestNotificationAction.class);

    static final RequestNotificationAction INSTANCE = new RequestNotificationAction();

    RequestNotificationAction() {
        super();
    }

    @Override
    public void execute(NotificationCenterState from, NotificationCenterState to, NotificationCenterEvent event, NotificationCenterTransitionContext context, NotificationCenterFsm stateMachine) {
        final NotificationCenterContext globalContext = stateMachine.getContext();
        final NotifiedEntity notifiedEntity = context.get(NOTIFIED_ENTITY, NotifiedEntity.class);
        final MgcpRequestedEvent[] events = context.get(REQUESTED_EVENTS, MgcpRequestedEvent[].class);
        final MgcpSignal<?>[] signals = context.get(REQUESTED_SIGNALS, MgcpSignal[].class);
        final String requestId = context.get(REQUEST_IDENTIFIER, String.class);

        // Update Request ID
        globalContext.setRequestId(requestId);

        // Update Notified Entity if defined. Otherwise, the current one will be maintained.
        if (notifiedEntity != null) {
            globalContext.setNotifiedEntity(notifiedEntity);
        }

        // Update Requested Events. Will be cleaned if no events are requested.
        final Set<MgcpRequestedEvent> eventSet = globalContext.getRequestedEvents();
        eventSet.clear();
        if (events.length > 0) {
            Collections.addAll(eventSet, events);
        }

        // Update Requested Signals.
        Set<TimeoutSignal> timeoutSignalSet = globalContext.getTimeoutSignals();
        TimeoutSignal[] ongoingTimeoutSignals = new TimeoutSignal[timeoutSignalSet.size()];
        timeoutSignalSet.toArray(ongoingTimeoutSignals);
        timeoutSignalSet.clear();

        Queue<BriefSignal> briefSignalSet = globalContext.getBriefSignals();
        briefSignalSet.clear();

        if (signals.length > 0) {
            Set<TimeoutSignal> timeoutSignals = new HashSet<>(signals.length);
            Set<BriefSignal> briefSignals = new HashSet<>(signals.length);

            for (MgcpSignal<?> signal : signals) {
                if (signal instanceof TimeoutSignal) {
                    timeoutSignals.add((TimeoutSignal) signal);
                } else if (signal instanceof BriefSignal) {
                    briefSignals.add((BriefSignal) signal);
                }
            }
        }

        // Log action
        logAction(globalContext);
    }

    private void logAction(NotificationCenterContext context) {
        if (log.isInfoEnabled()) {
            final StringBuilder builder = new StringBuilder();
            final String endpointId = context.getEndpoint().getEndpointId().toString();
            final String requestId = context.getRequestId();

            builder.append("Endpoint ").append(endpointId).append(" is executing request ").append(requestId);

            if (log.isDebugEnabled()) {
                builder.append(System.lineSeparator()).append(context.toString());
                log.debug(builder.toString());
            } else {
                log.info(builder.toString());
            }
        }
    }

}
