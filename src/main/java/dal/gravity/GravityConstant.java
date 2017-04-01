package dal.gravity;

/**
 * One strategy for GravityModel
 * Simply takes a constant in its constructor and assigns to the gravity value
 */
public class GravityConstant implements GravityModel {

	private double g;
	
	public GravityConstant(final double g)
	{
		this.g = g;
	}
	
	@Override
	public double getGravitationModel() 
	{
		return g;
	}

}
