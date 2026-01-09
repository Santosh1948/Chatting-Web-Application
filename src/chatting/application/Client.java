package chatting.application;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class Client implements ActionListener {

    JTextField text;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream dout;
    static JFrame f;   

    Client() {
        f = new JFrame();
        f.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0, 450,70);
        p1.setLayout(null);
        f.add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        JLabel back = new JLabel(new ImageIcon(i2));
        back.setBounds(5,20,25,25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        JLabel profile = new JLabel(new ImageIcon(i5));
        profile.setBounds(40,10,50,50);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        JLabel video = new JLabel(new ImageIcon(i8));
        video.setBounds(300,20,30,30);
        p1.add(video);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        JLabel call = new JLabel(new ImageIcon(i11));
        call.setBounds(360,20,35,30);
        p1.add(call);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        JLabel setting = new JLabel(new ImageIcon(i14));
        setting.setBounds(420,20,10,25);
        p1.add(setting);

        JLabel name = new JLabel("DOOM");
        name.setBounds(110,15,100,10);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SAN_SERIF", Font.BOLD,14));
        p1.add(name);

        JLabel status = new JLabel("Active Now");
        status.setBounds(110,35,100,10);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SAN_SERIF", Font.BOLD,14));
        p1.add(status);

        a1 = new JPanel();
        a1.setLayout(new BorderLayout());
        f.setUndecorated(true);

        JScrollPane sp = new JScrollPane(a1);
        sp.setBounds(5,75,440,570);
        sp.setBorder(null);
        f.add(sp);

        text = new JTextField();
        text.setBounds(5,655,310,40);
        text.setFont(new Font("Railway", Font.PLAIN,16));
        f.add(text);

        JButton send = new JButton("Send");
        send.setBounds(320,655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("Railway", Font.PLAIN,16));
        send.addActionListener(this);
        f.add(send);

        f.setSize(450,700);
        f.setLocation(800,50);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
        connect();
    }
    private void connect() {
        try {
            Socket s = new Socket("127.0.0.1",9999);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String msg = din.readUTF();
                        SwingUtilities.invokeLater(() -> {
                            JPanel panel = formatLabel(msg);
                            JPanel left = new JPanel(new BorderLayout());
                            left.add(panel,BorderLayout.LINE_START);
                            vertical.add(left);
                            vertical.add(Box.createVerticalStrut(15));
                            a1.add(vertical,BorderLayout.PAGE_START);
                            f.validate();
                        });
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }).start();

        } catch (Exception e) {
            System.out.print(e);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String out = text.getText();
            if(out.isEmpty()) return;

            JPanel p2 = formatLabel(out);
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);

            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical, BorderLayout.PAGE_START);

            dout.writeUTF(out);   
            text.setText("");
            f.validate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel output = new JLabel("<html><p style='width:150px'>" + out + "</p></html>");
        output.setFont(new Font("Railway", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(BorderFactory.createEmptyBorder(15,15,15,50));

        panel.add(output);

        JLabel time = new JLabel(
                new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime())
        );
        panel.add(time);

        return panel;
    }

    public static void main(String[] args) {
        new Client();
    }
}
