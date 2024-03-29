/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.abc.gui;

import com.abc.gui.user.admin.AdminSignup;
import com.abc.gui.user.ViewAccount;
import com.abc.model.dao.AdminDAO;
import com.abc.model.dto.AdminDTO;
import com.abc.model.dto.UserDTO;
import com.abc.service.gui.GUIManager;
import com.formdev.flatlaf.ui.FlatTextBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author nipun
 */
public class App extends javax.swing.JFrame {

	public GUIManager guiManager;
	private final JPanel mainPanel;
	public Class currentContent;
	public UserDTO user;
	public MainPanel home;

	/**
	 * Creates new form App
	 */
	public App() {
		initComponents();
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		home = new Welcome();
		mainPanel = new JPanel();
		this.add(mainPanel, BorderLayout.CENTER);
		guiManager = GUIManager.getManager(this.getContentPane(), mainPanel);
		JButton[] btns = new JButton[]{btnNext, btnBack, btnLogin, btnSignup};
		for (JButton btn : btns) {
			btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.setBorderPainted(false);
			btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		lblTitle.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setMinimumSize(new Dimension(1000, 700));
		setPreferredSize(new Dimension(1200, 800));

		System.out.println(upperLeftPanel.getLayout().getClass());
	}

	public void showAlert(Component parent, String msg, String title, int messageType) {
		JOptionPane.showMessageDialog(parent, msg, title, messageType);
	}

	public final void adminCheck() {
		AdminDAO adminDAO = new AdminDAO();
		try {
			List<AdminDTO> admins = adminDAO.getAll();
			if (!admins.isEmpty()) {
				return;
			}

			AdminSignup adminSignup = new AdminSignup(this, true);
			adminSignup.setVisible(true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void updateTopPanel() {
		if (user == null) {
			btnLogin.setText("Login");
			btnSignup.setText("Signup");
			return;
		}
		btnLogin.setText("Logout");
		btnSignup.setText("Account");
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}

	public void setFieldError(JTextField field, Boolean error) {
		if (error) {

			field.setBorder(new LineBorder(new Color(255, 0, 0), 1, true) {
				@Override
				public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
					super.paintBorder(c, g, x, y, width, height);
				}
			});
			return;
		}

		field.setForeground(new Color(0, 0, 0));
		field.setBorder(new FlatTextBorder());
	}

	public void setFieldError(JTextField field, JLabel errLbl, String errMsg) {
		errLbl.setText(errMsg);
		errLbl.setVisible(true);

		field.setBorder(new LineBorder(new Color(255, 0, 0), 1, true) {
			@Override
			public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
				super.paintBorder(c, g, x, y, width, height);
			}
		});
	}

	public void removeFieldError(JTextField field, JLabel errLbl) {
		field.setForeground(new Color(0, 0, 0));
		field.setBorder(new FlatTextBorder());
		errLbl.setVisible(false);
		errLbl.setText(" ");
	}

	public void removeFieldError(JTextField field) {
		field.setForeground(new Color(0, 0, 0));
		field.setBorder(new FlatTextBorder());
	}

	private void loginButtonOperation() {
		if (user == null && currentContent.equals(LoginContent.class)) {
			return;
		}
		if (user == null) {
			guiManager.setContent(new LoginAndSignup(new LoginContent()));
			return;
		}
		int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Warning!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		if (option == JOptionPane.YES_OPTION) {
			user = null;
			guiManager.clearHistory();
			home = new Welcome();
			guiManager.setContent(home);
			updateTopPanel();
		}
	}

	private void signupButtonOperation() {
		if (user == null && currentContent.equals(SignupContent.class)) {
			return;
		}
		if (user == null) {
			guiManager.setContent(new LoginAndSignup(new SignupContent()));
			updateTopPanel();
			return;
		}
		guiManager.setContent(new ViewAccount());
		updateTopPanel();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        upperLeftPanel = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        upperRightPanel = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        btnSignup = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1500, 900));
        setPreferredSize(new java.awt.Dimension(1600, 950));
        setSize(new java.awt.Dimension(1200, 700));

        topPanel.setBackground(new java.awt.Color(5, 55, 70));
        topPanel.setMinimumSize(new java.awt.Dimension(800, 540));
        topPanel.setPreferredSize(new java.awt.Dimension(1200, 50));
        topPanel.setLayout(new java.awt.BorderLayout());

        upperLeftPanel.setOpaque(false);
        upperLeftPanel.setPreferredSize(new java.awt.Dimension(200, 40));

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/assets/icons/previous-icon.png"))); // NOI18N
        btnBack.setBorderPainted(false);
        btnBack.setContentAreaFilled(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBack.setPreferredSize(new java.awt.Dimension(30, 30));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/abc/assets/icons/next-icon.png"))); // NOI18N
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(false);
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.setPreferredSize(new java.awt.Dimension(30, 30));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout upperLeftPanelLayout = new javax.swing.GroupLayout(upperLeftPanel);
        upperLeftPanel.setLayout(upperLeftPanelLayout);
        upperLeftPanelLayout.setHorizontalGroup(
            upperLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperLeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        upperLeftPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBack, btnNext});

        upperLeftPanelLayout.setVerticalGroup(
            upperLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperLeftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        upperLeftPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBack, btnNext});

        topPanel.add(upperLeftPanel, java.awt.BorderLayout.LINE_START);

        upperRightPanel.setOpaque(false);
        upperRightPanel.setPreferredSize(new java.awt.Dimension(250, 40));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10);
        flowLayout1.setAlignOnBaseline(true);
        upperRightPanel.setLayout(flowLayout1);

        btnLogin.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setBorderPainted(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.setPreferredSize(new java.awt.Dimension(110, 30));
        btnLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnLoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnLoginFocusLost(evt);
            }
        });
        btnLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLoginMouseExited(evt);
            }
        });
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        btnLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnLoginKeyReleased(evt);
            }
        });
        upperRightPanel.add(btnLogin);

        btnSignup.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        btnSignup.setForeground(new java.awt.Color(255, 255, 255));
        btnSignup.setText("Signup");
        btnSignup.setBorderPainted(false);
        btnSignup.setContentAreaFilled(false);
        btnSignup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignup.setPreferredSize(new java.awt.Dimension(110, 30));
        btnSignup.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnSignupFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btnSignupFocusLost(evt);
            }
        });
        btnSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSignupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSignupMouseExited(evt);
            }
        });
        btnSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignupActionPerformed(evt);
            }
        });
        btnSignup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnSignupKeyReleased(evt);
            }
        });
        upperRightPanel.add(btnSignup);

        topPanel.add(upperRightPanel, java.awt.BorderLayout.LINE_END);

        lblTitle.setFont(new java.awt.Font("Silom", 2, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("ABC University");
        lblTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTitleMouseClicked(evt);
            }
        });
        topPanel.add(lblTitle, java.awt.BorderLayout.CENTER);

        getContentPane().add(topPanel, java.awt.BorderLayout.NORTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseEntered
		btnLogin.setForeground(new Color(247, 93, 59));
    }//GEN-LAST:event_btnLoginMouseEntered

    private void btnLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoginMouseExited
		btnLogin.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnLoginMouseExited

    private void btnSignupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseEntered
		btnSignup.setForeground(new Color(247, 93, 59));
    }//GEN-LAST:event_btnSignupMouseEntered

    private void btnSignupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignupMouseExited
		btnSignup.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSignupMouseExited

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
		guiManager.previous();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
		guiManager.next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
		loginButtonOperation();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
		signupButtonOperation();
    }//GEN-LAST:event_btnSignupActionPerformed

    private void lblTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTitleMouseClicked
		guiManager.setContent(home);
    }//GEN-LAST:event_lblTitleMouseClicked

    private void btnLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnLoginFocusGained
		btnLogin.setForeground(new Color(247, 93, 59));
    }//GEN-LAST:event_btnLoginFocusGained

    private void btnLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnLoginFocusLost
		btnLogin.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnLoginFocusLost

    private void btnSignupFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnSignupFocusGained
		btnSignup.setForeground(new Color(247, 93, 59));
    }//GEN-LAST:event_btnSignupFocusGained

    private void btnSignupFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnSignupFocusLost
		btnSignup.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_btnSignupFocusLost

    private void btnLoginKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLoginKeyReleased
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			loginButtonOperation();
		}
    }//GEN-LAST:event_btnLoginKeyReleased

    private void btnSignupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSignupKeyReleased
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			signupButtonOperation();
		}
    }//GEN-LAST:event_btnSignupKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSignup;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPanel upperLeftPanel;
    private javax.swing.JPanel upperRightPanel;
    // End of variables declaration//GEN-END:variables
}
