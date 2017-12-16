/*
 * Autor: Juan Carlos Arcila Diaz
 * Localidad: Chiclayo-Peru
 * Email:carlos_ad_6@hotmail.com
 * Para Comunidad IncanatoHack.com
 */
package ventana;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import objetos.*;
import operaciones.Disjktra;

/**
 *
 * @author Jcarlosad7
 */
public class Gui extends javax.swing.JFrame {
    Grafo grafo = new Grafo();
    Nodo nodoInicio = null;
    Nodo nodoFin = null;
    /**
     * Creamos el form
     */
    public Gui() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Camino Minimo - Dijkstra"));

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        jButton1.setText("Instrucciones");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Resultado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel1)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void dibujarGrafo(){
        jPanel1.paint(jPanel1.getGraphics());
        dibujarArista();
        dibujarNodos();
    }
    public void dibujarNodos(){
        ArrayList<Nodo> listaNodo = grafo.getListaNodos();
        for(Nodo nodo:listaNodo){            
            nodo.getCirculo().dibujarCirculo(jPanel1.getGraphics());
        }
    }
    public void dibujarArista(){
        ArrayList<Nodo> listaNodo = grafo.getListaNodos();
        for(Nodo nodo:listaNodo){            
            ArrayList<Enlace> listaEnlace = nodo.getListaNodoAdyacente();
            if(listaEnlace != null){
                for(Enlace enlace:listaEnlace){
                    enlace.getArista().getLineaQuebrada().dibujarLineaQuebrada(jPanel1.getGraphics());
                }
            }            
        }
    }
    private int ingresarPeso(){
        String peso = JOptionPane.showInputDialog("Digite la distancia entre puntos");
        int intPeso = 0;
        try{
            intPeso = Integer.parseInt(peso);            
        }catch(Exception ex){
            intPeso = ingresarPeso();
        }
        return intPeso;
    }
    
    private void eliminarNodo(int x,int y){
        if(grafo.buscarNodo(x, y)!=null){//si se hace clic sobre un nodo
            Nodo temp = grafo.buscarNodo(x, y);

            grafo.eliminarAristasSalientes(temp);
            if(grafo.eliminarNodo(temp)){
                JOptionPane.showMessageDialog(null,"Eliminado");
                dibujarGrafo();
            }
            
        }
    }
    
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        int x = evt.getX();
        int y = evt.getY();
        if(evt.isMetaDown()){            
//            eliminarNodo(x, y);
//            JOptionPane.showMessageDialog(this,"Clic derecho");
            if(grafo.buscarNodo(x, y)!=null){//si se hace clic sobre un nodo
                if(nodoInicio == null){
                    grafo.reiniciarGrafoParaDisjktra();
                    grafo.reiniciarColores();
                    nodoInicio = grafo.buscarNodo(x, y);//nodoInicio lo pongo a apuntar al nodo donde hice clic
                    nodoInicio.getCirculo().setColor(Color.red);//Lo hago cambiar de color
                    JOptionPane.showMessageDialog(null,"Seleccione nodo fin");
                }else{//si nodoInicio ya estaba apunto a un nodo, lo preparo para crear arista             
                    nodoFin = grafo.buscarNodo(x, y);
                    Disjktra disjktra = new Disjktra(grafo);
                    disjktra.ejecutar(nodoInicio);
                    disjktra.marcarRutaCorta(nodoFin, Color.red);
                    jLabel1.setText(disjktra.getRuta());
                    
    //                crearArista();            
    //                                
    //                nodoInicio.getCirculo().setColor(Color.yellow);//lo regreso a su color original
    //                
                    nodoInicio = null;//null para poder crear mas arista
                    nodoFin = null;//null para poder crear mas arista
                }
            }
              
        }else{
            if(evt.isShiftDown()){
                JOptionPane.showMessageDialog(this, "shift down");
               eliminarNodo(x, y);
               
            }else{
                if(grafo.buscarNodo(x, y)!=null){//si se hace clic sobre un nodo
                    if(nodoInicio == null){
                        nodoInicio = grafo.buscarNodo(x, y);//nodoInicio lo pongo a apuntar al nodo donde hice clic
                        nodoInicio.getCirculo().setColor(Color.red);//Lo hago cambiar de color
        //                JOptionPane.showMessageDialog(null,"Seleccione otro nodo para crear una arista");
                    }else{//si nodoInicio ya estaba apunto a un nodo, lo preparo para crear arista             
                        nodoFin = grafo.buscarNodo(x, y);                
                        crearArista();            

                        nodoInicio.getCirculo().setColor(Color.yellow);//lo regreso a su color original

                        nodoInicio = null;//null para poder crear mas arista
                        nodoFin = null;//null para poder crear mas arista
                    }
                }else{//Si no he hecho clic sobre un nodo
                    crearNodo(x, y);//creo un nodo apartir de unas coordenadas
                }
            }
        }
        dibujarGrafo();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this,"1)Para colocar 1 punto o arista, "
                + "de click en cualquier lugar de la pantalla. \n"
                + "2) Para unir puntos de click en 1 punto y luego en otro punto e ingresar el valor. \n"
                + "3) Para hallar  el camino minimo, de click derecho de 1 punto inicio a 1 punto fin.");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jPanel1.removeAll();
        jPanel1.repaint();
        grafo.reiniciarGrafoParaDisjktra();
    }//GEN-LAST:event_jButton2ActionPerformed
   
  
    private void crearArista(){
        int intPeso = ingresarPeso();
        Arista arista = new Arista();
        arista.setPeso(intPeso);
        Coordenadas c = new Coordenadas(100000,100000);
        c.addCoordenada(nodoInicio.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro()/2),nodoInicio.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro()/2));
        c.addCoordenada(nodoFin.getCirculo().getX() + (nodoInicio.getCirculo().getDiametro()/2),nodoFin.getCirculo().getY() + (nodoInicio.getCirculo().getDiametro()/2));
        LineaQuebrada lq = new LineaQuebrada(c);
        arista.setLineaQuebrada(lq);               
        grafo.crearEnlacesNoDirigido(nodoInicio, nodoFin,arista);       
       
    }
    
    private void crearNodo(int x, int y){
        Coordenadas c = new Coordenadas(100000,100000, x, y);
            String dato = JOptionPane.showInputDialog("Digite un dato o Nombre de la coordenada");
            if(dato != null){
                dato = dato.toUpperCase();//por que lo quiero todo en mayusculas
                Nodo nodo = new Nodo(dato,c);
                nodo.getCirculo().setDiametro(12);
                nodo.getCirculo().setEtiqueta(nodo.getDato()+"");
                if(grafo.adjuntarNodo(nodo)){
                    nodo.getCirculo().dibujarCirculo(jPanel1.getGraphics());
                }else{
                }
                nodoInicio = null;
                nodoFin = null; 
            }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
