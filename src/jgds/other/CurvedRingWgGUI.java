package jgds.other;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import mathLib.util.CustomJFileChooser;
import mathLib.util.MathUtils;
import java.awt.Toolkit;

public class CurvedRingWgGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6307934649249297861L;
	private JPanel contentPane;
	private JTextField path;
	private JTextField ringRadius;
	private JTextField ringWidth;
	private JTextField gap;
	private JTextField theta;
	private JTextField busWidth;
	private JTextField busRadius;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurvedRingWgGUI frame = new CurvedRingWgGUI();
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
	public CurvedRingWgGUI() {
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
		
		setTitle("Curved Ring Waveguide Designer");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CurvedRingWgGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "gds path", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSaveGds = new JLabel("save GDS : ");
		GridBagConstraints gbc_lblSaveGds = new GridBagConstraints();
		gbc_lblSaveGds.anchor = GridBagConstraints.EAST;
		gbc_lblSaveGds.insets = new Insets(2, 5, 0, 5);
		gbc_lblSaveGds.gridx = 0;
		gbc_lblSaveGds.gridy = 0;
		panel.add(lblSaveGds, gbc_lblSaveGds);
		
		path = new JTextField();
		GridBagConstraints gbc_path = new GridBagConstraints();
		gbc_path.insets = new Insets(0, 0, 0, 5);
		gbc_path.fill = GridBagConstraints.HORIZONTAL;
		gbc_path.gridx = 1;
		gbc_path.gridy = 0;
		panel.add(path, gbc_path);
		path.setColumns(10);
		
		JButton chooseButton = new JButton("choose...");
		chooseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomJFileChooser fChooser = new CustomJFileChooser() ;
				fChooser.setFileExtension("gds");
				fChooser.saveFile();
				path.setText(fChooser.getSelectedFile().getAbsolutePath()+".gds");
			}
		});
		GridBagConstraints gbc_chooseButton = new GridBagConstraints();
		gbc_chooseButton.gridx = 2;
		gbc_chooseButton.gridy = 0;
		panel.add(chooseButton, gbc_chooseButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Parameters", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblRingRadiusum = new JLabel("Ring radius (um) : ");
		GridBagConstraints gbc_lblRingRadiusum = new GridBagConstraints();
		gbc_lblRingRadiusum.anchor = GridBagConstraints.EAST;
		gbc_lblRingRadiusum.insets = new Insets(5, 5, 5, 5);
		gbc_lblRingRadiusum.gridx = 0;
		gbc_lblRingRadiusum.gridy = 0;
		panel_1.add(lblRingRadiusum, gbc_lblRingRadiusum);
		
		ringRadius = new JTextField();
		GridBagConstraints gbc_ringRadius = new GridBagConstraints();
		gbc_ringRadius.insets = new Insets(0, 0, 5, 0);
		gbc_ringRadius.fill = GridBagConstraints.HORIZONTAL;
		gbc_ringRadius.gridx = 1;
		gbc_ringRadius.gridy = 0;
		panel_1.add(ringRadius, gbc_ringRadius);
		ringRadius.setColumns(10);
		
		JLabel lblRingWidthum = new JLabel("Ring width (um) : ");
		GridBagConstraints gbc_lblRingWidthum = new GridBagConstraints();
		gbc_lblRingWidthum.anchor = GridBagConstraints.EAST;
		gbc_lblRingWidthum.insets = new Insets(0, 0, 5, 5);
		gbc_lblRingWidthum.gridx = 0;
		gbc_lblRingWidthum.gridy = 1;
		panel_1.add(lblRingWidthum, gbc_lblRingWidthum);
		
		ringWidth = new JTextField();
		GridBagConstraints gbc_ringWidth = new GridBagConstraints();
		gbc_ringWidth.insets = new Insets(0, 0, 5, 0);
		gbc_ringWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_ringWidth.gridx = 1;
		gbc_ringWidth.gridy = 1;
		panel_1.add(ringWidth, gbc_ringWidth);
		ringWidth.setColumns(10);
		
		JLabel lblGapum = new JLabel("Gap (um) : ");
		GridBagConstraints gbc_lblGapum = new GridBagConstraints();
		gbc_lblGapum.anchor = GridBagConstraints.EAST;
		gbc_lblGapum.insets = new Insets(0, 0, 5, 5);
		gbc_lblGapum.gridx = 0;
		gbc_lblGapum.gridy = 2;
		panel_1.add(lblGapum, gbc_lblGapum);
		
		gap = new JTextField();
		GridBagConstraints gbc_gap = new GridBagConstraints();
		gbc_gap.insets = new Insets(0, 0, 5, 0);
		gbc_gap.fill = GridBagConstraints.HORIZONTAL;
		gbc_gap.gridx = 1;
		gbc_gap.gridy = 2;
		panel_1.add(gap, gbc_gap);
		gap.setColumns(10);
		
		JLabel lblBusRadiusum = new JLabel("Theta (degree) : ");
		GridBagConstraints gbc_lblBusRadiusum = new GridBagConstraints();
		gbc_lblBusRadiusum.anchor = GridBagConstraints.EAST;
		gbc_lblBusRadiusum.insets = new Insets(0, 0, 5, 5);
		gbc_lblBusRadiusum.gridx = 0;
		gbc_lblBusRadiusum.gridy = 3;
		panel_1.add(lblBusRadiusum, gbc_lblBusRadiusum);
		
		theta = new JTextField();
		GridBagConstraints gbc_theta = new GridBagConstraints();
		gbc_theta.insets = new Insets(0, 0, 5, 0);
		gbc_theta.fill = GridBagConstraints.HORIZONTAL;
		gbc_theta.gridx = 1;
		gbc_theta.gridy = 3;
		panel_1.add(theta, gbc_theta);
		theta.setColumns(10);
		
		JLabel lblBusRadiusum_1 = new JLabel("Bus width(um) : ");
		GridBagConstraints gbc_lblBusRadiusum_1 = new GridBagConstraints();
		gbc_lblBusRadiusum_1.anchor = GridBagConstraints.EAST;
		gbc_lblBusRadiusum_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBusRadiusum_1.gridx = 0;
		gbc_lblBusRadiusum_1.gridy = 4;
		panel_1.add(lblBusRadiusum_1, gbc_lblBusRadiusum_1);
		
		busWidth = new JTextField();
		GridBagConstraints gbc_busWidth = new GridBagConstraints();
		gbc_busWidth.insets = new Insets(0, 0, 5, 0);
		gbc_busWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_busWidth.gridx = 1;
		gbc_busWidth.gridy = 4;
		panel_1.add(busWidth, gbc_busWidth);
		busWidth.setColumns(10);
		
		JLabel lblBusRadiusum_2 = new JLabel("Bus radius (um) : ");
		GridBagConstraints gbc_lblBusRadiusum_2 = new GridBagConstraints();
		gbc_lblBusRadiusum_2.anchor = GridBagConstraints.EAST;
		gbc_lblBusRadiusum_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblBusRadiusum_2.gridx = 0;
		gbc_lblBusRadiusum_2.gridy = 5;
		panel_1.add(lblBusRadiusum_2, gbc_lblBusRadiusum_2);
		
		busRadius = new JTextField();
		GridBagConstraints gbc_busRadius = new GridBagConstraints();
		gbc_busRadius.fill = GridBagConstraints.HORIZONTAL;
		gbc_busRadius.gridx = 1;
		gbc_busRadius.gridy = 5;
		panel_1.add(busRadius, gbc_busRadius);
		busRadius.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "configuration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		contentPane.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 57, 107, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JRadioButton allPass = new JRadioButton("All-pass");
		buttonGroup.add(allPass);
		GridBagConstraints gbc_allPass = new GridBagConstraints();
		gbc_allPass.insets = new Insets(5, 5, 0, 5);
		gbc_allPass.gridx = 0;
		gbc_allPass.gridy = 0;
		panel_2.add(allPass, gbc_allPass);
		
		JRadioButton addDrop = new JRadioButton("Add-drop");
		buttonGroup.add(addDrop);
		GridBagConstraints gbc_addDrop = new GridBagConstraints();
		gbc_addDrop.insets = new Insets(0, 0, 0, 5);
		gbc_addDrop.anchor = GridBagConstraints.SOUTH;
		gbc_addDrop.gridx = 1;
		gbc_addDrop.gridy = 0;
		panel_2.add(addDrop, gbc_addDrop);
		
		JButton btnRun = new JButton("RUN");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double ring_radius = MathUtils.evaluate(ringRadius.getText()) ;
				double ring_width = MathUtils.evaluate(ringWidth.getText()) ;
				double gap_um = MathUtils.evaluate(gap.getText()) ;
				double theta_degree = MathUtils.evaluate(theta.getText()) ;
				double bus_width = MathUtils.evaluate(busWidth.getText()) ;
				double bus_radius = MathUtils.evaluate(busRadius.getText()) ;
				if(allPass.isSelected()) {
					CurvedRingWg allpass = new CurvedRingWg(ring_radius, ring_width, gap_um, bus_width, bus_radius, theta_degree) ;
					allpass.setFile(path.getText());
					allpass.createGDS();
				}
				else if(addDrop.isSelected()) {
					CurvedAddDrop adr = new CurvedAddDrop(ring_radius, ring_width, gap_um, bus_width, bus_radius, theta_degree) ;
					adr.setFile(path.getText());
					adr.createGDS();
				}
					
			}
		});
		GridBagConstraints gbc_btnRun = new GridBagConstraints();
		gbc_btnRun.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRun.gridx = 3;
		gbc_btnRun.gridy = 0;
		panel_2.add(btnRun, gbc_btnRun);
	}

}
