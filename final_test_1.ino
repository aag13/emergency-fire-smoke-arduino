
#include <SPI.h>
#include <Ethernet2.h>
#include <Servo.h>

//////////////////////////////
const int gasPin = 2; //GAS sensor output pin to Arduino analog A0 pin
float gas_pin_smoke_thresshold = 300;
float gas_pin_thresshold = 200;
float temp_pin_thresshold = 80;
float gas_sensor = A0;
float gas_value;
float smoke_sensor = A1;
float smoke_value;
float temp;
int tempPin = A5;
int LED = 13;


Servo motor;
int pos = 0;
boolean motorFlag = true;

//////////////////////////
// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = {
  0x90, 0xA2, 0xDA, 0x0F, 0xF9, 0x4D
};

// the router's gateway address:
byte gateway[] = { 172, 16, 33, 1 };
// the subnet:
byte subnet[] = { 255, 255, 255, 0 };

IPAddress ip(172, 16, 33, 82);

// Initialize the Ethernet server library
// with the IP address and port you want to use
// (port 80 is default for HTTP):

EthernetServer server(3000);

void setup() {

  motor.attach(9);
  motor.write(180);

  pinMode(LED, OUTPUT);
  // Open serial communications and wait for port to open:
  Serial.begin(9600);

  // start the Ethernet connection and the server:
  Ethernet.begin(mac, ip, gateway, subnet);
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());
}


void loop() {
  // listen for incoming clients
  EthernetClient client = server.available();
  if (client) {
    Serial.println("new client");


    while (client.connected()) {


            if (client.available()) {
              char c = client.read();
              if(c == 'm'){
                motorFlag = true;
                motor.write(0);
              }
              
            }
      
      
      //      while (client.available()) {
      //        char c = client.read();
      //        Serial.print(c);
      //
      //      }
      //      Serial.println();

      gas_value = 0;
      smoke_value = 0;
      temp = 0;
      Serial.print("GASVALUE = ");
      Serial.println(gas_value = analogRead(gas_sensor));

      Serial.print("SMOKEVALUE = ");
      Serial.println( smoke_value = analogRead(smoke_sensor));

      temp = analogRead(tempPin);
      temp = temp * 0.48828125;
      Serial.print("TEMPRATURE = ");
      Serial.print(temp);
      Serial.print("*C");
      Serial.println();

      if (gas_value > gas_pin_smoke_thresshold || gas_value > gas_pin_thresshold || temp > temp_pin_thresshold) {

        if (motorFlag) {
          motor.write(60);
          motorFlag = false;
        }
        digitalWrite(LED, HIGH);
        client.print(gas_value);
        client.print(" ");
        
        client.print(smoke_value);
        client.print(" ");
        
        client.print(temp);
        client.print(" ");
        client.println();

      }
      else {
        digitalWrite(LED, LOW);

      }

      delay(1000);
    }

  }
}
