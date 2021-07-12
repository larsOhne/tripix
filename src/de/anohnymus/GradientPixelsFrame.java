package de.anohnymus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.game.plugin.swingcomponents.Constants;
import de.game.plugin.swingcomponents.GButton;
import de.game.plugin.swingcomponents.GLabel;
import de.game.plugin.swingcomponents.GPanel;
import de.game.plugin.swingcomponents.GTextField;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class GradientPixelsFrame extends JFrame {

	private static final long serialVersionUID = -4052365587760192416L;

	private GTextField textField;
	private GButton btnSuchen;
	private GButton btnGo;
	private JScrollPane scrollPane;
	private GPanel picture;
	private GButton btnEx;
	private JLabel container;
	private GPanel settingsPanel;

//	private BufferedImage grImg;
	private GLabel lblGradientVertices;
	private JSpinner spinnNumOfGradientVertices;
	private GLabel lblRandomVertices;
	private JSpinner spinnNumOfRandVertices;
	private JLabel lblBorderVertices;
	private JSpinner spinnBorderVertices;
	private JLabel lblRandomWeight;
	private JSlider slider;
	private JSpinner spinnColorDens;
	private JLabel lblColorDensity;
	private JTextArea textArea;
	
	private Settings settings = new Settings();
	private BufferedImage img;
	private Triangulizer t;
	private JScrollPane scrollPane_1;
	private JPanel panel;

	
	public GradientPixelsFrame() {
		super();
		setMinimumSize(new Dimension(600,400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		
		try {
			BufferedImage logo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("TriPix.png"));
			setIconImage(logo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		initComponents();
		initListeners();
		
		setVisible(true);
	}
	
	private void initComponents() {
		GPanel content = new GPanel();
		content.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(content);
		GridBagLayout gbl_content = new GridBagLayout();
		gbl_content.columnWidths = new int[]{0, 200, 200, 0};
		gbl_content.rowHeights = new int[]{0, 0, 0, 0};
		gbl_content.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_content.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		content.setLayout(gbl_content);
		
		try {
			settings = (Settings) GradientPixelsMain.files.loadObject("settings");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		textField = new GTextField();
		textField.setText(settings.getPathToSource());
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		content.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnSuchen = new GButton("...");
		btnSuchen.getLbl().setFont(new Font("Verdana",Font.PLAIN,18));
		GridBagConstraints gbc_btnSuchen = new GridBagConstraints();
		gbc_btnSuchen.anchor = GridBagConstraints.WEST;
		gbc_btnSuchen.fill = GridBagConstraints.VERTICAL;
		gbc_btnSuchen.insets = new Insets(0, 0, 5, 0);
		gbc_btnSuchen.gridx = 2;
		gbc_btnSuchen.gridy = 0;
		content.add(btnSuchen, gbc_btnSuchen);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		content.add(scrollPane, gbc_scrollPane);
		
		picture = new GPanel();
		scrollPane.setViewportView(picture);
		
		container = new JLabel();
		picture.add(container);
		
		settingsPanel = new GPanel();
		GridBagConstraints gbc_settingsPanel = new GridBagConstraints();
		gbc_settingsPanel.gridwidth = 2;
		gbc_settingsPanel.fill = GridBagConstraints.BOTH;
		gbc_settingsPanel.gridx = 1;
		gbc_settingsPanel.gridy = 2;
		content.add(settingsPanel, gbc_settingsPanel);
		GridBagLayout gbl_settingsPanel = new GridBagLayout();
		gbl_settingsPanel.columnWidths = new int[]{100, 100, 0};
		gbl_settingsPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_settingsPanel.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_settingsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		settingsPanel.setLayout(gbl_settingsPanel);
		
		lblGradientVertices = new GLabel(4, "Gradient Vertices");
		lblGradientVertices.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblGradientVertices = new GridBagConstraints();
		gbc_lblGradientVertices.anchor = GridBagConstraints.WEST;
		gbc_lblGradientVertices.insets = new Insets(0, 0, 5, 5);
		gbc_lblGradientVertices.gridx = 0;
		gbc_lblGradientVertices.gridy = 0;
		settingsPanel.add(lblGradientVertices, gbc_lblGradientVertices);
		
		spinnNumOfGradientVertices = new JSpinner();
		spinnNumOfGradientVertices.setModel(new SpinnerNumberModel(new Integer(settings.getNumOfVertices()), null, null, new Integer(100)));
		GridBagConstraints gbc_spinnNumOfGradientVertices = new GridBagConstraints();
		gbc_spinnNumOfGradientVertices.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnNumOfGradientVertices.insets = new Insets(0, 0, 5, 0);
		gbc_spinnNumOfGradientVertices.gridx = 1;
		gbc_spinnNumOfGradientVertices.gridy = 0;
		settingsPanel.add(spinnNumOfGradientVertices, gbc_spinnNumOfGradientVertices);
		
		lblRandomVertices = new GLabel(4, "Random Vertices");
		GridBagConstraints gbc_lblRandomVertices = new GridBagConstraints();
		gbc_lblRandomVertices.anchor = GridBagConstraints.WEST;
		gbc_lblRandomVertices.insets = new Insets(0, 0, 5, 5);
		gbc_lblRandomVertices.gridx = 0;
		gbc_lblRandomVertices.gridy = 2;
		settingsPanel.add(lblRandomVertices, gbc_lblRandomVertices);
		
		spinnNumOfRandVertices = new JSpinner();
		spinnNumOfRandVertices.setModel(new SpinnerNumberModel(new Integer(settings.getNumOfRandoms()), null, null, new Integer(100)));
		GridBagConstraints gbc_spinnNumOfRandVertices = new GridBagConstraints();
		gbc_spinnNumOfRandVertices.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnNumOfRandVertices.insets = new Insets(0, 0, 5, 0);
		gbc_spinnNumOfRandVertices.gridx = 1;
		gbc_spinnNumOfRandVertices.gridy = 2;
		settingsPanel.add(spinnNumOfRandVertices, gbc_spinnNumOfRandVertices);
		
		lblBorderVertices = new GLabel(4, "Border Vertices");
		GridBagConstraints gbc_lblBorderVertices = new GridBagConstraints();
		gbc_lblBorderVertices.anchor = GridBagConstraints.WEST;
		gbc_lblBorderVertices.insets = new Insets(0, 0, 5, 5);
		gbc_lblBorderVertices.gridx = 0;
		gbc_lblBorderVertices.gridy = 4;
		settingsPanel.add(lblBorderVertices, gbc_lblBorderVertices);
		
		spinnBorderVertices = new JSpinner();
		spinnBorderVertices.setModel(new SpinnerNumberModel(new Integer(settings.getAdditionalBorderVertices()), null, null, new Integer(1)));
		GridBagConstraints gbc_spinnBorderVertices = new GridBagConstraints();
		gbc_spinnBorderVertices.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnBorderVertices.insets = new Insets(0, 0, 5, 0);
		gbc_spinnBorderVertices.gridx = 1;
		gbc_spinnBorderVertices.gridy = 4;
		settingsPanel.add(spinnBorderVertices, gbc_spinnBorderVertices);
		
		lblRandomWeight = new GLabel(4,"Random Weight");
		GridBagConstraints gbc_lblRandomWeight = new GridBagConstraints();
		gbc_lblRandomWeight.anchor = GridBagConstraints.WEST;
		gbc_lblRandomWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblRandomWeight.gridx = 0;
		gbc_lblRandomWeight.gridy = 6;
		settingsPanel.add(lblRandomWeight, gbc_lblRandomWeight);
		
		slider = new JSlider();
		slider.setBackground(Color.DARK_GRAY);
		slider.setMaximum(1000);
		slider.setValue((int)(settings.getPercentageOfOld()*1000));
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.gridwidth = 2;
		gbc_slider.insets = new Insets(0, 0, 5, 0);
		gbc_slider.gridx = 0;
		gbc_slider.gridy = 7;
		settingsPanel.add(slider, gbc_slider);
		
		lblColorDensity = new GLabel(4,"Color Density");
		GridBagConstraints gbc_lblColorDensity = new GridBagConstraints();
		gbc_lblColorDensity.anchor = GridBagConstraints.WEST;
		gbc_lblColorDensity.insets = new Insets(0, 0, 5, 5);
		gbc_lblColorDensity.gridx = 0;
		gbc_lblColorDensity.gridy = 9;
		settingsPanel.add(lblColorDensity, gbc_lblColorDensity);
		
		spinnColorDens = new JSpinner();
		spinnColorDens.setModel(new SpinnerNumberModel(settings.getColorDenssity(),0.0 ,1000.0,0.1));
		GridBagConstraints gbc_spinnColorDens = new GridBagConstraints();
		gbc_spinnColorDens.fill = GridBagConstraints.BOTH;
		gbc_spinnColorDens.insets = new Insets(0, 0, 5, 0);
		gbc_spinnColorDens.gridx = 1;
		gbc_spinnColorDens.gridy = 9;
		settingsPanel.add(spinnColorDens, gbc_spinnColorDens);
		
		panel = new JPanel();
		panel.setOpaque(false);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 11;
		settingsPanel.add(panel, gbc_panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnGo = new GButton("go");
		panel.add(btnGo);
		btnGo.getLbl().setFont(new Font("Verdana",Font.PLAIN,18));
		
		btnEx = new GButton("ex");
		panel.add(btnEx);
		GridLayout gridLayout = (GridLayout) btnEx.getLayout();
		btnEx.getLbl().setFont(new Font("Verdana",Font.PLAIN,18));
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 13;
		settingsPanel.add(scrollPane_1, gbc_scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setBackground(Constants.FOREGROUND_COLOR_1);
		textArea.setBorder(null);
		textArea.setForeground(Constants.FOREGROUND_COLOR_3);
	}

	public GradientPixelsFrame(String title){
		this();
		setTitle(title);
	}
	
	private void initListeners() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				updateSettings();
				
				try {
					GradientPixelsMain.files.saveObject(settings, "settings");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				super.windowClosing(e);
			}
		});
		
		btnSuchen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				textField.setText(fc.getSelectedFile().getAbsolutePath());
				settings.setPathToSource(textField.getText());
				includeImage();
			}
		});
		
		btnGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startTriangulize();
			}
		});
		
		btnEx.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File toSave = saveTo(new File(textField.getText()));
				if(toSave != null){
					if(toSave.exists()){
						int result = JOptionPane.showConfirmDialog(GradientPixelsFrame.this, "Do you want do overwrite the existing File?", 
								"File already exists!", JOptionPane.WARNING_MESSAGE);
						if(result != JOptionPane.OK_OPTION)return;
					}
					
					switch(toSave.getName().split(Pattern.quote("."))[1]){
					case "jpg":
						try {
							ImageIO.write(t.getProcessed(), "JPEG", toSave);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(GradientPixelsFrame.this, e1.getMessage());
						}
						break;
						
					case "png":
						try {
							ImageIO.write(t.getProcessed(), "PNG", toSave);
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(GradientPixelsFrame.this, e1.getMessage());
						}
						break;
						
					default:
						try {
							SVGGenerator g = new SVGGenerator(toSave, t.get(), t.getProcessed().getWidth(), t.getProcessed().getHeight());
							g.execute();
						} catch (InterruptedException | ExecutionException e1) {
							JOptionPane.showMessageDialog(GradientPixelsFrame.this, e1.getMessage());
						}
					}
				}
				
			}
		});
		
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equalsIgnoreCase(settings.getPathToSource())){
					includeImage();
				}
				
			}
		});
		
	}
	
	private File saveTo(File source) {
		JFileChooser fc;
		if(source != null){
			fc = new JFileChooser(source);
			fc.setSelectedFile(source);
		}
		else fc = new JFileChooser();
		
		fc.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpg", "jpeg"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Portable Network Graphic (.png)", "png"));
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Scalable Vector Graphic (.svg)", "svg"));
		fc.setAcceptAllFileFilterUsed(false);
			
		fc.showSaveDialog(null);
		
		File toSave = fc.getSelectedFile();
		if(toSave == null)return null;
		
		FileNameExtensionFilter curFilter = (FileNameExtensionFilter) fc.getFileFilter();
		
		if(curFilter.accept(toSave))return toSave;
		else{
			String fileName = toSave.getName();
			if(fileName.contains("."))
				fileName = toSave.getName().split(Pattern.quote("."))[0] ;
			
			return new File(toSave.getParentFile().getAbsolutePath()+ "/" + fileName + "." + curFilter.getExtensions()[0]);
		}
		
	}
	
	private void updateSettings(){
		settings.setNumOfVertices((int) spinnNumOfGradientVertices.getValue());
		settings.setNumOfRandoms((int) spinnNumOfRandVertices.getValue());
		settings.setAdditionalBorderVertices((int) spinnBorderVertices.getValue());
		settings.setPercentageOfOld((double)(slider.getValue())/1000);
		settings.setColorDensity((double) spinnColorDens.getValue());
	}

	protected void startTriangulize() {
			updateSettings();
			try{
			if(img == null)includeImage();
			t = new Triangulizer(img, textArea, container, settings, btnEx);
			t.execute();
			btnEx.setEnabled(false);
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "Something went wrong",e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
	}
	
	private void includeImage(){
		try {
			img = ImageIO.read(new FileInputStream(new File(textField.getText())));
			container.setIcon(new ImageIcon(img));
			btnGo.setEnabled(true);
			btnEx.setEnabled(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Please check your input-file", "Something's wrong with input!", JOptionPane.ERROR_MESSAGE);
			textField.requestFocus();
			textField.selectAll();
			btnGo.setEnabled(false);
			btnEx.setEnabled(false);
		}
		
	}
}
