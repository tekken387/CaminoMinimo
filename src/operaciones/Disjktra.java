/*
 * Autor: Juan Carlos Arcila Diaz
 * Localidad: Chiclayo-Peru
 * Email:carlos_ad_6@hotmail.com
 * Para Comunidad IncanatoHack.com
 */

package operaciones;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import objetos.Arista;
import objetos.Enlace;
import objetos.Grafo;
import objetos.Nodo;


public class Disjktra {
    Grafo grafo;
    ListaNodo listaNodosAdyacentes;
    ArrayList<Arista> aux = new ArrayList<Arista>();
    String resultado;
    int suma;
    
    public Disjktra(Grafo grafo){
        this.grafo = grafo;
        listaNodosAdyacentes = new ListaNodo();
        resultado="La ruta es: ";
        suma=0;
    }
    private void llenarConAdyacentes(Nodo nodo){
        if(nodo != null){
            ArrayList<Enlace> listaAux = nodo.getListaNodoAdyacente();
            if(listaAux != null){
                    for(Enlace enlace:listaAux){
                    Nodo aux2 = enlace.getNodo();
                    if(!aux2.isMarca()){

                        if(listaNodosAdyacentes.isContenido(aux2)){
                            int longitud = nodo.getLongitudCamino() + enlace.getArista().getPeso();
                            if(aux2.getLongitudCamino() > longitud){
                                aux2.setLongitudCamino(longitud);
                                aux2.setNodoAntecesorDisjktra(nodo);
                            }
                        }else{
                            aux2.setLongitudCamino(nodo.getLongitudCamino() + enlace.getArista().getPeso());
                            aux2.setNodoAntecesorDisjktra(nodo);
                            listaNodosAdyacentes.add(aux2);
                        }

                    }
                }
            }
        }
    }
    public void ejecutar(Nodo nodoInicio){
        nodoInicio.setLongitudCamino(0);
        if(nodoInicio != null){
            listaNodosAdyacentes = new ListaNodo();
            listaNodosAdyacentes.add(nodoInicio);
            while(!listaNodosAdyacentes.isEmpty()){
                Nodo menor = listaNodosAdyacentes.buscarMenor();
                menor.setMarca(true);
                listaNodosAdyacentes.remove(menor);
                llenarConAdyacentes(menor);
            }
        }
    }
    
    private String rutaCorta(Nodo nodoFinal){
        aux.clear();
        Nodo nAux = nodoFinal;
        int i=0;
        while(nAux.getNodoAntecesorDisjktra() != null){
    //        aux.add(grafo.getArista(nAux.getCapital().getNombreCiudad(),
    //                nAux.getNodoAntecesorDisjktra().getCapital().getNombreCiudad()));
            aux.add(grafo.getArista(nAux,
                    nAux.getNodoAntecesorDisjktra()));
            resultado=resultado+nAux.getDato()+"-";
            suma=suma+aux.get(i).getPeso();
            nAux = nAux.getNodoAntecesorDisjktra();
            i++;
        }
        resultado=resultado+nAux.getDato()+". ";
        resultado=resultado+ "Total: "+suma;
        return resultado;
    }
    
    public String marcarRutaCorta(Nodo nodoFinal,Color color){
        String result="";
        if(nodoFinal != null){
             result=rutaCorta(nodoFinal);
                for(int i = 0;i < aux.size();i++){
                    if(!aux.isEmpty()){
                        aux.get(i).getLineaQuebrada().setColor(color);
                        aux.get(i).getLineaQuebrada().setGrosorLinea(4);
                    }
                }
        }
        
        return result;
    }

    public String getRuta(){
        return resultado;
    }
    
}
