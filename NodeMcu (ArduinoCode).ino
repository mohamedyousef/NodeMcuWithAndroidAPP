/*********
  Rui Santos
  Complete project details at http://randomnerdtutorials.com  
*********/

// Load Wi-Fi library
#include <ESP8266WiFi.h>
#define WATER 15 // d8
#define in1 2  // d4  
#define in2 0  // d3
#define in3 12 // d6
#define in4 13 // d7

// Replace with your network credentials
const char* ssid     = "nodemcu";
const char* password = "00112233";

// Set web server port number to 80
WiFiServer server(80);
int sensor  = 0; 
int water = 0 ; 
int state = 0; 
int autoorman = 0; 

// Variable to store the HTTP request
String header;


 
void setup() {
  Serial.begin(115200);
  pinMode(A0, INPUT);
  pinMode(WATER, OUTPUT);
  pinMode(in1,OUTPUT);
  pinMode(in2,OUTPUT);
  pinMode(in3,OUTPUT);
  pinMode(in4,OUTPUT);  
  
  // Connect to Wi-Fi network with SSID and password
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  // Print local IP address and start web server
  Serial.println("");
  Serial.println("WiFi connected.");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  server.begin();
   
}

void loop(){
  
  WiFiClient client = server.available();   // Listen for incoming clients

  if (client) {                             // If a new client connects,
    Serial.println("New Client.");          // print a message out in the serial port
    String currentLine = "";                // make a String to hold incoming data from the client
    while (client.connected()) {            // loop while the client's connected
      if (client.available()) {             // if there's bytes to read from the client,
        char c = client.read();             // read a byte, then
        Serial.write(c);                    // print it out the serial monitor
        header += c;
        if (c == '\n') {                    // if the byte is a newline character
          // if the current line is blank, you got two newline characters in a row.
          // that's the end of the client HTTP request, so send a response:
          if (currentLine.length() == 0) {
            // HTTP headers always start with a response code (e.g. HTTP/1.1 200 OK)
            // and a content-type so the client knows what's coming, then a blank line:
            client.println("HTTP/1.1 200 OK");
            client.println("Content-type:text/html");
            client.println("Connection: close");
            client.println();
            
            // turns the GPIOs on and off
            if (header.indexOf("GET /5/on") >= 0) {
              Serial.println("GPIO 5 on");
              
            } else if (header.indexOf("GET /5/off") >= 0) {
              Serial.println("GPIO 5 off");
              
             
            } else if (header.indexOf("GET /autoorman/man") >= 0) {
               autoorman = 0; 
            } else if (header.indexOf("GET /autoorman/auto") >= 0) {
               autoorman = 1 ;             
            }           
            // Display the HTML web page
            client.println("<!DOCTYPE html><html>");
            client.println("<head><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            client.println("<link rel=\"icon\" href=\"data:,\">");
            // CSS to style the on/off buttons 
            // Feel free to change the background-color and font-size attributes to fit your preferences
            client.println("<style>html { font-family: Helvetica; display: inline-block; margin: 0px auto; text-align: center;}");
            client.println(".button { background-color: #195B6A; border: none; color: white; padding: 16px 40px;");
            client.println("text-decoration: none; font-size: 30px; margin: 2px; cursor: pointer;}");
            client.println(".button2 {background-color: #77878A;}</style></head>");
            
            // Web Page Heading
            client.println("<body><h1>3TIA Project :r</h1>");
            
            // Display current state, and ON/OFF buttons for GPIO 4  
            if (header.indexOf("GET /sensor/") >= 0){
              sensor  = get_sensor_Data();
            client.println("<p>Sensor Value :"+ String (a) +"</p>");
            }
            else if(header.indexOf("GET /water/run") >= 0){
              water = 1;
            }
            else if(header.indexOf("GET /water/stop") >= 0) 
               water = 0 ;
            else if(header.indexOf("GET /motor/for") >= 0) 
               state = 1 ;
            else if(header.indexOf("GET /motor/stop") >= 0) 
               state = 0 ;   
            else if(header.indexOf("GET /motor/rev") >= 0) 
               state = 2 ;
            client.println("</body></html>");
            
            // The HTTP response ends with another blank line
            client.println();
            // Break out of the while loop
            break;
          } else { // if you got a newline, then clear currentLine
            currentLine = "";
          }
        } else if (c != '\r') {  // if you got anything else but a carriage return character,
          currentLine += c;      // add it to the end of the currentLine
        }
      }
    }
    // Clear the header variable
    header = "";
    // Close the connection
    client.stop();
    Serial.println("Client disconnected.");
    Serial.println("");
  }
waterMotor();
if (autoorman == 0)
manual();
else if(autoorman == 1) 
Auto();
}
void Auto(){ 
 sensor = get_sensor_Data();
  if(sensor =< 20) {
    gotoForward(); 
  }
  else{
   Stop();
  }
}
void manual(){
  if (state == 0 ) 
    Stop();
  else if(state ==  1) 
    gotoForward();
  else if(state == 2) 
    gotoReverse();
}
void Stop(){ 
  digitalWrite(in1,LOW);
  digitalWrite(in2,LOW);   
  digitalWrite(in3,LOW);
  digitalWrite(in4,LOW);
  state = 0  ; 
  //Serial.println("stop");
}
void waterMotor(){
  if(water == 0)  { 
     digitalWrite(WATER,LOW);
  }
 else{
    digitalWrite(WATER,HIGH);
   }
}
void gotoForward(){   
  //digitalWrite(ina, HIGH);
  digitalWrite(in1,HIGH);    
  digitalWrite(in2,LOW);  
  digitalWrite(in3,HIGH);   
  digitalWrite(in4,LOW);
  //Serial.println("forward");
}
void gotoReverse(){ 
  //digitalWrite(ina, HIGH);
  digitalWrite(in2,HIGH); 
  digitalWrite(in1,LOW);  
  digitalWrite(in4,HIGH); 
  digitalWrite(in3,LOW);
  //Serial.println("reverse");
 }
int get_sensor_Data(){ 
    int output_value = analogRead(A0);
    output_value = map(output_value,550,0,0,100);
    //Serial.println(output_value); 
    return output_value;
}
