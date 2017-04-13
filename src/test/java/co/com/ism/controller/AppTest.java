package co.com.ism.controller;

import co.com.ism.model.SimpsonIntegrationPart;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testGammaEntero() {
    	assertEquals(gamma(5),24.0);
		
    }
    
    public void testGammFraccion() {
    	assertEquals(gamma(9/2.0), 11.6317, 4);
    }
    
    public void testResult(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();
		iterator.calcularParte3();
		iterator.calcularFX();
		iterator.calcularMultiplier();
		iterator.calcularTerms();
		assertEquals(iterator.calcularResult(), 0.35006, 5);		
    }
    
    public void testParte1(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();		
		assertEquals(iterator.getParte1().get(1), 1.00134, 5);
		
    }
    
    public void testParte2(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();		
		assertEquals(iterator.getParte2().get(1), 0.9933, 4);
		
    }
    
    public void testParte3(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();
		iterator.calcularParte3();		
		assertEquals(iterator.getParte3(), 0.388035, 6);		
    }
    
    public void testTerms(){
    	SimpsonIntegrationPart iterator = new SimpsonIntegrationPart((float) 1.1, 9, 10);
    	iterator.calcularXi();
		iterator.calcularParte1();
		iterator.calcularParte2();
		iterator.calcularParte3();
		iterator.calcularFX();
		iterator.calcularMultiplier();
		iterator.calcularTerms();
		assertEquals(iterator.getTerms().get(1), 0.05653, 5);		
    }
    
    private double gamma(double num){
    	double fact = 0;
		if (num % 1 == 0){
			fact = factorial(num - 1, 1);
			return fact;
		}
		else {
			fact = factorial(num - 1, 1/2.0);
			return fact * Math.sqrt(Math.PI);
		} 
	}
    
    private double factorial(double num, double limit){
    	if (num <= limit){
    		return limit;
 		}
 		else{
 			return num * factorial(num - 1, limit); 			
 		}
    }
}
