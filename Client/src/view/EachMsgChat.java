package view;

import controller.Client;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import storage.Message;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author holohoi
 */
public class EachMsgChat extends javax.swing.JPanel {

    private javax.swing.JLabel auth;
    private javax.swing.JLabel content;
    private javax.swing.JLabel time;
    public boolean mine;

    public EachMsgChat() {
//        initComponents();
    }

    public EachMsgChat(Object[] msg) {
        String sendBy = msg[3].toString();
        try {
            int idSendBy = Integer.parseInt(sendBy);
            this.mine = idSendBy == Client.user.getId();
        } catch (NumberFormatException ex) {
            this.mine = false;
        }
        String objSendAt = msg[2].toString();
        Timestamp date2 = Timestamp.valueOf(objSendAt);

        String _time;
        if (isRightDate(date2)) {
            _time = new SimpleDateFormat("hh:mm a").format(date2);
        } else {
            _time = new SimpleDateFormat("hh:mm a dd/MM/yyyy").format(date2);
        }

        String _content = "<html><p>" + msg[1].toString() + "</p></html>";
        String[] tempName = msg[3].toString().split(" ");
        String _auth = tempName[tempName.length - 1];
        if (mine) {
            initComponentsRight(_content, _time);
        } else {
            initComponentsLeft(_content, _time, _auth);
        }
    }

//    public EachMsgChat(boolean _mine, String _content, String _time) {
//        this.mine = _mine;
//
//        if (_mine) {
//            initComponentsRight(_content, _time);
//        } else {
//            initComponentsLeft(_content, _time);
//
//        }
//    }
    private void initComponentsRight(String _content, String _time) {
        auth = new javax.swing.JLabel();
        content = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        this.setBackground(new java.awt.Color(116, 180, 224));

//        this.setAutoscrolls(true);
//        this.setOpaque(false);
        auth.setForeground(new java.awt.Color(0, 0, 0));
        auth.setText("You");

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        content.setForeground(new java.awt.Color(0, 0, 0));
        content.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        content.setText(_content);

//set border
        Color right = Color.magenta.darker();
        content.setBorder(new BorderMsgChat(right, 2, 14, 10, SwingConstants.RIGHT));
//end set border
        content.setMinimumSize(new java.awt.Dimension(0, 0));
//        content.setMaximumSize(new java.awt.Dimension(0, 0));

        content.setOpaque(true);

        time.setForeground(new java.awt.Color(0, 0, 0));
        time.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        time.setText(_time);

//        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
//        this.setLayout(thisLayout);
//        thisLayout.setHorizontalGroup(
//                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisLayout.createSequentialGroup()
//                                .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(auth))
//        );
//        thisLayout.setVerticalGroup(
//                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(auth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
        this.setLayout(thisLayout);
        thisLayout.setHorizontalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(time)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(auth))
        );
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(auth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void initComponentsLeft(String _content, String _time, String _auth) {
        auth = new javax.swing.JLabel();
        content = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        this.setBackground(new java.awt.Color(116, 180, 224));

        time.setForeground(new java.awt.Color(0, 0, 0));
//        time.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        time.setText(_time);

        content.setBackground(new java.awt.Color(255, 255, 255));
        content.setFont(new java.awt.Font("Dialog", 0, 15)); // NOI18N
        content.setForeground(new java.awt.Color(0, 0, 0));
        content.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        content.setText(_content);

// set border
        Color left = Color.CYAN.darker();

        content.setBorder(new BorderMsgChat(left, 2, 14, 10, SwingConstants.LEFT));
// end set border
        content.setMinimumSize(new java.awt.Dimension(0, 0));
//        content.setMaximumSize(new java.awt.Dimension(0, 0));

        content.setOpaque(true);

        auth.setForeground(new java.awt.Color(0, 0, 0));
        auth.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        auth.setText(_auth);

//        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
//        this.setLayout(thisLayout);
//        thisLayout.setHorizontalGroup(
//                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, thisLayout.createSequentialGroup()
//                                .addComponent(auth)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        thisLayout.setVerticalGroup(
//                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                        .addComponent(auth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
        javax.swing.GroupLayout thisLayout = new javax.swing.GroupLayout(this);
        this.setLayout(thisLayout);
        thisLayout.setHorizontalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(thisLayout.createSequentialGroup()
                                .addComponent(auth)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time))
        );
        thisLayout.setVerticalGroup(
                thisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(auth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    boolean isRightDate(Timestamp date2) {
        java.sql.Date date1 = new java.sql.Date(System.currentTimeMillis());
//        Timestamp date2 = msgContact.getSendAt();
        return date1.getDay() == date2.getDay() && date1.getMonth() == date2.getMonth() && date1.getYear() == date2.getYear();
    }
}

class BorderMsgChat extends AbstractBorder {

    private static final long serialVersionUID = 1L;
    private Color color;
    private int thickness;

    private int radius;
    private int pointerSize = 0;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private double pointerPadPercent = 0.5;
    int pointerSide = SwingConstants.RIGHT;
    RenderingHints hints;

    BorderMsgChat(Color color) {
        this(color, 2, 4, 0, SwingConstants.RIGHT);
    }

    BorderMsgChat(Color color, int thickness, int radius, int pointerSize, int side) {
        this.color = color;
        this.thickness = thickness;
        this.radius = radius;
        this.pointerSize = pointerSize;
        this.pointerSide = side;

        hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        insets = new Insets(0, 0, 0, 0);

        setThickness(thickness);
    }

    // {{ Setter/Getter
    public Color getColor() {
        return color;
    }

    public BorderMsgChat setColor(Color color) {
        this.color = color;
        return this;
    }

    public double getPointerPadPercent() {
        return pointerPadPercent;
    }

    public BorderMsgChat setPointerPadPercent(double percent) {
        this.pointerPadPercent = percent > 1 ? 1 : percent;
        pointerPadPercent = pointerPadPercent < 0 ? 0 : pointerPadPercent;
        return this;
    }

    public int getThickness() {
        return thickness;
    }

    public int getRadius() {
        return radius;
    }

    public int getPointerSize() {
        return pointerSize;
    }

    public BorderMsgChat setThickness(int n) {
        thickness = n < 0 ? 0 : n;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        setPointerSize(pointerSize);
        return this;
    }

    public BorderMsgChat setPointerSize(int size) {
        pointerSize = size < 0 ? 0 : size;
        // 这里需要 radii/2 不然会有多出来的边距
        int pad = radius / 2 + strokePad;

        int pointerSidePad = pad + pointerSize + strokePad;
        // 根据不同的方向设置不同的Padding
        int left, right, bottom, top;
        left = right = bottom = top = pad;
        switch (pointerSide) {
            case SwingConstants.TOP:
                top = pointerSidePad;
                break;
            case SwingConstants.LEFT:
                left = pointerSidePad;
                break;
            case SwingConstants.RIGHT:
                right = pointerSidePad;
                break;
            default:
            case SwingConstants.BOTTOM:
                bottom = pointerSidePad;
                break;
        }
        insets.set(top, left, bottom, right);
        return this;
    }

    public int getPointerSide() {
        return pointerSide;
    }

    public BorderMsgChat setPointerSide(int pointerSide) {
        this.pointerSide = pointerSide;
        setPointerSize(pointerSize);
        return this;
    }

    public BorderMsgChat setRadius(int radius) {
        this.radius = radius;
        setPointerSize(pointerSize);
        return this;
    }

    // }}
    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        // 修正背景色的问题
//        g2.setBackground(c.getBackground());

        int bottomLineY = height - thickness - pointerSize;

        RoundRectangle2D.Double bubble;
        Polygon pointer = new Polygon();

        // 初始范围
        {
            // 设置圆角矩形
            int rx, ry, rw, rh;
            rx = ry = strokePad;
            rw = width - thickness;
            rh = height - thickness;

            switch (pointerSide) {
                case SwingConstants.LEFT:
                    rx += pointerSize;
                case SwingConstants.RIGHT:
                    rw -= pointerSize;
                    break;

                case SwingConstants.TOP:
                    ry += pointerSize;
                case SwingConstants.BOTTOM:
                default:
                    rh -= pointerSize;
                    break;
            }

            bubble = new RoundRectangle2D.Double(rx, ry, rw, rh, radius, radius);

            // 计算偏移
            int pointerPad;

            if (pointerSide == SwingConstants.LEFT || pointerSide == SwingConstants.RIGHT) {
                pointerPad = (int) (pointerPadPercent * (height - radius * 2 - pointerSize));
            } else {
                pointerPad = (int) (pointerPadPercent * (width - radius * 2 - pointerSize));
            }

            // 设置三角
            int basePad = strokePad + radius + pointerPad;

            switch (pointerSide) {

                case SwingConstants.LEFT:
                    pointer.addPoint(rx, basePad);// top
                    pointer.addPoint(rx, basePad + pointerSize);// bottom
                    pointer.addPoint(strokePad, basePad + pointerSize / 2);
                    break;
                case SwingConstants.RIGHT:
                    pointer.addPoint(rw, basePad);// top
                    pointer.addPoint(rw, basePad + pointerSize);// bottom
                    pointer.addPoint(width - strokePad, basePad + pointerSize / 2);
                    break;

                case SwingConstants.TOP:
                    pointer.addPoint(basePad, ry);// left
                    pointer.addPoint(basePad + pointerSize, ry);// right
                    pointer.addPoint(basePad + (pointerSize / 2), strokePad);
                    break;
                default:
                case SwingConstants.BOTTOM:
                    pointer.addPoint(basePad, rh);// left
                    pointer.addPoint(basePad + pointerSize, rh);// right
                    pointer.addPoint(basePad + (pointerSize / 2), height - strokePad);
                    break;
            }
        }

        Area area = new Area(bubble);
        area.add(new Area(pointer));

        g2.setRenderingHints(hints);

//        Area spareSpace = new Area(new Rectangle(0, 0, width, height));
//        spareSpace.subtract(area);
//        g2.setClip(spareSpace);
//        g2.clearRect(0, 0, width, height);
//
//        g2.setClip(null);
//        g2.setBackground(Color.black);
        Component parent = c.getParent();

        if (parent != null) {
            Color bg = parent.getBackground();
            Rectangle rect = new Rectangle(0, 0, width, height);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRect(0, 0, width, height);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
    }
}
