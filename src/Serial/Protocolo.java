/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serial;

/**
 *
 * @author lipec
 */
public class Protocolo {

   // private String tipoDado;
    private String temperatura;


    private String leituraComando;

    private void interpretaComando() {
        //String aux[] = leituraComando.split(",");
        //if (aux.length == 3) {
            String temperatura = leituraComando;
            
        //}
    }

  
    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    /*public String getUmidade() {
        return umidade;
    }

    public void setUmidade(String umidade) {
        this.umidade = umidade;
    }*/

    public String getLeituraComando() {
        return leituraComando;
    }

    public void setLeituraComando(String leituraComando) {
        this.leituraComando = leituraComando;
        this.interpretaComando();
    }

}
