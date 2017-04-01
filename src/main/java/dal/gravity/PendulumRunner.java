package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

	private static final double EARTH_GRAVITY = 9.80665;
	private static final double JUPITER_GRAVITY = 24.79;
	
    public static void main (String [] args) {
		NumberFormat nf = NumberFormat.getInstance ();
		nf.setMaximumFractionDigits (3);
	
		double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
		double sLen = 10, pMass = 10, theta0 = Math.PI/30;
		
		RegularPendulum rp = 
			new RegularPendulum (sLen, pMass, theta0, new GravityConstant(EARTH_GRAVITY), delta);
		SimplePendulum sp = 
			new SimplePendulum (sLen, pMass, theta0, new GravityConstant(EARTH_GRAVITY));
	
		// print out difference in displacement in 1 second intervals
		// for 20 seconds
		int iterations = (int) (1/delta);
		System.out.println ("analytical vs. numerical displacement (simple, regular)");
		System.out.println("\nOn planet Earth");
		for (int second = 1; second <= 20; second++) {
		    for (int i = 0; i < iterations; i++) rp.step ();
		    System.out.println ("t=" + second + "s: \t" + 
					nf.format (Math.toDegrees (sp.getTheta (second))) 
					+ "\t" +
					nf.format (Math.toDegrees (rp.getLastTheta ())));
		}
		    
		System.out.println("\nOn planet Jupiter");
		sp.setModel(new GravityConstant(JUPITER_GRAVITY));
		rp.setModel(new GravityConstant(JUPITER_GRAVITY));
		
		for (int second = 1; second <= 20; second++) {
		    for (int i = 0; i < iterations; i++) rp.step ();
		    System.out.println ("t=" + second + "s: \t" + 
					nf.format (Math.toDegrees (sp.getTheta (second))) 
					+ "\t" +
					nf.format (Math.toDegrees (rp.getLastTheta ())));
		}
    }
}

