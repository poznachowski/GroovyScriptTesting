package pl.poznachowski.groovyscripttesting;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mule.api.MuleMessage;

@RunWith(JUnitParamsRunner.class)
public class CalculateSquareNumberTest {

	private static final String PAYLOAD = "payload";

	@Test
	@Parameters(method = "numbersAndSquares")
	public void test(int number, int square) throws Exception {
		
		Binding binding = new Binding();
		binding.setVariable("number", number);
		binding.setVariable("message", TestMuleMessage.withPayload(PAYLOAD));
		
		GroovyShell shell = new GroovyShell(binding);
		shell.evaluate(getFile("/scripts/GroovyScriptTesting/CalculateSquareNumber.groovy"));
		MuleMessage message = (MuleMessage) binding.getVariable("message");
		
		assertThat((Integer)message.getInvocationProperty("squareNumber"), is(square));
	}
	
	@SuppressWarnings("unused")
	private Object[] numbersAndSquares() {
		return $(
             $(3, 9),
             $(5, 25),
             $(10, 100),
             $(12, 144)
        );
	}
	
	private File getFile(String pathToFile) throws URISyntaxException, FileNotFoundException {
		URL url = CalculateSquareNumberTest.class.getResource(pathToFile);
		if (url == null) {
			throw new FileNotFoundException("Couldn't find: " + pathToFile);
		}
		return new File(url.toURI());
	}
}
