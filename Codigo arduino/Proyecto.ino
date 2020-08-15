// Importacion de librerias
#include <WISOL.h>
#include <Wire.h>
#include <math.h>
// Conexion con sigfox
Isigfox *Isigfox = new WISOL();
typedef union {
  uint16_t number;
  uint8_t bytes[2];
} UINT16_t;

// Declaracion de variables
int pin0,voltajeAlfombra;
float bateria, porcentajeBateria, voltajeMedido;
unsigned long tiempoActual;
long tiempoAnterior, intervalo;
String mesa;
/*
  Fuencion de inicio del programa que se
  ejecuta una sola vez
*/
void setup() {
  pin0 = 0;
  tiempoAnterior = 0;
  intervalo = 600000;
  mesa = "";
  //Inicio de la la comunicacion I2C
  Wire.begin();
  Wire.setClock(100000);
  // Velocidad del puerto serial
  Serial.begin(9600);
  Isigfox->initSigfox();
  Isigfox->testComms();
}
/*
  Funcion que se ejecutara continuamente durante el arduino
  se encuentre activo.
*/
void loop() {
  // Lectura de sensores
  ValoresSensados();
  if (voltajeMedido > 1.30) {
    if (mesa != "DE") {
      Serial.println("desocupada");
      mesa = ObtenerEstadoDeMesa(voltajeMedido);
      EnviarSigfox(mesa);
      delay(10000);
    }
  }
  else {
    if (mesa != "OC") {
      Serial.println("Ocupada");      
      mesa = ObtenerEstadoDeMesa(voltajeMedido);
      EnviarSigfox(mesa);
      delay(10000);
    }
  }
 
}

/*  
  La funcion EnviarSigfox envia los datos obtenidos a partir del estadod e la mesa
*/
void EnviarSigfox(String mesa) {
  byte *stringt_byte2 = (byte *)&mesa;
  const uint8_t payloadSize = 3;
  uint8_t buf_stri[payloadSize];
  buf_stri[0] = mesa.charAt(0);
  buf_stri[1] = mesa.charAt(1);
  uint8_t *sendData = buf_stri;
  int len = 3;
  recvMsg *RecvMsg;
  RecvMsg = (recvMsg *)malloc(sizeof(recvMsg));
  Isigfox->sendPayload(sendData, len, 0, RecvMsg);
  for (int i = 0; i < RecvMsg->len; i++) {
    Serial.print(RecvMsg->inData[i]);
  }
  Serial.println("");
  free(RecvMsg);
}

/* 
  La funcion ObtenerEstadoDeMesa obtiene el voltaje medido y lo compara para conocer el estado de la mesa
*/
String ObtenerEstadoDeMesa(float voltajeMedido) {
  if (voltajeMedido > 1.30) {
    return "DE";
  } else {
    return "OC";
  }
}

/*  
  Se obtienen los datos del voltaje del velostat
*/
void ValoresSensados() {
  voltajeAlfombra = analogRead(pin0);
  voltajeMedido = (((float)voltajeAlfombra) * 5.0) / 1023.0;
  Serial.println(voltajeMedido);
}
