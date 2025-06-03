package UI;

import func.MakeDiffArr;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener{
    private static final int PUZZLELENGTH = 4;
    private static final int KEYLEFT = 37;
    private static final int KEYUP = 38;
    private static final int KEYRIGHT = 39;
    private static final int KEYDOWN = 40;

    private int[] arr = MakeDiffArr.makeDiffArr();

    private int blankX;
    private int blankY;

    private String picturePath = "image\\animal\\animal4\\";
    private final String backgroundPath = "image\\background.png";

    private int step;
    private boolean isWim;

    private JMenuItem replayItem;
    private JMenuItem reLoginItem;
    private JMenuItem accountItem;
    private JMenuItem closeGameItem;
    private JMenuItem girl;
    private JMenuItem animal;
    private JMenuItem sport;


    public GameJFrame(){
        System.out.println();
        //框架制作
        initFrame();

        //菜单制作
        initMenuBar();
        
        //图片添加
        initIcon();

        //设置可见
        this.setVisible(true);
    }

    //图片添加 &
    private void initIcon() {
        //清空界面
        if(!isWim) this.getContentPane().removeAll();
        //步数显示
        showStep();
        //加载游戏图片
        for (int i = 0; i < PUZZLELENGTH * PUZZLELENGTH; i++) {
            if(arr[i] == 0) {
                blankX = i % PUZZLELENGTH;
                blankY = i / PUZZLELENGTH;
            }
            ImageIcon imageIcon = new ImageIcon(picturePath + arr[i] + ".jpg");
            JLabel jLable = new JLabel(imageIcon);
            jLable.setBounds((i % PUZZLELENGTH) * 105 + 83, (i / PUZZLELENGTH) * 105 + 134, 105, 105);
            jLable.setBorder(new BevelBorder(BevelBorder.LOWERED));
            this.getContentPane().add(jLable);
        }
        //设置背景图片
        JLabel jLable = new JLabel(new ImageIcon(backgroundPath));
        jLable.setBounds(40, 40, 508, 560);
        this.getContentPane().add(jLable);

        //刷新界面
        this.getContentPane().repaint();
    }

    private void showWin() {
        System.out.println("win");
        this.getContentPane().removeAll();
        JLabel jLabel = new JLabel(new ImageIcon("image\\win.png"));
        jLabel.setBounds(203, 283, 197, 73);
        this.getContentPane().add(jLabel);
        this.getContentPane().repaint();
    }

    private boolean isWin() {
        for (int i = 1; i < 16; i++) {
            if(arr[i - 1] != i) return false;
        }
        this.isWim = true;
        return true;
    }

    private void showStep() {
        JLabel jLabel = new JLabel("步数：" + this.step);
        jLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(jLabel);
    }

    //框架制作
    private void initFrame() {
        //刷新数据
        this.step = 0;
        this.isWim = false;
        //设置大小与标题
        this.setSize(603, 680);
        this.setTitle("拼图小游戏");
        //设置在最顶层
        this.setAlwaysOnTop(true);
        //设置在画面的中心
        this.setLocationRelativeTo(null);
        //设置在关闭窗口的时候退出程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //关闭布局管理器
        this.setLayout(null);

        //设置键盘监听
        this.addKeyListener(this);
    }

    //菜单制作
    private void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutUsJMenu = new JMenu("关于我们");
        //创建更换图片
        JMenu changeImage = new JMenu("更换图片");

        this.replayItem = new JMenuItem("重新游戏");
        this.reLoginItem = new JMenuItem("重新登录");
        this.accountItem = new JMenuItem("公众号");
        this.closeGameItem = new JMenuItem("关闭游戏");
        this.girl = new JMenuItem("美女");
        this.animal = new JMenuItem("动物");
        this.sport = new JMenuItem("运动");

        //把美女，动物，运动添加到更换图片当中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);


        //为每个栏添加监听事件
        this.replayItem.addActionListener(this);
        this.closeGameItem.addActionListener(this);
        this.reLoginItem.addActionListener(this);
        this.accountItem.addActionListener(this);
        this.girl.addActionListener(this);
        this.animal.addActionListener(this);
        this.sport.addActionListener(this);


        functionJMenu.add(changeImage);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeGameItem);

        aboutUsJMenu.add(accountItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutUsJMenu);

        this.setJMenuBar(jMenuBar);
    }

    //图片移动
    private void moveEvent(int diffX, int diffY) {
        int x = this.blankX + diffX, y = this.blankY + diffY;

        if(x >= 0 && x < PUZZLELENGTH && y >= 0 && y < PUZZLELENGTH){
            int temp = arr[this.blankY * PUZZLELENGTH + this.blankX];
            arr[this.blankY * PUZZLELENGTH + this.blankX] = arr[y * PUZZLELENGTH + x];
            arr[y * PUZZLELENGTH + x] = temp;
            step++; //刷新步数
            //游戏胜利判断
            if(isWin()) winOperate();
            initIcon();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(this.isWim) return;
        if(e.getKeyCode() == 65) showAllPicture();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(this.isWim) return;
        switch (e.getKeyCode()){
            case KEYLEFT -> moveEvent(-1, 0);
            case KEYUP -> moveEvent(0, -1);
            case KEYRIGHT -> moveEvent(1, 0);
            case KEYDOWN -> moveEvent(0, 1);
            case 65 -> initIcon();
            case 87 -> winOperate();
            default -> System.out.println(e.getKeyCode());
        }
    }

    private void winOperate() {
        this.arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};
        this.isWim = true;
        showWin();
        this.initIcon();
    }

    private void showAllPicture() {
        //加载源图片
        this.getContentPane().removeAll();
        JLabel jLabel = new JLabel(new ImageIcon(picturePath + "all.jpg"));
        jLabel.setBounds(84, 135, 420, 420);
        this.getContentPane().add(jLabel);

        //设置背景图片
        JLabel jLable1 = new JLabel(new ImageIcon(backgroundPath));
        jLable1.setBounds(40, 40, 508, 560);
        this.getContentPane().add(jLable1);

        this.getContentPane().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replayItem){
            replay();
        }else if(obj == reLoginItem){
            System.out.println("重新登录");
        }else if(obj == closeGameItem){
            System.exit(0);
        }else if(obj == accountItem){
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            jLabel.setBounds(0, 0, 258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }else{
            replacePicture(obj);
        }
    }

    private void replay() {
        this.arr = MakeDiffArr.makeDiffArr();
        this.step = 0;
        initIcon();
    }

    private void replacePicture(Object obj) {
        Random r = new Random();
        int pictureCnt;
        if(obj == girl){
            pictureCnt = 11;
            this.picturePath = "image\\girl\\girl" + (r.nextInt(pictureCnt) + 1) + "\\";
        }else if(obj == animal){
            pictureCnt = 8;
            this.picturePath = "image\\animal\\animal" + (r.nextInt(pictureCnt) + 1) + "\\";
        }else if(obj == sport){
            pictureCnt = 10;
            this.picturePath = "image\\sport\\sport" + (r.nextInt(pictureCnt) + 1) + "\\";
        }
        replay();
    }
}
