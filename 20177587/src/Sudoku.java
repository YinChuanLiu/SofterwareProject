import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
    仅支持 3 5 7 宫格
*/
public class Sudoku {
    static int arr[][] = new int[9][9];
    static int count = 0;
    static Set<Integer> set = null;

    public static void main(String[] args) {
        // 3宫格
        int m = Integer.parseInt(args[1]);
        //n个3宫格 等待解答
        int n = Integer.parseInt(args[3]);
        //输入的文本文件 路径   G:\IdeaProjects\SuanFa\input.txt
        String inputpath = System.getProperty("user.dir") + File.separator + "input.txt";
        //输入的文本文件 路径   G:\IdeaProjects\SuanFa\output.txt
        String outputpath = System.getProperty("user.dir") + File.separator + "output.txt";
        //文件输入流
        File file = new File(inputpath);
        FileInputStream fileInputStream = null;
        BufferedWriter bw = null;
        try {
            fileInputStream = new FileInputStream(file);
            bw = new BufferedWriter(new FileWriter(outputpath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("文件未找到!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(fileInputStream);

        //输入要求处理 n 个盘面
        for (int pm = 0; pm < n; pm++) {
            // 第 pm 个盘面 的第i行  宫格阶级为 m  也就是 m x m的盘面
            for (int i = 0; i < m; i++) {
                //第 pm 个盘面 的第j行
                for (int j = 0; j < m; j++) {
                    //拿到值存入整形二维数组
                    arr[i][j] = sc.nextInt();
                    if (arr[i][j] == 0) {
                        count++;
                    }
                }
            }

            while (count != 0) {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        if (arr[i][j] == 0) {
                            clear0(i, j, m);
                        }
                    }

                }
            }
            //输出文件
            try {
                for (int i = 0; i < m; i++) {

                    for (int j = 0; j < m; j++) {
                        //System.out.print(arr[i][j] + " ");
                        bw.append(arr[i][j] + " ");
                    }
                    //System.out.println();
                    bw.write("\r\n");
                }
                bw.write("\r\n");

            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println();
        }
        sc.close();
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param i 元素所在行
     * @param j 元素所在列
     * @param m 宫格级数
     */
    public static void clear0(int i, int j, int m) {
        set = new HashSet<>();
        //找同一行的
        for (int k = 0; k < m; k++) {
            if (arr[i][k] != 0) {
                set.add(arr[i][k]);
            }
        }

        //找同一列的
        for (int k = 0; k < m; k++) {
            if (arr[k][j] != 0) {
                set.add(arr[k][j]);
            }
        }
        //至此,将a[i][j] 的同一行和同一列的 大于0 的元素放入了set中

        //如果 只有一个元素没放进来, 那么这个 位置 确定了
        if (set.size() == m - 1) {
            for (int k = 0; k < m; k++) {
                if (!set.contains(k + 1)) {
                    arr[i][j] = k + 1;
                }
            }
            set.clear();
            count--;
        }
    }

}
