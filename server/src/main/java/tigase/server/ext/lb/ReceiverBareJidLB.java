/*
 * ReceiverBareJidLB.java
 *
 * Tigase Jabber/XMPP Server
 * Copyright (C) 2004-2017 "Tigase, Inc." <office@tigase.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. Look for COPYING file in the top folder.
 * If not, see http://www.gnu.org/licenses/.
 */

package tigase.server.ext.lb;

import tigase.server.Packet;
import tigase.server.ext.ComponentConnection;
import tigase.server.ext.ComponentIOService;

import java.util.List;

/**
 * @author Artur Hefczyc Created Jul 9, 2011
 */
public class ReceiverBareJidLB
		implements LoadBalancerIfc {

	@Override
	public ComponentIOService selectConnection(Packet p, List<ComponentConnection> conns) {
		ComponentIOService result = null;
		int idx = Math.abs(p.getStanzaTo().getBareJID().hashCode() % conns.size());
		ComponentConnection conn = conns.get(idx);

		if ((conn.getService() != null) && conn.getService().isConnected()) {
			result = conn.getService();
		}

		return result;
	}
}