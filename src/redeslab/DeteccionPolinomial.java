/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redeslab;

/**
 *
 * @author Carlo
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
public class DeteccionPolinomial extends javax.swing.JFrame {

    /**
     * Creates new form DeteccionPolinomial
     */
    Path file;
    Path file2;
    String inputPath;
    String inputPath2;
    char[] CharVec;
    String generador;
    public DeteccionPolinomial() {
        initComponents();
    }
    
    public String correcion(String generador, String word)
    {    
        int elev = word.length()- generador.length();
        String gen2=generador;
        int wordi = Integer.parseInt(word, 2);
        int geni = Integer.parseInt(gen2, 2);
        int res;
        
        for(int i=0; i<elev; i++)
        {
            gen2=gen2+"0";
        }
        //System.out.println(gen2);
        //System.out.println(word);
        
        while(word.length()>generador.length())
        {
            elev = word.length()-generador.length();
            gen2 = generador;
            
                for(int i=0; i<elev; i++)
                {
                gen2=gen2+"0";
                }
            wordi = Integer.parseInt(word, 2);
            geni = Integer.parseInt(gen2, 2);
            wordi = wordi^geni;
            word = Integer.toBinaryString(wordi);
        }
        
        
        
       
        
        String r = Integer.toBinaryString(wordi);
        
        return r;
        
        
    }
    
    public void GenCodCar()
    {
        inputPath = jTextField2.getText();
        file = Paths.get(inputPath);
        ArrayList<String> BinaryString = new ArrayList<>();
         try
        {
            InputStream in = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            line = reader.readLine();
            CharVec = line.toCharArray();
            String mensaje="",charr="";
            int binasci;
            generador=jTextField1.getText();
            boolean sw = false;
           
                 while(line!=null && sw==false)
                 {
                     if(line.length()<16){
                     
                            for (int j = 0; j < line.length(); j++) {
                                    charr=line.substring(j, j+1);
                                    for(int j2=Integer.toBinaryString(charr.charAt(0)).length();j2<8;j2++){
                                        mensaje=mensaje+"0";
                                    }
                                    mensaje=mensaje+Integer.toBinaryString(charr.charAt(0));
                                }
                                BinaryString.add(mensaje);
                                line = reader.readLine();
                                System.out.println(mensaje);
                    }else{
                         sw=true;
                         System.out.println("No es valido que haigan mas de 16 caracteres ASCII por linea");
                     }
                 }
                 if(sw==false){
                     PrintWriter writer = new PrintWriter("Codewords.crc", "UTF-8");
                     writer.write(generador);
                     for( String message : BinaryString ){

                     BigInteger divisor = new BigInteger(generador, 2);
                     double r = generador.length()-1;
                     double v = Math.pow(2,r);
                     String dividend2 = String.valueOf((int)v);
                     BigInteger xr = new BigInteger(dividend2);
                     BigInteger dateword = new BigInteger(message, 2);
                     BigInteger dividend = dateword.multiply(xr);
                   // BigInteger cociente = dividend.divide(divisor);
                     BigInteger codeword = dividend;
                     BigInteger mode = dividend.mod(divisor);
                     codeword = codeword.add(mode);
                     String code="";
                         //System.out.println(codeword.toString(2));
                         for (int i = codeword.toString(2).length(); i<message.length()+generador.length()-1; i++) {
                             code= code+"0";
                         }
                      code = code+codeword.toString(2);
                     
                     writer.println();
                     writer.write(code);
                     //System.out.println(code);
                     }
                   writer.close();
                 }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void genCode()
    {
        inputPath = jTextField2.getText();
        file = Paths.get(inputPath);
        ArrayList<String> BinaryString = new ArrayList<>();
        
         try
        {
            InputStream in = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            line = reader.readLine();
            generador=jTextField1.getText();
            
            char[] CharVec;
            String Mensaje = "";
            
           while(line!=null)
           {
               if (line.length()>16)
               {
                   System.out.println("No es valido mas de 16 caracteres");
                   break;
               }
               else
               {
                   CharVec = line.toCharArray();
                   for(char a : CharVec)
                   {
                       int BinAscii = (int)a;
                       String StringBin =Integer.toBinaryString(BinAscii);
                       if (StringBin.length()<8)
                       {
                           while(StringBin.length()<8)
                           {
                               StringBin = "0"+StringBin;
                           }
                       }
                       
                       System.out.println(StringBin);
                       
                       Mensaje = Mensaje + StringBin;
                   }
                   
                   System.out.println(Mensaje);
                   
                   for(int i=0;i<generador.length()-1;i++)
                   {
                       Mensaje = Mensaje+"0";
                   }
                   
                   System.out.println(Mensaje);
                   
                   String res = correcion(generador,Mensaje);
                   
                   int IntRes = Integer.parseInt(res, 2);
                   int IntMes = Integer.parseInt(Mensaje,2);
                   
                   int IntFinalMes = IntMes^IntRes;
                   
                   Mensaje = Integer.toBinaryString(IntFinalMes);
                   
                   if (Mensaje.length()<8*line.length()+generador.length()-1)
                   {
                       while(Mensaje.length()<8*line.length()+generador.length()-1)
                           Mensaje = "0"+Mensaje;
                   }
                   
                   System.out.println(Mensaje);
                   BinaryString.add(Mensaje);
                   
                   
               }
               
               line = reader.readLine();
                System.out.println("escrin");
           }
           reader.close();
           PrintWriter writer = new PrintWriter("Codewords.crc", "UTF-8");
           writer.println(generador);
           for( String message : BinaryString ){
            writer.println(message);
            }
           writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Volver");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Escriba el polinomio generador: ");

        jButton2.setText("Generar .crc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Verificar .crc");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("Escriba la direccion del archivo:");

        jTextField2.setText("C:\\Users\\Carlo\\Documents\\GitHub\\RedesLabUninorte\\Polinomio\\Emisor\\Codewords.txt");

        jTextField3.setText("C:\\Users\\Carlo\\Documents\\GitHub\\RedesLabUninorte\\Codewords.crc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jButton2))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        genCode();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        inputPath2 = jTextField3.getText();
        file2 = Paths.get(inputPath2);
        boolean correcto=true;
        String generadorr="";
        try{
            InputStream in2 = Files.newInputStream(file2);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in2));
            String line;
            generadorr = reader.readLine();
            line=reader.readLine();
            while(line!=null)
            {
                if (!correcion(generadorr,line).equals("0"))
                {
                    correcto = false;
                }
                line = reader.readLine();
            }
            if(correcto){
                 int j=0;
                 InputStream in3 = Files.newInputStream(file2);
                 BufferedReader reader2 = new BufferedReader(new InputStreamReader(in3));
                 generadorr=reader2.readLine();
                 line=reader2.readLine();
                 PrintWriter writer = new PrintWriter("Codewords.txt", "UTF-8");
                 BigInteger polgenerador = new BigInteger(generadorr, 2);
                 double r = generadorr.length()-1;
                 double v = Math.pow(2,r);
                 String dividend2 = String.valueOf((int)v);
                 BigInteger xr = new BigInteger(dividend2);
                 String dateword;
                 BigInteger num;
                 BigInteger residuo;
                 String temp="";
                    while(line!=null)
                    {
                         
                          line=line.substring(0,line.length()-generadorr.length()+1);
                          num = new BigInteger(line,2);
                          System.out.println(line);
                          System.out.println(line.length());
                          for (int i=0; i<line.length(); i=i+8) {
                              writer.write((char)Integer.parseInt(line.substring(i,i+8),2));
                              System.out.println((char)Integer.parseInt(line.substring(i,i+8),2));
                        }
                           writer.println();
                          line = reader2.readLine();
                    }
                  
                 writer.close();
                JOptionPane.showMessageDialog(null, "El mensaje es correcto, se ha generado el archivo de datos", "Correcto", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "El mensaje es incorrecto", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
     
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(DeteccionPolinomial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeteccionPolinomial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeteccionPolinomial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeteccionPolinomial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeteccionPolinomial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
