package mitchell_erik_ryan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class DefIntegrator
{
	public static JFrame frmDefiniteIntegrator;
	
	public static JTextField function;
	public static JTextField lowerBound;
	public static JTextField upperBound;
	public static JTextField result;
	public static JTextField deltaX;
	public static JTextField runtime;
	
	public static JScrollPane LRAM;
	public static JScrollPane RRAM;
	public static JScrollPane SIMPSONS;
	
	public static JToggleButton btnLeftRiemannSum;
	public static JToggleButton btnRightRiemannSum;
	public static JToggleButton btnSimpsonsRule;
	public static JToggleButton btnMiddleRiemannSum;
	
	public static JTextArea template1 = new JTextArea();
	public static JTextArea template2 = new JTextArea();
	public static JTextArea template3 = new JTextArea();
	
	public static long runLength;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new DefIntegrator();
					frmDefiniteIntegrator.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public DefIntegrator()
	{
		initialize();
	}

	private void initialize()
	{
		//Frames
		frmDefiniteIntegrator = new JFrame();
		frmDefiniteIntegrator.setTitle("Definite Integral Approximator");
		frmDefiniteIntegrator.getContentPane().setBackground(Color.WHITE);
		frmDefiniteIntegrator.getContentPane().setLayout(null);
		frmDefiniteIntegrator.setResizable(false);
		frmDefiniteIntegrator.setBounds(100, 100, 580, 640);
		frmDefiniteIntegrator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Panels
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 574, 612);
		frmDefiniteIntegrator.getContentPane().add(panel);
		panel.setLayout(null);
		
		//TextFields
		function = new JTextField();
		function.setFont(new Font("Tahoma", Font.PLAIN, 26));
		function.setBackground(Color.WHITE);
		function.setBounds(10, 38, 554, 32);
		panel.add(function);
		function.setColumns(10);
		
		lowerBound = new JTextField();
		lowerBound.setHorizontalAlignment(SwingConstants.RIGHT);
		lowerBound.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lowerBound.setBounds(180, 81, 92, 32);
		panel.add(lowerBound);
		lowerBound.setColumns(10);
		
		upperBound = new JTextField();
		upperBound.setHorizontalAlignment(SwingConstants.RIGHT);
		upperBound.setFont(new Font("Tahoma", Font.PLAIN, 26));
		upperBound.setColumns(10);
		upperBound.setBounds(180, 123, 92, 32);
		panel.add(upperBound);
		
		result = new JTextField();
		result.setHorizontalAlignment(SwingConstants.LEFT);
		result.setFont(new Font("Tahoma", Font.PLAIN, 26));
		result.setEditable(false);
		result.setBounds(106, 211, 458, 32);
		panel.add(result);
		result.setColumns(10);
		
		deltaX = new JTextField();
		deltaX.setFont(new Font("Tahoma", Font.BOLD, 46));
		deltaX.setText(".001");
		deltaX.setBounds(375, 81, 189, 74);
		panel.add(deltaX);
		deltaX.setColumns(10);
		
		runtime = new JTextField();
		runtime.setFont(new Font("Tahoma", Font.BOLD, 11));
		runtime.setEditable(false);
		runtime.setBounds(302, 578, 262, 26);
		panel.add(runtime);
		runtime.setColumns(10);
		
		//Buttons
		JButton calculate = new JButton("Calculate");
		calculate.addActionListener(new Listener());
		calculate.setFont(new Font("Tahoma", Font.BOLD, 22));
		calculate.setBounds(10, 166, 554, 32);
		calculate.setFocusPainted(false);
		calculate.setBackground(Color.WHITE);
		panel.add(calculate);
		
		//TextAreas
		template1.setEditable(false);
		template2.setEditable(false);
		template3.setEditable(false);
		
		//Scroll Panes
		LRAM = new JScrollPane(template1);
		LRAM.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		LRAM.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		LRAM.setBounds(10, 285, 262, 117);
		panel.add(LRAM);
		
		RRAM = new JScrollPane(template2);
		RRAM.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		RRAM.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		RRAM.setBounds(10, 444, 262, 117);
		panel.add(RRAM);
		
		SIMPSONS = new JScrollPane(template3);
		SIMPSONS.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		SIMPSONS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		SIMPSONS.setBounds(302, 285, 262, 117);
		panel.add(SIMPSONS);
		
		//Labels
		JLabel lblResult = new JLabel("Result:");
		lblResult.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblResult.setBounds(10, 209, 86, 34);
		panel.add(lblResult);
		
		JLabel lblFunction = new JLabel("Function:");
		lblFunction.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblFunction.setBounds(10, 11, 233, 26);
		panel.add(lblFunction);
		
		JLabel lblLowerBound = new JLabel("Lower Bound:");
		lblLowerBound.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLowerBound.setBounds(10, 81, 160, 32);
		panel.add(lblLowerBound);
			
		JLabel lblUpperBound = new JLabel("Upper Bound:");
		lblUpperBound.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblUpperBound.setBounds(10, 123, 160, 32);
		panel.add(lblUpperBound);	
		
		JLabel lblLRAM = new JLabel("Left Riemann Sum");
		lblLRAM.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblLRAM.setBounds(10, 254, 262, 26);
		panel.add(lblLRAM);
		
		JLabel lblRRAM = new JLabel("Right Riemann Sum");
		lblRRAM.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblRRAM.setBounds(10, 410, 262, 32);
		panel.add(lblRRAM);
		
		JLabel lblSimpsonsRule = new JLabel("Simpson's Rule");
		lblSimpsonsRule.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSimpsonsRule.setBounds(302, 254, 262, 26);
		panel.add(lblSimpsonsRule);
		
		JLabel lblDeltaX = new JLabel("\u2206X:");
		lblDeltaX.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblDeltaX.setBounds(302, 81, 63, 74);
		panel.add(lblDeltaX);
		
		JLabel lblMethodSelection = new JLabel("Method Selection");
		lblMethodSelection.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblMethodSelection.setBounds(302, 413, 262, 26);
		panel.add(lblMethodSelection);
		
		JLabel lblTimeTaken = new JLabel("Time Taken (in ms):");
		lblTimeTaken.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTimeTaken.setBounds(10, 578, 262, 26);
		panel.add(lblTimeTaken);
		
		//Toggle Buttons
		btnLeftRiemannSum = new JToggleButton("Left Riemann Sum");
		btnLeftRiemannSum.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLeftRiemannSum.setBounds(302, 444, 262, 25);
		btnLeftRiemannSum.addActionListener(new ToggleListener());
		btnLeftRiemannSum.setFocusPainted(false);
		btnLeftRiemannSum.setBackground(Color.WHITE);
		panel.add(btnLeftRiemannSum);
		
		btnRightRiemannSum = new JToggleButton("Right Riemann Sum");
		btnRightRiemannSum.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRightRiemannSum.setBounds(302, 476, 262, 25);
		btnRightRiemannSum.addActionListener(new ToggleListener());
		btnRightRiemannSum.setFocusPainted(false);
		btnRightRiemannSum.setBackground(Color.WHITE);
		panel.add(btnRightRiemannSum);
		
		btnMiddleRiemannSum = new JToggleButton("Middle Riemann Sum");
		btnMiddleRiemannSum.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMiddleRiemannSum.setSelected(true);
		btnMiddleRiemannSum.setBounds(302, 508, 262, 25);
		btnMiddleRiemannSum.addActionListener(new ToggleListener());
		btnMiddleRiemannSum.setFocusPainted(false);
		btnMiddleRiemannSum.setBackground(Color.WHITE);
		panel.add(btnMiddleRiemannSum);
		
		btnSimpsonsRule = new JToggleButton("Simpson's Rule");
		btnSimpsonsRule.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSimpsonsRule.setBounds(302, 540, 262, 25);
		btnSimpsonsRule.addActionListener(new ToggleListener());
		btnSimpsonsRule.setFocusPainted(false);
		btnSimpsonsRule.setBackground(Color.WHITE);
		panel.add(btnSimpsonsRule);	
	}
}