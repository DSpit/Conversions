

package com.dSpit.conversions;

/**
 *
 * @author David Boivin (Spit)
 */
public class CANtoUSA implements Conversions {

	@Override
	public double convert(double input) {
		return input * USA_DOLLAR;
	}

}
