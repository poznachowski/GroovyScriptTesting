package pl.poznachowski.groovyscripttesting;

import org.mule.DefaultMuleMessage;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.config.ConfigurationException;
import org.mule.api.context.MuleContextFactory;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.context.DefaultMuleContextFactory;

public class TestMuleMessage {

	public static MuleMessage withPayload(Object payload) {
		
		MuleContextFactory contextFactory = new DefaultMuleContextFactory();
		MuleContext muleContext = null;
		try {
			muleContext = contextFactory.createMuleContext();
		} catch (InitialisationException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		return new DefaultMuleMessage(payload, muleContext);
	}
}
