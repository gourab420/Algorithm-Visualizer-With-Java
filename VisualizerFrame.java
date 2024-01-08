package sortingvisualizer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VisualizerFrame extends JFrame {

	private final int MAX_SPEED=1000;
	private final int MIN_SPEED=1;
	private final int MAX_SIZE=200;
	private final int MIN_SIZE=1;
	private final int DEFAULT_SPEED=20;
	private final int DEFAULT_SIZE=50;
	private final String[] Sorts={"Bubble", "Selection", "Merge","Quick"};
	private JPanel wrapper;
	private JPanel arrayWrapper;
	private JPanel buttonWrapper;
	private JPanel[] squarePanels;
	private JButton start;
	private JComboBox<String> selection;
	private JSlider speed;
	private JSlider size;
	private JLabel speedVal;
	private JLabel sizeVal;
	private GridBagConstraints c;

	public VisualizerFrame(){
		super("Sorting Visualizer");
		start=new JButton("Start");
		buttonWrapper=new JPanel();
		arrayWrapper=new JPanel();
		wrapper=new JPanel();
		selection=new JComboBox<String>();
		speed=new JSlider(MIN_SPEED,MAX_SPEED,DEFAULT_SPEED);
		size=new JSlider(MIN_SIZE,MAX_SIZE,DEFAULT_SIZE);
		speedVal=new JLabel("Speed: 20 ms");
		sizeVal=new JLabel("Size: 50 values");
		c=new GridBagConstraints();
		for(int i=0;i<Sorts.length;i++) {
                selection.addItem(Sorts[i]); }
		arrayWrapper.setLayout(new GridBagLayout());
		wrapper.setLayout(new BorderLayout());
		c.insets=new Insets(0,2,0,2);
		c.anchor=GridBagConstraints.SOUTH;

		start.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent e) {
				SortingVisualizer.startSort((String) selection.getSelectedItem()); }
		});
               
		speed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg) {
				speedVal.setText(("Speed: "+Integer.toString(speed.getValue()) + "ms"));
				validate();
				SortingVisualizer.sleep=speed.getValue(); }
		});

		size.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				sizeVal.setText(("Size: "+Integer.toString(size.getValue())+" values"));
				validate();
				SortingVisualizer.sortDataCount=size.getValue(); }
		});

		buttonWrapper.add(speedVal);
		buttonWrapper.add(speed);
		buttonWrapper.add(sizeVal);
		buttonWrapper.add(size);
		buttonWrapper.add(start);
		buttonWrapper.add(selection);
		wrapper.add(buttonWrapper, BorderLayout.SOUTH);
		wrapper.add(arrayWrapper);
		add(wrapper);
		setExtendedState(JFrame.MAXIMIZED_BOTH );
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
        
	public void preDrawArray(Integer[] squares){
		squarePanels=new JPanel[SortingVisualizer.sortDataCount];
		arrayWrapper.removeAll();
		for(int i=0;i<SortingVisualizer.sortDataCount;i++) {
			squarePanels[i]=new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth,squares[i]*6));
			squarePanels[i].setBackground(Color.blue);
			arrayWrapper.add(squarePanels[i], c); }
		        repaint();
		        validate();
	}

	public void reDrawArray(Integer[] squares, int working, int comparing, int reading){
		arrayWrapper.removeAll();
		for(int i=0;i<squarePanels.length;i++){
			squarePanels[i]=new JPanel();
			squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*6));
			if (i==working){
				squarePanels[i].setBackground(Color.green);
			}else if(i==comparing){
				squarePanels[i].setBackground(Color.red);
			}else if(i==reading){
				squarePanels[i].setBackground(Color.yellow);
			}else{
				squarePanels[i].setBackground(Color.blue);
			}
			arrayWrapper.add(squarePanels[i], c); }
		        repaint();
		        validate(); }}