package jgds.tutorial;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;

import mathLib.plot.MatlabChart;
import mathLib.util.CustomJFileChooser;
import javax.swing.JRadioButton;

public class OptimalBendGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField start;
	private JTextField end;
	private JTextField rate;
	
	MatlabChart fig ;
	double xStart ;
	double xEnd ;
	int numPoints ;
	double[] x ;
	double[] y ;
	private JTextField lightTextField;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptimalBendGUI frame = new OptimalBendGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OptimalBendGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OptimalBendGUI.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Paste.png")));
		setTitle("Optimal Bend Designer");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{118, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel plotPanel = new JPanel();
		plotPanel.setBorder(new TitledBorder(null, "plot", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_plotPanel = new GridBagConstraints();
		gbc_plotPanel.fill = GridBagConstraints.BOTH;
		gbc_plotPanel.gridx = 0;
		gbc_plotPanel.gridy = 1;
		contentPane.add(plotPanel, gbc_plotPanel);
		plotPanel.setLayout(new BorderLayout(0, 0));
		
		fig = new MatlabChart() ;
		fig.plot(new double[0], new double[0]);
		fig.RenderPlot();
		fig.setXLabel("X (um)");
		fig.setYLabel("Y (um)");
		ChartPanel chartPanel = new ChartPanel(fig.getChart());
		plotPanel.add(chartPanel) ;
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Setup the function", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(5, 5, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblLightData = new JLabel("save GDS to:");
		GridBagConstraints gbc_lblLightData = new GridBagConstraints();
		gbc_lblLightData.anchor = GridBagConstraints.WEST;
		gbc_lblLightData.insets = new Insets(2, 5, 0, 5);
		gbc_lblLightData.gridx = 0;
		gbc_lblLightData.gridy = 0;
		panel_2.add(lblLightData, gbc_lblLightData);
		
		lightTextField = new JTextField();
		lightTextField.setColumns(10);
		GridBagConstraints gbc_lightTextField = new GridBagConstraints();
		gbc_lightTextField.insets = new Insets(0, 0, 0, 5);
		gbc_lightTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lightTextField.gridx = 1;
		gbc_lightTextField.gridy = 0;
		panel_2.add(lightTextField, gbc_lightTextField);
		
		JButton lightButton = new JButton("choose...");
		lightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomJFileChooser fChooser = new CustomJFileChooser() ;
				fChooser.setFileExtension("gds");
				fChooser.saveFile();
				lightTextField.setText(fChooser.getSelectedFile().getAbsolutePath());
			}
		});
		GridBagConstraints gbc_lightButton = new GridBagConstraints();
		gbc_lightButton.gridx = 2;
		gbc_lightButton.gridy = 0;
		panel_2.add(lightButton, gbc_lightButton);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(5, 5, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		panel.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblStartX = new JLabel("a = ");
		GridBagConstraints gbc_lblStartX = new GridBagConstraints();
		gbc_lblStartX.anchor = GridBagConstraints.EAST;
		gbc_lblStartX.insets = new Insets(5, 5, 5, 5);
		gbc_lblStartX.gridx = 0;
		gbc_lblStartX.gridy = 0;
		panel_3.add(lblStartX, gbc_lblStartX);
		
		start = new JTextField();
		GridBagConstraints gbc_start = new GridBagConstraints();
		gbc_start.insets = new Insets(0, 0, 5, 5);
		gbc_start.fill = GridBagConstraints.HORIZONTAL;
		gbc_start.gridx = 1;
		gbc_start.gridy = 0;
		panel_3.add(start, gbc_start);
		start.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 0;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton = new JButton("Set Parameters");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblNewLabel.setText("All set!");
				fig.clear();
				
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		panel_3.add(btnNewButton, gbc_btnNewButton);
		
		JCheckBox chckbxCircularBend = new JCheckBox("Circular bend");
		GridBagConstraints gbc_chckbxCircularBend = new GridBagConstraints();
		gbc_chckbxCircularBend.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxCircularBend.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCircularBend.gridx = 6;
		gbc_chckbxCircularBend.gridy = 0;
		panel_3.add(chckbxCircularBend, gbc_chckbxCircularBend);
		
		JRadioButton rdbtnCurve = new JRadioButton("Curve");
		GridBagConstraints gbc_rdbtnCurve = new GridBagConstraints();
		gbc_rdbtnCurve.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnCurve.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnCurve.gridx = 7;
		gbc_rdbtnCurve.gridy = 0;
		panel_3.add(rdbtnCurve, gbc_rdbtnCurve);
		

		
		JLabel lblEndX = new JLabel("b = ");
		GridBagConstraints gbc_lblEndX = new GridBagConstraints();
		gbc_lblEndX.anchor = GridBagConstraints.EAST;
		gbc_lblEndX.insets = new Insets(5, 5, 5, 5);
		gbc_lblEndX.gridx = 0;
		gbc_lblEndX.gridy = 1;
		panel_3.add(lblEndX, gbc_lblEndX);
		
		end = new JTextField();
		end.setColumns(10);
		GridBagConstraints gbc_end = new GridBagConstraints();
		gbc_end.insets = new Insets(0, 0, 5, 5);
		gbc_end.fill = GridBagConstraints.HORIZONTAL;
		gbc_end.gridx = 1;
		gbc_end.gridy = 1;
		panel_3.add(end, gbc_end);
		
		JButton appendButton = new JButton("Plot");
		appendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_appendButton = new GridBagConstraints();
		gbc_appendButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_appendButton.weighty = 2.0;
		gbc_appendButton.insets = new Insets(0, 0, 5, 5);
		gbc_appendButton.gridx = 3;
		gbc_appendButton.gridy = 1;
		panel_3.add(appendButton, gbc_appendButton);
		
		JCheckBox chckbxBezierBend = new JCheckBox("Bezier bend");
		GridBagConstraints gbc_chckbxBezierBend = new GridBagConstraints();
		gbc_chckbxBezierBend.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxBezierBend.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxBezierBend.gridx = 6;
		gbc_chckbxBezierBend.gridy = 1;
		panel_3.add(chckbxBezierBend, gbc_chckbxBezierBend);
		
		JRadioButton rdbtnRadiusOfCurvature = new JRadioButton("Radius of curvature");
		GridBagConstraints gbc_rdbtnRadiusOfCurvature = new GridBagConstraints();
		gbc_rdbtnRadiusOfCurvature.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnRadiusOfCurvature.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnRadiusOfCurvature.gridx = 7;
		gbc_rdbtnRadiusOfCurvature.gridy = 1;
		panel_3.add(rdbtnRadiusOfCurvature, gbc_rdbtnRadiusOfCurvature);
		
		JLabel lblPoints = new JLabel("R0 (um) = ");
		GridBagConstraints gbc_lblPoints = new GridBagConstraints();
		gbc_lblPoints.anchor = GridBagConstraints.EAST;
		gbc_lblPoints.insets = new Insets(5, 5, 0, 5);
		gbc_lblPoints.gridx = 0;
		gbc_lblPoints.gridy = 2;
		panel_3.add(lblPoints, gbc_lblPoints);
		
		rate = new JTextField();
		rate.setColumns(10);
		GridBagConstraints gbc_rate = new GridBagConstraints();
		gbc_rate.insets = new Insets(0, 0, 0, 5);
		gbc_rate.fill = GridBagConstraints.HORIZONTAL;
		gbc_rate.gridx = 1;
		gbc_rate.gridy = 2;
		panel_3.add(rate, gbc_rate);		
		
		JButton exportButton = new JButton("save GDS...");
		exportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		GridBagConstraints gbc_exportButton = new GridBagConstraints();
		gbc_exportButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_exportButton.insets = new Insets(0, 0, 0, 5);
		gbc_exportButton.gridx = 3;
		gbc_exportButton.gridy = 2;
		panel_3.add(exportButton, gbc_exportButton);
		
		JCheckBox chckbxOptimalBend = new JCheckBox("Optimal bend");
		GridBagConstraints gbc_chckbxOptimalBend = new GridBagConstraints();
		gbc_chckbxOptimalBend.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxOptimalBend.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxOptimalBend.gridx = 6;
		gbc_chckbxOptimalBend.gridy = 2;
		panel_3.add(chckbxOptimalBend, gbc_chckbxOptimalBend);
		
		JRadioButton rdbtnBendingLossdbcm = new JRadioButton("Bending loss (dB/cm)");
		GridBagConstraints gbc_rdbtnBendingLossdbcm = new GridBagConstraints();
		gbc_rdbtnBendingLossdbcm.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnBendingLossdbcm.gridx = 7;
		gbc_rdbtnBendingLossdbcm.gridy = 2;
		panel_3.add(rdbtnBendingLossdbcm, gbc_rdbtnBendingLossdbcm);
	}
}
