/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import org.apache.commons.math3.stat.StatUtils;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

/**
 *
 * @author sensei
 */
public class inputPrediction extends javax.swing.JFrame {

    /**
     * Creates new form inputPrediction
     */
    JFrame frame = new JFrame("Grafica");
    double B0,B1;
    double[] x,y;
    public inputPrediction() {
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

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingresa entrada");

        jButton1.setText("Predecir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Bts");

        jLabel3.setText("Predict");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(388, 388, 388)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(425, 425, 425)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel3)))
                .addContainerGap(381, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addGap(87, 87, 87)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(124, 124, 124))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        double val = Double.parseDouble(jTextField1.getText());
        jLabel3.setText(B0+" + "+B1+"("+val+") = "+(B0+(B1*val)));
        generateGrapic((float)B0,(float)B1,x,y,"Algoritmo genetico",val);
    }//GEN-LAST:event_jButton1ActionPerformed
    public void setValues(double b0, double b1, double[] xt, double[] yt){
        B0 = b0;
        B1 = b1;
        x = xt;
        y = yt;
        jLabel2.setText("B0: "+B0+"\n      B1: "+B1);    
    }
    private void generateGrapic(float b0, float b1,double[] xt,double[] yt,String title,double predict){
        
        
        Plot2DPanel plot = new Plot2DPanel();
        JTextArea resultados = new JTextArea();
        
        
        
        double[] yc = new double[xt.length+1];
        double[] x,y;
        x = new double[xt.length+1];
        y = new double[yt.length+1];
        
        for(int i=0;i<yt.length;i++){
            x[i] = xt[i];
            y[i] = yt[i];
            
        }
        int i = 0;
        for(i=0;i<yt.length;i++){
            yc[i] = b0 + (b1*x[i]);
            
        }
        x[i] = predict;
        yc[i] = b0 + (b1*predict);
        y[i] = yc[i];
        double[] px = new double[1];
        px[0] = predict;
        double[] py = new double[1];
        py[0] = yc[i];
        
        plot.addLegend(title);
        plot.addScatterPlot("Datos", x,y);
        plot.addLinePlot("Regresion",x,yc);
        
        plot.addScatterPlot("Punto", px,py);
        BaseLabel titulo = new BaseLabel("Algoritmo Genetico",Color.BLUE,0.5,1.1);
        plot.addPlotable(titulo);
        resultados.setBackground(Color.LIGHT_GRAY);
        resultados.append("\nValor minimo: "+StatUtils.min(y));
        resultados.append("\nValor maximo: "+StatUtils.max(y));
        resultados.append("\nValor promedio: "+StatUtils.mean(y));
        resultados.append("\nVarianza: "+StatUtils.variance(y));
        resultados.append("\nromedio geometrico: "+StatUtils.geometricMean(y));
        resultados.append("\nsuma: "+StatUtils.sum(y));
        resultados.append("\nProduto: "+StatUtils.product(y));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,700);
        frame.add(plot,BorderLayout.CENTER);
        frame.add(resultados,BorderLayout.SOUTH);
        frame.setVisible(true);
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
            java.util.logging.Logger.getLogger(inputPrediction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputPrediction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputPrediction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputPrediction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputPrediction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
