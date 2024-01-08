package sortingvisualizer;
public class MergeSort implements Runnable {
   @Override
   public void run() {
      Integer[]x=SortingVisualizer.toBeSorted;
      inPlaceSort(x, 0, x.length - 1);
      SortingVisualizer.isSorting=false;
   }

   private void inPlaceSort(Integer[]x,int first,int last) {
      int mid,lt,rt,tmp;
      if (first>=last){    return;   }
      mid=(first+last)/2;
      inPlaceSort(x,first,mid);
      inPlaceSort(x,mid+1,last);
      if (x[mid]<=x[mid+1]){  return; }
         lt=first;
         rt=mid+1;

      while(lt<=mid && rt<=last) {
         if (x[lt]<=x[rt]){  lt++; }
         else {
            tmp=x[rt];
            for(int i=rt-lt;i>0;i--) {
               x[lt+i]=x[lt+i-1];
            }
            x[lt]=tmp;
            lt++;
            mid++;
            rt++;
         }
         SortingVisualizer.frame.reDrawArray(x, mid, rt, lt);
         try {
            Thread.sleep(SortingVisualizer.sleep);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }}}}


