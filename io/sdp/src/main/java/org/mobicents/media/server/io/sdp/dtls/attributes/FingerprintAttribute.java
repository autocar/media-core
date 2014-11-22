package org.mobicents.media.server.io.sdp.dtls.attributes;

import org.mobicents.media.server.io.sdp.AttributeField;

/**
 * 
 * 
 * @author Henrique Rosa (henrique.rosa@telestax.com)
 *
 */
public class FingerprintAttribute extends AttributeField {

	private static final String NAME = "fingerprint";
	
	public FingerprintAttribute() {
		super(true);
		this.key = NAME;
	}

}
