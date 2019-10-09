package desHelper;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.junit.Assert;
import org.junit.Test;

public class DESHelperTest {

	private static final String ACC = "ZZZ";
	private static final String PWD = "!@#$%";

	@Test
	public void testCode() throws DecoderException {		
		String key = this.getKey();
		String enAcc = this.encode(key, ACC);
		String enPass = this.encode(key, PWD);
		String deAcc = this.decode(key, enAcc);
		String dePass = this.decode(key, enPass);		
		Assert.assertEquals(ACC, deAcc);
		Assert.assertEquals(PWD, dePass);
	}
	
	/**
	 * 取金鑰
	 */
	private String getKey() {
		byte[] hexkey = DESHelper.generateKey();
		String key = new String( Hex.encodeHex(hexkey) );
		System.out.println("keyString:" + key);
		return key;
	}
	
	/**
	 * 編碼
	 */
	private String encode(String key, String data) throws DecoderException {
		byte[] desKey = Hex.decodeHex(key.toCharArray());		
		byte[] enDataHex = DESHelper.encode(desKey, data.getBytes());
		String enData = new String(Hex.encodeHex(enDataHex));
		System.out.println("enData:" + enData);
		return enData;
	}
	
	/**
	 * 解碼
	 */
	private String decode(String key, String data) throws DecoderException {
		byte[] desKey = Hex.decodeHex(key.toCharArray());
		byte[] deDataHex = Hex.decodeHex(data.toCharArray());
		String deData = new String(DESHelper.decode(desKey, deDataHex));
		System.out.println("deData:" + deData);
		return deData;
	}

}
