/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jasacs;
// updated version
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author 1412625
 */
public class JASACS extends javax.swing.JFrame {

    private JTree assessmentTree;
    DefaultMutableTreeNode root;
    private String currentAssessment;
    Map<String, JSONObject> jsonAssessmentObjects = new HashMap<>();
    JMenuItem deleteassessment, loadstudent, newassessment, openAssessment;

    /**
     * Creates new form JASACS
     */
    public JASACS() {
        initComponents();

        root = new DefaultMutableTreeNode("Assessments");
        assessmentTree = new JTree(root);
        assessmentPanel.setLayout(new GridLayout(1, 1));
        assessmentPanel.add(assessmentTree);
        JPopupMenu popup = new JPopupMenu("JASACS Pop Up");
        popup.setBorderPainted(true);

        assessmentTree.add(popup);
        newassessment = new JMenuItem("Add New Assessment");
        newassessment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewAssessment();
            }
        });
        deleteassessment = new JMenuItem("Delete Current Assessment");
        deleteassessment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "You are about to delete this " + currentAssessment + " Assessment. \n Every information would be deleted forever", "You are about to delete", JOptionPane.WARNING_MESSAGE);

            }
        });
        loadstudent = new JMenuItem("Load Student's Current Assessment Version");
        loadstudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudentAssessment();
            }
        });
        openAssessment = new JMenuItem("Open An Assessment");
        openAssessment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAnAssessment();
            }
        });

        popup.add(newassessment);
        popup.add(openAssessment);
        loadstudent.setEnabled(false);
        deleteassessment.setEnabled(false);

        popup.add(loadstudent);
        popup.add(deleteassessment);

        setEnableLoad(false);

        //  
        //   ClosableTabbedPane ct = new ClosableTabbedPane();
        //  studentAssessmentTab = ct;
//        studentAssessmentTab.addPropertyChangeListener(TabbedPaneFactory.PROP_CLOSE, new PropertyChangeListener() {
//            public void propertyChange(PropertyChangeEvent evt) {
//                JTabbedPane pane = (JTabbedPane)evt.getSource();
//                int sel = pane.getSelectedIndex();
//                pane.removeTabAt(sel);
//                
//            }
//        });
        //assessmentTree.setRootVisible(false);
        //assessmentTree.setShowsRootHandles(true); 
        assessmentTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    System.out.println("event triggered by: " + e.getComponent().getName());
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {

                    System.out.println("event triggered by: " + e.getSource());
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                if (assessmentTree.getSelectionPath() == null) {
                    return;
                }
                TreePath tp = assessmentTree.getSelectionPath();

                int s = tp.getPathCount();
                //  System.out.println("Path Count: "+s);
                //  System.out.println("Path Component: "+tp.getPathComponent(s-1));
                switch (s) {
                    case 1:
                        setEnableLoad(false);
                        break;
                    case 3:
                        //  System.out.println("Parent");
                        DefaultMutableTreeNode d = (DefaultMutableTreeNode) tp.getPathComponent(s - 1);
                        currentAssessment = d.getParent().toString();
                        getStudentAssessment(tp.getPathComponent(s - 1));

                        pathInfo.setText(currentAssessment);
                        System.out.println("Current Assessment: " + currentAssessment);
                        setEnableLoad(true);
                        break;
                    default:
                        String ca = tp.getPathComponent(s - 1).toString();
                        currentAssessment = ca;
                        pathInfo.setText(ca);
                        setEnableLoad(true);
                        break;
                }

            }

        });

        assessmentTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath tp = e.getPath();
                int s = tp.getPathCount();
                //  System.out.println("Path Count: "+s);
                //  System.out.println("Path Component: "+tp.getPathComponent(s-1));
                switch (s) {
                    case 1:
                        setEnableLoad(false);
                        break;
                    case 3:
                        //  System.out.println("Parent");
                        DefaultMutableTreeNode d = (DefaultMutableTreeNode) tp.getPathComponent(s - 1);
                        currentAssessment = d.getParent().toString();
                        pathInfo.setText(currentAssessment);
                        System.out.println("the currenter: "+tp.getPathComponent(s - 1));
                        if(tp.getPathComponent(s - 1).equals("TestModel")){
                            
                        }else{
                            getStudentAssessment(tp.getPathComponent(s - 1));
                        }
                        

                        //   System.out.println("Current Assessment: "+currentAssessment);
                        setEnableLoad(true);
                        break;
                    default:
                        String ca = tp.getPathComponent(s - 1).toString();
                        currentAssessment = ca;
                        pathInfo.setText(ca);
                        setEnableLoad(true);
                        break;
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        statusBar = new javax.swing.JLabel();
        assessToolBar = new javax.swing.JToolBar();
        newAssessmentBtn = new javax.swing.JButton();
        loadAssessmentBtn = new javax.swing.JButton();
        studentAssessmentTab = new ClosableTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        assessmentPanel = new javax.swing.JPanel();
        pathInfo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newAssessmentMenu = new javax.swing.JMenuItem();
        loadAnAssessment = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        loadAssessmentMenu = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JASACS: Java Automated Structural Assessment Coding System");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("  Assessment");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        statusBar.setText("  some features are under construction");
        statusBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        assessToolBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        assessToolBar.setRollover(true);

        newAssessmentBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jasacs/new-hi.png"))); // NOI18N
        newAssessmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAssessmentBtnActionPerformed(evt);
            }
        });
        assessToolBar.add(newAssessmentBtn);

        loadAssessmentBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jasacs/load-hi.png"))); // NOI18N
        loadAssessmentBtn.setFocusable(false);
        loadAssessmentBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadAssessmentBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        loadAssessmentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadAssessmentBtnActionPerformed(evt);
            }
        });
        assessToolBar.add(loadAssessmentBtn);

        studentAssessmentTab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        assessmentPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout assessmentPanelLayout = new javax.swing.GroupLayout(assessmentPanel);
        assessmentPanel.setLayout(assessmentPanelLayout);
        assessmentPanelLayout.setHorizontalGroup(
            assessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        assessmentPanelLayout.setVerticalGroup(
            assessmentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(assessmentPanel);

        pathInfo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pathInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pathInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jMenu1.setText("File");
        jMenu1.setHideActionText(true);

        newAssessmentMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newAssessmentMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jasacs/ass.png"))); // NOI18N
        newAssessmentMenu.setText("New Assessment");
        newAssessmentMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAssessmentMenuActionPerformed(evt);
            }
        });
        jMenu1.add(newAssessmentMenu);

        loadAnAssessment.setText("Load An Assessment");
        loadAnAssessment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadAnAssessmentActionPerformed(evt);
            }
        });
        jMenu1.add(loadAnAssessment);
        jMenu1.add(jSeparator2);

        loadAssessmentMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        loadAssessmentMenu.setMnemonic('L');
        loadAssessmentMenu.setText("Load Student Assessmernt");
        loadAssessmentMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadAssessmentMenuActionPerformed(evt);
            }
        });
        jMenu1.add(loadAssessmentMenu);
        jMenu1.add(jSeparator3);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(assessToolBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pathInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
                            .addComponent(studentAssessmentTab, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(assessToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pathInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(studentAssessmentTab, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(statusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newAssessmentMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAssessmentMenuActionPerformed
        createNewAssessment();
    }//GEN-LAST:event_newAssessmentMenuActionPerformed

    private void newAssessmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAssessmentBtnActionPerformed
        createNewAssessment();
    }//GEN-LAST:event_newAssessmentBtnActionPerformed

    private void loadAssessmentMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadAssessmentMenuActionPerformed
        loadStudentAssessment();
    }//GEN-LAST:event_loadAssessmentMenuActionPerformed

    private void loadAssessmentBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadAssessmentBtnActionPerformed
        loadAnAssessment();
    }//GEN-LAST:event_loadAssessmentBtnActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void loadAnAssessmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadAnAssessmentActionPerformed
        loadAnAssessment();
        
    }//GEN-LAST:event_loadAnAssessmentActionPerformed
    
    private void loadAnAssessment(){
       AllAssessmentDialog aad = new AllAssessmentDialog(this, rootPaneCheckingEnabled);
       aad.setVisible(true);
       String assToLoad = aad.getSelectedAssessment();
        System.out.println("Ass to load: "+assToLoad);
        if(assToLoad != null){
            loadExistingAssessment(assToLoad);
        }
    }
    public void createNewAssessment() {
        AssessObject ao = null;
        AssessmentBoard af = new AssessmentBoard(this, rootPaneCheckingEnabled);
        af.setVisible(true);
        ClassLocationObject clo = af.clo;
        if (clo != null) {
            try {
                String prepare = System.getProperty("user.dir") + "\\JASACS\\" + clo.getClassName();
                String preparePath = prepare + "\\TestModel";
//                if(new File(preparePath).exists()){
//                JOptionPane.showMessageDialog(this, "This assessment exist!");
//                    return;
//                
//                }
                String check_for_space = clo.getFullClassLocation();
                if (check_for_space.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Please make sure no folder has space character i.e \" \".\n You can delete the space or use underscore (_ or anything) inplace of the space.\n" + check_for_space, "Space in one of the directory's name", JOptionPane.ERROR_MESSAGE);

                } else {
                    GetFullClassPath gfp = new GetFullClassPath(new File(clo.getFullClassLocation()));
                    File workdir = new File(preparePath, gfp.getPackagePath());
                    if (!workdir.exists()) {
                        workdir.mkdirs();
                        System.out.println("directory created");
                    }
                    //else {
//                    JOptionPane.showMessageDialog(this, "This assessment exist!");
//                    return;
//                }
                    //     System.out.println("class name:>>> "+clo.getClassName());
                    File f = new File(workdir, clo.getClassName() + ".class");
                    if (!f.exists()) {

                        System.out.println("directory created");
                    } else {
                        JOptionPane.showMessageDialog(this, "This assessment exist!");
                        return;
                    }
                    try (FileOutputStream fos = new FileOutputStream(prepare + "\\buildpath.jasacs")) {
                        String drep = System.getProperty("user.dir") + "\\JASACS\\testtemp";
                        copyFolder(new File(drep),new File(preparePath));
                       // Files.copy(source, target, REPLACE_EXISTING);
                        ao = new AssessObject(f.getAbsolutePath());

                        ObjectOutputStream out = new ObjectOutputStream(fos);
                        out.writeObject(ao);
                        out.close();
                         deletetempFolder(new File(drep));
                    }
                   
                    addAssessmentToTree(clo);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JASACS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JASACS.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(this, "No File chossen");
        }

    }

    private void loadStudentAssessment() {
        StudentsAssessmentVersionBoard af = new StudentsAssessmentVersionBoard(this, rootPaneCheckingEnabled, currentAssessment);
        af.setVisible(true);
        ClassLocationObject clo = af.clo;
        String sPath = af.studAsPath;
        String matric = af.studMatric;
        System.out.println("Student stuff: " + sPath);

        if (clo != null) {
            FileOutputStream fos = null;
            // write all dependencies 
           // CheckForStudentID cfsID = new CheckForStudentID();
           // String stud_ID = cfsID.studentID(sPath, currentAssessment);

            try {

                String preparePath = System.getProperty("user.dir") + "\\JASACS\\" + currentAssessment + "\\" + matric;
                sPath = sPath.replace(System.getProperty("user.dir") + "\\JASACS\\temp\\" + currentAssessment + "\\", "");
                sPath = preparePath + "\\" + sPath;
                AssessObject ao = new AssessObject(sPath);


                GetFullClassPath gfp = new GetFullClassPath(new File(clo.getFullClassLocation()));
                File workdir = new File(preparePath, gfp.getPackagePath());

                //     System.out.println("class name:>>> "+clo.getClassName());
                File f = new File(workdir, clo.getClassName() + ".class");
                if (!f.exists()) {
                } else {
                    JOptionPane.showMessageDialog(this, "Duplicate Student", "Student assessment exists", ERROR_MESSAGE);

                    // we can still use a dialog confirm to akk if it sjhpoild be overwritten or not
                    return;
                }

                String source = System.getProperty("user.dir") + "\\JASACS\\temp\\" + currentAssessment;
               

                copyFolder(new File(source), new File(preparePath));
                                FileOutputStream foa = new FileOutputStream(preparePath + "\\loadpath.jasacs");
                ObjectOutputStream oos = new ObjectOutputStream(foa);
                oos.writeObject(ao);

                deletetempFolder(new File(System.getProperty("user.dir") + "\\JASACS\\temp"));
                addStudentToNode(matric);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JASACS.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(JASACS.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No File chossen");
        }

    }

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JASACS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JASACS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JASACS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JASACS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JASACS().setVisible(true);
            }
        });
    }

    private boolean writeDependncy() {

        return true;
    }

    public void setEnableLoad(boolean flag) {

        loadAssessmentMenu.setEnabled(flag);
        deleteassessment.setEnabled(flag);
        loadstudent.setEnabled(flag);

    }

    private void addAssessmentToTree(ClassLocationObject clo) {
        String[] arr = clo.getClassLocation().split("\\.");
        String assessmentName = arr[arr.length - 2];
        currentAssessment = assessmentName;
        DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(assessmentName); //-------------------------------------------- i am here
        DefaultMutableTreeNode tm = new DefaultMutableTreeNode("Test Model");

        dmt.add(tm);

        addAssessmentJsonObject();
        //---------------------------------------------------------------------------------------------------->here
        DefaultTreeModel model = (DefaultTreeModel) assessmentTree.getModel();
        DefaultMutableTreeNode rooted = (DefaultMutableTreeNode) assessmentTree.getModel().getRoot();
        model.insertNodeInto(dmt, rooted, rooted.getChildCount());
        pathInfo.setText(currentAssessment);
        setEnableLoad(true);
    }

    private void addAssessmentJsonObject() {
        LoadTestModelStructure ltms = new LoadTestModelStructure(currentAssessment);

        JSONObject jo = ltms.startOperation();
        jsonAssessmentObjects.put(currentAssessment, jo);
        System.out.println("json obj:");
        System.out.println(jsonAssessmentObjects);
    }

    private void getStudentAssessment(Object o) {
        int cCount = -1;
        //   System.out.println("obj name: "+o.toString());
        DefaultMutableTreeNode t = (DefaultMutableTreeNode) o;
        //     System.out.println("parent name: "+t.getParent().toString());
        //   studentAssessmentTab.add(" "+o.toString()+" ",new JEditorPane());
        String tooltext = o.toString() + "'s Version for Assessment :" + t.getParent().toString();
        JEditorPane jed = null;
        Component[] comp = studentAssessmentTab.getComponents();
        System.out.println("comp count: " + comp.length);
        for (Component c : comp) {
            cCount = cCount + 1;
            JScrollPane jcp = (JScrollPane) c;
            JViewport jvp = jcp.getViewport();
            if (((JEditorPane) jvp.getView()).getName().equals(tooltext)) {

                jed = (JEditorPane) jvp.getView();
                break;
            }
        }

        if (jed == null) {
            jed = new JEditorPane() {
                String cName = tooltext;
                String level = o.toString();

                @Override
                public String getName() {
                    return cName;
                }

                @Override
                public String getSelectedText() {
                    return level;
                }
            };
            JScrollPane jsc = new JScrollPane(jed);
            studentAssessmentTab.addTab("  " + o.toString() + "  ", null, jsc, tooltext);
            jed.setToolTipText(tooltext);
            jed.setContentType("text/html");
            //add the file difference here---------------------------------------------------------------------------------> difference
            String text = "<html><h2><u><font color='blue'><center>" + tooltext + "</center></font></u></h2>";
            JSONObject jo = null;
            if (jed.getSelectedText().equals("Test Model")) {

                jo = jsonAssessmentObjects.get(currentAssessment);
                System.out.println("current assessment: " + currentAssessment);
                JSONArray ja = (JSONArray) jo.get("class");
                //  class array only has one element
                JSONObject jag = (JSONObject) ja.get(0);

                text += "<p><br/><u>Class Description</u><br/>Class Name: " + jag.get("class name");
                text += "<br/>Class Type: " + jag.get("type name");
                text += "<br/>Package Name: " + jag.get("package name");
                text += "<br/>Modifier: " + jag.get("modifier");
                text += "<br/>Superclass: " + jag.get("superclass");
                ///----------------------------------------------------------
                if (jag.containsKey("Abstract")) {
                    text += "<br/>Abstract: Class is Abstract";
                }
                if (jag.containsKey("Final")) {
                    text += "<br/>Final: Class is Final";
                }
                text += "</p>";

                text += "<p><u>CONSTRUCTORS</u><br/>";
                JSONArray jc = (JSONArray) jo.get("constructors");
                for (Object x : jc) {
                    JSONObject ob = (JSONObject) x;

                    text += ob.get("modifier") + " constructor with ";
                    JSONArray mp = (JSONArray) ob.get("parameters");
                    if (mp.isEmpty()) {
                        text += " no parameters;<br/>";
                    } else {
                        JSONArray[] jan = new JSONArray[mp.size()];
                        for (int ox = 0; ox < jan.length; ox++) {
                            text += mp.get(ox) + ", ";
                        }

                        text += " as parameters<br/>";
                    }

                }
                text += "<br/>";
                text += "</p>";

                jc = null;
                text += "<p><u>FIELDS</u><br/>";
                jc = (JSONArray) jo.get("fields");
                for (Object x : jc) {
                    JSONObject ob = (JSONObject) x;

                    text += ob.get("modifier") + " field with name: " + ob.get("name") + " and type: " + ob.get("generic type") + "<br/>";
                }
                text += "<br/>";
                text += "</p>";

                jc = null;
                text += "<p><u>METHODS</u><br/>";
                jc = (JSONArray) jo.get("methods");
                for (Object x : jc) {
                    JSONObject ob = (JSONObject) x;

                    text += ob.get("modifier") + " field with name: " + ob.get("name") + " and return type: " + ob.get("return type") + " and";
                    JSONArray mp = (JSONArray) ob.get("parameters");
                    if (mp.isEmpty()) {
                        text += " no parameters;<br/>";
                    } else {
                        JSONArray[] jan = new JSONArray[mp.size()];
                        for (int ox = 0; ox < jan.length; ox++) {
                            text += mp.get(ox) + ", ";
                        }

                        text += " as parameters<br/>";
                    }

                }
                text += "<br/>";
                text += "</p>";

            } else {
                LoadStudentModelVersion ltms = new LoadStudentModelVersion(currentAssessment, jed.getSelectedText());
                jo = ltms.startOperation();
                //---------------------- 
                TestJsonDiff tj = new TestJsonDiff(jsonAssessmentObjects.get(currentAssessment), jo);

                text += "<p> <u>Class Description</u><br/>" + tj.testClassDescriptions() + "</p>";
                text += "<p> <u>Constructors</u><br/>" + tj.testConstructors() + "</p>";
                text += "<p> <u>Fields</u><br/>" + tj.testFields() + "</p>";
                text += "<p> <u>Methods</u><br/>" + tj.testMethods() + "</p>";

            }
            text += "</html>";
            jed.setText(text);
            jed.setEditable(false);
            studentAssessmentTab.setSelectedIndex(studentAssessmentTab.getComponentCount() - 1);
        } else {
            System.out.println("This is displayed and has name : " + jed.getName());
            System.out.println("This is level and has name : " + jed.getSelectedText());
            studentAssessmentTab.requestFocusInWindow();
            studentAssessmentTab.getComponent(cCount).requestFocusInWindow();
            studentAssessmentTab.setSelectedIndex(cCount);

        }

        // studentAssessmentTab.getToolTipText();
        //      JEditorPane jt = (JEditorPane) studentAssessmentTab.getComponentAt(0);  
        //       System.out.println("tool kit :=> "+jt.getName());
    }
    //   String preparePath =  System.getProperty("user.dir")+ "\\JASACS\\temp";

    private void addStudentToNode(String sID) {
        Enumeration e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();

            if (node.getLevel() != 1) {
                continue;
            }

            if (node.toString().equals(currentAssessment)) {
                //  System.out.println("The target assessment: "+node.toString());
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(sID);
                DefaultTreeModel model = (DefaultTreeModel) assessmentTree.getModel();
                DefaultMutableTreeNode rooted = (DefaultMutableTreeNode) assessmentTree.getModel().getRoot();
                int xs = rooted.getIndex(node);
                DefaultMutableTreeNode assess = (DefaultMutableTreeNode) rooted.getChildAt(xs);
                model.insertNodeInto(child, assess, assess.getChildCount() - 1);
                break;
            }

        }

    }

    private void copyFolder(File sourceFolder, File destinationFolder) throws IOException {

        if (sourceFolder.isDirectory()) {
            if (!destinationFolder.exists()) {
                destinationFolder.mkdir();
                System.out.println("Directory created :: " + destinationFolder);
            }
            String files[] = sourceFolder.list();

            for (String file : files) {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                copyFolder(srcFile, destFile);
            }
        } else {
            Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied :: " + destinationFolder);
        }
    }

    private void deletetempFolder(File f) {
        if (f.isDirectory()) {
            for (File fx : f.listFiles()) {
                deletetempFolder(fx);
            }
        }

        f.delete();
        // Path p = Paths.get(dir);            
    }
    
    
    private void loadExistingAssessment(String ea){
        currentAssessment = ea;
        DefaultMutableTreeNode dmt = new DefaultMutableTreeNode(ea);
        String directory = System.getProperty("user.dir")+ "\\JASACS\\"+ea;
        File dir = new File(directory);
        File[] aDItem = dir.listFiles();
        for(File item : aDItem){
            if(item.isDirectory()){
                DefaultMutableTreeNode child = null;
                if(item.getName().equals("TestModel")){
                    child = new DefaultMutableTreeNode("Test Model");
                    addAssessmentJsonObject();
                }else{
               child = new DefaultMutableTreeNode(item.getName());
                }
                dmt.add(child);
            }
        }
        DefaultTreeModel model = (DefaultTreeModel) assessmentTree.getModel();
        DefaultMutableTreeNode rooted = (DefaultMutableTreeNode) assessmentTree.getModel().getRoot();
        model.insertNodeInto(dmt, rooted, rooted.getChildCount());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar assessToolBar;
    private javax.swing.JPanel assessmentPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuItem loadAnAssessment;
    private javax.swing.JButton loadAssessmentBtn;
    private javax.swing.JMenuItem loadAssessmentMenu;
    private javax.swing.JButton newAssessmentBtn;
    private javax.swing.JMenuItem newAssessmentMenu;
    private javax.swing.JLabel pathInfo;
    private javax.swing.JLabel statusBar;
    private javax.swing.JTabbedPane studentAssessmentTab;
    // End of variables declaration//GEN-END:variables
}

//
// MouseListener ml = new MouseAdapter() {
//     public void mousePressed(MouseEvent e) {
//         int selRow = tree.getRowForLocation(e.getX(), e.getY());
//         TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
//         if(selRow != -1) {
//             if(e.getClickCount() == 1) {
//                 mySingleClick(selRow, selPath);
//             }
//             else if(e.getClickCount() == 2) {
//                 myDoubleClick(selRow, selPath);
//             }
//         }
//     }
// };
// tree.addMouseListener(ml);
