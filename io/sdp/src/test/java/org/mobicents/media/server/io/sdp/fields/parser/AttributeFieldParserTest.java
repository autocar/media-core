package org.mobicents.media.server.io.sdp.fields.parser;

import junit.framework.Assert;

import org.junit.Test;
import org.mobicents.media.server.io.sdp.SdpException;
import org.mobicents.media.server.io.sdp.attributes.GenericAttribute;

/**
 * 
 * @author Henrique Rosa (henrique.rosa@telestax.com)
 *
 */
public class AttributeFieldParserTest {
	
	private final GenericAttributeParser parser = new GenericAttributeParser();
	
	@Test
	public void testCanParse() {
		// given
		String sdp1 = "a=ice-lite\n\r";
		String sdp2 = "a=ice-pwd:P4sSw0rD\n\r";
		String sdp3 = "x=ice-lite\n\r";
		String sdp4 = "a=ice-pwd: \n\r";
		
		// when
		boolean canParseSdp1 = parser.canParse(sdp1);
		boolean canParseSdp2 = parser.canParse(sdp2);
		boolean canParseSdp3 = parser.canParse(sdp3);
		boolean canParseSdp4 = parser.canParse(sdp4);
		
		// then
		Assert.assertTrue(canParseSdp1);
		Assert.assertTrue(canParseSdp2);
		Assert.assertFalse(canParseSdp3);
		Assert.assertFalse(canParseSdp4);
	}
	
	@Test
	public void testParseSimpleAttribute() throws SdpException {
		// given
		String sdp1 = "a=ice-lite\n\r";
		
		// when
		GenericAttribute field = parser.parse(sdp1);
		
		// then
		Assert.assertNotNull(field);
		Assert.assertEquals("ice-lite", field.getKey());
		Assert.assertNull(field.getValue());		
	}

	@Test
	public void testParseValuedAttribute() throws SdpException {
		// given
		String sdp1 = "a=ice-pwd:P4sSw0Rd\n\r";
		
		// when
		GenericAttribute field = parser.parse(sdp1);
		
		// then
		Assert.assertNotNull(field);
		Assert.assertEquals("ice-pwd", field.getKey());
		Assert.assertEquals("P4sSw0Rd", field.getValue());		
	}

	@Test
	public void testParseOverwriteValuedToSimpleAttribute() throws SdpException {
		// given
		String sdp1 = "a=ice-pwd:P4sSw0Rd\n\r";
		String sdp2 = "a=ice-lite\n\r";
		
		// when
		GenericAttribute field = parser.parse(sdp1);
		parser.parse(field, sdp2);
		
		// then
		Assert.assertEquals("ice-lite", field.getKey());
		Assert.assertNull(field.getValue());
	}

	@Test
	public void testParseOverwriteSimpleToValuedAttribute() throws SdpException {
		// given
		String sdp1 = "a=ice-lite\n\r";
		String sdp2 = "a=ice-pwd:P4sSw0Rd\n\r";
		
		// when
		GenericAttribute field = parser.parse(sdp1);
		parser.parse(field, sdp2);
		
		// then
		Assert.assertEquals("ice-pwd", field.getKey());
		Assert.assertEquals("P4sSw0Rd", field.getValue());
	}

}