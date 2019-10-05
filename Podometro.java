/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author Christian Jimenez Cuesta 
 * 
 */
public class Podometro {
    //constantes
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    //atributos
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche;
    
    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = MUJER;
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado  = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if(sexo == 'M'){
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }else if(sexo == 'H'){
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
    }

     /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
                            int horaFin) {
            //Seg�n los d�as de la semana, nos guarda los pasos.
            switch (dia){
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: totalPasosLaborables += pasos;
                        break;
                case 6: totalPasosSabado += pasos;
                        totalDistanciaFinSemana += (pasos * (longitudZancada / 100000));
                        break;
                case 7: totalPasosDomingo += pasos;
                        totalDistanciaFinSemana += (pasos * (longitudZancada / 100000));
                        break;
            }
            totalDistanciaSemana += (pasos * (longitudZancada / 100000));
            //Calcula y almacena los minutos caminados.
            int horasCaminadas = 0;
            if(horaFin / 100 > horaInicio / 100){
                horasCaminadas = horaFin / 100 - horaInicio / 100;
            }else if(horaFin / 100 < horaInicio / 100){
                horasCaminadas = horaInicio / 100 - horaFin / 100;
            }  
            int minutosCaminados = 0;
            if(horaFin % 100 > horaInicio % 100){
                minutosCaminados = horaFin % 100 - horaInicio % 100;
            }else if(horaFin % 100 < horaInicio %  100){
                minutosCaminados = horaInicio % 100 - horaFin % 100;
            }   
            tiempo = minutosCaminados + (horasCaminadas * 60);
            //Incrementa las caminatas nocturnas, solo a partir de las 21h.
            if(horaInicio > 2100){
                caminatasNoche++;
            }
    }
    
     /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        String nombreSexo = "";
        if(sexo == 'M'){
            nombreSexo = "MUJER";
        }else if(sexo == 'H'){
            nombreSexo = "HOMBRE";
        }
        System.out.println("Configuraci�n del pod�metro"
                            + "\n*********************************"
                            + "\nAltura: " + (altura / 100) + " mtos"
                            + "\nSexo: " + nombreSexo
                            + "\nLongitud zancada: " + (longitudZancada / 100) + " mtos");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        String diaMasCaminado = "";
        if(totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo){
            diaMasCaminado = "LABORABLES";
        }else if(totalPasosSabado > totalPasosDomingo){
            diaMasCaminado = "S�BADO";
        }else{
            diaMasCaminado = "DOMINGO";
        }
        System.out.println("Estad�sticas"
                            + "\n*********************************"
                            + "\nDistancia recorrida toda la semana: " + totalDistanciaSemana 
                                + " Km"
                            + "\nDistancia fin de semana: " + totalDistanciaFinSemana + " Km"
                            + "\n"
                            + "\nN� pasos d�as laborables: " + totalPasosLaborables
                            + "\nN� pasos S�BADO: " + totalPasosSabado
                            + "\nN� pasos DOMINGO: " + totalPasosDomingo
                            + "\n"
                            + "\nN� de caminatas realizadas a partir de las 21h: " + caminatasNoche
                            + "\n"
                            + "\nTiempo total caminado en la semana: " + (tiempo / 60) + "h. y "
                                + (tiempo % 60) + "m."
                            + "\nD�a/s com m�s pasos caminados: " + diaMasCaminado);
    }

   

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diaMasCaminado = "";
        if(totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo){
            diaMasCaminado = "LABORABLES";
        }else if(totalPasosSabado > totalPasosDomingo){
            diaMasCaminado = "S�BADO";
        }else{
            diaMasCaminado = "DOMINGO";
        }
        return diaMasCaminado;
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        sexo = MUJER;
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado  = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

}
