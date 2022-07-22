package dp.pb.learn.learn;

/**
 * @author pengbo
 * @date 2021/5/26 15:56
 * @name:
 */
public class XXXXAA {
    static int x=0;
    public static int[] spiralOrder(int[][] matrix) {
        int tR=0;
        int tC=0;
        int dR=matrix.length-1;
        int dC=matrix[0].length-1;
        int[] res =new int[matrix.length*matrix[0].length];

        while (tR<=dR&&tC<=dC){
            printedge(matrix,tR++,tC++,dR--,dC--,res);
        }
        return res;
    }
    public static void printedge(int[][] m,int tR,int tC,int dR,int dC,int[] res){
        if (tR==dR){
            for (int i=tC;i<=dC;i++){
                res[x++]=m[tR][i];
            }
        }
        else if (tC==dC){
            for (int i=tR;i<=dR;i++){
                res[x++]=m[i][tC];
            }
        }else{
            int curC=tC;
            int curR=tR;
            while(curC!=dC){
                res[x++]=m[tR][curC];
                curC++;
            }
            while(curR!=dR){
                res[x++]=m[curR][dC];
                curR++;
            }
            while(curC!=tC){
                res[x++]=m[dR][curC];
                curC--;
            }
            while (curR!=tR){
                res[x++]=m[curR][tC];
                curR--;
            }
        }
    }

    public static void main(String[] args) {
        int[][] x ={{1,2,3}, {4,5,6},{7,8,9}};
        int[] ints = spiralOrder(x);
    }
}
