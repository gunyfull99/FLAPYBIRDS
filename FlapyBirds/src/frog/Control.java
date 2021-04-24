package frog;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Control {

    private FrogGame f;
    ImageIcon icon = new ImageIcon("./frog.png");
    JButton btnF = new JButton(icon);//tạo button với icon hình con cóc

    // private Timer timer;
    List<JButton> list = new ArrayList<>(); //list để lưu các button là các thanh chướng ngại vật
    int yfrog = 40;//chiều cao của con cóc
    int mark = 0;
    int count = 120;
    Key key = new Key();
    private boolean checkSave = false;

    JButton btnDown = new JButton();
    JButton btnUp1 = new JButton();
    JButton btnDown1 = new JButton();
    JButton btnUp2 = new JButton();
    JButton btnDown2 = new JButton();
    JButton btnUp = new JButton();

    public Control(FrogGame f) {
        this.f = f;
        f.getjButton2().addKeyListener(key);
        btnDown.addKeyListener(key);
        btnDown1.addKeyListener(key);
        btnDown2.addKeyListener(key);
        btnUp.addKeyListener(key);
        btnUp1.addKeyListener(key);
        btnUp2.addKeyListener(key);

        f.getjButton3().addKeyListener(key);
        f.getjButton4().addKeyListener(key);
        f.getjPanel1().add(btnF);
        btnF.addKeyListener(key);
    }

    public void save() {
        checkSave = true;
        try {
            FileWriter fw = new FileWriter("data.txt");//tạo file
            BufferedWriter bw = new BufferedWriter(fw);// viết vào file
            for (JButton btn : list) {
                int x = btn.getX();// lấy tọa độ X của button
                int y = btn.getY();//lấy tọa độ Y của button
                int width = btn.getWidth();//lấy chiều dài của button
                int height = btn.getHeight();//lấy chiều cao của button
                bw.write(x + ";" + y + ";" + width + ";" + height);
                bw.newLine();
            }
            bw.write(count + ";" + mark + ";" + yfrog);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
        }
    }

    public boolean checkTouch() {
        if (btnF.getY() <= 0 || btnF.getY() >= 250) {
//nếu tọa độ Y của button chạm bên trên hoặc bên dưới thì trả về true
            return true;
        }
        Rectangle bf = new Rectangle(btnF.getX(), btnF.getY(), btnF.getWidth(), btnF.getHeight());
//tạo ra 1 hình chữ nhật có các 
        //chỉ số giống với con cóc
        for (JButton btn : list) {
            Rectangle b = new Rectangle(btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight());
//tạo ra 1 hình chữ nhật có các chỉ số giống với các chướng ngại vật
            if (bf.intersects(b)) { // nếu 2 hình chữ nhật chạm vào nhâu thì trả về true
                return true;
            }
        }
        return false;
    }
    int n = 80;
    Random r = new Random();
    int hup1 = r.nextInt(160 - 90) + 90;
    int hup2 = r.nextInt(114) + 30;
    int hup3 = r.nextInt(114) + 30;

    public void addButton(int hup) {

        hup = hup1;
        btnUp.setBounds(f.getjPanel1().getWidth(), 0, 40, hup);//set tọa độ thanh button trên

        btnDown.setBounds(f.getjPanel1().getWidth(), hup + 90, 40, f.jPanel1.getHeight() - (hup + 90));
        // set tọa độ thanh button dưới 
        list.add(btnUp);
        list.add(btnDown);
        f.getjPanel1().add(btnUp);
        f.getjPanel1().add(btnDown);
    }

    public void addButton1(int hup) {
        hup = hup2;
        btnUp1.setBounds(f.getjPanel1().getWidth(), 0, 40, hup);//set tọa độ thanh button trên

        btnDown1.setBounds(f.getjPanel1().getWidth(), hup + 90, 40, f.jPanel1.getHeight() - (hup + 90));
        // set tọa độ thanh button dưới 
        list.add(btnUp1);
        list.add(btnDown1);
        f.getjPanel1().add(btnUp1);
        f.getjPanel1().add(btnDown1);
    }

    public void addButton2(int hup) {
        hup = hup3;
        btnUp2.setBounds(f.getjPanel1().getWidth(), 0, 40, hup);//set tọa độ thanh button trên

        btnDown2.setBounds(f.getjPanel1().getWidth(), hup + 90, 40, f.jPanel1.getHeight() - (hup + 90));
        // set tọa độ thanh button dưới 
        list.add(btnUp2);
        list.add(btnDown2);
        f.getjPanel1().add(btnUp2);
        f.getjPanel1().add(btnDown2);
    }

    public void showMes() {
        if (checkSave == false) {
            Object mes[] = {"New Game", "Exit"};//
            int option = JOptionPane.showOptionDialog(null, "Do you want to continue?",
                    "Notice!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, mes, mes[0]);
            if (option == 0) {
                n1 = 10;
                f.jPanel1.removeAll();
                f.jPanel1.repaint();
                list.clear();
                mark = 0;
                count = 120;
                f.jLabel1.setText("Point: 0");
                yfrog = 40;
                f.getjPanel1().add(btnF);
                //timer.restart();
            }
            if (option == 1) {
                System.exit(0);
            }
        } else {
            Object mes[] = {"New Game", "Continue", "Exit"};
            int option = JOptionPane.showOptionDialog(null, "Do you want to continue?",
                    "Notice!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, mes, mes[0]);
            if (option == 0) {
                f.jPanel1.removeAll();
                n1 = 10;
                f.jPanel1.repaint();
                list.clear();
                mark = 0;
                count = 120;
                f.jLabel1.setText("Point: 0");
                yfrog = 40;
                f.getjPanel1().add(btnF);
                // timer.restart();
            }
            if (option == 1) {
                f.jPanel1.removeAll();
                f.jPanel1.repaint();
                list.clear();
                try {
                    FileReader fr = new FileReader("data.txt");//đọc file
                    BufferedReader br = new BufferedReader(fr);
                    String line = "";
                    do {
                        line = br.readLine().trim();
                        if (line == null) {
                            break;
                        }
                        JButton btn = new JButton();
                        String txt[] = line.split(";");//tách các chỉ số tại ";"
                        int arr[] = new int[txt.length];
                        for (int i = 0; i < arr.length; i++) {
                            arr[i] = Integer.parseInt(txt[i]);
                        }
                        if (arr.length == 4) {//nếu mảng có độ dài 4 nghĩa là dòng của các chỉ số button
                            btn.setBounds(arr[0], arr[1], arr[2], arr[3]);//set lại các button
                            list.add(btn);
                            f.jPanel1.add(btn);
                        } else {// 
                            count = arr[0];
                            mark = arr[1];
                            f.jLabel1.setText("Point: " + mark / 2);
                            yfrog = arr[2];
                        }
                    } while (true);
                    br.close();
                } catch (Exception e) {
                }
                f.getjPanel1().add(btnF);
                // timer.restart();
            }
            if (option == 2) {
                System.exit(0);
            }
        }
    }
    // boolean checkPause = false;

    public void pause() {
        if (check == false) {
            // timer.stop();
            check = true;
        } else {
            //timer.restart();
            check = false;
        }
    }

    public void getMark() {
        for (JButton btn : list) {
            if (btnF.getX() == btn.getX()) {
                mark++;
                f.jLabel1.setText("Point: " + mark / 2);
            }
        }
    }
    int n1 = 10;
    boolean check = true;

    public void run() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (mark >= 2) {
                            n1 = 9;
                        }
                        if (check) {
                            if (key.isPress()) {
                                yfrog = yfrog - 30;// nút trên bàn phím dc nhấn thì con cóc sẽ nhảy lên
                                key.setPress(false);
                            }
                            yfrog++;//con cóc rơi theo thời gian

                            btnF.setBounds(60, yfrog, 30, 30);//set lại vị trí con cóc

                            for (int i = 0; i < list.size(); i++) {
                                int x = list.get(i).getBounds().x - 1;//trừ tọa độ của x theo thời gian
                                int y = list.get(i).getBounds().y;
                                list.get(i).setLocation(x, y);//set lại tọa độ của các button chướng ngại vật
                                if (x <= -40) {
                                    hup1 = r.nextInt(114) + 30;
                                    if (i == 0) {
                                        list.get(i).setBounds(600, y, 40, hup1);
                                    }
                                    if (i == 2) {
                                        list.get(i).setBounds(600, y, 40, hup1);
                                    }
                                    if (i == 4) {
                                        list.get(i).setBounds(600, y, 40, hup1);
                                    }
                                    if (i == 1) {
                                        list.get(i).setBounds(600, hup1 + 90, 40, f.jPanel1.getHeight() - (hup1 + 90));
                                    }
                                    if (i == 3) {
                                        list.get(i).setBounds(600, hup1 + 90, 40, f.jPanel1.getHeight() - (hup1 + 90));
                                    }
                                    if (i == 5) {
                                        list.get(i).setBounds(600, hup1 + 90, 40, f.jPanel1.getHeight() - (hup1 + 90));
                                    }

                                }
                            }
                            getMark();
                            count++;
                            if (count == 121) {
                                addButton(hup1);
                            }
                            if (count == 242) {
                                addButton1(hup2);
                            }
                            if (count == 363) {
                                addButton2(hup3);
                            }
                            if (checkTouch()) {
                                showMes();
                            }
                        }
                        Thread.sleep(n1);

                    } catch (Exception e) {
                        Logger.getLogger(FrogGame.class.getName()).log(Level.SEVERE, null, e);
                    }

                }
            }
        };
        thread.start();
//        timer = new Timer(15, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (mark >= 1) {
//
//                }
//               
//            }
//        });
//        timer.start();

    }

}
