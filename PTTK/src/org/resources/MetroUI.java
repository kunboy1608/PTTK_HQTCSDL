package org.resources;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.metal.MetalTextFieldUI;
import jiconfont.IconFontSwing;
import jiconfont.icons.google_material_design_icons.GoogleMaterialDesignIcons;

public class MetroUI {
    
    public static final Color COLOR_WHITE = Color.WHITE;
    public static final Color COLOR_BACKGROUND = new Color(51, 51, 51);
    public static final Color COLOR_BACKGROUND_SELECTED = Color.GRAY;
    
    public static final Insets metroMargin = new Insets(0, 10, 0, 10);
    public static final Border selectedBorder = MetroUI.getBorder(Color.WHITE, COLOR_BACKGROUND, 3, 10);
    public static final Border unselectedBorder = MetroUI.getBorder(COLOR_BACKGROUND, COLOR_BACKGROUND, 0, 13);
    public static final Border hoverBorder = MetroUI.getBorder(Color.GRAY, COLOR_BACKGROUND, 3, 10);
    public static final Border buttonBorder = MetroUI.getBorder(Color.BLACK, Color.WHITE, 2, 0);
    public static final Border textFieldBorder = MetroUI.getBorder(Color.BLACK, Color.WHITE, 1, 10);
    public static final Border voidBorder = MetroUI.getBorder(COLOR_WHITE, Color.WHITE, 0, 0);
    
    public static GoogleMaterialDesignIcons icons;
    
    public static void notificate(String title, 
                                    String message,
                                    TrayIcon.MessageType type) {
        SystemTray sysTray = SystemTray.getSystemTray();
        //setting the icon
        TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage("icon.jpg"), "");
        trayIcon.setImageAutoSize(true);
        try {
            //setting and display the notification
            sysTray.add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
        trayIcon.displayMessage(title, message, type);
    }
            
    public static Border getBorder(Color lineColor, Color paddingColor, int width, int padding) {
        return BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(lineColor, width),
                    BorderFactory.createLineBorder(paddingColor, padding)
               );
    }
    
    public static void applySpecilized(JButton b) {
        Dimension bd = b.getSize();
        Point bp = b.getLocation();
        
        b.setBackground(COLOR_BACKGROUND);
        b.setForeground(COLOR_WHITE);
        b.setBorder(voidBorder);
        b.setContentAreaFilled(true);
        b.setUI(new MetalButtonUI() { });
        b.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) { }

            @Override
            public void mousePressed(MouseEvent me) {
                b.setBackground(COLOR_BACKGROUND_SELECTED);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                b.setBackground(COLOR_BACKGROUND);
            }

            @Override
            public void mouseEntered(MouseEvent me) { }

            @Override
            public void mouseExited(MouseEvent me) { }  
        });
    }
    
    public static void apply(JToggleButton b) {
        b.setBorder(null);
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setMargin(metroMargin);
        b.setUI(new MetalButtonUI() {
            @Override
            protected Color getSelectColor() {
                return new Color(51, 51, 51);
            }
        }
        );
        if (b.isSelected()) {
            b.setFont(b.getFont().deriveFont(
                            Collections.singletonMap(TextAttribute.WEIGHT,  TextAttribute.WEIGHT_BOLD)
                    ));
            b.setBorder(selectedBorder);
        }
        b.addChangeListener((ChangeEvent evt) -> {
            if (b.isSelected()) {
                b.setFont(b.getFont().deriveFont(
                        Collections.singletonMap(TextAttribute.WEIGHT,  TextAttribute.WEIGHT_BOLD)
                ));
                b.setBorder(selectedBorder);
            } else {
                b.setFont(b.getFont().deriveFont(
                        Collections.singletonMap(TextAttribute.WEIGHT,  TextAttribute.WEIGHT_MEDIUM)
                ));
                b.setBorder(unselectedBorder);
            }
        });
        b.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {}

            @Override
            public void mousePressed(MouseEvent me) {}

            @Override
            public void mouseReleased(MouseEvent me) {}

            @Override
            public void mouseEntered(MouseEvent me) {
                if (!b.isSelected()) {
                    b.setBorder(hoverBorder);
                }
            }
            @Override
            public void mouseExited(MouseEvent me) {
                if (!b.isSelected()) {
                    b.setBorder(unselectedBorder);
                }
            }
            
        });
    }
    public static void apply(JButton b) {

        b.setBackground(Color.WHITE);
        b.setBorder(buttonBorder);
        b.setContentAreaFilled(true);
        b.setUI(new MetalButtonUI() { });
        b.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) { }

            @Override
            public void mousePressed(MouseEvent me) {
                b.setBackground(Color.GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                b.setBackground(Color.WHITE);
            }

            @Override
            public void mouseEntered(MouseEvent me) { }

            @Override
            public void mouseExited(MouseEvent me) { }
            
        });
    }   
    public static void apply(JTextField tf, String placeHolder) {
        tf.setUI(new MetalTextFieldUI());
        tf.setBorder(textFieldBorder);
        tf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (tf.getText().equals(placeHolder)) {
                    tf.setText("");
                    tf.setForeground(Color.BLACK);
                } 
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (tf.getText().equals("")) {
                     tf.setText(placeHolder);
                    tf.setForeground(Color.GRAY);
                }
            }
        });
    }

    
    public static Icon getGoogleIcon(GoogleMaterialDesignIcons iconStyle, int size, Color color) {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        return IconFontSwing.buildIcon(iconStyle, size, color);
    }
    
}
