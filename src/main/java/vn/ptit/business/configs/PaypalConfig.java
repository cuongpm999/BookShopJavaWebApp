package vn.ptit.business.configs;

import java.util.HashMap;
import java.util.Map;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

public class PaypalConfig {
	public Map paypalSdkConfig() {
		Map configMap = new HashMap<>();
		configMap.put("mode", "sandbox");
		return configMap;
	}
	
	public OAuthTokenCredential oAuthTokenCredential() {
		return new OAuthTokenCredential("ATt_uS_7TlE3LUQ4JBmuA1JkEwv-viD1LZnf8bZBAlZ5mNyTU4ejkt7F0fbq_9d8L-Y98fdOjgcOGLgG", "ECt4EcVYMWpkmn1CXZzSBwd0KAaUo6_gQW8wqNedm7Q2tD4Ja4USCXkCCIZMhbCljZVSXCq5uPOGlCC2", paypalSdkConfig());
	}
	
	public APIContext apiContext() throws PayPalRESTException {
		APIContext context = new APIContext(oAuthTokenCredential().getAccessToken());
		context.setConfigurationMap(paypalSdkConfig());
		return context;
	}
}
