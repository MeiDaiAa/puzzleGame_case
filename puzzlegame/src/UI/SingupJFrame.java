package UI;

import javax.swing.*;

public class SingupJFrame extends JFrame {
    public SingupJFrame(){
        //设置大小与标题
        this.setSize(488, 500);
        this.setTitle("注册界面");
        //设置在最顶层
        this.setAlwaysOnTop(true);
        //设置在画面的中心
        this.setLocationRelativeTo(null);
        //设置在关闭窗口的时候退出程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //设置可见
        this.setVisible(true);
    }
}
