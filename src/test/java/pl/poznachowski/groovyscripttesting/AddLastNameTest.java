package pl.poznachowski.groovyscripttesting;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;
import org.mule.api.MuleMessage;

public class AddLastNameTest {

	
	@Test
	public void test() throws Exception {
		
		Binding binding = new Binding();
		binding.setVariable("firstName", "Greg");
		binding.setVariable("message", TestMuleMessage.withPayload("whatever"));
		
		GroovyShell shell = new GroovyShell(binding);
		shell.evaluate(getFile("/scripts/GroovyScriptTesting/AddLastName.groovy"));
		MuleMessage message = (MuleMessage) binding.getVariable("message");
		Assert.assertTrue(message.getInvocationProperty("fullName").toString().equals("Greg Poznachowski"));
		
	}
	
	private File getFile(String pathToFile) throws URISyntaxException, FileNotFoundException {
		URL url = AddLastNameTest.class.getResource(pathToFile);
		if (url == null) {
			throw new FileNotFoundException("Couldn't find: " + pathToFile);
		}
		return new File(url.toURI());
	}
}
