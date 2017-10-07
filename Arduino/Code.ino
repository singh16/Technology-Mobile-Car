int trigPin = 2;
int echoPin = 4;

void setup() {
  Serial.begin(9600);
}

void loop(){
  long duration;
  float cm;
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);
  digitalWrite(trigPin, LOW);
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  cm = microsecondsToCentimeters(duration);
  Serial.print(cm);
  Serial.print("cm");
  Serial.println();
  delay(100);
} 
float microsecondsToCentimeters(long microseconds){
  return (microseconds*0.034029)/2;
}






#include <SoftwareSerial.h>

SoftwareSerial BT(10, 11); //TX, RX respetively
String device;

void setup() {
 BT.begin(9600);
 Serial.begin(9600);
  pinMode(3, OUTPUT);
  pinMode(4, OUTPUT);
  pinMode(5, OUTPUT);


}
//-----------------------------------------------------------------------//  
void loop() {
  while (BT.available()){  //Check if there is an available byte to read
  delay(10); //Delay added to make thing stable 
  char c = BT.read(); //Conduct a serial read
  device += c; //build the string.
  }  
  if (device.length() > 0) {
    Serial.println(device); 

  if(device == "doneon") 
  {
    digitalWrite(3, HIGH);
  } 
  
  else if(device == "doneoff") 
  {
    digitalWrite(3, LOW);
  }
  
  else if (device == "dtwoon")
  {
    digitalWrite (4,HIGH);
  }
  
 else if ( device == "dtwooff")
 {
   digitalWrite (4, LOW);
 }
 
 else if (device == "dthreeon")
 {
   digitalWrite (5, HIGH);
  
 }
 
 else if (device == "dthreeoff")
 {digitalWrite (5, LOW);}

device="";}} //Reset the variable















#include <SoftwareSerial.h>

SoftwareSerial BT(0, 1); //TX, RX respetively
String device;
int trigPin = 2;
int echoPin = 4;
void setup() {
 BT.begin(9600);
 Serial.begin(9600);
pinMode(13, OUTPUT);

}
//-----------------------------------------------------------------------//  
void loop() {
  long duration;
  float cm;
  int dl;

label:  while (BT.available()){  //Check if there is an available byte to read
  delay(10); //Delay added to make thing stable 
  char c = BT.read(); //Conduct a serial read
  device += c; //build the string.
  }  
  if (device.length() > 0) {
   
   
if(device=="uon")

  {    digitalWrite(8, HIGH);
    long duration;
  float cm;
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);
  digitalWrite(trigPin, LOW);
  delayMicroseconds(3);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  cm = microsecondsToCentimeters(duration);
  Serial.print(cm);
  Serial.println();
  delay(1000);
      goto label;
   }
  
  
  else if( device=="ton") 
  {
  digitalWrite(13, HIGH);
    
 
  }
    else if( device=="tof") 
  {
     digitalWrite(13, LOW);
    
  }
  
device="";}} //Reset the variable
float microsecondsToCentimeters(long microseconds){
  return (microseconds*0.034029)/2;
}


3 oct 2015
#include <SoftwareSerial.h>

SoftwareSerial BT(0, 1); //TX, RX respetively
String device;
int trigPin = 2;
int echoPin = 4;
void setup() {
 BT.begin(9600);
 Serial.begin(9600);
pinMode(11, OUTPUT);
pinMode(12, OUTPUT);

}
//-----------------------------------------------------------------------//  
void loop() {
  long duration;
  float cm;
  int dl;

label:  while (BT.available()){  //Check if there is an available byte to read
  delay(10); //Delay added to make thing stable 
  char c = BT.read(); //Conduct a serial read
  device += c; //build the string.
  }  
  if (device.length() > 0) {
   
   
if(device=="uon")

  {     
    long duration;
  float cm;
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);
  digitalWrite(trigPin, LOW);
  delayMicroseconds(3);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  cm = microsecondsToCentimeters(duration);
  Serial.print(cm);
  Serial.println();
  delay(1000);
      goto label;
   }
  
  
  else if( device=="ton") 
  {
  digitalWrite(11, HIGH);
    
 
  }
    else if( device=="tof") 
  {
     digitalWrite(11, LOW);
    
  }
   
  else if( device=="uof") 
  {
    
  digitalWrite(12, HIGH);
  delay(2000);
     digitalWrite(12, LOW);
 
  }
  
device="";}} //Reset the variable
float microsecondsToCentimeters(long microseconds){
  return (microseconds*0.034029)/2;
}







19 October 2015


#include <SoftwareSerial.h>

SoftwareSerial BT(0, 1); //TX, RX respetively

String device;
int trigPin = 10; // Ultrasonic 
int echoPin = 11;  // Ultrasonic

void setup() 
{
 BT.begin(9600);
 Serial.begin(9600);
pinMode(2,OUTPUT); // Front Right Window M Exite 
pinMode(3,OUTPUT); // Front Right Window Up and Down
pinMode(4,OUTPUT); // Front Left Window M1 M2 Exite 
pinMode(5,OUTPUT); // Front Left Window Up and Down
pinMode(6,OUTPUT); // Back Right Window M1 M2 Exite 
pinMode(7,OUTPUT); // Back Right Window Up and Down 
pinMode(8,OUTPUT); // Back Left Window M1 M2 Exite
pinMode(9,OUTPUT); // Back Left Window Up and Down 
pinMode(12,OUTPUT);// Side Miror M1 M2 Exite
pinMode(13,OUTPUT);// Side Miror Fold and Unfold 
pinMode(A2,OUTPUT);// Mute 1 Speaker 
pinMode(A3,OUTPUT);// Mute 2 Speaker
}

//-----------------------------------------------------------------------//  
void loop() {
  long duration;
  float cm;
  int dl;

label:  while (BT.available())  //Check if there is an available byte to read
{  
  delay(10); //Delay added to make thing stable 
  char c = BT.read(); //Conduct a serial read
  device += c; //build the string.
} 
  
  
  if (device.length() > 0) {
    
    
    if(device == "fru")    // Front Right Window UP
    {
      digitalWrite(2,HIGH);
      delay(10);
      digitalWrite(3,LOW);
      delay(3200);
      digitalWrite(2,LOW);
      delay(10);
      digitalWrite(3,LOW);
    }
    
    else if(device == "frd")    // Front Right Window Down
    {
      digitalWrite(2,HIGH);
      delay(10);
      digitalWrite(3,HIGH);
      delay(3200);
      digitalWrite(2,LOW);
      delay(10);
      digitalWrite(3,LOW);
    }   
   
   else if(device == "flu")    // Front Left Window Up
    {
      digitalWrite(4,HIGH);
      delay(10);
      digitalWrite(5,LOW);
      delay(3200);
      digitalWrite(4,LOW);
      delay(10);
      digitalWrite(5,LOW);
    } 
    
  else if(device == "fld")    // Front Left Window Down
    {
      digitalWrite(4,HIGH);
      delay(10);
      digitalWrite(5,HIGH);
      delay(3200);
      digitalWrite(4,LOW);
      delay(10);
      digitalWrite(5,LOW);
    }
    
  else if(device == "bru")    // Back Right Window Up
    {
      digitalWrite(6,HIGH);
      delay(10);
      digitalWrite(7,LOW);
      delay(3200);
      digitalWrite(6,LOW);
      delay(10);
      digitalWrite(7,LOW);
    }
    
  else if(device == "brd")    // Back Right Window Down
    {
      digitalWrite(6,HIGH);
      delay(10);
      digitalWrite(7,HIGH);
      delay(3200);
      digitalWrite(6,LOW);
      delay(10);
      digitalWrite(7,LOW);
    }
   
   else if(device == "blu")    // Back Left Window Up
    {
      digitalWrite(8,HIGH);
      delay(10);
      digitalWrite(9,LOW);
      delay(3200);
      digitalWrite(8,LOW);
      delay(10);
      digitalWrite(9,LOW);
    }
    
   else if(device == "bld")    // Back Left Window Down
    {
      digitalWrite(8,HIGH);
      delay(10);
      digitalWrite(9,HIGH);
      delay(3200);
      digitalWrite(8,LOW);
      delay(10);
      digitalWrite(9,LOW);
    }
   
    else if(device == "smf")    // Side Miror Fold 
    {
      digitalWrite(12,HIGH);
      delay(10);
      digitalWrite(13,LOW);
      delay(3200);
      digitalWrite(12,LOW);
      delay(10);
      digitalWrite(13,LOW);
    }
    
  else if(device == "smu")    // Side Miror UnFold 
    {
      digitalWrite(12,HIGH);
      delay(10);
      digitalWrite(13,HIGH);
      delay(3200);
      digitalWrite(12,LOW);
      delay(10);
      digitalWrite(13,LOW);
    }
   
   
  else if(device=="uon")    // Ultrasonic On

  {     
    long duration;
  float cm;
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);
  digitalWrite(trigPin, LOW);
  delayMicroseconds(3);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  cm = microsecondsToCentimeters(duration);
  Serial.print(cm);
  Serial.println();
  delay(1000);
      goto label;
   }
  
    else if( device=="uof") // Ultrasonic OFF
  {
   device="";
  }
  
  else if( device=="teon") // Engine Temprature ON
  {
float temp1;
temp1 = analogRead(A0);
temp1 = temp1 * 0.48828125;
Serial.print(temp1);
Serial.print("*C");
Serial.println();
delay(1000);
goto label;
  }
    
   else if( device=="teof") // Engine Temprature OFF
  {
   device="";
  }
   
  else if( device=="tion") // Inside Temprature ON
  {
float temp2;
temp2 = analogRead(A1);
temp2 = temp2 * 0.48828125;
Serial.print(temp2);
Serial.print("*C");
Serial.println();
delay(1000);
goto label;
  }
    
   else if( device=="tiof") // Inside Temprature OFF
  {
   device="";
  }
  
    else if( device=="mson")  // Mudic Speaker On
  {
    
  digitalWrite(A2, LOW);
  digitalWrite(A3, LOW);
 
  }
  
 else if( device=="msof")  // Mudic Speaker OFF
  {
    
  digitalWrite(A2, HIGH);
  digitalWrite(A3, HIGH);
 
  }
  
  


device="";}} //Reset the variable


float microsecondsToCentimeters(long microseconds)
{
  return (microseconds*0.034029)/2;
}
#include <SoftwareSerial.h>

SoftwareSerial BT(0, 1); //TX, RX respetively

String device;
int trigPin = 10; // Ultrasonic 
int echoPin = 11;  // Ultrasonic

void setup() 
{
 BT.begin(9600);
 Serial.begin(9600);
pinMode(2,OUTPUT); // Front Right Window M Exite 
pinMode(3,OUTPUT); // Front Right Window Up and Down
pinMode(4,OUTPUT); // Front Left Window M1 M2 Exite 
pinMode(5,OUTPUT); // Front Left Window Up and Down
pinMode(6,OUTPUT); // Back Right Window M1 M2 Exite 
pinMode(7,OUTPUT); // Back Right Window Up and Down 
pinMode(8,OUTPUT); // Back Left Window M1 M2 Exite
pinMode(9,OUTPUT); // Back Left Window Up and Down 
pinMode(12,OUTPUT);// Side Miror M1 M2 Exite
pinMode(13,OUTPUT);// Side Miror Fold and Unfold 
pinMode(A2,OUTPUT);// Mute 1 Speaker 
pinMode(A3,OUTPUT);// Mute 2 Speaker
}

//-----------------------------------------------------------------------//  
void loop() {
  long duration;
  float cm;
  int dl;

label:  while (BT.available())  //Check if there is an available byte to read
{  
  delay(10); //Delay added to make thing stable 
  char c = BT.read(); //Conduct a serial read
  device += c; //build the string.
} 
  
  
  if (device.length() > 0) {
    
    
    if(device == "fru")    // Front Right Window UP
    {
      digitalWrite(2,HIGH);
      delay(10);
      digitalWrite(3,LOW);
      delay(3200);
      digitalWrite(2,LOW);
      delay(10);
      digitalWrite(3,LOW);
    }
    
    else if(device == "frd")    // Front Right Window Down
    {
      digitalWrite(2,HIGH);
      delay(10);
      digitalWrite(3,HIGH);
      delay(3200);
      digitalWrite(2,LOW);
      delay(10);
      digitalWrite(3,LOW);
    }   
   
   else if(device == "flu")    // Front Left Window Up
    {
      digitalWrite(4,HIGH);
      delay(10);
      digitalWrite(5,LOW);
      delay(3200);
      digitalWrite(4,LOW);
      delay(10);
      digitalWrite(5,LOW);
    } 
    
  else if(device == "fld")    // Front Left Window Down
    {
      digitalWrite(4,HIGH);
      delay(10);
      digitalWrite(5,HIGH);
      delay(3200);
      digitalWrite(4,LOW);
      delay(10);
      digitalWrite(5,LOW);
    }
    
  else if(device == "bru")    // Back Right Window Up
    {
      digitalWrite(6,HIGH);
      delay(10);
      digitalWrite(7,LOW);
      delay(3200);
      digitalWrite(6,LOW);
      delay(10);
      digitalWrite(7,LOW);
    }
    
  else if(device == "brd")    // Back Right Window Down
    {
      digitalWrite(6,HIGH);
      delay(10);
      digitalWrite(7,HIGH);
      delay(3200);
      digitalWrite(6,LOW);
      delay(10);
      digitalWrite(7,LOW);
    }
   
   else if(device == "blu")    // Back Left Window Up
    {
      digitalWrite(8,HIGH);
      delay(10);
      digitalWrite(9,LOW);
      delay(3200);
      digitalWrite(8,LOW);
      delay(10);
      digitalWrite(9,LOW);
    }
    
   else if(device == "bld")    // Back Left Window Down
    {
      digitalWrite(8,HIGH);
      delay(10);
      digitalWrite(9,HIGH);
      delay(3200);
      digitalWrite(8,LOW);
      delay(10);
      digitalWrite(9,LOW);
    }
   
    else if(device == "smf")    // Side Miror Fold 
    {
      digitalWrite(12,HIGH);
      delay(10);
      digitalWrite(13,LOW);
      delay(3200);
      digitalWrite(12,LOW);
      delay(10);
      digitalWrite(13,LOW);
    }
    
  else if(device == "smu")    // Side Miror UnFold 
    {
      digitalWrite(12,HIGH);
      delay(10);
      digitalWrite(13,HIGH);
      delay(3200);
      digitalWrite(12,LOW);
      delay(10);
      digitalWrite(13,LOW);
    }
   
   
  else if(device=="uon")    // Ultrasonic On

  {     
    long duration;
  float cm;
  pinMode(echoPin, INPUT);
  pinMode(trigPin, OUTPUT);
  digitalWrite(trigPin, LOW);
  delayMicroseconds(3);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  cm = microsecondsToCentimeters(duration);
  Serial.print(cm);
  Serial.println();
  delay(1000);
      goto label;
   }
  
    else if( device=="uof") // Ultrasonic OFF
  {
   device="";
  }
  
  else if( device=="teon") // Engine Temprature ON
  {
float temp1;
temp1 = analogRead(A0);
temp1 = temp1 * 0.48828125;
Serial.print(temp1);
Serial.print("*C");
Serial.println();
delay(1000);
goto label;
  }
    
   else if( device=="teof") // Engine Temprature OFF
  {
   device="";
  }
   
  else if( device=="tion") // Inside Temprature ON
  {
float temp2;
temp2 = analogRead(A1);
temp2 = temp2 * 0.48828125;
Serial.print(temp2);
Serial.print("*C");
Serial.println();
delay(1000);
goto label;
  }
    
   else if( device=="tiof") // Inside Temprature OFF
  {
   device="";
  }
  
    else if( device=="mson")  // Mudic Speaker On
  {
    
  digitalWrite(A2, LOW);
  digitalWrite(A3, LOW);
 
  }
  
 else if( device=="msof")  // Mudic Speaker OFF
  {
    
  digitalWrite(A2, HIGH);
  digitalWrite(A3, HIGH);
 
  }
  
  


device="";}} //Reset the variable


float microsecondsToCentimeters(long microseconds)
{
  return (microseconds*0.034029)/2;
}

