/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package redeslab;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Sebastian
 */
public class CorreccionHamming extends javax.swing.JFrame {

    /**
     * Creates new form CorreccionHamming
     */
    Path file;
    String inputPath;
    int DecimalConversion;
    String inputString;
    char[] CharVec;
    Vector <Integer>intVec;
    
    int ParityLength;
    
    
    public CorreccionHamming() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Archivo Entrada");

        jButton1.setText("Generar .ham");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(240, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        inputPath = jTextField1.getText();
        file = Paths.get(inputPath);
        intVec = new Vector();
        
        System.out.println("Entre");
        
        try
        {
            InputStream in = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            inputString = reader.readLine();
            CharVec = inputString.toCharArray();
            
            for(int i = 0; i<CharVec.length; i++)
            {
                intVec.add((int)CharVec[i]);
            }
            
            //System.out.println(DecimalConversion + " - "+ BinaryString);
            //JOptionPane.showMessageDialog(this,DecimalConversion + " - "+ BinaryString );
            GenerateCode();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton1MouseClicked

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
            java.util.logging.Logger.getLogger(CorreccionHamming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CorreccionHamming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CorreccionHamming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CorreccionHamming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CorreccionHamming().setVisible(true);
            }
        });
    }
    
    public void GenerateCode ()throws Exception
    {
        ArrayList<String> BinaryString = new ArrayList<>();
        
        for(int i=0; i<intVec.size(); i++)
        BinaryString.add(Integer.toBinaryString(intVec.elementAt(i)));
        PrintWriter writer = new PrintWriter("PalabrasCodigo.ham", "UTF-8");
        
        for(int i = 0; i<BinaryString.size(); i++)
        {
            char[] chars=BinaryString.get(i).toCharArray();
            
            char[] charsec = {'0','0','0','0','0','0','0','0','0'};
            
            for(int j = 0; j<chars.length;j++)
            {
                charsec[j] = chars[j];
                //System.out.print(charsec[j]+"- "+j+"   ");
                
            }
            //System.out.println();
            
            
            int c1 = ((int)charsec[0]-48)^((int)charsec[1]-48)^((int)charsec[3]-48)^((int)charsec[4]-48)^((int)charsec[6]-48);
            //System.out.println(c1+"= "+((int)charsec[0]-48)+" "+((int)charsec[1]-48)+" "+((int)charsec[3]-48)+" "+((int)charsec[4]-48)+" "+((int)charsec[6]-48));
            int c2 = ((int)charsec[0]-48)^((int)charsec[2]-48)^((int)charsec[3]-48)^((int)charsec[5]-48)^((int)charsec[6]-48);
            
            int c3 = ((int)charsec[1]-48)^((int)charsec[2]-48)^((int)charsec[3]-48)^((int)charsec[7]-48);
            
            int c4 = ((int)charsec[4]-48)^((int)charsec[5]-48)^((int)charsec[6]-48)^((int)charsec[7]-48);
            
            System.out.println(c1 + " " + c2 + " " + c3 + " " + c4);
            
            writer.println(charsec[7]+""+charsec[6]+""+charsec[5]+""+c4+""+charsec[4]+""+charsec[3]+""+charsec[2]+""+charsec[1]+""+c3+""+charsec[0]+""+c2+""+c1);
            //writer.print(charsec[7]);
            //writer.print(charsec[6]);
            
            System.out.println("Escribi");
            
        }
        
        writer.close();
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
