/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.abc.gui.user.admin;

import com.abc.exceptions.DataInsertException;
import com.abc.gui.LoginAndSignup;
import com.abc.gui.LoginContent;
import static com.abc.gui.Splash.APP;
import com.abc.model.dao.AdminDAO;
import com.abc.model.dao.EmailDAO;
import com.abc.model.dao.PasswordDAO;
import com.abc.model.dao.UserDAO;
import com.abc.model.dao.UserTypeDAO;
import com.abc.model.dto.AdminDTO;
import com.abc.model.dto.UserDTO;
import com.abc.util.MyPatterns;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author nipun
 */
public class AdminSignup extends javax.swing.JDialog {

    /**
     * Creates new form AdminSignup
     *
     * @param parent
     * @param modal
     */
    public AdminSignup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        clearErrors();
    }

    private void clearForm() {
        txtFname.setText(" ");
        txtLname.setText(" ");
        txtMobile.setText(" ");
        txtEmail.setText(" ");
        txtNewPass.setText(" ");
        txtConfirmPass.setText(" ");

        clearErrors();
    }

    private void clearErrors() {
        APP.removeFieldError(txtFname, lblLnameError);
        APP.removeFieldError(txtLname, lblFnameError);
        APP.removeFieldError(txtMobile, lblMobileError);
        APP.removeFieldError(txtEmail, lblEmailError);
        APP.removeFieldError(txtNewPass, lblNewPassError);
        APP.removeFieldError(txtConfirmPass, lblConfirmPassError);
    }

    private Map<String, String> validateData(String fname, String lname, String mobile, String email, String newPass, String confirmPass) {
        clearErrors();

        Map<String, String> errors = new HashMap<>();
        Matcher matcher = MyPatterns.EMAIL_CASE_SENSITIVE.matcher(email);

        if (!Pattern.compile("[a-zA-Z]+").matcher(fname).matches()) {
            errors.put("fname", "Invalid Name.");
        }
        if (!Pattern.compile("[a-zA-Z]+").matcher(lname).matches()) {
            errors.put("lname", "Invalid Name.");
        }
        if (!MyPatterns.MOBILE_PHONE.matcher(mobile).matches()) {
            errors.put("mobile", "Invalid Mobile Number.");
        }
        if (!matcher.matches()) {
            errors.put("email", "Invalid Email.");
        }

        if (newPass.length() < 6) {
            errors.put("newPass", "Password should have atleast 6 characters.");
        }

        if (newPass.isBlank()) {
            errors.put("newPass", "Password cannot be blank.");
            errors.put("confirmPass", "");
        }

        if (!newPass.equals(confirmPass)) {
            errors.put("confirmPass", "Passwords doesn't match.");
        }

        return errors;
    }

    private void signup() {
        clearErrors();
        String fname = txtFname.getText();
        String lname = txtLname.getText();
        String mobile = txtMobile.getText();
        String email = txtEmail.getText();
        String newPass = new String(txtNewPass.getPassword());
        String confirmPass = new String(txtConfirmPass.getPassword());

        Map<String, String> errors = validateData(fname, lname, mobile, email, newPass, confirmPass);

        if (!errors.isEmpty()) {
            if(errors.containsKey("fname")){
                APP.setFieldError(txtFname, lblFnameError, errors.get("fname"));
            }
            if(errors.containsKey("lname")){
                APP.setFieldError(txtLname, lblLnameError, errors.get("lname"));
            }
            if(errors.containsKey("mobile")){
                APP.setFieldError(txtMobile, lblMobileError, errors.get("mobile"));
            }
            if (errors.containsKey("email")) {
                APP.setFieldError(txtEmail, lblEmailError, errors.get("email"));
            }
            if (errors.containsKey("newPass")) {
                APP.setFieldError(txtNewPass, lblNewPassError, errors.get("newPass"));
            }
            if (errors.containsKey("confirmPass")) {
                APP.setFieldError(txtNewPass, lblNewPassError, errors.get("confirmPass"));
                APP.setFieldError(txtConfirmPass, lblConfirmPassError, errors.get("confirmPass"));
            }
            return;
        }

        EmailDAO emailDAO = new EmailDAO();
        UserDAO userDAO = new UserDAO();
        AdminDAO adminDAO = new AdminDAO();
        PasswordDAO passDAO = new PasswordDAO();
        UserTypeDAO userTypeDAO = new UserTypeDAO();
        
        try {
            if (emailDAO.isExist(email)) {
                APP.setFieldError(txtEmail, lblEmailError, "Existing Email.");
                return;
            }

            int emailId = emailDAO.save(email);
            int passId = passDAO.save(newPass);

            if(emailId < 1 || passId < 1){
                throw new DataInsertException();
            }
            
            AdminDTO admin = new AdminDTO(0, fname, lname, mobile, emailId);
            int adminId = adminDAO.save(admin);
            
            int userTypeId = userTypeDAO.getId("admin");
            
            UserDTO user = new UserDTO(0, emailId, passId, userTypeId);
            int userId = userDAO.save(user);

            if (userId < 1 || adminId < 1) {
                throw new DataInsertException();
            }

            APP.showAlert(this, "New User Registered!\nPlease login with your Email & Password.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            clearForm();
            this.dispose();
            APP.guiManager.setContent(new LoginAndSignup(new LoginContent()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        basePanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        btnSignup = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        midPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtFname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtLname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNewPass = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtConfirmPass = new javax.swing.JPasswordField();
        lblFnameError = new javax.swing.JLabel();
        lblLnameError = new javax.swing.JLabel();
        lblEmailError = new javax.swing.JLabel();
        lblNewPassError = new javax.swing.JLabel();
        lblConfirmPassError = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMobile = new javax.swing.JTextField();
        lblMobileError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 153));
        setMixingCutoutShape(null);
        setUndecorated(true);

        basePanel.setBackground(new java.awt.Color(255, 255, 255));

        bottomPanel.setOpaque(false);

        btnSignup.setBackground(new java.awt.Color(0, 204, 153));
        btnSignup.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnSignup.setForeground(new java.awt.Color(255, 255, 255));
        btnSignup.setText("Signup");
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

        btnClose.setBackground(new java.awt.Color(255, 153, 0));
        btnClose.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        btnClose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnCloseKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSignup)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        bottomPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClose, btnSignup});

        topPanel.setBackground(new java.awt.Color(255, 102, 0));

        lblTitle.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Admin Signup");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addContainerGap())
        );

        midPanel.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        jLabel1.setText("First Name:");

        txtFname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFnameKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        jLabel2.setText("Last Name:");

        txtLname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLnameKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        jLabel3.setText("Email:");

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        jLabel4.setText("New Password:");

        txtNewPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewPassKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        jLabel5.setText("Confirm Password:");

        txtConfirmPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtConfirmPassKeyReleased(evt);
            }
        });

        lblFnameError.setForeground(new java.awt.Color(255, 0, 0));
        lblFnameError.setText("error fname");

        lblLnameError.setForeground(new java.awt.Color(255, 0, 0));
        lblLnameError.setText("error lname");

        lblEmailError.setForeground(new java.awt.Color(255, 0, 0));
        lblEmailError.setText("error email");

        lblNewPassError.setForeground(new java.awt.Color(255, 0, 0));
        lblNewPassError.setText("error newPass");

        lblConfirmPassError.setForeground(new java.awt.Color(255, 0, 0));
        lblConfirmPassError.setText("error confirmPass");

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 13)); // NOI18N
        jLabel6.setText("Mobile:");

        txtMobile.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMobileKeyReleased(evt);
            }
        });

        lblMobileError.setForeground(new java.awt.Color(255, 0, 0));
        lblMobileError.setText("error mobile");

        javax.swing.GroupLayout midPanelLayout = new javax.swing.GroupLayout(midPanel);
        midPanel.setLayout(midPanelLayout);
        midPanelLayout.setHorizontalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                    .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblFnameError, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFname, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtNewPass)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(lblMobileError, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblNewPassError, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73)
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblEmailError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLnameError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(midPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblConfirmPassError, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        midPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel4, txtConfirmPass, txtEmail, txtFname, txtLname, txtMobile, txtNewPass});

        midPanelLayout.setVerticalGroup(
            midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createSequentialGroup()
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, midPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addComponent(txtFname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFnameError))
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addComponent(txtLname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLnameError)))
                .addGap(18, 18, 18)
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMobileError))
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblEmailError)))
                .addGap(18, 18, 18)
                .addGroup(midPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNewPassError))
                    .addGroup(midPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConfirmPassError)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        midPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtConfirmPass, txtEmail, txtFname, txtLname, txtMobile, txtNewPass});

        javax.swing.GroupLayout basePanelLayout = new javax.swing.GroupLayout(basePanel);
        basePanel.setLayout(basePanelLayout);
        basePanelLayout.setHorizontalGroup(
            basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(basePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, basePanelLayout.createSequentialGroup()
                        .addGroup(basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(midPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        basePanelLayout.setVerticalGroup(
            basePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, basePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(midPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(basePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignupActionPerformed
        signup();
    }//GEN-LAST:event_btnSignupActionPerformed

    private void txtFnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFnameKeyReleased
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signup();
        }
    }//GEN-LAST:event_txtFnameKeyReleased

    private void txtLnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLnameKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signup();
        }
    }//GEN-LAST:event_txtLnameKeyReleased

    private void txtMobileKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMobileKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signup();
        }
    }//GEN-LAST:event_txtMobileKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signup();
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtNewPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewPassKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signup();
        }
    }//GEN-LAST:event_txtNewPassKeyReleased

    private void txtConfirmPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmPassKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            signup();
        }
    }//GEN-LAST:event_txtConfirmPassKeyReleased

    private void btnCloseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCloseKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.dispose();
        }
    }//GEN-LAST:event_btnCloseKeyReleased

    private void btnSignupKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSignupKeyReleased
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            signup();
        }
    }//GEN-LAST:event_btnSignupKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel basePanel;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSignup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblConfirmPassError;
    private javax.swing.JLabel lblEmailError;
    private javax.swing.JLabel lblFnameError;
    private javax.swing.JLabel lblLnameError;
    private javax.swing.JLabel lblMobileError;
    private javax.swing.JLabel lblNewPassError;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel midPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFname;
    private javax.swing.JTextField txtLname;
    private javax.swing.JTextField txtMobile;
    private javax.swing.JPasswordField txtNewPass;
    // End of variables declaration//GEN-END:variables
}
