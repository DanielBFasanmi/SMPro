/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasacs;


import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


/**
 *
 * @author 1412625
 */
public class StudentAssessmentVersionBoard extends javax.swing.JDialog {
    public ClassLocationObject clo;
    List<ClassLocationObject> depClosQue;
    DefaultListModel<String> dlm = new DefaultListModel<>();
    String currentAssessment;
    String studAsPath;
    /**
     * Creates new form DependencyBoard
     */
    public StudentAssessmentVersionBoard(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);               
        depClosQue = new ArrayList<>();
    }
    public StudentAssessmentVersionBoard(java.awt.Frame parent, boolean modal, String curAs) {
        super(parent, modal);
        initComponents();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);               
        depClosQue = new ArrayList<>();
        currentAssessment = curAs;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        stAsLab = new javax.swing.JLabel();
        addstueAssessmentBtn = new javax.swing.JButton();
        finish = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        selectedFile = new javax.swing.JTextField();
        dependChecker = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        addDependency = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lister = new javax.swing.JList<>();
        removeDependency = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "StudentAssessmentVersionBoard.title")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "DependencyBoard.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(stAsLab, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "DependencyBoard.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(addstueAssessmentBtn, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "DependencyBoard.addAssessmentBtn.text")); // NOI18N
        addstueAssessmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addstueAssessmentBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(finish, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "DependencyBoard.finish.text")); // NOI18N
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(cancelBtn, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "DependencyBoard.cancelBtn.text")); // NOI18N
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        selectedFile.setEditable(false);
        selectedFile.setText(org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "DependencyBoard.jTextField1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dependChecker, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "StudentAssessmentVersionBoard.dependChecker.text")); // NOI18N
        dependChecker.setEnabled(false);
        dependChecker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dependCheckerActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "StudentAssessmentVersionBoard.jPanel1.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(addDependency, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "StudentAssessmentVersionBoard.addDependency.text")); // NOI18N
        addDependency.setEnabled(false);
        addDependency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDependencyActionPerformed(evt);
            }
        });

        lister.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listerValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lister);

        org.openide.awt.Mnemonics.setLocalizedText(removeDependency, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "StudentAssessmentVersionBoard.removeDependency.text")); // NOI18N
        removeDependency.setEnabled(false);
        removeDependency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDependencyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(StudentAssessmentVersionBoard.class, "StudentAssessmentVersionBoard.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addDependency, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeDependency, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8))
                    .addComponent(jSeparator4))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addDependency)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeDependency, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stAsLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(finish, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jSeparator5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(selectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(addstueAssessmentBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dependChecker)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(stAsLab))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addstueAssessmentBtn)
                    .addComponent(selectedFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(dependChecker)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(finish)
                    .addComponent(cancelBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addstueAssessmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addstueAssessmentBtnActionPerformed
        AssessmentFile af = new AssessmentFile();
        clo = af.getNewAssessmentFile();       
        selectedFile.setText(clo.getClassName());
        dependChecker.setEnabled(true);
        
    }//GEN-LAST:event_addstueAssessmentBtnActionPerformed

    private void dependencyCheckerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dependenctCheckerActionPerformed

    }//GEN-LAST:event_dependenctCheckerActionPerformed

    private void dependCheckerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dependCheckerActionPerformed
        if(dependChecker.isSelected()){
            addDependency.setEnabled(true);
        }else{
             addDependency.setEnabled(false);
        }
    }//GEN-LAST:event_dependCheckerActionPerformed

    private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
        createDependencies();
        dispose();
    }//GEN-LAST:event_finishActionPerformed

    private void addDependencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDependencyActionPerformed
        AssessmentFile afc = new AssessmentFile();
        ClassLocationObject c = afc.getNewAssessmentFile();   
        if(c != null){
        dlm.addElement(c.getClassName());
        System.out.println("dlm elem: "+dlm);
        lister.setModel(dlm); 
        depClosQue.add(c);
//        System.out.println("Syste: "+c.getClassName());
//        System.out.println("class location: "+c.getClassLocation());
//        System.out.println("full class location : "+c.getFullClassLocation());
//        System.out.println("class details: "+c.getFullClassDetails());
        
        }

       // System.out.println("Syste: "+depClos.size());
    }//GEN-LAST:event_addDependencyActionPerformed

    private void createDependencies(){
        depClosQue.add(clo);
        int c = 0;
        int threshold = 0;
        for(int i = depClosQue.size(); i > 0; i--){
            threshold += i;
        }
        System.out.println("****************************************class name:>>> "+threshold);
        while(!depClosQue.isEmpty()){
            if (c > threshold){
                JOptionPane.showMessageDialog(this, "Some Dependencies are not provided");
               break;
            }           
        c++;
        System.out.println("-----------------class name:>>> "+depClosQue.get(0).getClassName());
        ClassLocationObject x = depClosQue.remove(0);
        String sf = selectedFile.getText();
        CreateDependencies cd = new CreateDependencies(x, currentAssessment);
        // check if it returns true or false
        Object[] ret =  cd.createStudDependency(sf.equalsIgnoreCase(x.getClassName()));
        if(!(Boolean) ret[0]){
            depClosQue.add(x);
          }
        if((String) ret[1] != null){
            studAsPath = (String)ret[1];
            }
        }
    }

    private void removeDependencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDependencyActionPerformed
        int[] x = lister.getSelectedIndices();
        int shifter = 0;
        for(int c : x){
        depClosQue.remove(c-shifter);
        dlm.remove(c-shifter);
        shifter++;
        }
        lister.setModel(dlm);
    }//GEN-LAST:event_removeDependencyActionPerformed

    private void listerValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listerValueChanged
        if(lister.isSelectionEmpty()){
            removeDependency.setEnabled(false);
        }else{
            removeDependency.setEnabled(true);
        }
    }//GEN-LAST:event_listerValueChanged

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
    
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentAssessmentVersionBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                StudentAssessmentVersionBoard dialog = new StudentAssessmentVersionBoard(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDependency;
    private javax.swing.JButton addstueAssessmentBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JCheckBox dependChecker;
    private javax.swing.JButton finish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JList<String> lister;
    private javax.swing.JButton removeDependency;
    private javax.swing.JTextField selectedFile;
    private javax.swing.JLabel stAsLab;
    // End of variables declaration//GEN-END:variables
}