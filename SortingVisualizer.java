package sortingvisualizer;
import javax.swing.JOptionPane;
public class SortingVisualizer {
    private static Thread sortingThread;
    public static VisualizerFrame frame;
    public static Integer[] toBeSorted;
    public static boolean isSorting = false;
    public static int sortDataCount = 50;
    public static int sleep = 20;
    public static int blockWidth;
    public static boolean stepped = false;

    public static void main(String[] args) {
        frame = new VisualizerFrame();
        resetArray();
        frame.setLocationRelativeTo(null); }

    public static void resetArray() {
        if (isSorting) 
           {  return; }
           toBeSorted=new Integer[sortDataCount];
           blockWidth=(int) Math.max(Math.floor(900 / sortDataCount), 1);
             for (int i=0;i<toBeSorted.length;i++) {
               if (stepped) 
                 {  toBeSorted[i]=i; } 
               else 
                 {  toBeSorted[i]=(int)(sortDataCount*Math.random()); }}
        frame.preDrawArray(toBeSorted); }

    public static void startSort(String type) {
    try{ if (sortingThread == null || !isSorting) {
            resetArray();
            isSorting = true;
            switch (type) {
                case "Bubble":
                    sortingThread = new Thread(new BubbleSort(toBeSorted, frame, false));
                    break;

                case "Selection":
                    sortingThread = new Thread(new SelectionSort(toBeSorted, frame, false));
                    break;

                case "Merge":
                    sortingThread = new Thread(new MergeSort());
                    break;
                    
                case "Quick":
                    sortingThread = new Thread(new QuickSort(toBeSorted, frame, false));
                    break;

                     default:
                    isSorting = false;
                    return; }
                    sortingThread.start(); }}
    catch(Exception ex)  {
        JOptionPane.showMessageDialog(null, ex);  }}}