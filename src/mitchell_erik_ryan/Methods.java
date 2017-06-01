package mitchell_erik_ryan;

import static mitchell_erik_ryan.DefIntegrator.*;
import static mitchell_erik_ryan.Listener.prepFunction;
import static mitchell_erik_ryan.Listener.runTime;
import static mitchell_erik_ryan.Listener.width;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Methods
{
	public static BigDecimal leftRiemannSum(String function, double lower, double upper) throws ScriptException
	{
		BigDecimal total = new BigDecimal(0 + "");
		BigDecimal x = new BigDecimal(lower + "");
		BigDecimal up = new BigDecimal(upper + "");
		
		template1.setText("Begin\n");
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
			
		function = prepFunction(function);
		for(; x.compareTo(up) < 0;)
		{
			engine.put("x", x);
			total = total.add(new BigDecimal(((Number) engine.eval(function)).doubleValue() + ""));
			x = x.add(width);
			template1.append(total.doubleValue()/(1 / (width.doubleValue())) + "\n");
		}
		BigDecimal result = total.multiply(width);
		updateTime(lower, upper);
		return result;
	}

	public static BigDecimal rightRiemannSum(String function, double lower, double upper) throws ScriptException
	{
		BigDecimal total = new BigDecimal(0 + "");
		BigDecimal x = new BigDecimal(lower + "").add(width);
		BigDecimal up = new BigDecimal(upper + "");
		
		template2.setText("Begin\n");
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
			
		function = prepFunction(function);
		for(; x.compareTo(up) <= 0;)
		{
			engine.put("x", x);
			total = total.add(new BigDecimal(((Number) engine.eval(function)).doubleValue() + ""));
			x = x.add(width);
			template2.append(total.doubleValue()/(1 / (width.doubleValue())) + "\n");
		}
		BigDecimal result = total.multiply(width);
		updateTime(lower, upper);
		return result;
	}

	public static BigDecimal middleRiemannSum(String function, double lower, double upper) throws ScriptException
	{
		BigDecimal result = (leftRiemannSum(function, lower, upper).add(rightRiemannSum(function, lower, upper))).divide(new BigDecimal(2));
		updateTime(lower, upper);
		return result;
	}
	
	public static double simpsonsRule(String function, double lower, double upper) throws ScriptException
	{
		BigDecimal total = new BigDecimal(0);
		BigDecimal x = new BigDecimal(lower).add(width);
		BigDecimal up = new BigDecimal(upper);
		int counter = 1;
		
		template3.setText("");
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine engine = mgr.getEngineByName("JavaScript");
		
		template3.append(total.doubleValue()/(1 / (width.doubleValue()))/3 + "\n");
		function = prepFunction(function);
		engine.put("x" , lower);
		total = total.add(new BigDecimal(((Number) engine.eval(function)).doubleValue()));
		template3.append(total.doubleValue()/(1 / (width.doubleValue()))/3 + "\n");
		
		for(; x.compareTo(up) < 0;)
		{
			engine.put("x", x);
			if(counter % 2 == 1)
				total = total.add(new BigDecimal(4 * ((Number) engine.eval(function)).doubleValue()));
			else
				total = total.add(new BigDecimal(2 * ((Number) engine.eval(function)).doubleValue()));
			counter++;
			x = x.add(width);
			template3.append(total.doubleValue()/(1 / (width.doubleValue()))/3 + "\n");
		}	
		engine.put("x", upper);
		total = total.add(new BigDecimal((double) engine.eval(function)));
		double result = (total.doubleValue()) / 3.0 * (width.doubleValue());
		template3.append(total.doubleValue()/(1 / (width.doubleValue())) / 3 + "\n");
		updateTime(lower, upper);
		return result;
	}
	
	public static void updateTime(double lower, double upper)
	{
		double iter = (upper - lower) / width.doubleValue();
		runtime.setText(System.currentTimeMillis() - runTime + " milliseconds for " + Math.ceil(iter) + " iterations");
	}
}