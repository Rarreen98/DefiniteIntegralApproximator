package mitchell_erik_ryan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import static mitchell_erik_ryan.DefIntegrator.*;

public class ToggleListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source instanceof JToggleButton)
		{
			JToggleButton button = (JToggleButton) e.getSource();
			if(button == btnLeftRiemannSum)
			{
				btnLeftRiemannSum.setSelected(true);
				btnRightRiemannSum.setSelected(false);
				btnMiddleRiemannSum.setSelected(false);
				btnSimpsonsRule.setSelected(false);
			}
			else if(button == btnRightRiemannSum)
			{
				btnRightRiemannSum.setSelected(true);
				btnLeftRiemannSum.setSelected(false);
				btnMiddleRiemannSum.setSelected(false);
				btnSimpsonsRule.setSelected(false);
			}
			else if(button == btnMiddleRiemannSum)
			{
				btnMiddleRiemannSum.setSelected(true);
				btnRightRiemannSum.setSelected(false);
				btnLeftRiemannSum.setSelected(false);
				btnSimpsonsRule.setSelected(false);
			}
			else if(button == btnSimpsonsRule)
			{
				btnSimpsonsRule.setSelected(true);
				btnLeftRiemannSum.setSelected(false);
				btnRightRiemannSum.setSelected(false);
				btnMiddleRiemannSum.setSelected(false);
			}
		}		
	}
}